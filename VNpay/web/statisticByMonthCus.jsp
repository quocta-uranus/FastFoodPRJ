<%-- 
    Document   : statisticByDate
    Created on : Oct 19, 2023, 12:24:46 AM
    Author     : Asus
--%>

<%@page import="dal.StoreDAO"%>
<%@page import="dal.OrderDAO"%>
<%@page import="model.Order"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FastFood</title>
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="vendor/aos/aos.css" rel="stylesheet">
        <!--  <link href="vendor/glightbox/css/glightbox.min.css" rel="stylesheet">-->
        <link href="vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <!-- Template Main CSS File -->
        <link href="css/styleguest.css" rel="stylesheet">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/5.3.45/css/materialdesignicons.css" integrity="sha256-NAxhqDvtY0l4xn+YVa6WjAcmd94NNfttjNsDmNatFVc=" crossorigin="anonymous" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            .totall{
                margin-bottom: 30px;
                text-align: center;
                font-weight: 500;
                font-family: revert;
                color: maroon;
            }
            .modal{
                position: fixed;
                z-index: 1;
                top:0;
                left: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0,0,0,0.4);
                background-clip: padding-box;
            }
            .modal-content{
                text-align: center;
                padding: 40px;
                position: absolute;
                top: 30% !important;
                left: 35% !important;
                background-color: #fefefe;
                margin: 2% auto;
                border: 1px solid #888;
                width: 30%;
                width: 480px;
                height: auto;
                border-radius: 4px;
                animation: slideDown 0.5s forwards;
            }
            .btnlogout{
                margin: auto;
                gap: 20px;
            }
        </style>
    </head>

    <body>

        <header id="header" class="header fixed-top d-flex align-items-center" >
            <div class="container d-flex align-items-center justify-content-between">

                <a href="index.html" class="logo d-flex align-items-center me-auto me-lg-0">
                    <!-- Uncomment the line below if you also wish to use an image logo -->
                    <!-- <img src="assets/img/logo.png" alt=""> -->
                    <img class="img-navbar" src="img/logo.jpg" alt="">
                </a>

                <nav id="navbar" class="navbar">
                    <ul>
                        <li><a href="ListProductCustomer#hero">Home</a></li>
                        <li><a href="ListProductCustomer#about">About</a></li>
                        <li><a href="ListProductCustomer#menu">Menu</a></li>
                        <li><a href="ListProductCustomer#contact">Contact</a></li>
                    </ul>
                </nav><!-- .navbar -->
                <div> 
                    <a href="Cart.jsp"><i class="fa-solid fa-cart-shopping fa-bounce fa-2xl" style="color: #ff0000;"></i></a>
                    <a class="btn-book-a-table" href="Profile?acc=<%=session.getAttribute("account")%>">
                        <span> <%=session.getAttribute("account")%></span>

                    </a>
                    <a href="#" id="logout" onclick="logout()"> <i class="fa-solid fa-right-from-bracket fa-2xl" style="color: #ff0000; margin-left: 20px;"></i></a>
                </div>
                <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
                <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

            </div>


        </header><!-- End Header -->
        <div class="modal" id="myModal">
            <div class="modal-content" style="width: 30%;">
                <h5 style=" margin-bottom: 20px;">Are you sure you want to log out?</h5>
                <div class="d-flex btnlogout">
                    <button onclick="yes()" type="button" class="btn btn-warning">Yes</button>
                    <button onclick="no()" type="button" class="btn btn-success">No</button>
                </div>
            </div>

        </div>
        <div style="margin:100px 0 150px 0;">
            <div style="text-align: center;">
                <h2 style=" font-weight: bold;">Thống kê theo tháng</h2>
                <%
                    List<Integer> listMonth = (List) request.getAttribute("listMonth");
                    List<Integer> listYear = (List) request.getAttribute("listYear");
                    List<Order> listOrder = (List) request.getAttribute("listOrder");
                    int sum = (int) request.getAttribute("sum");
                %>
                <%
                    Cookie[] cookies = request.getCookies();
                    int customer_id = 0;
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("customer_idd")) {
                                customer_id = Integer.parseInt(cookie.getValue());

                            }
                        }
                    }
                    OrderDAO orderDAO = new OrderDAO();
                    StoreDAO storeDAO = new StoreDAO();
                %>
                <button class="btn btn-secondary mb-4">  <a style="
                                                            color: white; text-decoration: none;" href="OrderHistoryServlet?customer_id=<%=customer_id%>">Trở về</a> </button>
                <form action="OrderHistoryByDMY" method="Post"  style="display: flex; width: fit-content; gap:10px;
                      margin: auto;">
                    <select name="month" id="monthSelect" class="form-select" aria-label="Default select example">
                        <c:forEach var="month" items="${listMonth}">
                            <option value="${month}">${month}</option>
                        </c:forEach>
                    </select>

                    <select name="year" id="yearSelect" class="form-select" aria-label="Default select example" style="width:100px;">
                        <c:forEach var="year" items="${listYear}">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="type" value="2" />
                    <input type="hidden" name="customer_id" id="customer_id" value="<%= customer_id%>">
                    <button type="submit" class="btn btn-info">Xem</button>
                </form>
            </div>
            <table class="table mt-4" style="text-align: center;">
                <thead  class="thead-dark">
                      <tr>
                            <th scope="col">Mã đơn hàng</th>
                              <th scope="col">Cửa hàng</th>
                            <th scope="col">Tên người nhận hàng  </th>
                            <th scope="col">Địa chỉ</th>
                            <th scope="col">Giá tiền</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Ngày mua hàng</th>
                            <th scope="col">Hoạt động</th>
                        </tr>    
                </thead>
                <tbody>

                    <c:forEach var="order" items="<%= listOrder%>" >
                        <tr>
                         <td>${order.getOrder_id()}</td>
                            <c:set var="order_id" value="${order.getOrder_id()}"></c:set>
                            <c:set var="store_id" value="${order.getStore_id()}"></c:set>
                            <td><%= storeDAO.getStoreById((int) pageContext.getAttribute("store_id")).getStore_name()%></td>
                            <td><%= orderDAO.getOrderById((int) pageContext.getAttribute("order_id")).getReceiver_name() %></td>
                           <td><%= orderDAO.getOrderById((int) pageContext.getAttribute("order_id")).getReceiver_address()%></td>                       
                            <td>${order.getTotalmoney()}</td>
                            <td>${order.getStatus()}</td>
                            <td>${order.getDate()}</td>
                         <td>
                            <form action="ViewOrderHistoryDetail" method="GET">
                                <input type="hidden" name="order_id" value="${order.getOrder_id()}"/>
                                <button type="submit" class="btn btn-success" > Xem chi tiết </button>
                            </form>
                        </td>
                    </tr>
                    </c:forEach>

                </tbody>
            </table>
            <h4 style="text-align: end;  padding-right: 40px;">Tổng tiền: <%= sum%> đ</h4>
        </div>
        <footer id="footer" class="footer">

            <div class="container">
                <div class="row gy-3">
                    <div class="col-lg-3 col-md-6 d-flex">
                        <i class="bi bi-geo-alt icon"></i>
                        <div>
                            <h4>Address</h4>
                            <p>
                                FPT University <br>
                                Khu đô thị FPT Đà Nẵng<br>
                            </p>
                        </div>

                    </div>

                    <div class="col-lg-3 col-md-6 footer-links d-flex">
                        <i class="bi bi-telephone icon"></i>
                        <div>
                            <h4>Reservations</h4>
                            <p>
                                <strong>Phone:</strong> (+84) 334807725<br>
                                <strong>Email:</strong> haulvdev@gmail.com<br>
                            </p>
                        </div>
                    </div>

                    <div class="col-lg-3 col-md-6 footer-links d-flex">
                        <i class="bi bi-clock icon"></i>
                        <div>
                            <h4>Opening Hours</h4>
                            <p>
                                <strong>Mon-Sat: 11AM</strong> - 23PM<br>
                                Sunday: Closed
                            </p>
                        </div>
                    </div>

                    <div class="col-lg-3 col-md-6 footer-links">
                        <h4>Follow Us</h4>
                        <div class="social-links d-flex">
                            <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                            <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
                            <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
                            <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
                        </div>
                    </div>

                </div>
            </div>

            <div class="container">
                <div class="copyright">
                    &copy; Copyright <strong><span>420ent</span></strong>. All Rights Reserved
                </div>
                <div class="credits">
                    <!-- All the links in the footer should remain intact. -->
                    <!-- You can delete the links only if you purchased the pro version. -->
                    <!-- Licensing information: https://bootstrapmade.com/license/ -->
                    <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/yummy-bootstrap-restaurant-website-template/ -->
                </div>
            </div>

        </footer>
        <script>
            function logout() {
                document.getElementById("myModal").style.display = "block";
            }
            function no() {
                document.getElementById("myModal").style.display = "none";
            }
            function yes() {

                window.location.href = "ListProductGuest";
            }
            document.addEventListener("DOMContentLoaded", function () {
                var monthSelect = document.getElementById("monthSelect");
                var yearSelect = document.getElementById("yearSelect");
                // Xem nếu đã có tháng và năm đã lưu trong Local Storage
                var savedMonth = localStorage.getItem("selectedMonth");
                var savedYear = localStorage.getItem("selectedYear");
                // Nếu có, thiết lập giá trị tháng và năm đã chọn
                if (savedMonth) {
                    monthSelect.value = savedMonth;
                }
                if (savedYear) {
                    yearSelect.value = savedYear;
                }

                // Lắng nghe sự kiện thay đổi tháng và năm và cập nhật giá trị trong Local Storage
                monthSelect.addEventListener("change", function () {
                    localStorage.setItem("selectedMonth", monthSelect.value);
                });
                yearSelect.addEventListener("change", function () {
                    localStorage.setItem("selectedYear", yearSelect.value);
                });
            });
        </script>
        <!-- Vendor JS Files -->
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/aos/aos.js"></script>
        <script src="vendor/glightbox/js/glightbox.min.js"></script>
        <script src="vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="vendor/swiper/swiper-bundle.min.js"></script>
        <script src="vendor/php-email-form/validate.js"></script>
    </body>
</html>
