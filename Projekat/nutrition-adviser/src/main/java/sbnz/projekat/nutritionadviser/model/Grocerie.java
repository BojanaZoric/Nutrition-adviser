package sbnz.projekat.nutritionadviser.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}
