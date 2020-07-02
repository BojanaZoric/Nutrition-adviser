package sbnz.projekat.nutritionadviser.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.GrocerieList;
import sbnz.projekat.nutritionadviser.model.GrocerieQuantity;
import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.model.MealDetails;
import sbnz.projekat.nutritionadviser.model.MissingGroceries;
import sbnz.projekat.nutritionadviser.model.PossibleMeals;
import sbnz.projekat.nutritionadviser.model.UserData;
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
	public void testCalorieLimitExceed() {
		Grocerie grocerie1 = new Grocerie(new Long(1), "sugar", 1500, 5.0, 40.0, false);
		Grocerie grocerie2 = new Grocerie(new Long(2), "milk", 50, 15.0, 10.0, false);
		Grocerie grocerie3 = new Grocerie(new Long(3), "banana", 50, 15.0, 10.0, false);
		Grocerie grocerie4 = new Grocerie(new Long(4), "banana2", 50, 15.0, 10.0, false);

		Meal meal = new Meal();
		meal.setGroceries(new HashSet<>());
		meal.getGroceries().add(new GrocerieQuantity(new Long(1), grocerie1, 100.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie2, 200.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(2), grocerie3, 300.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(new Long(3), grocerie4, 300.0, meal));

		meal.setCalories(2000.0);
		
		MealDetails md1 = new MealDetails(meal, LocalDate.now());
		MealDetails md2 = new MealDetails(meal, LocalDate.now());

		UserData ud = new UserData();
		ud.getDetails().add(md1);
		ud.getDetails().add(md2);
		
		boolean exceed = mealService.calorieLimitExceed(ud);
		assertEquals(true, exceed);

	}
	
	@Test
	public void testCalorieLimitExceed2() {
		

		Meal meal = new Meal();

		meal.setCalories(100.0);
		
		MealDetails md1 = new MealDetails(meal, LocalDate.now());
		MealDetails md2 = new MealDetails(meal, LocalDate.now());

		UserData ud = new UserData();
		ud.getDetails().add(md1);
		ud.getDetails().add(md2);
		
		boolean exceed = mealService.calorieLimitExceed(ud);
		assertEquals(false, exceed);

	}
}
