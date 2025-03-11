<%-- 
    Document   : statisticByDate
    Created on : Oct 19, 2023, 12:24:46 AM
    Author     : Asus
--%>

<%@page import="dal.OrderDAO"%>
<%@page import="model.Order"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/apexcharts/3.35.3/apexcharts.min.js"></script>
        <title>FastFood</title>
        <style>
            td {
                padding: 10px; /* Adjust the value as needed */
            }
        </style>
        <style>
            tr {
                margin-bottom: 20px; /* Adjust the value as needed */
            }
            .titlee{
                font-weight: bold;
            }
            table {
                text-align: center;
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
            table {
                text-align: center;
            }
            .charts {
                display: grid;
                gap: 20px;
            }

            .charts-card {
                background-color: #ffffff;
                margin-bottom: 20px;
                padding: 25px;
                box-sizing: border-box;
                -webkit-column-break-inside: avoid;
                border: 1px solid #d2d2d3;
                border-radius: 5px;
                box-shadow: 0 6px 7px -4px rgba(0, 0, 0, 0.2);
            }

            .chart-title {
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 22px;
                font-weight: 600;
            }
            .btt{
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 0 3px;
                font-family: var(--font-secondary);
                font-size: 16px;
                font-weight: 600;
                color: #7f7f90;
                white-space: nowrap;
                transition: 0.3s;
                position: relative;
                text-decoration: none;
                background-color: transparent;
                border: none;
            }
            .btt:hover{
                border-bottom: 3px red solid;
                color: black;
            }
        </style>
    </head>


    <body>
        <%
            Cookie[] cookies = request.getCookies();
            int storeId = 0;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("store_id".equals(cookie.getName())) {
                        String storeIdValue = cookie.getValue();
                        storeId = Integer.parseInt(storeIdValue);

                    }
                }
            }


        %>
        <header id="header" class="header fixed-top d-flex align-items-center" >
            <div class="container d-flex align-items-center justify-content-between">

                <a href="index.html" class="logo d-flex align-items-center me-auto me-lg-0">
                    <!-- Uncomment the line below if you also wish to use an image logo -->
                    <!-- <img src="assets/img/logo.png" alt=""> -->
                    <img class="img-navbar" src="img/logo.jpg" alt="">
                </a>

                <nav id="navbar" class="navbar">
                    <ul>

                        <li> <a href="ShowConfirmOrder?store_id=<%= storeId%>">Xác nhận đơn hàng</a> </li>
                        <li><a href="manageStore.jsp">Xem doanh thu</a></li>
                        <li><form action="ShowSucceedOrder" method="get">
                                <input type="hidden" name="store_id" value="<%= storeId%>"/>
                                <button class="btt" type="submit">Đơn hàng thành công</button>
                            </form></li>

                        <li> <form action="ShowCanceledOrder" method="get">
                                <input type="hidden" name="store_id" value="<%= storeId%>"/>
                                <button class="btt" type="submit">Đơn hàng đã hủy</button>
                            </form></li>


                    </ul>
                </nav><!-- .navbar -->
                <div> 
                    <a href="#" id="logout" onclick="logout()"> <i class="fa-solid fa-right-from-bracket fa-2xl" style="color: #ff0000; margin-left: 20px;"></i></a>
                </div>
                <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
                <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

            </div>


        </header><!-- End Header -->
        <div class="modal" id="myModal">
            <div class="modal-content" style="width: 30%;">
                <h5 style=" margin-bottom: 20px;">Bạn có chắc chắn bạn muốn thoát?</h5>
                <div class="d-flex btnlogout">
                    <button onclick="yes()" type="button" class="btn btn-warning">Yes</button>
                    <button onclick="no()" type="button" class="btn btn-success">No</button>
                </div>
            </div>

        </div>
        <div style="margin:100px 0 150px 0;">
            <div style="text-align: center;">
                <h2 style=" font-weight: bold;">Thống kê theo tháng</h2>
                <button class="btn btn-secondary mb-4">  <a style="
                                                            color: white; text-decoration: none;" href="manageStore.jsp">Trở về</a> </button>
                <form action="StatisticByStoreByMonthExcel" method="GET">
                    <input type="hidden" name="month" value="${month}"/>
                    <input type="hidden" name="year" value="${year}"/>
                    <input type="hidden" name="store_id" value="<%= storeId%>" /><!-- comment -->
                    <button class="btn btn-warning mb-3" type="submit">Xuất ra exel</button>
                </form>
                <%            List<Integer> listMonth = (List) request.getAttribute("listMonth");
                    List<Integer> listYear = (List) request.getAttribute("listYear");
                    List<Order> listOrder = (List) request.getAttribute("listOrder");
                    int sum = (int) request.getAttribute("sum");
                    OrderDAO orderDAO = new OrderDAO();
                %>
                <form action="RevenueByStoreDMY" method="Post" style="display: flex; width: fit-content; gap:10px;
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
                    <input type="hidden" name="store_id" value="<%= storeId%>"/>
                    <button type="submit" class="btn btn-info">Xem</button>
                </form>
            </div>
            <div class="charts container" style="margin-top:20px;">
                <div class="charts-card">
                    <p class="chart-title">Doanh thu và số đơn</p>
                    <div id="area-chart"></div>
                </div>
            </div>
            <table class="table mt-4" style="text-align: center;">
                <thead  class="thead-dark">
                    <tr>
                        <th scope="col">Mã đơn hàng</th>
                        <th scope="col">Tên người nhận hàng  </th>
                        <th scope="col">Địa chỉ</th>
                        <th scope="col">Giá tiền</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Ngày mua hàng</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="order" items="<%= listOrder%>" >
                        <tr>
                            <td>${order.getOrder_id()}</td>
                            <c:set var="order_id" value="${order.getOrder_id()}"></c:set>
                            <td><%= orderDAO.getOrderById((int) pageContext.getAttribute("order_id")).getReceiver_name()%></td>
                            <td><%= orderDAO.getOrderById((int) pageContext.getAttribute("order_id")).getReceiver_address()%></td>                       
                            <td>${order.getTotalmoney()}</td>
                            <td>${order.getStatus()}</td>
                            <td>${order.getDate()}</td>

                        </tr>
                    </c:forEach>

                </tbody>
            </table>
            <h4 style="text-align: end;  padding-right: 40px;">Tổng tiền: <%= sum%> đ</h4>
        </div>
        <div class="chart-revenue d-none">
            <table>
                <thead>
                    <tr>
                        <td>Month</td>
                        <td>Date</td>
                        <td>Total</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="d" items="${listTotal}">
                        <tr>
                            <td>${d.getMonth()}</td>
                            <td class="namee">${d.getDate()}</td>
                            <td class="revenuee">${d.getTotal()}</td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
        <div class="chart-count d-none">
            <table>
                <thead>
                    <tr>
                        <td>Year</td>
                        <td>Month</td>
                        <td>Date</td>
                        <td>Count</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="d" items="${listCount}">
                        <tr>
                            <td>${d.getYear()}</td>
                            <td>${d.getMonth()}</td>
                            <td class="dayy">${d.getDate()}</td>
                            <td class="quan">${d.getCount()}</td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
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
            var storeNames = [];
            var nameElements = document.getElementsByClassName('quan');
            for (var i = 0; i < nameElements.length; i++) {
                storeNames.push(nameElements[i].innerText);
            }
            var day = [];
            var Elements = document.getElementsByClassName('dayy');
            for (var i = 0; i < Elements.length; i++) {
                day.push(Elements[i].innerText);
            }
            var doanhthu = [];
            var dtElements = document.getElementsByClassName('revenuee');
            for (var i = 0; i < dtElements.length; i++) {
                doanhthu.push(dtElements[i].innerText);
            }
            const areaChartOptions = {
                series: [
                    {
                        name: 'Doanh thu',
                        data: doanhthu,
                    },
                    {
                        name: 'Số đơn',
                        data: storeNames,
                    },
                ],
                chart: {
                    height: 350,
                    type: 'area',
                    toolbar: {
                        show: false,
                    },
                },
                colors: ['#4f35a1', '#246dec'],
                dataLabels: {
                    enabled: false,
                },
                stroke: {
                    curve: 'smooth',
                },
                labels: day,
                markers: {
                    size: 0,
                },
                yaxis: [
                    {
                        title: {
                            text: 'Doanh thu',
                        },
                    },
                    {
                        opposite: true,
                        title: {
                            text: 'Số đơn',
                        },
                    },
                ],
                tooltip: {
                    shared: true,
                    intersect: false,
                },
            };

            const areaChart = new ApexCharts(
                    document.querySelector('#area-chart'),
                    areaChartOptions
                    );
            areaChart.render();
        </script>
        <!-- Vendor JS Files -->
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/aos/aos.js"></script>
        <script src="vendor/glightbox/js/glightbox.min.js"></script>
        <script src="vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="vendor/swiper/swiper-bundle.min.js"></script>
        <script src="vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="js/main.js"></script>
    </body>
</html>
