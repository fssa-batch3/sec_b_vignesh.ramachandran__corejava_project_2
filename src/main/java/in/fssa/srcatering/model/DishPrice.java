package in.fssa.srcatering.model;

public class DishPrice extends DishPriceEntity {
	
	public DishPrice() {
		
	}
	
	public DishPrice(int id, int price, int dishId) {
		super.setId(id);
		super.setPrice(price);
		super.setDishId(dishId);
	}

	@Override
	public int compareTo(DishEntity o) {
		
		return Integer.compare(this.getId(), o.getId());
	}

}
