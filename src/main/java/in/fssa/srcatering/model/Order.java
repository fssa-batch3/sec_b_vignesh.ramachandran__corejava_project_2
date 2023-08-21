package in.fssa.srcatering.model;

import java.time.LocalDateTime;

public class Order extends OrderEntity {
	
	public Order() {
		
	}

	public Order(int id, int user_id, int menu_id, int category_id, int no_of_guest, int total_cost, LocalDateTime order_date,
			LocalDateTime delivery_date, OrderStatus order_status){
		
		super.setId(id);
		super.setMenu_id(menu_id);
		super.setCategory_id(category_id);
		super.setUser_id(user_id);
		super.setNo_of_guest(no_of_guest);
		super.setTotal_cost(total_cost);
		super.setOrder_date(order_date);
		super.setDelivery_date(delivery_date);
		super.setOrder_status(order_status);
		
	}
}
