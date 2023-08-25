package in.fssa.srcatering.model;

import java.time.LocalDateTime;

public class Order extends OrderEntity {
	
	public Order() {
		
	}

	public Order(int id, int user_id, int menu_id, int category_id, int no_of_guest, int total_cost, LocalDateTime order_date,
			LocalDateTime delivery_date, OrderStatus order_status){
		
		super.setId(id);
		super.setMenuId(menu_id);
		super.setCategoryId(category_id);
		super.setUserId(user_id);
		super.setNoOfGuest(no_of_guest);
		super.setTotalCost(total_cost);
		super.setOrderDate(order_date);
		super.setDeliveryDate(delivery_date);
		super.setOrderStatus(order_status);
		
	}
}
