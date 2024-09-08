<%@page import="com.uts.restaurant.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User user = (User)request.getSession().getAttribute("user");
    if(user != null) {
       response.sendRedirect("dashboard.jsp");
    }
    else {
        response.sendRedirect("index.jsp");
    }
%>