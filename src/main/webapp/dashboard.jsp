<%@page contentType="text/html" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
        <title>Restaurant | Dashboard</title>
        <link rel="stylesheet" href="styles.css">
    </head>

    <body>
        <header>
            <div>
                <img src="placeholderimage.webp" alt="Logo" height="80px" width="80px">
            </div>
            <ul>
                <li><a class="active" href="homedirect.jsp">Home</a></li>
                <li><a href=".">Menu</a></li>
                <li><a href=".">About</a></li>
                <li><a href=".">Contact</a></li>
            </ul>
        </header>

        <div class="content">
            <h1>Dashboard</h1>
            <div class="dashboard">
                <ul>
                    <li><a href="view-users">View Users</a></li>
                    <li><a href=".">View Access Logs</a></li>
                    <li><a href=".">View Product Logs</a></li>
                    <li><a href=".">View Order Logs</a></li>
                </ul>
            </div>
            <a href="logout" class="logoutbtn">Logout</a>
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