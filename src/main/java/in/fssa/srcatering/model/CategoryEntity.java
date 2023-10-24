package in.fssa.srcatering.model;

public abstract class CategoryEntity implements Comparable<CategoryEntity> {

	private int id;
	private String categoryName;
	private String image;
	private int menuId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", categoryName=" + categoryName + ", image=" + image + ", menu_id="
				+ menuId + "]";
	}

}
