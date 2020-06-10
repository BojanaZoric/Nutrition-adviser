package sbnz.projekat.nutritionadviser.rules;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

import sbnz.projekat.nutritionadviser.model.Allergen;
import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.GrocerieQuantity;
import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.model.UserData;
import sbnz.projekat.nutritionadviser.service.MealService;

public class MealRulesTest {
	
	private MealService mealService;

	@Before
	public void setUp() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("sbnz.projekat", "nutrition-adviser-drools", "0.0.1-SNAPSHOT"));
		
		mealService = new MealService(null, null, null, kContainer);
	}
	
	
	@Test
	public void calculateCalories() {
		
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false);
		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		Meal saved = mealService.calculateCalories(meal);
		
		assertNotNull(saved.getCalories());
		assertEquals(600, saved.getCalories());
		
		assertNotNull(saved.getProteinAmount());
		assertEquals(35, saved.getProteinAmount());
		
		assertNotNull(saved.getCarbohydrateAmount());
		assertEquals(60, saved.getCarbohydrateAmount());
	}
	
	@Test
	public void userGroceriHasAllergie() {
		
		Allergen a = new Allergen();
		a.setId(new Long(5));
		
		UserData data = new UserData();
		data.setAllergies(new HashSet<>());
		data.getAllergies().add(a);
		
		Grocerie grocerie = new Grocerie();
		grocerie.setAllergens(new HashSet<>());
		grocerie.getAllergens().add(a);
		
		mealService.userGrocerieAllergie(data, grocerie);
	}
	
	@Test
	public void userGroceriNotHasAllergie() {
		
		Allergen a = new Allergen();
		Allergen b = new Allergen();
		a.setId(new Long(5));
		
		UserData data = new UserData();
		data.setAllergies(new HashSet<>());
		data.getAllergies().add(a);
		
		Grocerie grocerie = new Grocerie();
		grocerie.setAllergens(new HashSet<>());
		grocerie.getAllergens().add(b);
		
		mealService.userGrocerieAllergie(data, grocerie);
	}

}
