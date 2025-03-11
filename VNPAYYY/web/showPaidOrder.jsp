<%-- 
    Document   : showSucceedOrder
    Created on : Oct 26, 2023, 3:18:41 PM
    Author     : Asus
--%>

<%@page import="dal.CustomerDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:nth-child(odd) {
                background-color: #ffffff;
            }
        </style>
    </head>
    <body>
        <h1>Show paid order</h1>

        <%

            CustomerDAO customerDAO = new CustomerDAO();
        %>
        <%
            Cookie[] cookies = request.getCookies(); // Get the array of cookies from the request
            int storeId = 0;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("store_id".equals(cookie.getName())) {
                        String storeIdValue = cookie.getValue();
                        storeId = Integer.parseInt(storeIdValue);

                    }
                }
            }
        %>
        <a href="manageStore.jsp"  >Trở về</a> 
        <table>
            <thead>
                <tr>
                    <td>Customer</td>
                    <td>Order ID</td>
                    <td>Phone Number</td>
                    <td>Address</td>
                    <td>Date</td>
                    <td>Total</td>
                    <td>Order Status</td>
                    <td>Payment status</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${paidList}">
                    <tr>
                        <c:set var="customer_id" value="${order.getCustomer_id()}"></c:set>
                        <td><%= customerDAO.getCustomer((int) pageContext.getAttribute("customer_id")).getUsername()%></td>

                        <td>${order.getOrder_id()}</td>
                        <td>${order.getCustomer_phone()}</td>
                        <td>${order.getCustomer_address()}</td>
                        <td>${order.getDate()}</td>
                        <td>${order.getTotalmoney()}</td>
                        <td>${order.getStatus()}</td>   
                        <td>${order.getPaymentStatus()}</td>   

                        <td>
                            <form action="ShowPaidOrder" method="Post">
                                <input type="hidden" name="order_id" value="${order.getOrder_id()}">
                                <button type="submit" name="viewButton" value="view" class="btn btn-success">Xem chi tiết</button>
                            </form>

                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </body>
</html>
