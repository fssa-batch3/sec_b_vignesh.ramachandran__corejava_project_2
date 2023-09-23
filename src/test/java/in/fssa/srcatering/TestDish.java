package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.model.QuantityUnit;
import in.fssa.srcatering.service.CategoryDishService;
import in.fssa.srcatering.service.DishService;

 class TestDish {

//	@Test
//	 void testCreateDishWithValidInput() {
//		DishService dishService = new DishService();
//
//		Dish dish = new Dish();
//
//		dish.setMenuId(1);
//		dish.setCategoryId(1);
//		dish.setDishName(generateRandomDishName());
//		dish.setDishPrice(30);
//		dish.setQuantity(2);
//		dish.setQuantityUnit(QuantityUnit.NOS);
//
//		assertDoesNotThrow(() -> {
//			dishService.createDish(dish);
//		});
//	}

	@Test
	 void testCreateDishWithInvalidInput() { 
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			dishService.createDish(null); 
		});
		String expectedMessage = "Invalid Dish Input";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateDishWithDishNameNull() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setCategoryId(1);
			dish.setMenuId(1);
			dish.setDishName(null);
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "Dish Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);

	}

	@Test
	 void testCreateDishWithDishNameEmpty() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setCategoryId(1);
			dish.setMenuId(1);
			dish.setDishName("");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "Dish Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateDishWithAlreadyExistsDishName() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setCategoryId(1);
			dish.setMenuId(1);
			dish.setDishName("mini LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "Dish name already Exists";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateDishWithMenuIdZero() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(0);
			dish.setCategoryId(1);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(30);
			dish.setQuantity(2);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateDishWithNegativeMenuId() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(-1);
			dish.setCategoryId(1);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(30);
			dish.setQuantity(2);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateDishWithInvalidMenuId() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(20);
			dish.setCategoryId(1);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(30);
			dish.setQuantity(2);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);

	}

	@Test
	 void testCreateDishWithCategoryIdZero() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(1);
			dish.setCategoryId(0);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(30);
			dish.setQuantity(2);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateDishWithNegativeCategoryId() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(1);
			dish.setCategoryId(-1);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(30);
			dish.setQuantity(2);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateDishWithInvalidCategoryId() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(1);
			dish.setCategoryId(20);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(30);
			dish.setQuantity(2);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "CategoryId not found";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateDishWithNegativeDishPrice() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(Exception.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(-30);
			dish.setQuantity(2);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "Price cannot be less than zero";
		String actualMessage = exception.getMessage();
		
		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	void testCreateDishWithInvalidPrice() {
		DishService dishService = new DishService();
		
		Exception exception = assertThrows(Exception.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(101);
			dish.setQuantity(2);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "Price cannot be above 100";
		String actualMessage = exception.getMessage();
		
		assertEquals(expectedMessage,actualMessage);
	}

	// Quantity
	@Test
	 void testCreateDishWithNegativeQuantity() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(30);
			dish.setQuantity(-2);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "Quantity cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateDishWithQuantityZero() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(30);
			dish.setQuantity(0);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "Quantity cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateDishWithWrongQuantityAndQuantityUnit() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Dish dish = new Dish();

			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(30);
			dish.setQuantity(2);
			dish.setQuantityUnit(QuantityUnit.GRAMS);

			dishService.createDish(dish);
		});
		
		String expectedMessage = "Check Quantity and QuantityUnit Eg(NOS cannot be greater than 5 or less than 1 "
				+ "& GRAMS cannot be less than 20 or greater than 500";
		String actualMessage = exception.getMessage();
 
		assertEquals(expectedMessage,actualMessage);
	}
	

	// update
	@Test
	 void testUpdatedDishWithNegativeQuantity() {
		DishService dishService = new DishService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(-1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1);

			dishService.updateDish(dish);
		});
		String expectedMessage = "Quantity cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}
	
	
	@Test
	 void testUpdateDishWithQuantityZero() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(0);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1);

			dishService.updateDish(dish);
		});
		String expectedMessage = "Quantity cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	
	@Test
	 void testUpdateDishWithWrongQuantityAndQuantityUnit() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(100);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1);

			dishService.updateDish(dish);
		});
		
		String expectedMessage = "Check Quantity and QuantityUnit Eg(NOS cannot be greater than 5 or less than 1 "
				+ "& GRAMS cannot be less than 20 or greater than 500";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	
	@Test
	 void testUpdateDishWithMenuIdZero() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(0);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1);

			dishService.updateDish(dish);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}
	
	
	@Test
	 void testUpdateDishWithNegativeMenuId() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(-1);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1);

			dishService.updateDish(dish);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testUpdateDishWithInvalidMenuId() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(20);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1);

			dishService.updateDish(dish);
		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();
		//System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);

	}

	@Test
	 void testUpdateDishWithCategoryIdZero() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(0);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1);

			dishService.updateDish(dish);
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testUpdateDishWithNegativeCategoryId() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(-1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1);

			dishService.updateDish(dish);
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testUpdateDishWithInvalidCategoryId() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(20);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1);

			dishService.updateDish(dish);
		});
		String expectedMessage = "CategoryId not found";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}
	
	
	@Test
	 void testUpdateDishWithNegativeDishPrice() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(-10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1);

			dishService.updateDish(dish);
		});
		String expectedMessage = "Price cannot be less than zero";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}
	
	
	@Test
	 void testUpdateDishWithInvalidDishPrice() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(101);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1);

			dishService.updateDish(dish);
		});
		String expectedMessage = "Price cannot be above 100";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}
	
	
	@Test
	 void testUpdateDishWithDishIdZero() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(0);

			dishService.updateDish(dish);
		});
		String expectedMessage = "DishId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}
	
	
	@Test
	 void testUpdateDishWithNegativeDishId() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(-1);

			dishService.updateDish(dish);
		});
		String expectedMessage = "DishId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}

	
	@Test
	 void testUpdateDishWithInvalidDishId() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(1000);

			dishService.updateDish(dish);
		});
		String expectedMessage = "Invalid DishId";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}
	
	
	// delete
	@Test
	 void testDeleteDishWithValidDishId() {
		DishService dishService = new DishService();
		
		assertDoesNotThrow(() -> {
			dishService.deleteDish(1, 1, 1);
		});
		
		CategoryDishService categorydishService = new CategoryDishService();
		try {
			categorydishService.changeCategoryDishStatus(1, 1, 1);
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	 void testDeleteDishWithDishIdZero() {
		DishService dishService = new DishService();
		
		Exception exception = assertThrows(ValidationException.class, ()-> {

			dishService.deleteDish(1, 1, 0);
		});
		String expectedMessage = "DishId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	 void testDeleteDishWithNegativeDishId() {
		DishService dishService = new DishService();
		
		Exception exception = assertThrows(ValidationException.class, ()-> {

			dishService.deleteDish(1, 1, -2);
		});
		String expectedMessage = "DishId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	 void testDeleteDishWithInvalidDishId() {
		DishService dishService = new DishService();
		
		Exception exception = assertThrows(ValidationException.class, ()-> {

			dishService.deleteDish(1, 1, 1000);
		});
		String expectedMessage = "DishId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	
//    private String generateRandomDishName() {
//        String alphabet = "abcdefghijklmnopqrstuvwxyz";
//        StringBuilder dishName = new StringBuilder();
//
//        for (int i = 0; i < 10; i++) {
//            int index = (int) (Math.random() * alphabet.length());
//            char randomChar = alphabet.charAt(index);
//            dishName.append(Character.toUpperCase(randomChar));
//        }
//
//        return dishName.toString();
//    }

}
