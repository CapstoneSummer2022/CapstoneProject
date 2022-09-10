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
    <%--@elvariable id="orderDto" type="com.example.electriccomponentsshop.dto.OrderDTO"--%>
    <form:form modelAttribute="orderDto">
        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <h3 class="tile-title">Thông tin nhận hàng</h3>
                    <div class="tile-body">
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label required-field">Họ và tên</label>
                                <input id="orderId" type="hidden" value="${orderDto.id}">
                                <input id="name" class="form-control" type="text" value="${orderDto.receivedPerson}" required>
                                <form:errors path="receivedPerson" element="span"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-3">
                                <label for="exampleSelect1" class="control-label required-field">Tỉnh/Thành
                                    phố</label>
                                    <select class="form-control" id="province">
                                    <c:forEach  var="province" items="${listProvince}" >
                                        <option value="${province.name}" <c:if test="${orderDto.provinceName == province.name}">
                                            selected
                                        </c:if>>
                                                ${province.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="exampleSelect1" class="control-label required-field">Quận/Huyện</label>
                                <select class="form-control" id="district" >
                                    <c:forEach  var="district" items="${listDistrict}" >
                                        <option value="${district.name}" <c:if test="${orderDto.districtName == district.name}">
                                            selected
                                        </c:if>>
                                                ${district.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="exampleSelect1" class="control-label required-field">Phường/Xã/Thị
                                    trấn</label>
                                <select class="form-control" id="ward" >
                                    <c:forEach  var="ward" items="${listWard}" >
                                        <option value="${ward.name}" <c:if test="${orderDto.wardName == ward.name}">
                                            selected
                                        </c:if>>
                                                ${ward.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label required-field">Địa chỉ</label>
                                <input id ="detail-location" class="form-control" value="${orderDto.detailLocation}" type="text" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label required-field">Số điện thoại</label>
                                <input id="phone" class="form-control" type="number" value="${orderDto.receivedPhone}" required>
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

                                <button type="button" class="btn btn-add btn-sm"data-toggle="modal" data-target="#productList"><i class="fas fa-plus"></i>Thêm sản phẩm</button>
                        </div>
                    </div>
                    <div class="du--lieu-san-pham">
                        <table id="orderProductList" class="table table-hover table-bordered form-product">
                            <thead>
                            <tr>
                                <th class="order-item-number">Mã sản phẩm</th>
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
                            <tr class="order-item">
                                <td>1</td>
                                <td>Diode Xung Đôi SBL3040 TO-247 600V 30A</td>
                                <td><img src="/img-sanpham/reno.jpg" alt="" width="50px;"></td>
                                <td>5600</td>
                                <td>
                                    <div class="quantity">
                                        <button class="minus-btn" type="button" name="button">
                                            -
                                        </button>
                                        <input class="input-number" type="number" name="name" value="1">
                                        <button class="plus-btn" type="button" name="button">
                                            +
                                        </button>
                                    </div>
                                </td>
                                <td>5600</td>
                                <td>
                                    <button class="btn btn-primary btn-sm trash" type="button" title="Xóa"
                                            data-toggle="modal" data-target="#deleteProduct"><i
                                            class="fas fa-trash-alt"></i></button>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="tile">
                    <h3 class="tile-title">Thông tin thanh toán</h3>
                    <div class="row">
                        <div class="form-group  col-md-6">
                            <label class="control-label">Tổng cộng thanh toán: </label>
                            <p class="control-all-money-total"><span id="sum">0</span></p>
                        </div>
                        <div class="tile-footer col-md-12">
                            <button id="create" class="btn btn-primary create-order-button" type="button">Tạo đơn
                                hàng</button>
                            <a class="btn btn-primary cancel-order-button" href="order-management.html"
                               type="button">Huỷ và quay về</a>
                        </div>
                    </div>

                </div>
            </div>

        </div>
    </form:form>
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
                                <div class="modal-search-row">
                                    <button class="btn btn-save"><i class='fas fa-plus'></i>
                                        Thêm vào giỏ hàng</button>
                                    <form action="">
                                        <div class="search-container">
                                            <input class="form-control" type="text" placeholder="Tìm kiếm"
                                                   name="search">
                                            <button type="button" id="find-product"><i class="fa fa-search"></i></button>
                                        </div>
                                    </form>
                                </div>
                                <table class="table table-hover table-bordered" id="productTable">
                                    <thead>
                                    <tr>
                                        <th>Mã sản phẩm</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Ảnh sản phẩm</th>
                                        <th>Số lượng tồn kho</th>
                                        <th>Tình trạng</th>
                                        <th>Giá tiền</th>
                                        <th>Danh mục</th>
                                        <th>Chọn</th>
                                    </tr>
                                    </thead>
                                    <tbody id="list-product">

                                    <tr>
                                        <td>1-A-2-2</td>
                                        <td>Diode Xung Đôi SBL3040 TO-247 600V 30A</td>
                                        <td><img src="/img-sanpham/theresa.jpg" alt="" width="100px;"></td>
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
                                        <td><img src="/img-sanpham/reno.jpg" alt="" width="100px;"></td>
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
                                        <td><img src="/img-sanpham/matda.jpg" alt="" width="100px;"></td>
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
                                        <td><img src="/img-sanpham/ghethera.jpg" alt="" width="100px;"></td>
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
                                        <td><img src="/img-sanpham/zuno.jpg" alt="" width="100px;"></td>
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
                                        <td><img src="/img-sanpham/vita.jpg" alt="" width="100px;"></td>
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
<!-- The Modal -->
<div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="errorAlert">X</span>
        </div>
    </div>
</div>
<!-- Essential javascripts for application to work-->

<!-- The javascript plugin to display page loading on top-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<script src="js/api-province.js"></script>
<script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/main.js"/>"></script>
<script src="<c:url value="/js/plugins/pace.min.js"/>"></script>
<script src="<c:url value="/resources/data.json"/>"></script>
<script>
    var element = document.getElementById("create");
    element.addEventListener('click',()=>{
        var orderItems= new Array();
        var id = $("#orderId").val();
        $(".order-item").each(function (){
            var row = $(this);
            var orderItem = new Object();
            orderItem.productId = row.find("TD").eq(0).html();
            orderItem.quantity = row.find("INPUT").val();
            orderItems.push(orderItem);
        });
        var data1={
            receivedPerson: $('#name').val(),
            provinceName: $('#province').val(),
            districtName: $('#district').val(),
            wardName:$('#ward').val(),
            detailLocation:$('#detail-location').val(),
            receivedPhone:$('#phone').val(),
            orderItems: orderItems
        };
        alert(data1.receivedPerson);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/admin/orders/save/" + id,
            data:
                JSON.stringify(data1)
            ,
            dataType:"text",
            success: function (response){
                $("errorAlert").innerHTML = response
                alert(response);
            }
        });
    });
</script>
<script>
    $(document).ready(function (){
        function showWard(){

        }
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