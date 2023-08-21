package in.fssa.srcatering.util;

import in.fssa.srcatering.exception.ValidationException;

public class IntUtil {

	/**
     * Validates and rejects an invalid phone number.
     *
     * @param phoneNumber The phone number to validate.
     * @throws ValidationException If the phone number is not within a valid range.
     */
	public static void rejectIfInvalidPhoneNumber(long phoneNumber) throws ValidationException {
		
	    String phoneNumberStr = String.valueOf(phoneNumber);

	    if (phoneNumberStr.length() != 10) {
	        throw new ValidationException("PhoneNumber doesn't match the length");
	    }

		if (phoneNumber < 6000000000L || phoneNumber > 9999999999L) {
			throw new ValidationException("Invalid phone number");
		}

	}
	
	/**
     * Validates and rejects an invalid integer input.
     *
     * @param input     The integer input to validate.
     * @param inputName The name of the input field for error messaging.
     * @throws ValidationException If the integer input is invalid (e.g., non-positive).
     */
	public static void rejectIfInvalidInt(int input, String inputName) throws ValidationException {
		if(input <=0) {
			throw new ValidationException("Invalid ".concat(inputName));
		}
	}

	/**
     * Checks the validity of a price value.
     *
     * @param input     The price input to check.
     * @param inputName The name of the input field for error messaging.
     * @throws ValidationException If the price value is negative.
     */
	public static void priceCheck(int input, String inputName) throws ValidationException {
		if(input < 0) {
			throw new ValidationException("Invalid ".concat(inputName));
		}
	}
	
	/**
     * Checks the validity of a quantity value.
     *
     * @param input     The quantity input to check.
     * @param inputName The name of the input field for error messaging.
     * @throws ValidationException If the quantity value exceeds the limit.
     */
	public void QuantityCheck(int input, String inputName) throws ValidationException {
		if(input > 500) {
			throw new ValidationException("Quantity doesn't above 500");
		}
	}
}
