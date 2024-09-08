package com.uts.restaurant.model.dao;

import java.sql.*;

public class DBManager {
    Connection conn;

    public DBManager(Connection conn) throws SQLException {   
        this.conn = conn;    
    }

    public void temp() throws SQLException {
    }
}