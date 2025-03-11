/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.guest.controller;

import customer.controller.ValidateOtp;
import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class ValidateOtpNewAccount extends HttpServlet {

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
            out.println("<title>Servlet ValidateOtpNewAccount</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValidateOtpNewAccount at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int value = (request.getParameter("otp1") != null) ? Integer.parseInt(request.getParameter("otp1")) : 0;
        
        int otp = Integer.parseInt(request.getParameter("otp"));
        
        RequestDispatcher dispatcher = null;
        if (otp != 0) {
            if (value == otp) {
                try {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    Base64.Encoder encoder = Base64.getEncoder();
                    String encodePass = encoder.encodeToString(password.getBytes());
                    
                    String email = request.getParameter("email");
                    String phoneNumber = "";
                    String role = "c";
                    String customer_name = "";
                    String customer_address= "";
                    CustomerDAO customer = new CustomerDAO();
                    customer.add(username, encodePass, email, phoneNumber, role,customer_name,customer_address);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(ValidateOtpNewAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                request.setAttribute("message", "Mã OTP sai !");
                dispatcher = request.getRequestDispatcher("OtpNewAccount.jsp");
                dispatcher.forward(request, response);
                
            }
        } else {
            request.setAttribute("message", "Mã OTP đã hết hạn!");
            dispatcher = request.getRequestDispatcher("OtpNewAccount.jsp");
            dispatcher.forward(request, response);
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
