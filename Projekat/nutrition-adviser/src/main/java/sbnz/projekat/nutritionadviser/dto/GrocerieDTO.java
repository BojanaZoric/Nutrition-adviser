package sbnz.projekat.nutritionadviser.dto;

public class GrocerieDTO {

	private long id;
	private String name;
	private int calories;
	private double proteinAmount;
	private double carbohydrateAmount;
	private boolean glutenFree;

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
}
