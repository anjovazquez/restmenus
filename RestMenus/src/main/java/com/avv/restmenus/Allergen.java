package com.avv.restmenus;

public class Allergen {

	private Number idAllergen;
	private String allergenName;

	public Allergen() {
	}

	public Number getIdAllergen() {
		return idAllergen;
	}

	public void setIdAllergen(Number idAllergen) {
		this.idAllergen = idAllergen;
	}

	public String getAllergenName() {
		return allergenName;
	}

	public void setAllergenName(String allergenName) {
		this.allergenName = allergenName;
	}

}
