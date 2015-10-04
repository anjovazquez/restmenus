package com.avv.restmenus.mapper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.avv.restmenus.Allergen;
import com.avv.restmenus.Ingredient;
import com.ontimize.db.EntityResult;

public class IngredientMapper {

	public IngredientMapper() {
	}

	public List<Ingredient> transform(EntityResult result) {
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

		for (int i = 0; i < result.calculateRecordNumber(); i++) {
			Hashtable values = result.getRecordValues(i);
			ingredients.add(transform(values));
		}

		return ingredients;
	}

	public Ingredient transform(Hashtable values) {
		Ingredient indredient = new Ingredient();
		indredient.setIdIngredient((Number) values.get("idIngredient"));
		indredient.setIngredientName((String) values.get("ingredientName"));
		return indredient;
	}

}
