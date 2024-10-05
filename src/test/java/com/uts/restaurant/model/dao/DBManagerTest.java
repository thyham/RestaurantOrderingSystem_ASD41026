package com.uts.restaurant.model.dao;

import com.uts.restaurant.model.User;
import com.uts.restaurant.model.Users;
import com.uts.restaurant.model.AccessLogs;
import com.uts.restaurant.model.ProductLogs;

import java.math.BigDecimal;
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
            conn.prepareStatement("DELETE FROM Products").executeUpdate();
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

    @Test //Given that a newly created user is added to the users table, when addAccessLog() method is called, then the access log with correct details should be in the AccessLogs table.
    public void testAddAccessLog() {
        try {
            int id = -1;
            manager.addCustomer("test1@mail.com", "test1", "Test1", "Test2", "0411111111");
            ResultSet rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
                manager.addAccessLog(id, "2024-09-29 14:30:08", "Successful Login");
            }
            rs = conn.prepareStatement("SELECT * FROM accesslogs").executeQuery();
            if (rs.next()) {
                assertEquals(id, rs.getInt("user_id"));
                assertEquals("2024-09-29 14:30:08", rs.getString("date"));
                assertEquals("Successful Login", rs.getString("desc"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given that newly created users and access logs are added to the database, when getAccessLogs() method is called, then an arraylist of AccessLog objects should be returned with details corresponding to the created users/access logs.
    public void testGetAccessLogs() {
        try {
            int id1 = -1, id2 = -1, id3 = -1;
            manager.addCustomer("test1@mail.com", "test1", "Test1", "Test2", "0411111111");
            ResultSet rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                id1 = rs.getInt(1);
            }
            manager.addAccessLog(id1, "2024-09-29 14:30:08", "Successful Login");
            manager.addCustomer("test2@mail.com", "test2", "Test3", "Test4", "0422222222");
            rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                id2 = rs.getInt(1);
            }
            manager.addAccessLog(id2,"2024-09-30 12:28:58","Successful Login");
            manager.addStaff("test3@mail.com", "test3", "Test5", "Test6", "0433333333");
            rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                id3 = rs.getInt(1);
            }
            manager.addAccessLog(id3,"2024-09-28 13:31:42","Successful Login");
            
            AccessLogs accessLogs = manager.getAccessLogs();
            assertNotNull(accessLogs.getAccessLogs().get(0));
            assertNotNull(accessLogs.getAccessLogs().get(1));
            assertNotNull(accessLogs.getAccessLogs().get(2));
            assertEquals(id1, accessLogs.getAccessLogs().get(0).getUser().getID());
            assertEquals("2024-09-29 14:30:08", accessLogs.getAccessLogs().get(0).getDate());
            assertEquals("Successful Login", accessLogs.getAccessLogs().get(0).getDesc());
            assertEquals(id2, accessLogs.getAccessLogs().get(1).getUser().getID());
            assertEquals("2024-09-30 12:28:58", accessLogs.getAccessLogs().get(1).getDate());
            assertEquals("Successful Login", accessLogs.getAccessLogs().get(1).getDesc());
            assertEquals(id3, accessLogs.getAccessLogs().get(2).getUser().getID());
            assertEquals("2024-09-28 13:31:42", accessLogs.getAccessLogs().get(2).getDate());
            assertEquals("Successful Login", accessLogs.getAccessLogs().get(1).getDesc());
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given that newly created users and access logs are added to the database, when getAccessLogs() method is called with filters, then an arraylist of filtered AccessLog objects should be returned with details corresponding to the created users/access logs.
    public void testGetAccessLogsFiltered() {
        try {
            int id1 = -1, id2 = -1, id3 = -1;
            manager.addCustomer("test1@mail.com", "test1", "Test1", "Test2", "0411111111");
            ResultSet rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                id1 = rs.getInt(1);
            }
            manager.addAccessLog(id1, "2024-09-29 14:30:08", "Successful Login");
            manager.addCustomer("test2@mail.com", "test2", "Test3", "Test4", "0422222222");
            rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                id2 = rs.getInt(1);
            }
            manager.addAccessLog(id2,"2024-09-30 12:28:58","Successful Login");
            manager.addStaff("test3@mail.com", "test3", "Test5", "Test6", "0433333333");
            rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                id3 = rs.getInt(1);
            }
            manager.addAccessLog(id3,"2024-09-28 13:31:42","Successful Login");
            
            AccessLogs accessLogs = manager.getAccessLogs("test1", "2024-09-29 12:28:58", "");
            assertNotNull(accessLogs.getAccessLogs().get(0));
            assertEquals(id1, accessLogs.getAccessLogs().get(0).getUser().getID());
            assertEquals("2024-09-29 14:30:08", accessLogs.getAccessLogs().get(0).getDate());
            assertEquals("Successful Login", accessLogs.getAccessLogs().get(0).getDesc());
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given that the Products table is set up, when addProduct() method is called, then the product with associated details should be added to Products table.
    public void testAddProduct() {
        try {
            manager.addProduct("product", "product desc", new BigDecimal("1.50"));
            ResultSet rs = conn.prepareStatement("SELECT * FROM Products").executeQuery();
            if (rs.next()) {
                assertEquals("product", rs.getString("name"));
                assertEquals("product desc", rs.getString("desc"));
                assertEquals(new BigDecimal("1.50"), rs.getBigDecimal("price"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }

    @Test //Given that a newly created staff is added to the users table, when addProductLog() method is called, then the product log with correct details should be in the ProductLogs table.
    public void testAddProductLog() {
        try {
            int staffID = -1;
            int productID = -1;
            manager.addStaff("test1@mail.com", "test1", "Test1", "Test2", "0411111111");
            ResultSet rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                staffID = rs.getInt(1);
            }
            manager.addProduct("product", "product desc", new BigDecimal(1.50));
            rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                productID = rs.getInt(1);
            }
            manager.addProductLog(staffID, "2024-09-29 14:30:08", productID, "Changed X");
            rs = conn.prepareStatement("SELECT * FROM productlogs").executeQuery();
            if (rs.next()) {
                assertEquals(staffID, rs.getInt("staff_id"));
                assertEquals("2024-09-29 14:30:08", rs.getString("date"));
                assertEquals(productID, rs.getInt("product_id"));
                assertEquals("Changed X", rs.getString("desc"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Test //Given that newly created staff, products and product logs are added to the database, when getProductLogs() method is called with filters, then an arraylist of filtered ProductLog objects should be returned with details corresponding to the created staff/product logs.
    public void testGetProductLogsFiltered() {
        try {
            int staffID1 = -1, staffID2 = -1, staffID3 = -1, productID1 = -1, productID2 = -1, productID3 = -1;
            manager.addStaff("test1@mail.com", "test1", "Test1", "Test2", "0411111111");
            ResultSet rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                staffID1 = rs.getInt(1);
            }
            manager.addProduct("product1", "desc1", new BigDecimal("1.50"));
            rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                productID1 = rs.getInt(1);
            }
            manager.addProductLog(staffID1, "2024-09-29 14:30:08", productID1, "Change X");
            manager.addStaff("test2@mail.com", "test2", "Test3", "Test4", "0422222222");
            rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                staffID2 = rs.getInt(1);
            }
            manager.addProduct("product2", "desc2", new BigDecimal("2.50"));
            rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                productID2 = rs.getInt(1);
            }
            manager.addProductLog(staffID2,"2024-09-30 12:28:58", productID2, "Change Y");
            manager.addStaff("test3@mail.com", "test3", "Test5", "Test6", "0433333333");
            rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                staffID3 = rs.getInt(1);
            }
            manager.addProduct("product3", "desc3", new BigDecimal("2.00"));
            rs = conn.prepareStatement("SELECT last_insert_id()").executeQuery();
            if (rs.next()) {
                productID3 = rs.getInt(1);
            }
            manager.addProductLog(staffID3,"2024-09-28 13:31:42", productID3,"Change X");
            manager.addProductLog(staffID1, "2024-09-30 13:31:42", productID3, "Change X Change Y");
            
            ProductLogs productLogs = manager.getProductLogs("test1", "product3", "2024-09-29 12:28:58", "");
            assertNotNull(productLogs.getProductLogs().get(0));
            assertEquals(staffID1, productLogs.getProductLogs().get(0).getStaff().getID());
            assertEquals("2024-09-30 13:31:42", productLogs.getProductLogs().get(0).getDate());
            assertEquals(productID3, productLogs.getProductLogs().get(0).getProduct().getID());
            assertEquals("Change X Change Y", productLogs.getProductLogs().get(0).getDesc());
        }
        catch (SQLException ex) {
            Logger.getLogger(DBManagerTest.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }
}
