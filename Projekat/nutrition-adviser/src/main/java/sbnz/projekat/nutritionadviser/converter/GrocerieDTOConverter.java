package sbnz.projekat.nutritionadviser.converter;

import sbnz.projekat.nutritionadviser.dto.GrocerieDTO;
import sbnz.projekat.nutritionadviser.model.Grocerie;

public class GrocerieDTOConverter {
	
	public static Grocerie DtoToGrocerie(GrocerieDTO dto) {
		
		Grocerie grocerie = new Grocerie(dto.getId(), dto.getName(), dto.getCalories(), dto.isGlutenFree());
		
		return grocerie;
	}

}
