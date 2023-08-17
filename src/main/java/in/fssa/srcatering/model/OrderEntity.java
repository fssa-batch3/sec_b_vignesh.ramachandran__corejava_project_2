package in.fssa.srcatering.model;

import java.time.LocalDateTime;

public class OrderEntity {
	
	private int id;
	private int user_id;
	private int menu_id;
	private int category_id;
	private LocalDateTime order_date;
	private LocalDateTime delivery_date;
	private OrderStatus order_status;
	private int no_of_guest;
	private int total_cost;
	
	public enum OrderStatus {
		DELIVERED, NOT_DELIVERED, CANCELLED;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public LocalDateTime getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDateTime order_date) {
		this.order_date = order_date;
	}

	public LocalDateTime getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(LocalDateTime delivery_date) {
		this.delivery_date = delivery_date;
	}

	public OrderStatus getOrder_status() {
		return order_status;
	}

	public void setOrder_status(OrderStatus order_status) {
		this.order_status = order_status;
	}

	public int getNo_of_guest() {
		return no_of_guest;
	}

	public void setNo_of_guest(int no_of_guest) {
		this.no_of_guest = no_of_guest;
	}

	public int getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(int total_cost) {
		this.total_cost = total_cost;
	}


}


