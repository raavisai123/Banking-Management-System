<%-- dashboard.jsp --%>
<% 
    // Retrieve the user's email from the session
    String userEmail = (String) session.getAttribute("user");

    // If no user is logged in, redirect to the login page
    if (userEmail == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome to Your Dashboard</h1>
    <p>Logged in as: <%= userEmail %></p>
    <a href="logout.jsp">Logout</a>
</body>
</html>
