package in.fssa.srcatering.model;

public class Dish extends DishEntity {
	
	public Dish() {
		
	}
	
	public Dish(int id, String dish_name, int quantity, QuantityUnit quantity_unit, int menu_id, int category_id, int dish_price) {
		super.setId(id);
		super.setDish_name(dish_name);
		super.setQuantity(quantity);
		super.setQuantity_unit(quantity_unit);
		super.setMenu_id(menu_id);
		super.setCategory_id(category_id);
		super.setDish_price(dish_price);
	}

}
