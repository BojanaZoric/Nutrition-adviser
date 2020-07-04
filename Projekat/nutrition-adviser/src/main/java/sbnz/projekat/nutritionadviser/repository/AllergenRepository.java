package sbnz.projekat.nutritionadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.projekat.nutritionadviser.model.Allergen;

public interface AllergenRepository extends JpaRepository<Allergen, Long> {

}
