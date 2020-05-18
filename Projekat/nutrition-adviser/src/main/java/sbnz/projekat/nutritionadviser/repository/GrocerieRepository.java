package sbnz.projekat.nutritionadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.projekat.nutritionadviser.model.Grocerie;

public interface GrocerieRepository extends JpaRepository<Grocerie, Long> {

}
