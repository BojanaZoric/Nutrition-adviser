package sbnz.projekat.nutritionadviser.dto;

public class GroceriesQuantityDTO {
	
	private Long grocerie_id;
	private double quantity;
	
	public Long getGrocerie_id() {
		return grocerie_id;
	}
	public void setGrocerie_id(Long grocerie_id) {
		this.grocerie_id = grocerie_id;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}
