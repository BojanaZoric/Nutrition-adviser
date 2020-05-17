package sbnz.projekat.nutritionadviser.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Meal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
	private Set<GrocerieQuantity> groceries;
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

	public Set<GrocerieQuantity> getGroceries() {
		return groceries;
	}

	public void setGroceries(Set<GrocerieQuantity> groceries) {
		this.groceries = groceries;
	}

}
