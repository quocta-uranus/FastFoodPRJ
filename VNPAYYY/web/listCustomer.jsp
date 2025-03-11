 <%-- 
    Document   : list
    Created on : Jun 1, 2023, 10:26:42 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FastFood</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    </head>
    <body>
        <h1>Danh sách khach hang</h1>
      
        <%@include file="menu.jsp" %>

            <a href="ListCustomerServlet">List Customer</a>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>phoneNumber</th>
                    <th>role</th>
                    <!--phân quyền chỉ dành cho admin-->
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${listc}">
                    <tr>
                        <td>${customer.getCustomer_id()}</td>
                        <td>${customer.getUsername()}</td>
                        <td>${customer.getPassword()}</td>
                        <td>${customer.getEmail()}</td>
                        <td>${customer.getPhoneNumber()}</td>
                        <td>${customer.getRole()}</td>
                    </tr>  
                </c:forEach>
            </tbody>
        </table>
        <h3>${message}</h3>
        <c:set var="message" value="${null}" scope="session"></c:set>
    </body>
</html>
