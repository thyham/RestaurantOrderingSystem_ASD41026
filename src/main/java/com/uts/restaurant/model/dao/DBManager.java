package com.uts.restaurant.model.dao;

import com.uts.restaurant.model.AccessLog;
import com.uts.restaurant.model.AccessLogs;
import com.uts.restaurant.model.Customer;
import com.uts.restaurant.model.Product;
import com.uts.restaurant.model.ProductLog;
import com.uts.restaurant.model.ProductLogs;
import com.uts.restaurant.model.Staff;
import com.uts.restaurant.model.User;
import com.uts.restaurant.model.Users;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


public class DBManager {
    Connection conn;

    public DBManager(Connection conn) throws SQLException {   
        this.conn = conn;    
    }

    public void temp() throws SQLException {
    }

    public boolean checkUser(String email) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        return (rs.next());
    }

    public boolean checkUser(String email, String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE email=? AND password=?");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return (rs.next());
    }

    public boolean checkCustomer(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Customers WHERE customer_id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return (rs.next());
    }

    public boolean checkStaff(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Staff WHERE staff_id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return (rs.next());
    }

    private void addUser(String email, String password, String fname, String surname, String phoneNo) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Users (email, password, fname, surname, phoneNo, isactive) VALUES (?, ?, ?, ?, ?, ?)");
        ps.setString(1, email);
        ps.setString(2, password);
        ps.setString(3, fname);
        ps.setString(4, surname);
        ps.setString(5, phoneNo);
        ps.setInt(6, (int)1);
        ps.executeUpdate();
    }

    public void addCustomer(String email, String password, String fname, String surname, String phoneNo) throws SQLException {   
        addUser(email, password, fname, surname, phoneNo);
        ResultSet rs = conn.prepareStatement("select LAST_INSERT_ID()").executeQuery();
        if (rs.next()) {
            int id = rs.getInt(1);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Customers (customer_id) VALUES (?)");
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void addStaff(String email, String password, String fname, String surname, String phoneNo) throws SQLException {   
        addUser(email, password, fname, surname, phoneNo);
        ResultSet rs = conn.prepareStatement("select LAST_INSERT_ID()").executeQuery();
        if (rs.next()) {
            int id = rs.getInt(1);
            PreparedStatement ps2 = conn.prepareStatement("INSERT INTO Staff (staff_id) VALUES (?)");
            ps2.setInt(1, id);
            ps2.executeUpdate();
        }
    }

    public User getUser(int id) throws SQLException { 
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE user_id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            String email = rs.getString("email");
            String fname = rs.getString("fname");
            String surname = rs.getString("surname");
            String phoneNo = rs.getString("phoneno");
            Boolean isActive = rs.getBoolean("isactive");
            if (checkCustomer(id)) {
                return new Customer(id, fname, surname, email, phoneNo, isActive);
            }
            else {
                return new Staff(id, fname, surname, email, phoneNo, isActive);
            }
        }
        return null;
    }

    public Users getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        ResultSet rs = conn.prepareStatement("SELECT * FROM Users ORDER BY user_id").executeQuery();
        while (rs.next()) {
            int id = rs.getInt("user_id");
            String email = rs.getString("email");
            String fname = rs.getString("fname");
            String surname = rs.getString("surname");
            String phoneNo = rs.getString("phoneno");
            Boolean isActive = rs.getBoolean("isactive");
            if (checkCustomer(id)) {
                users.add(new Customer(id, fname, surname, email, phoneNo, isActive));
            }
            else {
                users.add(new Staff(id, fname, surname, email, phoneNo, isActive));
            }
            
        }
        return new Users(users);
    }

    public void updateUserFromAdmin(int id, String email, String password, String fname, String surname, String phoneNo, int isActive) throws SQLException {
        if (!password.equals("")) {
            PreparedStatement ps = conn.prepareStatement("UPDATE Users SET email=?, password=?, fname=?, surname=?, phoneno=?, isactive=? WHERE user_id=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, fname);
            ps.setString(4, surname);
            ps.setString(5, phoneNo);
            ps.setInt(6, isActive);
            ps.setInt(7, id);
            ps.executeUpdate();
        }
        else {
            PreparedStatement ps = conn.prepareStatement("UPDATE Users SET email=?, fname=?, surname=?, phoneno=?, isactive=? WHERE user_id=?");
            ps.setString(1, email);
            ps.setString(2, fname);
            ps.setString(3, surname);
            ps.setString(4, phoneNo);
            ps.setInt(5, isActive);
            ps.setInt(6, id);
            ps.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException{ 
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE user_id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public Users getUsers(String emailFilter, String phoneNoFilter) throws SQLException {
        if (emailFilter == null) {
            emailFilter = "";
        }
        if (phoneNoFilter == null) {
            phoneNoFilter = "";
        }
        ArrayList<User> users = new ArrayList<User>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE email LIKE ? AND phoneno LIKE ? ORDER BY user_id");
        ps.setString(1, emailFilter + "%");
        ps.setString(2, phoneNoFilter + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("user_id");
            String email = rs.getString("email");
            String fname = rs.getString("fname");
            String surname = rs.getString("surname");
            String phoneNo = rs.getString("phoneno");
            Boolean isActive = rs.getBoolean("isactive");
            if (checkCustomer(id)) {
                users.add(new Customer(id, fname, surname, email, phoneNo, isActive));
            }
            else {
                users.add(new Staff(id, fname, surname, email, phoneNo, isActive));
            }
        }
        return new Users(users);
    }

    public void addAccessLog(int id, String date, String desc) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO AccessLogs (user_id, `date`, `desc`) VALUES (?, ?, ?)");
        ps.setInt(1, id);
        ps.setString(2, date);
        ps.setString(3, desc);
        ps.executeUpdate();
    }

    public AccessLogs getAccessLogs() throws SQLException {
        ArrayList<AccessLog> accessLogs = new ArrayList<AccessLog>();
        ResultSet rs = conn.prepareStatement(
        "SELECT accesslogs.user_id, users.email, accesslogs.date, accesslogs.desc " +
            "FROM accesslogs " +
            "INNER JOIN users ON accesslogs.user_id=users.user_id " +
            "ORDER BY user_id").executeQuery();
        while (rs.next()) {
            int id = rs.getInt("user_id");
            String email = rs.getString("email");
            String date = rs.getString("date");
            String desc = rs.getString("desc");
            if (checkCustomer(id)) {
                accessLogs.add(new AccessLog(new Customer(id, email), date, desc));
            }
            else {
                accessLogs.add(new AccessLog(new Staff(id, email), date, desc));
            }
            
        }
        return new AccessLogs(accessLogs);
    }

    public AccessLogs getAccessLogs(String emailFilter, String fromDate, String toDate) throws SQLException {
        if (fromDate == null || fromDate.isEmpty()) {
            fromDate = "2000-01-01 00:00:00";
        }
        if (toDate == null || toDate.isEmpty()) {
            toDate = "3000-01-01 23:59:59";
        }
        ArrayList<AccessLog> accessLogs = new ArrayList<AccessLog>();
        PreparedStatement ps = conn.prepareStatement(
        "SELECT accesslogs.user_id, users.email, accesslogs.date, accesslogs.desc " +
            "FROM accesslogs " +
            "INNER JOIN users ON accesslogs.user_id=users.user_id " +
            "WHERE users.email LIKE ? AND ? <= date AND date <= ? " +
            "ORDER BY user_id");
        ps.setString(1, emailFilter + "%");
        ps.setString(2, fromDate);
        ps.setString(3, toDate);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("user_id");
            String email = rs.getString("email");
            String date = rs.getString("date");
            String desc = rs.getString("desc");
            if (checkCustomer(id)) {
                accessLogs.add(new AccessLog(new Customer(id, email), date, desc));
            }
            else {
                accessLogs.add(new AccessLog(new Staff(id, email), date, desc));
            }
            
        }
        return new AccessLogs(accessLogs);
    }

    public void addProduct(String name, String desc, BigDecimal price) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Products (`name`, `desc`, `price`) VALUES (?, ?, ?)");
        ps.setString(1, name);
        ps.setString(2, desc);
        ps.setBigDecimal(3, price);
        ps.executeUpdate();
    }

    public void addProductLog(int staffID, String date, int productID, String desc) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO ProductLogs (staff_id, `date`, product_id, `desc`) VALUES (?, ?, ?, ?)");
        ps.setInt(1, staffID);
        ps.setString(2, date);
        ps.setInt(3, productID);
        ps.setString(4, desc);
        ps.executeUpdate();
    }

    public ProductLogs getProductLogs(String emailFilter, String productFilter, String fromDate, String toDate) throws SQLException {
        if (fromDate == null || fromDate.isEmpty()) {
            fromDate = "2000-01-01 00:00:00";
        }
        if (toDate == null || toDate.isEmpty()) {
            toDate = "3000-01-01 23:59:59";
        }
        ArrayList<ProductLog> productLogs = new ArrayList<ProductLog>();
        PreparedStatement ps = conn.prepareStatement(
        "SELECT productlogs.staff_id, users.email, productlogs.`date`, products.product_id, products.`name`, productlogs.desc " +
        "FROM productlogs " +
            "INNER JOIN users ON productlogs.staff_id=users.user_id " +
            "INNER JOIN products ON productlogs.product_id=products.product_id " +
            "WHERE users.email LIKE ? AND products.`name` LIKE ? AND ? <= date AND date <= ? " +
            "ORDER BY staff_id");
        ps.setString(1, emailFilter + "%");
        ps.setString(2, productFilter + "%");
        ps.setString(3, fromDate);
        ps.setString(4, toDate);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int staffID = rs.getInt("staff_id");
            String email = rs.getString("email");
            String date = rs.getString("date");
            int productID = rs.getInt("product_id");
            String productName = rs.getString("name");
            String desc = rs.getString("desc");
            productLogs.add(new ProductLog(new Staff(staffID, email), date, new Product(productID, productName), desc));
            
        }
        return new ProductLogs(productLogs);
    }
}