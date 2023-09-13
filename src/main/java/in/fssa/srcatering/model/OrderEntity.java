package in.fssa.srcatering.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class OrderEntity {

	private int id;
	private int userId;
	private int noOfGuest;
	private int totalCost; 
	private LocalDateTime orderDate;
	private LocalDate deliveryDate;
	private OrderStatus orderStatus;

	private int menuId;
	private int categoryId;

	public int getId() {
		return id; 
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getNoOfGuest() {
		return noOfGuest;
	}

	public void setNoOfGuest(int noOfGuest) {
		this.noOfGuest = noOfGuest;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
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

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", userId=" + userId + ", noOfGuest=" + noOfGuest + ", totalCost=" + totalCost
				+ ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + ", orderStatus=" + orderStatus
				+ ", menuId=" + menuId + ", categoryId=" + categoryId + "]";
	}
	
	

}
