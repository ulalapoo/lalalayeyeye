package mobi.mobileforce.garudamiles.model;

public class MenuModel {

	String menuTitle;
	int res;
	boolean typeItem;

//	public MenuModel(String title, int res, boolean type) {
//		// TODO Auto-generated constructor stub
//		this.menuTitle = title;
//		this.res = res;
//		this.typeItem = type;
//	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public boolean isTypeItem() {
		return typeItem;
	}

	public void setTypeItem(boolean typeItem) {
		this.typeItem = typeItem;
	}

}
