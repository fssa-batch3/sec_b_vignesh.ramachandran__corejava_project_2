package in.fssa.srcatering.model;

public class OrderProduct extends OrderProductEntity {

	public OrderProduct() {
		
	}
	
	public OrderProduct(int id, int menuId, int categoryId, int dishId, int price) {
		super.setId(id);
		super.setMenuId(menuId);
		super.setCategoryId(categoryId);
		super.setDishId(dishId);
		super.setPrice(price);
	}
	
	

}
