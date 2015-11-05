package com.avv.restmenus.executor.actions;

import java.util.Hashtable;
import java.util.Vector;

import com.ontimize.db.EntityResult;
import com.ontimize.demo.common.IBenMeSabeReferenceLocator;
import com.ontimize.locator.EntityReferenceLocator;
import com.ontimize.locator.ReferenceLocator;
import com.ontimize.util.rmi.ConnectionBean;

public class GetIngredientsByProduct extends AbstractOperationConnectorAction {

	private Number idProduct;

	public GetIngredientsByProduct(Number idProduct) {
		this.idProduct = idProduct;
	}

	@Override
	public EntityResult execute(ConnectionBean connection) throws Exception {
		Hashtable kv = new Hashtable();
		kv.put("idProduct", idProduct);
		return connection.query(kv, new Vector(), "EProductIngredient");
	}
}
