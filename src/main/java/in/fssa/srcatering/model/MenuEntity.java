package in.fssa.srcatering.model;

public abstract class MenuEntity implements Comparable<MenuEntity> {
	
	private int id;
	private String menu_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

}
