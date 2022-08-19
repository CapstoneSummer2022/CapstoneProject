<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Quản lý xuất kho | Quản trị</title>
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
    <header class="app-header">
        <!-- Navbar Right Menu-->
        <ul class="app-nav">
            <!-- User Menu-->
            <div class="app-nav__button">
                <div class="app-nav__item"><i class='bx bx-cog'></i></div>
                <div class="app-nav__item-content">
                    <a href="personal-info.html">Thông tin cá nhân</a>
                    <a href="#">Đăng xuất</a>
                </div>
            </div>
        </ul>
    </header>
    <!-- Sidebar menu-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <aside class="app-sidebar">
        <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="resources/images/avatar.jpg"
                width="50px" alt="User Image">
            <div>
                <p class="app-sidebar__user-name"><b>Bùi Minh Hiệu</b></p>
                <p class="app-sidebar__user-designation">Quản lý</p>
            </div>
        </div>
        <hr>
        <ul class="app-menu">
            <li><a class="app-menu__item" href="home-page.html"><i class='app-menu__icon bx bx-cart-alt'></i>
                    <span class="app-menu__label">Trang chủ</span></a></li>
            <li><a class="app-menu__item" href="system-account-management.html"><i
                        class='app-menu__icon bx bx-id-card'></i>
                    <span class="app-menu__label">Quản lý tài khoản</span>
                </a>
            </li>
            <li><a class="app-menu__item" href="category-management.html"><i
                        class='app-menu__icon bx bx-category'></i><span class="app-menu__label">Quản lý danh
                        mục</span></a></li>
            <li><a class="app-menu__item" href="product-management.html"><i
                        class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản
                        phẩm</span></a>
            </li>
            <li><a class="app-menu__item" href="supplier-management.html"><i
                        class='app-menu__icon bx bxs-user-account'></i><span class="app-menu__label">Quản lý nhà cung
                        cấp
                    </span></a></li>
            <li><a class="app-menu__item active" href="warehouse-management.html"><i
                        class='app-menu__icon bx bx-building-house'></i><span class="app-menu__label">Quản lý kho
                        hàng
                    </span></a>
                <ul class="sub-app-menu">
                    <li><a class="sub-app-menu_item" href="warehouse-management.html"><span class="app-menu__label">Kho
                                hàng</span></a></li>
                    <li><a class="sub-app-menu_item" href="warehouse-import-management.html"><span
                                class="app-menu__label">Quản lý
                                nhập kho</span></a></li>
                    <li><a class="sub-app-menu_item sub-item-active" href="warehouse-export-management.html"><span
                                class="app-menu__label">Quản lý
                                xuất kho</span></a></li>
                </ul>
            </li>
            <li><a class="app-menu__item" href="order-management.html"><i class='app-menu__icon bx bx-task'></i><span
                        class="app-menu__label">Quản lý đơn hàng</span></a></li>
            <li><a class="app-menu__item" href="feedback-management.html"><i
                        class='app-menu__icon bx bx-user-voice'></i><span class="app-menu__label">Feedback</span></a>
            </li>
            <li><a class="app-menu__item" href="report.html"><i class='app-menu__icon bx bx-pie-chart-alt-2'></i><span
                        class="app-menu__label">Báo cáo thống kê</span></a>
            </li>
        </ul>
    </aside>
    <main class="app-content">
        <div class="app-title">
            <ul class="app-breadcrumb breadcrumb side">
                <li class="breadcrumb-item active"><a href="warehouse-export-management.html"><b>Quản lý xuất
                            kho</b></a></li>
            </ul>
            <div id="clock"></div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <div class="tile-body">
                        <div class="row element-button">
                            <div class="col-sm-2">
                                <a class="btn btn-add btn-sm" href="add-warehouse-export.html" title="Thêm"><i
                                        class="fas fa-plus"></i>
                                    Thêm giao dịch mới</a>
                            </div>
                        </div>
                        <div class="search-row">
                            <form action="">
                                <div class="search-container">
                                    <input class="form-control" type="text" placeholder="Tìm kiếm" name="search">
                                    <button type="submit"><i class="fa fa-search"></i></button>
                                </div>
                            </form>
                        </div>
                        <table class="table table-hover table-bordered" cellpadding="0" cellspacing="0"
                            id="sampleTable">
                            <thead>
                                <tr>
                                    <th width="200">Mã giao dịch xuất kho</th>
                                    <th>Ngày xuất</th>
                                    <th width="100">Tuỳ chọn</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>20/07/2022</td>
                                    <td>
                                        <a href="setting-warehouse-export.html" class="btn btn-primary btn-sm edit"
                                            type="button" title="Sửa"><i class="fas fa-edit"></i></a>
                                        <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng"
                                            onclick="location.href='export-transaction-detail.html'"><i
                                                class="fa fa-info"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>20/07/2022</td>
                                    <td>
                                        <a href="setting-warehouse-export.html" class="btn btn-primary btn-sm edit"
                                            type="button" title="Sửa"><i class="fas fa-edit"></i></a>
                                        <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng"
                                            onclick="location.href='export-transaction-detail.html'"><i
                                                class="fa fa-info"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>20/07/2022</td>
                                    <td>
                                        <a href="setting-warehouse-export.html" class="btn btn-primary btn-sm edit"
                                            type="button" title="Sửa"><i class="fas fa-edit"></i></a>
                                        <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng"
                                            onclick="location.href='export-transaction-detail.html'"><i
                                                class="fa fa-info"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>20/07/2022</td>
                                    <td>
                                        <a href="setting-warehouse-export.html" class="btn btn-primary btn-sm edit"
                                            type="button" title="Sửa"><i class="fas fa-edit"></i></a>
                                        <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng"
                                            onclick="location.href='export-transaction-detail.html'"><i
                                                class="fa fa-info"></i></button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="pagination-row">
                            <div class="pagination-container">
                                <div class="dataTables_paginate paging_simple_numbers" id="sampleTable_paginate">
                                    <ul class="pagination">
                                        <li class="paginate_button page-item previous disabled"
                                            id="sampleTable_previous"><a href="#" aria-controls="sampleTable"
                                                data-dt-idx="0" tabindex="0" class="page-link">Lùi</a></li>
                                        <li class="paginate_button page-item active"><a href="#"
                                                aria-controls="sampleTable" data-dt-idx="1" tabindex="0"
                                                class="page-link">1</a></li>
                                        <li class="paginate_button page-item "><a href="#" aria-controls="sampleTable"
                                                data-dt-idx="2" tabindex="0" class="page-link">2</a></li>
                                        <li class="paginate_button page-item next" id="sampleTable_next"><a href="#"
                                                aria-controls="sampleTable" data-dt-idx="3" tabindex="0"
                                                class="page-link">Tiếp</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
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