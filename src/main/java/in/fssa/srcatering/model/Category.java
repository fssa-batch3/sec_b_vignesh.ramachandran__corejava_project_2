package in.fssa.srcatering.model;

public class Category extends CategoryEntity {

	public Category() {

	}

	public Category(int id, String name) {
		super.setId(id);
		super.setCategoryName(name);
	}

	@Override
	public int compareTo(CategoryEntity o) {

		return Integer.compare(this.getId(), o.getId());
	}

}
