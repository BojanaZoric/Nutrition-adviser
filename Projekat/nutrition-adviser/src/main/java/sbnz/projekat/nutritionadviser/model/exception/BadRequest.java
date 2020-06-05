package sbnz.projekat.nutritionadviser.model.exception;

public class BadRequest extends RuntimeException{

	private String message;

	public BadRequest() {
	}

	public BadRequest(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
