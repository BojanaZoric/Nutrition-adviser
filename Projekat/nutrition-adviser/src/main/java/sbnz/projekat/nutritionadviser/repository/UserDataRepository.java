package sbnz.projekat.nutritionadviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.projekat.nutritionadviser.model.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

	UserData findByUserId(Long userId);
}
