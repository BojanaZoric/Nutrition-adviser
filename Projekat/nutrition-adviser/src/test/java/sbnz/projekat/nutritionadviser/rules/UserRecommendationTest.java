package sbnz.projekat.nutritionadviser.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

import sbnz.projekat.nutritionadviser.model.Allergen;
import sbnz.projekat.nutritionadviser.model.Diet;
import sbnz.projekat.nutritionadviser.model.Gender;
import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.GrocerieQuantity;
import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.model.UserData;
import sbnz.projekat.nutritionadviser.service.MealService;

public class UserRecommendationTest {

	private MealService mealService;

	@Before
	public void setUp() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("sbnz.projekat", "nutrition-adviser-drools", "0.0.1-SNAPSHOT"));
		
		mealService = new MealService(null, null, null, null, kContainer);
	}
	
	@Test
	public void checkMeal() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false, false, true, true, null, null);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false, true, true, true, null, null);
		
		Allergen a = new Allergen();
		grocerie1.setAllergens(new HashSet<>());
		grocerie2.setAllergens(new HashSet<>());
		
		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		
		
		UserData data = new UserData(new Long(1), 185.0, 150.0, 1970, Gender.MALE, null, null, null, true, false, false, null);
	
	
		List<Meal> allMeals = new ArrayList<Meal>();
		allMeals.add(meal);
		
		assertEquals(0, mealService.recommendForUser(allMeals, data).size());
	}
	
	@Test
	public void checkMeal2() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false, false, true, true, null, null);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false, true, true, true, null, null);
		
		Allergen a = new Allergen();
		grocerie1.setAllergens(new HashSet<>());
		grocerie2.setAllergens(new HashSet<>());
		
		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		
		
		UserData data = new UserData(new Long(1), 185.0, 150.0, 1970, Gender.MALE, null, null, null, false, false, false, null);
	
	
		List<Meal> allMeals = new ArrayList<Meal>();
		allMeals.add(meal);
		
		assertEquals(1, mealService.recommendForUser(allMeals, data).size());
	}
	
	@Test
	public void checkMeal3() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false, false, false, false, null, null);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false, true, true, true, null, null);
		
		Allergen a = new Allergen();
		grocerie1.setAllergens(new HashSet<>());
		grocerie2.setAllergens(new HashSet<>());
		
		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		
		Meal meal2 = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(3), grocerie2, 200.0, meal));
		
		
		UserData data = new UserData(new Long(1), 185.0, 150.0, 1970, Gender.MALE, null, null, null, false, true, false, null);
	
	
		List<Meal> allMeals = new ArrayList<Meal>();
		allMeals.add(meal);
		allMeals.add(meal2);
		
		assertEquals(1, mealService.recommendForUser(allMeals, data).size());
	}
	
	@Test
	public void checkMeal4() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false, false, false, false, null, null);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false, true, true, true, null, null);
		
		Allergen a = new Allergen();
		grocerie1.setAllergens(new HashSet<>());
		grocerie1.getAllergens().add(a);
		grocerie2.setAllergens(new HashSet<>());
		
		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		
		Meal meal2 = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(3), grocerie2, 200.0, meal));
		
		
		UserData data = new UserData(new Long(1), 185.0, 150.0, 1970, Gender.MALE, null, null, null, false, false, false, null);
	
		Diet diet = new Diet();
		diet.setId(new Long(1));
		diet.setDiet_type("vegan");
		
		data.setAllergies(new HashSet<>());
		data.getAllergies().add(a);
	
		List<Meal> allMeals = new ArrayList<Meal>();
		allMeals.add(meal);
		allMeals.add(meal2);
		
		assertEquals(0, mealService.recommendForUser(allMeals, data).size());
	}
	
}
