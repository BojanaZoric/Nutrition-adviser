package sbnz.projekat.nutritionadviser.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.model.Meal;

@Service
public class MealService {

	private final KieContainer kieContainer;
	
	@Autowired
	public MealService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public Meal calculateCalories(Meal meal){
		
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(meal);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return meal;
	}
	
}
