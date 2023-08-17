package in.fssa.srcatering.model;

import java.time.LocalDateTime;

import in.fssa.srcatering.model.OrderEntity.OrderStatus;

public class Order extends OrderEntity {
	
	public Order(int id, int user_id, int menu_id, int category_id, LocalDateTime order_date,
			LocalDateTime delivery_date, OrderStatus order_status, int no_of_guest, int total_cost) {
		
		super.setCategory_id(category_id);
		super.setDelivery_date(delivery_date);
		super.setId(id);
		super.setMenu_id(menu_id);
		super.setNo_of_guest(no_of_guest);
		super.setOrder_date(order_date);
		super.setOrder_status(order_status);
		super.setTotal_cost(total_cost);
		super.setUser_id(user_id);
	}
	
	public Order() {
		
	}

}
