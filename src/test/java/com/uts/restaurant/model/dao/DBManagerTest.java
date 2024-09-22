package com.uts.restaurant.model.dao;

import com.uts.restaurant.model.Users;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class DBManagerTest {
    Connection conn;
    DBManager manager;
    
    @Before 
    public void initialize() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "password");
            conn.setAutoCommit(false);
            conn.prepareStatement("DELETE FROM Users").executeUpdate();
            manager = new DBManager(conn); 
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);     
        }
    }

    @After
    public void cleanUp() {
        try {
            conn.rollback();
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }

    @Test //Given that a newly created user is added to the users table, when addCustomer() method is called, then the user id should be in the customers table.
    public void testAddCustomer() {
        try {
            manager.addCustomer("test@mail.com", "test", "Test", "Test", "0412345678");
            ResultSet rs = conn.prepareStatement("SELECT * FROM Customers WHERE customer_id=last_insert_id()").executeQuery();
            assertTrue(rs.next());
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }

    @Test //Given that a newly created user is added to the users table, when addCustomer() method is called, then the user should contain all of the details passed in the addCustomer() method.
    public void testAddCustomerDetails() {
        try {
            manager.addCustomer("test@mail.com", "test", "Test1", "Test2", "0412345678");
            ResultSet rs = conn.prepareStatement("SELECT * FROM Users WHERE user_id=last_insert_id()").executeQuery();
            if (rs.next()) {
                assertEquals("test@mail.com", rs.getString("email"));
                assertEquals("test", rs.getString("password"));
                assertEquals("Test1", rs.getString("fname"));
                assertEquals("Test2", rs.getString("surname"));
                assertEquals("0412345678", rs.getString("phoneno"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given that a newly created user is added to the users table, when addStaff() method is called, then the user id should be in the staff table.
    public void testAddStaff() {
        try {
            manager.addStaff("test@mail.com", "test", "Test", "Test", "0412345678");
            ResultSet rs = conn.prepareStatement("SELECT * FROM Staff WHERE staff_id=last_insert_id()").executeQuery();
            assertTrue(rs.next());
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given that a newly created user is added to the users table, when addStaff() method is called, then the user should contain all of the details passed in the addStaff() method.
    public void testAddStaffDetails() {
        try {
            manager.addStaff("test@mail.com", "test", "Test1", "Test2", "0412345678");
            ResultSet rs = conn.prepareStatement("SELECT * FROM Users WHERE user_id=last_insert_id()").executeQuery();
            if (rs.next()) {
                assertEquals("test@mail.com", rs.getString("email"));
                assertEquals("test", rs.getString("password"));
                assertEquals("Test1", rs.getString("fname"));
                assertEquals("Test2", rs.getString("surname"));
                assertEquals("0412345678", rs.getString("phoneno"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }

    @Test //Given that newly created users are added to the users table, when getUsers() method is called, then an arraylist of user objects should be returned with details corresponding to the created users.
    public void testGetUsers() {
        try {
            manager.addCustomer("test1@mail.com", "test1", "Test1", "Test2", "0411111111");
            manager.addCustomer("test2@mail.com", "test2", "Test3", "Test4", "0422222222");
            manager.addStaff("test3@mail.com", "test3", "Test5", "Test6", "0433333333");
            Users users = manager.getUsers();
            assertNotNull(users.getUsers().get(0));
            assertNotNull(users.getUsers().get(1));
            assertNotNull(users.getUsers().get(2));
            assertEquals("test1@mail.com", users.getUsers().get(0).getEmail());
            assertEquals("Test1", users.getUsers().get(0).getFname());
            assertEquals("Test2", users.getUsers().get(0).getSurname());
            assertEquals("0411111111", users.getUsers().get(0).getPhoneNo());
            assertEquals("test2@mail.com", users.getUsers().get(1).getEmail());
            assertEquals("Test3", users.getUsers().get(1).getFname());
            assertEquals("Test4", users.getUsers().get(1).getSurname());
            assertEquals("0422222222", users.getUsers().get(1).getPhoneNo());
            assertEquals("test3@mail.com", users.getUsers().get(2).getEmail());
            assertEquals("Test5", users.getUsers().get(2).getFname());
            assertEquals("Test6", users.getUsers().get(2).getSurname());
            assertEquals("0433333333", users.getUsers().get(2).getPhoneNo());
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }
}
