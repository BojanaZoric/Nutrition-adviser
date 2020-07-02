package sbnz.projekat.nutritionadviser.model;

import java.util.ArrayList;
import java.util.Set;

public class PossibleMeals {
	private ArrayList<Meal> meals;
	
	public PossibleMeals() {
		this.meals = new ArrayList<Meal>();
	}

	public ArrayList<Meal> getMeals() {
		return meals;
	}

	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}
	
	
}
