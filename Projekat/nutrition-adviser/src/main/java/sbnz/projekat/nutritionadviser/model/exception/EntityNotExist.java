package sbnz.projekat.nutritionadviser.model.exception;

public class EntityNotExist extends RuntimeException {

	private String message;

	public EntityNotExist(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
