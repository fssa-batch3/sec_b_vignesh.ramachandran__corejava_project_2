package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.dao.AddressBookDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.AddressBook;
import in.fssa.srcatering.util.Logger;
import in.fssa.srcatering.validator.AddressBookValidator;
import in.fssa.srcatering.validator.UserValidator;

public class AddressBookService {
	AddressBookDAO addressBookDAO = new AddressBookDAO();

	/**
	 * Create a new address book entry in the database or update an existing entry if it exists. Checks and handles address existence and status based on the provided address details.
	 *
	 * @param addressBook The address details to be created or updated.
	 * @throws ValidationException If the address details are invalid.
	 * @throws ServiceException If a service error occurs during address creation or update.
	 */
	public void createAddress(AddressBook addressBook) throws ValidationException, ServiceException {

		try {

			AddressBookValidator.validateAddress(addressBook);

			AddressBook addressBook1 = new AddressBook();

			addressBook1 = AddressBookValidator.isAddressAlreadyExists(addressBook);

			if (addressBook1 != null) {
				if (addressBook1.isStatus() == 0) {
					addressBookDAO.setStausTrue(addressBook1.getId());
				} else {
					throw new DAOException("Address already Exists");
				}
			} else {
				addressBookDAO.create(addressBook);
			}

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Update an address book entry in the database. Handles existing address status and 'set as default' functionality based on the provided address details.
	 *
	 * @param addressBook The address details to be updated.
	 * @throws ValidationException If the address details are invalid.
	 * @throws ServiceException If a service error occurs during address update.
	 */
	public void updateAddress(AddressBook addressBook) throws ValidationException, ServiceException {

		try {
			AddressBookValidator.validateAddress(addressBook);

			AddressBook addressBook1 = new AddressBook();

			addressBook1 = AddressBookValidator.isAddressAlreadyExists(addressBook);

			if (addressBook1 != null) {
				if (addressBook1.isStatus() == 0) {
					addressBookDAO.setStausFalse(addressBook.getId());
					addressBookDAO.setStausTrue(addressBook1.getId());
					
					if(addressBook.isSetAsDefault() == 1) {
						addressBookDAO.setAsDefaultTrue(addressBook1.getId());
						addressBookDAO.setAsDefaultFalse(addressBook.getId());
					}
				} else {
					throw new DAOException("Address already Exists");
				}
			} else {
				
				addressBookDAO.create(addressBook);
				addressBookDAO.setStausFalse(addressBook.getId());
				if(addressBook.isSetAsDefault() == 1) {
					addressBookDAO.setAsDefaultFalse(addressBook.getId());
				}
			}
			
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Set the status of an address book entry to true (active) in the database.
	 *
	 * @param addressId The ID of the address to update its status.
	 * @throws ValidationException If the provided address ID is invalid.
	 * @throws ServiceException If a service error occurs during status update.
	 */
	public void setStatusTrue(int addressId) throws ValidationException, ServiceException {

		try {
			AddressBookValidator.isAddressIdIsValid(addressId);
			addressBookDAO.setStausTrue(addressId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Set the status of an address book entry to false (inactive) in the database.
	 *
	 * @param addressId The ID of the address to update its status.
	 * @throws ValidationException If the provided address ID is invalid.
	 * @throws ServiceException If a service error occurs during status update.
	 */
	public void setStatusFalse(int addressId) throws ValidationException, ServiceException {

		try {
			AddressBookValidator.isAddressIdIsValid(addressId);
			addressBookDAO.setStausFalse(addressId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Set an address book entry as the default address in the database.
	 *
	 * @param addressId The ID of the address to set as default.
	 * @throws ValidationException If the provided address ID is invalid.
	 * @throws ServiceException If a service error occurs during the default address update.
	 */
	public void setAsDefaultTrue(int addressId) throws ValidationException, ServiceException {

		try {
			AddressBookValidator.isAddressIdIsValid(addressId);
			addressBookDAO.setAsDefaultTrue(addressId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Unset an address book entry as the default address in the database.
	 *
	 * @param addressId The ID of the address to unset as default.
	 * @throws ValidationException If the provided address ID is invalid.
	 * @throws ServiceException If a service error occurs during the default address update.
	 */
	public void setAsDefaultFalse(int addressId) throws ValidationException, ServiceException {

		try {
			AddressBookValidator.isAddressIdIsValid(addressId);
			addressBookDAO.setAsDefaultFalse(addressId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Set an address book entry as selected (active) in the database.
	 *
	 * @param addressId The ID of the address to set as selected.
	 * @throws ValidationException If the provided address ID is invalid.
	 * @throws ServiceException If a service error occurs during the selected address update.
	 */
	public void setSelectedTrue(int addressId) throws ValidationException, ServiceException {

		try {
			AddressBookValidator.isAddressIdIsValid(addressId);
			addressBookDAO.setSelectedTrue(addressId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Unset an address book entry as selected (inactive) in the database.
	 *
	 * @param addressId The ID of the address to unset as selected.
	 * @throws ValidationException If the provided address ID is invalid.
	 * @throws ServiceException If a service error occurs during the selected address update.
	 */
	public void setSelectedFalse(int addressId) throws ValidationException, ServiceException {

		try {
			AddressBookValidator.isAddressIdIsValid(addressId);
			addressBookDAO.setSelectedFalse(addressId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Get an address book entry by its ID from the database.
	 *
	 * @param addressId The ID of the address to retrieve.
	 * @return The address book entry with the provided ID, or null if not found.
	 * @throws ValidationException If the provided address ID is invalid.
	 * @throws ServiceException If a service error occurs during address retrieval.
	 */
	public AddressBook getAddressByAddressId(int addressId) throws ValidationException, ServiceException {

		AddressBook addressBook = new AddressBook();
		try {
			AddressBookValidator.isAddressIdIsValid(addressId);
			addressBook = addressBookDAO.findAddressByAddressId(addressId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return addressBook;
	}

	/**
	 * Get all address book entries associated with a specific user from the database.
	 *
	 * @param userId The ID of the user to retrieve addresses for.
	 * @return A list of address book entries associated with the user.
	 * @throws ValidationException If the provided user ID is invalid.
	 * @throws ServiceException If a service error occurs during address retrieval.
	 */
	public List<AddressBook> getAllAddressesByUserId(int userId) throws ValidationException, ServiceException {

		List<AddressBook> addressList = new ArrayList<>();
		try {
			UserValidator.isUserIdIsValid(userId);
			addressList = addressBookDAO.findAllAddressesByUserId(userId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return addressList;
	}

	/**
	 * Get the default address associated with a specific user from the database.
	 *
	 * @param userId The ID of the user to retrieve the default address for.
	 * @return The default address associated with the user, or null if not found.
	 * @throws ValidationException If the provided user ID is invalid.
	 * @throws ServiceException If a service error occurs during address retrieval.
	 */
	public AddressBook getDefaultAddressByUserId(int userId) throws ValidationException, ServiceException {

		AddressBook addressBook = null;
		try {
			UserValidator.isUserIdIsValid(userId);
			addressBook = addressBookDAO.findDefaultAddressByUserId(userId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return addressBook;
	}
}
