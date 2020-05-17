package sbnz.projekat.nutritionadviser.model;

import java.util.List;

public class Meal {

	private Long id;
	private List<GrocerieQuantity> groceries;
	private Double calories;

	public Meal() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<GrocerieQuantity> getGroceries() {
		return groceries;
	}

	public void setGroceries(List<GrocerieQuantity> groceries) {
		this.groceries = groceries;
	}

	public Double getCalories() {
		return calories;
	}

	public void setCalories(Double calories) {
		this.calories = calories;
	}

}
