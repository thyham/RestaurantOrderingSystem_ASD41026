package com.uts.restaurant.controller;

import com.uts.restaurant.model.User;
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

public class ViewUserServlet extends HttpServlet {

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("emailErr", "");
        session.setAttribute("passwordErr", "");
        session.setAttribute("fnameErr", "");
        session.setAttribute("surnameErr", "");
        session.setAttribute("phoneErr", "");
        int id = Integer.valueOf(request.getParameter("id"));
        String type = request.getParameter("type");
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        User user = null;

        try {
            user = manager.getUser(id);
        }
        catch (SQLException ex) {           
            Logger.getLogger(ViewUserServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
    
        session.setAttribute("type", type);
        session.setAttribute("target-user", user);
        request.getRequestDispatcher("view-user.jsp").include(request, response);
    }
}
