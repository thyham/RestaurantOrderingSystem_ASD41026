package com.uts.restaurant.controller;

import com.uts.restaurant.model.dao.DBManager;
import com.uts.restaurant.model.AccessLogs;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewAccessLogsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailFilter = request.getParameter("emailFilter");
        String fromDate = request.getParameter("fromDate");
        if(fromDate.isEmpty()){
            fromDate = "2000-01-01 00:00:00";
            session.setAttribute("fromDate", "");
        }
        else {
            session.setAttribute("fromDate", fromDate);
        }
        String toDate = request.getParameter("toDate");
        if(toDate.isEmpty()){
            toDate = "3000-01-01 23:59:59";
            session.setAttribute("toDate", "");
        }
        else {
            session.setAttribute("toDate", toDate);
        }
        session.setAttribute("emailFilter", emailFilter);
        DBManager manager = (DBManager) session.getAttribute("manager");
        AccessLogs accessLogs = null;
        try {
            accessLogs = manager.getAccessLogs(emailFilter, fromDate, toDate);
        }
        catch (SQLException ex) {           
            Logger.getLogger(ViewAccessLogsServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        session.setAttribute("accessLogs", accessLogs);
        request.getRequestDispatcher("view-access-logs.jsp").include(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailFilter = (String) session.getAttribute("emailFilter");
        if (emailFilter == null) {
            emailFilter = "";
        }
        String fromDate = (String) session.getAttribute("fromDate");
        if (fromDate == null || fromDate.isEmpty()) {
            fromDate = "2000-01-01 00:00:00";
        }
        String toDate = (String) session.getAttribute("toDate");
        if (toDate == null || toDate.isEmpty()) {
            toDate = "3000-01-01 23:59:59";
        }
        DBManager manager = (DBManager) session.getAttribute("manager");
        AccessLogs accessLogs = null;
        try {
            accessLogs = manager.getAccessLogs(emailFilter, fromDate, toDate);
        }
        catch (SQLException ex) {           
            Logger.getLogger(ViewAccessLogsServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        session.setAttribute("accessLogs", accessLogs);
        request.getRequestDispatcher("view-access-logs.jsp").include(request, response);
    }
}
