<%-- 
    Document   : guest
    Created on : Sep 11, 2023, 4:01:00 PM
    Author     : HAU
--%>

<%@page import="model.Dish"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <title> 420ent </title>
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
        <a href="ListProductGuest">List Product</a> 
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
                        <li><a href="#hero">Home</a></li>
                        <li><a href="#about">About</a></li>
                        <li><a href="#menu">Menu</a></li>
                        <li><a href="#contact">Contact</a></li>
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
        <!--Modal-->
        <div class="modal" id="myModal">
            <div class="modal-content" style="width: 30%;">
                <h5 style=" margin-bottom: 20px;">Bạn có chắc chắn bạn muốn thoát?</h5>
                <div class="d-flex btnlogout">
                    <button onclick="yes()" type="button" class="btn btn-warning">Yes</button>
                    <button onclick="no()" type="button" class="btn btn-success">No</button>
                </div>
            </div>

        </div>
        <!--End Modal-->
        <!-- ======= Hero Section ======= -->
        <section id="hero" class="hero d-flex align-items-center section-bg">
            <div class="container">
                <div class="row justify-content-between gy-5">
                    <div class="col-lg-5 order-2 order-lg-1 d-flex flex-column justify-content-center align-items-center align-items-lg-start text-center text-lg-start">
                        <h2 data-aos="fade-up">Enjoy Your Healthy<br>Delicious Food</h2>
                        <p data-aos="fade-up" data-aos-delay="100">Sed autem laudantium dolores. Voluptatem itaque ea consequatur eveniet. Eum quas beatae cumque eum quaerat.</p>
                        <div class="d-flex" data-aos="fade-up" data-aos-delay="200">
                            <a href="#book-a-table" class="btn-book-a-table">Book a Table</a>
                            <a href="https://www.youtube.com/watch?v=LXb3EKWsInQ" class="glightbox btn-watch-video d-flex align-items-center"><i class="bi bi-play-circle"></i><span>Watch Video</span></a>
                        </div>
                    </div>
                    <div class="col-lg-5 order-1 order-lg-2 text-center text-lg-start">
                        <img src="img/hero-img.png" class="img-fluid" alt="" data-aos="zoom-out" data-aos-delay="300">
                    </div>
                </div>
            </div>
        </section><!-- End Hero Section -->

        <main id="main">

            <!-- ======= About Section ======= -->
            <section id="about" class="about">
                <div class="container" data-aos="fade-up">

                    <div class="section-header">
                        <h2>About Us</h2>
                        <p>Learn More <span>About Us</span></p>
                    </div>

                    <div class="row gy-4">
                        <div class="col-lg-7 position-relative about-img" style="background-image: url(img/about.jpg) ;" data-aos="fade-up" data-aos-delay="150">
                        </div>
                        <div class="col-lg-5 d-flex align-items-end" data-aos="fade-up" data-aos-delay="300">
                            <div class="content ps-0 ps-lg-5">
                                <p class="fst-italic">
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
                                    magna aliqua.
                                </p>
                                <ul>
                                    <li><i class="bi bi-check2-all"></i> Ullamco laboris nisi ut aliquip ex ea commodo consequat.</li>
                                    <li><i class="bi bi-check2-all"></i> Duis aute irure dolor in reprehenderit in voluptate velit.</li>
                                    <li><i class="bi bi-check2-all"></i> Ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate trideta storacalaperda mastiro dolore eu fugiat nulla pariatur.</li>
                                </ul>
                                <p>
                                    Ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                                    velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident
                                </p>

                                <div class="position-relative mt-4">
                                    <img src="img/about-2.jpg" class="img-fluid" alt="">
                                    <a href="https://www.youtube.com/watch?v=LXb3EKWsInQ" class="glightbox play-btn"></a>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </section><!-- End About Section -->

            <!-- ======= Why Us Section ======= -->
            <section id="why-us" class="why-us section-bg">
                <div class="container" data-aos="fade-up">
                    <div class="row gy-4">


                        <div class="col-lg-4" data-aos="fade-up" data-aos-delay="100">
                            <div class="why-box">
                                <h3>Why Choose Yummy?</h3>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit
                                    Asperiores dolores sed et. Tenetur quia eos. Autem tempore quibusdam vel necessitatibus optio ad corporis.
                                </p>
                                <div class="text-center">
                                    <a href="#" class="more-btn">Learn More <i class="bx bx-chevron-right"></i></a>
                                </div>
                            </div>
                        </div><!-- End Why Box -->

                        <div class="col-lg-8 d-flex align-items-center">
                            <div class="row gy-4">

                                <div class="col-xl-4" data-aos="fade-up" data-aos-delay="200">
                                    <div class="icon-box d-flex flex-column justify-content-center align-items-center">
                                        <i class="bi bi-clipboard-data"></i>
                                        <h4>Corporis voluptates officia eiusmod</h4>
                                        <p>Consequuntur sunt aut quasi enim aliquam quae harum pariatur laboris nisi ut aliquip</p>
                                    </div>
                                </div><!-- End Icon Box -->

                                <div class="col-xl-4" data-aos="fade-up" data-aos-delay="300">
                                    <div class="icon-box d-flex flex-column justify-content-center align-items-center">
                                        <i class="bi bi-gem"></i>
                                        <h4>Ullamco laboris ladore pan</h4>
                                        <p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt</p>
                                    </div>
                                </div><!-- End Icon Box -->

                                <div class="col-xl-4" data-aos="fade-up" data-aos-delay="400">
                                    <div class="icon-box d-flex flex-column justify-content-center align-items-center">
                                        <i class="bi bi-inboxes"></i>
                                        <h4>Labore consequatur incidid dolore</h4>
                                        <p>Aut suscipit aut cum nemo deleniti aut omnis. Doloribus ut maiores omnis facere</p>
                                    </div>
                                </div><!-- End Icon Box -->

                            </div>
                        </div>

                    </div>

                </div>
            </section><!-- End Why Us Section -->

            <!-- ======= Stats Counter Section ======= -->
            <section id="stats-counter" class="stats-counter">
                <div class="container" data-aos="zoom-out">

                    <div class="row gy-4">

                        <div class="col-lg-3 col-md-6">
                            <div class="stats-item text-center w-100 h-100">
                                <span data-purecounter-start="0" data-purecounter-end="${customers}" data-purecounter-duration="1" class="purecounter"></span>
                                <p>Clients</p>
                            </div>
                        </div><!-- End Stats Item -->

                        <div class="col-lg-3 col-md-6">
                            <div class="stats-item text-center w-100 h-100">
                                <span data-purecounter-start="0" data-purecounter-end="${products}" data-purecounter-duration="1" class="purecounter"></span>
                                <p>Products</p>
                            </div>
                        </div><!-- End Stats Item -->

                        <div class="col-lg-3 col-md-6">
                            <div class="stats-item text-center w-100 h-100">
                                <span data-purecounter-start="0" data-purecounter-end="${stores}" data-purecounter-duration="1" class="purecounter"></span>
                                <p>Stores</p>
                            </div>
                        </div><!-- End Stats Item -->

                        <div class="col-lg-3 col-md-6">
                            <div class="stats-item text-center w-100 h-100">
                                <span data-purecounter-start="0" data-purecounter-end="${workers}" data-purecounter-duration="1" class="purecounter"></span>
                                <p>Workers</p>
                            </div>
                        </div><!-- End Stats Item -->

                    </div>

                </div>
            </section><!-- End Stats Counter Section -->

            <!-- ======= Menu Section ======= -->

            <section id="menu" class="menu">
                <div class="container"  data-aos="fade-up">

                    <div class="section-header">
                        <h2>Our Menu</h2>
                        <p>Check Our <span>FastFood Menu</span></p>
                    </div>

                    <ul class="nav nav-tabs d-flex justify-content-center" data-aos="fade-up" data-aos-delay="200">

                        <li class="nav-item">
                            <a class="nav-link active show" data-bs-toggle="tab" data-bs-target="#menu-starters">
                                <h4>Tất cả</h4>
                            </a>
                        </li><!-- End tab nav item -->
                        <li class="nav-item">
                            <a  class="nav-link" data-bs-toggle="tab"data-id="searchType" data-value="f" 
                                data-bs-target="#menu-food" >
                                <h4>Food</h4>
                            </a>
                        </li><!-- End tab nav item -->

                        <li class="nav-item">
                            <a  class="nav-link" data-bs-toggle="tab"data-id="searchType" data-value="d" 
                                data-bs-target="#menu-drink" >
                                <h4>Drink</h4>
                            </a>
                        </li><!-- End tab nav item -->
                        <li class="nav-item">
                            <a  class="nav-link" data-bs-toggle="tab"data-id="searchType" data-value="i" 
                                data-bs-target="#menu-ice" >
                                <h4>Icecream</h4>
                            </a>
                        </li><!-- End tab nav item -->
                        <li class="nav-item">
                            <a  class="nav-link" data-bs-toggle="tab"data-id="searchType" data-value="c" 
                                data-bs-target="#menu-combo" >
                                <h4>Combo</h4>
                            </a>
                        </li><!-- End tab nav item -->

                        <!--                        <div style="display:flex;gap:0px; margin-left: 20px;">-->

                        <!-- End tab nav item -->
                        <form class="box" action="searchAjax" method="get" style="display:flex; gap:5px; margin-left: 15px; ">

                            <input class="form-control" aria-label="Text input with segmented dropdown button" type="text" id="searchInput" value="" oninput="searchByName()" name="txt" />
                            <button class="btn btn-light" type="button"><i class="fa-solid fa-magnifying-glass"></i></button>
                        </form>
                </div>
                </ul>


                <div class="tab-content" data-aos="fade-up" data-aos-delay="300">

                    <div class="tab-pane fade active show" id="menu-starters">

                        <div class="tab-header text-center" >
                            <p>Menu</p>
                            <h3>Tất cả</h3>
                        </div>
                        <div class="row gy-5" id="content" >
                            <c:forEach var="dish" items="${listss}" >
                                <div class="col-lg-4 menu-item" >
                                    <a href="img/${dish.getImage()}" class="glightbox"><img src="img/${dish.getImage()}" class="menu-img img-fluid" alt=""></a>
                                    <h4><a href="DetailProduct?pid=${dish.getDish_id()}&customer_id=<%=customer_id%>" title="View Product">${dish.getName()}</a></h4>

                                    <p class="ingredients">
                                        ${dish.getInfor()}
                                    </p>
                                    <p class="price" >
                                        ${dish.getPrice()} đ
                                    </p>           
                                </div><!-- Menu Item -->

                            </c:forEach>

                        </div>

                    </div><!-- End Starter Menu Content -->

                    <div class="tab-pane fade" id="menu-food">

                        <div class="tab-header text-center">
                            <p>Menu</p>
                            <h3>Food</h3>
                        </div>

                        <div class="row gy-5" id="content1">


                            <c:forEach var="dish" items="${listTypeF}" >

                                <div class="col-lg-4 menu-item" >
                                    <a href="img/${dish.getImage()}" class="glightbox"><img src="img/${dish.getImage()}" class="menu-img img-fluid" alt=""></a>
                                    <h4><a href="DetailProduct?pid=${dish.getDish_id()}&customer_id=<%=customer_id%>" title="View Product">${dish.getName()}</a></h4>
                                    <p class="ingredients">
                                        ${dish.getInfor()}
                                    </p>
                                    <p class="price" >
                                        $ ${dish.getPrice()}
                                    </p>           
                                </div><!-- Menu Item -->
                            </c:forEach>
                            <c:if test="${empty listTypeF}">
                                <p>Danh sách món ăn hiện đang trống.</p>
                            </c:if>
                        </div>
                    </div><!-- End Breakfast Menu Content -->

                    <div class="tab-pane fade" id="menu-drink">

                        <div class="tab-header text-center">
                            <p>Menu</p>
                            <h3>Drink</h3>
                        </div>

                        <div class="row gy-5" id="content2">

                            <c:forEach var="dish" items="${listPriceD}" >

                                <div class="col-lg-4 menu-item" >
                                    <a href="img/${dish.getImage()}" class="glightbox"><img src="img/${dish.getImage()}" class="menu-img img-fluid" alt=""></a>
                                    <h4><a href="DetailProduct?pid=${dish.getDish_id()}&customer_id=<%=customer_id%>" title="View Product">${dish.getName()}</a></h4>
                                    <p class="ingredients">
                                        ${dish.getInfor()}
                                    </p>
                                    <p class="price" >
                                        $ ${dish.getPrice()}
                                    </p>           
                                </div><!-- Menu Item -->
                            </c:forEach>
                            <c:if test="${empty listPriceD}">
                                <p>Danh sách món ăn hiện đang trống.</p>
                            </c:if>

                        </div>
                    </div><!-- End Lunch Menu Content -->

                    <div class="tab-pane fade" id="menu-ice">

                        <div class="tab-header text-center">
                            <p>Menu</p>
                            <h3>Ice cream</h3>
                        </div>

                        <div class="row gy-5" id="content3">

                            <c:forEach var="dish" items="${listPriceI}" >

                                <div class="col-lg-4 menu-item" >
                                    <a href="img/${dish.getImage()}" class="glightbox"><img src="img/${dish.getImage()}" class="menu-img img-fluid" alt=""></a>
                                    <h4><a href="DetailProduct?pid=${dish.getDish_id()}&customer_id=<%=customer_id%>" title="View Product">${dish.getName()}</a></h4>
                                    <p class="ingredients">
                                        ${dish.getInfor()}
                                    </p>
                                    <p class="price" >
                                        $ ${dish.getPrice()}
                                    </p>           
                                </div><!-- Menu Item -->
                            </c:forEach>
                            <c:if test="${empty listPriceD}">
                                <p>Danh sách món ăn hiện đang trống.</p>
                            </c:if>

                        </div>
                    </div>
                    <div class="tab-pane fade" id="menu-combo">

                        <div class="tab-header text-center">
                            <p>Menu</p>
                            <h3>Combo</h3>
                        </div>

                        <div class="row gy-5" id="content4">

                            <c:forEach var="dish" items="${listTypeC}" >   

                                <div class="col-lg-4 menu-item"  >

                                    <a href="img/${dish.getImage()}" class="glightbox"><img src="img/${dish.getImage()}" class="menu-img img-fluid" alt=""></a>
                                    <h4><a href="DetailProduct?pid=${dish.getDish_id()}&customer_id=<%=customer_id%>" title="View Product">${dish.getName()}</a></h4>
                                    <p class="ingredients">
                                        ${dish.getInfor()}
                                    </p>
                                    <p class="price" >
                                        $ ${dish.getPrice()}
                                    </p>   
                                </div><!-- Menu Item -->
                            </c:forEach>
                            <c:if test="${empty listTypeC}">
                                <p>Danh sách món ăn hiện đang trống.</p>
                            </c:if>

                        </div>
                    </div>
                    <!-- End Dinner Menu Content -->

                </div>

                </div>
            </section><!-- End Menu Section -->

            <!-- ======= Contact Section ======= -->
            <section id="contact" class="contact">
                <div class="container" data-aos="fade-up">

                    <div class="section-header">
                        <h2>Contact</h2>
                        <p>Need Help? <span>Contact Us</span></p>
                    </div>

                    <div class="mb-3">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d15343.455683547993!2d108.2605568!3d15.9684812!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3142116949840599%3A0x365b35580f52e8d5!2zxJDhuqFpIGjhu41jIEZQVCAoRlBUIHVuaXZlcnNpdHkp!5e0!3m2!1sen!2s!4v1696228489889!5m2!1sen!2s"  style="border:0; width: 100%; height: 350px;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                    </div><!-- End Google Maps -->

                    <div class="row gy-4">

                        <div class="col-md-6">
                            <div class="info-item  d-flex align-items-center">
                                <i class="icon bi bi-map flex-shrink-0"></i>
                                <div>
                                    <h3>Our Address</h3>
                                    <p>FPT University, Khu đô thị FPT Đà Nẵng</p>
                                </div>
                            </div>
                        </div><!-- End Info Item -->

                        <div class="col-md-6">
                            <div class="info-item d-flex align-items-center">
                                <i class="icon bi bi-envelope flex-shrink-0"></i>
                                <div>
                                    <h3>Email Us</h3>
                                    <p>haulvdev@gmail.com</p>
                                </div>
                            </div>
                        </div><!-- End Info Item -->

                        <div class="col-md-6">
                            <div class="info-item  d-flex align-items-center">
                                <i class="icon bi bi-telephone flex-shrink-0"></i>
                                <div>
                                    <h3>Call Us</h3>
                                    <p>+84 334807725</p>
                                </div>
                            </div>
                        </div><!-- End Info Item -->

                        <div class="col-md-6">
                            <div class="info-item  d-flex align-items-center">
                                <i class="icon bi bi-share flex-shrink-0"></i>
                                <div>
                                    <h3>Opening Hours</h3>
                                    <div><strong>Mon-Sat:</strong> 11AM - 23PM;
                                        <strong>Sunday:</strong> Closed
                                    </div>
                                </div>
                            </div>
                        </div><!-- End Info Item -->

                    </div>


            </section><!-- End Contact Section -->

        </main><!-- End #main -->

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

        <a href="#" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

        <div id="preloader"></div>

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
    <script  type="text/javascript">
                        document.getElementById("form-dish").addEventListener("click", function (event) {
                            event.preventDefault(); // Ngăn chặn form gửi đi mặc định
                            window.location.href = this.action; // Chuyển hướng đến trang được chỉ định trong thuộc tính "action" của form
                        });
                        function logout() {
                            document.getElementById("myModal").style.display = "block";
                        }
                        function no() {
                            document.getElementById("myModal").style.display = "none";
                        }
                        function yes() {

                            window.location.href = "ListProductGuest";
                        }

                        function searchByName() {
                            var txtSearch = document.getElementById("searchInput").value; // Lấy giá trị từ ô input
                            $.ajax({
                                url: "/FastFoodStore/searchAjax",
                                type: "get",
                                data: {
                                    txt: txtSearch
                                },
                                success: function (data) {
                                    // Sử dụng ID "content" để cập nhật nội dung
                                    var row = document.getElementById("content");
                                    row.innerHTML = data;
                                    var row1 = document.getElementById("content1");
                                    row1.innerHTML = data;
                                    var row2 = document.getElementById("content2");
                                    row2.innerHTML = data;
                                    var row3 = document.getElementById("content3");
                                    row3.innerHTML = data;
                                    var row4 = document.getElementById("content4");
                                    row4.innerHTML = data;
                                },
                                error: function (xhr) {
                                    // Xử lý lỗi nếu cần
                                }
                            });
                        }
//------------------------------------------------------------------------------
                        document.addEventListener("DOMContentLoaded", function () {
                            // Lắng nghe sự kiện khi người dùng bấm vào thẻ a
                            document.getElementById("submitLink").addEventListener("click", function (e) {
                                e.preventDefault(); // Ngăn chặn thực hiện hành động mặc định của thẻ a (chuyển đến URL)
                                // Lấy ra form bằng ID
                                var form = document.getElementById("myForm");

                                // Gửi form
                                form.submit();
                            });
                        });
//------------------------------------------------------------------------------
                        var compareSelect = document.getElementById("compareSelect");
                        var submitButton = document.getElementById("submitButton");

                        // Thêm sự kiện change cho thẻ select
                        compareSelect.addEventListener("change", function () {
                            // Hiển thị nút gửi và gửi form khi sự kiện change xảy ra
                            submitButton.style.display = "block";
                            submitButton.click();
                        });
    </script>

</html>