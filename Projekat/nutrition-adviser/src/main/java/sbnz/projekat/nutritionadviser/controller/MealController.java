package sbnz.projekat.nutritionadviser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.projekat.nutritionadviser.dto.FilterDTO;
import sbnz.projekat.nutritionadviser.dto.MealDTO;
import sbnz.projekat.nutritionadviser.model.Alarm;
import sbnz.projekat.nutritionadviser.model.GrocerieIdList;
import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.model.MissingGroceries;
import sbnz.projekat.nutritionadviser.model.PossibleMeals;
import sbnz.projekat.nutritionadviser.service.MealService;

@RestController
@CrossOrigin
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
	
	//@PreAuthorize("hasRole('ADMIN')")
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
	public ResponseEntity<Meal> getOne(@PathVariable("id") Long id){
		
		return new ResponseEntity<Meal>(mealService.getOne(id), HttpStatus.OK);
	}
	
	@PostMapping(
			value = "/filter",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<PossibleMeals> filter(@RequestBody FilterDTO dto) {
		PossibleMeals m = this.mealService.getFilteredMeals(dto);
		return new ResponseEntity<PossibleMeals>(m, HttpStatus.OK);
	}
	
	@PostMapping(
			value = "/hasAllGroceries",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<PossibleMeals> mealsHasAllGroceries(@RequestBody GrocerieIdList list) {
		PossibleMeals m = this.mealService.getMealsHasAllGroceries(list);
		return new ResponseEntity<PossibleMeals>(m, HttpStatus.OK);
	}
	
	
	@PostMapping(
			value = "/hasAllGroceriesAndMore",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<PossibleMeals> mealsHasAllGroceriesAndMore(@RequestBody GrocerieIdList list) {
		PossibleMeals m = this.mealService.getMealsHasAllGroceriesAndMore(list);
		return new ResponseEntity<PossibleMeals>(m, HttpStatus.OK);
	}
	
	@PostMapping(
			value = "/missingGroceries/{mealId}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<MissingGroceries> missingGroceriew(@RequestBody GrocerieIdList list, @PathVariable("mealId") Long mealId) {
		MissingGroceries m = this.mealService.getMissingGroceries(list, mealId);
		return new ResponseEntity<MissingGroceries>(m, HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('USER')")
	@GetMapping(
			value = "/checkMeal/{mealId}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Alarm>> canIEatThisMeal(@PathVariable("mealId") Long mealId){
		
		return new ResponseEntity<List<Alarm>>(mealService.canIEatThisMeal(mealId), HttpStatus.OK);
	}
	
	@GetMapping(
			value = "/checkGrocerie/{grocerieId}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Alarm>> canIEatThisGrocerie(@PathVariable("grocerieId") Long grocerieId){
		
		return new ResponseEntity<List<Alarm>>(mealService.canIEatThisGrocerie(grocerieId), HttpStatus.OK);
	}
	
}
