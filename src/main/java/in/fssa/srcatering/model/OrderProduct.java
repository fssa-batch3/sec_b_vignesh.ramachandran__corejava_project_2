package in.fssa.srcatering.model;

public class OrderProduct extends OrderProductEntity {

	public OrderProduct() {
		
	}
	
	public OrderProduct(int id, int menu_id, int category_id, int dish_id, int price) {
		super.setId(id);
		super.setMenu_id(menu_id);
		super.setCategory_id(category_id);
		super.setDish_id(dish_id);
		super.setPrice(price);
	}
	
	

}
