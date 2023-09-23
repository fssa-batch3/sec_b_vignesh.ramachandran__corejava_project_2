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
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidDishName(String input, String inputName) throws ValidationException {
		final String NAME_PATTERN = "^[a-zA-Z -]+$";
		
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

		final String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8}$";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object
		Matcher matcher = pattern.matcher(password);

		if (!matcher.matches()) {

			throw new ValidationException("Password atleast contain one Uppercase, one Lowercase,one Special character,"
					+ "one number, must contains 8 characters. Eg: Abc@1234");
		}
	}
	
	/**
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static boolean validatePhoneNumber(String phoneNumber) {
        // Define a regular expression pattern for a 10-digit phone number starting with 6, 7, 8, or 9
        String pattern = "^[6-9]\\d{9}$";

        // Create a Pattern object
        Pattern regexPattern = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regexPattern.matcher(phoneNumber);

        // Use the Matcher.matches() method to check if the input matches the pattern
        return matcher.matches();
    }
	
	
	

}
