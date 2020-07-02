package sbnz.projekat.nutritionadviser.rules;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

import sbnz.projekat.nutritionadviser.dto.FilterDTO;
import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.GrocerieQuantity;
import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.model.PossibleMeals;
import sbnz.projekat.nutritionadviser.service.MealService;

public class FilterRulesTest {

	private MealService mealService;

	@Before
	public void setUp() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz.projekat", "nutrition-adviser-drools", "0.0.1-SNAPSHOT"));

		mealService = new MealService(null, null, null, kContainer);
	}

	@Test
	public void filterMeals() {

		Meal meal = new Meal();

		meal.setName("pizza");
		meal.setCalories(500.0);

		FilterDTO dto = new FilterDTO();
		dto.setName("pizza");
		dto.setMinCalories(200);
		dto.setMaxCalories(1000);

		List<Meal> meals = new ArrayList<Meal>();
		meals.add(meal);

		PossibleMeals pm = this.mealService.filterMeals(meals, dto);

		assertEquals(1, pm.getMeals().size());
	}

	@Test
	public void filterMeals2() {

		Meal meal = new Meal();

		meal.setName("pizza");
		meal.setCalories(500.0);

		Meal meal2 = new Meal();

		meal2.setName("pasta");
		meal2.setCalories(300.0);

		FilterDTO dto = new FilterDTO();
		//dto.setName("pizza");
		dto.setMinCalories(200);
		dto.setMaxCalories(1000);

		List<Meal> meals = new ArrayList<Meal>();
		meals.add(meal);
		meals.add(meal2);

		PossibleMeals pm = this.mealService.filterMeals(meals, dto);

		assertEquals(2, pm.getMeals().size());
	}
	
	@Test
	public void filterMeals3() {
		Meal meal = new Meal();

		meal.setName("pizza");
		meal.setCalories(500.0);
		meal.setPreparationTime(60.0);
		meal.setProteinAmount(20.0);

		Meal meal2 = new Meal();

		meal2.setName("pasta");
		meal2.setCalories(300.0);
		meal2.setPreparationTime(90.0);
		meal2.setProteinAmount(10.0);
		
		Meal meal3 = new Meal();

		meal3.setName("omlette");
		meal3.setCalories(200.0);
		meal3.setPreparationTime(120.0);
		meal3.setProteinAmount(15.0);

		FilterDTO dto = new FilterDTO();
		//dto.setName("pizza");
		dto.setMinProteins(15.0);
		dto.setMaxPrepTime(100.0);;

		List<Meal> meals = new ArrayList<Meal>();
		meals.add(meal);
		meals.add(meal2);
		meals.add(meal3);

		PossibleMeals pm = this.mealService.filterMeals(meals, dto);

		assertEquals(1, pm.getMeals().size());
	
	}

}
