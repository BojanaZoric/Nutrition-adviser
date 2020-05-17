package sbnz.projekat.nutritionadviser.model;

import java.util.List;

public class Meal {

	private Long id;
	private List<GrocerieQuantity> groceries;
	private Double calories;

	public Meal() {
	}

	public double getMealCalories() {
		Double cals = new Double(0);
		if (this.calories != null && this.calories.doubleValue() >= 0) {
			cals = this.calories;
		} else {
			for (GrocerieQuantity gq : groceries) {
				cals += gq.getQuantity() * gq.getGrocerie().getCalories();
			}
		}

		return cals;
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

}
