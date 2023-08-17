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
		MenuService menuservice = new MenuService();
		
		assertDoesNotThrow(() -> {
			menuservice.findById(1);
		});
	}
	
	
	@Test
	public void testFindMenuByIdWithMenuIdZero() {
		MenuService menuservice = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			menuservice.findById(0);
		});
		String expectedMessage = "Invalid MenuId";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	

	@Test
	public void testFindMenuByIdWithNegativeMenuId() {
		MenuService menuservice = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			menuservice.findById(-1);
		});
		String expectedMessage = "Invalid MenuId";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindMenuByIdWithInvalidMenuId() {
		MenuService menuservice = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			menuservice.findById(5);
		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

}
