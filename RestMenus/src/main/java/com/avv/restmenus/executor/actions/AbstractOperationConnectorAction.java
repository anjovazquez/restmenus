package com.avv.restmenus.executor.actions;

import com.avv.restmenus.ConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.util.rmi.ConnectionBean;

public abstract class AbstractOperationConnectorAction {

	public EntityResult execute() throws Exception {
		EntityResult result = null;
		ConnectionBean connection = ConnectionManager.getConnectionManager().openConnection();
		try {
			result = execute(connection);
		} catch (Exception excp) {
			excp.printStackTrace();
			throw excp;
		} finally {
			ConnectionManager.getConnectionManager().closeConnection(connection);
		}
		return result;
	}

	public abstract EntityResult execute(ConnectionBean connection) throws Exception;

}
