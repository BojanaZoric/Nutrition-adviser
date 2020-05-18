package sbnz.projekat.nutritionadviser.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Meal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
	private Set<GrocerieQuantity> groceries;
	
	private Double calories;
	
	@ManyToOne
	private MealType mealType;

	public Meal() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCalories() {
		return calories;
	}

	public void setCalories(Double calories) {
		this.calories = calories;
	}

	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

}
