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

		assertDoesNotThrow(() -> {
			categoryService.createCategory(category);
		});
	}
	
	@Test
	void testCreateCategoryWithCategoryNameNull() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			Category category = new Category();
			category.setCategoryName(null);
			
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
			
			categoryService.createCategory(category);
		});
		String expectedMessage = "CategoryName already exists";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	

	@Test
	void testFindMenuByIdWithValidCategoryId() {
		CategoryService categoryService = new CategoryService();

		assertDoesNotThrow(() -> {
			categoryService.findByIdCategoryId(1);
		});
	}

	@Test
	void testFindMenuByIdWithCategoryIdZero() {
		CategoryService categoryService = new CategoryService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.findByIdCategoryId(0);
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testFindMenuByIdWithNegativeCategoryId() {
		CategoryService categoryService = new CategoryService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.findByIdCategoryId(-1);
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testFindMenuByIdWithInvalidCategoryId() {
		CategoryService categoryService = new CategoryService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.findByIdCategoryId(20);
		});
		String expectedMessage = "CategoryId not found";
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
