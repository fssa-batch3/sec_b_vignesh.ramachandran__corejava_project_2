package in.fssa.srcatering.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order extends OrderEntity {
	
	public Order() {
		
	}

	public Order(int id, int userId, int menuId, int categoryId, int noOfGuest, int totalCost, LocalDateTime orderDate,
			LocalDate deliveryDate, OrderStatus orderStatus){
		
		super.setId(id);
		super.setMenuId(menuId);
		super.setCategoryId(categoryId);
		super.setUserId(userId);
		super.setNoOfGuest(noOfGuest);
		super.setTotalCost(totalCost);
		super.setOrderDate(orderDate);
		super.setDeliveryDate(deliveryDate);
		super.setOrderStatus(orderStatus);
		
	}
}
