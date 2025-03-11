<%-- 
    Document   : list
    Created on : Jun 1, 2023, 10:26:42 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FastFood</title>
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
                            <li><a href="#">Món ăn</a></li>
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



            <c:if test="${admin != null}">
            </c:if>
            <div class="container" style="margin:100px auto;
                 text-align: center;">
                <button class="btn btn-secondary mb-4 mt-4" id="btnThem">Thêm món ăn</button>
                <div class="row www">
                    <c:forEach var="dish" items="${list}" >
                        <div class="product col-md-3 mx-3 mb-4">

                            <img><img src="img/${dish.getImage()}" alt="alt" style="
                                      height: 260px; "/> 
                            <div class="product-name-price">
                                <h3 class="product-name" style=" margin-left: 0px;">${dish.getName()}</h3>
                                <p class="product-price"> ${dish.getPrice()} đ</p>
                            </div>

                            <p class="product-info">${dish.getInfor()}</p>

                            <div class="product-button d-flex justify-content-center">
                                <form  action="updateServlet" method="GET">
                                    <input type="hidden" name="dish_id" value="${dish.getDish_id()}">
                                    <button  class="btn btn-success" type="button" name="updateButton" value="update" onclick="modaledit.style.display = 'block';

                                            document.getElementById('dish_idd').value = ${dish.getDish_id()};

                                            document.getElementById('namee').value = '${dish.getName().trim()}';

                                            document.getElementById('inforr').value = '${dish.getInfor().trim()}';

                                            document.getElementById('pricee').value = '${dish.getPrice()}';

                                            document.getElementById('typeOfDishh').value = '${dish.getTypeOfDish().trim()}';

                                            document.getElementById('imagee').value = '${dish.getImage().trim()}';

                                             "
                                             ><i class="fa-solid fa-wrench"></i></button>
                                </form>
                                <form action="deleteServlet" method="POST">
                                    <input type="hidden" name="dish_id" value="${dish.getDish_id()}">
                                    <button class="product-delete btn btn-danger"> <i class="fa-solid fa-trash"></i></button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>


            <form id="myModal" class="modal" action="addServlet" method="POST" enctype="multipart/form-data">
                <div class="modal-content" id="modall" style="width:30%;">
                    <h2 id="header-modal">Thêm món ăn mới</h2>
                    <div  class="container-form">
                        <div id="employeeForm">
                            <div class="form-group">
                                <label for="name"><i class="fa-solid fa-drumstick-bite"></i></label>
                                <input type="text" id="name" name="name" placeholder="Tên món ăn" required>
                                <span class="sp-thongbao" id="tbName"></span>
                            </div>

                            <div class="form-group">
                                <label for="infor" ><i class="fa-solid fa-circle-info"></i></label>
                                <input type="text" id="infor" name="infor" placeholder="Mô tả món ăn" required>
                                <span class="sp-thongbao" id="tbInfor"></span>
                            </div>

                            <div class="form-group">
                                <label for="price" ><i class="fa-solid fa-money-bill"></i></label>
                                <input type="number" id="price" name="price" placeholder="Giá" required>
                                <span class="sp-thongbao" id="tbPrice"></span>
                            </div>
                            <div class="form-group">

                                <input type="hidden" id="comment" name="comment" value="" required/>
                                <span class="sp-thongbao" id="tbComment"></span>
                            </div>
                            <div class="form-group">
                                <label for="typeOfDish"><i class="fa-solid fa-tag"></i></label>

                                <select name="typeOfDish" require>
                                    <option value="f">Food</option>
                                    <option value="d">Drink</option><!-- comment -->
                                    <option value="c">Combo</option>
                                    <option value="i">Icecream</option>
                                </select>
                                <span class="sp-thongbao" id="tbType"></span>
                            </div>

                            <div class="form-group">
                                <label for="image"><i class="fa-solid fa-image"></i></label>
                                <input type="file" id="image" name="image" accept="img/*" placeholder="Tải ảnh lên" required><br>
                                <span class="sp-thongbao" id="tbSalary"></span>
                            </div>
                        </div>
                    </div>
                    <div class="footer-modal">
                        <button type="submit" value="Submit!" name="submit" style="margin-left: 46.5%;" class="submit submit-form" id="them">Thêm món ăn</button>
                        <button class="close-button" onclick="closeForm('myModal')">Đóng</button>
                    </div>
                </div>
            </form> 


            <form action="updateServlet" method="POST"id="myModaledit" class="modal"  enctype="multipart/form-data">

                <div class="modal-content" id="modaledit" style="width:30%;">
                    <h2 id="header-modaledit">Chỉnh sửa món ăn</h2>
                    <div  class="container-form">
                        <div id="employeeFormedit">
                            <input type="hidden" id="dish_idd"  name="dish_idd" value="${dish.getDish_id()}">
                            <div class="form-group">
                                <label for="namee"><i class="fa-solid fa-drumstick-bite"></i></label>

                                <input type="text" id="namee" name="namee" value="${dish.getName()}">
                                <span class="sp-thongbao" id="tbName"></span>
                            </div>
                            <div class="form-group">
                                <label for="inforr" ><i class="fa-solid fa-circle-info"></i></label>
                                <input type="text" id="inforr" name="inforr" value="${dish.getInfor()}" >
                                <input type="hidden" id="inforr" name="inforr" value="${dish.getInfor()}" >
                                <span class="sp-thongbao" id="tbInfor"></span>
                            </div>
                            <div class="form-group">
                                <label for="pricee" ><i class="fa-solid fa-money-bill"></i></label>
                                <input type="number" id="pricee" name="pricee" value="${dish.getPrice()}">
                                <input type="hidden" id="pricee" name="pricee" value="${dish.getPrice()}">
                                <span class="sp-thongbao" id="tbPrice"></span>
                            </div>
                                 
                                 <div class="form-group">
                                <label for="typeOfDishh"><i class="fa-solid fa-tag"></i></label>

                                <select name="typeOfDishh" id="typeOfDishh" require>
                                    <option value="f">Food</option>
                                    <option value="d">Drink</option>
                                    <option value="c">Combo</option>
                                    <option value="i">Icecream</option>
                                </select>
                                <span class="sp-thongbao" id="tbType"></span>
                            </div>
                                
<!--                            <div class="form-group">
                                <label for="typeOfDishh"><i class="fa-solid fa-tag"></i></label>
                                <input type="text" id="typeOfDishh" name="typeOfDishh"value="${dish.getTypeOfDish()}">
                                <input type="hidden" id="typeOfDishh" name="typeOfDishh"value="${dish.getTypeOfDish()}">
                                <span class="sp-thongbao" id="tbType"></span>
                            </div>-->

                            <div class="form-group">
                                <label for="imagee"><i class="fa-solid fa-image"></i></label>                       
                                <input type="file" id="fileInput" name="fileInput" value="${dish.getImage()}" style="display: none;">
                                <input type="text" id="imagee" name="imagee" value="${dish.getImage()}">
                                <button type="button" onclick="selectFile()">Chọn tệp</button>
                                <span class="sp-thongbao" id="tbImage"></span>
                            </div>
                            <input  type="hidden" value="${dish.getComment()} "id="commentt" name="commentt" >
                        </div>
                    </div>
                    <div class="footer-modal">
                        <input  class="btn btn-success" type="submit" style="margin-left: 46.5%;" name="submit" value="Sửa món ăn">
                        <button type="button"class="close-button"onclick="closeForm('myModaledit')">Đóng</button>
                    </div>
                </div>
            </form> 
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


                function selectFile() {
                    // Khi người dùng nhấn nút "Chọn tệp", kích hoạt sự kiện của trường nhập tệp ẩn
                    document.getElementById('fileInput').click();
                }

                document.getElementById('fileInput').addEventListener('change', function () {
                    var fileInput = document.getElementById('fileInput');
                    var imageInput = document.getElementById('imagee');
                    var dishImage = "${dish.getImage()}";

                    if (fileInput.files.length > 0) {
                        // Nếu có tệp được chọn, gửi yêu cầu đến servlet để thực hiện upload
                        imageInput.value = fileInput.files[0].name;
                        var formData = new FormData();
                        formData.append("fileInput", fileInput.files[0]);

                        // Sử dụng XMLHttpRequest hoặc fetch API để gửi yêu cầu POST đến servlet
                        // Ví dụ sử dụng XMLHttpRequest:
                        var xhr = new XMLHttpRequest();
                        xhr.open("POST", "updateServlet", true);
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === 4 && xhr.status === 200) {
                                // Xử lý phản hồi từ servlet (nếu cần)
                            }
                        };
                        xhr.send(formData);
                    } else {
                        // Nếu không có tệp nào được chọn, sử dụng giá trị mặc định (tên ảnh cũ)
                        imageInput.value = dishImage;
                    }
                });

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