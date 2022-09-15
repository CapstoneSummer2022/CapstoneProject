<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng ký</title>
        <link rel="stylesheet" href="/css/style.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Alef' rel='stylesheet'>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
      <body>    
        <!--Page Header-->
        <jsp:include page="header.jsp"/>
    
        <!--Page Content-->
        <main>
          <div class="sign_form">
              <div id="signup_content">
                <c:if test="${not empty error}">
                  <p class="error" style="display: block; background-color: pink; text-align: center; padding: 10px; border-radius: 5px;">
                    ${error}
                  </p>
                </c:if>

                <h3>Đăng ký</h3>
                <form action="/signup" method="post" name="signupForm" onsubmit="return validateRegisterForm();">
                  <label for="email">Email</label>
                  <input type="text" id="email" name="email" placeholder="email@example.com"/>
                  <p id="email-error" class="error"></p>
                  <label for="password">Mật khẩu</label>
                  <input type="password" id="pwd" name="pwd"/>
                  <p id="password-error" class="error"></p>
                  <label for="repassword">Mật khẩu xác nhận</label>
                  <input type="password" id="repwd" name="repwd"/>
                  <p id="repassword-error" class="error"></p>
                  <input type="submit" value="Đăng ký"/>
                </form> 
                <div class="switch_sign">
                    Bạn có tài khoản rồi? <a href="/auth/signin" id="signin">Đăng nhập</a>
                </div>    
              </div>
          </div>  
        </main>
    
        <!--Page Footer-->
        <jsp:include page="footer.jsp"/>

        <script src="/js/validate.js"></script>
      </body>
</html>