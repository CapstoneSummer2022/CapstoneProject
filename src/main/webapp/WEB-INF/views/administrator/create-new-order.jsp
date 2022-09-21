<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Tạo đơn hàng mới | Quản trị</title>
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
    <div class="row">
        <div class="col-md-12">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item"><a href="order-management.html"><b>Quản lý đơn hàng</b></a>
                    </li>
                    <li class="breadcrumb-item active"><b>Tạo đơn hàng mới</b></li>
                </ul>
                <div id="clock"></div>
            </div>
        </div>
    </div>
    <form>
        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <h3 class="tile-title">Thông tin người mua</h3>
                    <div class="tile-body">
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label required-field">Số điện thoại</label>
                                <input class="form-control" type="number" id="orderPhone" required>
                            </div>
                        </div>
                    </div>
                    <h3 class="tile-title">Thông tin nhận hàng</h3>
                    <div class="tile-body">
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label required-field">Họ và tên</label>
                                <input class="form-control" type="text" id="receivedPerson" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-3">
                                <label for="exampleSelect1" class="control-label required-field">Tỉnh/Thành
                                    phố</label>
                                <select class="form-control" id="province" required>
                                    <c:forEach  var="province" items="${listProvince}" >
                                        <option value="${province.name}">
                                                ${province.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="exampleSelect1" class="control-label required-field">Quận/Huyện</label>
                                <select class="form-control" id="district" required>
                                    <c:forEach  var="district" items="${listDistrict}" >
                                        <option value="${district.name}">
                                                ${district.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="exampleSelect1" class="control-label required-field">Phường/Xã/Thị
                                    trấn</label>
                                <select class="form-control" id="ward" required>
                                    <c:forEach  var="ward" items="${listWard}" >
                                        <option value="${ward.name}">
                                                ${ward.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label required-field">Địa chỉ</label>
                                <input class="form-control" id="detailLocation" type="text" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label required-field">Số điện thoại</label>
                                <input class="form-control" type="number" id="receivedPhone" required>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="tile">
                    <h3 class="tile-title">Danh sách sản phẩm mua</h3>
                    <div class="row element-button">
                        <div class="col-sm-2">
                            <button class="btn btn-add btn-sm" title="Thêm" data-toggle="modal"
                                    data-target="#productList" type="button"><i class="fas fa-plus"></i>
                                Thêm sản phẩm</button>
                        </div>
                    </div>
                    <div class="du--lieu-san-pham">
                        <table id="orderProductList" class="table table-hover table-bordered form-product">
                            <thead>
                            <tr>
                                <th class="order-item-number">Mã SKUD</th>
                                <th class="order-item-number">Tên sản phẩm</th>
                                <th class="order-item-number">Hình ảnh</th>
                                <th class="order-item-number">Đơn giá</th>
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
                    <h3 class="tile-title">Thông tin thanh toán</h3>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label  class="control-label required-field">Loại đơn hàng</label>
                            <select class="form-control" id="orderType"required>
                               <c:forEach var="type" items="${listType}">
                                   <option value="${type.id}">${type.name}</option>
                               </c:forEach>
                            </select>
                            <label style="margin-top: 8px;" class="control-label required-field"> Phương thức thanh toán</label><br/>
                            <input type="radio" id="all" name="payment_method" value="Thanh toán toàn bộ" checked>
                            <label for="all">Thanh toán toàn bộ</label><br/>
                            <input type="radio" id="40" name="payment_method" value="Thanh toán 40% đơn hàng">
                            <label for="40">Thanh toán 40% đơn hàng</label><br/>
                        </div>
                        <div class="form-group col-md-12">
                            <label class="control-label">Tổng cộng thanh toán: </label>
                            <p class="control-all-money-total"><span id="sum">0</span></p>
                        </div>
                        <div class="tile-footer col-md-12">
                            <button type="button" class="btn btn-primary create-order-button" id="createOrder">Tạo đơn
                                hàng</button>
                            <a class="btn btn-primary cancel-order-button" href="order-management.html"
                               type="button">Huỷ và quay về</a>
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
                                <form>
                                    <div class="modal-search-row">
                                        <button class="btn btn-save" type="button" data-dismiss="modal"
                                                onclick="addToProductTable()"><i class='fas fa-plus'></i>
                                            Thêm vào đơn hàng</button>
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
                                            <th>Tên sản phẩm</th>
                                            <th>Ảnh sản phẩm</th>
                                            <th>Số lượng tồn kho</th>
                                            <th>Giá tiền</th>
                                            <th>Chọn</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="product" items="${listProduct}">
                                            <tr>
                                                <td>${product.id}</td>
                                                <td>${product.name}</td>
                                                <td></td>
                                                <td>${product.available}</td>
                                                <td>${product.price}</td>
                                                <td><input class="status-checkbox" type="checkbox"
                                                           data-toggle="modal" data-target="#confirmStatus"
                                                           name="check1" value="1">
                                                </td>
                                            </tr>
                                        </c:forEach>


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
                        <p class="modal-notify-successful">Tạo đơn hàng thành công</p>
                    </div>
                </div>
                <div style="display: flex; justify-content: center; padding: 10px;">
                    <a href="${pageContext.request.contextPath}/admin/orders" style="margin: 5px;" class="btn btn-save">Đóng</a>
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

<!-- The javascript plugin to display page loading on top-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

<script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/main.js"/>"></script>
<script src="<c:url value="/js/plugins/pace.min.js"/>"></script>
<script src="<c:url value="/resources/data.json"/>"></script>
<script>


    var element = document.getElementById("createOrder");
    element.addEventListener('click',()=>{
        var orderItems= new Array();
        var type = $('input[name="payment_method"]:checked').val();
        $(".order-item").each(function (){
            var row = $(this);
            var orderItem = new Object();
            orderItem.productId = row.find("TD").eq(0).html();
            orderItem.quantity = row.find("INPUT").val();
            orderItems.push(orderItem);

        });

        var data1={
            accountCustomerPhone:$('#orderPhone').val(),
            receivedPerson: $('#receivedPerson').val(),
            provinceName: $('#province').val(),
            districtName: $('#district').val(),
            wardName:$('#ward').val(),
            detailLocation:$('#detailLocation').val(),
            receivedPhone:$('#receivedPhone').val(),
            kindId:$('#orderType').val(),
            paymentMethod:type,
            orderItems: orderItems
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/admin/orders/add",
            data:
                JSON.stringify(data1)
            ,
            dataType:"text",
            success: function (response){
                if(response==="thành công"){
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
<script>
    $(document).ready(function (){
        $('#province').change(function(event){
            var pro = $('#province').val();

            $.ajax({
                type:"GET",
                contentType:"application/x-www-form-urlencoded",
                url: "/address/district?province="+pro,
                success: function (response){
                    var  $dis= $('#district');
                    var $ward = $('#ward')
                    $dis.find('option').remove();
                    $ward.find('option').remove();
                    response[0].forEach(d=>{
                        $('<option>').val(d.name).text(d.name).appendTo($dis);
                    });
                    response[1].forEach(w=>{
                        $('<option>').val(w.name).text(w.name).appendTo($ward);
                    })
                }
            });
        });
        $('#district').change(function (){
            var district = $('#district').val();
            $.ajax({
                type: "GET",
                url: "/address/ward?district="+district,
                success: function (response){
                    var $ward = $('#ward');
                    $ward.find('option').remove();
                    $.each(response, function(key,value){
                        $('<option>').val(value.name).text(value.name).appendTo($ward);
                    });
                }
            });
        });
    });
</script>
</body>

</html>