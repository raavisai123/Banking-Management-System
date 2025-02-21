package banker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CloseAccountServlet")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String jdbcURL = "jdbc:mysql://localhost:3306/bankerDB";
    private String dbUser = "root";
    private String dbPassword = "67896789Bhargav@";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (var out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>Close Your Account</h1>");
            out.println("<form action='CloseAccountServlet' method='post'>");
            out.println("<input type='submit' value='Close Account'>");
            out.println("</form>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String accountNumber = (String) session.getAttribute("accountnumber");
        if (session == null || accountNumber == null) {
            response.sendRedirect("input.jsp?message=Please login first.");
            return;
        }

       
        String checkBalanceSQL = "SELECT balance FROM customers WHERE accountnumber = ?";
        String updateSQL = "UPDATE customers SET status = 'inactive' WHERE accountnumber = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {

                // Check account balance
                try (PreparedStatement checkStatement = connection.prepareStatement(checkBalanceSQL)) {
                    checkStatement.setString(1, accountNumber);
                    try (ResultSet resultSet = checkStatement.executeQuery()) {

                        if (resultSet.next()) {
                            double balance = resultSet.getDouble("balance");
                            if (balance != 0) {
                                response.setContentType("text/html");
                                try (var out = response.getWriter()) {
                                    out.println("<html><body>");
                                    out.println("<h1>Close Your Account</h1>");
                                    out.println("<p>Account balance must be zero to close.</p>");
                                    out.println("<a href='CloseAccountServlet'>Go back</a>");
                                    out.println("</body></html>");
                                }
                            } else {
                                // Update account status to inactive
                                try (PreparedStatement updateStatement = connection.prepareStatement(updateSQL)) {
                                    updateStatement.setString(1, accountNumber);
                                    int rowsUpdated = updateStatement.executeUpdate();

                                    response.setContentType("text/html");
                                    try (var out = response.getWriter()) {
                                        out.println("<html><body>");
                                        if (rowsUpdated > 0) {
                                            session.invalidate();
                                            out.println("<h1>Close Account</h1>");
                                            out.println("<p>Your account has been closed successfully.</p>");
                                            out.println("<a href='input.jsp'>Go to Login Page</a>");
                                        } else {
                                            out.println("<h1>Close Account</h1>");
                                            out.println("<p>Error closing account. Please try again later.</p>");
                                            out.println("<a href='CloseAccountServlet'>Go back</a>");
                                        }
                                        out.println("</body></html>");
                                    }
                                }
                            }
                        } else {
                            response.setContentType("text/html");
                            try (var out = response.getWriter()) {
                                out.println("<html><body>");
                                out.println("<h1>Close Account</h1>");
                                out.println("<p>Account number not found.</p>");
                                out.println("<a href='CloseAccountServlet'>Go back</a>");
                                out.println("</body></html>");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            try (var out = response.getWriter()) {
                out.println("<html><body>");
                out.println("<h1>Close Account</h1>");
                out.println("<p>An error occurred: " + e.getMessage() + "</p>");
                out.println("<a href='CloseAccountServlet'>Go back</a>");
                out.println("</body></html>");
            }
        }
    }
}
