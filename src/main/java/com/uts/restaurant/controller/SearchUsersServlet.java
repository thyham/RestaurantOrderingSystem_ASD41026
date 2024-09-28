package com.uts.restaurant.controller;

import com.uts.restaurant.model.Users;
import com.uts.restaurant.model.dao.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchUsersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailFilter = request.getParameter("emailFilter");
        String phoneNoFilter = request.getParameter("phoneNoFilter");
        session.setAttribute("emailFilter", emailFilter);
        session.setAttribute("phoneNoFilter", phoneNoFilter);
        DBManager manager = (DBManager) session.getAttribute("manager");
        Users users = null;
        try {
            users = manager.getUsers(emailFilter, phoneNoFilter);
        }
        catch (SQLException ex) {           
            Logger.getLogger(SearchUsersServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        session.setAttribute("users", users);
        request.getRequestDispatcher("view-users.jsp").include(request, response);
    }
    
}
