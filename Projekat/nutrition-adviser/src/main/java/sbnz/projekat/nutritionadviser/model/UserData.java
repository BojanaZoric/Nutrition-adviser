package sbnz.projekat.nutritionadviser.model;

public class UserData {

	private Double height;
	private Double weight;
	private Integer yearOfBirth;
	private Gender gender;
	private Double bmi;
	private Diet diet;
	private Boolean diabetes;
	private Boolean heartDisease;
	private Boolean highBloodPresure;

	public UserData() {
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
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

	public Boolean getHighBloodPresure() {
		return highBloodPresure;
	}

	public void setHighBloodPresure(Boolean highBloodPresure) {
		this.highBloodPresure = highBloodPresure;
	}

	public Diet getDiet() {
		return diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
	}

}
