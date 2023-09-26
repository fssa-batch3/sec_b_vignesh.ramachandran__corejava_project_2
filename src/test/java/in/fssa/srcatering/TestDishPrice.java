package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.srcatering.service.DishPriceService;


public class TestDishPrice {
	
	@Test
	void testIsCategoryIdExistsForThatMenu() {
		DishPriceService dishPriceService = new DishPriceService();
		
		assertDoesNotThrow(() -> {
			dishPriceService.findPriceByDishId(1);
		});
	}
	
	@Test
	void testFindPriceIdByDishId() {
		DishPriceService dishPriceService = new DishPriceService();
		
		assertDoesNotThrow(() -> {
			dishPriceService.findPriceIdByDishId(1);
		});
	}
	
	@Test
	void testIsDishIdIsValidInDishPrice() {
		DishPriceService dishPriceService = new DishPriceService();
		
		assertDoesNotThrow(() -> {
			dishPriceService.isDishIdIsValid(1);
		});
	}
	
	
	@Test
	void testGetAllPriceIdsByMenuIdAndCategoryId() {
		DishPriceService dishPriceService = new DishPriceService();
		
		assertDoesNotThrow(() -> {
			dishPriceService.getAllPriceIdsByMenuIdAndCategoryId(1, 1);
		});
	}
	
	@Test
	void testGetTotalPriceByPriceId() {
		DishPriceService dishPriceService = new DishPriceService();
		
		List<Integer> priceIds = new ArrayList<>();
		priceIds.add(1);
		priceIds.add(2);
		priceIds.add(3);
		priceIds.add(4);
		priceIds.add(5);
		
		assertDoesNotThrow(() -> {
			dishPriceService.getTotalPriceByPriceId(priceIds);
		});
	}
	
	

}
