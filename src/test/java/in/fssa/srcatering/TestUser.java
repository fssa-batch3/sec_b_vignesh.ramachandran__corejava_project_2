package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.service.UserService;

public class TestUser {

	@Test
	public void testCreateUserWithValidInput() {
		UserService userservice = new UserService();

		// user object
		User user = new User();
		user.setName("Varun");
		user.setEmail("varun@gmail.com");
		user.setPassword("Var@1234");
		user.setPhone_number(9876545678L);

		assertDoesNotThrow(() -> {
			userservice.create(user);
		});
	}

	@Test
	public void testCreateUserWithInvalidInput() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userservice.create(null);
		});
		String expectedMessage = "Invalid user Input";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithExistingEmail() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhone_number(9876545678L);
			newUser.setPassword("Vig@1234");

			userservice.create(newUser);
		});
		String expectedMessage = "Email already exists";
		String actualMessage = exception.getMessage();
		
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithEmailNull() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail(null);
			newUser.setPhone_number(9876545678L);
			newUser.setPassword("Vig@1234");
			
			userservice.create(newUser);

		});

		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();
		exception.printStackTrace();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithEmailEmpty() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("");
			newUser.setPhone_number(9876545678L);
			newUser.setPassword("Vig@1234");

			userservice.create(newUser);

		});

		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	// password test case
	@Test
	public void testCreateUserWithPasswordNull() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhone_number(9876545678L);
			newUser.setPassword(null);

			userservice.create(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithPasswordEmpty() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhone_number(9876545678L);
			newUser.setPassword("");

			userservice.create(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithInvalidPhoneNumber() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhone_number(1237649873L);
			newUser.setPassword("Vig@1234");

			userservice.create(newUser);
		});

		String expectedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	// name test case
	@Test
	public void testCreateUserWithNameNull() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName(null);
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhone_number(9876545678L);
			newUser.setPassword("Vig@1234");

			userservice.create(newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithNameEmpty() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhone_number(9876545678L);
			newUser.setPassword("Vig@1234");

			userservice.create(newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateUserWithValidInputAndId() {
		UserService userservice = new UserService();

		// user object
		User user = new User();
		user.setName("Vignesh");
		user.setEmail("vignesh@gmail.com");
		user.setPassword("Var@1234");
		user.setPhone_number(9876545678L);

		assertDoesNotThrow(() -> {
			userservice.update(1, user);
		});
	}

	@Test
	public void testUpdateUserWithIdZero() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhone_number(9876545678L);
			newUser.setPassword("Vig@1234");

			userservice.update(0, newUser);
		});

		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateUserWithInvalidId() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhone_number(9876545678L);
			newUser.setPassword("Vig@1234");

			userservice.update(6, newUser);
		});

		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testDeleteUserWithValidId() {
		UserService userservice = new UserService();

		assertDoesNotThrow(() -> {
			userservice.delete(3);
		});
	}

	@Test
	public void testDeleteUserWithIdZero() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userservice.delete(0);
		});
		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testDeleteUserWithInvalidId() {
		UserService userservice = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userservice.delete(6);
		});
		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
