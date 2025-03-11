/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.customer.controller;

import com.entity.Cart;
import com.google.gson.Gson;
import dal.CustomerDAO;
import dal.OrderDAO;
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
import model.Customer;

/**
 *
 * @author Asus
 */
public class CheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServlet at " + request.getContextPath() + "</h1>");
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
            HttpSession session = request.getSession();
            int paymentMethod = Integer.parseInt(request.getParameter("cod"));
            int customer_id = Integer.parseInt(request.getParameter("customer_id"));
            String receiver_name = request.getParameter("name");
            String phone = request.getParameter("phone");

            String address = request.getParameter("address");
            // Lấy giá trị phone và address từ request

            Cookie phoneCookie = new Cookie("phonee", phone);
            phoneCookie.setMaxAge(24 * 60 * 60); // Thời gian sống là 24 giờ
            response.addCookie(phoneCookie);
            Cookie addressCookie = new Cookie("addresss", address);
            session.setAttribute("name", receiver_name);
            addressCookie.setMaxAge(24 * 60 * 60);

            response.addCookie(addressCookie);
            String paymentStatus = request.getParameter("pStatus");
            String pStatus = "";
            if (paymentStatus.equals("N")) {
                pStatus = "Not yet";
            } else {
                pStatus = "Paid";
            }
            Cookie[] cookies = request.getCookies();
            String encodedCartJson = null;

            for (Cookie cookie : cookies) {
                if (("cart123_" + customer_id).equals(cookie.getName())) {
                    encodedCartJson = cookie.getValue();
                    break; // Tìm thấy cookie, thoát khỏi vòng lặp
                }
            }
            String cartJson = new String(Base64.getDecoder().decode(encodedCartJson), "UTF-8");
            Gson gson = new Gson();
            Cart cart = gson.fromJson(cartJson, Cart.class);

            if (encodedCartJson != null) {
                // 1. Lưu thông tin đơn hàng vào cơ sở dữ liệu
                if (paymentMethod == 1) {
                    if (address != null) {
                        address = address.replace("|", ", ");
                    }

                    CustomerDAO customerDAO = new CustomerDAO();
                    String store_selected = request.getParameter("store_id");
                    int store_id = Integer.parseInt(store_selected);
                    session.setAttribute("store_id", store_id);
                    Customer c = customerDAO.getCustomer(customer_id);
                    if (c == null) {
                        response.sendRedirect("login.jsp");
                    } else {
                        try {
                            OrderDAO orderDAO = new OrderDAO();
                            orderDAO.addOrder(c, cart, store_id, receiver_name, phone, address, pStatus);
                            session.removeAttribute("cart6");
                            Cookie cartCookie = null;
                            for (Cookie cookie : cookies) {
                                if (("cart123_" + customer_id).equals(cookie.getName())) {
                                    cartCookie = cookie;
                                    break;
                                }
                            }
                            if (cartCookie != null) {
                                cartCookie.setMaxAge(0);
                                response.addCookie(cartCookie);

                            }
                            Cart cart1 = new Cart();
                            session.setAttribute("cart_" + customer_id, cart1);
                            session.setAttribute("cart6", cart1);
                            response.sendRedirect("successOder.jsp");
                        } catch (Exception ex) {
                            String errorMessage = ex.getMessage();

                            // Đặt thông báo lỗi vào request
                            request.setAttribute("errorMessage", errorMessage + "loi1");

                            // Chuyển hướng người dùng đến trang error.jsp
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    String store_selected = request.getParameter("store_id");
                    int store_id = Integer.parseInt(store_selected);
                    session.setAttribute("store_id", store_id);
                    response.sendRedirect("vnpay_pay.jsp");
                }

            }

        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage + "loi2");

            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);

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
            HttpSession session = request.getSession();
            int paymentMethod = Integer.parseInt(request.getParameter("cod"));
            String receiver_name = request.getParameter("name");
            int customer_id = Integer.parseInt(request.getParameter("customer_id"));
            String phone = request.getParameter("phone");
            //   String customer_name = request.getParameter("customer_name");
            String address = request.getParameter("address");
            // Lấy giá trị phone và address từ request

            Cookie phoneCookie = new Cookie("phonee", phone);
            phoneCookie.setMaxAge(24 * 60 * 60); // Thời gian sống là 24 giờ
            response.addCookie(phoneCookie);
            Cookie addressCookie = new Cookie("addresss", address);

            addressCookie.setMaxAge(24 * 60 * 60);

            response.addCookie(addressCookie);

            String paymentStatus = request.getParameter("pStatus");
            String pStatus = "";
            if (paymentStatus.equals("N")) {
                pStatus = "Not yet";
            } else {
                pStatus = "Paid";
            }
            Cookie[] cookies = request.getCookies();
            String encodedCartJson = null;
            response.sendRedirect("successOder.jsp");
            for (Cookie cookie : cookies) {
                if (("cart123_" + customer_id).equals(cookie.getName())) {
                    encodedCartJson = cookie.getValue();
                    break; // Tìm thấy cookie, thoát khỏi vòng lặp
                }
            }
            String cartJson = new String(Base64.getDecoder().decode(encodedCartJson), "UTF-8");
            Gson gson = new Gson();
            Cart cart = gson.fromJson(cartJson, Cart.class);

            if (encodedCartJson != null) {
                // 1. Lưu thông tin đơn hàng vào cơ sở dữ liệu
                if (paymentMethod == 1) {

                    CustomerDAO customerDAO = new CustomerDAO();
                    String store_selected = request.getParameter("store_id");
                    int store_id = Integer.parseInt(store_selected);
                    session.setAttribute("store_id", store_id);
                    Customer c = customerDAO.getCustomer(customer_id);
                    if (c == null) {
                        response.sendRedirect("login.jsp");
                    } else {
                        try {
                            OrderDAO orderDAO = new OrderDAO();
                            orderDAO.addOrder(c, cart, store_id, receiver_name, phone, address, pStatus);
                            session.removeAttribute("cart6");
                            Cookie cartCookie = null;
                            for (Cookie cookie : cookies) {
                                if (("cart123_" + customer_id).equals(cookie.getName())) {
                                    cartCookie = cookie;
                                    break;
                                }
                            }
                            if (cartCookie != null) {
                                cartCookie.setMaxAge(0);
                                response.addCookie(cartCookie);

                            }
                            Cart cart1 = new Cart();
                            session.setAttribute("cart_" + customer_id, cart1);
                            session.setAttribute("cart6", cart1);

                            response.sendRedirect("successOder.jsp");
                        } catch (Exception ex) {
                            String errorMessage = ex.getMessage();

                            // Đặt thông báo lỗi vào request
                            request.setAttribute("errorMessage", errorMessage + "loi1");

                            // Chuyển hướng người dùng đến trang error.jsp
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    session.setAttribute("address", address);
                    String store_selected = request.getParameter("store_id");
                    int store_id = Integer.parseInt(store_selected);
                    session.setAttribute("store_id", store_id);
                    request.getRequestDispatcher("vnpay_pay.jsp").forward(request, response);
                }

            }

        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage + "loi2");

            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);

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
