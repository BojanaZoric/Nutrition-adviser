package sbnz.projekat.nutritionadviser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.service.MealService;

@RestController
@RequestMapping(value = "/meal")
public class MealController {

	private final MealService mealService;
	
	@Autowired
	public MealController(MealService mealService) {
		this.mealService = mealService;
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Meal save() {
		Meal m = new Meal();
		//m = mealService.calculateCalories(m);
		return m;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Meal>> getAll(){
		
		return new ResponseEntity<List<Meal>>(mealService.getAll(), HttpStatus.OK);
	}
}
