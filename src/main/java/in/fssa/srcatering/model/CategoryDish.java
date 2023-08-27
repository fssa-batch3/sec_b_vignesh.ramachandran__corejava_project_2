package in.fssa.srcatering.model;

public class CategoryDish extends CategoryDishEntity {

	public CategoryDish() {

	}

	public CategoryDish(int id, int menuId, int categoryId, int dishId, boolean status) {
		super.setId(id);
		super.setMenuId(menuId);
		super.setCategoryId(categoryId);
		super.setDishId(dishId);
		super.setStatus(status);
	}

	@Override
	public int compareTo(CategoryDishEntity o) {

		return Integer.compare(this.getId(), o.getId());
	}

}
