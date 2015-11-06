package com.avv.restmenus;

import java.util.List;

public class Order {

	private String tableNo;
	private String name;
	private List<Product> productList;

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "Order [tableNo=" + tableNo + ", name=" + name
				+ ", productList=" + productList + "]";
	}

}
