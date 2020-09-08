package sbnz.projekat.nutritionadviser.dto;

import java.util.List;

public class GrocerieDTO {

	private long id;
	private String name;
	private int calories;
	private double proteinAmount;
	private double carbohydrateAmount;
	private boolean glutenFree;
	private String measure;
	
	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public void setDiabetes(boolean diabetes) {
		this.diabetes = diabetes;
	}

	public void setHeartDisease(boolean heartDisease) {
		this.heartDisease = heartDisease;
	}

	public void setHighBloodPressure(boolean highBloodPressure) {
		this.highBloodPressure = highBloodPressure;
	}

	public void setDiet(long diet) {
		this.diet = diet;
	}

	private boolean diabetes; // pogodno za dijabeticare
	private boolean heartDisease; // pogodno za srcane bolesnike
	private boolean highBloodPressure; // pogodno za ljude sa visokim pritiskom
	
	private long diet;
	
	private List<Long> allergens;

	public GrocerieDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public boolean isGlutenFree() {
		return glutenFree;
	}

	public void setGlutenFree(boolean glutenFree) {
		this.glutenFree = glutenFree;
	}

	public double getProteinAmount() {
		return proteinAmount;
	}

	public void setProteinAmount(double proteinAmount) {
		this.proteinAmount = proteinAmount;
	}

	public double getCarbohydrateAmount() {
		return carbohydrateAmount;
	}

	public void setCarbohydrateAmount(double carbohydrateAmount) {
		this.carbohydrateAmount = carbohydrateAmount;
	}

	public Boolean getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(Boolean diabetes) {
		this.diabetes = diabetes;
	}

	public Boolean getHeartDisease() {
		return heartDisease;
	}

	public void setHeartDisease(Boolean heartDisease) {
		this.heartDisease = heartDisease;
	}

	public Boolean getHighBloodPressure() {
		return highBloodPressure;
	}

	public void setHighBloodPressure(Boolean highBloodPressure) {
		this.highBloodPressure = highBloodPressure;
	}

	public Long getDiet() {
		return diet;
	}

	public void setDiet(Long diet) {
		this.diet = diet;
	}

	public List<Long> getAllergens() {
		return allergens;
	}

	public void setAllergens(List<Long> allergens) {
		this.allergens = allergens;
	}
}
