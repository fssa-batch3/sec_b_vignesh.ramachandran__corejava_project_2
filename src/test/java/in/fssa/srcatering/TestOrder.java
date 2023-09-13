package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Order;
import in.fssa.srcatering.model.OrderStatus;
import in.fssa.srcatering.service.OrderService;


public class TestOrder {
	
	@Test
	void testCreateOrderWithUserIdZero() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(0);
			order.setMenuId(1);
			order.setCategoryId(1);
			order.setNoOfGuest(50);
			order.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
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
			order.setMenuId(1);
			order.setCategoryId(1);
			order.setNoOfGuest(50);
			order.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
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
			order.setMenuId(1);
			order.setCategoryId(1);
			order.setNoOfGuest(50);
			order.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderWithMenuIdZero() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(1);
			order.setMenuId(0);
			order.setCategoryId(1);
			order.setNoOfGuest(50);
			order.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderWithNegativeMenuId() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(1);
			order.setMenuId(-2);
			order.setCategoryId(1);
			order.setNoOfGuest(50);
			order.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderWithInvalidMenuId() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(1);
			order.setMenuId(100);
			order.setCategoryId(1);
			order.setNoOfGuest(50);
			order.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderWithCategoryIdZero() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(1);
			order.setMenuId(1);
			order.setCategoryId(0);
			order.setNoOfGuest(50);
			order.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderWithNegativeCategoryId() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(1);
			order.setMenuId(1);
			order.setCategoryId(-2);
			order.setNoOfGuest(50);
			order.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderWithInvalidCategoryId() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(1);
			order.setMenuId(1);
			order.setCategoryId(20);
			order.setNoOfGuest(50);
			order.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "CategoryId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderWithZeroNoOfGuest() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(1);
			order.setMenuId(1);
			order.setCategoryId(20);
			order.setNoOfGuest(0);
			order.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "NoOfGuest cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderWithHighNoOfGuest() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(1);
			order.setMenuId(1);
			order.setCategoryId(1);
			order.setNoOfGuest(501);
			order.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "NoOfGuest should be above 49 and less than 501";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateOrderWithInvalidDeliveryDate() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Order order = new Order();
			order.setUserId(1);
			order.setMenuId(1);
			order.setCategoryId(1);
			order.setNoOfGuest(50);
			order.setDeliveryDate(LocalDate.parse("10-09-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			order.setOrderStatus(OrderStatus.NOT_DELIVERED);
			orderService.createOrder(order);
			
		});
		String expectedMessage = "The date is more than one week from today";
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
			orderService.getOrderByOrderId(0);
			
		});
		String expectedMessage = "OrderId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testFindOrderByInvalidOrderId() {
		OrderService orderService = new OrderService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			orderService.getOrderByOrderId(100);
			
		});
		String expectedMessage = "OrderId not found";
		String actualMessage = exception.getMessage();

		
		assertEquals(expectedMessage, actualMessage);
	}

}
