package Bankers.controller;

import Bankers.util.DBConnection;
import Bankers.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginServlet")  // Maps the servlet to the URL "/LoginServlet"
public class LoginServlet extends HttpServlet {

    // This method handles the POST request for login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve email and password from the form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate the email format using the utility class
        if (!Validation.isValidEmail(email)) {
            response.getWriter().println("Invalid email format.");
            return;
        }

        // Authenticate the user by checking the database
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);  // Set the email parameter
            stmt.setString(2, password);  // Set the password parameter

            ResultSet rs = stmt.executeQuery();  // Execute the query

            if (rs.next()) {  // If user is authenticated
                // Create session and set the user attribute
                HttpSession session = request.getSession();
                session.setAttribute("user", email);

                // Redirect to the dashboard or main page
                response.sendRedirect("dashboard.jsp");
            } else {
                // If credentials are incorrect, show an error message
                response.getWriter().println("Invalid login credentials.");
            }
        } catch (Exception e) {
            e.printStackTrace();  // Print error if any occurs
            response.getWriter().println("Error while processing login.");
        }
    }
}
