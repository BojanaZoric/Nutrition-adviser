package sbnz.projekat.nutritionadviser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.projekat.nutritionadviser.model.Allergen;
import sbnz.projekat.nutritionadviser.service.AllergenService;

@RestController
@RequestMapping(value = "/allergen")
public class AllergenController {
	
	@Autowired
	private AllergenService allergenService;

	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Allergen>> getAll(){
		
		return new ResponseEntity<List<Allergen>>(this.allergenService.getAll(), HttpStatus.OK);
	}
}
