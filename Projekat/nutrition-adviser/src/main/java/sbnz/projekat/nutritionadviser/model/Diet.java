package sbnz.projekat.nutritionadviser.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Diet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String diet_type; // vegan, vegeterian
	
	@OneToMany(mappedBy = "diet", cascade = CascadeType.ALL)
	private Set<UserData> userData;
	
	@OneToMany(mappedBy = "diet", cascade = CascadeType.ALL)
	private Set<Grocerie> groceries;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiet_type() {
		return diet_type;
	}

	public void setDiet_type(String diet_type) {
		this.diet_type = diet_type;
	}

	public Set<UserData> getUserData() {
		return userData;
	}

	public void setUserData(Set<UserData> userData) {
		this.userData = userData;
	}
}
