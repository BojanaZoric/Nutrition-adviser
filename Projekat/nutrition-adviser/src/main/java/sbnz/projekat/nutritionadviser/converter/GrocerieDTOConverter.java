package sbnz.projekat.nutritionadviser.converter;

import sbnz.projekat.nutritionadviser.dto.GrocerieDTO;
import sbnz.projekat.nutritionadviser.model.Grocerie;

public class GrocerieDTOConverter {
	
	public static Grocerie DtoToGrocerie(GrocerieDTO dto) {
		
		Grocerie grocerie = new Grocerie(dto.getId(), dto.getName(), dto.getCalories(), dto.getProteinAmount(), dto.getCarbohydrateAmount(), dto.isGlutenFree());
		
		grocerie.setDiabetes(dto.getDiabetes());
		grocerie.setHeartDisease(dto.getHeartDisease());
		grocerie.setHighBloodPressure(dto.getHighBloodPressure());
		return grocerie;
	}

}
