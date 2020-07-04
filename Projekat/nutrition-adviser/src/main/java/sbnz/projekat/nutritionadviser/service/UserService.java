package sbnz.projekat.nutritionadviser.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.dto.UserDetailsDTO;
import sbnz.projekat.nutritionadviser.model.Gender;
import sbnz.projekat.nutritionadviser.model.User;
import sbnz.projekat.nutritionadviser.model.UserData;
import sbnz.projekat.nutritionadviser.repository.UserDataRepository;
import sbnz.projekat.nutritionadviser.repository.UserRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

	private final UserDataRepository userDataRepository;
	private final UserRepository userRepository;
	private final KieContainer kieContainer;

	@Autowired
	public UserService(UserDataRepository userDataRepository, UserRepository userRepository,
			KieContainer kieContainer) {
		this.userDataRepository = userDataRepository;
		this.userRepository = userRepository;
		this.kieContainer = kieContainer;
	}
	
	public User findByUsername(String username) {
		User user = this.userRepository.findOneByUsername(username);
		
		return user;
	}

	public UserData save(UserDetailsDTO details, Long userId) {

		User user = this.userRepository.getOne(userId);

		Gender gender = null;

		if (details.getGender().equalsIgnoreCase("male")) {
			gender = Gender.MALE;
		} else {
			gender = Gender.FEMALE;
		}

		UserData data = new UserData(null, details.getHeight(), details.getWeight(), details.getYearOfBirth(), gender,
				null, null, null, details.isDiabetes(), details.isHeartDisease(), details.isHighBloodPressure(), user);
		data = this.calculateData(data);
		UserData saved = userDataRepository.save(data);
		user.setUserData(saved);
		this.userRepository.save(user);
		return saved;
	}

	public List<UserData> getAllData() {
		return this.userDataRepository.findAll();
	}

	private UserData calculateData(UserData data) {
		
		KieSession kieSession = kieContainer.newKieSession("session");
		kieSession.insert(data);
		kieSession.getAgenda().getAgendaGroup("user").setFocus();
		int numOfRules = kieSession.fireAllRules();
		System.out.println("Broj aktiviranih pravila: " + numOfRules);
		kieSession.dispose();

		return data;
	}
}
