package sbnz.projekat.nutritionadviser.model;

import java.util.ArrayList;

public class GrocerieIdList {
	private ArrayList<Long> grocerieList;

	public GrocerieIdList() {
		this.grocerieList = new ArrayList<Long>();
	}

	public ArrayList<Long> getGrocerieList() {
		return grocerieList;
	}

	public void setGrocerieList(ArrayList<Long> grocerieList) {
		this.grocerieList = grocerieList;
	}

}
