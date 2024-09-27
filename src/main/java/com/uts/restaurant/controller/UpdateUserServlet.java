package com.uts.restaurant.controller;

import com.uts.restaurant.model.dao.DBManager;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.valueOf(request.getParameter("id"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fname = request.getParameter("fname");
        String surname = request.getParameter("surname");
        String phoneNo = request.getParameter("phoneNo");
        int isActive = Boolean.parseBoolean(request.getParameter("status")) ? 1 : 0;
        DBManager manager = (DBManager) session.getAttribute("manager");

        try {
            Boolean valid = true;
            if (!Utils.validateEmail(email)) {
                session.setAttribute("emailErr", "Invalid email! Please enter a valid email with format '(prefix)@(domain)'.");
                valid = false;   
            }
            if (!Utils.validateName(fname)) {
                session.setAttribute("fnameErr", "Invalid first name! Please enter a valid first name (letters only).");
                valid = false;   
            }
            if (!Utils.validateName(surname)) {
                session.setAttribute("surnameErr", "Invalid surname! Please enter a valid surname (letters only).");
                valid = false;   
            }
            if (!Utils.validatePhoneNo(phoneNo)) {
                session.setAttribute("phoneErr", "Invalid phone number! Please enter a valid phone number beginning with '04' followed by 8 digits.");
                valid = false;
            }
            if (valid) {
                manager.updateUserFromAdmin(id, email, password, fname, surname, phoneNo, isActive);
                request.getRequestDispatcher("view-users").include(request, response);
            }
            else {
                request.getRequestDispatcher("view-user.jsp").include(request, response);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(UpdateUserServlet.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }
}
