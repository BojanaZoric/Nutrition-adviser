package sbnz.projekat.nutritionadviser.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.service.MealService;

@RestController
@RequestMapping(value = "/meal")
public class MealController {

	private final MealService mealService;
	
	public MealController(MealService mealService) {
		this.mealService = mealService;
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Meal save() {
		Meal m = new Meal();
		m = mealService.calculateCalories(m);
		return m;
	}
}
