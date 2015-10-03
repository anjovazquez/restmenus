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

import com.ontimize.db.EntityResult;
import com.ontimize.util.rmi.ConnectionBean;

@Path("")
public class RestMenus {

	public final static String BASE_URL = "http://52.10.245.28:8080/static/";

	public final static String HOSTNAME = "Hostname";
	public final static String PORT = "Port";
	public final static String REMOTE_LOCATOR = "RemoteLocatorName";
	public final static String USER = "User";
	public final static String PASSWORD = "Password";

	public final static String ONTIMIZE_CONNECTION_HOST = "localhost";
	public final static String ONTIMIZE_CONNECTION_PORT = "49015";
	public final static String ONTIMIZE_CONNECTION_LOCATOR = "ServerName";
	public final static String ONTIMIZE_CONNECTION_USER = "admin";
	public final static String ONTIMIZE_CONNECTION_PASSWORD = "angel";

	private static Properties params;

	static {
		try {
			if (params == null) {
				params = new Properties();
				params.load(RestMenus.class.getResourceAsStream("connection.properties"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ConnectionBean openConnection() {
		params.put(HOSTNAME, ONTIMIZE_CONNECTION_HOST);
		params.put(PORT, ONTIMIZE_CONNECTION_PORT);
		params.put(REMOTE_LOCATOR, ONTIMIZE_CONNECTION_LOCATOR);
		params.put(USER, ONTIMIZE_CONNECTION_USER);
		params.put(PASSWORD, ONTIMIZE_CONNECTION_PASSWORD);

		return new ConnectionBean(params);
	}

	private void closeConnection(ConnectionBean connection) {
		try {
			connection.endSession(connection.getSessionId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GET
	@Path("/product")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getProducts() {

		ArrayList<Product> products = new ArrayList<Product>();
		EntityResult result = null;

		ConnectionBean connection = openConnection();
		try {
			result = connection.query(new Hashtable(), new Vector(), "EProduct");
			if (result.calculateRecordNumber() > 0) {
				for (int i = 0; i < result.calculateRecordNumber(); i++) {
					Hashtable res = result.getRecordValues(0);

					String pImgName = (String) res.get("productImageName");
					String imageURL = BASE_URL + pImgName;
					products.add(new Product((Number) res.get("idProduct"), (String) res.get("productName"),
							(String) res.get("productDescription"), imageURL));
				}
			}
			return products;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return products;
	}

	@GET
	@Path("/product/{productId}/ingredient")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getIngredientByProduct(@PathParam("productId") String productId) {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();

		EntityResult result = null;
		ConnectionBean connection = openConnection();
		try {
			Hashtable kv = new Hashtable();
			kv.put("idProduct", productId);
			result = connection.query(kv, new Vector(), "EIngredient");
			if (result.calculateRecordNumber() > 0) {
				ingredients = new ArrayList<Ingredient>();
				for (int i = 0; i < result.calculateRecordNumber(); i++) {
					Hashtable values = result.getRecordValues(i);
					Ingredient ing = new Ingredient();
					ing.setIdIngredient((Number) values.get("idIngredient"));
					ing.setIngredientName((String) values.get("ingredientName"));

					ingredients.add(ing);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return ingredients;
	}

	@GET
	@Path("/product/{productId}/allergen")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getAllergenByProduct(@PathParam("productId") String productId) {
		List<Allergen> allergens = new ArrayList<Allergen>();

		EntityResult result = null;

		ConnectionBean connection = openConnection();
		try {
			Hashtable kv = new Hashtable();
			kv.put("idProduct", productId);
			result = connection.query(kv, new Vector(), "EAllergen");
			if (result.calculateRecordNumber() > 0) {
				allergens = new ArrayList<Allergen>();
				for (int i = 0; i < result.calculateRecordNumber(); i++) {
					Hashtable values = result.getRecordValues(i);
					Allergen allergen = new Allergen();
					allergen.setIdAllergen((Number) values.get("idAllergen"));
					allergen.setAllergenName((String) values.get("allergenName"));

					allergens.add(allergen);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return allergens;
	}

	@GET
	@Path("/product/{productId}")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getProduct(@PathParam("productId") String productId) {

		EntityResult result = null;

		ConnectionBean connection = openConnection();
		try {
			Hashtable kv = new Hashtable();
			kv.put("idProduct", productId);
			result = connection.query(kv, new Vector(), "EProduct");
			if (result.calculateRecordNumber() == 1) {
				Hashtable res = result.getRecordValues(0);
				String pImgName = (String) res.get("productImageName");
				String imageURL = BASE_URL + pImgName;

				Product product = new Product(Integer.valueOf(productId), (String) res.get("productName"),
						(String) res.get("productDescription"));
				product.setImageURL(imageURL);
				return product;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		System.out.println("adsasdas");
	}

}
