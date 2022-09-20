<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Tuỳ chỉnh xuất kho | Quản trị</title>
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
    <jsp:include page="warehouse-home-menu.jsp"/>
    <main class="app-content">
        <div class="row">
            <div class="col-md-12">
                <div class="app-title">
                    <ul class="app-breadcrumb breadcrumb side">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/warehouses"><b>Quản lý xuất
                            kho</b></a>
                        </li>
                        <li class="breadcrumb-item active"><b>Tuỳ chỉnh xuất kho</b></li>
                    </ul>
                </div>
            </div>
        </div>
        <form>
            <div class="row">

                <div class="col-md-8">
                    <div class="tile">
                        <h3 class="tile-title">Thông tin sản phẩm</h3>
                        <div class="row element-button">
                            <div class="col-sm-2">
                                <button class="btn btn-add btn-sm" title="Thêm" type="button" data-toggle="modal" data-target="#productList"><i class="fas fa-plus"></i>
                                    Chọn đơn hàng</button>
                            </div>
                        </div>
                        <div class="du--lieu-san-pham">
                            <table id="exportProductList" class="table table-hover table-bordered form-product">
                                <thead>
                                <tr>
                                    <th class="order-item-number">Tên sản phẩm</th>
                                    <th class="order-item-number" width="10">Tổng Số lượng</th>
                                    <th class="order-item-number">Mã SKUD</th>
                                    <th class="order-item-number">Số lượng theo từng SKU</th>
                                    <th width="100" class="order-item-number text-center" style="text-align: center; vertical-align: middle;">Tuỳ chọn
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="exportItem" items="${exportTransaction.exportItems}">
                                    <tr>
                                        <td>${exportItem.productName}</td>
                                        <td>
                                            2
                                        </td>
                                        <td>
                                            <select class="skud" required="">
                                                <option disabled="" hidden="" value=""> Mã SKUD</option>
                                                <option selected="" value="1">1-A-B-4-5</option>
                                                <option value="2">1-A-B-4-6</option>
                                                <option value="3">1-A-B-4-7</option>
                                                <option value="4">1-A-B-4-8</option>
                                            </select>
                                        </td>
                                        <td>
                                            <input class="numberForSKU input-number" type="number" value="1" onchange="validateNumberSKUByField(this)">
                                        </td>
                                        <td>
                                            <button class="btn btn-primary btn-sm trash" type="button" title="Xóa" data-toggle="modal" data-target="#deleteProduct"><i class="fas fa-trash-alt"></i></button>
                                            <button class="btn btn-add btn-sm" title="Thêm" type="button" onclick="duplicateRow(this)"><i class="fas fa-plus"></i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td>1</td>
                                    <td>Diode Xung Đôi SBL3040 TO-247 600V 30A</td>
                                    <td><img src="resources/images/diode.jpg" alt="" width="50px;"></td>
                                    <td>
                                        2
                                    </td>
                                    <td>
                                        <select class="skud" required="">
                                            <option disabled="" hidden="" value=""> Mã SKUD</option>
                                            <option selected="" value="1">1-A-B-4-5</option>
                                            <option value="2">1-A-B-4-6</option>
                                            <option value="3">1-A-B-4-7</option>
                                            <option value="4">1-A-B-4-8</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input class="numberForSKU input-number" type="number" value="1" onchange="validateNumberSKUByField(this)">
                                    </td>
                                    <td>
                                        <button class="btn btn-primary btn-sm trash" type="button" title="Xóa" data-toggle="modal" data-target="#deleteProduct"><i class="fas fa-trash-alt"></i></button>
                                        <button class="btn btn-add btn-sm" title="Thêm" type="button" onclick="duplicateRow(this)"><i class="fas fa-plus"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Diode SS34 SMD</td>
                                    <td><img src="resources/images/diode.jpg" alt="" width="50px;"></td>
                                    <td>
                                        3
                                    </td>
                                    <td>
                                        <select class="skud" required="">
                                            <option disabled="" hidden="" value=""> Mã SKUD</option>
                                            <option selected="" value="1">1-A-B-4-5</option>
                                            <option value="2">1-A-B-4-5</option>
                                            <option value="3">1-A-B-4-6</option>
                                            <option value="4">1-A-B-4-7</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input class="numberForSKU input-number" type="number" value="1" onchange="validateNumberSKUByField(this)">
                                    </td>
                                    <td>
                                        <button class="btn btn-primary btn-sm trash" type="button" data-toggle="modal" data-target="#deleteProduct" title="Xóa"><i class="fas fa-trash-alt"></i></button>
                                        <button class="btn btn-add btn-sm" title="Thêm" type="button" onclick="duplicateRow(this)"><i class="fas fa-plus"></i></button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="tile">
                        <h3 class="tile-title">Thông tin xuất hàng</h3>

                        <div class="tile-body">
                            <div class="form-group col-md-12">
                                <label for="importDate" class="control-label required-field">Mã giao dịch xuất</label>
                                <input class="form-control" type="text" id="orderId" value="${exportTransaction.id}" readonly>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="importDate" class="control-label required-field">Mã đơn hàng</label>
                                <input class="form-control" type="text" id="orderId" value="${exportTransaction.orderId}" readonly>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="importDate" class="control-label required-field">Ngày Xuất</label>
                                <input class="form-control" type="date" name="ExportDate" id="exportDate" value="2013-01-08" required="">
                            </div>
                            <div class="form-group col-md-12">
                                <label for="importDate" class="control-label required-field">Họ và tên người xuất
                                    kho</label>
                                <input class="form-control" type="text" name="exportName" id="exportName" value="${exportTransaction.exportPerson}" readonly>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="importDate" class="control-label required-field">Họ và tên người
                                    nhận</label>
                                <input class="form-control" type="text" name="receive" id="receive" value="Bùi Phương Thảo" required="">
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label">Ghi chú</label>
                                <textarea class="form-control" name="mota" id="mota">Xuất kho để bán</textarea>
                            </div>
                            <div class="row">
                                <div class="tile-footer col-md-12">
                                    <button type="submit" class="btn btn-primary create-order-button" onclick="checkDate()">Lưu
                                        giao
                                        dịch</button>
                                    <a class="btn btn-primary cancel-order-button" href="warehouse-export-management.html" type="button">Huỷ và quay về</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </form>
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
                                <h3 class="tile-title">Chọn sản phẩm xuất kho</h3>
                                <div class="tile-body">
                                    <div class="modal-search-row">
                                        <button class="btn btn-save" data-dismiss="modal" data-toggle="modal"
                                            data-target="#productList"><i class='fas fa-plus'></i>
                                            Thêm vào danh sách</button>
                                        <form action="">
                                            <div class="search-container">
                                                <input class="form-control" type="text" placeholder="Tìm kiếm"
                                                    name="search">
                                                <button type="submit"><i class="fa fa-search"></i></button>
                                            </div>
                                        </form>
                                    </div>
                                    <table class="table table-hover table-bordered" id="sampleTable">
                                        <thead>
                                            <tr>
                                                <th>Mã sản phẩm</th>
                                                <th>Tên sản phẩm</th>
                                                <th>Số lượng tồn kho</th>
                                                <th>Tình trạng</th>
                                                <th>Giá tiền</th>
                                                <th>Danh mục</th>
                                                <th>Chọn</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1-A-2-2</td>
                                                <td>Diode Xung Đôi SBL3040 TO-247 600V 30A</td>
                                                <td>40</td>
                                                <td><span class="badge bg-success">Còn hàng</span></td>
                                                <td>5.600 đ</td>
                                                <td>Đi-ốt</td>
                                                <td><input class="status-checkbox" type="checkbox" data-toggle="modal"
                                                        data-target="#confirmStatus" name="check1" value="1">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>1-A-10-1</td>
                                                <td>Diode Chỉnh Lưu FR307 3A 1000V</td>
                                                <td>70</td>
                                                <td><span class="badge bg-success">Còn hàng</span></td>
                                                <td>24.200 đ</td>
                                                <td>Đi-ốt</td>
                                                <td><input class="status-checkbox" type="checkbox" data-toggle="modal"
                                                        data-target="#confirmStatus" name="check1" value="1">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>1-A-6-2</td>
                                                <td>Diode Cầu 25A Dẹt 1000V KBJ2510</td>
                                                <td>40</td>
                                                <td><span class="badge bg-success">Còn hàng</span></td>
                                                <td>33.235 đ</td>
                                                <td>Đi-ốt</td>
                                                <td><input class="status-checkbox" type="checkbox" data-toggle="modal"
                                                        data-target="#confirmStatus" name="check1" value="1">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>1-A-10-2</td>
                                                <td>Tụ Hóa 6.3V</td>
                                                <td>50</td>
                                                <td><span class="badge bg-success">Còn hàng</span></td>
                                                <td>9.500 đ</td>
                                                <td>Tụ điện</td>
                                                <td><input class="status-checkbox" type="checkbox" data-toggle="modal"
                                                        data-target="#confirmStatus" name="check1" value="1">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>1-A-6-5</td>
                                                <td>Tụ Phân Tần Loa Trebel CBB 335J 250V</td>
                                                <td>50</td>
                                                <td><span class="badge bg-success">Còn hàng</span></td>
                                                <td>3.800 đ</td>
                                                <td>Tụ điện</td>
                                                <td><input class="status-checkbox" type="checkbox" data-toggle="modal"
                                                        data-target="#confirmStatus" name="check1" value="1">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>1-A-10-6</td>
                                                <td>Cuộn cảm HCI1005LF-10NJ-MS8</td>
                                                <td>55</td>
                                                <td><span class="badge bg-success">Còn hàng</span></td>
                                                <td>4.600 đ</td>
                                                <td>Cuộn cảm</td>
                                                <td><input class="status-checkbox" type="checkbox" data-toggle="modal"
                                                        data-target="#confirmStatus" name="check1" value="1">
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