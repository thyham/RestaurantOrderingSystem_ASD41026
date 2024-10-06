package com.uts.restaurant.controller;

import com.uts.restaurant.model.dao.DBManager;
import com.uts.restaurant.model.OrderItems;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewOrderItemsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int orderID;
        if (request.getParameter("orderID") != null) {
            orderID = Integer.valueOf(request.getParameter("orderID"));
        }
        else {
            orderID = (int) session.getAttribute("orderID");
        }
        String productFilter = request.getParameter("productFilter");
        session.setAttribute("emailFilter", productFilter);
        session.setAttribute("orderID", orderID);
        DBManager manager = (DBManager) session.getAttribute("manager");
        OrderItems orderItems = null;
        try {
            orderItems = manager.getOrderItems(orderID, productFilter);
        }
        catch (SQLException ex) {           
            Logger.getLogger(ViewOrderItemsServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        session.setAttribute("orderItems", orderItems);
        request.getRequestDispatcher("view-order-items.jsp").include(request, response);
    }
}
