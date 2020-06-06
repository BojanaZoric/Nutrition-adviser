package sbnz.projekat.nutritionadviser.service;

import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.dto.GroceriesQuantityDTO;
import sbnz.projekat.nutritionadviser.dto.MealDTO;
import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.GrocerieQuantity;
import sbnz.projekat.nutritionadviser.model.Meal;
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

	public Meal calculateCalories(Meal meal) {

		KieSession kieSession = kieContainer.newKieSession("session");
		kieSession.insert(meal);
		kieSession.fireAllRules();
		kieSession.dispose();

		return meal;
	}

}
