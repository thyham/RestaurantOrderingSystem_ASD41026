package com.uts.restaurant.controller;

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

public class DeleteUserServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            manager.deleteUser(id);
        }
        catch (SQLException ex) {
            Logger.getLogger(DeleteUserServlet.class.getName()).log(Level.SEVERE, null, ex);    
        }

        request.getRequestDispatcher("view-users").include(request, response);
    }
}
