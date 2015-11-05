package com.avv.restmenus.executor.actions;

import java.util.List;

import com.ontimize.db.EntityResult;
import com.ontimize.demo.common.IBenMeSabeReferenceLocator;
import com.ontimize.demo.common.dto.NutritionalInformationCategory;
import com.ontimize.util.rmi.ConnectionBean;

public class GetNutritionalInformationIngredient extends
		AbstractOperationConnectorAction {
	
	private String ingredientId;
	
	public GetNutritionalInformationIngredient(String ingredient){
		this.ingredientId = ingredientId;
	}

	@Override
	public EntityResult execute(ConnectionBean connection) throws Exception {
		IBenMeSabeReferenceLocator locator = (IBenMeSabeReferenceLocator)connection.getReferenceLocator();
		
		
		
		List<NutritionalInformationCategory> nutritionalInfo = locator.getNutritionalInformation(ingredientId);
		
		
		
		return null;
	}

}
