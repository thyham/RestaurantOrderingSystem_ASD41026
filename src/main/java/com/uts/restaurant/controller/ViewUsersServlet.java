package com.uts.restaurant.controller;

import com.uts.restaurant.model.dao.DBManager;
import com.uts.restaurant.model.Users;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewUsersServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        Users users = null;
        try {
            users = manager.getUsers();
        }
        catch (SQLException ex) {           
            Logger.getLogger(ViewUsersServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        session.setAttribute("users", users);
        request.getRequestDispatcher("view-users.jsp").include(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        Users users = null;
        try {
            users = manager.getUsers();
        }
        catch (SQLException ex) {           
            Logger.getLogger(ViewUsersServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        session.setAttribute("users", users);
        request.getRequestDispatcher("view-users.jsp").include(request, response);
    }
}
