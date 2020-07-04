package sbnz.projekat.nutritionadviser.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Double bmr; //minimalan broj kalorija
	
	/*
	 Men: BMR = 88.362 + (13.397 x weight in kg) + (4.799 x height in cm) - (5.677 x age in years)
	 Women: BMR = 447.593 + (9.247 x weight in kg) + (3.098 x height in cm) - (4.330 x age in years)
	 * */
	
	@ManyToOne
	private Diet diet;
	private Boolean diabetes;
	private Boolean heartDisease;
	private Boolean highBloodPressure;
	
	@OneToMany(mappedBy = "userData", cascade = CascadeType.ALL)
	private Set<MealDetails> details = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
			name = "user_allergy",
			joinColumns = @JoinColumn(name = "user_data_id"),
			inverseJoinColumns = @JoinColumn(name = "allergen_id")
	)
	private Set<Allergen> allergies;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	public UserData() {
		this.details = new HashSet<MealDetails>();
	}

	public UserData(Long id, Double height, Double weight, Integer yearOfBirth, Gender gender, Double bmi, Double bmr, Diet diet,
			Boolean diabetes, Boolean heartDisease, Boolean highBloodPressure, User user) {
		this.id = id;
		this.height = height;
		this.weight = weight;
		this.yearOfBirth = yearOfBirth;
		this.gender = gender;
		this.bmi = bmi;
		this.bmr = bmr;
		this.diet = diet;
		this.diabetes = diabetes;
		this.heartDisease = heartDisease;
		this.highBloodPressure = highBloodPressure;
		this.user = user;
	}
	
	public Set<MealDetails> getDetails() {
		return details;
	}

	public void setDetails(Set<MealDetails> details) {
		this.details = details;
	}

	public UserData(Long id, Double height, Double weight, Integer yearOfBirth, Gender gender, Diet diet,
			Boolean diabetes, Boolean heartDisease, Boolean highBloodPressure, User user) {
		this.id = id;
		this.height = height;
		this.weight = weight;
		this.yearOfBirth = yearOfBirth;
		this.gender = gender;

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

	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Allergen> getAllergies() {
		return allergies;
	}

	public void setAllergies(Set<Allergen> allergies) {
		this.allergies = allergies;
	}

	public Double getBmr() {
		return bmr;
	}

	public void setBmr(Double bmr) {
		this.bmr = bmr;
	}

}
