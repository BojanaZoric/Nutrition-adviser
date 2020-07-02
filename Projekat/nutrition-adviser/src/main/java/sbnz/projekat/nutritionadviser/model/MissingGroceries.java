package sbnz.projekat.nutritionadviser.model;

import java.util.ArrayList;

public class MissingGroceries {
	private ArrayList<Grocerie> groceries;
	
	public MissingGroceries() {
		this.groceries = new ArrayList<Grocerie>();
	}

	public ArrayList<Grocerie> getGroceries() {
		return groceries;
	}

	public void setGroceries(ArrayList<Grocerie> groceries) {
		this.groceries = groceries;
	}
	
	

}
