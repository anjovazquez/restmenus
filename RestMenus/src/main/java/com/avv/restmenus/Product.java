package com.avv.restmenus;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

	private Number productId;
	private String productName;
	private String description;
	private String imageURL;
	private String productSection;
	private Number productPrice;

	public Product() {
	}

	public Product(Number productId, String productName, String description) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
	}
	
	public Product(Number productId, String productName, String description, String imageURL) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.imageURL = imageURL;
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

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getProductSection() {
		return productSection;
	}

	public void setProductSection(String productSection) {
		this.productSection = productSection;
	}

	public Number getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Number productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + ", description=" + description + ", imageURL="
				+ imageURL + ", productSection=" + productSection
				+ ", productPrice=" + productPrice + "]";
	}
	
	

}
