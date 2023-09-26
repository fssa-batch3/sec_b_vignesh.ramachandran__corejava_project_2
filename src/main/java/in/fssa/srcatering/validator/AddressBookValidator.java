package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.AddressBookDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.AddressBook;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.Logger;
import in.fssa.srcatering.util.StringUtil;

public class AddressBookValidator {

	/**
	 * 
	 * @param addressBook
	 * @throws ValidationException
	 */
	public static void validateAddress(AddressBook addressBook) throws ValidationException {
		
		if(addressBook == null) {
			throw new ValidationException("Invalid Address Input");
		}

		// null or empty validation
		IntUtil.rejectIfInvalidInt(addressBook.getUserId(), "UserId");
		StringUtil.rejectIfInvalidString(addressBook.getName(), "Name");
		StringUtil.rejectIfInvalidString(addressBook.getEmail(), "Email");
		StringUtil.rejectIfInvalidString(addressBook.getPhoneNumber(), "PhoneNumber");
		StringUtil.rejectIfInvalidString(addressBook.getDoorNo(), "DoorNo");
		StringUtil.rejectIfInvalidString(addressBook.getStreetName(), "StreetName");
		StringUtil.rejectIfInvalidString(addressBook.getSubLocality(), "SubLocality");
		StringUtil.rejectIfInvalidString(addressBook.getCity(), "City");
		StringUtil.rejectIfInvalidString(addressBook.getDistrict(), "District");
		StringUtil.rejectIfInvalidString(addressBook.getState(), "State");
		IntUtil.rejectIfInvalidInt(addressBook.getPincode(), "Pincode");

		// pattern validation
		StringUtil.rejectIfInvalidName(addressBook.getName(), "Name"); 
		StringUtil.rejectIfInvalidEmail(addressBook.getEmail());

		if (StringUtil.validatePhoneNumber(addressBook.getPhoneNumber()) == false) {
			throw new ValidationException("Invalid PhoneNumber");
		}

		StringUtil.rejectIfInvalidName(addressBook.getCity(), "City");
		StringUtil.rejectIfInvalidName(addressBook.getDistrict(), "District");
		StringUtil.rejectIfInvalidName(addressBook.getState(), "State");

		if (addressBook.getPincode() < 600001 || addressBook.getPincode() > 643253) {
			throw new ValidationException("Invalid Pincode. Enter Tamilnadu pincode only");
		}

		// business validation
		UserValidator.isUserIdIsValid(addressBook.getUserId());
	}

	/**
	 * 
	 * @param addressBook
	 * @return
	 * @throws ValidationException
	 */
	public static AddressBook isAddressAlreadyExists(AddressBook addressBook) throws ValidationException {

		AddressBookDAO addressBookDAO = new AddressBookDAO();
		AddressBook addressBook1 = null;
		try {

			addressBook1 = addressBookDAO.isAddressAlreadyExists(addressBook);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ValidationException(e.getMessage());
		}
		return addressBook1;

	}

	/**
	 * 
	 * @param addressId
	 * @throws ValidationException
	 */
	public static void isAddressIdIsValid(int addressId) throws ValidationException {

		try {
			IntUtil.rejectIfInvalidInt(addressId, "AddressId");
			AddressBookDAO addressBookDAO = new AddressBookDAO();
			addressBookDAO.isAdressIdIsValid(addressId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ValidationException(e.getMessage());
		}
	}

}
