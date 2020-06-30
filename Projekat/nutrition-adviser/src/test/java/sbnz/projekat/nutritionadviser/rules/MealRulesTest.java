package sbnz.projekat.nutritionadviser.rules;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.rule.QueryResults;

import sbnz.projekat.nutritionadviser.model.Alarm;
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
		
		Alarm alarm = mealService.userGrocerieAllergie(data, grocerie);
		
		assertNotNull(alarm);
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
		
		Alarm alarm = mealService.userGrocerieAllergie(data, grocerie);
		assertNull(alarm);
	}
	
	@Test
	public void userMealHasAllergie() {
		//alergican na obrok
		Allergen a = new Allergen();
		a.setId(new Long(5));
		
		UserData data = new UserData();
		data.setAllergies(new HashSet<>());
		data.getAllergies().add(a);
		
		Grocerie grocerie = new Grocerie();
		grocerie.setAllergens(new HashSet<>());
		grocerie.getAllergens().add(a);
		grocerie.setCalories(100);
		grocerie.setCarbohydrateAmount(50.0);
		grocerie.setProteinAmount(10.0);
		
		Meal meal = new Meal();
		meal.setName("Pizza");
		
		meal.setGroceries(new HashSet<>());
		
		meal.getGroceries().add(new GrocerieQuantity(10l, grocerie, 200.0, meal));
		
		Alarm alarm = mealService.userMealAllergie(data, meal);
		
		assertNotNull(alarm);
	}
	
	@Test
	public void userMealNotHasAllergie() {
		//alergican na obrok
		Allergen a = new Allergen();
		Allergen b = new Allergen();
		a.setId(new Long(5));
		b.setId(new Long(55));
		UserData data = new UserData();
		data.setAllergies(new HashSet<>());
		data.getAllergies().add(a);
		
		Grocerie grocerie = new Grocerie();
		grocerie.setAllergens(new HashSet<>());
		grocerie.getAllergens().add(b);
		grocerie.setCalories(100);
		grocerie.setCarbohydrateAmount(50.0);
		grocerie.setProteinAmount(10.0);
		
		Meal meal = new Meal();
		meal.setName("Pizza");
		
		meal.setGroceries(new HashSet<>());
		
		meal.getGroceries().add(new GrocerieQuantity(10l, grocerie, 200.0, meal));
		
		Alarm alarm = mealService.userMealAllergie(data, meal);
		
		assertNull(alarm);
	}

}
