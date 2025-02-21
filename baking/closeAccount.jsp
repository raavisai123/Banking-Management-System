<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Close Account</title>
</head>
<body>
    <h1>Close Your Account</h1>
    <form action="CloseAccountServlet" method="post">
        <input type="submit" value="Close Account">
    </form>
    <p>${message}</p>
</body>
</html>
