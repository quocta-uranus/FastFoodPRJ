<%-- 
    Document   : index
    Created on : Sep 28, 2023, 12:49:10 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FastFood</title>
    </head>
    <body>

        <form action="LoginGoogle" method="post">
            <!-- Các trường dữ liệu khác, ví dụ: -->
            <input type="hidden" name="verified_email" value="<%= request.getAttribute("verified_email")%>" />
            <input type="hidden" id ="id" name="id" value="<%= request.getAttribute("id")%>" />
            <input type="hidden" id ="email" name="email" value="<%= request.getAttribute("email")%>" />
            <!-- Các trường dữ liệu khác -->
            <input type="submit" id="autoSubmitButton" style="display: none;" />

        </form>


        <script type="text/javascript">
            // Chờ trang tải xong
            window.onload = function () {
                // Tự động gửi form khi trang tải xong
                document.getElementById("autoSubmitButton").click();
            };
        </script>
    </body>
</html>