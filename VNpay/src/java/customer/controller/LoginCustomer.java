/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.customer.controller;

import dal.AdminDAO;
import dal.CustomerDAO;
import dal.OrderDAO;
import dal.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import model.Customer;
import model.Order;
import model.Staff;

/**
 *
 * @author Asus
 */
public class LoginCustomer extends HttpServlet {

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
            out.println("<title>Servlet LoginCustomer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginCustomer at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("login.jsp");
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

            String userInput = request.getParameter("username");
            String passInput = request.getParameter("password");
            CustomerDAO customerDAO = new CustomerDAO();
            AdminDAO adminDAO = new AdminDAO();
            Admin admin = adminDAO.getAdmin(userInput, passInput);
            Base64.Encoder encoder = Base64.getEncoder();
            String encodePass = encoder.encodeToString(passInput.getBytes());
            Customer customer = customerDAO.getCustomer(userInput, encodePass);
            StaffDAO staffDAO = new StaffDAO();
            Staff staff = staffDAO.getStaff(userInput, passInput);
            String role = "";
            if (customer != null) {
                // neu la customer thi chuyen toi trang hone.jsp
                int customerId;
                customerId = customer.getCustomer_id();
                HttpSession session = request.getSession();
                Customer c = customerDAO.getCustomer(userInput, passInput);
                session.setAttribute("account", c);
                Cookie customerIdCookie = new Cookie("customer_idd", String.valueOf(customerId));
                role = "c";
                // Đặt thời gian sống của cookie, ví dụ: 30 ngày
                customerIdCookie.setMaxAge(30 * 24 * 60 * 60);
                request.setAttribute("role", role);
                // Thêm cookie vào HTTP response
                response.addCookie(customerIdCookie);
                session.setAttribute("account", userInput);
                response.sendRedirect("ListProductCustomer");
            } else if (admin != null) {
                role = "a";
                request.setAttribute("role", role);

                response.sendRedirect("ListProductServlet");
            } else if (staff != null) {
                int store_id = staff.getStore_id();

                Cookie store_idCookie = new Cookie("store_id", String.valueOf(store_id));
                role = "s";

                // Đặt thời gian sống của cookie, ví dụ: 30 ngày
                store_idCookie.setMaxAge(30 * 24 * 60 * 60);
                request.setAttribute("role", role);
                // Thêm cookie vào HTTP response
                response.addCookie(store_idCookie);
                request.setAttribute("store_id", store_id);
                
                OrderDAO orderDAO = new OrderDAO();
                List<Order> listOrder = orderDAO.getOrderByStoreId(store_id);
                int num = listOrder.size();
                
                request.setAttribute("num", num);
                
//                request.getRequestDispatcher("manageStore.jsp").forward(request, response);
                response.sendRedirect("manageStore.jsp");
            } else {
                request.setAttribute("tbsubmit", "Tài khoản hoặc mật khẩu không đúng");
                // return back login.jsp using Servlet (method GET)
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            response.sendRedirect("error.jsp");
            Logger.getLogger(LoginCustomer.class.getName()).log(Level.SEVERE, null, ex);
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
