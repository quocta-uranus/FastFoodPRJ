<%-- 
    Document   : Cart
    Created on : Oct 8, 2023, 10:16:16 PM
    Author     : HAU
--%>

<%@page import="com.entity.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FastFood</title>
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
        <link rel="stylesheet" href="css/stylecart.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/5.3.45/css/materialdesignicons.css" integrity="sha256-NAxhqDvtY0l4xn+YVa6WjAcmd94NNfttjNsDmNatFVc=" crossorigin="anonymous" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
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

        </style>
    </head>
    <body>
        <header id="header" class="header fixed-top d-flex align-items-center">
            <div class="container d align-items-center justify-content-between">

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
                <a class="btn-book-a-table" href="Profile?acc=<%=session.getAttribute("account")%>"> <span> <%=session.getAttribute("account")%></span> </a>
                <a href="#" id="logout" onclick="logout()"> <i class="fa-solid fa-right-from-bracket fa-2xl" style="color: #ff0000; margin-left: 20px;"></i></a>
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
        <div class="modal" id="modalcheckout">
            <div class="modal-content" style="width: 30%;">
                <h5 style=" margin-bottom: 20px;">Giỏ hàng của bạn chưa có đơn hàng nào !</h5>
                <div class="d-flex btnlogout">
                    <button onclick="no1()" type="button" class="btn btn-success">Đã Hiểu</button>
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
        <input type="hidden" name="customer_id" id="customer_id" value="<%= customer_id%>">
        <div class="container content">
            <div class="row">
                <div class="col-xl-8">
                    <c:forEach var="dish" items="${cart6.getCartItems()}" varStatus="loop">
                        <div class="card border shadow-none">
                            <div class="card-body">

                                <div class="d-flex align-items-start border-bottom pb-3">
                                    <div class="me-4">
                                        <img src="img/${dish.getDish().getImage()}" alt="" class="avatar-lg rounded">
                                    </div>
                                    <div class="flex-grow-1 align-self-center overflow-hidden">
                                        <div>
                                            <h5 class="text-truncate font-size-18"><a href="#" class="text-dark">${dish.getDish().getName()} </a></h5>
                                            <p class="text-muted mb-0">
                                                <i class="bx bxs-star text-warning"></i>
                                                <i class="bx bxs-star text-warning"></i>
                                                <i class="bx bxs-star text-warning"></i>
                                                <i class="bx bxs-star text-warning"></i>
                                                <i class="bx bxs-star-half text-warning"></i>
                                            </p>
                                            <p class="mb-0 mt-1">Đơn vị :  <span class="fw-medium">phần</span></p>
                                        </div>
                                    </div>

                                    <div class="flex-shrink-0 ms-2">
                                        <ul class="list-inline mb-0 font-size-16">
                                            <li class="list-inline-item" >

                                                <input type="hidden" name="index" id="index" value="${loop.index}">
                                                <button  class="delete" type="button" onclick="delete1(${loop.index})" name="deleteButton" value="delete">
                                                    <i  class="mdi mdi-trash-can-outline"></i>
                                                </button>

                                            </li>
                                            <li class="list-inline-item">
                                                <a href="#" class="text-muted px-1">
                                                    <i class="mdi mdi-heart-outline"></i>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <div>
                                    <div class="row" style=" justify-content: space-between;">
                                        <div class="col-md-4">
                                            <div class="mt-3">
                                                <p class="text-muted mb-2">Giá</p>
                                                <h5 class="mb-0 mt-2" style="color:red; font-weight: bold;"><span class="text-muted me-2"><del class="font-size-16 fw-normal"></del></span>$${dish.getDish().getPrice()}</h5>
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="mt-3" style="">
                                                <p class="text-muted mb-2 ml-3">Số lượng</p>
                                                <div class="quantity" style="gap:10px;">
                                                    <button class="minus" onclick="decrementQuantity(${loop.index}, ${dish.getDish().getDish_id()})">-</button>
                                                    <span class="quantity-input" id="quantity${loop.index}" style=" font-weight: bold;" >${dish.getQuantity()}</span>
                                                    <button class="plus" onclick="incrementQuantity(${loop.index}, ${dish.getDish().getDish_id()})">+</button>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="modal" id="modaldelete${loop.index}">
                            <div class="modal-content" style="width: 30%;">
                                <h5 style=" margin-bottom: 20px;">Are you sure you want to delete?</h5>
                                <div class="d-flex btnlogout">
                                    <button onclick="yes2(${loop.index},<%= customer_id%>)" type="button" class="btn btn-warning">Yes</button>
                                    <button onclick="no2(${loop.index})" type="button" class="btn btn-success">No</button>
                                </div>
                            </div><!-- comment -->

                        </div>
                    </c:forEach>
                    <!-- end card -->

                    <div class="row my-4">
                        <div class="col-sm-6">
                            <a href="ListProductCustomer#menu" class="btn btn-link text-muted">
                                <i class="mdi mdi-arrow-left me-1"></i> Tiếp tục mua hàng </a>
                        </div> <!-- end col -->
                        <div class="col-sm-6" id="payyy">
                            <div class="text-sm-end mt-2 mt-sm-0 ">
                                <a id="checkoutButton"  class="btn btn-success">
                                    <i class="mdi mdi-cart-outline me-1 "></i> Đặt hàng </a>
                            </div>
                        </div> <!-- end col -->
                    </div> <!-- end row-->
                </div>

                <div class="col-xl-4">
                    <div class="mt-5 mt-lg-0">
                        <div class="card border shadow-none">
                            <div class="card-header bg-transparent border-bottom py-3 px-4">
                                <h5 class="font-size-16 mb-0">Tóm tắt đơn hàng <span class="float-end"></span></h5>
                            </div>
                            <div class="card-body p-4 pt-2">

                                <div class="table-responsive">
                                    <table class="table mb-0">
                                        <tbody>
                                            <tr>
                                                <td>Tổng tiền dự kiến :</td>
                                                <td class="text-end" style="color:green; font-family: bold;">
                                                    <p>$  ${sessionScope.cart6.total}</p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Giảm giá : </td>
                                                <td class="text-end">- $ 0</td>
                                            </tr>

                                            <tr class="bg-light">
                                                <th>Tổng tiền :</th>
                                                <td class="text-end">
                                                    <span class="fw-bold" id="total" style="color:red; font-family: bold;">
                                                        $ ${sessionScope.cart6.total}

                                                    </span>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- end table-responsive -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end row -->
            <%
                int totalValue = ((Cart) pageContext.findAttribute("cart6")).getTotal();
                session.setAttribute("totalValue", totalValue);
            %>
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
    </body>
    <script>
        // kiem tra gio hang, khong co thi khong cho checkout
        var totalValue = parseInt(document.getElementById("total").textContent.match(/\d+/));
        var checkoutButton = document.getElementById("checkoutButton");


        if (totalValue === 0) {

            checkoutButton.addEventListener("click", function () {
                document.getElementById("modalcheckout").style.display = "block";
//                    //dung co dung` alert Duc, chu` Duc cu alert duoc di r de chut hau sua lai
//                    // thanh 1 cai modal thong bao nhay xuong
            });
        } else {

            checkoutButton.href = "ListStoreServlet?customer_id=" + <%= customer_id%>;
        }
        function incrementQuantity(index, dish_id) {
            var quantitySpan = document.getElementById("quantity" + index);
            var currentQuantity = parseInt(quantitySpan.innerText);
            quantitySpan.innerText = currentQuantity + 1;
            updateTotal();
            var customer_id = document.getElementById("customer_id").value;
            // Chuyển hướng và truyền dish_id trong URL
            window.location.href = "CookieAddToCart?dish_id=" + dish_id + "&action=increment&customer_id=" + customer_id;
        }
        function decrementQuantity(index, dish_id) {
            var quantitySpan = document.getElementById("quantity" + index);
            var currentQuantity = parseInt(quantitySpan.innerText);
            if (currentQuantity > 1) {
                quantitySpan.innerText = currentQuantity - 1;
                updateTotal();
                var customer_id = document.getElementById("customer_id").value;
                // Chuyển hướng và truyền dish_id trong URL
                window.location.href = "CookieAddToCart?dish_id=" + dish_id + "&action=decrement&customer_id=" + customer_id;
            }
        }

        //update total price
        function updateTotal() {
            var total = 0;
            var quantities = document.querySelectorAll('[id^="quantity"]');
            var prices = document.querySelectorAll('td:nth-child(2)');

            for (var i = 0; i < quantities.length; i++) {
                var quantity = parseInt(quantities[i].innerText);
                var price = parseFloat(prices[i].innerText);
                total += quantity * price;
            }

            document.getElementById("total").innerText = "Total: " + total.toFixed(2);
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

        function no1() {
            document.getElementById("modalcheckout").style.display = "none";
        }
        function delete1(index) {
            var modal = document.getElementById("modaldelete" + index);
            modal.style.display = "block";
        }

        function yes2(index, customer_id) {

            window.location.href = "RemoveItem?index=" + index + "&customer_id=" + customer_id;
        }
        //khac me chi o tren dau ma k duoc ta dcm
        function no2(index) {
            var modal = document.getElementById("modaldelete" + index);
            modal.style.display = "none";
        }

    </script>
</html>
