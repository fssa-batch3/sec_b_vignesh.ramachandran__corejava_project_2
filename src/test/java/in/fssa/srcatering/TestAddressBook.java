package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.AddressBook;
import in.fssa.srcatering.service.AddressBookService;

public class TestAddressBook {
	
	@Test
	void testCreateAddressWithInvalidInput() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {

			addressBookService.createAddress(null);
		});
		String expectedMessage = "Invalid Address Input";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	
	@Test
	void testCreateAddressWithUserIdZero() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(0);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithNegativeUserId() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(-5);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "UserId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithInvalidUserId() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1000);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "Invalid UserId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithNameNull() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName(null);
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithNameEmpty() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithEmailNull() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail(null);
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithEmailEmpty() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	
	@Test
	void testCreateAddressWithPhoneNumberNull() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber(null);
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "PhoneNumber cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithPhoneNumberEmpty() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "PhoneNumber cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateAddressWithDoorNoNull() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo(null);
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "DoorNo cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithDoorNoEmpty() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "DoorNo cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithStreetNameNull() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName(null);
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "StreetName cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithStreetNameEmpty() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "StreetName cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithSubLocalityNull() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality(null);
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "SubLocality cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithSubLocalityEmpty() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "SubLocality cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithCityNull() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity(null);
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "City cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithCityEmpty() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "City cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithDistrictNull() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict(null);
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "District cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithDistrictEmpty() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("");
			addressBook.setState("TN");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "District cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithStateNull() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState(null);
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "State cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithStateEmpty() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("");
			addressBook.setPincode(612345);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "State cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithPincodeZero() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(0);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "Pincode cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithPincodeLessThan600001() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(600000);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "Invalid Pincode. Enter Tamilnadu pincode only";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateAddressWithPincodeGreaterThan643253() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			AddressBook addressBook = new AddressBook();
			addressBook.setUserId(1);
			addressBook.setName("Vignesh");
			addressBook.setEmail("abc@gmail.com");
			addressBook.setPhoneNumber("9876543210");
			addressBook.setDoorNo("123");
			addressBook.setStreetName("New Street");
			addressBook.setSubLocality("Kumbakonam");
			addressBook.setCity("Kumbakonam");
			addressBook.setDistrict("Thanjavur");
			addressBook.setState("TN");
			addressBook.setPincode(643254);
			
			addressBookService.createAddress(addressBook);
		});
		String expectedMessage = "Invalid Pincode. Enter Tamilnadu pincode only";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testSetStatusTrueByAddressIdZero() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			addressBookService.setStatusTrue(0);
		});
		String expectedMessage = "AddressId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testSetStatusTrueByAddressIdNegative() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			addressBookService.setStatusTrue(-2);
		});
		String expectedMessage = "AddressId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testSetStatusTrueByInvalidAddressId() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			addressBookService.setStatusTrue(1000);
		});
		String expectedMessage = "Invalid AddressId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testSetStatusFalseByAddressIdZero() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			addressBookService.setStatusFalse(0);
		});
		String expectedMessage = "AddressId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testSetAsDefaultTrueByAddressIdZero() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			addressBookService.setAsDefaultTrue(0);
		});
		String expectedMessage = "AddressId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testSetAsDefaultFalseByAddressIdZero() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			addressBookService.setAsDefaultFalse(0);
		});
		String expectedMessage = "AddressId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testSetSelectedTrueByAddressIdZero() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			addressBookService.setSelectedTrue(0);
		});
		String expectedMessage = "AddressId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testSetSelectedFalseByAddressIdZero() {
		AddressBookService addressBookService = new AddressBookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			
			addressBookService.setSelectedFalse(0);
		});
		String expectedMessage = "AddressId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
}
