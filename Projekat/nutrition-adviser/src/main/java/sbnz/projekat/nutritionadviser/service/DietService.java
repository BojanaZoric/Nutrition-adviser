package sbnz.projekat.nutritionadviser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.model.Diet;
import sbnz.projekat.nutritionadviser.repository.DietRepository;

@Service
public class DietService {

	@Autowired
	private DietRepository dietRepository;
	
	public List<Diet> getAll(){
		return this.dietRepository.findAll();
	}
}
