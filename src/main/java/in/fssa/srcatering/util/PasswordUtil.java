package in.fssa.srcatering.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import in.fssa.srcatering.exception.DAOException;

public class PasswordUtil {
	
    public static String getSecurePassword(String password, byte[] salt) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            Logger.error(e);
        }
        return generatedPassword; 
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    
    
    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
    	
    	byte[] salt = getSalt();
    	String newPass = getSecurePassword(password, salt);
    	
    	String genPass = "$"+Base64.getEncoder().encodeToString(salt)+"$"+newPass;
		return genPass;
    }
    
    
    public static String checkPass(String password, String salt) {
    	
    	byte[] saltt = Base64.getDecoder().decode(salt);
    	String newPass = getSecurePassword(password, saltt);
    	return newPass;
    }

}
