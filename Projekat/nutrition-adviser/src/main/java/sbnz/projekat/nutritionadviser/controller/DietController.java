package sbnz.projekat.nutritionadviser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.projekat.nutritionadviser.model.Diet;
import sbnz.projekat.nutritionadviser.service.DietService;

@RestController
@RequestMapping(value = "/diet")
public class DietController {

	@Autowired
	private DietService dietService;

	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Diet>> getAll(){
		
		return new ResponseEntity<List<Diet>>(this.dietService.getAll(), HttpStatus.OK);
	}
}
