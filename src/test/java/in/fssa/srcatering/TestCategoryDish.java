package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.srcatering.service.CategoryDishService;

public class TestCategoryDish {
	
	@Test
	void testFindDishIdByMenuIdAndCategoryId() {
		CategoryDishService categoryDishService = new CategoryDishService();
		
		assertDoesNotThrow(() -> {
			categoryDishService.findDishIdByMenuIdAndCategoryId(1,1);
		});
	}
	
	@Test
	void testFindDishNamesByMenuIdAndCategoryId() {
		CategoryDishService categoryDishService = new CategoryDishService();
		
		assertDoesNotThrow(() -> {
			categoryDishService.findDishNamesByMenuIdAndCategoryId(1, 1);
		});
	}

}
