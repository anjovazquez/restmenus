package com.avv.restmenus.mapper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.avv.restmenus.ImageManager;
import com.avv.restmenus.Product;
import com.avv.restmenus.RestMenus;
import com.ontimize.db.EntityResult;

public class ProductMapper {

	public ProductMapper() {
	}

	public List<Product> transform(EntityResult result) {
		ArrayList<Product> products = new ArrayList<Product>();

		for (int i = 0; i < result.calculateRecordNumber(); i++) {
			Hashtable values = result.getRecordValues(i);
			products.add(transform(values));
		}

		return products;
	}

	public Product transform(Hashtable productValues) {
		Product product = new Product();
		product.setProductId((Number) productValues.get("idProduct"));
		product.setProductName((String) productValues.get("productName"));
		product.setDescription((String) productValues.get("productDescription"));
		product.setImageURL(ImageManager.getImageManager().getImagesPathURL() + (String) productValues.get("productImageName"));
		return product;
	}

}
