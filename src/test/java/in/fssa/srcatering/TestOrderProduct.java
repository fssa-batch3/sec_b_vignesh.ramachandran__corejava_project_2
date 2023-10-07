package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.CaterApproval;
import in.fssa.srcatering.model.OrderProduct;
import in.fssa.srcatering.model.OrderStatus;
import in.fssa.srcatering.service.OrderProductService;


public class TestOrderProduct {
	
	@Test
	void testCreateOrderProductWithOrderIdZero() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setMenuId(1);
			orderProduct.setCategoryId(1);
			orderProduct.setNoOfGuest(50);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(0, orderProduct);
			
		});
		String expectedMessage = "OrderId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateOrderProductWithNegativeOrderId() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setMenuId(1);
			orderProduct.setCategoryId(1);
			orderProduct.setNoOfGuest(50);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(-3, orderProduct);
			
		});
		String expectedMessage = "OrderId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderProductWithInvalidOrderId() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setMenuId(1);
			orderProduct.setCategoryId(1);
			orderProduct.setNoOfGuest(50);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1000, orderProduct);
			
		});
		String expectedMessage = "Invalid OrderId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderProductWithMenuIdZero() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setMenuId(0);
			orderProduct.setCategoryId(1);
			orderProduct.setNoOfGuest(50);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1, orderProduct);
			
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderProductWithNegativeMenuId() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderId(1);
			orderProduct.setMenuId(-1);
			orderProduct.setCategoryId(1);
			orderProduct.setNoOfGuest(50);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1, orderProduct);
			
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderProductWithInvalidMenuId() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderId(1);
			orderProduct.setMenuId(20);
			orderProduct.setCategoryId(1);
			orderProduct.setNoOfGuest(50);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1, orderProduct);
			
		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderProductWithCategoryIdZero() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderId(1);
			orderProduct.setMenuId(1);
			orderProduct.setCategoryId(0);
			orderProduct.setNoOfGuest(50);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1, orderProduct);
			
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderProductWithNegativeCategoryId() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderId(1);
			orderProduct.setMenuId(1);
			orderProduct.setCategoryId(-1);
			orderProduct.setNoOfGuest(50);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1, orderProduct);
			
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderProductWithInvalidCategoryId() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderId(1);
			orderProduct.setMenuId(1);
			orderProduct.setCategoryId(20);
			orderProduct.setNoOfGuest(50);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1, orderProduct);
			
		});
		String expectedMessage = "Invalid CategoryId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderProductWithZeroNoOfGuest() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderId(1);
			orderProduct.setMenuId(1);
			orderProduct.setCategoryId(1);
			orderProduct.setNoOfGuest(0);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1, orderProduct);
			
		});
		String expectedMessage = "NoOfGuest cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderProductWithHighNoOfGuest() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderId(1);
			orderProduct.setMenuId(1);
			orderProduct.setCategoryId(1);
			orderProduct.setNoOfGuest(1501);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1, orderProduct);
			
		});
		String expectedMessage = "NoOfGuest should be above 49 and less than 1501";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateOrderProductWithLowNoOfGuest() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderId(1);
			orderProduct.setMenuId(1);
			orderProduct.setCategoryId(1);
			orderProduct.setNoOfGuest(49);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1, orderProduct);
			
		});
		String expectedMessage = "NoOfGuest should be above 49 and less than 1501";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderProductWithLessThanAWeekDeliveryDate() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderId(1);
			orderProduct.setMenuId(1);
			orderProduct.setCategoryId(1);
			orderProduct.setNoOfGuest(50);
			orderProduct.setDeliveryDate(LocalDate.parse("23-09-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1, orderProduct);
			
		});
		String expectedMessage = "Delivery date cannot be less than one week from today";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateOrderProductWithTwoMonthsAboveDeliveryDate() {
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderId(1);
			orderProduct.setMenuId(1);
			orderProduct.setCategoryId(1);
			orderProduct.setNoOfGuest(50);
			orderProduct.setDeliveryDate(LocalDate.parse("23-11-2025",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			orderProductService.createOrderProduct(1, orderProduct);
			
		});
		String expectedMessage = "Delivery date cannot be more than two months from today";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	//update
	@Test
	void testUpdateOrderProductOrderStatusWithOrderIdZero() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			OrderStatus orderStatus = OrderStatus.CANCELLED;
			
			orderProductService.updateOrderStatusAndCancelDate(orderStatus, 0, 1, 1, "Cancel Reason");

		});
		String expectedMessage = "OrderId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
		
	}
	
	
	@Test
	void testUpdateOrderProductOrderStatusWithNegativeOrderId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			OrderStatus orderStatus = OrderStatus.CANCELLED;
			
			orderProductService.updateOrderStatusAndCancelDate(orderStatus, -1, 1, 1, "Cancel Reason");

		});
		String expectedMessage = "OrderId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
		
	}
	
	
	@Test
	void testUpdateOrderProductOrderStatusWithInvalidOrderId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			OrderStatus orderStatus = OrderStatus.CANCELLED;
			
			orderProductService.updateOrderStatusAndCancelDate(orderStatus, 1000, 1, 1, "Cancel Reason");

		});
		String expectedMessage = "Invalid OrderId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductOrderStatusWithMenuIdZero() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			OrderStatus orderStatus = OrderStatus.CANCELLED;
			
			orderProductService.updateOrderStatusAndCancelDate(orderStatus, 1, 0, 1, "Cancel Reason");

		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductOrderStatusWithNegativeMenuId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			OrderStatus orderStatus = OrderStatus.CANCELLED;
			
			orderProductService.updateOrderStatusAndCancelDate(orderStatus, 1, -1, 1, "Cancel Reason");

		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductOrderStatusWithInvalidMenuId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			OrderStatus orderStatus = OrderStatus.CANCELLED;
			
			orderProductService.updateOrderStatusAndCancelDate(orderStatus, 1, 20, 1, "Cancel Reason");

		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductOrderStatusWithCategoryIdZero() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			OrderStatus orderStatus = OrderStatus.CANCELLED;
			
			orderProductService.updateOrderStatusAndCancelDate(orderStatus, 1, 1, 0, "Cancel Reason");

		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductOrderStatusWithNegativeCategoryId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			OrderStatus orderStatus = OrderStatus.CANCELLED;
			
			orderProductService.updateOrderStatusAndCancelDate(orderStatus, 1, 1, -2, "Cancel Reason");

		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductOrderStatusWithInvalidCategoryId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			OrderStatus orderStatus = OrderStatus.CANCELLED;
			
			orderProductService.updateOrderStatusAndCancelDate(orderStatus, 1, 1, 20, "Cancel Reason");

		});
		String expectedMessage = "CategoryId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductOrderStatusWithCancelReasonNull() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			OrderStatus orderStatus = OrderStatus.CANCELLED;
			
			orderProductService.updateOrderStatusAndCancelDate(orderStatus, 1, 1, 1, null);

		});
		String expectedMessage = "CancelReason cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductOrderStatusWithCancelReasonEmpty() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			OrderStatus orderStatus = OrderStatus.CANCELLED;
			
			orderProductService.updateOrderStatusAndCancelDate(orderStatus, 1, 1, 1, "");

		});
		String expectedMessage = "CancelReason cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	
	@Test
	void testUpdateOrderProductCaterApprovalWithOrderIdZero() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			CaterApproval caterApproval = CaterApproval.REJECTED;
			
			orderProductService.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, "Reject Reason", 0, 1, 1);

		});
		String expectedMessage = "OrderId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
		
	}
	
	
	@Test
	void testUpdateOrderProductCaterApprovalWithNegativeOrderId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			CaterApproval caterApproval = CaterApproval.REJECTED;
			
			orderProductService.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, "Reject Reason", -1, 1, 1);

		});
		String expectedMessage = "OrderId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
		
	}
	
	
	@Test
	void testUpdateOrderProductCaterApprovalWithInvalidOrderId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			CaterApproval caterApproval = CaterApproval.REJECTED;
			
			orderProductService.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, "Reject Reason", 1000, 1, 1);

		});
		String expectedMessage = "Invalid OrderId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductCaterApprovalWithMenuIdZero() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			CaterApproval caterApproval = CaterApproval.REJECTED;
			
			orderProductService.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, "Reject Reason", 1, 0, 1);

		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductCaterApprovalWithNegativeMenuId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			CaterApproval caterApproval = CaterApproval.REJECTED;
			
			orderProductService.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, "Reject Reason", 1, -1, 1);

		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductCaterApprovalWithInvalidMenuId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			CaterApproval caterApproval = CaterApproval.REJECTED;
			
			orderProductService.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, "Reject Reason", 1, 20, 1);

		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductCaterApprovalWithCategoryIdZero() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			CaterApproval caterApproval = CaterApproval.REJECTED;
			
			orderProductService.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, "Reject Reason", 1, 1, 0);

		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductCaterApprovalWithNegativeCategoryId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			CaterApproval caterApproval = CaterApproval.REJECTED;
			
			orderProductService.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, "Reject Reason", 1, 1, -2);

		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductCaterApprovalWithInvalidCategoryId() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			CaterApproval caterApproval = CaterApproval.REJECTED;
			
			orderProductService.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, "Reject Reason", 1, 1, 200);

		});
		String expectedMessage = "CategoryId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateOrderProductCaterApprovalWithRejectReasonNull() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			CaterApproval caterApproval = CaterApproval.REJECTED;
			
			orderProductService.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, null, 1, 1, 1);

		});
		String expectedMessage = "RejectReason cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testUpdateOrderProductCaterApprovalWithRejectReasonEmpty() {
		
		OrderProductService orderProductService = new OrderProductService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			CaterApproval caterApproval = CaterApproval.REJECTED;
			
			orderProductService.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, "", 1, 1, 1);

		});
		String expectedMessage = "RejectReason cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testGetAllMenuNames() {
		OrderProductService orderProductService = new OrderProductService();

		assertDoesNotThrow(() -> {
			orderProductService.getAllOrderProductsByOrderId(1);
		});
	}
	
	
	@Test
	void testGetOrderProductByOrderIdAndMenuIdAndCategoryId() {
		OrderProductService orderProductService = new OrderProductService();

		assertDoesNotThrow(() -> {
			orderProductService.getOrderProductByOrderIdAndMenuIdAndCategoryId(1, 1, 1);
		});
	}
	
	
	@Test
	void testGetOrderedMenuIdAndCategoryIdByOrderId() {
		OrderProductService orderProductService = new OrderProductService();

		assertDoesNotThrow(() -> {
			orderProductService.getOrderedMenuIdAndCategoryIdByOrderId(1);
		});
	}
	
	@Test
	void testGetAllOrderIds() {
		OrderProductService orderProductService = new OrderProductService();

		assertDoesNotThrow(() -> {
			orderProductService.getAllOrderIds();
		});
	}
	
	
	@Test
	void testGetAllOrderProducts() {
		OrderProductService orderProductService = new OrderProductService();

		assertDoesNotThrow(() -> {
			orderProductService.getAllOrderProducts();
		});
	}
	
	
}
