/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.admin.controller;

import com.entity.Cart;
import dal.CustomerDAO;
import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author Asus
 */
public class ConfirmOrderServlet extends HttpServlet {

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
            out.println("<title>Servlet ConfirmOrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmOrderServlet at " + request.getContextPath() + "</h1>");
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
            response.setCharacterEncoding("UTF-8");
            OrderDAO orderDAO = new OrderDAO();
            int order_id = Integer.parseInt(request.getParameter("order_id"));
            int value = Integer.parseInt(request.getParameter("viewButton"));

            int store_id = Integer.parseInt(request.getParameter("store_id"));

            if (value == 1) {

                //----------send bill to email--------------
                List<OrderDetail> list = orderDAO.getItem(order_id);
                int total = orderDAO.getOrderById(order_id).getTotalmoney();
                int customer_id = orderDAO.getOrderById(order_id).getCustomer_id();
                CustomerDAO customerDAO = new CustomerDAO();
                String email = customerDAO.getCustomer(customer_id).getEmail();
                Customer customer = customerDAO.getCustomer(customer_id);
                String username = customer.getUsername();
                String address = customer.getAddress();
                String storePhone = "086868686";

                RequestDispatcher dispatcher = null;

                HttpSession mySession = request.getSession();

                // sending bill
                String to = email;// change accordingly

                // Get the session object
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("anhhoangduc145@gmail.com", "wubnvlrqqzhmwjho");// Put your email
                        // password here
                    }
                });
                // compose message
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(email));// change accordingly
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject("Xác nhận Đơn Hàng Thành Công và Thông Tin Chi Tiết Đơn Hàng Của Bạn");

                    String emailContent = " <html><body>";
                    emailContent += "<p>Chào " + username + ",</p>";
                    emailContent += "<p>Chúng tôi xin trân trọng thông báo rằng đơn đặt hàng của bạn đã được xác nhận thành công. Chúng tôi đang xử lý đơn hàng của bạn và sẽ gửi thông tin cập nhật về tình trạng đơn hàng của bạn trong thời gian sớm nhất.</p>";

// Bắt đầu bảng thông tin đơn hàng
                    for (OrderDetail item : list) {
                        emailContent += "<p>Sản phẩm :" + item.getName() + "</p>";
                        emailContent += "<p>Số lượng :" + item.getQuantity() + " </p>";
                        emailContent += "<p>Giá tiền :" + item.getPrice() + " đ </p>";
                        emailContent += "<br>";
                    }
                    emailContent += "<p>-------------------------</p>";
                    emailContent += "<p>Tổng tiền :" + total + " đ </p>";

// Thêm các phần còn lại của email
                    emailContent += "<p>Nếu bạn có bất kỳ thắc mắc hoặc câu hỏi nào, vui lòng liên hệ với chúng tôi qua địa chỉ email 420fastfood@gmail.com hoặc số điện thoại " + storePhone + ".</p>";
                    emailContent += "<p>Chúng tôi xin chân thành cảm ơn sự ủng hộ của bạn và hy vọng bạn sẽ hài lòng với trải nghiệm mua sắm tại cửa hàng của chúng tôi.</p>";
                    emailContent += "<p>Trân trọng,</p>";
                    emailContent += "<p>FastFood Store</p>";

                    emailContent += "</body></html>";
                    message.setContent(emailContent, "text/html; charset=UTF-8");

                    // send message
                    Transport.send(message);
                    System.out.println("message sent successfully");
                } catch (MessagingException ex) {
                    String errorMessage = ex.getMessage();

                    // Đặt thông báo lỗi vào request
                    request.setAttribute("errorMessage", errorMessage + "loi1");

                    // Chuyển hướng người dùng đến trang error.jsp
//                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    throw new RuntimeException(ex);
                }
                orderDAO.resetStatus(order_id, "Preparing");
                //------------------------------------------
            } else if (value == 2) {
                orderDAO.resetStatus(order_id, "Delivering");
//                List<Order> listOrder = orderDAO.getOrderByStoreIdAndStatus(order_id, "Succeed");
//                request.setAttribute("listOrder", listOrder);
                response.sendRedirect("ShowConfirmOrder?store_id=" + store_id);
            } else if (value == 3) {
                orderDAO.resetStatus(order_id, "Succeed");
                orderDAO.resetStatusPaymentStatus(order_id, "Paid");
//                List<Order> listOrder = orderDAO.getOrderByStoreIdAndStatus(order_id, "Succeed");
//                request.setAttribute("listOrder", listOrder);
                response.sendRedirect("ShowConfirmOrder?store_id=" + store_id);

            } else {
                orderDAO.resetStatus(order_id, "Canceled");
                List<Order> listOrder = orderDAO.getOrderByStoreIdAndStatus(order_id, "Succeed");
                request.setAttribute("listOrder", listOrder);
                response.sendRedirect("ShowConfirmOrder?store_id=" + store_id);
            }

            List<Order> list = orderDAO.getOrderByStatus("Pending");
            int sum = orderDAO.sumOrderByStore(store_id);
            request.setAttribute("pendingList", list);
            response.sendRedirect("ShowConfirmOrder?store_id=" + store_id);
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage + "loi2");

            // Chuyển hướng người dùng đến trang error.jsp
//            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(ConfirmOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        HttpSession session = request.getSession();
        Cart cart1 = (Cart) session.getAttribute("adminCart");
        session.setAttribute("adminCart", cart1);

        request.getRequestDispatcher("confirmOrder.jsp").forward(request, response);

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
