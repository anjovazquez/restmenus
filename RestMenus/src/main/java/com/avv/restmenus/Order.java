package com.avv.restmenus;

import java.util.Date;
import java.util.List;

public class Order {

	private Number idOrder;
	private String tableNo;
	private String orderName;
	private String customerToken;
	private Date orderDate;
	private Number idCompany;
	private List<Product> productList;

	public Number getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Number idOrder) {
		this.idOrder = idOrder;
	}

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Number getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Number idCompany) {
		this.idCompany = idCompany;
	}

	public String getCustomerToken() {
		return customerToken;
	}

	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}

	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", tableNo=" + tableNo
				+ ", orderName=" + orderName + ", customerToken="
				+ customerToken + ", orderDate=" + orderDate + ", idCompany="
				+ idCompany + ", productList=" + productList + "]";
	}

}
