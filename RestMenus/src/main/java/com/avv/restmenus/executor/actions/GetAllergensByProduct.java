package com.avv.restmenus.executor.actions;

import java.util.Hashtable;
import java.util.Vector;

import com.ontimize.db.EntityResult;
import com.ontimize.util.rmi.ConnectionBean;

public class GetAllergensByProduct extends AbstractOperationConnectorAction {

	private Number idProduct;

	public GetAllergensByProduct(Number idProduct) {
		this.idProduct = idProduct;
	}

	@Override
	public EntityResult execute(ConnectionBean connection) throws Exception {
		Hashtable kv = new Hashtable();
		kv.put("idProduct", idProduct);
		return connection.query(kv, new Vector(), "EProductAllergen");
	}

}
