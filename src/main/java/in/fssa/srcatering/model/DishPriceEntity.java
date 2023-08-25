package in.fssa.srcatering.model;

public class DishPriceEntity implements Comparable<DishEntity> {

	int id;
	int price;
	int dishId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	

	@Override
	public String toString() {
		return "DishPriceEntity [id=" + id + ", price=" + price + ", dishId=" + dishId + "]";
	}

	@Override
	public int compareTo(DishEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
