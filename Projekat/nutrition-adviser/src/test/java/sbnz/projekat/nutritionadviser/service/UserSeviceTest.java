package sbnz.projekat.nutritionadviser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import sbnz.projekat.nutritionadviser.dto.UserDetailsDTO;
import sbnz.projekat.nutritionadviser.model.Gender;
import sbnz.projekat.nutritionadviser.model.User;
import sbnz.projekat.nutritionadviser.model.UserData;
import sbnz.projekat.nutritionadviser.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSeviceTest {
	
	@Autowired
    private UserService userService;
	
	@MockBean
    private UserRepository userRepositoryMock;
	
	@Test
	public void calculateBMIafterSave(){
		
		UserData data = new UserData(1L, 185.0, 77.0, 1997, Gender.FEMALE,null, false, false, false, null);
		User user = new User("user1", "test1", "Nikolina", "Petrovic", data);

		Mockito.when(userRepositoryMock.findById(1l))
        .thenReturn(Optional.of(user));
		UserDetailsDTO details = new UserDetailsDTO(185, 77, 1999, "MALE", null, false, false, false);
		UserData saved = userService.save(details);
		
		assertEquals(saved.getBmi(), data.getWeight()/Math.pow(data.getHeight()/100, 2));
	
	}

}
