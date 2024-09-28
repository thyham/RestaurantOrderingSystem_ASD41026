package com.uts.restaurant.model.dao;

import com.uts.restaurant.model.User;
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

    @Test //Given that a newly created user is added to the users table, when checkUser() method is called with the user email and password, then it should return true.
    public void testCheckUser() {
        try {
            manager.addCustomer("test@mail.com", "test", "Test", "Test", "0412345678");
            assertTrue(manager.checkUser("test@mail.com", "test"));
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }

    @Test //Given that a newly created user is added to the users table via the addCustomer() method, when checkCustomer() method is called with the user id, then it should return true.
    public void testCheckCustomerAsCustomer() {
        try {
            manager.addCustomer("test@mail.com", "test", "Test", "Test", "0412345678");
            ResultSet rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                assertTrue(manager.checkCustomer(id));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given that a newly created user is added to the users table via the addStaff() method, when checkCustomer() method is called with the user id, then it should return false.
    public void testCheckCustomerAsStaff() {
        try {
            manager.addStaff("test@mail.com", "test", "Test", "Test", "0412345678");
            ResultSet rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                assertFalse(manager.checkCustomer(id));
            }      
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }

    @Test //Given that a newly created user is added to the users table via the addCustomer() method, when checkStaff() method is called with the user id, then it should return false.
    public void testCheckStaffAsCustomer() {
        try {
            manager.addCustomer("test@mail.com", "test", "Test", "Test", "0412345678");
            ResultSet rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                assertFalse(manager.checkStaff(id));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given that a newly created user is added to the users table via the addStaff() method, when checkStaff() method is called with the user id, then it should return true.
    public void testCheckStaffAsStaff() {
        try {
            manager.addStaff("test@mail.com", "test", "Test", "Test", "0412345678");
            ResultSet rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                assertTrue(manager.checkStaff(id));
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

    @Test //Given that a newly created user is added to the users table, when updateUser() method is called, then the created user should be modified to contain the parameters input in the method.
    public void testUpdateUser() {
        try {
            manager.addCustomer("test1@mail.com", "test1", "Test1", "Test2", "0411111111");
            ResultSet rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                manager.updateUserFromAdmin(id, "test2@mail.com", "test2", "Test3", "Test4", "0422222222", (int)0);
                User user = manager.getUser(id);
                assertEquals("test2@mail.com", user.getEmail());
                assertEquals("Test3", user.getFname());
                assertEquals("Test4", user.getSurname());
                assertEquals("0422222222", user.getPhoneNo());
                assertFalse(user.isActive());
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given a newly created user is added to the users table, when deleteUser() method is called, then the created user should be deleted from the user table.
    public void testDeleteUser() {
        try {
            manager.addCustomer("test1@mail.com", "test1", "Test1", "Test2", "0411111111");
            ResultSet rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                manager.deleteUser(id);
                User user = manager.getUser(id);
                assertEquals(null, user);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given that newly created users are added to the users table, when getUsers() method is called with email filter, then an arraylist of user objects containing email filter (beginning from the start of string) should be returned with details corresponding to the created users.
    public void testGetUsersEmailFilter() {
        try {
            manager.addCustomer("test1@mail.com", "test1", "Test1", "Test2", "0411111111");
            manager.addCustomer("test1@mail2.com", "test2", "Test3", "Test4", "0422222222");
            manager.addStaff("test3@mail.com", "test3", "Test5", "Test6", "0433333333");
            Users users = manager.getUsers("test1@ma", "");
            assertNotNull(users.getUsers().get(0));
            assertNotNull(users.getUsers().get(1));
            assertEquals("test1@mail.com", users.getUsers().get(0).getEmail());
            assertEquals("Test1", users.getUsers().get(0).getFname());
            assertEquals("Test2", users.getUsers().get(0).getSurname());
            assertEquals("0411111111", users.getUsers().get(0).getPhoneNo());
            assertEquals("test1@mail2.com", users.getUsers().get(1).getEmail());
            assertEquals("Test3", users.getUsers().get(1).getFname());
            assertEquals("Test4", users.getUsers().get(1).getSurname());
            assertEquals("0422222222", users.getUsers().get(1).getPhoneNo());
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given that newly created users are added to the users table, when getUsers() method is called with phone filter, then an arraylist of user objects containing phone filter (beginning from the start of string) should be returned with details corresponding to the created users.
    public void testGetUsersPhoneFilter() {
        try {
            manager.addCustomer("test1@mail.com", "test1", "Test1", "Test2", "0411111111");
            manager.addCustomer("test2@mail.com", "test2", "Test3", "Test4", "0411111111");
            manager.addStaff("test3@mail.com", "test3", "Test5", "Test6", "0433333333");
            Users users = manager.getUsers("", "041111");
            assertNotNull(users.getUsers().get(0));
            assertNotNull(users.getUsers().get(1));
            assertEquals("test1@mail.com", users.getUsers().get(0).getEmail());
            assertEquals("Test1", users.getUsers().get(0).getFname());
            assertEquals("Test2", users.getUsers().get(0).getSurname());
            assertEquals("0411111111", users.getUsers().get(0).getPhoneNo());
            assertEquals("test2@mail.com", users.getUsers().get(1).getEmail());
            assertEquals("Test3", users.getUsers().get(1).getFname());
            assertEquals("Test4", users.getUsers().get(1).getSurname());
            assertEquals("0411111111", users.getUsers().get(1).getPhoneNo());
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given that newly created users are added to the users table, when getUsers() method is called with email and phone filter, then an arraylist of user objects containing email and phone filter (beginning from the start of strings) should be returned with details corresponding to the created users.
    public void testGetUsersEmailAndPhoneFilter() {
        try {
            manager.addCustomer("test1@mail.com", "test1", "Test1", "Test2", "0411111111");
            manager.addCustomer("test1@mail2.com", "test2", "Test3", "Test4", "0422222222");
            manager.addStaff("test3@mail.com", "test3", "Test5", "Test6", "0433333333");
            Users users = manager.getUsers("test1@ma", "042");
            assertNotNull(users.getUsers().get(0));
            assertEquals("test1@mail2.com", users.getUsers().get(0).getEmail());
            assertEquals("Test3", users.getUsers().get(0).getFname());
            assertEquals("Test4", users.getUsers().get(0).getSurname());
            assertEquals("0422222222", users.getUsers().get(0).getPhoneNo());
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }
}
