package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.UserDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.StringUtil;

public class UserValidator {

	public static void validate(User newUser) throws ValidationException {

		if (newUser == null) {
			throw new ValidationException("Invalid user Input");
		}

		StringUtil.rejectIfInvalidString(newUser.getName(), "Name");
		StringUtil.rejectIfInvalidString(newUser.getEmail(), "Email");
		StringUtil.rejectIfInvalidString(newUser.getPassword(), "Password");
		IntUtil.rejectIfInvalidPhoneNumber(newUser.getPhone_number());
		StringUtil.rejectIfInvalidEmail(newUser.getEmail());
		StringUtil.rejectIfIvalidPassword(newUser.getPassword());
	}
	
	public static void isEmailAlreadyExists(String email) throws ValidationException {
		
		try {
			
			StringUtil.rejectIfInvalidString(email, "Email");
			StringUtil.rejectIfInvalidEmail(email);
			
			UserDAO userdao = new UserDAO();
			userdao.isEmailAlreadyExists(email);
			
		} catch (DAOException e) {
			throw new ValidationException("Email already exists");
		}
		
	}

	public static void isUserIdIsValid(int id) throws ValidationException {
		
		try {
			IntUtil.rejectIfInvalidInt(id, "UserId");
			UserDAO userdao = new UserDAO();
			userdao.isIdAlreadyExists(id);
			
		} catch (DAOException e) {
			throw new ValidationException("Invalid UserId");
		}
		
	}
	
	

}
