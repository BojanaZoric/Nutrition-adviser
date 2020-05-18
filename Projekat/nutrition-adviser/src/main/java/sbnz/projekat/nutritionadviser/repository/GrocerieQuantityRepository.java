package sbnz.projekat.nutritionadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.projekat.nutritionadviser.model.GrocerieQuantity;

public interface GrocerieQuantityRepository extends JpaRepository<GrocerieQuantity, Long> {

}
