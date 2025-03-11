/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.admin.controller;

import dal.DishDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Dish;

/**
 *
 * @author Asus
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class updateServlet extends HttpServlet {

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
            out.println("<title>Servlet updateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateServlet at " + request.getContextPath() + "</h1>");
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
            int id = Integer.parseInt(request.getParameter("dish_id"));

            // get a person from DB
            DishDAO dishDAO = new DishDAO();
            Dish dish = dishDAO.getDish(id);
            request.setAttribute("dish", dish);

            // get list address from DB
            request.getRequestDispatcher("admin.jsp").forward(request, response);
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

        try {
            int id = Integer.parseInt(request.getParameter("dish_idd"));
            DishDAO dishDAO = new DishDAO();
            Dish dish = dishDAO.getDish(id);
            request.setAttribute("dish", dish);
            String name = (String) request.getParameter("namee");
            String infor = (String) request.getParameter("inforr");
            int price = Integer.parseInt(request.getParameter("pricee"));
            Part part = null; // Khởi tạo biến part là null
            String image = ""; // Khởi tạo biến image trống

            Part filePart = request.getPart("fileInput");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            if (!fileName.isEmpty()) {
                // Nếu có tệp được chọn, thực hiện đoạn mã sau
                part = filePart;
                String realPath = request.getServletContext().getRealPath("/img");
                image = fileName;
                part.write(realPath + "/" + image);
            } else {
                // Nếu không có tệp nào được chọn, sử dụng giá trị mặc định (tên ảnh cũ)
                image = dish.getImage();
            }
            String comment = (String) request.getParameter("commentt");
            String typeOfDish = (String) request.getParameter("typeOfDishh");
            if (!image.isEmpty()) {
                dishDAO.update(id, name, infor, price, image, comment, typeOfDish);
            } else {
                // Sử dụng tên ảnh cũ nếu không có tệp mới
                dishDAO.update(id, name, infor, price, dish.getImage(), comment, typeOfDish);
            }

            response.sendRedirect("ListProductServlet");
//        request.getRequestDispatcher("list.jsp").forward(request, response);
        } catch (Exception ex) {
            String errorMessage = "Lỗi xảy ra khi thực hiện hành động: " + ex.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("error.jsp");
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