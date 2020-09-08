package sbnz.projekat.nutritionadviser.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.projekat.nutritionadviser.dto.GrocerieDTO;
import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.service.GrocerieService;

@RestController
@RequestMapping(value = "/groceries")
public class GrocerieController {
	
	private final GrocerieService grocerieService;
	
	public GrocerieController(GrocerieService grocerieService) {
		this.grocerieService = grocerieService;
	}
	
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Grocerie>> getAll(){
		
		return new ResponseEntity<List<Grocerie>>(this.grocerieService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping(
			value = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Grocerie> getOne(@PathVariable("id") Long id){
		
		return new ResponseEntity<Grocerie>(this.grocerieService.getOne(id), HttpStatus.OK);
	}

	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Grocerie> create(@RequestBody GrocerieDTO dto) {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);
		System.out.println(username);

		return new ResponseEntity<Grocerie>(this.grocerieService.save(dto), HttpStatus.CREATED);
	}
	
	@PutMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Grocerie> update(@RequestBody GrocerieDTO dto) {
		
		return new ResponseEntity<Grocerie>(this.grocerieService.update(dto), HttpStatus.CREATED);
	}
	
	@DeleteMapping(
			value = "/{id}"
	)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		this.grocerieService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
