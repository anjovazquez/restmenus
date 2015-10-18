package com.avv.restmenus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.avv.restmenus.executor.actions.GetAllergens;
import com.avv.restmenus.executor.actions.GetAllergensByProduct;
import com.avv.restmenus.executor.actions.GetIngredients;
import com.avv.restmenus.executor.actions.GetIngredientsByProduct;
import com.avv.restmenus.executor.actions.GetProduct;
import com.avv.restmenus.executor.actions.GetProducts;
import com.avv.restmenus.executor.actions.PostProductSimple;
import com.avv.restmenus.mapper.AllergenMapper;
import com.avv.restmenus.mapper.IngredientMapper;
import com.avv.restmenus.mapper.ProductMapper;
import com.ontimize.db.EntityResult;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("")
public class RestMenus {

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

	@GET
	@Path("/allergen")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getAllergens() {
		List<Allergen> allergens = new ArrayList<Allergen>();

		try {
			GetAllergens getAllergens = new GetAllergens();
			EntityResult result = getAllergens.execute();

			if (result.calculateRecordNumber() > 0) {
				allergens = new AllergenMapper().transform(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allergens;
	}

	@GET
	@Path("/ingredient")
	@Produces({ "application/json", MediaType.APPLICATION_JSON })
	public Object getIngredients() {
		List<Allergen> allergens = new ArrayList<Allergen>();

		try {
			GetIngredients getIngredients = new GetIngredients();
			EntityResult result = getIngredients.execute();

			if (result.calculateRecordNumber() > 0) {
				allergens = new AllergenMapper().transform(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allergens;
	}

	@POST
	@Path("/product/{productId}/image")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postProduct(@PathParam("productId") String productId,
			@FormDataParam("picture") InputStream uploadedInputStream,
			@FormDataParam("picture") FormDataContentDisposition fileDetail) {

		Product product = null;

		try {
			PostProductSimple postProduct = new PostProductSimple(Integer.valueOf(productId),
					saveToFile(uploadedInputStream), fileDetail.getFileName());
			EntityResult result = postProduct.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(200).entity("Uploaded").build();
	}

	private File saveToFile(InputStream uploadedStream) throws Exception {
		File file = File.createTempFile("fileTemp", ".img");

		OutputStream out = new FileOutputStream(file);
		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = uploadedStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();

		return file;
	}

}
