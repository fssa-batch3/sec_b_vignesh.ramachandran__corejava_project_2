package in.fssa.srcatering.model;

public class AddressBook extends AddressBookEntity {
	
	public AddressBook() {
		
	}

	public AddressBook(int id, int userId, String name, String email, String phoneNumber, String doorNo, String streetName, String subLocality, String city, String district, String state, int pincode, boolean status, boolean setAsDefault, boolean selected) {
		super.setId(id);
		super.setUserId(userId);
		super.setName(name);
		super.setEmail(email);
		super.setPhoneNumber(phoneNumber);
		super.setDoorNo(doorNo);
		super.setStreetName(streetName);
		super.setSubLocality(subLocality);
		super.setCity(city);
		super.setDistrict(district);
		super.setState(state);
		super.setPincode(pincode);
		super.setStatus(status);
		super.setSetAsdefault(setAsDefault);
		super.setSelected(selected);
	}

}
