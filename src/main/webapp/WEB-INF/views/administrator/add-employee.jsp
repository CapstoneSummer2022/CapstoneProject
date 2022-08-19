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

</head>

<body onload="time()" class="app sidebar-mini rtl">
  </style>
  <!-- Navbar-->
  <jsp:include page="header.jsp"/>
  <!-- Sidebar menu-->
  <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
  <jsp:include page="home-account-menu.jsp"/>
  <main class="app-content">
    <div class="app-title">
      <ul class="app-breadcrumb breadcrumb">
        <li class="breadcrumb-item"><a href="#"></a>Tài khoản hệ thống</li>
        <li class="breadcrumb-item active">Tạo tài khoản nhân viên</li>
      </ul>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="tile">
          <h3 class="tile-title">Tạo tài khoản nhân viên</h3>
          <div class="tile-body">
            ${error}
            <%--@elvariable id="accountDto" type="com.example.electriccomponentsshop.dto.AccountDTO"--%>
            <form:form class="row" method="POST" action="${pageContext.request.contextPath}/admin/accounts/add}" modelAttribute="accountDto">
            <div class="row">
              <div class="form-group col-md-3">
                <label class="control-label">Họ và tên</label>
                <form:input class="form-control" type="text" path="name"  />
              </div>
              <div class="form-group col-md-3">
                <label class="control-label required-field">Giới tính</label>
                <div class="options">
                  <div class="option-item">
                    <form:label path="gender" >
                      <form:radiobutton path="gender" value="Nam"/>
                      Nam
                    </form:label>
                  </div>
                  <div class="option-item">
                    <form:label path="gender" >
                      <form:radiobutton path="gender" value="Nữ"/>
                      Nữ
                    </form:label>
                  </div>
                </div>
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label required-field">Ngày sinh</label>
                  <form:input class="form-control" type="date" path="dob"/>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-3">
                  <label for="province" class="control-label">Tỉnh/Thành phố</label>
                  <form:select class="form-control" id="province" path="provinceName">
                    <c:forEach items ="${listProvince}" var="province">
                      <option value="${province.name}">${province.name}-</option>
                    </c:forEach>
                  </form:select>
                </div>
                <div class="form-group col-md-3">
                  <label for="district" class="control-label">Quận/Huyện</label>
                  <form:select class="form-control" id="district" path="districtName">
                    <c:forEach items ="${listDistrict}" var="district">
                      <option value="${district.name}">${district.name}-</option>
                    </c:forEach>
                  </form:select>
                </div>
                <div class="form-group col-md-3">
                  <label for="ward" class="control-label">Phường/Xã/Thị trấn</label>
                  <form:select class="form-control" id="ward" path="wardName">
                    <c:forEach items ="${listWard}" var="ward">
                      <option value="${ward.name}">${ward.name}-</option>
                    </c:forEach>
                  </form:select>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-3">
                  <label class="control-label">Địa chỉ email</label>
                  <form:input class="form-control" type="text" path="email"  />
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label">Số điện thoại</label>
                  <form:input class="form-control" type="number" path="phone" />
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label">Mật Khẩu</label>
                  <form:input class="form-control" type="password" path="password"  />
                </div>
              </div>
              <div class="row">
                <div class="confirm-button">
                  <div class="button">
                    <button type="submit" class="btn btn-save" type="button" onclick="continueornot()">Lưu lại</button>
                    <button class="btn btn-cancel" onclick="location.href='system-account-management.html'">Hủy
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
  <!--
  MODAL INVALID INPUT 
-->
  <div class="modal fade" id="invalidInput" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <label class="control-label">Email không hợp lệ</label>
            </div>
          </div>
          <div style="display: flex; justify-content: center; padding: 10px;">
            <a style="margin: 5px;" class="btn btn-save" data-dismiss="modal" href="#">Xác nhận</a>
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
  <script src="<c:url value="/js/jquery-3.2.1.min.js"/>></script>
  <script src="<c:url value="/js/popper.min.js"/>></script>
  <script src="<c:url value="/js/bootstrap.min.js"/>></script>
  <script src="<c:url value="/js/main.js"/>></script>
  <script src="<c:url value="/js/js/api-province.js"/>></script>
  <script src="<c:url value="/js/plugins/pace.min.js"/>></script>
  <script src="<c:url value="/resources/data.json"/>></script>

</body>

</html>