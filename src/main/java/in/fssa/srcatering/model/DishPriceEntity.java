package in.fssa.srcatering.model;

public class DishPriceEntity implements Comparable<DishEntity> {

	int id;
	int price;
	int dihs_id;

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

	public int getDihs_id() {
		return dihs_id;
	}

	public void setDihs_id(int dihs_id) {
		this.dihs_id = dihs_id;
	}

	@Override
	public int compareTo(DishEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
