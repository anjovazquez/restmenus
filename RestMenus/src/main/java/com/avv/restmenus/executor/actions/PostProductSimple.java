package com.avv.restmenus.executor.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import com.ontimize.db.EntityResult;
import com.ontimize.util.remote.BytesBlock;
import com.ontimize.util.rmi.ConnectionBean;

public class PostProductSimple extends AbstractOperationConnectorAction {

	private Number idProduct;
	private File file;
	private String name;

	public PostProductSimple(Number idProduct, File file, String name) {
		this.idProduct = idProduct;
		this.name = name;
		this.file = file;
	}

	@Override
	public EntityResult execute(ConnectionBean connection) throws Exception {
		Hashtable kv = new Hashtable();
		kv.put("idProduct", idProduct);
		Hashtable av = new Hashtable();
		av.put("productImage", idProduct);
		av.put("productImageName", name);
		av.put("productImage", fileToBytesBlock(file));

		return connection.update(av, kv, "EProduct");
	}

	private BytesBlock fileToBytesBlock(File selectedFile) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(selectedFile);
		byte[] bytesImage = new byte[(int) selectedFile.length()];
		int read = 0;
		while (read < bytesImage.length) {
			read += fileInputStream.read(bytesImage, read, bytesImage.length - read);
			if (read == -1) {
				break;
			}
		}
		return new BytesBlock(bytesImage);
	}

}
