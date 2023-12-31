package in.fssa.srcatering.model;

public abstract class DishEntity implements Comparable<DishEntity> {

	private int id;
	private String dishName;
	private int quantity;
	private QuantityUnit quantityUnit;
	private int menuId;
	private int categoryId;
	private int dishPrice;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public QuantityUnit getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(QuantityUnit quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(int dishPrice) {
		this.dishPrice = dishPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DishEntity [id=" + id + ", dishName=" + dishName + ", quantity=" + quantity + ", quantityUnit="
				+ quantityUnit + ", menuId=" + menuId + ", categoryId=" + categoryId + ", dishPrice=" + dishPrice
				+ ", status=" + status + "]";
	}

}
