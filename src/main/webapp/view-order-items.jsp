<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="com.uts.restaurant.model.OrderItem"%>
<%@page import="com.uts.restaurant.model.OrderItems"%>
<%@page import="java.io.IOException"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
    <title>Restaurant | View Order Items</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>

    <%
        OrderItems orderItems = (OrderItems) request.getSession().getAttribute("orderItems");
        String orderID = (String) String.valueOf(session.getAttribute("orderID"));
        String productFilter = (String) session.getAttribute("productFilter");
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
            <h1>Viewing Order Items for Order <%= orderID%></h1>
        </div>
        <div>
            <form action="view-order-items", method="post" class="search">
                <input type="text" id="productFilter" name="productFilter" value="<%= (productFilter != null ? productFilter: "")%>" placeholder="Filter by product name...">
                <button type="submit">Search</button>
            </form>
            <table id="center">
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Customisation</th>
                    <th>Quantity</th>
                </tr>
                <% 
                    int i = 0;
                    for (OrderItem orderItem : orderItems.getOrderItems()) {
                        int productID = orderItem.getProduct().getID();
                        String productName = orderItem.getProduct().getName();
                        String customisation = orderItem.getCustomisation();
                        int quantity = orderItem.getQuantity();
                %>
                <tr>
                    <td><%= productID%></td>
                    <td><%= productName%></td>
                    <td><%= customisation%></td>
                    <td><%= quantity%></td>
                </tr>
                <% i++; }%>
            </table>
        </div>
        <a href="view-orders" class="logoutbtn">Back</a>
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