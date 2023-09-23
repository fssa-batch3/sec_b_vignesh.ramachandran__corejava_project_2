package in.fssa.srcatering.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import in.fssa.srcatering.exception.DAOException;

public class PasswordUtil {
	
	private static final String KEY = "SR_is_a_brand";


	public static String encodePassword(String password) {
		try {
			// Convert the password and key to byte arrays
			byte[] passwordBytes = password.getBytes("UTF-8");
			byte[] keyBytes = KEY.getBytes("UTF-8");

			// Perform XOR operation
			for (int i = 0; i < passwordBytes.length; i++) {
				passwordBytes[i] ^= keyBytes[i % keyBytes.length];
			}

			// Encode the result to Base64
			byte[] encodedBytes = Base64.getEncoder().encode(passwordBytes);
			return new String(encodedBytes, "UTF-8");
		} catch (Exception e) {
			Logger.error(e);
			return null;
		}
	}

	public static String decodePassword(String encodedPassword) {
		
		try {
			// Decode the Base64-encoded password to byte array
			byte[] encodedBytes = encodedPassword.getBytes("UTF-8");
			byte[] passwordBytes = Base64.getDecoder().decode(encodedBytes);
			byte[] keyBytes = KEY.getBytes("UTF-8");

			// Perform XOR operation to decode
			for (int i = 0; i < passwordBytes.length; i++) {
				passwordBytes[i] ^= keyBytes[i % keyBytes.length];
			}

			// Convert byte array back to string
			return new String(passwordBytes, "UTF-8");
		} catch (Exception e) {
			Logger.error(e);
			return null;
		}
		
	}
	
	/**
	 * Hashes the provided password using the SHA-256 cryptographic hash function.
	 *
	 * @param password the raw password to be hashed.
	 * @return the hashed password as a hexadecimal string.
	 * @throws InvalidEmployeeException if an error occurs during hashing (e.g.,
	 *                                  NoSuchAlgorithmException).
	 */
	public static String hashPassword(String password) throws DAOException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

			// Convert the byte array to a hexadecimal string
			StringBuilder sb = new StringBuilder();
			
			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new DAOException(e.getMessage());
		}
	}

}
