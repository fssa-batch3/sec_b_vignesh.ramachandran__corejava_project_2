package in.fssa.srcatering.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public abstract class OrderProductEntity {

	private int id;
	private int orderId;
	private int dishId;
	private int priceId;

	private Map<Integer, Integer> dishIdPriceIdMap;

	private int noOfGuest;
	private LocalDate deliveryDate;
	private OrderStatus orderStatus;
	private int menuId;
	private int categoryId;
	
	private LocalDateTime cancelDate;
	private String cancelReason;

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

	public int getNoOfGuest() {
		return noOfGuest;
	}

	public void setNoOfGuest(int noOfGuest) {
		this.noOfGuest = noOfGuest;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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

	public LocalDateTime getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(LocalDateTime cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	@Override
	public String toString() {
		return "OrderProductEntity [id=" + id + ", orderId=" + orderId + ", dishId=" + dishId + ", priceId=" + priceId
				+ ", dishIdPriceIdMap=" + dishIdPriceIdMap + ", noOfGuest=" + noOfGuest + ", deliveryDate="
				+ deliveryDate + ", orderStatus=" + orderStatus + ", menuId=" + menuId + ", categoryId=" + categoryId
				+ ", cancelDate=" + cancelDate + ", cancelReason=" + cancelReason + "]";
	}

	

}
