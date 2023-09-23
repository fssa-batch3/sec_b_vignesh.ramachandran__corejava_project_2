package in.fssa.srcatering.model;

import java.time.LocalDateTime;

public class Order extends OrderEntity {

	public Order() {

	}

	public Order(int id, int userId, int addressId, LocalDateTime orderDate, String eventName, int totalCost) {

		super.setId(id);
		super.setUserId(userId);
		super.setAddressId(addressId);
		super.setTotalCost(totalCost);
		super.setOrderDate(orderDate);
		super.setEventName(eventName);

	}
}
