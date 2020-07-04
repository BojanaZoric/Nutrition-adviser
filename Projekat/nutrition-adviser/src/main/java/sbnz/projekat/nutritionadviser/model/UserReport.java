package sbnz.projekat.nutritionadviser.model;

import java.util.List;

public class UserReport {

	private List<Meal> dayMeals;
	private double calorieLimit;
	
	public List<Meal> getDayMeals() {
		return dayMeals;
	}
	public void setDayMeals(List<Meal> dayMeals) {
		this.dayMeals = dayMeals;
	}
	public double getCalorieLimit() {
		return calorieLimit;
	}
	public void setCalorieLimit(double calorieLimit) {
		this.calorieLimit = calorieLimit;
	}
}
