package sbnz.projekat.nutritionadviser.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grocerie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer calories;
	private Boolean glutenFree;
	// dodati tipove korisnika

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

}
