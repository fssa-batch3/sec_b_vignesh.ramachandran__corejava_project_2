package in.fssa.srcatering.model;

public class DishEntity implements Comparable<DishEntity> {

	private int id;
	private String dish_name;
	private int quantity;
	private String quantity_unit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDish_name() {
		return dish_name;
	}

	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getQuantity_unit() {
		return quantity_unit;
	}

	public void setQuantity_unit(String quantity_unit) {
		this.quantity_unit = quantity_unit;
	}

	@Override
	public int compareTo(DishEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
