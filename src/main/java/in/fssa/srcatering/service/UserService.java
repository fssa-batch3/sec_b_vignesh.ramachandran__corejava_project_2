package in.fssa.srcatering.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.srcatering.dao.UserDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.StringUtil;
import in.fssa.srcatering.validator.UserValidator;

public class UserService {

	UserDAO userdao = new UserDAO();

	/**
     * Retrieves a list of all users.
     *
     * @return A list of all users.
     * @throws ServiceException If there's an issue with the service operation.
     */
	public List<User> getAll() throws ServiceException {

		List<User> userList;
		try {
			userList = userdao.findAll();
			Iterator<User> iterator = userList.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}

			return userdao.findAll();
		} catch (DAOException e) {
			e.printStackTrace();
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
	public void create(User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validate(newUser);
			UserValidator.isEmailAlreadyExists(newUser.getEmail());
			userdao.create(newUser);
		} catch (DAOException e) {
			if(e.getMessage().contains("Email already exists")) {
				e.printStackTrace();
				throw new ServiceException("Failed to Create User EmailId already exists");
			}else {
				e.printStackTrace();
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
	public void update(int id, User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validate(newUser);
			UserValidator.isUserIdIsValid(id);

			userdao.update(id, newUser);

		} catch (DAOException e) {
			e.printStackTrace();
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
	public void delete(int id) throws ValidationException, ServiceException {

		try {
			UserValidator.isUserIdIsValid(id);

			userdao.delete(id);

		} catch (DAOException e) {
			e.printStackTrace();
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
	public User findById(int id) throws ValidationException, ServiceException {

		try {
			UserValidator.isUserIdIsValid(id);

			return userdao.findById(id);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}
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

		try {

			StringUtil.rejectIfInvalidString(email, "Email");

			return userdao.findByEmail(email);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}
	}
	
	 /**
     * Changes the status of a user by ID.
     *
     * @param id The ID of the user whose status to change.
     * @throws ValidationException If the provided user ID is not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void changeStatus(int id) throws ValidationException, ServiceException {
		
		
		try {
			IntUtil.rejectIfInvalidInt(id, "UserId");
			userdao.changeStatus(id);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
			
		}
	}

}
