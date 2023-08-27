package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Menu;
import in.fssa.srcatering.service.MenuService;

class TestMenu {

	@Test
	void testCreateMenuWithValidInput() {
		MenuService menuService = new MenuService();

		Menu menu = new Menu();
		menu.setMenuName(generateRandomMenuName());
		menu.setDescription(
				"SR Catering Service present a detailed dinner menu which is available for all parties, "
				+ "wedding and reception; we also aid customers to create a personalized and special menu just "
				+ "according to the taste and budget");
		menu.setImage("https://iili.io/HWhlwKB.jpg");

		assertDoesNotThrow(() -> {
			menuService.createMenu(menu);
		});
	}

	@Test
	void testCreateMenuWithMenuNameNull() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName(null);
			menu.setDescription(
					"SR Catering Service present a detailed dinner menu which is available for all parties, "
					+ "wedding and reception; we also aid customers to create a personalized and special menu just"
					+ " according to the taste and budget");
			menu.setImage("https://iili.io/HWhlwKB.jpg");

			menuService.createMenu(menu);

		});
		String expectedMessage = "MenuName cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testCreateMenuWithMenuNameEmpty() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("");
			menu.setDescription(
					"SR Catering Service present a detailed dinner menu which is available for all parties, "
					+ "wedding and reception; we also aid customers to create a personalized and special menu just "
					+ "according to the taste and budget");
			menu.setImage("https://iili.io/HWhlwKB.jpg");

			menuService.createMenu(menu);

		});
		String expectedMessage = "MenuName cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateMenuWithInvalidMenuName() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("1234ghjnj1");
			menu.setDescription(
					"SR Catering Service present a detailed dinner menu which is available for all parties, "
					+ "wedding and reception; we also aid customers to create a personalized and special menu just "
					+ "according to the taste and budget");
			menu.setImage("https://iili.io/HWhlwKB.jpg");

			menuService.createMenu(menu);

		});
		String expectedMessage = "MenuName should contain only alphabetic characters";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateMenuWithAlreadyExistsMenuName() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Breakfast");
			menu.setDescription(
					"SR Catering Service present a detailed dinner menu which is available for all parties, "
					+ "wedding and reception; we also aid customers to create a personalized and special menu just "
					+ "according to the taste and budget");
			menu.setImage("https://iili.io/HWhlwKB.jpg");

			menuService.createMenu(menu);

		});
		String expectedMessage = "MenuName already exists";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testCreateMenuWithDescriptionNull() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Dinner");
			menu.setDescription(null);
			menu.setImage("https://iili.io/HWhlwKB.jpg");

			menuService.createMenu(menu);

		});
		String expectedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateMenuWithDescriptionEmpty() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Dinner");
			menu.setDescription("");
			menu.setImage("https://iili.io/HWhlwKB.jpg");

			menuService.createMenu(menu);

		});
		String expectedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateMenuWithImageNull() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Dinner");
			menu.setDescription("SR Catering Service present a detailed dinner menu which is available for all parties, "
					+ "wedding and reception; we also aid customers to create a personalized and special menu just according "
					+ "to the taste and budget");
			menu.setImage(null);

			menuService.createMenu(menu);

		});
		String expectedMessage = "Image cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateMenuWithImageEmpty() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Dinner");
			menu.setDescription("SR Catering Service present a detailed dinner menu which is available for all parties, "
					+ "wedding and reception; we also aid customers to create a personalized and special menu just according "
					+ "to the taste and budget");
			menu.setImage("");

			menuService.createMenu(menu);

		});
		String expectedMessage = "Image cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateMenuWithInvalidImage() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Dinner");
			menu.setDescription("SR Catering Service present a detailed dinner menu which is available for all parties, "
					+ "wedding and reception; we also aid customers to create a personalized and special menu just according "
					+ "to the taste and budget");
			menu.setImage("sflkj2349");

			menuService.createMenu(menu);

		});
		String expectedMessage = "Image should be URL.eg:http, https";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	// update
	@Test
	void testUpdateMenuWithValidInput() {
		MenuService menuService = new MenuService();
		
		Menu menu = new Menu();
		menu.setMenuName("Breakfast");
		menu.setDescription(
				"SR Catering Service offers best breakfast menu that is available for all parties and weddings; "
				+ "we also assist customers to create a personalized menu just according to client’s taste and perfect "
				+ "for the occasion.");
		menu.setImage("https://iili.io/HWhcQZx.jpg");
		menu.setId(1);

		assertDoesNotThrow(() -> {
			menuService.updateMenu(menu);
		});
	}
	
	
	@Test
	void testUpdateMenuWithDescriptionNull() {
		MenuService menuService = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Breakfast");
			menu.setDescription(null);
			menu.setImage("https://iili.io/HWhcQZx.jpg");
			menu.setId(1);

			menuService.updateMenu(menu);

		});
		String expectedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateMenuWithDescriptionEmpty() {
		MenuService menuService = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Breakfast");
			menu.setDescription("");
			menu.setImage("https://iili.io/HWhcQZx.jpg");
			menu.setId(1);

			menuService.updateMenu(menu);

		});
		String expectedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateMenuWithImageNull() {
		MenuService menuService = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Breakfast");
			menu.setDescription(
					"SR Catering Service offers best breakfast menu that is available for all parties and weddings; "
					+ "we also assist customers to create a personalized menu just according to client’s taste and perfect "
					+ "for the occasion.");
			menu.setImage(null);
			menu.setId(1);

			menuService.updateMenu(menu);

		});
		String expectedMessage = "Image cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateMenuWithImageEmpty() {
		MenuService menuService = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Breakfast");
			menu.setDescription(
					"SR Catering Service offers best breakfast menu that is available for all parties and weddings; "
					+ "we also assist customers to create a personalized menu just according to client’s taste and perfect "
					+ "for the occasion.");
			menu.setImage("");
			menu.setId(1);

			menuService.updateMenu(menu);

		});
		String expectedMessage = "Image cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateMenuWithMenuIdZero() {
		MenuService menuService = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Breakfast");
			menu.setDescription(
					"SR Catering Service offers best breakfast menu that is available for all parties and weddings; "
					+ "we also assist customers to create a personalized menu just according to client’s taste and perfect "
					+ "for the occasion.");
			menu.setImage("https://iili.io/HWhcQZx.jpg");
			menu.setId(0);

			menuService.updateMenu(menu);

		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateMenuWithNegativeMenuId() {
		MenuService menuService = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Breakfast");
			menu.setDescription(
					"SR Catering Service offers best breakfast menu that is available for all parties and weddings; "
					+ "we also assist customers to create a personalized menu just according to client’s taste and perfect "
					+ "for the occasion.");
			menu.setImage("https://iili.io/HWhcQZx.jpg");
			menu.setId(-1);

			menuService.updateMenu(menu);

		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testUpdateMenuWithInvalidMenuId() {
		MenuService menuService = new MenuService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {

			Menu menu = new Menu();
			menu.setMenuName("Breakfast");
			menu.setDescription(
					"SR Catering Service offers best breakfast menu that is available for all parties and weddings; "
					+ "we also assist customers to create a personalized menu just according to client’s taste and perfect "
					+ "for the occasion.");
			menu.setImage("https://iili.io/HWhcQZx.jpg");
			menu.setId(20);

			menuService.updateMenu(menu);

		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	

	@Test
	void testFindMenuWithValidMenuId() {
		MenuService menuService = new MenuService();

		assertDoesNotThrow(() -> {
			menuService.findByMenuId(1);
		});
	}

	@Test
	void testFindMenuWithMenuIdZero() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			menuService.findByMenuId(0);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testFindMenuWithNegativeMenuId() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			menuService.findByMenuId(-1);
		});
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testFindMenuWithInvalidMenuId() {
		MenuService menuService = new MenuService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			menuService.findByMenuId(20);
		});
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
    private String generateRandomMenuName() {
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
