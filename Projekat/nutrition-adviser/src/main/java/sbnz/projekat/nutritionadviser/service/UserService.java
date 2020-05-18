package sbnz.projekat.nutritionadviser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.dto.UserDetailsDTO;
import sbnz.projekat.nutritionadviser.model.User;
import sbnz.projekat.nutritionadviser.model.UserData;
import sbnz.projekat.nutritionadviser.repository.UserDataRepository;
import sbnz.projekat.nutritionadviser.repository.UserRepository;

@Service
public class UserService {

	private final UserDataRepository userDataRepository;
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserDataRepository userDataRepository, UserRepository userRepository) {
		this.userDataRepository = userDataRepository;
		this.userRepository = userRepository;
	}
	
	public UserData save(UserDetailsDTO details, Long userId) {
		
		User user = this.userRepository.getOne(userId);
		
		UserData data = new UserData(null, details.getHeight(), details.getWeight(), details.getYearOfBirth(), null, null, null, details.isDiabetes(), details.isHeartDisease(), details.isHighBloodPressure(), user);
	
		return userDataRepository.save(data);
	}
	
	public List<UserData> getAllData(){
		return this.userDataRepository.findAll();
	}
}
