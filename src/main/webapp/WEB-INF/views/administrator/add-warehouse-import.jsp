<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Thêm nhập kho | Quản trị</title>
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
                    <li><a class="sub-app-menu_item sub-item-active" href="warehouse-import-management.html"><span
                                class="app-menu__label">Quản lý
                                nhập kho</span></a></li>
                    <li><a class="sub-app-menu_item" href="warehouse-export-management.html"><span
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
                <li class="breadcrumb-item active"><a href="warehouse-import-management.html"><b>Quản lý nhập
                            kho</b></a></li>
                <li class="breadcrumb-item active"><b>Thêm giao dịch nhập kho</b></li>
            </ul>
            <div id="clock"></div>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="tile">
                    <h3 class="tile-title">Thông tin sản phẩm</h3>
                    <div class="row element-button">
                        <div class="col-sm-2">
                            <button class="btn btn-add btn-sm" title="Thêm" data-toggle="modal"
                                data-target="#productList"><i class="fas fa-plus"></i>
                                Thêm sản phẩm</button>
                        </div>
                    </div>
                    <div class="du--lieu-san-pham">
                        <table id="importProductList" class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>Mã sản phẩm</th>
                                <th class="order-item-number">Mã SKUD</th>
                                <th class="order-item-number" width="200">Tên sản phẩm</th>
                                <th class="order-item-number">Hình ảnh</th>
                                <th class="order-item-number" width="100">Đơn giá</th>
                                <th class="order-item-number" width="10">Số lượng</th>
                                <th class="order-item-number">Thành tiền</th>
                                <th width="100" class="order-item-number text-center" style="text-align: center; vertical-align: middle;">Tuỳ chọn</th>
                            </tr>
                            </thead>
                            <tbody>
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
                            <label for="exampleSelect1" class="control-label">Nhà cung cấp</label>
                            <select class="form-control" id="supplier">
                                <option selected disabled hidden>-- Nhà cung cấp --</option>
                                <option>DAEWOO</option>
                                <option>ABECO</option>
                                <option>Nguyên phi</option>
                            </select>
                        </div>
                        <div class="form-group col-md-12">
                            <label for="exampleSelect1" class="control-label">Vị trí lưu kho</label>
                            <select class="form-control" id="exampleSelect1">
                                <option selected disabled hidden>-- Chọn kho hàng --</option>
                                <option>XQCV+F65, P. Văn Quán, Hà Đông, Hà Nội</option>
                                <option>68 P. Nguyễn Văn Giáp, Mễ Trì, Nam Từ Liêm, Hà Nội</option>
                                <option>Đinh Tú, Cấn Hữu, Quốc Oai, Hà Nội</option>
                                <option>4F9R+78G, Đường khu TĐC, Tông, Sơn Tây, Hà Nội</option>
                            </select>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label required-field">Số hàng</label>
                                <input class="form-control" type="number" required>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label required-field">Số cột</label>
                                <input class="form-control" type="number" required>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label required-field">Mã kệ hàng</label>
                                <input class="form-control" type="text" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="tile-footer col-md-12">
                                <button type="submit" class="btn btn-primary create-order-button" type="button">Lưu giao
                                    dịch</button>
                                <a class="btn btn-primary cancel-order-button" href="order-management.html"
                                    type="button">Huỷ và quay về</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!--
  PRODUCT MODAL
-->
    <div class="modal fade" id="productList" role="dialog" tabindex="-1" aria-labelledby="exampleModalCenterTitle"
        data-backdrop="static" data-mdb-keyboard="false">
        <div class="modal-dialog info-modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="tile">
                                <h3 class="tile-title">Chọn sản phẩm theo nhà sản xuất đã chọn</h3>
                                <div class="tile-body">
                                    <div class="modal-search-row">
                                        <button class="btn btn-save" data-dismiss="modal" data-toggle="modal"
                                            data-target="#productList" onclick="addToImportTable()"><i class='fas fa-plus'></i>
                                            Thêm vào danh sách</button>
                                        <form action="">
                                            <div class="search-container">
                                                <input class="form-control" type="text" placeholder="Tìm kiếm"
                                                    name="search">
                                                <button type="submit"><i class="fa fa-search"></i></button>
                                            </div>
                                        </form>
                                    </div>
                                    <table id="products" class="table table-hover table-bordered">
                                        <thead>
                                        <tr>
                                            <th>Mã sản phẩm</th>
                                            <th class="order-item-number">Mã SKU</th>
                                            <th class="order-item-number" width="200">Tên sản phẩm</th>
                                            <th class="order-item-number">Hình ảnh</th>
                                            <th class="order-item-number" width="100">Đơn giá</th>
                                            <th class="order-item-number" width="10">Số lượng</th>
                                            <th class="order-item-number">Thành tiền</th>
                                            <th width="100" class="order-item-number text-center" style="text-align: center; vertical-align: middle;">Tuỳ chọn</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Diode Xung Đôi SBL3040 TO-247 600V 30A</td>
                                            <td><img src="resources/images/diode.jpg" alt="" width="100px;">
                                            </td>
                                            <td>40</td>
                                            <td><span class="badge bg-success">Còn hàng</span></td>
                                            <td>5600</td>
                                            <td>Đi-ốt</td>
                                            <td><input class="status-checkbox" type="checkbox"
                                                       data-toggle="modal" data-target="#confirmStatus"
                                                       name="check1" value="1">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Diode Chỉnh Lưu FR307 3A 1000V</td>
                                            <td><img src="resources/images/diode.jpg" alt="" width="100px;">
                                            </td>
                                            <td>70</td>
                                            <td><span class="badge bg-success">Còn hàng</span></td>
                                            <td>24200</td>
                                            <td>Đi-ốt</td>
                                            <td><input class="status-checkbox" type="checkbox"
                                                       data-toggle="modal" data-target="#confirmStatus"
                                                       name="check1" value="1">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>Diode Cầu 25A Dẹt 1000V KBJ2510</td>
                                            <td><img src="resources/images/diode.jpg" alt="" width="100px;">
                                            </td>
                                            <td>40</td>
                                            <td><span class="badge bg-success">Còn hàng</span></td>
                                            <td>33235</td>
                                            <td>Đi-ốt</td>
                                            <td><input class="status-checkbox" type="checkbox"
                                                       data-toggle="modal" data-target="#confirmStatus"
                                                       name="check1" value="1">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>4</td>
                                            <td>Tụ Hóa 6.3V</td>
                                            <td><img src="resources/images/diode.jpg" alt="" width="100px;">
                                            </td>
                                            <td>50</td>
                                            <td><span class="badge bg-success">Còn hàng</span></td>
                                            <td>9500</td>
                                            <td>Tụ điện</td>
                                            <td><input class="status-checkbox" type="checkbox"
                                                       data-toggle="modal" data-target="#confirmStatus"
                                                       name="check1" value="1">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>5</td>
                                            <td>Tụ Phân Tần Loa Trebel CBB 335J 250V</td>
                                            <td><img src="resources/images/diode.jpg" alt="" width="100px;">
                                            </td>
                                            <td>50</td>
                                            <td><span class="badge bg-success">Còn hàng</span></td>
                                            <td>3800</td>
                                            <td>Tụ điện</td>
                                            <td><input class="status-checkbox" type="checkbox"
                                                       data-toggle="modal" data-target="#confirmStatus"
                                                       name="check1" value="1">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>6</td>
                                            <td>Cuộn cảm HCI1005LF-10NJ-MS8</td>
                                            <td><img src="resources/images/diode.jpg" alt="" width="100px;">
                                            </td>
                                            <td>55</td>
                                            <td><span class="badge bg-success">Còn hàng</span></td>
                                            <td>4600</td>
                                            <td>Cuộn cảm</td>
                                            <td><input class="status-checkbox" type="checkbox"
                                                       data-toggle="modal" data-target="#confirmStatus"
                                                       name="check1" value="1">
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div class="pagination-row">
                                        <div class="pagination-container">
                                            <div class="dataTables_paginate paging_simple_numbers"
                                                id="sampleTable_paginate">
                                                <ul class="pagination">
                                                    <li class="paginate_button page-item previous disabled"
                                                        id="sampleTable_previous"><a href="#"
                                                            aria-controls="sampleTable" data-dt-idx="0" tabindex="0"
                                                            class="page-link">Lùi</a></li>
                                                    <li class="paginate_button page-item active"><a href="#"
                                                            aria-controls="sampleTable" data-dt-idx="1" tabindex="0"
                                                            class="page-link">1</a></li>
                                                    <li class="paginate_button page-item "><a href="#"
                                                            aria-controls="sampleTable" data-dt-idx="2" tabindex="0"
                                                            class="page-link">2</a></li>
                                                    <li class="paginate_button page-item next" id="sampleTable_next"><a
                                                            href="#" aria-controls="sampleTable" data-dt-idx="3"
                                                            tabindex="0" class="page-link">Tiếp</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="modal-confirm-button">
                            <div>
                                <button class="btn btn-cancel" data-dismiss="modal">Hủy bỏ</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                </div>
            </div>
        </div>
    </div>
    <!--
MODAL
-->
    <!--
  MODAL DELETE PRODUCT
-->
    <div class="modal fade" id="deleteProduct" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
        data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">

                <div class="modal-body">
                    <div class="row">
                        <div class="form-group  col-md-12">
                            <span class="thong-tin-thanh-toan">
                                <h5>Chú ý</h5>
                            </span>
                        </div>
                        <div class="form-group col-md-12" style="text-align: center;">
                            <label class="control-label">Bạn có chắc chắn xoá sản phẩm này khỏi danh sách</label>
                        </div>
                    </div>
                    <div style="display: flex; justify-content: center; padding: 10px;">
                        <a style="margin: 5px;" class="btn btn-save" data-dismiss="modal" href="#">Xác nhận</a>
                        <a style="margin: 5px;" class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
                    </div>
                </div>
                <div class="modal-footer">
                </div>
            </div>
        </div>
    </div>
    <!--
MODAL
-->
    <!-- Essential javascripts for application to work-->

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="src/jquery.table2excel.js"></script>

    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/popper.min.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/js/main.js"/>"></script>
    <script src="<c:url value="/js/api-province.js"/>"></script>
    <script src="<c:url value="/js/plugins/pace.min.js"/>"></script>
    <script src="<c:url value="/resources/data.json"/>"></script>
    <!-- Page specific javascripts-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
</body>

</html>