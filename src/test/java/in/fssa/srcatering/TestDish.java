package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
		dish.setQuantity_unit(QuantityUnit.nos);

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
			dish.setDish_price(0);
			dish.setQuantity(20);
			dish.setQuantity_unit(QuantityUnit.grams);

			dishservice.create(dish);
		});
		String expectedMessage = "Dish Name cannot be null or empty";
		String actualMessage = exception.getMessage();

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
			dish.setDish_price(0);
			dish.setQuantity(20);
			dish.setQuantity_unit(QuantityUnit.grams);

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
			dish.setDish_name("SAMBAR");
			dish.setDish_price(0);
			dish.setQuantity(20);
			dish.setQuantity_unit(QuantityUnit.grams);

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
			dish.setCategory_id(1);
			dish.setMenu_id(0);
			dish.setDish_name("SAMBAR");
			dish.setDish_price(0);
			dish.setQuantity(20);
			dish.setQuantity_unit(QuantityUnit.grams);

			dishservice.create(dish);
		});
		String expectedMessage = "Invalid MenuId";
		String actualMessage = exception.getMessage();
		System.out.println(exception.getMessage());

		assertTrue(expectedMessage.equals(actualMessage));
		
	}

}
