<%-- 
    Document   : listStaffAccount
    Created on : Oct 26, 2023, 1:58:14 AM
    Author     : Asus
--%>

<%@page import="model.Staff"%>
<%@page import="java.util.List"%>
<%@page import="dal.StoreDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="css/styleadmin.css"/>
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
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
        <link rel="stylesheet" href="css/stylecart.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/5.3.45/css/materialdesignicons.css" integrity="sha256-NAxhqDvtY0l4xn+YVa6WjAcmd94NNfttjNsDmNatFVc=" crossorigin="anonymous" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
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
            .modal-content2{
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
                justify-content: center;
                gap: 20px;
            }
            .modal-content {
                width: 35% !important;
                padding: 40px;
                position: absolute;
                top: 15% !important;
                left: 30% !important;
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
                        <li><a href="ListProductServlet">Món ăn</a></li>
                        <li><a href="ListStore">Cửa hàng</a></li>

                        <li><a href="Statistic">Xem doanh thu</a></li>
                        <li><a href="ListStaffAccountServlet">Nhân viên</a></li>

                    </ul>
                </nav><!-- .navbar -->
                <div> 
                    <a href="#" id="logout" onclick="logout()"> <i class="fa-solid fa-right-from-bracket fa-2xl" style="color: #ff0000;
                                                                   margin-left: 20px;"></i></a>
                </div>
                <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
                <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

            </div>


        </header><!-- End Header -->
        <div style="margin:100px 0 150px 0;">
            <% StoreDAO storeDAO = new StoreDAO();

            %>
            <div style="text-align: center;">
                <h1 style=" font-weight: bold;">Danh sách nhân viên</h1>
                <button type="submit" class="btn btn-secondary mb-4" >
                    <a style=" color: white;
                       text-decoration: none;"  href="ListProductServlet">Trở về</a>
                </button>
                <br>
                <button class="btn btn-secondary mb-4 mt-4" id="btnThem">Thêm tài khoản nhân viên</button><br>
                <span class="form-message" style="color:red;">${tb}</span>
            </div>
            <table class="table">
                <thead class="thead-dark" style="text-align: center;">
                    <tr>
                        <th scope="col">ID Nhân viên</th>
                        <th scope="col">Tài khoản</th>
                        <th scope="col">Mật khẩu</th>
                        <th scope="col">Tên cửa hàng</th>
                        <th scope="col">Hành động</th>
                    </tr>
                </thead>
                <c:forEach items="${listStaff}" var="staff">
                    <tr>
                        <td>${staff.getStaff_id()}</td>
                        <td>${staff.getUsername()}</td>
                        <td>***********</td>
                        <c:set var="store_id" value="${staff.getStore_id()}"></c:set>
                        <td><%= storeDAO.getStoreById((int) pageContext.getAttribute("store_id")).getStore_name()%></td>
                        <td>
                            <div class="product-button d-flex justify-content-center">
                                <form action="DeleteStaffAccount" method="POST">
                                    <input type="hidden" name="staff_id" value="${staff.getStaff_id()}"/><!-- comment -->
                                    <button class="product-delete btn btn-danger"> <i class="fa-solid fa-trash"></i></button>
                                </form>
                                <form  action="UpdateStaffAccount" method="POST">
                                    <input type="hidden" name="staff_id" value="${staff.getStaff_id()}">
                                    <button  class="btn btn-success" type="button" name="updateButton" value="update" onclick="

                                            modall.style.display = 'block';

                                            document.getElementById('staff_idd').value = ${staff.getStaff_id()};

                                            document.getElementById('usernamee').value = '${staff.getUsername()}';

                                            document.getElementById('passwordd').value = '${staff.getPassword().trim()}';
                                            document.getElementById('store_namee').value = '<%= storeDAO.getStoreById((int) pageContext.getAttribute("store_id")).getStore_name()%>';
                                             "
                                             ><i class="fa-solid fa-wrench"></i></button>
                                </form>
                            </div>
                        </td>
                    </tr>

                </c:forEach>

            </table>

            <form id="myModal" class="modal" action="AddNewStaffAccount" method="get" >
                <div class="modal-content" id="modall" style="width:30%;">
                    <h2 id="header-modal">Thêm tài khoản nhân viên</h2>
                    <div  class="container-form">
                        <div id="employeeForm">
                            <div class="form-group">
                                <label for="username"><i class="fa-solid fa-drumstick-bite"></i></label>
                                <input type="text" id="username" name="username" placeholder="Tài khoản" required>

                            </div>

                            <div class="form-group">
                                <label for="password" ><i class="fa-solid fa-circle-info"></i></label>
                                <input type="password" id="password" name="password" placeholder="Mật khẩu" required>
                                <span class="sp-thongbao" id="tbInfor"></span>
                            </div>

                            <div class="form-group">
                                <select name="store_id" class="form-select" aria-label="Default select example">
                                    <option value="0">Chọn cửa hàng</option>
                                    <c:forEach var="store" items="${listStore}" >
                                        <option value="${store.getStore_id()}">${store.getStore_name()}</option>
                                    </c:forEach>
                                </select>
                                <span class="sp-thongbao" id="tbSalary"></span>
                            </div>
                        </div>
                    </div>
                    <div class="footer-modal">
                        <button type="submit" value="Submit!" name="submit" style="margin-left: 46.5%;" class="submit submit-form" id="them">Thêm</button>
                        <button class="close-button" onclick="closeForm('myModal')">Đóng</button>
                    </div>
                </div>

            </form> 
            <!--edit-->
            <form action="UpdateStaffAccount" method="POST" id="myModaledit" class="modal"  >

                <div class="modal-content" id="modaledit" style="width:30%;">
                    <h2 id="header-modaledit">Edit password</h2>
                    <div  class="container-form">
                        <div id="employeeFormedit">
                            <input type="hidden" id="staff_idd"  name="staff_idd" value="${staff.getStaff_id()}">
                            <div class="form-group">
                                <label for="usernamee"><i class="fa-solid fa-drumstick-bite"></i></label>

                                <input type="text" id="usernamee" name="usernamee" value="${staff.getUsername()}" readonly>
                                <span class="sp-thongbao" id="tbName"></span>
                            </div>
                            <div class="form-group">
                                <label for="passwordd" ><i class="fa-solid fa-circle-info"></i></label>
                                <input type="password" id="passwordd" name="passwordd" value="${staff.getPassword().trim()}" >
                                <input type="hidden" id="passwordd" name="passwordd" value="${staff.getPassword().trim()}" >
                                <span class="sp-thongbao" id="tbInfor"></span>
                            </div>


                            <div class="form-group">
                                <label for="store_namee"><i class="fa-solid fa-tag"></i></label>
                                <input type="text" id="store_namee" name="store_namee" value="<%= storeDAO.getStoreById((int) pageContext.getAttribute("store_id")).getStore_name()%>" readonly>
                                <input type="hidden" id="store_namee" name="store_namee" value="<%= storeDAO.getStoreById((int) pageContext.getAttribute("store_id")).getStore_name()%>" >
                                <span class="sp-thongbao" id="tbType"></span>
                            </div>

                        </div>
                    </div>
                    <div class="footer-modal">
                        <input  class="btn btn-success" type="submit" style="margin-left: 46.5%;" name="submit" value="Sửa món ăn">
                        <button type="button" class="close-button" onclick="closeForm('myModaledit')">Đóng</button>
                    </div>
                </div>
            </form> 
        </div>
        <div class="modal" id="myModal12">
            <div class="modal-content2" style="width: 30%;">
                <h5 style=" margin-bottom: 20px;">
                    Bạn có chắc chắn bạn muốn thoát??</h5>
                <div class="d-flex btnlogout">
                    <button onclick="yes()" type="button" class="btn btn-warning">Yes</button>
                    <button onclick="no()" type="button" class="btn btn-success">No</button>
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
        <script>
            const modal = document.getElementById('myModal');
            const modall = document.getElementById('myModaledit');
            const btn = document.getElementById('btnThem');
            btn.addEventListener('click', () => {
                modal.style.display = 'block';
            });

            window.addEventListener('click', (event) => {
                if (event.target === modal) {
                    modal.style.display = 'none';

                }
            });
            function closeForm(formId) {
                const form = document.getElementById(formId);
                form.style.display = 'none';
            }
            function logout() {
                document.getElementById("myModal12").style.display = "block";
            }
            function no() {
                document.getElementById("myModal12").style.display = "none";
            }
            function yes() {

                window.location.href = "ListProductGuest";
            }
        </script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/aos/aos.js"></script>
        <script src="vendor/glightbox/js/glightbox.min.js"></script>
        <script src="vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="vendor/swiper/swiper-bundle.min.js"></script>
        <script src="vendor/php-email-form/validate.js"></script>
    </body>
</html>