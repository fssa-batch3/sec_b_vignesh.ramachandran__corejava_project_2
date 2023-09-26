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
	 * 
	 * @param addressBook
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param addressBook
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param addressId
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param addressId
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param addressId
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param addressId
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param addressId
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param addressId
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param addressId
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param userId
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws  
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
