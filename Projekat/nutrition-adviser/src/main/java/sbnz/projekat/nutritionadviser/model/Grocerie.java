package sbnz.projekat.nutritionadviser.model;

public class Grocerie {

	private Long id;
	private Integer calories;
	// dodati tipove korisnika

	public Grocerie() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

}
