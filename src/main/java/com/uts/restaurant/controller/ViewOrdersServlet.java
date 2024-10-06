package com.uts.restaurant.controller;

import com.uts.restaurant.model.Orders;
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

public class ViewOrdersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailFilter = request.getParameter("emailFilter");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        session.setAttribute("emailFilter", emailFilter);
        session.setAttribute("fromDate", fromDate);
        session.setAttribute("toDate", toDate);
        DBManager manager = (DBManager) session.getAttribute("manager");
        Orders orders = null;
        try {
            orders = manager.getOrders(emailFilter, fromDate, toDate);
        }
        catch (SQLException ex) {           
            Logger.getLogger(ViewOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        session.setAttribute("orders", orders);
        request.getRequestDispatcher("view-orders.jsp").include(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailFilter = (String) session.getAttribute("emailFilter");
        String fromDate = (String) session.getAttribute("fromDate");
        String toDate = (String) session.getAttribute("toDate");
        DBManager manager = (DBManager) session.getAttribute("manager");
        Orders orders = null;
        try {
            orders = manager.getOrders(emailFilter, fromDate, toDate);
        }
        catch (SQLException ex) {           
            Logger.getLogger(ViewOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        session.setAttribute("orders", orders);
        request.getRequestDispatcher("view-orders.jsp").include(request, response);
    }
}
