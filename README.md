This repository is for Group 6's Restaurant Ordering System

Uses maven for building (.war file).

JUnit testing requires that the database is set up or the project will not compile. Either set it up, or remove the tests.

Make sure the username is "root" and password is "password" for the MySQL connection, and ensure the MySQL schema is called "restaurant" (check the connection string in the code). 

MySQL tables are in db folder and should be added to the schema. Database.sql contains all the tables + data and can be ran once.

Once compiled, host the .war file on Apache Tomcat and access the project at http://localhost:8080/restaurant-1.0 in your browser.

To log in as the system admin, enter the email as "root" and the password as "restaurant" on the login page of the website.
