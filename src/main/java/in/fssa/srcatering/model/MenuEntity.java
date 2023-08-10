package in.fssa.srcatering.model;

public abstract class MenuEntity implements Comparable<MenuEntity> {
	

	private int id;
	private String menu_name;
	private String description;
	
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

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "MenuEntity [id=" + id + ", menu_name=" + menu_name + ", description=" + description + "]";
	}
}
