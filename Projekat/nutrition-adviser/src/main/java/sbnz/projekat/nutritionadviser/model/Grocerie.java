package sbnz.projekat.nutritionadviser.model;

import java.util.HashSet;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Grocerie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer calories;
	private Double proteinAmount;
	private Double carbohydrateAmount;
	private Boolean glutenFree;
	// dodati tipove korisnika
	
	private Boolean diabetes; // pogodno za dijabeticare
	private Boolean heartDisease; // pogodno za srcane bolesnike
	private Boolean highBloodPressure; // pogodno za ljude sa visokim pritiskom
	
	private String measure;


	public Grocerie(Long id, String name, Integer calories, Double proteinAmount, Double carbohydrateAmount,
			Boolean glutenFree, Boolean diabetes, Boolean heartDisease, Boolean highBloodPressure, String measure,
			Diet diet, Set<Allergen> allergens, Set<GrocerieQuantity> grocerieQuantity) {
		super();
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.proteinAmount = proteinAmount;
		this.carbohydrateAmount = carbohydrateAmount;
		this.glutenFree = glutenFree;
		this.diabetes = diabetes;
		this.heartDisease = heartDisease;
		this.highBloodPressure = highBloodPressure;
		this.measure = measure;
		this.diet = diet;
		this.allergens = allergens;
		this.grocerieQuantity = grocerieQuantity;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	@ManyToOne
	private Diet diet; // pogodno za vegane, vegeterijance...
	
	@ManyToMany
	@JoinTable(
			name = "grocerie_allergen",
			joinColumns = @JoinColumn(name = "grocerie_id"),
			inverseJoinColumns = @JoinColumn(name = "allergen_id")
	)
	private Set<Allergen> allergens;

	@OneToMany(mappedBy = "grocerie", cascade = CascadeType.ALL)
	private Set<GrocerieQuantity> grocerieQuantity;

	public Grocerie() {
		this.grocerieQuantity = new HashSet<>();
	}

	public Grocerie(Long id, String name, Integer calories, Double proteinAmount, Double carbohydrateAmount,
			Boolean glutenFree) {
		this();
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.proteinAmount = proteinAmount;
		this.carbohydrateAmount = carbohydrateAmount;
		this.glutenFree = glutenFree;
	}
	
	public Grocerie(Long id, String name, Integer calories, Double proteinAmount, Double carbohydrateAmount,
			Boolean glutenFree, Boolean diabetes, Boolean heartDisease, Boolean highBloodPressure, Diet diet,
			Set<Allergen> allergens) {
		this();
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.proteinAmount = proteinAmount;
		this.carbohydrateAmount = carbohydrateAmount;
		this.glutenFree = glutenFree;
		this.diabetes = diabetes;
		this.heartDisease = heartDisease;
		this.highBloodPressure = highBloodPressure;
		this.diet = diet;
		this.allergens = allergens;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Boolean getGlutenFree() {
		return glutenFree;
	}

	public void setGlutenFree(Boolean glutenFree) {
		this.glutenFree = glutenFree;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Set<GrocerieQuantity> getGrocerieQuantity() {
		return grocerieQuantity;
	}

	@JsonIgnore
	public void setGrocerieQuantity(Set<GrocerieQuantity> grocerieQuantity) {
		this.grocerieQuantity = grocerieQuantity;
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

	public Set<Allergen> getAllergens() {
		return allergens;
	}

	public void setAllergens(Set<Allergen> allergens) {
		this.allergens = allergens;
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


}
