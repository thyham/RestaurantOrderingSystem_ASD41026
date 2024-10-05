package com.uts.restaurant.controller;

import com.uts.restaurant.model.dao.DBManager;
import com.uts.restaurant.model.ProductLogs;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewProductLogsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailFilter = request.getParameter("emailFilter");
        String productFilter = request.getParameter("productFilter");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        session.setAttribute("emailFilter", emailFilter);
        session.setAttribute("productFilter", productFilter);
        session.setAttribute("fromDate", fromDate);
        session.setAttribute("toDate", toDate);
        DBManager manager = (DBManager) session.getAttribute("manager");
        ProductLogs productLogs = null;
        try {
            productLogs = manager.getProductLogs(emailFilter, productFilter, fromDate, toDate);
        }
        catch (SQLException ex) {           
            Logger.getLogger(ViewProductLogsServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        session.setAttribute("productLogs", productLogs);
        request.getRequestDispatcher("view-product-logs.jsp").include(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailFilter = (String) session.getAttribute("emailFilter");
        String productFilter = (String) session.getAttribute("productFilter");
        String fromDate = (String) session.getAttribute("fromDate");
        String toDate = (String) session.getAttribute("toDate");
        DBManager manager = (DBManager) session.getAttribute("manager");
        ProductLogs productLogs = null;
        try {
            productLogs = manager.getProductLogs(emailFilter, productFilter, fromDate, toDate);
        }
        catch (SQLException ex) {           
            Logger.getLogger(ViewProductLogsServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        session.setAttribute("productLogs", productLogs);
        request.getRequestDispatcher("view-product-logs.jsp").include(request, response);
    }
}
