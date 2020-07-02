package sbnz.projekat.nutritionadviser.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	private Set<GrocerieQuantity> groceries = new HashSet<>();
	
	@OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
	private Set<MealDetails> details = new HashSet<>();

	private Double calories;

	private Double preparationTime;

	private Double proteinAmount;
	private Double carbohydrateAmount;
	private String description;
	private String instructions;

	@ManyToOne
	private MealType mealType;

	public Meal() {
		// this.groceries = new HashSet<>();
	}

	public List<Grocerie> getComponents() {
		// vraca samo listu namirnica koje jelo sadrzi, bez obzira na kolicinu

		List<Grocerie> components = new ArrayList<Grocerie>();

		for (GrocerieQuantity grocerieQuantity : groceries) {
			components.add(grocerieQuantity.getGrocerie());
		}

		return components;
	}

	public List<Allergen> getAllergens() {
		List<Allergen> allergens = new ArrayList<>();

		for (Grocerie groc : this.getComponents()) {

			for (Allergen allergen : groc.getAllergens()) {
				allergens.add(allergen);
			}

		}

		return allergens;
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

	public Double getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(Double preparationTime) {
		this.preparationTime = preparationTime;
	}

	public Double getProteinAmount() {
		return proteinAmount;
	}

	public void setProteinAmount(Double proteinAmount) {
		this.proteinAmount = proteinAmount;
	}

	public Double getCarbohydrateAmount() {
		return carbohydrateAmount;
	}

	public void setCarbohydrateAmount(Double carbohydrateAmount) {
		this.carbohydrateAmount = carbohydrateAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

}
