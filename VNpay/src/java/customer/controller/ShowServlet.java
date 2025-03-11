/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.customer.controller;

import com.entity.Cart;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class ShowServlet extends HttpServlet {

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
            out.println("<title>Servlet ShowServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowServlet at " + request.getContextPath() + "</h1>");
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
        // Lấy dữ liệu từ cookie và giải mã ở phía servlet
        try {
            HttpSession session = request.getSession();
            Cookie[] cookies = request.getCookies();
            String customer_id = null;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("customer_idd".equals(cookie.getName())) {
                        customer_id = cookie.getValue();
                        break;
                    }
                }
            }

            String encodedCartJson = null;
            String encodedAdminCartJson = null;

            for (Cookie cookie : cookies) {
                if (("cart123_" + customer_id).equals(cookie.getName())) {
                    encodedCartJson = cookie.getValue();

                } else if ("adminCart123".equals(cookie.getName())) {
                    encodedAdminCartJson = cookie.getValue();
                }
            }

            // Giải mã chuỗi JSON từ cookie
            String cartJson = new String(Base64.getDecoder().decode(encodedCartJson), "UTF-8");
            String adminCartJson = new String(Base64.getDecoder().decode(encodedAdminCartJson), "UTF-8");

            // Chuyển đổi chuỗi JSON thành đối tượng Cart bằng Gson
            Gson gson = new Gson();
            Cart cart = gson.fromJson(cartJson, Cart.class);
            Cart adminCart = gson.fromJson(adminCartJson, Cart.class);

            // Đặt cart và adminCart vào request attributes
            session.setAttribute("cart6", cart);
            session.setAttribute("adminCart", adminCart);

            // Chuyển hướng người dùng đến trang new.jsp để hiển thị thông tin
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace(); // Print the stack trace to the server logs for debugging

            String errorMessage = ex.getMessage();
            request.setAttribute("errorMessage", errorMessage);

            // Forward to an error page or handle the error in an appropriate way
            request.getRequestDispatcher("error.jsp").forward(request, response);

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
        processRequest(request, response);
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
