<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
        <title>Restaurant | Login</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
		<header>
			<div>
			  <img src="" alt="Logo">
			</div>
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href=".">Menu</a></li>
				<li><a href=".">About</a></li>
				<li><a href=".">Contact</a></li>
			</ul>
		</header>

		<div class="content">
			<div id="center">
                <form>
                    <h1>Login</h1>
                    <label for="uname"><b>Username</b></label>
                    <input type="text" placeholder="Enter Username" name="uname" required>
                    <label for="psw"><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="psw" required>
                    <button type="submit">Login</button>
                    <div class="container">
                        <a class="cancelbtn" href="index.jsp">Cancel</a>
                        <span>Dont have an <a href=".">account?</a></span>
                    </div>
                </form>
            </div>
		</div>

		<footer>
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href=".">Menu</a></li>
				<li><a href=".">About</a></li>
				<li><a href=".">Contact</a></li>
			</ul>
			<p>By Group 6 | University of Technology | Spring 2024</p>
		</footer>
	</body>
</html>