package com.avv.restmenus;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.avv.restmenus.executor.actions.GetAllergensByProduct;
import com.avv.restmenus.executor.actions.GetIngredientsByProduct;
import com.avv.restmenus.executor.actions.GetProduct;
import com.avv.restmenus.executor.actions.GetProducts;
import com.avv.restmenus.mapper.AllergenMapper;
import com.avv.restmenus.mapper.IngredientMapper;
import com.avv.restmenus.mapper.ProductMapper;
import com.ontimize.db.EntityResult;
import com.ontimize.util.rmi.ConnectionBean;

@Path("")
public class RestMenus {

	public final static String BASE_URL = "http://52.10.245.28:8080/static/";

	@GET
	@Path("/product")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getProducts() {

		List<Product> products = new ArrayList<Product>();

		GetProducts getProducts = new GetProducts();
		EntityResult result;
		try {
			result = getProducts.execute();
			if (result.calculateRecordNumber() > 0) {
				products = new ProductMapper().transform(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return products;
	}

	@GET
	@Path("/product/{productId}/ingredient")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getIngredientByProduct(@PathParam("productId") String productId) {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();

		try {
			GetIngredientsByProduct getIngredientsByProduct = new GetIngredientsByProduct(Integer.valueOf(productId));
			EntityResult result = getIngredientsByProduct.execute();
			if (result.calculateRecordNumber() > 0) {
				ingredients = new IngredientMapper().transform(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ingredients;
	}

	@GET
	@Path("/product/{productId}/allergen")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getAllergenByProduct(@PathParam("productId") String productId) {
		List<Allergen> allergens = new ArrayList<Allergen>();

		try {
			GetAllergensByProduct getAllergensByProduct = new GetAllergensByProduct(Integer.valueOf(productId));
			EntityResult result = getAllergensByProduct.execute();

			if (result.calculateRecordNumber() > 0) {
				allergens = new AllergenMapper().transform(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allergens;
	}

	@GET
	@Path("/product/{productId}")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getProduct(@PathParam("productId") String productId) {

		EntityResult result = null;
		try {
			GetProduct getProduct = new GetProduct(Integer.valueOf(productId));
			result = getProduct.execute();
			if (result.calculateRecordNumber() == 1) {
				Product product = new ProductMapper().transform(result.getRecordValues(0));
				return product;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
