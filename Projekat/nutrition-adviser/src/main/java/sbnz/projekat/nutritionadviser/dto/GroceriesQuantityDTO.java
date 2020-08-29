package sbnz.projekat.nutritionadviser.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class GroceriesQuantityDTO {
    private Long id;

    public GroceriesQuantityDTO() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
