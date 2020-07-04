package sbnz.projekat.nutritionadviser.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.projekat.nutritionadviser.dto.UserDetailsDTO;
import sbnz.projekat.nutritionadviser.dto.UserTokenState;
import sbnz.projekat.nutritionadviser.model.User;
import sbnz.projekat.nutritionadviser.model.UserData;
import sbnz.projekat.nutritionadviser.security.TokenUtils;
import sbnz.projekat.nutritionadviser.security.auth.JwtAuthenticationRequest;
import sbnz.projekat.nutritionadviser.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(
			value = "/{username}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<User> getUserData(@PathVariable("username") String username) {
		
		User data = this.userService.findByUsername(username);
		
		return new ResponseEntity<User>(data, HttpStatus.OK);
	}
	
	@PostMapping(
			value = "/data/{userId}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<UserData> saveData(@PathVariable("userId") Long userId, @RequestBody UserDetailsDTO  dto) {
		
		UserData data = this.userService.save(dto, userId);
		
		return new ResponseEntity<UserData>(data, HttpStatus.OK);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws AuthenticationException, IOException {

		System.out.println("login");
		if (authenticationRequest.getUsername().trim().equals("") || authenticationRequest.getUsername() == null
				|| authenticationRequest.getPassword().trim().equals("")
				|| authenticationRequest.getPassword() == null) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		// Ubaci username + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user);

		// int expiresIn = tokenUtils.getExpiredIn();
		// Vrati token kao odgovor na uspesno autentifikaciju

		UserTokenState tokenState = new UserTokenState(jwt, 43000L, "Bearer", user.getId(), user.getUsername(),
				null, null);

		return ResponseEntity.ok(tokenState);
	}

}
