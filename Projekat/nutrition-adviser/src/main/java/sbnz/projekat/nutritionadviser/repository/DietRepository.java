package sbnz.projekat.nutritionadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.projekat.nutritionadviser.model.Diet;

public interface DietRepository extends JpaRepository<Diet, Long> {

}
