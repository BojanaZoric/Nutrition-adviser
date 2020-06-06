package sbnz.projekat.nutritionadviser.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Allergen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToMany(mappedBy = "allergens")
	Set<Grocerie> groceries;
	
	@ManyToMany(mappedBy = "allergies")
	Set<UserData> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Set<Grocerie> getGroceries() {
		return groceries;
	}

	public void setGroceries(Set<Grocerie> groceries) {
		this.groceries = groceries;
	}

	@JsonIgnore
	public Set<UserData> getUsers() {
		return users;
	}

	public void setUsers(Set<UserData> users) {
		this.users = users;
	}
}
