package sbnz.projekat.nutritionadviser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.projekat.nutritionadviser.dto.MealDTO;
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
	
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Meal> save(@RequestBody MealDTO dto) {
		Meal m = this.mealService.save(dto);
		return new ResponseEntity<Meal>(m, HttpStatus.OK);
	}
	
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Meal>> getAll(){
		
		return new ResponseEntity<List<Meal>>(mealService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(
			value = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Meal>> getOne(@PathVariable("id") Long id){
		
		return new ResponseEntity<List<Meal>>(mealService.getAll(), HttpStatus.OK);
	}
	
}
