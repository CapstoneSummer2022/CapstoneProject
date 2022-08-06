<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>

</head>
<body>
<div id="main-content">
    <div id="signin-form">
        <div id="header-for-form">
            <h2 style="margin: 0; padding: 0;">ECOM</h2>
        </div>

        <%--@elvariable id="account" type="com.example.electriccomponentsshop.dto.AccountDTO"--%>
        <form:form action="/signin" method="POST" modelAttribute="account" style="width: 100%;">
            <div class="form-elements">
                <form:input type="email" id="email" name ="email" placeholder="Email" path="email"/>
                <form:errors path="email" element="span" />
            </div>
            <div class="form-elements">
                <input type="password" id="pwd" name="password" placeholder="Mật khẩu"/>
                <form:errors path="password" />
            </div>
            <div class="form-elements">
                <input type="checkbox" id="remember" value="Nhớ mật khẩu"><label for="remember">Nhớ mật khẩu</label>
                <a href="" id="change-pwd" style="float: right;">Quên mật khẩu</a><br>
            </div>
            <div class="form-elements">
                <input type="submit" value="Đăng nhập"/>
            </div>
            <hr style="color: grey;">
            <div class="form-elements">
                Bạn chưa có tài khoản?<a href="/signup.html" id="signup">Đăng ký</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>