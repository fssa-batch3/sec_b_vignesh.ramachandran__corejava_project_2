package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.model.QuantityUnit;
import in.fssa.srcatering.service.DishService;

public class TestDish {

	@Test
	public void testCreateDishWithValidInput() {
		DishService dishservice = new DishService();

		Dish dish = new Dish();

		dish.setMenu_id(1);
		dish.setCategory_id(1);
		dish.setDish_name("MINI UTTAPPAM");
		dish.setDish_price(30);
		dish.setQuantity(2);
		dish.setQuantity_unit(QuantityUnit.NOS);

		assertDoesNotThrow(() -> {
			dishservice.create(dish);
		});
	}

	@Test
	public void testCreateDishWithNull() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			dishservice.create(null);
		});
		String expectedMessage = "Invalid Dish Input";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithDishNameNull() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setCategory_id(1);
			dish.setMenu_id(1);
			dish.setDish_name(null);
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "Dish Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		exception.printStackTrace();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateDishWithDishNameEmpty() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setCategory_id(1);
			dish.setMenu_id(1);
			dish.setDish_name("");
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "Dish Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithAlreadyExistsDishName() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setCategory_id(1);
			dish.setMenu_id(1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "Dish name already Exists";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithMenuIdZero() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenu_id(0);
			dish.setCategory_id(1);
			dish.setDish_name("MINI UTTAPPAM");
			dish.setDish_price(30);
			dish.setQuantity(2);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "Invalid MenuId";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithNegativeMenuId() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenu_id(-1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI UTTAPPAM");
			dish.setDish_price(30);
			dish.setQuantity(2);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "Invalid MenuId";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithInvalidMenuId() {

		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenu_id(5);
			dish.setCategory_id(1);
			dish.setDish_name("MINI UTTAPPAM");
			dish.setDish_price(30);
			dish.setQuantity(2);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateDishWithCategoryIdZero() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenu_id(1);
			dish.setCategory_id(0);
			dish.setDish_name("MINI UTTAPPAM");
			dish.setDish_price(30);
			dish.setQuantity(2);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "Invalid CategoryId";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithNegativeCategoryId() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenu_id(1);
			dish.setCategory_id(-1);
			dish.setDish_name("MINI UTTAPPAM");
			dish.setDish_price(30);
			dish.setQuantity(2);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "Invalid CategoryId";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithInvalidCategoryId() {

		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenu_id(1);
			dish.setCategory_id(5);
			dish.setDish_name("MINI UTTAPPAM");
			dish.setDish_price(30);
			dish.setQuantity(2);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "CategoryId not found";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithNegativeDishPrice() {

		DishService dishservice = new DishService();

		Exception exception = assertThrows(Exception.class, () -> {

			Dish dish = new Dish();

			dish.setMenu_id(1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI UTTAPPAM");
			dish.setDish_price(-30);
			dish.setQuantity(2);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "Invalid Price";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		exception.printStackTrace();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	// Quantity
	@Test
	public void testCreateDishWithNegativeQuantity() {

		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenu_id(1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI UTTAPPAM");
			dish.setDish_price(30);
			dish.setQuantity(-2);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "Invalid Quantity";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithQuantityZero() {

		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenu_id(1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI UTTAPPAM");
			dish.setDish_price(30);
			dish.setQuantity(0);
			dish.setQuantity_unit(QuantityUnit.NOS);

			dishservice.create(dish);
		});
		String expectedMessage = "Invalid Quantity";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithWrongQuantityAndQuantityUnit() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			Dish dish = new Dish();

			dish.setMenu_id(1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI UTTAPPAM");
			dish.setDish_price(30);
			dish.setQuantity(2);
			dish.setQuantity_unit(QuantityUnit.GRAMS);

			dishservice.create(dish);
		});
		
		String expectedMessage = "Check Quantity and QuantityUnit";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	// update
	@Test
	public void testUpdatedDishWithNegativeQuantity() {
		DishService dishservice = new DishService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Dish dish = new Dish();
			dish.setMenu_id(1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(-1);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(1);

			dishservice.update(dish);
		});
		String expectedMessage = "Invalid Quantity";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithQuantityZero() {

		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenu_id(1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(0);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(1);

			dishservice.update(dish);
		});
		String expectedMessage = "Invalid Quantity";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithWrongQuantityAndQuantityUnit() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Dish dish = new Dish();
			dish.setMenu_id(1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(100);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(1);

			dishservice.update(dish);
		});
		
		String expectedMessage = "Check Quantity and QuantityUnit";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithMenuIdZero() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenu_id(0);
			dish.setCategory_id(1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(1);

			dishservice.update(dish);
		});
		String expectedMessage = "Invalid MenuId";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithNegativeMenuId() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenu_id(-1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(1);

			dishservice.update(dish);
		});
		String expectedMessage = "Invalid MenuId";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateDishWithInvalidMenuId() {

		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenu_id(5);
			dish.setCategory_id(1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(1);

			dishservice.update(dish);
		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();
		//System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateDishWithCategoryIdZero() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenu_id(1);
			dish.setCategory_id(0);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(1);

			dishservice.update(dish);
		});
		String expectedMessage = "Invalid CategoryId";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateDishWithNegativeCategoryId() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenu_id(1);
			dish.setCategory_id(-1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(1);

			dishservice.update(dish);
		});
		String expectedMessage = "Invalid CategoryId";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateDishWithInvalidCategoryId() {

		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenu_id(1);
			dish.setCategory_id(5);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(1);

			dishservice.update(dish);
		});
		String expectedMessage = "CategoryId not found";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithNegativeDishPrice() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenu_id(1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(-10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(1);

			dishservice.update(dish);
		});
		String expectedMessage = "Invalid Price";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	
	@Test
	public void testUpdateDishWithDishIdZero() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenu_id(1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(0);

			dishservice.update(dish);
		});
		String expectedMessage = "Invalid DishId";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithNegativeDishId() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenu_id(1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(-1);

			dishservice.update(dish);
		});
		String expectedMessage = "Invalid DishId";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	
	@Test
	public void testUpdateDishWithInvalidDishId() {
		DishService dishservice = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenu_id(1);
			dish.setCategory_id(1);
			dish.setDish_name("MINI LADDU");
			dish.setDish_price(10);
			dish.setQuantity(1);
			dish.setQuantity_unit(QuantityUnit.NOS);
			dish.setId(9);

			dishservice.update(dish);
		});
		String expectedMessage = "Invalid DishId";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	// delete
	@Test
	public void testDeleteDishWithDishIdZero() {
		DishService dishservice = new DishService();
		
		Exception exception = assertThrows(ValidationException.class, ()-> {

			dishservice.delete(1, 1, 0);
		});
		String expectedMessage = "Invalid DishId";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDeleteDishWithNegativeDishId() {
		DishService dishservice = new DishService();
		
		Exception exception = assertThrows(ValidationException.class, ()-> {

			dishservice.delete(1, 1, -2);
		});
		String expectedMessage = "Invalid DishId";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDeleteDishWithInvalidDishId() {
		DishService dishservice = new DishService();
		
		Exception exception = assertThrows(ValidationException.class, ()-> {

			dishservice.delete(1, 1, 8);
		});
		String expectedMessage = "DishId not found";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

}
