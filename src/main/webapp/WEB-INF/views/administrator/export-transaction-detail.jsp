<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Chi tiết giao dịch nhập kho | Quản trị</title>
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
                <li class="breadcrumb-item active"><a href="warehouse-import-management.html"><b>Quản lý nhập
                            kho</b></a></li>
                <li class="breadcrumb-item active"><b>Chi tiết giao dịch nhập kho</b></li>
            </ul>
            <div id="clock"></div>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="tile">
                    <h3 class="tile-title">Thông tin sản phẩm giao dịch</h3>
                    <div class="du--lieu-san-pham">
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th class="order-item-number">Mã sản phẩm</th>
                                    <th class="order-item-number" width="200">Tên sản phẩm</th>
                                    <th class="order-item-number" width="10">Số lượng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1-A-9-1</td>
                                    <td>Diode Xung Đôi SBL3040 TO-247 600V 30A</td>
                                    <td>12</td>
                                </tr>
                                <tr>
                                    <td>1-A-5-2</td>
                                    <td>Diode SS34 SMD</td>
                                    <td>13</td>
                                </tr>
                                <tr>
                                    <td>1-A-8-2</td>
                                    <td>Diode Cầu 25A Dẹt 1000V KBJ2510</td>
                                    <td>14</td>
                                </tr>
                                <tr>

                                    <td>1-A-7-5</td>
                                    <td>Tụ Cao Áp 102K 2kV</td>
                                    <td>15</td>
                                </tr>
                                <tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="tile">
                    <h3 class="tile-title">Thông tin nhập hàng</h3>
                    <div class="tile-body">
                        <div class="form-group col-md-12">
                            <label for="exampleSelect1" class="control-label">Ngày xuất</label>
                            <p>20/7/2022</p>
                        </div>
                        <div class="form-group col-md-12">
                            <label for="exampleSelect1" class="control-label">Kho xuất</label>
                            <p> XQCV+F65, P. Văn Quán, Hà Đông, Hà Nội</p>
                        </div>
                    </div>
                    <div class="middle-button">
                        <a type="submit" class="btn btn-primary create-order-button" type="button"
                            href="setting-warehouse-export.html">Cập
                            nhật</a>
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