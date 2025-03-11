/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.customer.controller;

import com.entity.Cart;
import com.google.gson.Gson;
import dal.DishDAO;
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
import model.Dish;

/**
 *
 * @author Asus
 */
public class CookieAddToCart extends HttpServlet {

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
            out.println("<title>Servlet CookieAddToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CookieAddToCart at " + request.getContextPath() + "</h1>");
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
            response.setContentType("text/html;charset=UTF-8");
            int dish_id = Integer.parseInt(request.getParameter("dish_id"));
            String action = request.getParameter("action");

            DishDAO dishDAO = new DishDAO();
            Dish dish = dishDAO.getDish(dish_id);
            HttpSession session = request.getSession();
            int customer_id = Integer.parseInt(request.getParameter("customer_id").trim());
            Cart cart = (Cart) session.getAttribute("cart_" + customer_id);
            Cart adminCart = (Cart) session.getAttribute("adminCart");

            if (cart == null) {
                cart = new Cart();
                adminCart = new Cart();
                session.setAttribute("cart_" + customer_id, cart);
                session.setAttribute("adminCart", adminCart);
            }

            if ("increment".equalsIgnoreCase(action)) {
                cart.addItem(dish, 1);
                adminCart.addItem(dish, 1);
            } else if ("decrement".equalsIgnoreCase(action)) {
                cart.decrementItem(dish, 1);
                adminCart.addItem(dish, 1);
            }

            Gson gson = new Gson();
            String cartJson = gson.toJson(cart);
            String adminCartJson = gson.toJson(adminCart);

// Mã hóa chuỗi JSON để sử dụng trong cookie
            String encodedCartJson = Base64.getEncoder().encodeToString(cartJson.getBytes("UTF-8"));
            String encodedAdminCartJson = Base64.getEncoder().encodeToString(adminCartJson.getBytes("UTF-8"));

// Lưu chuỗi JSON đã mã hóa vào cookie
//            String uniqueCookieName = "user_" + customer;
            String cartCookieName = "cart123_" + customer_id;
            Cookie cartCookie = new Cookie(cartCookieName, encodedCartJson);
            Cookie adminCartCookie = new Cookie("adminCart123", encodedAdminCartJson);

// Đặt thời gian tồn tại cho cookie (ví dụ: 1 ngày)
            cartCookie.setMaxAge(86400);
            adminCartCookie.setMaxAge(86400);

// Thêm cookie vào phản hồi
            response.addCookie(cartCookie);
            response.addCookie(adminCartCookie);

// Chuyển hướng người dùng đến trang viewCard.jsp
            response.sendRedirect("ShowServlet");
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            // Đặt thông báo lỗi vào request
            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(CookieAddToCart.class.getName()).log(Level.SEVERE, null, ex);
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
