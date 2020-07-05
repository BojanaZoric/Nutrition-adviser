package sbnz.projekat.nutritionadviser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.dto.FilterDTO;
import sbnz.projekat.nutritionadviser.dto.GroceriesQuantityDTO;
import sbnz.projekat.nutritionadviser.dto.MealDTO;
import sbnz.projekat.nutritionadviser.event.EatingMealEvent;
import sbnz.projekat.nutritionadviser.model.Alarm;
import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.GrocerieIdList;
import sbnz.projekat.nutritionadviser.model.GrocerieList;
import sbnz.projekat.nutritionadviser.model.GrocerieQuantity;
import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.model.MissingGroceries;
import sbnz.projekat.nutritionadviser.model.PossibleMeals;
import sbnz.projekat.nutritionadviser.model.User;
import sbnz.projekat.nutritionadviser.model.UserData;
import sbnz.projekat.nutritionadviser.repository.GrocerieQuantityRepository;
import sbnz.projekat.nutritionadviser.repository.GrocerieRepository;
import sbnz.projekat.nutritionadviser.repository.MealRepository;
import sbnz.projekat.nutritionadviser.repository.UserRepository;

@Service
public class MealService {

	private final MealRepository mealRepository;
	private final UserRepository userRepository;
	private final GrocerieRepository grocerieRepository;
	private final GrocerieQuantityRepository grocerieQuantityRepository;
	private final KieContainer kieContainer;

	@Autowired
	public MealService(MealRepository mealRepository, UserRepository userRepository, GrocerieRepository grocerieRepository,
			GrocerieQuantityRepository grocerieQuantityRepository, KieContainer kieContainer) {
		this.kieContainer = kieContainer;
		this.mealRepository = mealRepository;
		this.userRepository = userRepository;
		this.grocerieRepository = grocerieRepository;
		this.grocerieQuantityRepository = grocerieQuantityRepository;
	}

	public List<Meal> getAll() {
		return this.mealRepository.findAll();
	}

	public Meal getOne(Long id) {
		Optional<Meal> meal = this.mealRepository.findById(id);
		
		if(meal.isPresent()) {
			return meal.get();
		}
		
		return null;
	}

	public Meal save(MealDTO dto) {

		Meal m = new Meal();

		m.setName(dto.getName());
		m.setCarbohydrateAmount(dto.getCarbohydrateAmount());
		m.setCalories(dto.getCalories());
		m.setDescription(dto.getDescription());
		m.setInstructions(dto.getInstructions());
		m.setPreparationTime(dto.getPreparationTime());
		m.setProteinAmount(dto.getProteinAmount());
		
		

		for (GroceriesQuantityDTO gqDTO : dto.getGroceries()) {
			Grocerie groc = grocerieRepository.getOne(gqDTO.getGrocerie_id());

			GrocerieQuantity gq = new GrocerieQuantity(null, groc, gqDTO.getQuantity(), m);

			m.getGroceries().add(gq);
			//this.grocerieQuantityRepository.save(gq);
		}

		this.calculateCalories(m);
		
		Meal saved = mealRepository.save(m);
		
		return saved;
	}
	
	// da li je pogodno za moje zdravstveno stanje?
	public List<Alarm> canIEatThisMeal(Long mealId){
		
		Optional<Meal> meal = mealRepository.findById(mealId);
		
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findOneByUsername(username);
		
		if(meal.isPresent() && username != null) {
		
			return this.userMealAllergie(user.getUserData(), meal.get());
		}
		
		return null;
	}
	
	// da li je pogodno za moje zdravstveno stanje?
	public List<Alarm> canIEatThisGrocerie(Long grocerieId){
		
		Optional<Grocerie> grocerie = grocerieRepository.findById(grocerieId);
		
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findOneByUsername(username);
		
		if(grocerie.isPresent() && username != null) {
		
			return this.userGrocerieAllergie(user.getUserData(), grocerie.get());
		}
		
		return null;
	}
	
	// filter
	public PossibleMeals getFilteredMeals(FilterDTO dto) {
		
		List<Meal> meals = this.mealRepository.findAll();
		
		PossibleMeals pm = this.filterMeals(meals, dto);
		
		return pm;
	}
	
	public PossibleMeals getMealsHasAllGroceries(GrocerieIdList grocerieIdList) {
		PossibleMeals pm = new PossibleMeals();
		
		
		GrocerieList grocerieList = new GrocerieList();
		grocerieList.setGrocerieList(new ArrayList<>());
		
		for (Long id : grocerieIdList.getGrocerieList()) {
			Optional<Grocerie> groc = this.grocerieRepository.findById(id);
			
			if(groc.isPresent()) {
				grocerieList.getGrocerieList().add(groc.get());
			}
		}
		
		List<Meal> allMeals = this.mealRepository.findAll();
		KieSession kieSession = kieContainer.newKieSession("session");
		kieSession.insert(pm);
		kieSession.insert(grocerieList);

		for (Meal meal : allMeals) {
			kieSession.insert(meal);
		}
		
		kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila (checkIfMealHasAllGroceries): " + numOfRules);
		System.out.println(pm.getMeals().size());
		
		kieSession.dispose();
		return pm;
	}
	
	public PossibleMeals getMealsHasAllGroceriesAndMore(GrocerieIdList grocerieIdList) {
		
		GrocerieList grocerieList = new GrocerieList();
		grocerieList.setGrocerieList(new ArrayList<>());
		
		for (Long id : grocerieIdList.getGrocerieList()) {
			Optional<Grocerie> groc = this.grocerieRepository.findById(id);
			
			if(groc.isPresent()) {
				grocerieList.getGrocerieList().add(groc.get());
			}
		}
		
		List<Meal> allMeals = this.mealRepository.findAll();
		KieSession kieSession = kieContainer.newKieSession("session");
		PossibleMeals pm = new PossibleMeals();
		kieSession.insert(pm);
		kieSession.insert(grocerieList);

		for (Meal meal : allMeals) {
			kieSession.insert(meal);
		}
		
		kieSession.getAgenda().getAgendaGroup("recommendation-more").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila (checkIfMealHasAllGroceriesAndMore): " + numOfRules);
		System.out.println(pm.getMeals().size());
		
		kieSession.dispose();
		
		return pm;
	}
	
	public MissingGroceries getMissingGroceries(GrocerieIdList grocerieIdList) {
		MissingGroceries mg = new MissingGroceries();
		
		GrocerieList grocerieList = new GrocerieList();
		grocerieList.setGrocerieList(new ArrayList<>());
		
		for (Long id : grocerieIdList.getGrocerieList()) {
			Optional<Grocerie> groc = this.grocerieRepository.findById(id);
			
			if(groc.isPresent()) {
				grocerieList.getGrocerieList().add(groc.get());
			}
		}
		
		List<Meal> allMeals = this.mealRepository.findAll();
		
		for (Meal meal : allMeals) {
			mg = this.findMissingGroceriesFromMeal(grocerieList, meal);
		}
		
		return mg;
	}
	
	
	// ------- pravila -------
	
	
	public List<Alarm> userGrocerieAllergie(UserData data, Grocerie grocerie) {
		KieSession kieSession = kieContainer.newKieSession("session");
		kieSession.insert(grocerie);
		kieSession.insert(data);
		kieSession.getAgenda().getAgendaGroup("user-meal").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila: " + numOfRules);
		
		QueryResults results = kieSession.getQueryResults("Get alarm");

		ArrayList<Alarm> alarms = new ArrayList<>();
		Alarm alarm = null;
		for (QueryResultsRow queryResultsRow : results) {
			alarm = (Alarm) queryResultsRow.get("$alarm");
			alarms.add(alarm);
			System.out.println("Alarm " + alarm.getMessage());
		}
		
		
		kieSession.dispose();
		
		return alarms;
	}
	
	public List<Alarm> userMealAllergie(UserData data, Meal meal) {
		KieSession kieSession = kieContainer.newKieSession("session");
		kieSession.insert(meal);
		kieSession.insert(data);
		kieSession.getAgenda().getAgendaGroup("user-meal").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila: " + numOfRules);
		
		QueryResults results = kieSession.getQueryResults("Get alarm");

		ArrayList<Alarm> alarms = new ArrayList<>();
		Alarm alarm = null;
		for (QueryResultsRow queryResultsRow : results) {
			alarm = (Alarm) queryResultsRow.get("$alarm");
			alarms.add(alarm);
			System.out.println("Alarm " + alarm.getMessage());
		}
		
		kieSession.dispose();
		
		return alarms;
	}

	public Meal calculateCalories(Meal meal) {

		KieSession kieSession = kieContainer.newKieSession("session");

		kieSession.insert(meal);
		kieSession.getAgenda().getAgendaGroup("meal").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila: " + numOfRules);
	
		
		kieSession.dispose();

		return meal;
	}
	
	
	public PossibleMeals checkIfMealHasAllGroceries(GrocerieList grocerieList, Meal meal) {

		KieSession kieSession = kieContainer.newKieSession("session");
		PossibleMeals pm = new PossibleMeals();
		kieSession.insert(pm);
		kieSession.insert(meal);
		kieSession.insert(grocerieList);
		kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila (checkIfMealHasAllGroceries): " + numOfRules);
	
		
		kieSession.dispose();

		return pm;
	}
	
	public PossibleMeals checkIfMealHasAllGroceriesAndMore(GrocerieList grocerieList, Meal meal) {

		KieSession kieSession = kieContainer.newKieSession("session");
		PossibleMeals pm = new PossibleMeals();
		kieSession.insert(pm);
		kieSession.insert(meal);
		kieSession.insert(grocerieList);
		kieSession.getAgenda().getAgendaGroup("recommendation-more").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila (checkIfMealHasAllGroceriesAndMore): " + numOfRules);
		System.out.println(pm.getMeals().size());
		
		kieSession.dispose();

		return pm;
	}
	

	public PossibleMeals filterMeals(List<Meal> meals, FilterDTO filterDTO) {
		
		KieSession kieSession = kieContainer.newKieSession("session");
		PossibleMeals pm = new PossibleMeals();
		kieSession.insert(pm);
		
		for (Meal meal : meals) {
			kieSession.insert(meal);
		}
		
		kieSession.insert(filterDTO);
		kieSession.getAgenda().getAgendaGroup("filter").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila: " + numOfRules);
	
		
		kieSession.dispose();

		return pm;
	}
	public MissingGroceries findMissingGroceriesFromMeal(GrocerieList grocerieList, Meal meal) {

		KieSession kieSession = kieContainer.newKieSession("session");
		MissingGroceries mg = new MissingGroceries();
		kieSession.insert(mg);
		kieSession.insert(meal);
		kieSession.insert(grocerieList);
		kieSession.getAgenda().getAgendaGroup("recommendation-missing").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila (findMissingGroceriesFromMeal): " + numOfRules);
	
		kieSession.dispose();

		return mg;
	}

	public boolean calorieLimitExceed(UserData details) {

		KieSession kieSession = kieContainer.newKieSession("session");
		boolean exceed = false;
		System.out.println("UDDDDaDDD" +details.getDetails().size());
		kieSession.insert(details);
		kieSession.getAgenda().getAgendaGroup("calorie-limit").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila (calorieLimitExceed): " + numOfRules);
	
		QueryResults results = kieSession.getQueryResults("Get alarm");

		Alarm alarm = null;
		for (QueryResultsRow queryResultsRow : results) {
			alarm = (Alarm) queryResultsRow.get("$alarm");
			
			System.out.println("Alarm " + alarm.getMessage());
			exceed = true;
		}
		
		kieSession.dispose();

		return exceed;
	}
	
	public KieSession makeSession() {
		KieSession kieSession = kieContainer.newKieSession("session");
		return kieSession;
	}
	
	public boolean addEatingMealEvent(KieSession kieSession , EatingMealEvent eme) {

		//KieSession kieSession = kieContainer.newKieSession("session");
		MissingGroceries mg = new MissingGroceries();
		kieSession.insert(eme);
		kieSession.getAgenda().getAgendaGroup("allowed-to-eat").setFocus();

		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila (addEatingMealEvent): " + numOfRules);
	
		/// Get Suspicious User Event
		
		//kieSession.dispose();

		return eme.getUser().isAllowedToEat();
	}

	
	public List<Meal> recommendForUser(List<Meal> allMeals, UserData data){
		
		List<Meal> meals = new ArrayList<Meal>();
		
		KieSession kieSession = kieContainer.newKieSession("session");
		
		kieSession.insert(data);
		for (Meal meal : allMeals) {
			System.out.println(meal.getComponents().size());
			kieSession.insert(meal);
		}
		
		kieSession.getAgenda().getAgendaGroup("filter-and-recommend").setFocus();

		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila (recommendForUser): " + numOfRules);
	
		QueryResults results = kieSession.getQueryResults("get meals");

		for (QueryResultsRow queryResultsRow : results) {
			Meal meal = (Meal) queryResultsRow.get("$meal");
			
			meals.add(meal);
		}
		
		kieSession.dispose();
		
		return meals;
	}
}
