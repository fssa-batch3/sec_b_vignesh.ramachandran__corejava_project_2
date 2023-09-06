package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Category;
import in.fssa.srcatering.service.CategoryService;

class TestCategory {

	@Test
	void testCreateCategoryWithValidInput() {
		CategoryService categoryService = new CategoryService();
		
		Category category = new Category();
		category.setCategoryName(generateRandomCategoryName());
		category.setImage("https://iili.io/HWh0ZrB.jpg");
		category.setMenu_id(1);

		assertDoesNotThrow(() -> {
			categoryService.createCategory(category); 
		});
	}
	
	
	@Test
	void testCreateCategoryWithInvalidInput() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			categoryService.createCategory(null);
		});
		String expectedMessage = "Invalid Category Input";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	
	@Test
	void testCreateCategoryWithCategoryNameNull() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			Category category = new Category();
			category.setCategoryName(null);
			category.setImage("https://iili.io/HWh0ZrB.jpg");
			category.setMenu_id(1);
			
			categoryService.createCategory(category);
		});
		String expectedMessage = "CategoryName cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateCategoryWithCategoryNameEmpty() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			Category category = new Category();
			category.setCategoryName("");
			category.setImage("https://iili.io/HWh0ZrB.jpg");
			category.setMenu_id(1);
			
			categoryService.createCategory(category);
		});
		String expectedMessage = "CategoryName cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateCategoryWithAlreadyExistsCategoryName() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			Category category = new Category();
			category.setCategoryName("Ordinary");
			category.setImage("https://iili.io/HWh0ZrB.jpg");
			category.setMenu_id(1);
			
			categoryService.createCategory(category);
		});
		String expectedMessage = "CategoryName already exists";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateCategoryWithImageNull() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			Category category = new Category();
			category.setCategoryName("Ultra");
			category.setImage(null);
			category.setMenu_id(1);
			
			categoryService.createCategory(category);
		});
		String expectedMessage = "CategoryImage cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateCategoryWithImageEmpty() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			Category category = new Category();
			category.setCategoryName("Ultra");
			category.setImage(""); 
			category.setMenu_id(1);
			
			categoryService.createCategory(category);
		});
		String expectedMessage = "CategoryImage cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCategoryWithInvalidImage() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			Category category = new Category();
			category.setCategoryName("Ultra");
			category.setImage("httlskd2345");
			category.setMenu_id(1);
			
			categoryService.createCategory(category);
		});
		String expectedMessage = "Image should be URL.eg:http, https";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateCategoryWithMenuIdZero() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			Category category = new Category();
			category.setCategoryName("Ultra");
			category.setImage("https://iili.io/HWh0ZrB.jpg");
			category.setMenu_id(0);
			
			categoryService.createCategory(category);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateCategoryWithNegativeMenuId() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			Category category = new Category();
			category.setCategoryName("Ultra");
			category.setImage("https://iili.io/HWh0ZrB.jpg");
			category.setMenu_id(-2);
			
			categoryService.createCategory(category);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateCategoryWithInvalidMenuId() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			Category category = new Category();
			category.setCategoryName("Ultra");
			category.setImage("https://iili.io/HWh0ZrB.jpg");
			category.setMenu_id(20);
			
			categoryService.createCategory(category);
		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}


	
    private String generateRandomCategoryName() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder dishName = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * alphabet.length());
            char randomChar = alphabet.charAt(index);
            dishName.append(Character.toUpperCase(randomChar));
        }

        return dishName.toString();
    }

}
