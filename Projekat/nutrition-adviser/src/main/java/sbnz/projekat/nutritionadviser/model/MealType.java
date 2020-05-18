package sbnz.projekat.nutritionadviser.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MealType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String mealType; // breakfast, lunch...
	
	@OneToMany(mappedBy = "mealType")
	private Set<Meal> meal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

}
