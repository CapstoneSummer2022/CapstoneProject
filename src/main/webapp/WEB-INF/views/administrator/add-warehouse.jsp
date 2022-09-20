<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Header</title>
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

  <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
  <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>

</head>

<body onload="time()" class="app sidebar-mini rtl">

<!-- Navbar-->
<jsp:include page="header.jsp"/>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<jsp:include page="home-menu.jsp"/>
<main class="app-content">
  <div class="app-title">
    <ul class="app-breadcrumb breadcrumb">
      <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/warehouses">Quản lý kho hàng</a></li>
      <li class="breadcrumb-item active">Thêm kho hàng</li>
    </ul>
    <div id="clock"></div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <div class="tile">
        <h3 class="tile-title">Thêm kho hàng</h3>
        <div class="tile-body">
          <%--@elvariable id="newWarehouse" type="com.example.electriccomponentsshop.dto.WarehouseDTO"--%>
          <form:form action="${pageContext.request.contextPath}/admin/warehouses/add" method="post" modelAttribute="newWarehouse">
            <div class="row">
              <div class="form-group col-md-3">
                <label for="exampleSelect1" class="control-label required-field">Tỉnh/Thành phố</label>
                <form:select class="form-control" id="province" path="provinceName" required="required" >
                  <c:forEach  var="province" items="${listProvince}" >
                    <option value="${province.name}">
                        ${province.name}
                    </option>
                  </c:forEach>
                </form:select>
                <form:errors path="provinceName" element="span" />
              </div>
              <div class="form-group col-md-3">
                <label for="exampleSelect1" class="control-label required-field">Quận/Huyện</label>
                <form:select class="form-control" id="district" path="districtName"  required="required" >
                  <c:forEach  var="district" items="${listDistrict}" >
                    <option value="${district.name}">
                        ${district.name}
                    </option>
                  </c:forEach>
                </form:select>
                <form:errors path="districtName" element="span" />
              </div>
              <div class="form-group col-md-3">
                <label for="exampleSelect1" class="control-label required-field">Phường/Xã/Thị trấn</label>
                <form:select class="form-control" id="ward" path="wardName" required="required" >
                  <c:forEach  var="ward" items="${listWard}" >
                    <option value="${ward.name}">
                        ${ward.name}
                    </option>
                  </c:forEach>
                </form:select>
                <form:errors path="wardName" element="span" />
              </div>
              <div class="form-group col-md-3">
              <label class="control-label required-field">Địa chỉ chi tiết</label>
              <form:input class="form-control" type="text" path="detailLocation" required="required"/>
               </div>
            </div>
            <div class="row">
              <div class="confirm-button">
                <div class="button">
                  <button type="submit" class="btn btn-save" >Lưu lại</button>
                  <button type="button" class="btn btn-cancel" onclick="location.href='warehouse-management.html'">Hủy
                    bỏ</button>
                </div>
              </div>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>

</main>

<script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/main.js"/>"></script>
<script src="<c:url value="/js/plugins/pace.min.js"/>"></script>
<script src="<c:url value="/resources/data.json"/>"></script>
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