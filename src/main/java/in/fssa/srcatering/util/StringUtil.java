package in.fssa.srcatering.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.srcatering.exception.ValidationException;

public class StringUtil {

	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
	}

	/**
	 * 
	 * @param email
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidEmail(String email) throws ValidationException {

		String regexPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object
		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches()) {
			throw new ValidationException("Invalid email");
		}
	}

	/**
	 * 
	 * @param password
	 * @throws ValidationException
	 */
	public static void rejectIfIvalidPassword(String password) throws ValidationException {

		String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object
		Matcher matcher = pattern.matcher(password);

		if (!matcher.matches()) {

			throw new ValidationException("Password doesn't matches with pattern");
		}
	}

	/**
	 * 
	 * @param newString
	 * @return
	 */
	public static boolean isValidString(String newString) {

		if (newString == null || "".equals(newString.trim())) {

			return false;
		}
		return true;

	}

	/**
	 * 
	 * @param newString
	 * @return
	 */
	public static boolean isInvalidString(String newString) {

		if (!isValidString(newString)) {

			return true;
		}
		return false;

	}

}
