package com.avv.restmenus.executor.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import com.avv.restmenus.Order;
import com.avv.restmenus.Product;
import com.ontimize.db.EntityResult;
import com.ontimize.util.remote.BytesBlock;
import com.ontimize.util.rmi.ConnectionBean;

public class PostOrder extends AbstractOperationConnectorAction {

	private Order order;
	
	public PostOrder(Order order) {
		this.order = order;
	}

	@Override
	public EntityResult execute(ConnectionBean connection) throws Exception {
		
		Hashtable kv = new Hashtable();
		kv.put("tableNo", order.getTableNo());
		kv.put("name", order.getName());
		
		connection.insert(kv, "EOrder");
		
		for(Product product:order.getProductList()){
			kv.clear();
			kv.put("idProduct", product.getProductId());
			connection.insert(kv, "EOrderProduct");
		}
		
		EntityResult result = new EntityResult();
		result.setCode(EntityResult.OPERATION_SUCCESSFUL);
		return result;
	}

}
