package sbnz.projekat.nutritionadviser.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.projekat.nutritionadviser.event.EatingMealEvent;
import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.GrocerieList;
import sbnz.projekat.nutritionadviser.model.GrocerieQuantity;
import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.model.PossibleMeals;
import sbnz.projekat.nutritionadviser.model.User;
import sbnz.projekat.nutritionadviser.repository.MealRepository;
import sbnz.projekat.nutritionadviser.service.MealService;

public class CepTest {

	private MealService mealService;

	@Before
	public void setUp() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("sbnz.projekat", "nutrition-adviser-drools", "0.0.1-SNAPSHOT"));
		
		mealService = new MealService(null, null, null, null, kContainer);
	}

	@Test
	public void eatingMealEvent_false() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false);
		Meal meal1 = new Meal();
		meal1.setGroceries(new HashSet<>());
		meal1.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal1));
		meal1.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal1));
		
		Meal meal2 = new Meal();
		meal2.setGroceries(new HashSet<>());
		meal2.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal1));
		
		User u = new User("usetr", "pass", "Name", "Surname", null);
		u.setId(new Long(25));
		Date date = new Date(System.currentTimeMillis());
		EatingMealEvent eatingEvent = new EatingMealEvent(date, u, meal2);
		EatingMealEvent eatingEvent2 = new EatingMealEvent(date, u, meal1);
		EatingMealEvent eatingEvent3 = new EatingMealEvent(date, u, meal1);
		EatingMealEvent eatingEvent4 = new EatingMealEvent(date, u, meal1);

		KieSession kieSession = mealService.makeSession();
		
		boolean isAllowed = mealService.addEatingMealEvent(kieSession, eatingEvent);
		assertEquals(true, isAllowed);
		isAllowed = mealService.addEatingMealEvent(kieSession, eatingEvent2);
		assertEquals(true, isAllowed);
		isAllowed = mealService.addEatingMealEvent(kieSession, eatingEvent3);
		assertEquals(false, isAllowed);
		isAllowed = mealService.addEatingMealEvent(kieSession, eatingEvent4);
		
		assertEquals(false, isAllowed);

	}
	
	
}
