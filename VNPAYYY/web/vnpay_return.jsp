<%@page import="vnpay.Config"%>

<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags must come first in the head; any other head content must come after these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>KẾT QUẢ THANH TOÁN</title>
        <!-- Bootstrap core CSS -->
        <link href="assets/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="assets/jumbotron-narrow.css" rel="stylesheet"> 
        <script src="assets/jquery-1.11.3.min.js"></script>
    </head>
    <body>
        <%
            //Begin process return from VNPAY
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }

            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = Config.hashAllFields(fields);

            // Lưu giá trị vào session
            session.setAttribute("vnp_SecureHash", vnp_SecureHash);
            session.setAttribute("signValue", signValue);


        %>

        <!--Begin display -->
        <div class="container">
            <div class="header clearfix">
                <h3 class="text-muted">KẾT QUẢ THANH TOÁN</h3>
            </div>
            <div class="table-responsive">
                <div class="form-group">
                    <label >Mã giao dịch thanh toán:</label>

                    <%                        Map<String, String> vnp_Params = (Map<String, String>) session.getAttribute("vnp_Params");
                        if (vnp_Params != null) {
                            String vnp_TxnRef = vnp_Params.get("vnp_TxnRef");
                    %>
                    <label><%= vnp_TxnRef%></label>
                    <%
                        }
                    %>
                </div>    
                <div class="form-group">
                    <label >Số tiền:</label>

                    <%
                        if (vnp_Params != null) {
                            String vnp_Amount = vnp_Params.get("vnp_Amount");
                            String chuoiMoi = vnp_Amount.substring(0, vnp_Amount.length() - 2);
                    %>
                    <label><%= chuoiMoi%></label>
                    <%
                        }
                    %>

                </div>  
                <div class="form-group">
                    <label >Mô tả giao dịch:</label>

                    <%
                        if (vnp_Params != null) {
                            String vnp_OrderInfo = vnp_Params.get("vnp_OrderInfo");
                    %>
                    <label><%= vnp_OrderInfo%></label>
                    <%
                        }
                    %>
                </div> 
                <div class="form-group" id="fault">
                    <label >Mã lỗi thanh toán:</label>
                    <label ID="error"><%= request.getParameter("vnp_ResponseCode")%></label>


                </div> 
                <div class="form-group">
                    <label >Mã giao dịch tại CTT VNPAY-QR:</label>
                    <label><%=request.getParameter("vnp_TransactionNo")%></label>

                </div> 
                <div class="form-group">
                    <label >Mã ngân hàng thanh toán:</label>

                    <%
                        if (vnp_Params != null) {
                            String vnp_BankCode = vnp_Params.get("vnp_BankCode");
                    %>
                    <label><%= vnp_BankCode%></label>
                    <%
                        }
                    %>

                </div> 
                <div class="form-group">
                    <label >Thời gian thanh toán:</label>

                    <%
                        if (vnp_Params != null) {
                            String vnp_PayDate = vnp_Params.get("vnp_CreateDate");
                    %>
                    <label><%= vnp_PayDate%></label>
                    <%
                        }
                    %>
                </div> 
                <div class="form-group">
                    <label>Tình trạng giao dịch:</label>
                    <label id="state">
                        <%
                            if (signValue.equals(session.getAttribute("vnp_SecureHash"))) {
                                if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                                    out.print("Thành công");

                                } else {
                                    out.print("Không thành công");
                                }
                            } else {
                                out.print("invalid signature");
                            }
                        %>
                    </label>
                </div>



                <form action="CheckOutServlet" method="GET" id="checkoutFormm">
                    <input type="hidden" name="store_id" value="<%= session.getAttribute("store_id")%>">
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
                        String phone = "";
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if ("phonee".equals(cookie.getName())) {
                                    phone = cookie.getValue();
                                    // Sử dụng giá trị của cookie "phone"
                                    break; // Tìm thấy "phone" cookie, thoát khỏi vòng lặp
                                }
                            }
                        }
                        String address = "";
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if ("addresss".equals(cookie.getName())) {
                                    address = cookie.getValue();
                                    // Sử dụng giá trị của cookie "phone"
                                    break; // Tìm thấy "phone" cookie, thoát khỏi vòng lặp
                                }
                            }
                        }
                        String name = String.valueOf(session.getAttribute("name"));
                       
                    %>
                    <input type="hidden" name="customer_id" id="customer_id" value="<%= customer_id%>">
                    <c:forEach var="dish" items="${cart6.getCartItems()}" varStatus="loop">
                        <input type="hidden" name="name" value="<%= name%>">
                        <input type="hidden" name="dish_id" value="${dish.getDish().getDish_id()}">
                        <input type="hidden" name="quantity" value="${dish.getQuantity()}">
                        <input type="hidden" name="total" value="${cart6.getTotal()}">
                        <input type="hidden" name="cod" id="cod" value="1">
                        <input type="hidden" id="pStatus" name="pStatus" value="Y"/>
                        <input type="hidden" id="address" name="address" value="<%= address%>"/>
                        <input type="hidden" id="phone" name="phone" value="<%= phone%>"/>
                    </c:forEach>
                    <button type="submit" class="btn btn-primary" id="confirmButton" >Xác nhận</button>
                </form>
            </div>
            <p>
                &nbsp;
            </p>
            <footer class="footer">
                <p>&copy; VNPAY 2020</p>
            </footer>
        </div>  
        <script>
            // Kiểm tra tình trạng giao dịch

// Automatically submit the form when the transaction is successful
            var stateLabel = document.getElementById("state");
            if (stateLabel.innerHTML === "Thành công") {
                document.getElementById("checkoutFormm").submit();
            }

            if (document.getElementById("state").innerHTML === "Thành công") {
                document.getElementById("error").innerHTML = "00";
            } else {
                document.getElementById("error").innerHTML = "24";
            }
        </script>
    </body>
</html>