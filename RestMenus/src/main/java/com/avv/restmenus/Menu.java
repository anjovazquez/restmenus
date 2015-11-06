package com.avv.restmenus;

public class Menu {

	private Number idMenu;
	private String menuName;
	
	public Number getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(Number idMenu) {
		this.idMenu = idMenu;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Override
	public String toString() {
		return "Menu [idMenu=" + idMenu + ", menuName=" + menuName + "]";
	}	
	
}
