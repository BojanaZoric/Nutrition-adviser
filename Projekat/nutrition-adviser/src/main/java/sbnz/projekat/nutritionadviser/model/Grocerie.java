package sbnz.projekat.nutritionadviser.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Grocerie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer calories;
	private Boolean glutenFree;
	// dodati tipove korisnika
	
	@OneToMany(mappedBy = "grocerie", cascade = CascadeType.ALL)
	private Set<GrocerieQuantity> grocerieQuantity;

	public Grocerie() {
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

	public Set<GrocerieQuantity> getGrocerieQuantity() {
		return grocerieQuantity;
	}

	public void setGrocerieQuantity(Set<GrocerieQuantity> grocerieQuantity) {
		this.grocerieQuantity = grocerieQuantity;
	}

}
