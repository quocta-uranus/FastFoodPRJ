<%@page import="dal.CustomerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FastFood</title>
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
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
        <link rel="stylesheet" href="css/styleguest.css"/>
        <style>
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
                    <a href="#" id="logout" onclick="logout()"> <i class="fa-solid fa-right-from-bracket fa-2xl" style="color: #ff0000; margin-left: 20px;"></i></a>
                </div>
                <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
                <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

            </div>


        </header><!-- End Header -->
        <div class="container content-checkout" style="margin-top: 80px;">
            <h1 class="text-center" style="font-weight: bold;">Vui lòng nhập thông tin</h1>
            <div class="text-center mb-3">
                <a href="CartServlet" class="btn btn-success">Giỏ hàng</a>
            </div>
            <form action="FindAddressServlet" method="GET">
                <div class="mb-3">
                    <%
                        Cookie[] cookies = request.getCookies();
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
                        CustomerDAO customerDAO = new CustomerDAO();
                        String name = customerDAO.getCustomer(customer_id).getCustomer_name();
                        String cus_name = customerDAO.getCustomer(customer_id).getCustomer_name();
                        String phoneNumber = customerDAO.getCustomer(customer_id).getPhoneNumber();
                    %>
                    <input type="hidden" name="customer_id" id="customer_id" value="<%= customer_id%>">

                    <c:forEach var="dish" items="${cart.getCartItems()}" varStatus="loop">
                        <input type="hidden" name="dish_id" value="${dish.getDish().getDish_id()}">
                        <input type="hidden" name="quantity" value="${dish.getQuantity()}">
                        <input type="hidden" name="total" value="${cart.getTotal()}">
                    </c:forEach>
                    <label for="name" class="form-label">Họ và tên người nhận</label>
                    <input type="text" class="form-control" id="name" name="name" value="<%= cus_name%>" required>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Số điện thoại người nhận</label>
                    <input type="text" class="form-control" id="phone" name="phone" value="<%= phoneNumber%>" required>
                </div>
                <div class="mb-3">
                    <div class="select-item">
                        <label for="city-province">Tỉnh/Thành Phố :</label>
                        <select id="city-province" name="address1" class="form-select" aria-label="Default select example">
                            <option value='0'>&nbsp;Chọn Tỉnh/Thành Phố...</option>
                            <option value='01'>&nbspThành phố Hà Nội</option>
                            <option value='79'>&nbspThành phố Hồ Chí Minh</option>
                            <option value='31'>&nbspThành phố Hải Phòng</option>
                            <option value='48'>&nbspThành phố Đà Nẵng</option>
                            <option value='92'>&nbspThành phố Cần Thơ</option>
                            <option value='02'>&nbspTỉnh Hà Giang</option>
                            <option value='04'>&nbspTỉnh Cao Bằng</option>
                            <option value='06'>&nbspTỉnh Bắc Kạn</option>
                            <option value='08'>&nbspTỉnh Tuyên Quang</option>
                            <option value='10'>&nbspTỉnh Lào Cai</option>
                            <option value='11'>&nbspTỉnh Điện Biên</option>
                            <option value='12'>&nbspTỉnh Lai Châu</option>
                            <option value='14'>&nbspTỉnh Sơn La</option>
                            <option value='15'>&nbspTỉnh Yên Bái</option>
                            <option value='17'>&nbspTỉnh Hoà Bình</option>
                            <option value='19'>&nbspTỉnh Thái Nguyên</option>
                            <option value='20'>&nbspTỉnh Lạng Sơn</option>
                            <option value='22'>&nbspTỉnh Quảng Ninh</option>
                            <option value='24'>&nbspTỉnh Bắc Giang</option>
                            <option value='25'>&nbspTỉnh Phú Thọ</option>
                            <option value='26'>&nbspTỉnh Vĩnh Phúc</option>
                            <option value='27'>&nbspTỉnh Bắc Ninh</option>
                            <option value='30'>&nbspTỉnh Hải Dương</option>
                            <option value='33'>&nbspTỉnh Hưng Yên</option>
                            <option value='34'>&nbspTỉnh Thái Bình</option>
                            <option value='35'>&nbspTỉnh Hà Nam</option>
                            <option value='36'>&nbspTỉnh Nam Định</option>
                            <option value='37'>&nbspTỉnh Ninh Bình</option>
                            <option value='38'>&nbspTỉnh Thanh Hóa</option>
                            <option value='40'>&nbspTỉnh Nghệ An</option>
                            <option value='42'>&nbspTỉnh Hà Tĩnh</option>
                            <option value='44'>&nbspTỉnh Quảng Bình</option>
                            <option value='45'>&nbspTỉnh Quảng Trị</option>
                            <option value='46'>&nbspTỉnh Thừa Thiên Huế</option>
                            <option value='49'>&nbspTỉnh Quảng Nam</option>
                            <option value='51'>&nbspTỉnh Quảng Ngãi</option>
                            <option value='52'>&nbspTỉnh Bình Định</option>
                            <option value='54'>&nbspTỉnh Phú Yên</option>
                            <option value='56'>&nbspTỉnh Khánh Hòa</option>
                            <option value='58'>&nbspTỉnh Ninh Thuận</option>
                            <option value='60'>&nbspTỉnh Bình Thuận</option>
                            <option value='62'>&nbspTỉnh Kon Tum</option>
                            <option value='64'>&nbspTỉnh Gia Lai</option>
                            <option value='66'>&nbspTỉnh Đắk Lắk</option>
                            <option value='67'>&nbspTỉnh Đắk Nông</option>
                            <option value='68'>&nbspTỉnh Lâm Đồng</option>
                            <option value='70'>&nbspTỉnh Bình Phước</option>
                            <option value='72'>&nbspTỉnh Tây Ninh</option>
                            <option value='74'>&nbspTỉnh Bình Dương</option>
                            <option value='75'>&nbspTỉnh Đồng Nai</option>
                            <option value='77'>&nbspTỉnh Bà Rịa - Vũng Tàu</option>
                            <option value='80'>&nbspTỉnh Long An</option>
                            <option value='82'>&nbspTỉnh Tiền Giang</option>
                            <option value='83'>&nbspTỉnh Bến Tre</option>
                            <option value='84'>&nbspTỉnh Trà Vinh</option>
                            <option value='86'>&nbspTỉnh Vĩnh Long</option>
                            <option value='87'>&nbspTỉnh Đồng Tháp</option>
                            <option value='89'>&nbspTỉnh An Giang</option>
                            <option value='91'>&nbspTỉnh Kiên Giang</option>
                            <option value='93'>&nbspTỉnh Hậu Giang</option>
                            <option value='94'>&nbspTỉnh Sóc Trăng</option>
                            <option value='95'>&nbspTỉnh Bạc Liêu</option>
                            <option value='96'>&nbspTỉnh Cà Mau</option>
                        </select>
                    </div>
                    <div class="select-item district-town-select">
                        <label for="district-town">Quận/Huyện:</label>
                        <select id="district-town" name="address2" class="form-select" aria-label="Default select example">
                            <option value='0'>Chọn Quận/Huyện...</option>
                            <!-- Quận/huyện sẽ được cập nhật thông qua Ajax -->
                        </select>
                    </div>


                    <div class="address">
                        <label for="address3">Địa chỉ:</label>
                        <input type="text" id="address3" name="address3" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" required/>
                    </div>
                </div>







                <button type="submit" class="btn btn-primary">Tiếp tục</button>
            </form>
        </div>
        <!-- ======= Footer ======= -->
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
            // Lắng nghe sự kiện khi người dùng thay đổi chọn tỉnh/thành phố
            document.getElementById("city-province").addEventListener("change", function () {
                const selectedProvince = this.value;
                if (selectedProvince === "0") {
                    // Chọn một lựa chọn không hợp lệ
                    return;
                }

                // Gửi yêu cầu Ajax để lấy danh sách quận huyện
                const xhr = new XMLHttpRequest();
                xhr.open("GET", "/FastFoodStore/AddressServlet?action=getDistrict&idProvince=" + selectedProvince);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        const districtSelect = document.getElementById("district-town");
                        const response = JSON.parse(xhr.responseText);

                        // Xóa tất cả các lựa chọn hiện có
                        while (districtSelect.firstChild) {
                            districtSelect.removeChild(districtSelect.firstChild);
                        }

                        // Thêm các quận huyện vào danh sách
                        response.forEach(function (district) {
                            const option = document.createElement("option");
                            option.value = district.idDistrict; // Giá trị của quận huyện
                            option.textContent = district.name; // Tên quận huyện
                            districtSelect.appendChild(option);
                        });

                    }
                    console.log(xhr.status);
                };
                xhr.send();
            });
            //----------------------------lay phuong - xa-------------------

            document.getElementById("district-town").addEventListener("change", function () {
                const selectedDistrict = this.value;
                if (selectedDistrict === "0") {
                    // Chọn một lựa chọn không hợp lệ
                    return;
                }

                // Gửi yêu cầu Ajax để lấy danh sách quận huyện
                const xhr = new XMLHttpRequest();
                xhr.open("GET", "/FastFoodStore/AddressServlet?action=getCommune&idDistrict=" + selectedDistrict);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        const commueSelect = document.getElementById("ward-commune");
                        const response = JSON.parse(xhr.responseText);

                        // Xóa tất cả các lựa chọn hiện có
                        while (commueSelect.firstChild) {
                            commueSelect.removeChild(commueSelect.firstChild);
                        }

                        // Thêm các quận huyện vào danh sách
                        response.forEach(function (commue) {
                            const option = document.createElement("option");
                            option.value = commue.idCommune; // Giá trị của quận huyện
                            option.textContent = commue.name; // Tên quận huyện
                            commueSelect.appendChild(option);
                        });
                    }
                    console.log();
                };
                xhr.send();
            });
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