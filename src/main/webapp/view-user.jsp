<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.uts.restaurant.model.User"%>
<%@page import="com.uts.restaurant.model.Customer"%>
<%@page import="com.uts.restaurant.model.Staff"%>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
        <title>Restaurant | Home</title>
        <link rel="stylesheet" href="styles.css">
    </head>

    <body>
        <%
            User user = (User)request.getSession().getAttribute("target-user");
            String id = (String)String.valueOf(user.getID());
            String type = (String)request.getSession().getAttribute("type");
            String fname = user.getFname();
            String surname = user.getSurname();
            String email = user.getEmail();
            String phoneNo = user.getPhoneNo();

            String emailErr = (String) session.getAttribute("emailErr");
            String fnameErr = (String) session.getAttribute("fnameErr");
            String surnameErr = (String) session.getAttribute("surnameErr");
            String phoneErr = (String) session.getAttribute("phoneErr");
            session.setAttribute("emailErr", "");
            session.setAttribute("fnameErr", "");
            session.setAttribute("surnameErr", "");
            session.setAttribute("phoneErr", "");
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
            <form action="update-user" , method="post" class="content">
                <h1>Viewing <%= fname%> <%= surname%>'s Profile</h1>
                <table>
                    <div class="profile">
                        <tr>
                            <td><b>Id</b></td>
                            <td><%= id%> <input name="id" type="hidden" value="<%= id%>"></td>
                        </tr>
                        <tr>
                            <td><b>User Type</b></td>
                            <td><%= type%></td>
                        </tr>
                        <tr>
                            <td><label for="fname"><b>First Name</b></label></td>
                            <td><input type="text" name="fname" value="<%= fname%>" required>
                                <% if (fnameErr != null) { %>
                                    <br><div style="color: red;"><%= fnameErr%></div>
                                <% } %>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="surname"><b>Surname</b></label></td>
                            <td><input type="text" name="surname" value="<%= surname%>" required>
                                <% if (surnameErr != null) { %>
                                    <br><div style="color: red;"><%= surnameErr%></div>
                                <% } %>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="email"><b>Email</b></label></td>
                            <td><input type="text" name="email" value="<%= email%>" required>
                                <% if (emailErr != null) { %>
                                    <br><div style="color: red;"><%= emailErr%></div>
                                <% } %>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="phoneNo"><b>Phone Number</b></label></td>
                            <td><input type="text" name="phoneNo" value="<%= phoneNo%>" required>
                                <% if (phoneErr != null) { %>
                                    <br><div style="color: red;"><%= phoneErr%></div>
                                <% } %>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="status"><b>User Status:</b></label></td>
                            <td>
                                <% if (user.isActive()) { %>
								<select name="status" id="status">
									<option selected value="true">Active</option>
									<option value="false">Inactive</option>
								</select>
								<% } else {%>
								<select name="status" id="status">
									<option value="true">Active</option>
									<option selected value="false">Inactive</option>
								</select>
								<% }%>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="password"><b>Reset Password (Optional)</b></label></td>
                            <td><input type="text" name="password">
                        </tr>
                    </div>
                </table>
                <div class="buttons">
                    <a class="button" href="view-users">Back</a>
                    <button>Update</button>
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