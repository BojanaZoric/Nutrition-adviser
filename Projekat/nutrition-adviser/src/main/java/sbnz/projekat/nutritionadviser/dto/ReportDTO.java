package sbnz.projekat.nutritionadviser.dto;

public class ReportDTO {

	private Long userId;
	private Integer days;
	private String number;

	public ReportDTO(Long userId, Integer days, String number) {
		this.userId = userId;
		this.days = days;
		this.number = number;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
