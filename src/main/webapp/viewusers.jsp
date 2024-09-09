<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
    <title>Restaurant | Home</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>
    <header>
        <div>
            <img src="" alt="Logo">
        </div>
        <ul>
            <li><a class="active" href="homedirect.jsp">Home</a></li>
            <li><a href=".">Menu</a></li>
            <li><a href=".">About</a></li>
            <li><a href=".">Contact</a></li>
        </ul>
    </header>

    <div class="content">
        <div>
            <h1>Viewing Users</h1>
        </div>
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
            <tr>
                <form action="." , method="post">
                    <input type="hidden" name="id">
                    <td><button>x</button></td>
                </form>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <form action="." , method="post">
                    <input type="hidden" name="id">
                    <input type="hidden" name="type">
                    <td><button onclick="">View</button></td>
                </form>
            </tr>
            <tr>
                <form action="." , method="post">
                    <input type="hidden" name="id">
                    <td><button>x</button></td>
                </form>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <form action="." , method="post">
                    <input type="hidden" name="id">
                    <input type="hidden" name="type">
                    <td><button onclick="">View</button></td>
                </form>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><a href="."><button>Add</button></a></td>
            </tr>
        </table>
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