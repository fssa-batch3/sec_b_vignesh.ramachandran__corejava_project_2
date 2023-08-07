package in.fssa.srcatering.model;

public class DishPrice extends DishPriceEntity {
	
	public DishPrice() {
		
	}
	
	public DishPrice(int id, int price, int dish_id) {
		super.setId(id);
		super.setPrice(price);
		super.setDihs_id(dish_id);
	}

}
