package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.UserDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.StringUtil;

public class UserValidator {

	/**
     * Validates the provided User object.
     *
     * @param newUser The User object to validate.
     * @throws ValidationException If the User object or its attributes are invalid.
     */
	public static void validate(User newUser) throws ValidationException {

		if (newUser == null) {
			throw new ValidationException("Invalid user Input");
		}

		StringUtil.rejectIfInvalidString(newUser.getName(), "Name");
		StringUtil.rejectIfInvalidString(newUser.getEmail(), "Email");
		StringUtil.rejectIfInvalidString(newUser.getPassword(), "Password");
		
		StringUtil.rejectIfInvalidName(newUser.getName(), "UserName");
		IntUtil.rejectIfInvalidPhoneNumber(newUser.getPhoneNumber());
		StringUtil.rejectIfInvalidEmail(newUser.getEmail());
		StringUtil.rejectIfIvalidPassword(newUser.getPassword());
	}
	
	/**
     * Validates if the provided email already exists in the system.
     *
     * @param email The email to validate.
     * @throws ValidationException If the email already exists.
     */
	public static void isEmailAlreadyExists(String email) throws ValidationException {
		
		try {
			
			StringUtil.rejectIfInvalidString(email, "Email");
			StringUtil.rejectIfInvalidEmail(email);
			
			UserDAO userDAO = new UserDAO();
			userDAO.isEmailAlreadyExists(email);
			
		} catch (DAOException e) {
			throw new ValidationException("Email already exists");
		}
		
	}

	/**
     * Validates if the provided user ID is valid and exists in the system.
     *
     * @param id The user ID to validate.
     * @throws ValidationException If the user ID is invalid or not found.
     */
	public static void isUserIdIsValid(int id) throws ValidationException {
		
		try {
			IntUtil.rejectIfInvalidInt(id, "UserId");
			UserDAO userDAO = new UserDAO();
			userDAO.isIdAlreadyExists(id);
			
		} catch (DAOException e) {
			throw new ValidationException("Invalid UserId");
		}
		
	}
}
