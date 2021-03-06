package com.avv.restmenus.executor.actions;

import java.util.Hashtable;
import java.util.Vector;

import com.ontimize.db.EntityResult;
import com.ontimize.util.rmi.ConnectionBean;

public class GetProducts extends AbstractOperationConnectorAction {

	public GetProducts() {
	}

	@Override
	public EntityResult execute(ConnectionBean connection) throws Exception {
		return connection.query(new Hashtable(), new Vector(), "EProduct");
	}

}
