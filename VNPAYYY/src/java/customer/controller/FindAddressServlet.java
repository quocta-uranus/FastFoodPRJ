/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.customer.controller;

import dal.StoreDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Store;

/**
 *
 * @author Asus
 */
public class FindAddressServlet extends HttpServlet {

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
            out.println("<title>Servlet FindAddressServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FindAddressServlet at " + request.getContextPath() + "</h1>");
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
            String city = String.valueOf(request.getParameter("address1"));
            String district = String.valueOf(request.getParameter("address2"));

            String street = String.valueOf(request.getParameter("address3"));
            int customter_id = Integer.parseInt(request.getParameter("customer_id"));
            String name = String.valueOf(request.getParameter("name"));
            String phoneNumber = String.valueOf(request.getParameter("phone"));

            StoreDAO storeDAO = new StoreDAO();
            List<Store> storeList = storeDAO.getAll();
            List<Store> recommendList = new ArrayList<>();
            List<Store> hcList = new ArrayList<>();
            List<Store> tkList = new ArrayList<>();
            if (city.equals("48")) {
                city = "Thành phố Đà Nẵng";
            }
            if (district.equals("490")) {
                district = "Quận Liên Chiểu";
            } else if (district.equals("491")) {
                district = "Quận Thanh Khê";
            } else if (district.equals("492")) {
                district = "Quận Hải Châu";
            } else if (district.equals("493")) {
                district = "Quận Sơn Trà";
            } else if (district.equals("494")) {
                district = "Quận Ngũ Hành Sơn";
            } else if (district.equals("495")) {
                district = "Quận Cẩm Lệ";
            } else if (district.equals("497")) {
                district = "Huyện Hòa Vang";
            } else if (district.equals("498")) {
                district = "Huyện Hoàng Sa";
            }

            for (Store store : storeList) {
                if (store.getAddress().contains("Quận Hải Châu")) {
                    hcList.add(store);
                }
                if (store.getAddress().contains("Quận Thanh Khê")) {
                    tkList.add(store);
                }
            }
            for (Store s : storeList) {
                if (city.equals("Thành phố Đà Nẵng")) {

                    switch (district) {
                        case "Quận Hải Châu":

                            if (s.getAddress().contains(street)) {
                                recommendList.add(s);
                            } else {
                                for (Store store1 : hcList) {
                                    recommendList.add(store1);
                                }
                            }
                            break;
                        case "Quận Thanh Khê":
                            if (s.getAddress().contains(street)) {
                                recommendList.add(s);
                            } else {
                                for (Store store1 : tkList) {
                                    recommendList.add(store1);
                                }
                            }
                            break;
                        case "Quận Cẩm Lệ":
                            for (Store store1 : hcList) {
                                recommendList.add(store1);
                            }
                            break;
                        case "Quận Liên Chiểu":
                            for (Store store1 : tkList) {
                                recommendList.add(store1);
                            }
                            break;
                        case "Quận Ngũ Hành Sơn":
                            for (Store store1 : hcList) {
                                recommendList.add(store1);
                            }
                            break;
                        case "Quận Sơn Trà":
                            for (Store store1 : hcList) {
                                recommendList.add(store1);
                            }
                            break;
                        case "Huyện Hòa Vang":
                            for (Store store1 : tkList) {
                                recommendList.add(store1);
                            }
                            break;
                        case "Huyện Hoàng Sa":
                            for (Store store1 : hcList) {
                                recommendList.add(store1);
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                }
            }
            request.setAttribute("name", name);
            request.setAttribute("customer_id", customter_id);
            request.setAttribute("phone", phoneNumber);
            request.setAttribute("district", district);
            request.setAttribute("street", street);
            request.setAttribute("city", city);
           
            request.setAttribute("recommendList", recommendList);
            request.getRequestDispatcher("storeList.jsp").forward(request, response);

        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage + "loi2");

            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(FindAddressServlet.class.getName()).log(Level.SEVERE, null, ex);
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
