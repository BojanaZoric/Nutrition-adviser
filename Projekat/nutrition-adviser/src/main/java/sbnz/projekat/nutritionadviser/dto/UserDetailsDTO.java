package sbnz.projekat.nutritionadviser.dto;

public class UserDetailsDTO {

	private double height;
	private double weight;
	private int yearOfBirth;
	private String gender;

	private String diet;
	private boolean diabetes;
	private boolean heartDisease;
	private boolean highBloodPressure;
	
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	public boolean isDiabetes() {
		return diabetes;
	}
	public void setDiabetes(boolean diabetes) {
		this.diabetes = diabetes;
	}
	public boolean isHeartDisease() {
		return heartDisease;
	}
	public void setHeartDisease(boolean heartDisease) {
		this.heartDisease = heartDisease;
	}
	public boolean isHighBloodPressure() {
		return highBloodPressure;
	}
	public void setHighBloodPressure(boolean highBloodPressure) {
		this.highBloodPressure = highBloodPressure;
	}

}
