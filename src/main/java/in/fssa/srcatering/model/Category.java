package in.fssa.srcatering.model;

public class Category extends CategoryEntity {

	public Category() {

	}

	public Category(int id, String name) {
		super.setId(id);
		super.setCategory_name(name);
	}

	@Override
	public int compareTo(CategoryEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
