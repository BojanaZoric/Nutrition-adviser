package sbnz.projekat.nutritionadviser.model;

import java.util.ArrayList;

import antlr.collections.List;

public class GrocerieList {
	private ArrayList<Grocerie> grocerieList;
	
	public GrocerieList() {
		this.grocerieList = new ArrayList<Grocerie>();
	}

	public ArrayList<Grocerie> getGrocerieList() {
		return grocerieList;
	}

	public void setGrocerieList(ArrayList<Grocerie> grocerieList) {
		this.grocerieList = grocerieList;
	}
	
	
}
