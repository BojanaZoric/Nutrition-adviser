package sbnz.projekat.nutritionadviser.rules;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

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
		
		mealService = new MealService(null, null, null, null, kContainer);
	}
	
	
	@Test
	public void calculateCalories() {
		// testiramo da li se dobro vrsi racunanje kalorija, broja proteina i ugljenih hidrata na osnovu sastojaka i njihove kolicine
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
		// testiramo da li se javlja upozorenje korisniku da je alergican na namirnicu
		Allergen a = new Allergen();
		a.setId(new Long(5));
		
		UserData data = new UserData();
		data.setAllergies(new HashSet<>());
		data.getAllergies().add(a);
		
		Grocerie grocerie = new Grocerie();
		grocerie.setAllergens(new HashSet<>());
		grocerie.getAllergens().add(a);
		
		List<Alarm> alarm = mealService.userGrocerieAllergie(data, grocerie);
		
		assertEquals(1, alarm.size());
	}
	
	@Test
	public void userGroceriNotHasAllergie() {
		// testiramo da li ce se javiti upozorenje korisniku koji NIJE alergican (ne treba da se javi)
		Allergen a = new Allergen();
		Allergen b = new Allergen();
		a.setId(new Long(5));
		
		UserData data = new UserData();
		data.setAllergies(new HashSet<>());
		data.getAllergies().add(a);
		
		Grocerie grocerie = new Grocerie();
		grocerie.setAllergens(new HashSet<>());
		grocerie.getAllergens().add(b);
		
		List<Alarm> alarm = mealService.userGrocerieAllergie(data, grocerie);
		assertEquals(0, alarm.size());
	}
	
	@Test
	public void userMealHasAllergie() {
		// testiramo da li se javlja upozorenje korisniku da je alergican na neki od sastojaka obroka
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
		
		List<Alarm> alarm = mealService.userMealAllergie(data, meal);
		
		assertEquals(1, alarm.size());
	}
	
	@Test
	public void userMealNotHasAllergie() {
		// korisnik NIJE alergican na obrok
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
		
		List<Alarm> alarm = mealService.userMealAllergie(data, meal);
		
		assertEquals(0, alarm.size());
	}
	
	
	@Test
	public void userMealHasAllergieDiabetesAndHeartDisease() {
		// testiramo da li se javljaju sva tri upozorenja
		Allergen a = new Allergen();
		a.setId(new Long(5));
		
		UserData data = new UserData();
		data.setAllergies(new HashSet<>());
		data.getAllergies().add(a);
		data.setDiabetes(true);
		data.setHeartDisease(true);
		data.setHighBloodPressure(false);
		
		Grocerie grocerie = new Grocerie();
		grocerie.setHeartDisease(false);
		grocerie.setHighBloodPressure(true);
		grocerie.setDiabetes(true);
		grocerie.setAllergens(new HashSet<>());
		grocerie.getAllergens().add(a);
		grocerie.setCalories(100);
		grocerie.setCarbohydrateAmount(50.0);
		grocerie.setProteinAmount(10.0);
		
		Grocerie grocerie2 = new Grocerie();
		grocerie2.setAllergens(new HashSet<>());
		grocerie2.setDiabetes(false);
		grocerie2.setHeartDisease(true);
		grocerie2.setHighBloodPressure(true);
		grocerie2.setCalories(100);
		grocerie2.setCarbohydrateAmount(50.0);
		grocerie2.setProteinAmount(10.0);
		
		Meal meal = new Meal();
		meal.setName("Pizza");
		
		meal.setGroceries(new HashSet<>());
		
		meal.getGroceries().add(new GrocerieQuantity(10l, grocerie, 200.0, meal));
		meal.getGroceries().add(new GrocerieQuantity(11l, grocerie2, 200.0, meal));
		List<Alarm> alarm = mealService.userMealAllergie(data, meal);
		
		assertEquals(3, alarm.size());
	}

}
