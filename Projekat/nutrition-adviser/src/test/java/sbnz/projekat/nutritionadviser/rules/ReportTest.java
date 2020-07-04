package sbnz.projekat.nutritionadviser.rules;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.model.MealDetails;
import sbnz.projekat.nutritionadviser.model.MissingGroceries;
import sbnz.projekat.nutritionadviser.model.User;
import sbnz.projekat.nutritionadviser.model.UserData;

public class ReportTest {
	
	@Test
	public void test() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kieContainer = ks.newKieContainer(ks.newReleaseId("sbnz.projekat", "nutrition-adviser-drools", "0.0.1-SNAPSHOT"));
		KieSession kieSession = kieContainer.newKieSession("session");

		kieSession.getAgenda().getAgendaGroup("report").setFocus();
		
		LocalDate ld = LocalDate.now().minusDays(7);
		System.out.println(ld);
		System.out.println(ld.isAfter(LocalDate.now()));
		System.out.println(ld.isBefore(LocalDate.now()));
		User user = new User();
		user.setId(1l);
		UserData data = new UserData();
		data.setUser(user);
		
		User nutri = new User();
		nutri.setId(2l);
		
		
		
		Meal meal = new Meal();
		meal.setCalories(200.0);
		
		MealDetails details = new MealDetails();
		details.setMeal(meal);
		details.setDate(LocalDate.now().minusDays(6));
		
		MealDetails details2 = new MealDetails();
		details2.setMeal(meal);
		details2.setDate(LocalDate.now().minusDays(10));
		data.setDetails(new HashSet<>());
		data.getDetails().add(details);
		data.getDetails().add(details2);
		kieSession.insert(data);
		kieSession.insert(nutri);

		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila (report): " + numOfRules);
	
		kieSession.dispose();
	}
}
