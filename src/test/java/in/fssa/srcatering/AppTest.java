package in.fssa.srcatering;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Cart;

import in.fssa.srcatering.service.CartService;
import in.fssa.srcatering.service.OrderService;

public class AppTest {

	public static void main(String[] args) {
		
//		Order order = new Order();
//
		OrderService orderService = new OrderService();
//
//		order.setUserId(2);
//		order.setMenuId(1);
//		order.setCategoryId(1);
//		order.setNoOfGuest(10);
//		order.setDeliveryDate(LocalDate.parse("06-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//		order.setOrderStatus(OrderStatus.NOT_DELIVERED);
//
//		try {
//			orderService.createOrder(order);
//		} catch (ValidationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		Cart addtoCart = new Cart();
		
		CartService CartService = new CartService();
		
		addtoCart.setUserId(3);
		addtoCart.setMenuId(1);
		addtoCart.setCategoryId(1);
		addtoCart.setNoOfGuest(59);
		addtoCart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		addtoCart.setId(2);
		
		try {
			CartService.createAddtoCart(addtoCart);
		} catch (ValidationException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


//		try {
//			System.out.println(new OrderProductService().getOrderProductsByOrderId(1));
//			
//		} catch (ValidationException | ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
		
	}

}
