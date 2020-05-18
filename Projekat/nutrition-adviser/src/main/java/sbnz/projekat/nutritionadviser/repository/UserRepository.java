package sbnz.projekat.nutritionadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.projekat.nutritionadviser.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
