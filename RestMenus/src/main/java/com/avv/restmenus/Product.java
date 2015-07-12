package com.avv.restmenus;

import java.io.Serializable;

public class Product implements Serializable {

	private Number productId;
	private String productName;
	private String description;

	public Product() {
	}

	public Product(Number productId, String productName, String description) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
	}

	public Number getProductId() {
		return productId;
	}

	public void setProductId(Number productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ "]";
	}

}
