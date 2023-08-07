package in.fssa.srcatering.model;

public class Dish extends DishEntity {
	
	public Dish() {
		
	}
	
	public Dish(int id, String dish_name, int quantity, String quantity_unit) {
		super.setId(id);
		super.setDish_name(dish_name);
		super.setQuantity(quantity);
		super.setQuantity_unit(quantity_unit);
	}

}
