<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Trang chủ</title>
        <link rel="stylesheet" href="../css/style.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Alef' rel='stylesheet'>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>    
        <!--Page Header-->
        <jsp:include page="header.jsp"/>
    
        <!--Page Content-->
        <main>
          <div id="contact_content">
            <table>
              <tr>
                <td style="width: 20%"></td>
                <td style="width: 15%"class="label">Số điện thoại:</td>
                <td class="field">0962352843</td>
              </tr>
              <tr>
                <td></td>
                <td class="label">Email:</td>
                <td class="field">electroco@gmail.com</td>
              </tr>
              <tr>
                <td></td>
                <td class="label">Địa chỉ:</td>
                <td class="field">444 Xuân Phương, P. Xuân Phương, Q. Nam Từ Liêm, Tp. Hà Nội</td>
              </tr>
              <tr>
                <td colspan="3">
                  <iframe style="width: 50%; height: 500px;" src="https://maps.google.com/maps?q=444+Đ.+Xuân+Phương,Xuân+Phương,Từ+Liêm,Hà+Nội,Việt+Nam&t=&z=13&ie=UTF8&iwloc=&output=embed" frameborder="0"
                  style="border:0" allowfullscreen></iframe>
                </td>
              </tr>
            </table>
          </div>
        </main>
    
        <!--Page Footer-->
        <jsp:include page="footer.jsp"/>

      </body>
</html>