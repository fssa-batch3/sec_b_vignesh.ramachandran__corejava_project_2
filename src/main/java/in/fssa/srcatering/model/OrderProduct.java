package in.fssa.srcatering.model;

public class OrderProduct extends OrderProductEntity {

	public OrderProduct() {
		
	}
	
	public OrderProduct(int id, int menu_id, int category_id, int dish_id, int price) {
		super.setId(id);
		super.setMenuId(menu_id);
		super.setCategoryId(category_id);
		super.setDishId(dish_id);
		super.setPrice(price);
	}
	
	

}
