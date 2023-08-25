package in.fssa.srcatering.model;

public class Dish extends DishEntity {
	
	public Dish() {
		
	}
	
	public Dish(int id, String dish_name, int quantity, QuantityUnit quantity_unit, int menu_id, int category_id, int dish_price) {
		super.setId(id);
		super.setDishName(dish_name);
		super.setQuantity(quantity);
		super.setQuantityUnit(quantity_unit);
		super.setMenuId(menu_id);
		super.setCategoryId(category_id);
		super.setDishPrice(dish_price);
	}

}
