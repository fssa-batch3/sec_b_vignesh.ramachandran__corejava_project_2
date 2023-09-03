package in.fssa.srcatering.model;

public class Dish extends DishEntity {
	
	public Dish() {
		
	}
	
	public Dish(int id, String dishName, int quantity, QuantityUnit quantityUnit, int menuId, int categoryId, int dishPrice,int status) {
		super.setId(id);
		super.setDishName(dishName);
		super.setQuantity(quantity);
		super.setQuantityUnit(quantityUnit);
		super.setMenuId(menuId);
		super.setCategoryId(categoryId);
		super.setDishPrice(dishPrice);
		super.setStatus(status);
	}

	@Override
	public int compareTo(DishEntity o) {
	
		return Integer.compare(this.getId(), o.getId());
	}

}
