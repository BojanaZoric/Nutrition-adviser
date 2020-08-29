package sbnz.projekat.nutritionadviser.dto;

import java.util.ArrayList;
import java.util.List;

public class MealDTO {

	private Long id;
	private String name;

	private Double preparationTime;
	private double calories;

	private Double proteinAmount;
	private Double carbohydrateAmount;
	private String description;
	private String instructions;

	private List<GroceriesQuantityDTO> groceries = new ArrayList<GroceriesQuantityDTO>();
	private List<StepDTO> steps = new ArrayList<StepDTO>();

	public List<StepDTO> getSteps() {
		return steps;
	}

	public void setSteps(List<StepDTO> steps) {
		this.steps = steps;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GroceriesQuantityDTO> getGroceries() {
		return groceries;
	}

	public void setGroceries(List<GroceriesQuantityDTO> groceries) {
		this.groceries = groceries;
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

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

}
