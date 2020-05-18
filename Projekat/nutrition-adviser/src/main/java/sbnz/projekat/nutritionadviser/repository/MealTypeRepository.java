package sbnz.projekat.nutritionadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.projekat.nutritionadviser.model.MealType;

public interface MealTypeRepository extends JpaRepository<MealType, Long> {

	
}
