package in.fssa.srcatering.model;

public class DishPriceEntity implements Comparable<DishEntity> {

	int id;
	int price;
	int dish_id;

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

	public int getDish_id() {
		return dish_id;
	}

	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}
	
	@Override
	public String toString() {
		return "DishPriceEntity [id=" + id + ", price=" + price + ", dish_id=" + dish_id + "]";
	}

	@Override
	public int compareTo(DishEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
