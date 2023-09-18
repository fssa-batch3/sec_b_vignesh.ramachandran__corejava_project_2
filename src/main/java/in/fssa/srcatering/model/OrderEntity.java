package in.fssa.srcatering.model;

import java.time.LocalDateTime;

public abstract class OrderEntity {

	private int id;
	private int userId;
	private int addressId;
	private LocalDateTime orderDate;
	private String eventName;
	private int totalCost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", userId=" + userId + ", addressId=" + addressId + ", orderDate=" + orderDate
				+ ", eventName=" + eventName + ", totalCost=" + totalCost + "]";
	}
	
	

}
