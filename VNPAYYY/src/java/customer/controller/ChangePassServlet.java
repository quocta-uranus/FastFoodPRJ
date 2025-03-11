/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.customer.controller;

import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */
public class ChangePassServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangePassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            int customer_id = Integer.parseInt(request.getParameter("customer_id"));
            String username = customerDAO.getCustomer(customer_id).getUsername();
            String oldpass = request.getParameter("oldPassword").trim();
            String newpass = request.getParameter("newPassword").trim();
            String re_newpass = request.getParameter("re_newPassword").trim();
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedPassword = encoder.encodeToString(oldpass.getBytes()).trim();
            String passInDb = customerDAO.getCustomer(customer_id).getPassword().trim();

            if (encodedPassword.equals(passInDb)) {
                if (re_newpass.equals(newpass)) {
                    String encodedPassword1 = encoder.encodeToString(newpass.getBytes());
                    customerDAO.editPassword(customer_id, encodedPassword1);
                    response.sendRedirect("Profile?acc=" + username);
                } else 
                {
                    request.setAttribute("errorMessage", "Mật khẩu mới không khớp. Vui lòng nhập lại.");

                request.getRequestDispatcher("Profile?acc=" + username).forward(request, response);
                }

            } else {
                request.setAttribute("errorMessage", "Mật khẩu cũ không đúng. Vui lòng nhập lại.");

                request.getRequestDispatcher("Profile?acc=" + username).forward(request, response);
            }
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            request.setAttribute("errorMessage", errorMessage + "loi2");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(ChangePassServlet.class.getName()).log(Level.SEVERE, null, ex);
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
