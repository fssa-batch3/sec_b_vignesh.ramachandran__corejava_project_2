package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Order;
import in.fssa.srcatering.service.OrderService;


public class TestOrder {
	
	@Test
	void testCreateOrderWithUserIdZero() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(0);
			order.setAddressId(1);
			order.setEventName("Marriage");
			order.setTotalCost(400);
			
			orderService.createOrder(order);
			
		});
		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateOrderWithNegativeUserId() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(-2);
			order.setAddressId(1);
			order.setEventName("Marriage");
			order.setTotalCost(400);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateOrderWithInvalidUserId() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(100);
			order.setAddressId(1);
			order.setEventName("Marriage");
			order.setTotalCost(400);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	
	@Test
	void testFindAllOrdersByValidUserId() {
		OrderService orderService = new OrderService();
		
		assertDoesNotThrow(() -> {
			orderService.getAllOrdersByUserId(2);
		});
	}
	
	
	@Test
	void testFindAllOrdersByUserIdZero() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.getAllOrdersByUserId(0);
			
		});
		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testFindAllOrdersByNegativeUserId() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.getAllOrdersByUserId(-2);
			
		});
		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testFindAllOrdersByInvalidUserId() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.getAllOrdersByUserId(100);
			
		});
		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testFindOrderByValidOrderId() {
		OrderService orderService = new OrderService();
		
		assertDoesNotThrow(() -> {
			orderService.getOrderByOrderId(1);
		});
	}
	
	
	@Test
	void testFindOrderByOrderIdZero() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.getOrderByOrderId(0);
			
		});
		String expectedMessage = "OrderId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testFindOrderByNegativeOrderId() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.getOrderByOrderId(-3);
			
		});
		String expectedMessage = "OrderId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testFindOrderByInvalidOrderId() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.getOrderByOrderId(1000);
			
		});
		String expectedMessage = "Invalid OrderId";
		String actualMessage = exception.getMessage();

		
		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testGetAllOrders() {
		OrderService orderService = new OrderService();

		assertDoesNotThrow(() -> {
			orderService.getAllOrders();
		});
	}

}
