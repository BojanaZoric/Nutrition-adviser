package sbnz.projekat.nutritionadviser.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class UserData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double height;
	private Double weight;
	private Integer yearOfBirth;
	private Gender gender;
	private Double bmi;
	
	@ManyToOne
	private Diet diet;
	private Boolean diabetes;
	private Boolean heartDisease;
	private Boolean highBloodPressure;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	public UserData() {
	}

	public UserData(Long id, Double height, Double weight, Integer yearOfBirth, Gender gender, Double bmi, Diet diet,
			Boolean diabetes, Boolean heartDisease, Boolean highBloodPressure, User user) {
		this.id = id;
		this.height = height;
		this.weight = weight;
		this.yearOfBirth = yearOfBirth;
		this.gender = gender;
		this.bmi = bmi;
		this.diet = diet;
		this.diabetes = diabetes;
		this.heartDisease = heartDisease;
		this.highBloodPressure = highBloodPressure;
		this.user = user;
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

	public Boolean getHighBloodPressure() {
		return highBloodPressure;
	}

	public void setHighBloodPressure(Boolean highBloodPressure) {
		this.highBloodPressure = highBloodPressure;
	}

	public Diet getDiet() {
		return diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
