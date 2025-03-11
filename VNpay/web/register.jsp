<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
      <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
      <link href="assets/img/favicon.png" rel="icon">
      
      
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="vendor/aos/aos.css" rel="stylesheet">
  <link href="vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

   
    <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">
  <link href="css/styleguest.css" rel="stylesheet">
  <title>FastFood</title>
  </head>
  <body>
      <header id="header" class="header fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">

      <a href="index.html" class="logo d-flex align-items-center me-auto me-lg-0">
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <!-- <img src="assets/img/logo.png" alt=""> -->
        <img class="img-navbar" src="img/logo.jpg" alt="">
      </a>

      <nav id="navbar" class="navbar">
        <ul>
          <li><a href="ListProductGuest">Home</a></li>
          <li><a href="#about">About</a></li>
          <li><a href="#menu">Menu</a></li>
          <li><a href="#contact">Contact</a></li>
        </ul>
      </nav><!-- .navbar -->

      <a class="btn-book-a-table" href="login.jsp"><i class="fa-regular fa-user fa-2xl"></i></a>
      <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
      <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

    </div>
  </header><!-- End Header -->
   <section>
  <div class="container py-5 h-100">
    <div class="row d-flex align-items-center justify-content-center h-100">
      <div class="col-md-8 col-lg-7 col-xl-6">
        <img src="https://cdna.artstation.com/p/assets/images/images/030/222/824/large/as-creative-labs-banner-test.jpg?1599974284"
          class="img-fluid" alt="Phone image">
      </div>
      <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
          <form action="RegisterServlet" method="POST" class="position-relative">
            <h3 style="font-size: 45px;
                font-weight: 700;">SIGN UP</h3>
          <!-- Email input -->
          <div class="form-outline mb-4">
            <input type="text" id="form1Example13" class="form-control form-control-lg" name="username" />
            <label class="form-label" for="form1Example13">User Account</label>
             <span class="form-message mt-2 position-absolute" style="color:red; right: 0; ">${tbTk}</span>
          </div>

          <div class="form-outline mb-4">
            <input type="text" id="form1Example13" class="form-control form-control-lg" name="email" />
            <label class="form-label" for="form1Example13">Enter Your Email</label>
            <span class="form-message mt-2 position-absolute" style="color:red; right: 0; ">${tbEmail}</span>
          </div>
          
          <div class="form-outline mb-4">
            <input type="password" id="form1Example13" class="form-control form-control-lg" name="password"/>
            <label class="form-label" for="form1Example13">Enter Your Password</label>
            <span class="form-message mt-2 position-absolute" style="color:red;right: 0;">${tbPass}</span>
          </div>
          
          <!-- Password input -->
          <div class="form-outline mb-4">
            <input type="password" id="form1Example23" class="form-control form-control-lg" name="password_confirmation"/>
            <label class="form-label" for="form1Example23">Enter Your Password Again</label>
            <span class="form-message mt-2 position-absolute" style="color:red; right: 0; ">${tbPass2}</span>
          </div>
          <div style="display: grid;">
          <!-- Submit button -->
          <button type="submit" class="btn btn-primary btn-lg btn-block mb-4 form-submit">Sign Up</button>
           <a class="btn btn-danger btn-lg btn-block" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/FastFoodStore/LoginGoogle&response_type=code
		   &client_id=231664848378-c2vupriqqpajggj6sj9b4dmq4jknu72g.apps.googleusercontent.com&approval_prompt=force"
            role="button">
           <i class="fa-brands fa-google" style="color: #ffffff; margin-right: 10px;"></i>Continue with Google
          </a>
          <p style="margin: 20px auto;">Do you already have an account ? <a class="btn btn-secondary" href="login.jsp">Login</a></p>
          </div>
        </form>
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
  <!-- End Footer -->

  <a href="#" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <div id="preloader"></div>
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