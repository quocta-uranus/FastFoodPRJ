/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.customer.controller;

import admin.controller.updateServlet;
import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Dish;

/**
 *
 * @author Asus
 */
public class UpdateProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfileServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            String oldpass = request.getParameter("oldPassword");
            int customer_id = Integer.parseInt(request.getParameter("customer_id"));
            String username = customerDAO.getCustomer(customer_id).getUsername();
            String newpass = request.getParameter("newPass");

            Base64.Encoder encoder = Base64.getEncoder();
            String encodedPassword = encoder.encodeToString(oldpass.getBytes());
            String passInDB = customerDAO.getCustomer(customer_id).getPassword();
            if (encodedPassword.equals(passInDB)) {
                String customer_name = request.getParameter("cus_namee");

                String phones = request.getParameter("phones");
                String addresss = request.getParameter("addresss");
                customerDAO.editProfile(customer_id, newpass, phones, addresss, customer_name);
                response.sendRedirect("Profile?acc=" + username);
            } else {
                request.setAttribute("tb", "sai mat khau cu");
                request.getRequestDispatcher("Profile?acc=" + username).forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(updateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        request.setCharacterEncoding("UTF-8");
        try {

            int customer_id = Integer.parseInt(request.getParameter("customer_id"));

            CustomerDAO customerDAO = new CustomerDAO();
            String username = customerDAO.getCustomer(customer_id).getUsername();
            String password = request.getParameter("add").trim();
            String encodedPassword = "";
            String passStored = customerDAO.getCustomer(customer_id).getPassword().trim();
            if (!password.equals(passStored)) {
                Base64.Encoder encoder = Base64.getEncoder();
                encodedPassword = encoder.encodeToString(password.getBytes());
                String phoneNumber = request.getParameter("phone");
                String address = request.getParameter("address");
                String cus_name = request.getParameter("cus_name");
                // So sánh mật khẩu đã cung cấp với mật khẩu lưu trữ

                customerDAO.editProfile(customer_id, encodedPassword, phoneNumber, address, cus_name);
            } else {
                String phoneNumber = request.getParameter("phone");
                String cus_name = request.getParameter("cus_name");
                String address = request.getParameter("address");
                // So sánh mật khẩu đã cung cấp với mật khẩu lưu trữ

                customerDAO.editProfile(customer_id, password, phoneNumber, address, cus_name);
            }

            response.sendRedirect("Profile?acc=" + username);
        } catch (NumberFormatException ex) {
            // Xử lý lỗi số học (không thể chuyển đổi thành số).
            String errorMessage = ex.getMessage();
            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage + "loi2");
            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (SQLException ex) {
            // Xử lý lỗi truy vấn cơ sở dữ liệu.
            String errorMessage = ex.getMessage();
            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage + "loi2");
            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(updateServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            // Xử lý lỗi khác (nếu có).
            String errorMessage = ex.getMessage();
            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage + "loi2");
            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(updateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
