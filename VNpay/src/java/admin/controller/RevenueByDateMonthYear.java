/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package java.admin.controller;

import dal.OrderDAO;
import dal.StoreDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DailyTotal;
import model.MonthCount;
import model.MonthlyTotal;
import model.Order;
import model.Store;
import model.YearCount;

/**
 *
 * @author Asus
 */
public class RevenueByDateMonthYear extends HttpServlet {

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
            out.println("<title>Servlet RevenueByDateMonthYear</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RevenueByDateMonthYear at " + request.getContextPath() + "</h1>");
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
            OrderDAO orderDAO = new OrderDAO();
            int select = Integer.parseInt(request.getParameter("select"));
            //--------------------list date -----------------------
            List<String> listDate = new ArrayList<>();
            LocalDate startDate = LocalDate.of(2021, 1, 1);
            LocalDate currentDate = LocalDate.now();

            for (LocalDate date = currentDate; !date.isBefore(startDate); date = date.minusDays(1)) {
                listDate.add(String.valueOf(date));
            }
            //----------------------list month---------------------
            List<Integer> listMonth = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                listMonth.add(i);
            }
            //------------------list year -----------------
            List<Integer> listYear = new ArrayList<>();
            for (int i = 2023; i >= 2020; i--) {
                listYear.add(i);
            }
            if (select == 1) {

                LocalDate currDate = LocalDate.now();
                String date = currDate.toString();
                List<Order> listOrder = orderDAO.getOrderByDate(date);
                int total = listOrder.size();
                request.setAttribute("total", total);
                int sum = orderDAO.sumOrderByDate(date);
                StoreDAO storeDAO = new StoreDAO();
                List<Store> listStore = storeDAO.totalPriceOfStoreByDate(date);
                request.setAttribute("listStore", listStore);
                request.setAttribute("listOrder", listOrder);
                request.setAttribute("listDate", listDate);
                request.setAttribute("date", date);
                request.setAttribute("sum", sum);
                request.getRequestDispatcher("statisticByDate.jsp").forward(request, response);
            } else if (select == 2) {

                Date date = new Date();
                SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
                SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MM");
                int year = Integer.parseInt(dateFormatYear.format(date));
                int month = Integer.parseInt(dateFormatMonth.format(date));

                List<Order> listOrder = orderDAO.getOrderByMonth(month, year);
                int sum = orderDAO.sumOrderByMonth(month, year);
                request.setAttribute("listMonth", listMonth);
                request.setAttribute("listYear", listYear);
                request.setAttribute("month", month);
                request.setAttribute("year", year);
                int total = listOrder.size();
                request.setAttribute("total", total);
                List<DailyTotal> listDaily = orderDAO.sumOfDayByMonth(year, month);
                request.setAttribute("listDaily", listDaily);
                request.setAttribute("listOrder", listOrder);
                List<MonthCount> listCount = orderDAO.getNumberOfOrderByMonth(year, month);
                request.setAttribute("listCount", listCount);
                StoreDAO storeDAO = new StoreDAO();
                List<Store> listStore = storeDAO.totalPriceOfStoreByMonth(year, month);
                request.setAttribute("listStore", listStore);
                request.setAttribute("sum", sum);
                request.getRequestDispatcher("statisticByMonth.jsp").forward(request, response);
            } else {
                Date date = new Date();
                SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
                int year = Integer.parseInt(dateFormatYear.format(date));
                List<MonthlyTotal> listTotal = orderDAO.sumOfMonthByYear(year);

                StoreDAO storeDAO = new StoreDAO();
                List<Order> listOrder = orderDAO.getOrderByYear(year);
                List<Store> listStore = storeDAO.totalPriceOfStoreByYear(year);
                int sum = orderDAO.sumOrderByYear(year);
                request.setAttribute("listYear", listYear);
                int total = listOrder.size();
                request.setAttribute("year", year);
                List<YearCount> countList = orderDAO.getNumberOfOrderByYear(year);
                request.setAttribute("countList", countList);
                request.setAttribute("total", total);

                request.setAttribute("listOrder", listOrder);
                request.setAttribute("listStore", listStore);
                request.setAttribute("sum", sum);
                request.setAttribute("listTotal", listTotal);
                request.getRequestDispatcher("statisticByYear.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage + "loi2");

            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(RevenueByDateMonthYear.class.getName()).log(Level.SEVERE, null, ex);
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
            //--------------------list date--------------------
            List<String> listDate = new ArrayList<>();
            LocalDate startDate = LocalDate.of(2021, 1, 1);
            LocalDate currentDate = LocalDate.now();

            for (LocalDate date = currentDate; !date.isBefore(startDate); date = date.minusDays(1)) {
                listDate.add(String.valueOf(date));
            }
            //----------------list month-----------------
            List<Integer> listMonth = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                listMonth.add(i);
            }
            //------------------list year -----------------
            List<Integer> listYear = new ArrayList<>();
            for (int i = 2023; i >= 2020; i--) {
                listYear.add(i);
            }
            int option = Integer.parseInt(request.getParameter("type"));
            if (option == 1) {
                String date = request.getParameter("date");

                OrderDAO orderDAO = new OrderDAO();
                List<Order> listOrder = orderDAO.getOrderByDate(date);
                int total = listOrder.size();
                request.setAttribute("total", total);
                int sum = orderDAO.sumOrderByDate(date);
                StoreDAO storeDAO = new StoreDAO();
                List<Store> listStore = storeDAO.totalPriceOfStoreByDate(date);
                request.setAttribute("listStore", listStore);
                request.setAttribute("listOrder", listOrder);
                request.setAttribute("listDate", listDate);
                request.setAttribute("date", date);
                request.setAttribute("sum", sum);
                request.getRequestDispatcher("statisticByDate.jsp").forward(request, response);
            } else if (option == 2) {
                OrderDAO orderDAO = new OrderDAO();
                int month = Integer.parseInt(request.getParameter("month"));
                int year = Integer.parseInt(request.getParameter("year"));
                List<Order> listOrder = orderDAO.getOrderByMonth(month, year);
                int sum = orderDAO.sumOrderByMonth(month, year);
                request.setAttribute("listMonth", listMonth);
                request.setAttribute("listYear", listYear);
                request.setAttribute("month", month);
                request.setAttribute("year", year);
                int total = listOrder.size();
                request.setAttribute("total", total);
                List<MonthCount> listCount = orderDAO.getNumberOfOrderByMonth(year, month);
                request.setAttribute("listCount", listCount);
                List<DailyTotal> listDaily = orderDAO.sumOfDayByMonth(year, month);
                request.setAttribute("listDaily", listDaily);
                request.setAttribute("listOrder", listOrder);
                StoreDAO storeDAO = new StoreDAO();
                List<Store> listStore = storeDAO.totalPriceOfStoreByMonth(year, month);
                request.setAttribute("listStore", listStore);
                request.setAttribute("sum", sum);
                request.getRequestDispatcher("statisticByMonth.jsp").forward(request, response);
            } else {
                int year = Integer.parseInt(request.getParameter("year"));
                OrderDAO orderDAO = new OrderDAO();
                StoreDAO storeDAO = new StoreDAO();
                List<Order> listOrder = orderDAO.getOrderByYear(year);
                List<Store> listStore = storeDAO.totalPriceOfStoreByYear(year);
                int sum = orderDAO.sumOrderByYear(year);
                List<MonthlyTotal> listTotal = orderDAO.sumOfMonthByYear(year);
                request.setAttribute("listTotal", listTotal);
                request.setAttribute("listYear", listYear);
                List<YearCount> countList = orderDAO.getNumberOfOrderByYear(year);
                request.setAttribute("listOrder", listOrder);
                int total = listOrder.size();
                request.setAttribute("year", year);
                request.setAttribute("total", total);
                request.setAttribute("listStore", listStore);
                request.setAttribute("sum", sum);
                request.setAttribute("countList", countList);
                request.getRequestDispatcher("statisticByYear.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", errorMessage + "loi2");

            // Chuyển hướng người dùng đến trang error.jsp
            request.getRequestDispatcher("error.jsp").forward(request, response);
            Logger.getLogger(RevenueByDateMonthYear.class.getName()).log(Level.SEVERE, null, ex);
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
