package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.service.MenuService;

public class TestMenu {
	
	@Test
	public void testFindMenuByIdWithValidMenuId() {
		MenuService menuService = new MenuService();
		
		assertDoesNotThrow(() -> {
			menuService.findByMenuId(1);
		});
	}
	
	
	@Test
	public void testFindMenuByIdWithMenuIdZero() {
		MenuService menuService = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			menuService.findByMenuId(0);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	

	@Test
	public void testFindMenuByIdWithNegativeMenuId() {
		MenuService menuService = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			menuService.findByMenuId(-1);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindMenuByIdWithInvalidMenuId() {
		MenuService menuService = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			menuService.findByMenuId(5);
		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

}
