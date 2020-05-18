package sbnz.projekat.nutritionadviser.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.repository.MealRepository;

@Service
public class MealService {
	
	
	private final MealRepository mealRepository;
	//private final KieContainer kieContainer;
	
	@Autowired
	public MealService(MealRepository mealRepository/*, KieContainer kieContainer*/) {
		//this.kieContainer = kieContainer;
		this.mealRepository = mealRepository;
	}
	
	public List<Meal> getAll() {
		return this.mealRepository.findAll();
	}
/*
	public Meal calculateCalories(Meal meal){
		
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(meal);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return meal;
	}
	*/
}
