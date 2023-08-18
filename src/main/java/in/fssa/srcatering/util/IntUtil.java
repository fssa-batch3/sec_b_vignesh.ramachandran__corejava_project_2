package in.fssa.srcatering.util;

import in.fssa.srcatering.exception.ValidationException;

public class IntUtil {

	/**
	 * 
	 * @param phoneNumber
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidPhoneNumber(long phoneNumber) throws ValidationException {

		if (phoneNumber < 6000000000L || phoneNumber > 9999999999L) {
			throw new ValidationException("Invalid phone number");
		}

	}
	
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidInt(int input, String inputName) throws ValidationException {
		if(input <=0) {
			throw new ValidationException("Invalid ".concat(inputName));
		}
	}

	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void priceCheck(int input, String inputName) throws ValidationException {
		if(input < 0) {
			throw new ValidationException("Invalid ".concat(inputName));
		}
	}
	
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	public void QuantityCheck(int input, String inputName) throws ValidationException {
		if(input > 500) {
			throw new ValidationException("Quantity doesn't above 500");
		}
	}
}
