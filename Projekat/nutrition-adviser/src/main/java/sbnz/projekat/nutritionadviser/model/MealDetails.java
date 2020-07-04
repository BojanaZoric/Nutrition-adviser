package sbnz.projekat.nutritionadviser.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MealDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Meal meal;
	
	@ManyToOne
	private UserData userData;

	private LocalDate date;

	public MealDetails() {
		
	}

	public MealDetails(Meal m, LocalDate d) {
		this.meal = m;
		this.date = d;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public double getCalories() {
		return this.meal.getCalories();
	}

}