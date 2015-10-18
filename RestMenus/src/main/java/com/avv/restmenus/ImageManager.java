package com.avv.restmenus;

import java.util.Properties;

public class ImageManager {

	public final static String BASE_URL = "http://52.26.71.31:8080/static/";
	public final static String IMAGEPATH = "ImagePath";

	private static Properties params;
	private String imagePath;

	private ImageManager() {
		try {
			if (params == null) {
				params = new Properties();
				params.load(RestMenus.class.getResourceAsStream("imagesConfiguration.properties"));
				imagePath = (String) params.get(IMAGEPATH)!=null?(String)params.get(IMAGEPATH):BASE_URL;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ImageManager imageManager = null;

	public static ImageManager getImageManager() {
		if (imageManager == null) {
			imageManager = new ImageManager();
		}
		return imageManager;
	}

	public String getImagesPathURL() {
		return imagePath;
	}

}
