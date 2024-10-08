<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="com.uts.restaurant.model.Order"%>
<%@page import="com.uts.restaurant.model.Orders"%>
<%@page import="com.uts.restaurant.model.Customer"%>
<%@page import="java.io.IOException"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
    <title>Restaurant | View Orders</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>

    <%
        Orders orders = (Orders) request.getSession().getAttribute("orders");
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
            <h1>Viewing Orders</h1>
        </div>
        <div>
            <form action="view-orders", method="post" class="search">
                <input type="text" id="emailFilter" name="emailFilter" value="<%= (emailFilter != null ? emailFilter : "")%>" placeholder="Filter by email...">
                <label for="fromDate">From:</label>
                <input type="datetime-local" name="fromDate" value="<%= fromDate%>">
                <label for="toDate">To:</label>
                <input type="datetime-local" name="toDate" value="<%= toDate%>">
                <button type="submit">Search</button>
            </form>
            <table id="center">
                <tr>
                    <th>Order ID</th>
                    <th>Customer ID</th>
                    <th>Customer Email</th>
                    <th>Date</th>
                    <th>Receipt Number</th>
                    <th>Payment Type</th>
                    <th></th>
                </tr>
                <% 
                    int i = 0;
                    for (Order order : orders.getOrders()) {
                        int orderID = order.getID();
                        int customerID = order.getCustomer().getID();
                        String email = order.getCustomer().getEmail();
                        String date = order.getDate();
                        int receiptNo = order.getReceiptNo();
                        String paymentType = order.getPaymentType();
                %>
                <tr>
                    <td><%= orderID%></td>
                    <td><%= customerID%></td>
                    <td><%= email%></td>
                    <td><%= date%></td>
                    <td><%= receiptNo%></td>
                    <td><%= paymentType%></td>
                    <form action="view-order-items", method="post">
                        <input type="hidden" name="orderID" value="<%= orderID%>">
                        <input type="hidden" name="email" value="<%= email%>">
                        <td><button type="submit">View</button></td>
                    </form>
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

</html>