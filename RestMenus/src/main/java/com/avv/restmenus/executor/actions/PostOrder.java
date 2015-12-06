package com.avv.restmenus.executor.actions;

import java.util.Date;
import java.util.Hashtable;

import com.avv.restmenus.Order;
import com.avv.restmenus.Product;
import com.ontimize.db.EntityResult;
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
		kv.put("orderName", order.getOrderName());
		kv.put("orderDate", new Date());
		kv.put("customerToken", order.getCustomerToken());
//		kv.put("idCompany", 1);

		EntityResult resOrderReg = connection.insert(kv, "EOrder");

		if (resOrderReg.getCode() == EntityResult.OPERATION_SUCCESSFUL) {
			Object idOrder = resOrderReg.get("idOrder");
			for (Product product : order.getProductList()) {
				kv.clear();
				kv.put("idProduct", product.getProductId());
				kv.put("idOrder", idOrder);
				connection.insert(kv, "EOrderLine");
			}
		}

//		EntityResult result = new EntityResult();
//		result.setCode(EntityResult.OPERATION_SUCCESSFUL);
		return resOrderReg;
	}

}
