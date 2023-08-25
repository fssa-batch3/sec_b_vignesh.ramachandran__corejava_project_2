package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.service.CategoryService;

public class TestCategory {
	
	@Test
	public void testFindMenuByIdWithValidCategoryId() {
		CategoryService categoryService = new CategoryService();
		
		assertDoesNotThrow(() -> {
			categoryService.findByIdCategoryId(1);
		});
	}
	
	
	@Test
	public void testFindMenuByIdWithCategoryIdZero() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			categoryService.findByIdCategoryId(0);
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	

	@Test
	public void testFindMenuByIdWithNegativeCategoryId() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			categoryService.findByIdCategoryId(-1);
		});
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindMenuByIdWithInvalidCategoryId() {
		CategoryService categoryService = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			categoryService.findByIdCategoryId(5);
		});
		String expectedMessage = "CategoryId not found";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

}
