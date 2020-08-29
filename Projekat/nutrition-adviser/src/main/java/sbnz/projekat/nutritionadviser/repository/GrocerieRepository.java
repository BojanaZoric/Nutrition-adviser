package sbnz.projekat.nutritionadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.projekat.nutritionadviser.model.Grocerie;
import sbnz.projekat.nutritionadviser.model.User;

public interface GrocerieRepository extends JpaRepository<Grocerie, Long> {
	Grocerie findOneByName(String name);
}
