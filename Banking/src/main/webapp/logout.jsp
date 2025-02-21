<%-- logout.jsp --%>
<% 
    session.invalidate();  // Invalidate the session
    response.sendRedirect("login.jsp");  // Redirect to the login page
%>
