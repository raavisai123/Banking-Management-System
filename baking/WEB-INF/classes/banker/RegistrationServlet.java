package banker;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String idproof = request.getParameter("idproof");
        String email = request.getParameter("email");
        String phone = request.getParameter("mobilenumber");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String accountType = request.getParameter("typeofaccount");
        double initialDeposit = Double.parseDouble(request.getParameter("initialDeposit"));
        String accountNumber = generateAccountNumber();
        String tempPassword = generateTempPassword();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Validate age
        if (!isValidAge(dob)) {
            out.println("<h1>Registration Failed</h1>");
            out.println("<p>You must be at least 18 years old to register.</p>");
            out.println("<a href='registration.jsp'>Try Again</a>");
            return;
        }

        // Validate initial deposit
        if (initialDeposit < 1000) {
            out.println("<h1>Registration Failed</h1>");
            out.println("<p>Initial deposit must be at least 1000 Rs.</p>");
            out.println("<a href='registration.jsp'>Try Again</a>");
            return;
        }

        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankerDB", "root", "67896789Bhargav@");

            String insertQuery = "INSERT INTO customers (accountnumber, fullname, idproof, email, mobilenumber, dob, gender, typeofaccount, initialdeposit, balance, password, temppassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
            ps = con.prepareStatement(insertQuery);
            ps.setString(1, accountNumber);
            ps.setString(2, fullname);
            ps.setString(3, idproof);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, dob);
            ps.setString(7, gender);
            ps.setString(8, accountType);
            ps.setDouble(9, initialDeposit);
            ps.setDouble(10, initialDeposit); // Setting balance equal to initialDeposit
            ps.setString(11, tempPassword);

            int result = ps.executeUpdate();

            if (result > 0) {
                out.println("<h1>Registration Successful</h1>");
                out.println("<p>Account Number: " + accountNumber + "</p>");
                out.println("<p>Temporary Password: " + tempPassword + "</p>");
                out.println("<a href='input.jsp'>GO TO LOGIN PAGE</a>");
            } else {
                out.println("<h1>Registration Failed</h1>");
                out.println("<a href='success.jsp'>Try Again</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isValidAge(String dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateOfBirth = sdf.parse(dob);
            Calendar dobCal = Calendar.getInstance();
            dobCal.setTime(dateOfBirth);
            Calendar today = Calendar.getInstance();
            int age = today.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR);
            if (today.get(Calendar.DAY_OF_YEAR) < dobCal.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }
            return age >= 18;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String generateAccountNumber() {
        SecureRandom random = new SecureRandom();
        return String.valueOf(1000000000 + random.nextInt(900000000));
    }

    private String generateTempPassword() {
        SecureRandom random = new SecureRandom();
        return String.valueOf(100000 + random.nextInt(900000));
    }
}
