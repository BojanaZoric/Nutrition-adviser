package sbnz.projekat.nutritionadviser.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.GrocerieList;
import sbnz.projekat.nutritionadviser.model.GrocerieQuantity;
import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.model.MissingGroceries;
import sbnz.projekat.nutritionadviser.model.PossibleMeals;
import sbnz.projekat.nutritionadviser.model.RecommendedMeal;
import sbnz.projekat.nutritionadviser.service.MealService;

public class RecommendationTest {

	private MealService mealService;

	@Before
	public void setUp() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("sbnz.projekat", "nutrition-adviser-drools", "0.0.1-SNAPSHOT"));
		
		mealService = new MealService(null, null, null, kContainer);
	}
	
	@Test
	public void checkIfMealHasAllGroceriesTrue() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false);
		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		

		
		GrocerieList gl = new GrocerieList();
		gl.getGrocerieList().add(grocerie1);
		gl.getGrocerieList().add(grocerie2);
		
		PossibleMeals pm = mealService.checkIfMealHasAllGroceries(gl, meal);
		assertEquals(1, pm.getMeals().size());

	}
	
	@Test
	public void checkIfMealHasAllGroceriesFalse() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false);
		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		

		
		GrocerieList gl = new GrocerieList();
		gl.getGrocerieList().add(grocerie1);
		gl.getGrocerieList().add(grocerie2);
		gl.getGrocerieList().add(new Grocerie(new Long(10), "banana", 50, 15.0, 10.0, false));
		
		PossibleMeals pm = mealService.checkIfMealHasAllGroceries(gl, meal);
		System.out.println("NJAA" + pm.getMeals().size());

		assertEquals(0, pm.getMeals().size());

	}
	
	@Test
	public void checkIfMealHasAllGroceries_notAll() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false);
		Grocerie grocerie3 = new Grocerie(new Long(2), "banana", 50, 15.0, 10.0, false);

		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie3, 300.0, meal));

		
		GrocerieList gl = new GrocerieList();
		gl.getGrocerieList().add(grocerie1);
		gl.getGrocerieList().add(grocerie2);
		
		PossibleMeals pm = mealService.checkIfMealHasAllGroceries(gl, meal);
		assertEquals(0, pm.getMeals().size());

	}
	
	@Test
	public void checkIfMealHasAllGroceriesAndMore_Equal() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false);
		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		

		
		GrocerieList gl = new GrocerieList();
		gl.getGrocerieList().add(grocerie1);
		gl.getGrocerieList().add(grocerie2);
		
		PossibleMeals pm = mealService.checkIfMealHasAllGroceriesAndMore(gl, meal);
		assertEquals(1, pm.getMeals().size());

	}
	
	@Test
	public void checkIfMealHasAllGroceriesAndMore_NotAll() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false);
		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		

		
		GrocerieList gl = new GrocerieList();
		gl.getGrocerieList().add(grocerie1);
		gl.getGrocerieList().add(grocerie2);
		gl.getGrocerieList().add(new Grocerie(new Long(10), "banana", 50, 15.0, 10.0, false));
		
		PossibleMeals pm = mealService.checkIfMealHasAllGroceriesAndMore(gl, meal);
		assertEquals(0, pm.getMeals().size());

	}
	
	@Test
	public void checkIfMealHasAllGroceriesandMore_More() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false);
		Grocerie grocerie3 = new Grocerie(new Long(3), "banana", 50, 15.0, 10.0, false);

		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie3, 300.0, meal));

		
		GrocerieList gl = new GrocerieList();
		gl.getGrocerieList().add(grocerie1);
		gl.getGrocerieList().add(grocerie2);
		
		PossibleMeals pm = mealService.checkIfMealHasAllGroceriesAndMore(gl, meal);
		assertEquals(1, pm.getMeals().size());

	}
	
	@Test
	public void findMissingGroceriesTest() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false);
		Grocerie grocerie3 = new Grocerie(new Long(3), "banana", 50, 15.0, 10.0, false);
		Grocerie grocerie4 = new Grocerie(new Long(4), "banana2", 50, 15.0, 10.0, false);

		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie3, 300.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(3), grocerie4, 300.0, meal));

		
		GrocerieList gl = new GrocerieList();
		gl.getGrocerieList().add(grocerie1);
		gl.getGrocerieList().add(grocerie2);
		
		MissingGroceries pm = mealService.findMissingGroceriesFromMeal(gl, meal);
		assertEquals(2, pm.getGroceries().size());

	}
	
	
	@Test
	public void recommendBestMeal() {
		
		ArrayList<Meal> meals = new ArrayList<Meal>();
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 500, 5.0, 40.0, false);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false);
		Grocerie grocerie3 = new Grocerie(new Long(3), "banana", 50, 15.0, 10.0, false);
		Grocerie grocerie4 = new Grocerie(new Long(4), "banana2", 50, 15.0, 10.0, false);

		Meal meal = new Meal();
		meal.setName("Meal1");
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(3), grocerie3, 300.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(4), grocerie4, 300.0, meal));

		meals.add(meal);
		
		Grocerie grocerie5 = new Grocerie(new Long(5), "sugar2", 500, 5.0, 40.0, false);
		Grocerie grocerie6 = new Grocerie(new Long(6), "milk2", 50, 15.0, 10.0, false);
		
		Meal meal2 = new Meal();
		meal2.setName("Meal2");
		meal2.setGroceries(new HashSet<>());
		meal2.getGroceries().add(new GrocerieQuantity(new Long(5), grocerie5, 100.0, meal));
		meal2.getGroceries().add(new GrocerieQuantity(new Long(6), grocerie6, 200.0, meal));
		meal2.getGroceries().add(new GrocerieQuantity(new Long(7), grocerie1, 300.0, meal));
		
		meals.add(meal2);
		GrocerieList gl = new GrocerieList();
		gl.getGrocerieList().add(grocerie1);
		gl.getGrocerieList().add(grocerie2);
		
		RecommendedMeal pm = mealService.recommendBestMeal(gl, meals);
		System.out.println(pm.getRecommendedMeal().getId());
		assertEquals("Meal1", pm.getRecommendedMeal().getName());

	}
}
