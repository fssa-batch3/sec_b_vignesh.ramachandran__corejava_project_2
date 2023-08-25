package in.fssa.srcatering.model;

public abstract class CategoryEntity implements Comparable<CategoryEntity> {

	private int id;
	private String categoryName;

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

	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", category_name=" + categoryName + "]";
	}
}
