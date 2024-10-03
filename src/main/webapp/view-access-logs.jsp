<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="com.uts.restaurant.model.AccessLogs"%>
<%@page import="com.uts.restaurant.model.AccessLog"%>
<%@page import="com.uts.restaurant.model.User"%>
<%@page import="com.uts.restaurant.model.Customer"%>
<%@page import="java.io.IOException"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
    <title>Restaurant | View Users</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>

    <%
        AccessLogs accessLogs = (AccessLogs) request.getSession().getAttribute("accessLogs");
        String emailFilter = (String) session.getAttribute("emailFilter");
        String fromDate = (String)request.getSession().getAttribute("fromDate");
        String toDate = (String)request.getSession().getAttribute("toDate");
    %>

    <header>
        <div>
            <img src="placeholderimage.webp" alt="Logo" height="80px" width="80px">
        </div>
        <ul>
            <li><a href="homedirect.jsp">Home</a></li>
            <li><a href=".">Menu</a></li>
            <li><a href=".">About</a></li>
            <li><a href=".">Contact</a></li>
        </ul>
    </header>

    <div class="content">
        <div>
            <h1>Viewing Access Logs</h1>
        </div>
        <div>
            <form action="view-access-logs", method="post" class="search">
                <input type="text" id="emailFilter" name="emailFilter" value="<%= (emailFilter != null ? emailFilter : "")%>" placeholder="Filter by email...">
                <label for="fromDate">From:</label>
                <input type="datetime-local" name="fromDate" value="<%= fromDate%>">
                <label for="toDate">To:</label>
                <input type="datetime-local" name="toDate" value="<%= toDate%>">
                <button type="submit">Search</button>
            </form>
            <table id="center">
                <tr>
                    <th>User ID</th>
                    <th>User Email</th>
                    <th>User Type</th>
                    <th>Date</th>
                    <th>Description</th>
                </tr>
                <% 
                    int i = 0;
                    for (AccessLog accessLog : accessLogs.getAccessLogs()) {
                        User user = accessLog.getUser();
                        int id = user.getID();
                        String email = user.getEmail();
                        String date = accessLog.getDate();
                        String desc = accessLog.getDesc();
                        String type = "";
                        if (user instanceof Customer) {
                            type = "Customer";
                        }
                        else {
                            type = "Staff";
                        }
                %>
                <tr>
                    <td><%= id%></td>
                    <td><%= email%></td>
                    <td><%= type%></td>
                    <td><%= date%></td>
                    <td><%= desc%></td>
                </tr>
                <% i++; }%>
            </table>
        </div>
        <a href="dashboard.jsp" class="logoutbtn">Back</a>
    </div>

    <footer>
        <ul>
            <li><a href="homedirect.jsp">Home</a></li>
            <li><a href=".">Menu</a></li>
            <li><a href=".">About</a></li>
            <li><a href=".">Contact</a></li>
        </ul>
        <p>By Group 6 | University of Technology | Spring 2024</p>
    </footer>
</body>