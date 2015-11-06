package com.avv.restmenus.mapper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.avv.restmenus.Menu;
import com.ontimize.db.EntityResult;

public class MenuMapper {
	
	public List<Menu> transform(EntityResult result) {
		ArrayList<Menu> companies = new ArrayList<Menu>();

		for (int i = 0; i < result.calculateRecordNumber(); i++) {
			Hashtable values = result.getRecordValues(i);
			companies.add(transform(values));
		}

		return companies;
	}

	public Menu transform(Hashtable values) {
		Menu menu = new Menu();
		menu.setIdMenu((Number) values.get("idMenu"));
		menu.setMenuName((String) values.get("menuName"));
		return menu;
	}
}
