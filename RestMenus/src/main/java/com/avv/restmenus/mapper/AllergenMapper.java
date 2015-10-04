package com.avv.restmenus.mapper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.avv.restmenus.Allergen;
import com.ontimize.db.EntityResult;

public class AllergenMapper {

	public AllergenMapper() {
		// TODO Auto-generated constructor stub
	}

	public List<Allergen> transform(EntityResult result) {
		ArrayList<Allergen> allergens = new ArrayList<Allergen>();

		for (int i = 0; i < result.calculateRecordNumber(); i++) {
			Hashtable values = result.getRecordValues(i);
			allergens.add(transform(values));
		}

		return allergens;
	}

	public Allergen transform(Hashtable values) {
		Allergen allergen = new Allergen();
		allergen.setIdAllergen((Number) values.get("idAllergen"));
		allergen.setAllergenName((String) values.get("allergenName"));
		return allergen;
	}

}
