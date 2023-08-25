package in.fssa.srcatering.model;

public abstract class MenuEntity implements Comparable<MenuEntity> {

	private int id;
	private String menuName;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MenuEntity [id=" + id + ", menuName=" + menuName + ", description=" + description + "]";
	}
	
	

}
