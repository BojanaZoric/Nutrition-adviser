package sbnz.projekat.nutritionadviser.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GrocerieQuantity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Grocerie grocerie;
	private Double quantity;
	
	@ManyToOne
	private Meal meal;

	public GrocerieQuantity() {
	}

	public Grocerie getGrocerie() {
		return grocerie;
	}

	public void setGrocerie(Grocerie grocerie) {
		this.grocerie = grocerie;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

}
