<%-- 
    Document   : Store
    Created on : Oct 23, 2023, 1:07:05 PM
    Author     : Asus
--%>

<%@page import="model.Store"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FastFood</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="css/styleadmin.css"/>
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
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
            .modal22{
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
                top: 20% !important;
                left: 32%;
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
                    <a href="#" id="logout" onclick="logout()"> <i class="fa-solid fa-right-from-bracket fa-2xl" style="color: #ff0000; margin-left: 20px;"></i></a>
                </div>
                <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
                <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

            </div>


        </header><!-- End Header -->
        <div class="modal" id="myModal22">
            <div class="modal-content" style="width: 30%;">
                <h5 style=" margin-bottom: 20px;">Bạn có chắc chắn bạn muốn thoát?</h5>
                <div class="d-flex btnlogout">
                    <button onclick="yes()" type="button" class="btn btn-warning">Yes</button>
                    <button onclick="no()" type="button" class="btn btn-success">No</button>
                </div>
            </div>

        </div>
        <div style="margin:120px auto; text-align: center;">
            <h1 style="font-weight:bold;">Danh sách cửa hàng</h1>
            <button class="btn btn-secondary mb-4 mt-4" id="btnThem">Thêm cửa hàng</button>
            <%
                List<Store> storeList = (List) request.getAttribute("storeList");

            %>
            <table  class="table mt-4" style="text-align: center;">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col" style=" vertical-align: middle;">Mã cửa hàng</th>
                        <th scope="col" style=" vertical-align: middle;">Tên cửa hàng</th>
                        <th scope="col" style=" vertical-align: middle;">Giờ đóng, mở cửa</th>
                        <th scope="col" style=" vertical-align: middle;">Địa chỉ</th><!-- comment -->
                        <th scope="col" style=" vertical-align: middle;">Số điện thoại</th>
                        <th scope="col" style=" vertical-align: middle;">Hành động</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="store" items="<%=storeList%>" >
                        <tr>
                            <td> ${store.getStore_id()} </td>
                            <td> ${store.getStore_name()}  </td>
                            <td> ${store.getOcTime()}  </td><!-- comment -->
                            <td> ${store.getAddress()}  </td>
                            <td> ${store.getStore_PhoneNumber()}  </td>
                            <td>
                                <div class="product-button d-flex justify-content-center">
                                    <form  accept-charset="UTF-8" action="UpdateStoreServlet" method="GET">

                                        <input type="hidden" name="store_id" value="${store.getStore_id()}">
                                        <button class="btn btn-success" type="button" name="updateButton" value="update" onclick="modaledit.style.display = 'block';

                                                document.getElementById('store_idd').value = ${store.getStore_id()};

                                                document.getElementById('namee').value = '${store.getStore_name().trim()}';

                                                document.getElementById('addresss').value = '${store.getAddress().trim()}';

                                                document.getElementById('phoneNumberr').value = '${store.getStore_PhoneNumber().trim()}';

                                                document.getElementById('timeOcc').value = '${store.getOcTime().trim()}';
                                                "
                                                ><i class="fa-solid fa-wrench"></i></button>
                                    </form>
                                    <form action="DeleteStoreServlet" method="POST">
                                        <input type="hidden" name="store_id" value="${store.getStore_id()}">
                                        <button class="product-delete btn btn-danger"> <i class="fa-solid fa-trash"></i></button>
                                    </form>
                                </div>
                            </td>
                        <tr>

                        </c:forEach>

                </tbody>
            </table>
        </div>
        <!-- chinh sua thong tin cua hang -->

        <form accept-charset="UTF-8" action="UpdateStoreServlet" method="POST" id="myModaledit" class="modal"  >

            <div class="modal-content" id="modaledit" style="width:35%;">
                <h2 id="header-modaledit">Chỉnh sửa </h2>
                <div  class="container-form">
                    <div id="employeeFormedit">
                        <input type="text" id="store_idd" style="display:none;"  name="store_idd" value="${store.getStore_id()}">
                        <div class="form-group">
                            <label for="namee"><i class="fa-solid fa-drumstick-bite"></i></label>
                            <input type="text" id="namee" name="namee" value="${store.getStore_name()}">
                            <span class="sp-thongbao" id="tbName"></span>
                        </div>
                        <div class="form-group">
                            <label for="addresss" ><i class="fa-solid fa-circle-info"></i></label>
                            <input type="text" id="addresss" name="addresss" value="${store.getAddress()}" >
                            <input type="hidden" id="addresss" name="addresss" value="${store.getAddress()}" >
                            <span class="sp-thongbao" id="tbInfor"></span>
                        </div>
                        <div class="form-group">
                            <label for="timeOcc" ><i class="fa-solid fa-money-bill"></i></label>
                            <input type="text" id="timeOcc" name="timeOcc" value="${store.getOcTime()}">
                            <input type="hidden" id="timeOcc" name="timeOcc" value="${store.getOcTime()}">
                            <span class="sp-thongbao" id="tbPrice"></span>
                        </div>

                        <div class="form-group">
                            <label for="phoneNumberr"><i class="fa-solid fa-tag"></i></label>
                            <input type="text" id="phoneNumberr" name="phoneNumberr" value="${store.getStore_PhoneNumber()}">
                            <input type="hidden" id="phoneNumberr" name="phoneNumberr" value="${store.getStore_PhoneNumber()}">
                            <span class="sp-thongbao" id="tbType"></span>
                        </div>


                    </div>
                </div>
                <div class="footer-modal">
                    <button type="submit" class="btn btn-success" style="margin-left: 46.5%;"  value="Sửa món ăn"> Sửa cửa hàng</button>
                    <button type="button" class="close-button" onclick="closeForm('myModaledit')">Đóng</button>
                </div>
            </div>
        </form>


        <!--  them cua hang moi -->

        <form id="myModal" class="modal" action="AddStoreServlet" method="GET" enctype="multipart/form-data">
            <div class="modal-content" id="modall" style="width:35%;">
                <h2 id="header-modal">Thêm cửa hàng mới</h2>
                <div  class="container-form">
                    <div id="employeeForm">
                        <div class="form-group">
                            <label for="name"><i class="fa-solid fa-drumstick-bite"></i></label>
                            <input type="text" id="name" name="name" placeholder="Tên cửa hàng" required>
                            <span class="sp-thongbao" id="tbName"></span>
                        </div>

                        <div class="form-group">
                            <label for="address" ><i class="fa-solid fa-circle-info"></i></label>
                            <input type="text" id="address" name="address" placeholder="Địa chỉ" required>
                            <span class="sp-thongbao" id="tbInfor"></span>
                        </div>

                        <div class="form-group">
                            <label for="phoneNumber" ><i class="fa-solid fa-money-bill"></i></label>
                            <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Số điện thoại" required>
                            <span class="sp-thongbao" id="tbPrice"></span>
                        </div>
                        <div class="form-group">
                            <label for="ocTime"><i class="fa-solid fa-clock"></i></label>
                            <input type="text" id="ocTime" name="ocTime" placeholder="Thời gian mở, đóng cửa" required>
                            <span class="sp-thongbao" id="tbComment"></span>
                        </div>


                    </div>
                </div>
                <div class="footer-modal">
                    <button type="submit" value="Submit!" name="submit" style="margin-left: 46.5%;" class="submit submit-form" id="them">Thêm cửa hàng</button>
                    <button class="close-button" onclick="closeForm('myModal')">Đóng</button>
                </div>
            </div>
        </form> 
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
        <!-- Vendor JS Files -->
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/aos/aos.js"></script>
        <script src="vendor/glightbox/js/glightbox.min.js"></script>
        <script src="vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="vendor/swiper/swiper-bundle.min.js"></script>
        <script src="vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="js/main.js"></script>
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
                        //=================================================
                        const modaledit = document.getElementById('myModaledit');

                        function closeForm(formId) {
                            const form = document.getElementById(formId);
                            form.style.display = 'none';
                        }

                        window.addEventListener('click', (event) => {
                            if (event.target === modaledit) {
                                modaledit.style.display = 'none';
                            }
                        });

                        function logout() {
                            document.getElementById("myModal22").style.display = "block";
                        }
                        function no() {
                            document.getElementById("myModal22").style.display = "none";
                        }
                        function yes() {

                            window.location.href = "ListProductGuest";
                        }
        </script>
    </body>

</html>