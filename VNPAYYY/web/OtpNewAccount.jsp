<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>

        <link
            href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
            rel="stylesheet" id="bootstrap-css">
        <script
        src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

        <style type="text/css">
            .form-gap {
                padding-top: 70px;
            }
        </style>
    </head>

    <body>
        <div class="form-gap"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="text-center">
                                <h3>
                                    <i class="fa fa-lock fa-4x"></i>
                                </h3>
                                <h2 class="text-center">Enter OTP</h2>
                                <%
                                    if (request.getAttribute("message") != null) {
                                        out.print("<p class='text-danger ml-1'>" + request.getAttribute("message") + "</p>");
                                    }

                                %>
                                <%// Lấy giá trị otp từ session attribute
                                    int otp = (int) session.getAttribute("otp");
                                    String username = request.getParameter("username");
                                     String password = request.getParameter("password");
                                     String email = request.getParameter("email");
// Bây giờ bạn có thể sử dụng giá trị otp ở đây trong trang EnterOtp.jsp
                                %>
                                <p id="countdown"></p>
                                <div class="panel-body">
                                    
                                       
                                    
                                    <form id="register-form" action="ValidateOtpNewAccount" role="form" autocomplete="off"
                                          class="form" method="POST">
                                      <input type="hidden" id ="otp" name="otp" value="<%= otp %>" />
                                       <input type="hidden" id ="username" name="username" value="<%= username %>" />
                                       <input type="hidden" id ="password" name="password" value="<%= password %>" />
                                       <input type="hidden" id ="email" name="email" value="<%= email %>" />
                                       <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i
                                                        class="glyphicon glyphicon-envelope color-blue"></i></span> 
                                                <input id="otp1"  name="otp1" placeholder="Enter OTP"
                                                       class="form-control" type="number" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <input name="recover-submit"
                                                   class="btn btn-lg btn-primary btn-block"
                                                   value="Create new account" type="submit">
                                        </div>

                                        <input type="hidden" class="hide" name="token" id="token"
                                               value=""> 
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
              <script type="text/javascript">
    // Lấy giá trị otp từ session attribute
    var otp = <%= otp %>;
    
    // Đếm ngược thời gian
    var countdown = 100; // 10 giây
    var countdownInterval = setInterval(function() {
        countdown--;
        document.getElementById("countdown").innerHTML = "The OTP code is valid  for " + countdown + " seconds";
        if (countdown <= 0) {
            // Khi thời gian hết, thực hiện gửi yêu cầu đến ValidateOtp servlet
            clearInterval(countdownInterval);
            document.getElementById("otp").value = 0; // Đặt giá trị otp1 thành 0
            document.forms["register-form"];
            // Gửi form
        }
    }, 1000);
</script>                         
    </body>
</html>