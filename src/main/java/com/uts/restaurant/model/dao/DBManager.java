package com.uts.restaurant.model.dao;

import java.sql.*;
import java.util.ArrayList;

import com.uts.restaurant.model.Customer;
import com.uts.restaurant.model.Staff;
import com.uts.restaurant.model.User;
import com.uts.restaurant.model.Users;

public class DBManager {
    Connection conn;

    public DBManager(Connection conn) throws SQLException {   
        this.conn = conn;    
    }

    public void temp() throws SQLException {
    }

    public boolean checkCustomer(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Customers WHERE customer_id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return (rs.next());
    }

    public Users getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        ResultSet rs = conn.prepareStatement("SELECT * FROM Users").executeQuery();
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
}