package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    // Hash the password using SHA-256
    public static String hashPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256 hashing
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // Add password bytes to digest
            byte[] hash = md.digest(password.getBytes());
            
            // Convert the byte array into a hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            
            // Return the hashed password
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;  // In case of error, return null
        }
    }
}
