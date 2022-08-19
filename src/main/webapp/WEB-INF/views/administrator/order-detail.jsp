<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Chi tiết đơn hàng | Quản trị</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link href="<c:url value="/css/main.css"/>" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <!-- or -->
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css"
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

</head>

<body onload="time()" class="app sidebar-mini rtl">
    <!-- Navbar-->
    <jsp:include page="header.jsp"/>

    <!-- Sidebar menu-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <jsp:include page="home-menu.jsp"/>
    <main class="app-content">
        <div class="app-title">
            <ul class="app-breadcrumb breadcrumb side">
                <li class="breadcrumb-item active"><a href="#"><b>Chi tiết đơn hàng</b></a></li>
            </ul>
            <div id="clock"></div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <div class="tile-body">
                        <table class="table table-hover table-bordered" id="sampleTable">
                            <thead>
                                <tr>
                                    <th>Mã sản phẩm</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Ảnh sản phẩm</th>
                                    <th>Danh mục</th>
                                    <th>Đơn giá</th>
                                    <th>Số lượng đặt hàng</th>
                                    <th>Thành tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1-B-3-2</td>
                                    <td>Dây jumper đực/đực 10cm</td>
                                    <td><img src="/img-sanpham/dolas.jpg" alt="" width="100px;"></td>
                                    <td>Dây kết nối</td>
                                    <td>22.650 đ</td>
                                    <td>6</td>
                                    <td>135900</td>
                                </tr>
                                <tr>
                                    <td>1-B-3-2</td>
                                    <td>Biến trở 1KΩ 1/2W 2.29mm</td>
                                    <td><img src="/img-sanpham/dolas.jpg" alt="" width="100px;"></td>
                                    <td>Biến trở</td>
                                    <td>12.000 đ</td>
                                    <td>6</td>
                                    <td>135900</td>
                                </tr>
                                <tr>
                                    <td>1-B-3-2</td>
                                    <td>Biến trở 5KΩ 1/2W 2.29mm</td>
                                    <td><img src="/img-sanpham/dolas.jpg" alt="" width="100px;"></td>
                                    <td>Biến trở</td>
                                    <td>22.650 đ</td>
                                    <td>7</td>
                                    <td>135900</td>
                                </tr>
                                <tr>
                                    <td>1-B-3-2</td>
                                    <td>Biến trở 50KΩ 1/2W 2.77mm</td>
                                    <td><img src="/img-sanpham/dolas.jpg" alt="" width="100px;"></td>
                                    <td>Biến trở</td>
                                    <td>22.650 đ</td>
                                    <td>5</td>
                                    <td>135900</td>
                                </tr>
                                <tr>
                                    <td colspan="6" style="font-weight: bold;">Tổng tiền: </td>
                                    <td>543600 đ</td>
                                </tr>
                                <tr>
                                    <td colspan="6" style="font-weight: bold;">Tình trạng: </td>
                                    <td><span class="badge bg-warning">Đang giao hàng</span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <!-- Essential javascripts for application to work-->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="src/jquery.table2excel.js"></script>
    <script src="js/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="js/plugins/pace.min.js"></script>
    <!-- Page specific javascripts-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
</body>

</html>