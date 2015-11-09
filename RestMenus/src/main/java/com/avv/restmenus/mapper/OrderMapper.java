package com.avv.restmenus.mapper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.avv.restmenus.Order;
import com.ontimize.db.EntityResult;

public class OrderMapper {

	public OrderMapper() {
	}

	public List<Order> transform(EntityResult result) {
		ArrayList<Order> orders = new ArrayList<Order>();

		for (int i = 0; i < result.calculateRecordNumber(); i++) {
			Hashtable values = result.getRecordValues(i);
			orders.add(transform(values));
		}

		return orders;
	}

	public Order transform(Hashtable orderValues) {
		Order order = new Order();
		order.setIdOrder((Number) orderValues.get("idOrder"));
		order.setOrderName((String) orderValues.get("orderName"));
		return order;
	}

}
