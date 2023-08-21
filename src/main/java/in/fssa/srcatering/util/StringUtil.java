package in.fssa.srcatering.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.srcatering.exception.ValidationException;

public class StringUtil {

	/**
     * Validates and rejects an invalid string input.
     *
     * @param input     The string input to validate.
     * @param inputName The name of the input field for error messaging.
     * @throws ValidationException If the string input is null or empty after trimming.
     */
	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
	}
	
	/**
	 * This method checks if the input string follows the specified name pattern.
	 *
	 * @param input The input string to be validated.
	 * @param inputName A description of the input (e.g., "Name" or "Full Name") used in the error message.
	 * @throws ValidationException Thrown if the input string doesn't match the specified pattern.
	 */
	public static void rejectIfInvalidName(String input, String inputName) throws ValidationException {
		final String NAME_PATTERN = "^[a-zA-Z ]+$";
		
		if (!Pattern.matches(NAME_PATTERN, input)) {
			throw new ValidationException(inputName+" should contain only alphabetic characters");
		}
	}

	/**
     * Validates and rejects an invalid email address.
     *
     * @param email The email address to validate.
     * @throws ValidationException If the email address is invalid according to a regex pattern.
     */
	public static void rejectIfInvalidEmail(String email) throws ValidationException {

		final String regexPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object
		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches()) {
			throw new ValidationException("Invalid email");
		}
	}

	/**
     * Validates and rejects an invalid password.
     *
     * @param password The password to validate.
     * @throws ValidationException If the password doesn't match the required pattern.
     */
	public static void rejectIfIvalidPassword(String password) throws ValidationException {

		final String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object
		Matcher matcher = pattern.matcher(password);

		if (!matcher.matches()) {

			throw new ValidationException("Password doesn't matches with pattern. Password atleast contain one Uppercase,"
					+ "one Lowercase,one Special character,one number");
		}
	}

	/**
     * Checks if a string is valid (not null or empty after trimming).
     *
     * @param newString The string to check.
     * @return True if the string is valid, otherwise false.
     */
	public static boolean isValidString(String newString) {

		if (newString == null || "".equals(newString.trim())) {

			return false;
		}
		return true;

	}

	/**
     * Checks if a string is invalid (null or empty after trimming).
     *
     * @param newString The string to check.
     * @return True if the string is invalid, otherwise false.
     */
	public static boolean isInvalidString(String newString) {

		if (!isValidString(newString)) {

			return true;
		}
		return false;
	}

}
