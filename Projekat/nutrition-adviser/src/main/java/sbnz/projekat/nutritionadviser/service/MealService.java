package sbnz.projekat.nutritionadviser.service;

import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.dto.FilterDTO;
import sbnz.projekat.nutritionadviser.dto.GroceriesQuantityDTO;
import sbnz.projekat.nutritionadviser.dto.MealDTO;
import sbnz.projekat.nutritionadviser.model.Alarm;
import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.GrocerieList;
import sbnz.projekat.nutritionadviser.model.GrocerieQuantity;
import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.model.MissingGroceries;
import sbnz.projekat.nutritionadviser.model.PossibleMeals;
import sbnz.projekat.nutritionadviser.model.UserData;
import sbnz.projekat.nutritionadviser.repository.GrocerieQuantityRepository;
import sbnz.projekat.nutritionadviser.repository.GrocerieRepository;
import sbnz.projekat.nutritionadviser.repository.MealRepository;

@Service
public class MealService {

	private final MealRepository mealRepository;
	private final GrocerieRepository grocerieRepository;
	private final GrocerieQuantityRepository grocerieQuantityRepository;
	private final KieContainer kieContainer;

	@Autowired
	public MealService(MealRepository mealRepository, GrocerieRepository grocerieRepository,
			GrocerieQuantityRepository grocerieQuantityRepository, KieContainer kieContainer) {
		this.kieContainer = kieContainer;
		this.mealRepository = mealRepository;
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
	
	public Alarm userGrocerieAllergie(UserData data, Grocerie grocerie) {
		KieSession kieSession = kieContainer.newKieSession("session");
		kieSession.insert(grocerie);
		kieSession.insert(data);
		kieSession.getAgenda().getAgendaGroup("user-meal").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila: " + numOfRules);
		
		QueryResults results = kieSession.getQueryResults("Get alarm");

		Alarm alarm = null;
		for (QueryResultsRow queryResultsRow : results) {
			alarm = (Alarm) queryResultsRow.get("$alarm");
			
			System.out.println("Alarm " + alarm.getMessage());
		}
		
		
		kieSession.dispose();
		
		return alarm;
	}
	
	public Alarm userMealAllergie(UserData data, Meal meal) {
		KieSession kieSession = kieContainer.newKieSession("session");
		kieSession.insert(meal);
		kieSession.insert(data);
		kieSession.getAgenda().getAgendaGroup("user-meal").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila: " + numOfRules);
		
		QueryResults results = kieSession.getQueryResults("Get alarm");

		Alarm alarm = null;
		for (QueryResultsRow queryResultsRow : results) {
			alarm = (Alarm) queryResultsRow.get("$alarm");
			
			System.out.println("Alarm " + alarm.getMessage());
		}
		
		kieSession.dispose();
		
		return alarm;
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

}
