<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.uts.restaurant.model.User"%>
<%@page import="com.uts.restaurant.model.Customer"%>
<%@page import="com.uts.restaurant.model.Staff"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
    <title>Restaurant | Add User</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>
    <%
        String duplicateErr = (String) session.getAttribute("duplicateErr");
        String emailErr = (String) session.getAttribute("emailErr");
        String fnameErr = (String) session.getAttribute("fnameErr");
        String surnameErr = (String) session.getAttribute("surnameErr");
        String phoneErr = (String) session.getAttribute("phoneErr");
        session.setAttribute("emailErr", "");
        session.setAttribute("fnameErr", "");
        session.setAttribute("surnameErr", "");
        session.setAttribute("phoneErr", "");
        session.setAttribute("duplicateErr", "");
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

    <div id="center">
        <form action="add-user" , method="post" class="content">
            <h1>Add User</h1>
            <div style="color: red;"><%= (duplicateErr != null ? duplicateErr : "")%></div>
            <table>
                <div class="profile">
                    <tr>
                      <td><label for="type"><b>User Type</b></label></td>
                        <td>
                            <select name="type" id="type">
                                <option selected value="Customer">Customer</option>
                                <option value="Staff">Staff</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="fname"><b>First Name</b></label></td>
                        <td><input type="text" name="fname" required>
                            <% if (fnameErr != null) { %>
                                <br><div style="color: red;"><%= fnameErr%></div>
                            <% } %>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="surname"><b>Surname</b></label></td>
                        <td><input type="text" name="surname" required>
                            <% if (surnameErr != null) { %>
                                <br><div style="color: red;"><%= surnameErr%></div>
                            <% } %>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="email"><b>Email</b></label></td>
                        <td><input type="text" name="email" required>
                            <% if (emailErr != null) { %>
                                <br><div style="color: red;"><%= emailErr%></div>
                            <% } %>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="phoneNo"><b>Phone Number</b></label></td>
                        <td><input type="text" name="phoneNo" required>
                            <% if (phoneErr != null) { %>
                                <br><div style="color: red;"><%= phoneErr%></div>
                            <% } %>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="password"><b>Password</b></label></td>
                        <td><input type="password" name="password">
                    </tr>
                </div>
            </table>
            <div class="buttons">
                <a class="button" href="view-users">Back</a>
                <button>Add</button>
            </div>
        </form>
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