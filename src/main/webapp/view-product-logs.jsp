<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="com.uts.restaurant.model.ProductLogs"%>
<%@page import="com.uts.restaurant.model.ProductLog"%>
<%@page import="com.uts.restaurant.model.Product"%>
<%@page import="com.uts.restaurant.model.Staff"%>
<%@page import="java.io.IOException"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
    <title>Restaurant | View Product Logs</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>

    <%
        ProductLogs productLogs = (ProductLogs) request.getSession().getAttribute("productLogs");
        String emailFilter = (String) session.getAttribute("emailFilter");
        String productFilter = (String) session.getAttribute("productFilter");
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
            <h1>Viewing Product Logs</h1>
        </div>
        <div>
            <form action="view-product-logs", method="post" class="search">
                <input type="text" id="emailFilter" name="emailFilter" value="<%= (emailFilter != null ? emailFilter : "")%>" placeholder="Filter by email...">
                <input type="text" id="productFilter" name="productFilter" value="<%= (productFilter != null ? productFilter : "")%>" placeholder="Filter by product name...">
                <label for="fromDate">From:</label>
                <input type="datetime-local" name="fromDate" value="<%= fromDate%>">
                <label for="toDate">To:</label>
                <input type="datetime-local" name="toDate" value="<%= toDate%>">
                <button type="submit">Search</button>
            </form>
            <table id="center">
                <tr>
                    <th>Staff ID</th>
                    <th>Staff Email</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Date</th>
                    <th>Description</th>
                </tr>
                <% 
                    int i = 0;
                    for (ProductLog productLog : productLogs.getProductLogs()) {
                        Staff staff = productLog.getStaff();
                        int staffID = staff.getID();
                        String email = staff.getEmail();
                        String date = productLog.getDate();
                        String desc = productLog.getDesc();
                        Product product = productLog.getProduct();
                        int productID = product.getID();
                        String productName = product.getName();
                %>
                <tr>
                    <td><%= staffID%></td>
                    <td><%= email%></td>
                    <td><%= productID%></td>
                    <td><%= productName%></td>
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