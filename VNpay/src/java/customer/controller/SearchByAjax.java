/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.customer.controller;

import dal.DishDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dish;

/**
 *
 * @author Asus
 */
public class SearchByAjax extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            String search = request.getParameter("txt");
            DishDAO dishDAO = new DishDAO();
            List<Dish> listDish = dishDAO.searchByName(search);
            PrintWriter out = response.getWriter();
            Cookie[] cookies = request.getCookies();
            int customer_id = 0;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("customer_idd")) {
                        customer_id = Integer.parseInt(cookie.getValue());
                    }
                }
            }
            for (Dish dish : listDish) {
                out.println(" <div class=\"col-lg-4 menu-item\" >\n"
                        + "                                        <a href=\"img/" + dish.getImage() + "\" class=\"glightbox\"><img src=\"img/" + dish.getImage() + "\" class=\"menu-img img-fluid\" alt=\"\"></a>\n"
                        + "                                        <h4><a href=\"DetailProduct?pid=" + dish.getDish_id() + "\" title=\"View Product\">" + dish.getName() + "</a></h4>\n"
                        + "                                        <p class=\"ingredients\">\n"
                        + "                                            " + dish.getInfor() + "\n"
                        + "                                        </p>\n"
                        + "                                        <p class=\"price\" >\n"
                        + "                                            " + dish.getPrice() + "đ\n"
                        + "                                        </p>           \n"
                        + "                                    </div><!-- Menu Item -->");

            }
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage);

            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(SearchByAjax.class.getName()).log(Level.SEVERE, null, ex);
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
//        try {
//            String searchTxt = request.getParameter("txt");
//            DishDAO dishDAO = new DishDAO();
//            List<Dish> list = dishDAO.searchByName(searchTxt);
//            request.setAttribute("list", list);
//            request.getRequestDispatcher("viewProduct.jsp").forward(request, response);
//        } catch (Exception ex) {
//            Logger.getLogger(SearchByAjax.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
