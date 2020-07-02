package sbnz.projekat.nutritionadviser.dto;

public class FilterDTO {

	private String name;
	private double minCalories;
	private double maxCalories;
	private double minPrepTime;
	private double maxPrepTime;
	private double minProteins;
	private double maxProteins;
	private double minCarbo;
	private double maxCarbo;

	public FilterDTO() {
		this.minCalories = -1;
		this.maxCalories = -1;
		this.minPrepTime = -1;
		this.maxPrepTime = -1;
		this.minProteins = -1;
		this.maxProteins = -1;
		this.minCarbo = -1;
		this.maxCarbo = -1;
	}

	public FilterDTO(String name, double minCalories, double maxCalories, double minPrepTime, double maxPrepTime,
			double minProteins, double maxProteins, double minCarbo, double maxCarbo) {
		this.name = name;
		this.minCalories = minCalories;
		this.maxCalories = maxCalories;
		this.minPrepTime = minPrepTime;
		this.maxPrepTime = maxPrepTime;
		this.minProteins = minProteins;
		this.maxProteins = maxProteins;
		this.minCarbo = minCarbo;
		this.maxCarbo = maxCarbo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMinCalories() {
		return minCalories;
	}

	public void setMinCalories(double minCalories) {
		this.minCalories = minCalories;
	}

	public double getMaxCalories() {
		return maxCalories;
	}

	public void setMaxCalories(double maxCalories) {
		this.maxCalories = maxCalories;
	}

	public double getMinPrepTime() {
		return minPrepTime;
	}

	public void setMinPrepTime(double minPrepTime) {
		this.minPrepTime = minPrepTime;
	}

	public double getMaxPrepTime() {
		return maxPrepTime;
	}

	public void setMaxPrepTime(double maxPrepTime) {
		this.maxPrepTime = maxPrepTime;
	}

	public double getMinProteins() {
		return minProteins;
	}

	public void setMinProteins(double minProteins) {
		this.minProteins = minProteins;
	}

	public double getMaxProteins() {
		return maxProteins;
	}

	public void setMaxProteins(double maxProteins) {
		this.maxProteins = maxProteins;
	}

	public double getMinCarbo() {
		return minCarbo;
	}

	public void setMinCarbo(double minCarbo) {
		this.minCarbo = minCarbo;
	}

	public double getMaxCarbo() {
		return maxCarbo;
	}

	public void setMaxCarbo(double maxCarbo) {
		this.maxCarbo = maxCarbo;
	}

}
