/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.admin.controller;

import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author Asus
 */
public class StatisticByDateDetail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StatisticByDateDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticByDateDetail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            OrderDAO orderDAO = new OrderDAO();
            int store_id = Integer.parseInt(request.getParameter("store_id"));
            String date = request.getParameter("date");
            request.setAttribute("date", date);
            request.setAttribute("store_id", store_id);
            int totalMoney = orderDAO.sumOrderOfStoreByDate(date, store_id);
            request.setAttribute("totalMoney", totalMoney);
            List<Order> listOrder = orderDAO.getOrderOfStoreByDate(date, store_id);
            request.setAttribute("listOrder", listOrder);
            request.getRequestDispatcher("statisticByDateDetail.jsp").forward(request, response);
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage + "loi2");

            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(StatisticByDateDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            OrderDAO orderDAO = new OrderDAO();
            int order_id = Integer.parseInt(request.getParameter("order_id"));
            String date = request.getParameter("date");
            request.setAttribute("date", date);
            List<OrderDetail> listDetail = orderDAO.getItemById(order_id);
            request.setAttribute("listDetail", listDetail);
            request.getRequestDispatcher("statisticByDateDetail1.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(StatisticByDateDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
