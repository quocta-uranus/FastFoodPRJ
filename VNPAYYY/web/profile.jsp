<%-- 
    Document   : profile
    Created on : Oct 8, 2023, 8:00:25 PM
    Author     : HAU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="css/styleprofile.css"/>
        <title>FastFood</title>
        <style>

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
        </style>
    </head>

    <body>

        <header id="header" class="header fixed-top d-flex align-items-center">
            <div class="container d-flex align-items-center justify-content-between">

                <a href="index.html" class="logo d-flex align-items-center me-auto me-lg-0">
                    <!-- Uncomment the line below if you also wish to use an image logo -->
                    <!-- <img src="assets/img/logo.png" alt=""> -->
                    <img class="img-navbar" src="img/logo.jpg" alt="ListProductCustomer">
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
                    <a href="#" id="logout" onclick="logout()"> <i class="fa-solid fa-right-from-bracket fa-2xl" style="color: #ff0000; margin-left: 20px;"></i></a>
                </div>
                <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
                <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

            </div>
            <div class="modal" id="myModal">
                <div class="modal-content" style="width: 30%;">
                    <h5 style=" margin-bottom: 20px;">Bạn có chắc chắn bạn muốn thoát?</h5>
                    <div class="d-flex btnlogout">
                        <button onclick="yes()" type="button" class="btn btn-warning">Yes</button>
                        <button onclick="no()" type="button" class="btn btn-success">No</button>
                    </div>
                </div>

            </div> 
        </header><!-- End Header -->
        <section style="background-color: #eee; margin-top: 20px;">
            <div class="container py-5">


                <div class="row">
                    <div class="col-lg-4">
                        <div class="card mb-4">
                            <div class="card-body text-center">
                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                                     class="rounded-circle img-fluid" style="width: 150px;">
                                <h5 class="my-3">${profile.getUsername()}</h5>
                                <p class="text-muted mb-1">Người dùng</p>
                                <p class="text-muted mb-4">${profile.getAddress()}</p>
                                <!--            <div class="d-flex justify-content-center mb-2">
                                              <button type="button" class="btn btn-primary">Follow</button>
                                              <button type="button" class="btn btn-outline-primary ms-1">Message</button>
                                            </div>-->
                            </div>
                        </div>
                    </div>

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

                    %>

                    <div class="col-lg-8" style=" margin-top: -40px;">
                        <div style="display:flex; gap:10px; margin-bottom: 15px;justify-content: end;">
                            <form action="OrderTracking" method="GET">
                                <input type="hidden" name="customer_id" id="customer_id" value="<%= customer_id%>">
                                <button class="btn btn-primary" type="submit">Chờ xác nhận </button>
                            </form>
                                <form action="PreparingOrderServlet" method="GET">
                                <input type="hidden" name="customer_id" id="customer_id" value="<%= customer_id%>">
                                <button class="btn btn-dark" type="submit">Đơn hàng đã xác nhận</button>
                            </form>
                            <form action="DeliveringOrderServlet" method="GET">
                                <input type="hidden" name="customer_id" id="customer_id" value="<%= customer_id%>">
                                <button class="btn btn-warning" type="submit">Đang giao hàng </button>
                            </form>
                            <form action="OrderHistoryServlet" method="GET">
                                <input type="hidden" name="customer_id" id="customer_id" value="<%= customer_id%>">
                                <button class="btn btn-success" type="submit">Đơn hàng đã hoàn thành </button>
                            </form>  
                            <form action="CancelOrderServlet" method="POST">
                                <input type="hidden" name="customer_id" id="customer_id" value="<%= customer_id%>">
                                <button class="btn btn-danger" type="submit">Đơn hàng đã hủy </button>
                            </form>
                        </div>
                        <form action="UpdateProfileServlet" method="POST" id="profileForm">


                            <div class="card mb-4">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Tài khoản</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0">${profile.getUsername()}</p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Tên người dùng</p>

                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0" id="cus_namee">${profile.getCustomer_name()}</p>
                                            <input type="text" id="cus_name" value="${profile.getCustomer_name().trim()}"  name="cus_name" class=" d-none form-control">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Email</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0" id="emails">${profile.getEmail()}</p>
                                            <input type="text" id="email" value="${profile.getEmail()}" name="email" class=" d-none">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Số điện thoại</p>

                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0" id="phones">${profile.getPhoneNumber()}</p>
                                            <input type="text" id="phone" value="${profile.getPhoneNumber().trim()}"  name="phone" class="d-none form-control">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Địa chỉ</p>

                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0 " id="addresss">${profile.getAddress()}</p>
                                            <input type="text" id="address" value="${profile.getAddress().trim()}"  name="address" class=" form-control" style="display:none;">
                                        </div>
                                    </div>

                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Mật khẩu</p>
                                        </div>
                                        <div class="col-sm-9">

                                            <p class="text-muted mb-0" id="adds">***********</p>
                                            <input type="password" id="add" value="${profile.getPassword().trim()}" name="add" class=" d-none">
                                        </div>
                                    </div>

                                </div>
                                <input type="hidden" name="customer_id" id="customer_id" value="<%= customer_id%>">
                            </div>
                            <button type="submit" class="btn btn-outline-primary ms-1 d-none" id="btnsave" onclick="save()">Save Your Profile</button>

                        </form>
                        <c:if test="${errorMessage ne null}">
                            <p style="color: red;">${errorMessage}</p>
                        </c:if>
                        <div class="text-center">

                            <button type="button" class="btn btn-outline-danger ms-1" id="btnedit" onclick="edit()">Edit Your Profile</button>
                            <button id="passwordButton" class="btn btn-primary" onclick="showPasswordForm()">Edit your password</button>

                            <form action="ChangePassServlet" method="POsT" id="changePass" class="d-none" style="padding:0 20px">
                                <input type="hidden" name="customer_id" value="<%= customer_id%>"/>
                                <div class="row mb-3 mt-3" style=" text-align: start !important;">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Mật khẩu cũ : </p>

                                    </div>
                                    <div class="col-sm-9">
                                        <input type="password" id="oldPassword" name="oldPassword"  class="form-control" required>

                                    </div>


                                </div>

                                <div class="row mb-3" style=" text-align: start !important;">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Mật khẩu mới :</p>

                                    </div>
                                    <div class="col-sm-9">
                                        <input type="password" id="newPassword" name="newPassword" class="form-control" required>
                                    </div>
                                </div>  

                                <div class="row mb-3" style=" text-align: start !important;">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Nhập lại mật khẩu :</p>

                                    </div>
                                    <div class="col-sm-9">
                                        <input type="password" id="re_newPassword" name="re_newPassword" class="form-control" required>
                                    </div>
                                </div>  

                                <button id="passwordButton" class="btn btn-danger" >Save your password</button>

                            </form>

                        </div>
                    </div>
                </div>

            </div>
        </section>
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
    </body>
    <script>
        function edit() {
            if (document.getElementById("btnedit").innerHTML === "Edit Your Profile")
            {
                document.getElementById("email").className = "d-none form-control";
                document.getElementById("phone").className = "d-block form-control";
                document.getElementById("add").className = "d-none form-control";
                document.getElementById("address").className = "d-block form-control";
                document.getElementById("cus_name").className = "d-block form-control";
                document.getElementById("adds").style.display = "d-block form-control";
                document.getElementById("addresss").style.display = "none";
                document.getElementById("cus_namee").style.display = "none";
                document.getElementById("phones").style.display = "none";
                document.getElementById("emails").style.display = "d-none form-control";
                document.getElementById("btnedit").innerHTML = "Save Your Profile";

//                 document.getElementById("btnedit").type= "submit";
            } else {
                document.getElementById("btnedit").innerHTML = "Edit Your Profile";
                document.getElementById("email").className = "d-none";
                document.getElementById("phone").className = "d-none";
                document.getElementById("add").className = "d-block";
                document.getElementById("address").className = "d-none";
                document.getElementById("adds").style.display = "d-block";
                document.getElementById("addresss").style.display = "block";
                document.getElementById("phones").style.display = "block";
                document.getElementById("emails").style.display = "d-none";
                document.getElementById("profileForm").submit();
            }

        }
        function showPasswordForm() {
            const passwordButton = document.getElementById("passwordButton");
            const passwordForm = document.getElementById("changePass");

            // Đảm bảo form ban đầu ẩn, và thay đổi lớp CSS khi cần
            if (passwordForm.classList.contains("d-none")) {
                passwordButton.innerText = "Cancel"; // Thay đổi văn bản nút
                passwordForm.classList.remove("d-none"); // Hiển thị form
            } else {
                passwordButton.innerText = "Edit your password"; // Thay đổi văn bản nút
                passwordForm.classList.add("d-none"); // Ẩn form
            }
        }


        function logout() {
            document.getElementById("myModal").style.display = "block";
        }
        function no() {
            document.getElementById("myModal").style.display = "none";
        }
        function yes() {

            window.location.href = "ListProductGuest";
        }



    </script>
</html>