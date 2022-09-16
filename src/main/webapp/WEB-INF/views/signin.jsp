<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Alef' rel='stylesheet'>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<header>
    <div id="first_row">
        <a href="home.html"><h1 id="store_name">Electroco</h1></a>
        <div id="searchByCate">
            <form action="" id="store_search">
                <select name="cate_search" id="cate_search">
                    <option value="">Tất cả</option>
                    <option value="">Diode</option>
                    <option value="">Transistor</option>
                    <option value="">Thysistor</option>
                    <option value="">Triac - Diac</option>
                    <option value="">IGBT</option>
                    <option value="">Fet</option>
                    <option value="">Tụ điện</option>
                    <option value="">Cuộn cảm</option>
                    <option value="">Điện trở</option>
                    <option value="">Thạch anh</option>
                    <option value="">Biến trở</option>
                    <option value="">Chiết áp</option>
                    <option value="">Nút bấm</option>
                    <option value="">Cầu chì</option>
                    <option value="">Led</option>
                    <option value="">Lcd</option>
                    <option value="">Cảm biến nhiệt độ</option>
                    <option value="">Cảm biến độ ẩm</option>
                    <option value="">Cảm biến ánh sáng</option>
                    <option value="">Cảm biến màu</option>
                    <option value="">Cảm biến chuyển động</option>
                    <option value="">Cảm biến nước</option>
                    <option value="">Cảm biến âm thanh</option>
                    <option value="">Cảm biến từ</option>
                    <option value="">IC cảm ứng</option>
                    <option value="">IC giao tiếp</option>
                    <option value="">IC real time</option>
                    <option value="">IC nguồn</option>
                    <option value="">IC audio</option>
                    <option value="">Dây kết nối</option>
                    <option value="">Cổng kết nối</option>
                </select>
                <input type="text" id="prod_search" name="prod_search" placeholder="Tìm kiếm..."/>
                <button type="submit"><i class="material-icons">search</i></button>
            </form>
        </div>
        <div id="store_icons">
            <div id="cart_icon">
                <a href="cart.html"><i class="material-icons">shopping_cart</i><span id="no_of_prods">1</span> </a> <a href="cart.html">Giỏ hàng</a>
            </div>
            <div id="avatar_icon">
                <a href="signin.html"><i class="material-icons">person</i></a><a href="signin.html">Đăng nhập</a> / <a href="signup.html">Đăng ký</a>
            </div>
        </div>
    </div>
    <div id="second_row">
        <ul id="nav">
            <li><a href="#">Danh mục sản phẩm <i class="material-icons">arrow_drop_down</i></a>
                <ul>
                    <li><a href="parent-cgr.html">Linh kiện bán dẫn </a>
                        <ul>
                            <li><a href="child-cgr.html">Transistor</a>
                            <li><a href="#">Diode</a>
                            <li><a href="#">Thysistor</a>
                            <li><a href="#">Diac - Triac</a>
                        </ul>
                    </li>
                    <li><a href="#">Linh kiện thụ động </a>
                        <ul>
                            <li><a href="child-cgr.html">Tụ điện</a>
                            <li><a href="#">Cuộn cảm</a>
                            <li><a href="#">Biến trở</a>
                            <li><a href="#">Điện trở</a>
                            <li><a href="#">Chiết áp</a>
                        </ul>
                    </li>
                    <li><a href="#">Cảm biến</a></li>
                </ul>
            </li>
            <li><a href="#">Nhà cung cấp <i class="material-icons">arrow_drop_down</i></a>
                <ul>
                    <li><a href="prod-by-supplier.html">DAEWOO</a></li>
                    <li><a href="#">ABECO</a></li>
                    <li><a href="#">DIGIKEY</a></li>
                </ul>
            </li>
            <li><a href="track-order.html">Theo dõi đơn hàng</a></li>
            <li><a href="contact.html">Thông tin liên hệ</a></li>
        </ul>
    </div>
</header>
<div id="main-content">
    <div id="signin-form">
        <div id="header-for-form">
            <h2 style="margin: 0; padding: 0;">ECOM</h2>
        </div>

        <%--@elvariable id="signinRequest" type="shop.db.dto.SigninRequest"--%>
        <form:form action="/auth/signin" method="POST" modelAttribute="signinRequest" style="width: 100%;">
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