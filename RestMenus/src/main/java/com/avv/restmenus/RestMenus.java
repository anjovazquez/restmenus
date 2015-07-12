package com.avv.restmenus;

import java.util.ArrayList;
import java.util.Hashtable;
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

	@GET
	@Path("/product")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getProducts() {
		Properties params = new Properties();
		params.put("Hostname", "localhost");
		params.put("Port", "49015");
		params.put("RemoteLocatorName", "ServerName");
		params.put("User", "admin");
		params.put("Password", "angel");

		String output = "";
		EntityResult result = null;

		ConnectionBean connection = new ConnectionBean(params);
		try {
			result = connection.query(new Hashtable(), new Vector(), "EProduct");
			ArrayList<Product> products = new ArrayList<Product>();
			if (result.calculateRecordNumber() > 0) {
				for (int i = 0; i < result.calculateRecordNumber(); i++) {
					Hashtable res = result.getRecordValues(0);
					products.add(new Product((Number) res.get("idProduct"), (String) res.get("productName"),
							(String) res.get("productDescription")));
				}
			}
			return products;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.endSession(connection.getSessionId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// output = "Jersey say : " + msg;

		// return Response.status(200).entity(output).build();
		// return new Product(new Integer("1"), "Chocolate");
		return result;
	}

	@GET
	@Path("/product/{productId}")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getProduct(@PathParam("productId") String productId) {
		Properties params = new Properties();
		params.put("Hostname", "localhost");
		params.put("Port", "49015");
		params.put("RemoteLocatorName", "ServerName");
		params.put("User", "admin");
		params.put("Password", "angel");

		String output = "";
		EntityResult result = null;

		ConnectionBean connection = new ConnectionBean(params);
		try {
			Hashtable kv = new Hashtable();
			kv.put("idProduct", productId);
			result = connection.query(kv, new Vector(), "EProduct");
			if (result.calculateRecordNumber() == 1) {
				Hashtable res = result.getRecordValues(0);
				return new Product(Integer.valueOf(productId), (String) res.get("productName"),
						(String) res.get("productDescription"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.endSession(connection.getSessionId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
