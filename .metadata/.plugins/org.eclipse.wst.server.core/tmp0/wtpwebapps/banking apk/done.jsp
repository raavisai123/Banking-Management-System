<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, banker.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
       <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
</script>
    
    <title>Customer Details</title>
    <style type="text/css">
        *{
            text-decoration: none;
        }
        .navbar{
            background: rgb(65, 81, 255); font-family: calibri; padding-right: 15px;padding-left: 15px;
        }
        .navdiv{
            display: flex; align-items: center; justify-content: space-between;
        }
        .logo a{
            font-size: 35px; font-weight: 600; color: white;
        }
        li{
            list-style: none; display: inline-block;
        }
        li a{
            color: white; font-size: 18px; font-weight: bold; margin-right: 25px;
        }
        button{
            background-color: black; margin-left: 10px; border-radius: 10px; padding: 10px; width: 90px;
        }
        button a{
            color: white; font-weight: bold; font-size: 15px;
        }
        .content {
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
    </style>
</head>
<body>


</div>
    <nav class="navbar">
        <div class="navdiv">
            <div class="logo"><a href="#">PRIME BANK</a> </div>
            <ul>
                <li><a href="deposit.jsp">DEPOSITE</a></li>
                <li><a href="withdraw.jsp">WITHDRAW</a></li>
                <li><a href="transactionHistory.jsp">TRANSACTION HISTORY</a></li>
                <li><a href="CloseAccountServlet">CLOSE ACCOUNT</a></li>
                <button> <a href="input.jsp">LOGOUT</a></button>
            </ul>
        </div>
    </nav>
    <div class="content">
        <h1>Customer Details</h1>
        <%
            Customer customer = (Customer) request.getAttribute("customer");
            if (customer != null) {
        %>
        <table>
            <tr>
                <th>Full Name</th>
                <td><%= customer.getFullName() %></td>
            </tr>
            <tr>
                <th>ID Proof</th>
                <td><%= customer.getIdProof() %></td>
            </tr>
            <tr>
                <th>Mobile Number</th>
                <td><%= customer.getMobileNumber() %></td>
            </tr>
            <tr>
                <th>Gender</th>
                <td><%= customer.getGender() %></td>
            </tr>
            <tr>
                <th>Type of Account</th>
                <td><%= customer.getTypeOfAccount() %></td>
            </tr>
            <tr>
                <th>Initial Deposit</th>
                <td><%= customer.getInitialDeposit() %></td>
            </tr>
            <tr>
                <th>Present Balance</th>
                <td><%= customer.getBalance() %></td>
            </tr>
        </table>
        <% } else { %>
        <p>No customer details found.</p>
        <% } %>
    </div>
</body>
</html>