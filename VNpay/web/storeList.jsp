<%-- 
    Document   : storeList
    Created on : Oct 18, 2023, 1:34:24 AM
    Author     : Asus
--%>

<%@page import="model.Store"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FastFood</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
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
        <style>
            .form-label{
                font-weight: bold;
            }
            input{
                margin-bottom: 20px;
            }
            .quantity {
                display: flex;
                align-items: center;
                justify-content: space-between;
                width: 100px;
                height: 30px;
                border: 1px solid #ddd;
                border-radius: 5px;
                overflow: hidden;
            }

            .quantity-input {
                text-align: center;
                width: 50%;
                border: none;
            }

            .minus,
            .plus {
                width: 30px;
                height: 100%;
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
            }

            .minus:hover,
            .plus:hover {
                background-color: red;
            }
            .delete{
                border: none;
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
                top: 30%;
                left: 35%;
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
            .d{
                display: -webkit-box!important;
            }
            .form-label{
                font-weight: bold;
            }
            input{
                margin-bottom: 20px;
            }
            .pay{
                font-weight: bolder;
                font-size: 20px;
                color: red;
            }
            .pay input{
                -ms-transform: scale(2); /* IE 9 */
                -moz-transform: scale(2); /* Firefox */
                -webkit-transform: scale(2); /* Safari and Chrome */
                -o-transform: scale(2); /* Opera */
                transform: scale(2);
                margin-right:15px;
            }

        </style>
    </head>
    <body>
        <!-- ======= Header ======= -->
        <header id="header" class="header fixed-top d-flex align-items-center">
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
                    <a href="CartServlet"><i class="fa-solid fa-cart-shopping fa-bounce fa-2xl" style="color: #ff0000;"></i></a>
                    <a class="btn-book-a-table" href="Profile?acc=<%=session.getAttribute("account")%>">
                        <span> <%=session.getAttribute("account")%></span>

                    </a>
                    <a href="#"onclick="logoutt()"> <i class="fa-solid fa-right-from-bracket fa-2xl" style="color: #ff0000; margin-left: 20px;"></i></a>
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


        <%
            List<Store> recommendList = (List) request.getAttribute("recommendList");
            String name = (String) request.getAttribute("name");
            String phone = (String) request.getAttribute("phone");
            String city = (String) request.getAttribute("city");
            String district = (String) request.getAttribute("district");

            String street = (String) request.getAttribute("street");
            String address = street.replace(" ", "") + "|" + district.replace(" ", "") + "|" + city.replace(" ", "");

        %>
        <div class="container" style="margin: 100px auto 30px;">
            <h1 style="display: grid;
                justify-content: center; font-weight: bold;">Vui lòng kiểm tra lại thông tin và chọn cửa hàng</h1>
            <div class="text-center mb-3">
                <a href="checkout.jsp" class="btn btn-success">Quay lại</a>
            </div>

            <form action="CheckOutServlet" method="GET">
                <div>
                    <% Cookie[] cookies = request.getCookies();

                        int customer_id = 0;
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("customer_idd")) {
                                    customer_id = Integer.parseInt(cookie.getValue());

                                }
                            }
                        } else {
                            customer_id = 1;
                        }

                    %>
                    <input type="hidden" name="customer_id" id="customer_id" value="<%= customer_id%>">

                    <c:forEach var="dish" items="${cart.getCartItems()}" varStatus="loop">
                        <input type="hidden" name="dish_id" value="${dish.getDish().getDish_id()}">
                        <input type="hidden" name="quantity" value="${dish.getQuantity()}">
                        <input type="hidden" name="total" value="${cart.getTotal()}">
                    </c:forEach>
                    <h5 for="name" class="form-label">Họ và tên :</h5>
                    <input  class="form-control" id="name" name="name" value= "<%= name%>"readonly />
                </div>
                <div>
                    <h5 for="phone" class="form-label">Số điện thoại :</h5>
                    <input  class="form-control" id="phone" name="phone" value="<%= phone%>"readonly/>
                </div>
                <div class="mb-3">


                    <h5 for="address" class="form-label">Thành Phố : </h5>
                    <input class="form-control" id="address1" name="address1"value="<%=city%>" readonly /> 
                    <h5 for="address" class="form-label">Quận/Huyện :</h5>
                    <input class="form-control" id="address2" name="address2" value="<%= district%>"readonly />
                    <h5 for="address" class="form-label">Địa chỉ  :</h5>
                    <input class="form-control" id="address3" name="address3" value="<%= street%>" readonly />
                </div>
                <div class="mb-3">
                    <h2 style=" font-weight: bold;">Vui lòng chọn cửa hàng</h2>
                    <c:if test="${not empty recommendList}">
                        <select name="store_id" class="form-select" aria-label="Default select example" >
                            <c:forEach var="store" items="${recommendList}">
                                <option value="${store.store_id}">${store.address}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${empty recommendList}">
                        <p>Hiện không có cửa hàng gần bạn.</p>
                    </c:if>
                </div>
                <div class="pay">
                    <input type="radio"  id="codd" name="cod" value="1">
                    <input type="hidden" id="pStatus" name="pStatus" value="N"/>
                    <input type="hidden" id="address" name="address" value="<%= address%>"/>
                    <label for="COD">Thanh toán khi nhận hàng</label><br>
                    <input type="radio"  id="cod" name="cod" value="2">
                    <label for="bankCode">Thanh toán qua ví VNPAY</label><br>
                </div>
                <button type="submit" class="btn btn-primary">Xác nhận</button>
            </form>
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
        <script>
            function logoutt() {
                document.getElementById("myModal").style.display = "block";
            }
            function no() {
                document.getElementById("myModal").style.display = "none";
            }
            function yes() {

                window.location.href = "ListProductGuest";
            }
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