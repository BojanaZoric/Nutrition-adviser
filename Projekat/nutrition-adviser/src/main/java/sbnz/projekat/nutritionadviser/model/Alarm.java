package sbnz.projekat.nutritionadviser.model;

public class Alarm {

	private User user; // korisnik kojem se javlja alarm
	private Meal meal; // obrok koji nije pogodan za korisnika
	private String message;

	public Alarm() {
	}

	public Alarm(User user, Meal meal, String message) {
		this.user = user;
		this.meal = meal;
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
