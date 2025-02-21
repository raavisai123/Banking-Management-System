package banker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/MiniStatementServlet")
public class MiniStatementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MiniStatementServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"mini_statement.csv\"");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountnumber");

        // Debug statement to check if the account number is correctly retrieved
        System.out.println("Account Number: " + accountNumber);

        if (accountNumber == null || accountNumber.isEmpty()) {
            out.println("No account number found in session.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankerDB", "root", "67896789Bhargav@");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM transactions WHERE accountnumber = ? ORDER BY transactionDate DESC LIMIT 10");
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            out.println("Transaction ID,Transaction Date,Transaction Type,Amount");

            boolean hasTransactions = false;

            while (rs.next()) {
                hasTransactions = true;
                out.println(rs.getString("transactionId") + "," +
                            rs.getTimestamp("transactionDate") + "," +
                            rs.getString("transactionType") + "," +
                            rs.getFloat("amount"));
            }

            if (!hasTransactions) {
                out.println("No transactions found for the given account number.");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error occurred: " + e.getMessage());
        } finally {
            out.close();
        }
    }
}
