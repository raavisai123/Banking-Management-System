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

@WebServlet("/TransactionHistoryServlet")
public class TransactionHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String jdbcURL = "jdbc:mysql://localhost:3306/bankerDB";
    private String dbUser = "root";
    private String dbPassword = "67896789Bhargav@";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("accountNumber") == null) {
            response.sendRedirect("input.jsp?message=Please login first.");
            return;
        }

        String accountNumberStr = (String) session.getAttribute("accountNumber");
        int accountNumber = 0;
        try {
            accountNumber = Integer.parseInt(accountNumberStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("input.jsp?message=Invalid account number.");
            return;
        }

        String sql = "SELECT * FROM transactions WHERE accountnumber = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, accountNumber);

                try (ResultSet resultSet = statement.executeQuery()) {
                    // Process the result set and generate response
                    response.setContentType("text/html");
                    try (var out = response.getWriter()) {
                        out.println("<html><body>");
                        out.println("<h1>Transaction History</h1>");
                        while (resultSet.next()) {
                            // Example of displaying transaction data
                            out.println("<p>Transaction ID: " + resultSet.getInt("transaction_id") + "</p>");
                            out.println("<p>Amount: " + resultSet.getDouble("amount") + "</p>");
                            out.println("<p>Date: " + resultSet.getDate("transaction_date") + "</p>");
                        }
                        out.println("</body></html>");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("input.jsp?message=An error occurred: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
