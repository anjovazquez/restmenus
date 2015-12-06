package com.avv.restmenus.executor.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import com.avv.restmenus.CustomerRequest;
import com.avv.restmenus.Order;
import com.avv.restmenus.Product;
import com.ontimize.db.EntityResult;
import com.ontimize.util.remote.BytesBlock;
import com.ontimize.util.rmi.ConnectionBean;

public class PostCustomerRequest extends AbstractOperationConnectorAction {

	private CustomerRequest request;
	
	public PostCustomerRequest(CustomerRequest request) {
		this.request = request;
	}

	@Override
	public EntityResult execute(ConnectionBean connection) throws Exception {
		
		Hashtable kv = new Hashtable();
		kv.put("tableNo", request.getTableNo());
		kv.put("type", request.getType());
		kv.put("customerToken", request.getCustomerToken());
		//kv.put("idCompany", request.getOrderName());
		
		EntityResult result = connection.insert(kv, "ECustomerRequest");
		return result;
	}

}
