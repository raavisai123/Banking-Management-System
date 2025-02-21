package banker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerCloseAccount {

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bankerDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "67896789Bhargav@";

    // Method to check balance
    public boolean hasZeroBalance(int accountNumber) throws SQLException {
        String checkBalanceSQL = "SELECT balance FROM customers WHERE accountnumber = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(checkBalanceSQL)) {
            statement.setInt(1, accountNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    double balance = resultSet.getDouble("balance");
                    return balance == 0;
                }
                return false; // Account not found
            }
        }
    }

    // Method to mark account as inactive
    public boolean closeAccount(int accountNumber) throws SQLException {
        String updateSQL = "UPDATE customers SET status = 'inactive' WHERE accountnumber = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            statement.setInt(1, accountNumber);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0; // Return true if update was successful
        }
    }

    // Public method to handle account closure
    public String closeCustomerAccount(int accountNumber) {
        try {
            if (!hasZeroBalance(accountNumber)) {
                return "Account balance must be zero to close the account.";
            }

            if (closeAccount(accountNumber)) {
                return "Your account has been closed successfully.";
            } else {
                return "Error closing account. Please try again later.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "An error occurred: " + e.getMessage();
        }
    }
}
