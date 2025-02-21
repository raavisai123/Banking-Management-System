<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="registration.css">
<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
<title>Registration Form</title>
</head>
<body>
    <div class="container">
        <header>Registration</header>
        <form action="RegistrationServlet" method="post">
            <div class="form first">
                <div class="details personal">
                    <span class="title">Personal Details</span>
                    <div class="fields">
                        <div class="input-field">
                            <label>Full Name</label>
                            <input type="text" id="fullname" name="fullname" required>

                        </div>
                        <div class="input-field">
                            <label>Id Proof</label>
        <input type="text" id="idproof" name="idproof" required>
                        </div>
                        <div class="input-field">
                            <label>Date of Birth</label>
        <input type="date" id="dob" name="dob" required>
                        </div>
                        <div class="input-field">
                            <label>Email</label>
        <input type="email" id="email" name="email" required>
                        </div>
                        <div class="input-field">
                            <label>Mobile Number</label>
                                    <input type="text" id="mobilenumber" name="mobilenumber" required>

                        </div>
                        <div class="input-field">
        <label for="initialDeposit">Initial Deposit:</label>
        <input type="number" id="initialDeposit" name="initialDeposit" required>
                        </div>
                        <div class="input-field">
                            <label>Gender</label>
                            <select name="gender" required>
                                <option disabled selected>Select gender</option>
                                <option>Male</option>
                                <option>Female</option>
                                <option>Others</option>
                            </select>
                        </div>
                        <div class="input-field">
                            <label>Type of Account</label>
                            <select name="typeofaccount" required>
                                <option disabled selected>Select Account Type</option>
                                <option>Savings Account</option>
                                <option>Current Account</option>
                            </select>
                        </div>
                    </div>
                </div>
                 <div class="buttons">
        <button type="submit" value="Register" class="submit">
            <span class="btnText">Submit</span>
            <i class="uil uil-navigator"></i>
        </button>
    </div>
            </div>
        </form>
    </div>
    <script src="script.js"></script>
</body>
</html>
