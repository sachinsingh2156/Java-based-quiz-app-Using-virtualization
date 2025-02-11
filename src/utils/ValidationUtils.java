package utils;

import db.PlayerDAO;

public class ValidationUtils {

    // Check if the username already exists in the database
    public static boolean isUsernameAvailable(String username) {
        PlayerDAO playerDAO = new PlayerDAO();
        return playerDAO.isUsernameAvailable(username);  // Assume this method checks if the username exists
    }

    // Validate the password length (e.g., minimum 6 characters)
    public static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    // Validate the email format
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
