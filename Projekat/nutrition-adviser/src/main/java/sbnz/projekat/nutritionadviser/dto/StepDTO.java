package sbnz.projekat.nutritionadviser.dto;

public class StepDTO {
    private Long id;

	private String name;
	private String instruction;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public StepDTO() {
		super();
	}
}
