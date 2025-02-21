package Bankers.util;

public class Validation {

    // Method to check if an email is valid
    public static boolean isValidEmail(String email) {
        // A simple regex for validating email format
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Method to validate a password (e.g., must be at least 8 characters)
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }
}
