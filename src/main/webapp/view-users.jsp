<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="com.uts.restaurant.model.Users"%>
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
        Users users = (Users)request.getSession().getAttribute("users");
        String emailFilter = (String) session.getAttribute("emailFilter");
        String phoneNoFilter = (String) session.getAttribute("phoneNoFilter");
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
            <h1>Viewing Users</h1>
        </div>
        <div>
            <form action="search-users", method="post" class="search">
                <input type="text" id="emailFilter" name="emailFilter" value="<%= (emailFilter != null ? emailFilter : "")%>" placeholder="Filter by email...">
                <input type="text" id="phoneNoFilter" name="phoneNoFilter" value="<%= (phoneNoFilter != null ? phoneNoFilter : "")%>" placeholder="Filter by phone number...">
                <button type="submit">Search</button>
            </form>
            <table>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>User Type</th>
                    <th>Status</th>
                    <th></th>
                </tr>
                <% 
                    int i = 0;
                    for (User user : users.getUsers()) {
                        int id = user.getID();
                        String email = user.getEmail();
                        String phoneNo = user.getPhoneNo();
                        String type = "";
                        String status = "";
                        if (user instanceof Customer) {
                            type = "Customer";
                        }
                        else {
                            type = "Staff";
                        }
                        if (user.isActive()) {
                            status = "Active";
                        }
                        else {
                            status = "Inactive";
                        }
                %>
                <tr>
                    <form action="delete-user", method="post">
                        <input type="hidden" name="id" value="<%= id%>">
                        <td><button>x</button></td>
                    </form>
                    <td><%= id%></td>
                    <td><%= email%></td>
                    <td><%= phoneNo%></td>
                    <td><%= type%></td>
                    <td><%= status%></td>
                    <form action="view-user", method="post">
                        <input type="hidden" name="id" value="<%= id%>">
                        <input type="hidden" name="type" value="<%= type%>">
                        <td><button>View</button></td>
                    </form>
                </tr>
                <% i++; }%>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="add-user.jsp"><button>Add</button></a></td>
                </tr>
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