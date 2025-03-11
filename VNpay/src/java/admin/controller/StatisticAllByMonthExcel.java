/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.admin.controller;

import dal.OrderDAO;
import dal.StoreDAO;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;
import model.Store;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Asus
 */
public class StatisticAllByMonthExcel extends HttpServlet {

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
            out.println("<title>Servlet StatisticAllByMonthExcel</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticAllByMonthExcel at " + request.getContextPath() + "</h1>");
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

            StoreDAO storeDAO = new StoreDAO();
            OrderDAO orderDAO = new OrderDAO();

            // Lấy dữ liệu từ cơ sở dữ liệu
            int month = Integer.parseInt(request.getParameter("month"));
            int year = Integer.parseInt(request.getParameter("year"));
            int sumByStore = orderDAO.sumOrderByMonth(month, year);
            List<Order> listOrder = orderDAO.getOrderByMonth(month, year);
            List<Store> listStore = storeDAO.totalPriceOfStoreByMonth(year, month);

            // Tạo một tệp Excel bằng Apache POI
            try ( XSSFWorkbook workbook = new XSSFWorkbook();  OutputStream out = response.getOutputStream()) {
                XSSFSheet sheet = workbook.createSheet("Doanh_thu_thang_" + month + "_nam_" + year + "_cua_he_thong");

                // Tạo dòng tiêu đề cho thông tin doanh thu
                XSSFRow revenueHeaderRow = sheet.createRow(0);
                revenueHeaderRow.createCell(0).setCellValue("Hệ thống cửa hàng");
                revenueHeaderRow.createCell(1).setCellValue("Tổng số đơn hàng");
                revenueHeaderRow.createCell(2).setCellValue("Tổng doanh thu");

                // Điền thông tin doanh thu vào tệp Excel
                XSSFRow revenueRow = sheet.createRow(1);

                revenueRow.createCell(1).setCellValue(listOrder.size());
                revenueRow.createCell(2).setCellValue(sumByStore);

                // Tạo dòng tiêu đề cho danh sách đơn hàng
                XSSFRow ordersHeaderRow = sheet.createRow(3);
                ordersHeaderRow.createCell(0).setCellValue("Cửa hàng");
                ordersHeaderRow.createCell(1).setCellValue("Địa chỉ cửa hàng");
                ordersHeaderRow.createCell(2).setCellValue("Doanh thu");

                int rowIndex = 2;

                // Điền danh sách đơn hàng vào tệp Excel
                for (Store store : listStore) {
                    XSSFRow orderRow = sheet.createRow(rowIndex);
                    orderRow.createCell(0).setCellValue(store.getStore_name());
                    orderRow.createCell(1).setCellValue(store.getAddress());
                    orderRow.createCell(2).setCellValue(store.getRevenue());

                    rowIndex++;
                }

                // Xuất tệp Excel
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=Doanh_thu_thang_" + month + "_nam_" + year + "_cua_he_thong.xlsx");

                workbook.write(out);
            }
        } catch (Exception ex) {
            Logger.getLogger(StatisticAllByMonthExcel.class.getName()).log(Level.SEVERE, null, ex);

            response.setContentType("text/html");
            response.getWriter().write("Đã xảy ra lỗi trong quá trình tạo tệp Excel.");
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
