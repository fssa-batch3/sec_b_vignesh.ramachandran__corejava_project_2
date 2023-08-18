package in.fssa.srcatering.model;

public class DishEntity implements Comparable<DishEntity> {

	private int id;
	private String dish_name;
	private int quantity;
	private QuantityUnit quantity_unit;
	private int menu_id;
	private int category_id;
	private int dish_price;

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
		this.dish_name = dish_name.trim();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public QuantityUnit getQuantity_unit() {
		return quantity_unit;
	}

	public void setQuantity_unit(QuantityUnit quantity_unit) {
		this.quantity_unit = quantity_unit;
	}
	

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getDish_price() {
		return dish_price;
	}

	public void setDish_price(int dish_price) {
		this.dish_price = dish_price;
	}

	@Override
	public int compareTo(DishEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "DishEntity [id=" + id + ", dish_name=" + dish_name + ", quantity=" + quantity + ", quantity_unit="
				+ quantity_unit + ", menu_id=" + menu_id + ", category_id=" + category_id + ", dish_price=" + dish_price
				+ "]";
	}

//	@Override
//	public String toString() {
//		return "DishEntity [id=" + id + ", dish_name=" + dish_name + ", quantity=" + quantity + ", quantity_unit="
//				+ quantity_unit + ", dish_price=" + dish_price + "]";
//	}
	


}
