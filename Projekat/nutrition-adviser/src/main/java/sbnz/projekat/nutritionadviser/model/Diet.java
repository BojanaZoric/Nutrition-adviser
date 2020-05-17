package sbnz.projekat.nutritionadviser.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Diet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String type; // vegan, vegeterian
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
