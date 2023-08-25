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
		UserService userService = new UserService();

		// user object
		User user = new User();
		user.setName("Varun");
		user.setEmail(generateRandomEmail());
		user.setPassword("Var@1234");
		user.setPhoneNumber(9876545678L);

		assertDoesNotThrow(() -> {
			userService.createUser(user);
		});
	}

	@Test
	public void testCreateUserWithInvalidInput() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(null);
		});
		String expectedMessage = "Invalid user Input";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithExistingEmail() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("Vig@1234");

			userService.createUser(newUser);
		});
		String expectedMessage = "Email already exists";
		String actualMessage = exception.getMessage();
		
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithEmailNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail(null);
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("Vig@1234");
			
			userService.createUser(newUser);

		});

		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();
		
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithEmailEmpty() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("Vig@1234");

			userService.createUser(newUser);

		});

		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}

	// password test case
	@Test
	public void testCreateUserWithPasswordNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword(null);

			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithPasswordEmpty() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("");

			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testCreateUserWithWrongPasswordPattern() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("fghjk34567");

			userService.createUser(newUser);
		});
		String expectedMessage = "Password doesn't matches with pattern. Password atleast contain one Uppercase,"
				+ "one Lowercase,one Special character,one number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	

	@Test
	public void testCreateUserWithInvalidPhoneNumber() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(1237649873L);
			newUser.setPassword("Vig@1234");

			userService.createUser(newUser);
		});

		String expectedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	// name test case
	@Test
	public void testCreateUserWithNameNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName(null);
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("Vig@1234");

			userService.createUser(newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithNameEmpty() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("Vig@1234");

			userService.createUser(newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testCreateUserWithInvalidName() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("12345sdfg");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("Vig@1234");

			userService.createUser(newUser);
		});
		String expectedMessage = "UserName should contain only alphabetic characters";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	// update
	@Test
	public void testUpdateUserWithValidInputAndId() {
		UserService userService = new UserService();

		// user object
		User user = new User();
		user.setName("Vignesh");
		user.setEmail("vignesh@gmail.com");
		user.setPassword("Var@1234");
		user.setPhoneNumber(9876545678L);

		assertDoesNotThrow(() -> {
			userService.updateUser(1, user);
		});
	}
	
	@Test
	public void testUpdateUserWithNameNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName(null);
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("Vig@1234");

			userService.updateUser(1, newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateUserWithNameEmpty() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("Vig@1234");

			userService.updateUser(1, newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateUserWithInvalidName() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("12sdf123");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("Vig@1234");

			userService.updateUser(1, newUser);
		});
		String expectedMessage = "UserName should contain only alphabetic characters";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateUserWithPasswordNull() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword(null);

			userService.updateUser(1,newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateUserWithPasswordEmpty() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("");

			userService.updateUser(1,newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void testUpdateUserWithWrongPasswordPattern() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			// user 1
			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("kljdbfka12");

			userService.updateUser(1,newUser);
		});
		String expectedMessage = "Password doesn't matches with pattern. Password atleast contain one Uppercase,"
				+ "one Lowercase,one Special character,one number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	

	@Test
	public void testUpdateUserWithIdZero() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("Vig@1234");

			userService.updateUser(0, newUser);
		});

		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}

	@Test
	public void testUpdateUserWithInvalidId() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			User newUser = new User();

			newUser.setName("Vignesh");
			newUser.setEmail("vignesh@gmail.com");
			newUser.setPhoneNumber(9876545678L);
			newUser.setPassword("Vig@1234");

			userService.updateUser(100, newUser);
		});

		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testDeleteUserWithValidId() {
		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
			userService.deleteUser(3);
		});
		
		try {
			userService.changeUserStatus(3);
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteUserWithIdZero() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.deleteUser(0);
		});
		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testDeleteUserWithInvalidId() {
		UserService userService = new UserService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.deleteUser(100);
		});
		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();
		
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	
    private String generateRandomEmail() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder email = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = (int) (Math.random() * alphabet.length());
            char randomChar = alphabet.charAt(index);
            email.append(randomChar);
        }

        email.append("@gmail.com");
        return email.toString();
    }

}
