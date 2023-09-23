package in.fssa.srcatering.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.srcatering.dao.UserDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.Logger;
import in.fssa.srcatering.util.PasswordUtil;
import in.fssa.srcatering.util.StringUtil;
import in.fssa.srcatering.validator.UserValidator;

public class UserService {

	UserDAO userDAO = new UserDAO();

	/**
	 * Retrieves a list of all users.
	 *
	 * @return A list of all users.
	 * @throws ServiceException If there's an issue with the service operation.
	 */
	public List<User> getAllUsers() throws ServiceException {

		List<User> userList;
		try {
			userList = userDAO.findAll();
			Iterator<User> iterator = userList.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}

			return userDAO.findAll();
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Failed to Getall User");
		}

	}

	/**
	 * Creates a new user.
	 *
	 * @param newUser The user object to create.
	 * @throws ValidationException If the provided user data is not valid.
	 * @throws ServiceException    If there's an issue with the service operation.
	 */
	public void createUser(User newUser) throws ValidationException, ServiceException {

		try {
			
			UserValidator.validate(newUser);
			UserValidator.isEmailAlreadyExists(newUser.getEmail());
			
			newUser.setPassword(PasswordUtil.encodePassword(newUser.getPassword()));
			userDAO.create(newUser);
			
		} catch (DAOException e) {
			if (e.getMessage().contains("Email already exists")) {

				throw new ServiceException("Failed to Create User EmailId already exists");
			} else {

				throw new ServiceException("Failed to Create User");
			}
		}

	}

	/**
	 * Updates an existing user.
	 *
	 * @param id      The ID of the user to update.
	 * @param newUser The updated user object.
	 * @throws ValidationException If the provided user data or ID is not valid.
	 * @throws ServiceException    If there's an issue with the service operation.
	 */
	public void updateUser(int id, User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validate(newUser);
			UserValidator.isUserIdIsValid(id);
			
			newUser.setPassword(PasswordUtil.encodePassword(newUser.getPassword()));
			userDAO.update(id, newUser);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Failed to Update User");
		}

	}

	/**
	 * Deletes a user by ID.
	 *
	 * @param id The ID of the user to delete.
	 * @throws ValidationException If the provided user ID is not valid.
	 * @throws ServiceException    If there's an issue with the service operation.
	 */
	public void deleteUser(int id) throws ValidationException, ServiceException {

		try {
			UserValidator.isUserIdIsValid(id);

			userDAO.delete(id);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Failed to Delete User");
		}
	}

	/**
	 * Finds a user by ID.
	 *
	 * @param id The ID of the user to find.
	 * @return The user object.
	 * @throws ValidationException If the provided user ID is not valid.
	 * @throws ServiceException    If there's an issue with the service operation.
	 */
	public User findByUserId(int id) throws ValidationException, ServiceException {

		User user = null;
		try {

			UserValidator.isUserIdIsValid(id);

			user = userDAO.findById(id);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Failed to findById");
		}
		System.out.println(user);
		return user;
	}

	/**
	 * Finds a user by email.
	 *
	 * @param email The email of the user to find.
	 * @return The user object.
	 * @throws ValidationException If the provided email is not valid.
	 * @throws ServiceException    If there's an issue with the service operation.
	 */
	public User findByEmail(String email) throws ValidationException, ServiceException {

		User user = null;
		try {
			StringUtil.rejectIfInvalidString(email, "Email");

			user = userDAO.findByEmail(email);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Failed to findById");
		}
		System.out.println(user);
		return user;
	}

	/**
	 * 
	 * @param email
	 * @param password
	 * @throws ValidationException
	 * @throws ServiceException 
	 */
	public void loginUser(String email, String password) throws ValidationException, ServiceException {
		try {
			StringUtil.rejectIfInvalidString(email, "Email");
			StringUtil.rejectIfInvalidString(password, "Password");
			StringUtil.rejectIfInvalidEmail(email);
			StringUtil.rejectIfIvalidPassword(password);
			
			userDAO.findByEmail(email);
			
			password = PasswordUtil.encodePassword(password);
			userDAO.passwordChecker(email, password);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}
	

	/**
	 * Changes the status of a user by ID.
	 *
	 * @param id The ID of the user whose status to change 0 to 1.
	 * @throws ValidationException If the provided user ID is not valid.
	 * @throws ServiceException    If there's an issue with the service operation.
	 */
	public void changeUserStatus(int id) throws ValidationException, ServiceException {

		try {
			IntUtil.rejectIfInvalidInt(id, "UserId");
			userDAO.changeStatus(id);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());

		}
	}

}
