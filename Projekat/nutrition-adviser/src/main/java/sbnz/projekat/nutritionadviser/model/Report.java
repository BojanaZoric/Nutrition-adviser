package sbnz.projekat.nutritionadviser.model;

public class Report {
	
	private Long userId;
	private Number kcal;
	private String message;

	

	public Report(Long userId, Number kcal, String message) {
		this.userId = userId;
		this.kcal = kcal;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Number getKcal() {
		return kcal;
	}

	public void setKcal(Number kcal) {
		this.kcal = kcal;
	}
}
