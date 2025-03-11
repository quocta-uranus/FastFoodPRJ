<%-- 
    Document   : viewDetailOrder
    Created on : Oct 14, 2023, 1:24:49 PM
    Author     : Asus
--%>

<%@page import="java.util.List"%>
<%@page import="dal.OrderDAO"%>
<%@page import="model.OrderDetail"%>
<%@page import="dal.DishDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
        <title>FastFood</title>
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
                        Cookie[] cookies = request.getCookies(); // Get the array of cookies from the request
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
                            <li> <form action="ShowConfirmOrder" method="get">
                                    <input type="hidden" name ="store_id" value="<%= storeId%>" />
                                    <button class="btt" type="submit">Xác nhận đơn hàng</button>
                                </form></li>
                            <li><form action="ShowSucceedOrder" method="get">
                                    <input type="hidden" name ="store_id" value="<%=storeId%>" />
                                    <button class="btt" type="submit">Đơn hàng thành công</button>
                                </form></li>

                            <li> <form action="ShowCanceledOrder" method="get">
                                    <input type="hidden" name ="store_id" value="<%=storeId%>" />
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
                <h1 style=" font-weight: bold;">Chi tiết đơn hàng</h1>
                <button type="submit" class="btn btn-secondary mb-4" >
                    <a href="ShowConfirmOrder?store_id=<%= storeId%>" style=" color: white; text-decoration: none;" >Trở về</a> </button>
            </div>
            <table class="table">
                <thead class="thead-dark">
                    <tr>

                        <th scope="col">Hình ảnh</th>
                        <th scope="col">Tên món ăn</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Giá tiền</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="orderDetail" items="${listDetail}">

                        <tr>

                            <td> <img src="img/${orderDetail.getImage()}" alt="anh" width="100px"/> </td>

                            <td style=" vertical-align: middle;"> ${orderDetail.getName()}</td>
                            <td style=" vertical-align: middle;"> ${orderDetail.getQuantity()}</td>
                            <td style=" vertical-align: middle;"> ${orderDetail.getPrice()} đ</td>

                        </c:forEach>


                        <% List<OrderDetail> list = (List) request.getAttribute("listDetail");
                            int order_id = list.get(0).getOrder_id();
                        %>
                        <% String role = String.valueOf(request.getAttribute("role"));%>






                        </td>
                </tbody>
            </table>
            <div style="display:flex; gap:26px; justify-content: end; margin-right: 50px;">

               


                <% int value = 4;
                    int value1 = 1;
                    OrderDAO orderDAO = new OrderDAO();
                    String status = orderDAO.getOrderById(order_id).getStatus().trim();
                    
                    if (status.equals("Pending")) {


                %>
                 <input type="hidden" name="order_id" value="<%= order_id%>">
                <input type="hidden" name="store_id" value="<%= storeId%>">
                <button class="btn btn-success" type="submit" name="viewButton" value="1" onclick="confirm()">
                    Xác nhận
                </button>
                <input type="hidden" name="order_id" value="<%= order_id%>">
                <input type="hidden" name="store_id" value="<%= storeId%>">
                <button type="submit" name="viewButton" value="2" class="btn btn-danger" onclick="delete1()">
                    Từ chối
                </button>
                <% }%>
              

                <div class="modal" id="modaldelete">
                    <div class="modal-content" style="width: 30%;">
                        <h5 style=" margin-bottom: 20px;">Bạn có chắc chắn muốn từ chối ?</h5>
                        <div class="d-flex btnlogout">
                            <button onclick="yes2(<%=order_id%>,<%=value%>, <%= storeId%>)" type="button" class="btn btn-warning">Yes</button>
                            <button onclick="no2()" type="button" class="btn btn-success">No</button>
                        </div>
                    </div><!-- comment -->

                </div>
                <div class="modal" id="modalconfirm">
                    <div class="modal-content" style="width: 30%;">
                        <h5 style=" margin-bottom: 20px;">Bạn có chắc chắn muốn xác nhận ?</h5>
                        <div class="d-flex btnlogout">
                            <button onclick="yes1(<%=order_id%>,<%= storeId%>,<%=value1%>)" type="button" class="btn btn-warning">Yes</button>
                            <button onclick="no1()" type="button" class="btn btn-success">No</button>
                        </div>
                    </div><!-- comment -->

                </div>

            </div>
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

            </footer><!-- End Footer -->
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="vendor/aos/aos.js"></script>
            <script src="vendor/glightbox/js/glightbox.min.js"></script>
            <script src="vendor/purecounter/purecounter_vanilla.js"></script>
            <script src="vendor/swiper/swiper-bundle.min.js"></script>
            <script src="vendor/php-email-form/validate.js"></script>

            <!-- Template Main JS File -->
            <script src="js/main.js"></script>
            <script> function logout() {
                                    document.getElementById("myModal").style.display = "block";
                                }
                                function no() {
                                    document.getElementById("myModal").style.display = "none";
                                }
                                function yes() {

                                    window.location.href = "ListProductGuest";
                                }
                                function delete1() {
                                    var modal = document.getElementById("modaldelete");
                                    modal.style.display = "block";
                                }

                                function yes2(order_id, value, store_id) {
                                    window.location.href = "ConfirmOrderServlet?order_id=" + order_id + "&viewButton=" + value + "&store_id=" + store_id;
                                }

                                //khac me chi o tren dau ma k duoc ta dcm
                                function no2() {
                                    var modal = document.getElementById("modaldelete");
                                    modal.style.display = "none";
                                }
                                function confirm() {
                                    var modal = document.getElementById("modalconfirm");
                                    modal.style.display = "block";
                                }

                                function yes1(order_id, store_id, value) {
                                    window.location.href = "ConfirmOrderServlet?order_id=" + order_id + "&viewButton=" + value + "&store_id=" + store_id;
                                }

                                //khac me chi o tren dau ma k duoc ta dcm
                                function no1() {
                                    var modal = document.getElementById("modalconfirm");
                                    modal.style.display = "none";
                                }
            </script>
    </body>
</html>