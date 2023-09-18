//package in.fssa.srcatering;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import org.junit.jupiter.api.Test;
//import in.fssa.srcatering.exception.ValidationException;
//import in.fssa.srcatering.model.Cart;
//import in.fssa.srcatering.service.CartService;
//
//public class TestCart {
//
//	@Test
//	void testCreateCartWithInvalidInput() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//
//			cartService.createAddtoCart(null);
//		});
//		String expectedMessage = "Invalid Cart Input";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testCreateCartWithUserIdZero() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(0);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "UserId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testCreateCartWithNegativeUserId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(-2);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "UserId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	
//	@Test
//	void testCreateCartWithInvalidUserId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(100);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "Invalid UserId";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testCreateCartWithMenuIdZero() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(0);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "MenuId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testCreateCartWithNegativeMenuId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(-2);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "MenuId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testCreateCartWithInvalidMenuId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(20);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "MenuId not found";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testCreateCartWithCategoryIdZero() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(0);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "CategoryId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testCreateCartWithNegativeCategoryId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(-2);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "CategoryId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	@Test
//	void testCreateCartWithInvalidCategoryId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(20);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "CategoryId not found";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testCreateCartWithZeroNoOfGuest() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(0);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "NoOfGuest cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testCreateCartWithNegativeNoOfGuest() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(-20);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "NoOfGuest cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testCreateCartWithHighNoOfGuest() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(501);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "NoOfGuest should be above 49 and less than 501";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testCreateCartWithInvalidDeliveryDate() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(501);
//			cart.setDeliveryDate(LocalDate.parse("10-09-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "The date is more than one week from today";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	@Test
//	void testCreateCartWithAlreadyExistsMenuIdAndCategoryIdForTheSameUser() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//
//			cartService.createAddtoCart(cart);
//		});
//		String expectedMessage = "This Menu With Category Already Exists in the cart";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
////	@Test
////	void testUpdateCartWithUserIdZero() {
////		CartService cartService = new CartService();
////
////		Exception exception = assertThrows(ValidationException.class, () -> {
////			Cart cart = new Cart();
////			cart.setUserId(0);
////			cart.setMenuId(1);
////			cart.setCategoryId(1);
////			cart.setNoOfGuest(50);
////			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
////			cart.setId(1);
////
////			cartService.updateCart(0, null, 0)
////		});
////		String expectedMessage = "UserId cannot be less than or equal to zero";
////		String actualMessage = exception.getMessage();
////
////		assertEquals(expectedMessage, actualMessage);
////	}
//	
////	@Test
////	void testUpdateCartWithNegativeUserId() {
////		CartService cartService = new CartService();
////
////		Exception exception = assertThrows(ValidationException.class, () -> {
////			Cart cart = new Cart();
////			cart.setUserId(-2);
////			cart.setMenuId(1);
////			cart.setCategoryId(1);
////			cart.setNoOfGuest(50);
////			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
////			cart.setId(1);
////
////			cartService.updateAddtoCart(cart);
////		});
////		String expectedMessage = "UserId cannot be less than or equal to zero";
////		String actualMessage = exception.getMessage();
////
////		assertEquals(expectedMessage, actualMessage);
////	}
//	
////	@Test
////	void testUpdateCartWithInvalidUserId() {
////		CartService cartService = new CartService();
////
////		Exception exception = assertThrows(ValidationException.class, () -> {
////			Cart cart = new Cart();
////			cart.setUserId(100);
////			cart.setMenuId(1);
////			cart.setCategoryId(1);
////			cart.setNoOfGuest(50);
////			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
////			cart.setId(1);
////
////			cartService.updateAddtoCart(cart);
////		});
////		String expectedMessage = "Invalid UserId";
////		String actualMessage = exception.getMessage();
////
////		assertEquals(expectedMessage, actualMessage);
////	}
//	
//	
////	@Test
////	void testUpdateCartWithZeroMenuId() {
////		CartService cartService = new CartService();
////
////		Exception exception = assertThrows(ValidationException.class, () -> {
////			Cart cart = new Cart();
////			cart.setUserId(1);
////			cart.setMenuId(0);
////			cart.setCategoryId(1);
////			cart.setNoOfGuest(50);
////			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
////			cart.setId(1);
////
////			cartService.updateAddtoCart(cart);
////		});
////		String expectedMessage = "MenuId cannot be less than or equal to zero";
////		String actualMessage = exception.getMessage();
////
////		assertEquals(expectedMessage, actualMessage);
////	}
//	
//	
////	@Test
////	void testUpdateCartWithNegativeMenuId() {
////		CartService cartService = new CartService();
////
////		Exception exception = assertThrows(ValidationException.class, () -> {
////			Cart cart = new Cart();
////			cart.setUserId(1);
////			cart.setMenuId(-2);
////			cart.setCategoryId(1);
////			cart.setNoOfGuest(50);
////			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
////			cart.setId(1);
////
////			cartService.updateAddtoCart(cart);
////		});
////		String expectedMessage = "MenuId cannot be less than or equal to zero";
////		String actualMessage = exception.getMessage();
////
////		assertEquals(expectedMessage, actualMessage);
////	}
//	
//	
////	@Test
////	void testUpdateCartWithInvalidMenuId() {
////		CartService cartService = new CartService();
////
////		Exception exception = assertThrows(ValidationException.class, () -> {
////			Cart cart = new Cart();
////			cart.setUserId(1);
////			cart.setMenuId(20);
////			cart.setCategoryId(1);
////			cart.setNoOfGuest(50);
////			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
////			cart.setId(1);
////
////			cartService.updateAddtoCart(cart);
////		});
////		String expectedMessage = "MenuId not found";
////		String actualMessage = exception.getMessage();
////
////		assertEquals(expectedMessage, actualMessage);
////	}
//	
//	
////	@Test
////	void testUpdateCartWithZeroCategoryId() {
////		CartService cartService = new CartService();
////
////		Exception exception = assertThrows(ValidationException.class, () -> {
////			Cart cart = new Cart();
////			cart.setUserId(1);
////			cart.setMenuId(1);
////			cart.setCategoryId(0);
////			cart.setNoOfGuest(50);
////			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
////			cart.setId(1);
////
////			cartService.updateAddtoCart(cart);
////		});
////		String expectedMessage = "CategoryId cannot be less than or equal to zero";
////		String actualMessage = exception.getMessage();
////
////		assertEquals(expectedMessage, actualMessage);
////	}
//	
//	
////	@Test
////	void testUpdateCartWithNegativeCategoryId() {
////		CartService cartService = new CartService();
////
////		Exception exception = assertThrows(ValidationException.class, () -> {
////			Cart cart = new Cart();
////			cart.setUserId(1);
////			cart.setMenuId(1);
////			cart.setCategoryId(-2);
////			cart.setNoOfGuest(50);
////			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
////			cart.setId(1);
////
////			cartService.updateAddtoCart(cart);
////		});
////		String expectedMessage = "CategoryId cannot be less than or equal to zero";
////		String actualMessage = exception.getMessage();
////
////		assertEquals(expectedMessage, actualMessage);
////	}
//	
//	
////	@Test
////	void testUpdateCartWithInvalidCategoryId() {
////		CartService cartService = new CartService();
////
////		Exception exception = assertThrows(ValidationException.class, () -> {
////			Cart cart = new Cart();
////			cart.setUserId(1);
////			cart.setMenuId(1);
////			cart.setCategoryId(20);
////			cart.setNoOfGuest(50);
////			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
////			cart.setId(1);
////
////			cartService.updateAddtoCart(cart);
////		});
////		String expectedMessage = "CategoryId not found";
////		String actualMessage = exception.getMessage();
////
////		assertEquals(expectedMessage, actualMessage);
////	}
//	
//	
////	@Test
////	void testUpdateCartWithZeroNoOfGuest() {
////		CartService cartService = new CartService();
////
////		Exception exception = assertThrows(ValidationException.class, () -> {
////			Cart cart = new Cart();
////			cart.setUserId(1);
////			cart.setMenuId(1);
////			cart.setCategoryId(1);
////			cart.setNoOfGuest(0);
////			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
////			cart.setId(1);
////
////			cartService.updateAddtoCart(cart);
////		});
////		String expectedMessage = "NoOfGuest cannot be less than or equal to zero";
////		String actualMessage = exception.getMessage();
////
////		assertEquals(expectedMessage, actualMessage);
////	}
//	
//	
//	@Test
//	void testUpdateCartWithNegativeNoOfGuest() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(-2);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//			cart.setId(1);
//
//			cartService.updateAddtoCart(cart);
//		});
//		String expectedMessage = "NoOfGuest cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testUpdateCartWithHighNoOfGuest() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(501);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//			cart.setId(1);
//
//			cartService.updateAddtoCart(cart);
//		});
//		String expectedMessage = "NoOfGuest should be above 49 and less than 501";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testUpdateCartWithInvalidDeliveryDate() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(501);
//			cart.setDeliveryDate(LocalDate.parse("10-09-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//			cart.setId(1);
//
//			cartService.updateAddtoCart(cart);
//		});
//		String expectedMessage = "The date is more than one week from today";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testUpdateCartWithZeroCartId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//			cart.setId(0);
//
//			cartService.updateAddtoCart(cart);
//		});
//		String expectedMessage = "CartId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testUpdateCartWithNegativeCartId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//			cart.setId(-2);
//
//			cartService.updateAddtoCart(cart);
//		});
//		String expectedMessage = "CartId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testUpdateCartWithInvalidCartId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			Cart cart = new Cart();
//			cart.setUserId(1);
//			cart.setMenuId(1);
//			cart.setCategoryId(1);
//			cart.setNoOfGuest(50);
//			cart.setDeliveryDate(LocalDate.parse("10-10-2023",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//			cart.setId(100);
//
//			cartService.updateAddtoCart(cart);
//		});
//		String expectedMessage = "Invalid CartId";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testDeleteCartWithInvalidCartId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//
//			cartService.deleteCart(100);
//		});
//		String expectedMessage = "Invalid CartId";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testDeleteCartWithZeroCartId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//
//			cartService.deleteCart(0);
//		});
//		String expectedMessage = "CartId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	@Test
//	void testDeleteCartWithNegativeCartId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//
//			cartService.deleteCart(-2);
//		});
//		String expectedMessage = "CartId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testFindAllCartsByUserIdZero() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//
//			cartService.getAllCartsByUserId(0);
//		});
//		String expectedMessage = "UserId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
// 
//	
//	@Test
//	void testFindAllCartsByNegativeUserId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//
//			cartService.getAllCartsByUserId(-2);
//		});
//		String expectedMessage = "UserId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test
//	void testFindAllCartsByInvalidUserId() {
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//
//			cartService.getAllCartsByUserId(100);
//		});
//		String expectedMessage = "Invalid UserId";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test 
//	void testFindCartByCartIdZero(){
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//
//			cartService.getCartByCartId(0);
//		});
//		String expectedMessage = "CartId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test 
//	void testFindCartByNegativeCartId(){
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//
//			cartService.getCartByCartId(-2);
//		});
//		String expectedMessage = "CartId cannot be less than or equal to zero";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//	
//	
//	@Test 
//	void testFindCartByInvalidCartId(){
//		CartService cartService = new CartService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//
//			cartService.getCartByCartId(100);
//		});
//		String expectedMessage = "Invalid CartId";
//		String actualMessage = exception.getMessage();
//
//		assertEquals(expectedMessage, actualMessage);
//	}
//
//}
