package sbnz.projekat.nutritionadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.projekat.nutritionadviser.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {

}
