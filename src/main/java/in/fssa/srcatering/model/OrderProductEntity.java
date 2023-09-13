package in.fssa.srcatering.model;

import java.util.Map;

public abstract class OrderProductEntity {

	private int id;
	private int orderId;
	private int dishId;
	private int priceId;
	
	private Map<Integer, Integer> dishIdPriceIdMap;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public Map<Integer, Integer> getDishIdPriceIdMap() {
		return dishIdPriceIdMap;
	}

	public void setDishIdPriceIdMap(Map<Integer, Integer> dishIdPriceIdMap) {
		this.dishIdPriceIdMap = dishIdPriceIdMap;
	}

	@Override
	public String toString() {
		return "OrderProductEntity [id=" + id + ", orderId=" + orderId + ", dishIdPriceIdMap=" + dishIdPriceIdMap + "]";
	}
	
	

}
