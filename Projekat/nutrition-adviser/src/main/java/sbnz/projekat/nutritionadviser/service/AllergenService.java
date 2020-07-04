package sbnz.projekat.nutritionadviser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.model.Allergen;
import sbnz.projekat.nutritionadviser.repository.AllergenRepository;

@Service
public class AllergenService {
	
	@Autowired
	private AllergenRepository allergenRepository;
	
	public List<Allergen> getAll(){
		return this.allergenRepository.findAll();
	}

}
