package sbnz.projekat.nutritionadviser.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.converter.GrocerieDTOConverter;
import sbnz.projekat.nutritionadviser.dto.GrocerieDTO;
import sbnz.projekat.nutritionadviser.model.Allergen;
import sbnz.projekat.nutritionadviser.model.Diet;
import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.exception.BadRequest;
import sbnz.projekat.nutritionadviser.model.exception.EntityNotExist;
import sbnz.projekat.nutritionadviser.repository.AllergenRepository;
import sbnz.projekat.nutritionadviser.repository.DietRepository;
import sbnz.projekat.nutritionadviser.repository.GrocerieRepository;

@Service
public class GrocerieService {

	private final GrocerieRepository grocerieRepository;
	
	@Autowired
	private AllergenRepository allergenRepository;
	
	@Autowired
	private DietRepository dietRepository;

	@Autowired
	public GrocerieService(GrocerieRepository grocerieRepository) {
		this.grocerieRepository = grocerieRepository;
	}

	public List<Grocerie> getAll() {
		return this.grocerieRepository.findAll();
	}

	public Grocerie getOne(Long id) {
		
		Optional<Grocerie> grocerie = this.grocerieRepository.findById(id);
		
		if(grocerie.isPresent()) {
			return grocerie.get();
		}
		return null;
	}

	public Grocerie save(GrocerieDTO dto) {

		Grocerie grocerie = this.grocerieRepository.findOneByNameAndMeasure(dto.getName(), dto.getMeasure());

		if(grocerie != null && grocerie.getMeasure().equals(dto.getMeasure()))
			return grocerie;
		
		grocerie = GrocerieDTOConverter.DtoToGrocerie(dto);
		grocerie.setMeasure(dto.getMeasure());
		grocerie.setAllergens(new HashSet<>());
		if(dto.getAllergens() != null) {
			for (Long id : dto.getAllergens()) {
				Optional<Allergen> a = this.allergenRepository.findById(id);
				
				if(a.isPresent()) {
					grocerie.getAllergens().add(a.get());
				}
			}
		}
		
		if(dto.getDiet() != null) {
	
				Optional<Diet> d = this.dietRepository.findById(dto.getDiet());
				
				if(d.isPresent()) {
					grocerie.setDiet(d.get());
				}
		
		}

		if (grocerie.getId() != null) {
			grocerie.setId(null);
		}

		return this.grocerieRepository.save(grocerie);
	}

	public Grocerie update(GrocerieDTO dto) {
		Grocerie grocerie = GrocerieDTOConverter.DtoToGrocerie(dto);

		if (grocerie.getId() == null) {
			throw new BadRequest("Grocerie id is not given");
		}

		return this.grocerieRepository.save(grocerie);
	}
	
	public void delete(Long id) {
		
		Grocerie grocerie = this.grocerieRepository.getOne(id);
		
		if (grocerie == null) {
			throw new EntityNotExist("Grocerie with id [" + id + "] not found");
		}
		
		this.grocerieRepository.deleteById(id);
	}

}
