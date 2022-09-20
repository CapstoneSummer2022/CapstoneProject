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
<jsp:include page="header.jsp"/>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<jsp:include page="warehouse-home-menu.jsp"/>
<main class="app-content">
    <div class="row">
        <div class="col-md-12">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item"><a href="warehouse-import-management.html"><b>Quản lý nhập
                        kho</b></a>
                    </li>
                    <li class="breadcrumb-item active"><b>Thêm nhập kho</b></li>
                </ul>
                <div id="clock"></div>
            </div>
        </div>
    </div>
    <form>
        <div class="row">
            <div class="row">
                <div class="col-md-8">
                    <div class="tile">
                        <h3 class="tile-title">Thông tin sản phẩm</h3>
                        <div class="row element-button">
                            <div class="col-sm-12">
                                <button class="btn btn-add btn-sm" title="Thêm" data-toggle="modal"
                                        data-target="#productList" type="button" onclick="importInfoSelect(this)"><i
                                        class="fas fa-plus"></i>
                                    Thêm sản phẩm</button>
                            </div>
                        </div>
                        <div class="du--lieu-san-pham">
                            <table id="importProductList" class="table table-hover table-bordered form-product">
                                <thead>
                                <tr>
                                    <th>Mã sản phẩm</th>
                                    <th class="order-item-number">Mã SKUD</th>
                                    <th class="order-item-number" width="200">Tên sản phẩm</th>
                                    <th class="order-item-number">Hình ảnh</th>
                                    <th class="order-item-number" width="100">Đơn giá</th>
                                    <th class="order-item-number" width="10">Số lượng</th>
                                    <th class="order-item-number">Thành tiền</th>
                                    <th width="100" class="order-item-number text-center"
                                        style="text-align: center; vertical-align: middle;">Tuỳ chọn</th>
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
                                <label for="importDate" class="control-label required-field">Ngày nhập</label>
                                <input class="form-control" type="date" name="birthmonth" id="importDate" onchange="checkDate()" required
                                >
                            </div>
                            <div class="form-group col-md-12">
                                <label for="supplierSelect" class="control-label required-field">Nhà cung
                                    cấp</label>
                                <select class="form-control" id="supplierSelect" onchange="resetImportTable()"
                                        required>
                                    <c:forEach items="${listSupplier}" var="supplier">
                                        <option value="${supplier.id}">${supplier.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="warehouse" class="control-label required-field">Vị trí lưu
                                    kho</label>
                                <select class="form-control" id="warehouse" required>
                                    <c:forEach var="warehouse" items="${listWarehouses}">
                                        <option value="${warehouse.id}">${warehouse.wardName}, ${warehouse.districtName},${warehouse.provinceName}</option>
                                    </c:forEach>
                                </select>
                            </div>


                            <div class="form-group col-md-12">
                                <label class="control-label">Tổng tiền: </label>
                                <p class="control-all-money-total"><span id="sum">0</span></p>
                            </div>
                            <div class="row">
                                <div class="tile-footer col-md-12">
                                    <button id="createImport" class="btn btn-primary create-order-button"
                                            type="button">Lưu giao
                                        dịch</button>
                                    <a class="btn btn-primary cancel-order-button" href="order-management.html"
                                       type="button">Huỷ và quay về</a>
                                </div>
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
     data-backdrop="static">
    <div class="modal-dialog info-modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="tile">
                            <div class="tile-body">
                                <div class="form-group col-md-5">
                                    <label for="warehouse" class="control-label required-field">Chọn ô chứa</label>
                                    <select class="form-control" id="container" required >
                                        <c:forEach var="container" items="${listContainer}">
                                            <option value = "${container.id}">${container.shelf}-${container.columnIn}-${container.rowIn}</option>

                                        </c:forEach>
                                    </select>
                                </div>
                                <form>
                                    <div class="modal-search-row">

                                        <button class="btn btn-save" type="button" data-dismiss="modal"
                                                onclick="addToImportTable()"><i class='fas fa-plus'></i>
                                            Thêm vào đơn hàng</button>
                                        <form action="">
                                            <div class="search-container">
                                                <input class="form-control" type="text" placeholder="Tìm kiếm"
                                                       name="search" id="searchText">
                                                <button type="button" onclick="searchProductInImport()"><i class="fa fa-search"></i></button>
                                            </div>
                                        </form>
                                    </div>
                                    <table id="products" class="table table-hover table-bordered">
                                        <thead>
                                        <tr>
                                            <th>Mã sản phẩm</th>
                                            <th>Tên sản phẩm</th>
                                            <th>Ảnh sản phẩm</th>
                                            <th>Số lượng tồn kho</th>
                                            <th>Giá tiền</th>
                                            <th>Chọn</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </form>
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
                    <a style="margin: 5px;" class="btn btn-save btn-confirm" data-dismiss="modal" href="#">Xác
                        nhận</a>
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

<!--
MODAL SUCCESSFUL
-->
<div class="modal fade" id="successful" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="form-group  col-md-12">
                            <span class="thong-tin-thanh-toan">
                                <h5>Thông báo</h5>
                            </span>
                    </div>
                    <div class="form-group col-md-12" style="text-align: center;">
                        <p class="modal-notify-successful">Tạo đơn nhập thành công</p>
                    </div>
                </div>
                <div style="display: flex; justify-content: center; padding: 10px;">
                    <a href="${pageContext.request.contextPath}/admin/warehouses/view/import" style="margin: 5px;" class="btn btn-save">Đóng</a>
                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="unsuccessful" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="form-group  col-md-12">
              <span class="thong-tin-thanh-toan">
                <h5>Thông báo</h5>
              </span>
                    </div>
                    <div class="form-group col-md-12" style="text-align: center;">
                        <p class="modal-notify-unsuccessful" id="reason"></p>
                    </div>
                </div>
                <div style="display: flex; justify-content: center; padding: 10px;">
                    <button style="margin: 5px;" class="btn btn-save" data-dismiss="modal">Đóng</button>
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
MODAL PRODUCT EMPTY
-->
<div class="modal fade" id="productEmpty" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="form-group  col-md-12">
                            <span class="thong-tin-thanh-toan">
                                <h5>Thông báo</h5>
                            </span>
                    </div>
                    <div class="form-group col-md-12" style="text-align: center;">
                        <p class="modal-notify-unsuccessful">Chưa có sản phẩm trong danh sách</p>
                    </div>
                </div>
                <div style="display: flex; justify-content: center; padding: 10px;">
                    <button style="margin: 5px;" class="btn btn-save" data-dismiss="modal">Quay lại</button>
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
MODAL CHOOSE INFO BEFORE SELECTING PRODUCT
-->
<div class="modal fade" id="selectImportInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="form-group  col-md-12">
                            <span class="thong-tin-thanh-toan">
                                <h5>Thông báo</h5>
                            </span>
                    </div>
                    <div class="form-group col-md-12" style="text-align: center;">
                        <p class="modal-notify-successful">Chọn đầy đủ thông tin trước khi chọn sản phẩm</p>
                    </div>
                </div>
                <div style="display: flex; justify-content: center; padding: 10px;">
                    <button style="margin: 5px;" class="btn btn-save" data-dismiss="modal">Quay lại</button>
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
<script>
    var element = document.getElementById("createImport");
    element.addEventListener('click',()=>{
        var importItems= new Array();
        $(".import-items").each(function (){
            var row = $(this);
            var importItem = new Object();

            importItem.productId = row.find("TD").eq(0).html();
            importItem.skuId = row.find("TD").eq(1).html();
            importItem.importPrice = row.find("TD").eq(4).find("INPUT").val();
            importItem.quantity = row.find("TD").eq(5).find("INPUT").val();
            importItem.containerId =  row.find("TD").eq(8).html();;
            importItems.push(importItem);

        });
        var data1={
            supplierId: $('#supplierSelect').val(),
            importDate: $('#importDate').val(),
            warehouseId:$('#warehouse').val(),
            importItems: importItems
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/admin/warehouses/import/add",
            data:
                JSON.stringify(data1)
            ,
            dataType:"text",
            success: function (response){
                if(response === "thành công"){
                    $('#successful').modal('show');
                }
                else {
                    $('#reason').innerHTML = response;
                    $('#unsuccessful').modal('show');
                }

            }
        });
    });
</script>
</body>

</html>