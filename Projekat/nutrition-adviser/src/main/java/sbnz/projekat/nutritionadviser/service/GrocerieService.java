package sbnz.projekat.nutritionadviser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.converter.GrocerieDTOConverter;
import sbnz.projekat.nutritionadviser.dto.GrocerieDTO;
import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.exception.BadRequest;
import sbnz.projekat.nutritionadviser.model.exception.EntityNotExist;
import sbnz.projekat.nutritionadviser.repository.GrocerieRepository;

@Service
public class GrocerieService {

	private final GrocerieRepository grocerieRepository;

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

		Grocerie grocerie = GrocerieDTOConverter.DtoToGrocerie(dto);

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
