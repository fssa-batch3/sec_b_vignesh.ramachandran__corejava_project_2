package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.model.QuantityUnit;
import in.fssa.srcatering.service.CategoryDishService;
import in.fssa.srcatering.service.DishService;

public class TestDish {

	@Test
	public void testCreateDishWithValidInput() {
		DishService dishService = new DishService();

		Dish dish = new Dish();

		dish.setMenuId(1);
		dish.setCategoryId(1);
		dish.setDishName(generateRandomDishName());
		dish.setDishPrice(30);
		dish.setQuantity(2);
		dish.setQuantityUnit(QuantityUnit.NOS);

		assertDoesNotThrow(() -> {
			dishService.createDish(dish);
		});
	}

	@Test
	public void testCreateDishWithNull() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			dishService.createDish(null);
		});
		String expectedMessage = "Invalid Dish Input";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithDishNameNull() {
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

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateDishWithDishNameEmpty() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithAlreadyExistsDishName() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setCategoryId(1);
			dish.setMenuId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "Dish name already Exists";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithMenuIdZero() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithNegativeMenuId() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithInvalidMenuId() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(10);
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

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateDishWithCategoryIdZero() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithNegativeCategoryId() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithInvalidCategoryId() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();

			dish.setMenuId(1);
			dish.setCategoryId(10);
			dish.setDishName("MINI UTTAPPAM");
			dish.setDishPrice(30);
			dish.setQuantity(2);
			dish.setQuantityUnit(QuantityUnit.NOS);

			dishService.createDish(dish);
		});
		String expectedMessage = "CategoryId not found";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithNegativeDishPrice() {

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
		
		assertTrue(expectedMessage.equals(actualMessage));
	}

	// Quantity
	@Test
	public void testCreateDishWithNegativeQuantity() {

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

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithQuantityZero() {

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

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateDishWithWrongQuantityAndQuantityUnit() {
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
		
		String expectedMessage = "Check Quantity and QuantityUnit";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	

	
	@Test
	public void testUpdatedDishWithNegativeQuantity() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithQuantityZero() {

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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithWrongQuantityAndQuantityUnit() {
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
		
		String expectedMessage = "Check Quantity and QuantityUnit";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithMenuIdZero() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithNegativeMenuId() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateDishWithInvalidMenuId() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(10);
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

		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateDishWithCategoryIdZero() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateDishWithNegativeCategoryId() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateDishWithInvalidCategoryId() {

		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(10);
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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithNegativeDishPrice() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	
	@Test
	public void testUpdateDishWithDishIdZero() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateDishWithNegativeDishId() {
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

		assertTrue(expectedMessage.equals(actualMessage));
	}

	
	@Test
	public void testUpdateDishWithInvalidDishId() {
		DishService dishService = new DishService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Dish dish = new Dish();
			dish.setMenuId(1);
			dish.setCategoryId(1);
			dish.setDishName("MINI LADDU");
			dish.setDishPrice(10);
			dish.setQuantity(1);
			dish.setQuantityUnit(QuantityUnit.NOS);
			dish.setId(100);

			dishService.updateDish(dish);
		});
		String expectedMessage = "Invalid DishId";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	// delete
	@Test
	public void testDeleteDishWithValidDishId() {
		DishService dishService = new DishService();
		
		assertDoesNotThrow(() -> {
			dishService.deleteDish(1, 1, 4);
		});
		
		CategoryDishService categorydishService = new CategoryDishService();
		try {
			categorydishService.changeCategoryDishStatus(1, 1, 4);
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testDeleteDishWithDishIdZero() {
		DishService dishService = new DishService();
		
		Exception exception = assertThrows(ValidationException.class, ()-> {

			dishService.deleteDish(1, 1, 0);
		});
		String expectedMessage = "DishId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDeleteDishWithNegativeDishId() {
		DishService dishService = new DishService();
		
		Exception exception = assertThrows(ValidationException.class, ()-> {

			dishService.deleteDish(1, 1, -2);
		});
		String expectedMessage = "DishId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDeleteDishWithInvalidDishId() {
		DishService dishService = new DishService();
		
		Exception exception = assertThrows(ValidationException.class, ()-> {

			dishService.deleteDish(1, 1, 100);
		});
		String expectedMessage = "DishId not found";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
    private String generateRandomDishName() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder dishName = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = (int) (Math.random() * alphabet.length());
            char randomChar = alphabet.charAt(index);
            dishName.append(Character.toUpperCase(randomChar));
        }

        return dishName.toString();
    }

}
