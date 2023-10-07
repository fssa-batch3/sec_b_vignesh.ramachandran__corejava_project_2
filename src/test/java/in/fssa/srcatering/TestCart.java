package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Cart;
import in.fssa.srcatering.service.CartService;

public class TestCart {

	@Test
	void testCreateCartWithInvalidInput() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			cartService.createAddtoCart(null);
		});
		String expectedMessage = "Invalid Cart Input";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCartWithUserIdZero() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(0);
			cart.setMenuId(1);
			cart.setCategoryId(1);
			cart.setNoOfGuest(50);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCartWithNegativeUserId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(-2);
			cart.setMenuId(1);
			cart.setCategoryId(1);
			cart.setNoOfGuest(50);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	
	@Test
	void testCreateCartWithInvalidUserId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(1000);
			cart.setMenuId(1);
			cart.setCategoryId(1);
			cart.setNoOfGuest(50);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCartWithMenuIdZero() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(1);
			cart.setMenuId(0);
			cart.setCategoryId(1);
			cart.setNoOfGuest(50);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCartWithNegativeMenuId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(1);
			cart.setMenuId(-2);
			cart.setCategoryId(1);
			cart.setNoOfGuest(50);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCartWithInvalidMenuId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(1);
			cart.setMenuId(200);
			cart.setCategoryId(1);
			cart.setNoOfGuest(50);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCartWithCategoryIdZero() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(1);
			cart.setMenuId(1);
			cart.setCategoryId(0);
			cart.setNoOfGuest(50);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCartWithNegativeCategoryId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(1);
			cart.setMenuId(1);
			cart.setCategoryId(-2);
			cart.setNoOfGuest(50);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateCartWithInvalidCategoryId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(1);
			cart.setMenuId(1);
			cart.setCategoryId(200);
			cart.setNoOfGuest(50);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "CategoryId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCartWithZeroNoOfGuest() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(1);
			cart.setMenuId(1);
			cart.setCategoryId(1);
			cart.setNoOfGuest(0);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "NoOfGuest cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCartWithNegativeNoOfGuest() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(1);
			cart.setMenuId(1);
			cart.setCategoryId(1);
			cart.setNoOfGuest(-20);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "NoOfGuest cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCartWithlowNoOfGuest() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(1);
			cart.setMenuId(1);
			cart.setCategoryId(1);
			cart.setNoOfGuest(49);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "NoOfGuest should be above 49 and less than 1501";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCartWithHighNoOfGuest() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Cart cart = new Cart();
			cart.setUserId(1);
			cart.setMenuId(1);
			cart.setCategoryId(1);
			cart.setNoOfGuest(1501);

			cartService.createAddtoCart(cart);
		});
		String expectedMessage = "NoOfGuest should be above 49 and less than 1501";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	//update 
	
	@Test
	void testUpdateCartWithZeroNoOfGuest() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
		
			LocalDate deliveryDate = LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			cartService.updateCart(0, deliveryDate, 1);
		});
		String expectedMessage = "NoOfGuest cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateCartWithNegativeNoOfGuest() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			LocalDate deliveryDate = LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			cartService.updateCart(-30, deliveryDate, 1);

		});
		String expectedMessage = "NoOfGuest cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateCartWithlowNoOfGuest() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			LocalDate deliveryDate = LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			cartService.updateCart(49, deliveryDate, 1);
			
		});
		String expectedMessage = "NoOfGuest should be above 49 and less than 1501";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateCartWithHighNoOfGuest() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			LocalDate deliveryDate = LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			cartService.updateCart(1501, deliveryDate, 1);
			
		});
		String expectedMessage = "NoOfGuest should be above 49 and less than 1501";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateCartWithLessThanAWeekDeliveryDate() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			LocalDate deliveryDate = LocalDate.parse("23-09-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			cartService.updateCart(100, deliveryDate, 1);
			
		});
		String expectedMessage = "Delivery date cannot be less than one week from today";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testUpdateCartWithTwoMonthsAboveDeliveryDate() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			LocalDate deliveryDate = LocalDate.parse("23-09-2025",DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			cartService.updateCart(100, deliveryDate, 1);
			
		});
		String expectedMessage = "Delivery date cannot be more than two months from today";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testUpdateCartWithZeroCartId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			LocalDate deliveryDate = LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			cartService.updateCart(100, deliveryDate, 0);
			
		});
		String expectedMessage = "CartId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateCartWithNegativeCartId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			LocalDate deliveryDate = LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			cartService.updateCart(100, deliveryDate, -3);
			
		});
		String expectedMessage = "CartId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateCartWithInvalidCartId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			LocalDate deliveryDate = LocalDate.parse("23-11-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			cartService.updateCart(100, deliveryDate, 1000);
			
		});
		String expectedMessage = "Invalid CartId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testDeleteCartWithInvalidCartId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			cartService.deleteCart(1000);
		});
		String expectedMessage = "Invalid CartId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testDeleteCartWithZeroCartId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			cartService.deleteCart(0);
		});
		String expectedMessage = "CartId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testDeleteCartWithNegativeCartId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			cartService.deleteCart(-2);
		});
		String expectedMessage = "CartId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testGetAllCartsByValidUserId() {
		CartService cartService = new CartService();
		
		assertDoesNotThrow(() -> {
			cartService.getAllCartsByUserId(1);
		});
	}
	
	
	@Test
	void testFindAllCartsByUserIdZero() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			cartService.getAllCartsByUserId(0);
		});
		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
 
	
	@Test
	void testFindAllCartsByNegativeUserId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			cartService.getAllCartsByUserId(-2);
		});
		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testFindAllCartsByInvalidUserId() {
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			cartService.getAllCartsByUserId(1000);
		});
		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test 
	void testFindCartByCartIdZero(){
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			cartService.getCartByCartId(0);
		});
		String expectedMessage = "CartId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test 
	void testFindCartByNegativeCartId(){
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			cartService.getCartByCartId(-2);
		});
		String expectedMessage = "CartId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test 
	void testFindCartByInvalidCartId(){
		CartService cartService = new CartService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			cartService.getCartByCartId(1000);
		});
		String expectedMessage = "Invalid CartId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testFindCartCountByUserId() {
		CartService cartService = new CartService();
		
		assertDoesNotThrow(() -> {
			cartService.getCartCountByUserId(1);
		});
	}

}
