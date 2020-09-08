package sbnz.projekat.nutritionadviser.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import sbnz.projekat.nutritionadviser.dto.UserDetailsDTO;
import sbnz.projekat.nutritionadviser.model.Allergen;
import sbnz.projekat.nutritionadviser.model.Authority;
import sbnz.projekat.nutritionadviser.model.Diet;
import sbnz.projekat.nutritionadviser.model.Gender;
import sbnz.projekat.nutritionadviser.model.User;
import sbnz.projekat.nutritionadviser.model.UserData;
import sbnz.projekat.nutritionadviser.repository.AllergenRepository;
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
	private AllergenRepository allergenRepository;

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
	
	public String getAutheeeeee() {
		System.out.println("USOO");
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);
		User user = this.userRepository.findOneByUsername(username);
		System.out.println(user.getAuthor());
		return user.getAuthor();
			}

	public UserData save(UserDetailsDTO details) {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);

		User user = this.userRepository.findOneByUsername(username);

		Gender gender = null;

		if (details.getGender().equalsIgnoreCase("male")) {
			gender = Gender.MALE;
		} else {
			gender = Gender.FEMALE;
		}
		
		UserData data1 = this.userDataRepository.findByUserId(user.getId());

		UserData data = new UserData(data1.getId(), details.getHeight(), details.getWeight(), details.getYearOfBirth(), gender,
				null, null, null, details.isDiabetes(), details.isHeartDisease(), details.isHighBloodPressure(), user);
		data = this.calculateData(data);
		
		data.setAllergies(new HashSet<>());
		if(details.getAllergies() != null) {
			for (Long id : details.getAllergies()) {
				Optional<Allergen> a = this.allergenRepository.findById(id);
				System.out.println(a.get().getName());
				if(a.isPresent()) {
					data.getAllergies().add(a.get());
				}
			}
		}
		UserData saved = userDataRepository.save(data);
		System.out.println(user.getAuthor());
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
