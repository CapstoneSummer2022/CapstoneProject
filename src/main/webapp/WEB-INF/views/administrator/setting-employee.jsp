<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Tuỳ chỉnh thông tin nhân viên | Quản trị Admin</title>
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
  <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
</head>

<body onload="time()" class="app sidebar-mini rtl">
  <!-- Navbar-->
  <jsp:include page="header.jsp"/>
    <!-- Sidebar menu start-->
      <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
      <aside class="app-sidebar">
          <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="${pageContext.request.contextPath}/img/avatar.jpg" width="50px"
                                              alt="User Image">
              <div>
                  <p class="app-sidebar__user-name"><b>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.name}</b></p>
                  <p class="app-sidebar__user-designation">
                      <sec:authorize access="hasRole('ROLE_MANAGER')">
                          Quản lí
                      </sec:authorize>
                      <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                          Nhân viên
                      </sec:authorize>
                  </p>
              </div>
          </div>
          <hr>
          <ul class="app-menu">
              <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/products"><i
                      class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản
                  phẩm</span></a>
              </li>
              <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/orders/waiting"><i class='app-menu__icon bx bx-task'></i><span
                      class="app-menu__label">Quản lý đơn hàng</span></a></li>

                  <li><a class="app-menu__item active" href="${pageContext.request.contextPath}/admin/accounts/system-account"><i class='app-menu__icon bx bx-id-card'></i>
                      <span class="app-menu__label">Quản lý tài khoản</span>
                  </a>
                          <ul class="sub-app-menu">
                            <li><a class="sub-app-menu_item sub-item-active" href="${pageContext.request.contextPath}/admin/accounts/system-account"><span class="app-menu__label">Tài khoản
                                  hệ thống</span></a></li>
                            <li><a class="sub-app-menu_item" href="${pageContext.request.contextPath}/admin/accounts/customer-account"><span
                                  class="app-menu__label">Tài khoản khách
                                  hàng</span></a></li>
                          </ul>
                        </li>

                  </li>
                  <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/categories"><i class='app-menu__icon bx bx-category'></i><span
                          class="app-menu__label">Quản lý danh mục</span></a></li>

                  <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/suppliers"><i
                          class='app-menu__icon bx bxs-user-account'></i><span class="app-menu__label">Quản lý nhà cung cấp
                </span></a></li>
                  <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/warehouses"><i
                          class='app-menu__icon bx bx-building-house'></i><span class="app-menu__label">Quản lý kho
                  hàng
                </span></a></li>
              <sec:authorize  access="hasRole('ROLE_MANAGER')">
                  <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/feedbacks"><i class='app-menu__icon bx bx-user-voice'></i><span
                          class="app-menu__label">Feedback</span></a>
                  </li>
                  <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/reports"><i class='app-menu__icon bx bx-pie-chart-alt-2'></i><span
                          class="app-menu__label">Báo cáo thống kê</span></a>
                  </li>
              </sec:authorize>
          </ul>
      </aside>
      <!-- Sidebar menu end--><main class="app-content">
    <div class="app-title">
      <ul class="app-breadcrumb breadcrumb">
        <li class="breadcrumb-item"><a href="#">Quản lý nhân viên</a></li>
        <li class="breadcrumb-item active">Tuỳ chỉnh nhân viên</li>
      </ul>
    </div>
    <div class="row">
      <div class="col-md-12">

        <div class="tile">

          <h3 class="tile-title">Tuỳ chỉnh nhân viên</h3>
          <div class="tile-body">
            ${error}
            <form:form class="row" method="POST" action="${pageContext.request.contextPath}/admin/accounts/edit/${accountDto.id}" modelAttribute="accountDto">
              <div class="row">
              <div class="form-group col-md-3">
                <label class="control-label">Họ và tên</label>
                <form:input class="form-control" type="text" path="name" value="${accountDto.name}" />
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
                <label class="control-label">Ngày sinh</label>
                <form:input class="form-control" type="date" path="dob" value="${accountDto.dob}" />
              </div>
              </div>
              <div class="row">
              <div class="form-group col-md-3">
                <label class="control-label">Tỉnh/Thành phố</label>
              <form:select class="form-control" id="exampleSelect1" path="provinceName">
                <c:forEach  var="province" items="${listProvince}" >
                  <option value="${province.name}" <c:if test="${accountDto.provinceName == province.name}">
                    selected
                  </c:if>>
                    ${province.name}
                  </option>
                </c:forEach>
              </form:select>
              </div>
              <div class="form-group col-md-3">
                <label class="control-label">Quận/huyện</label>
                <form:select class="form-control" id="exampleSelect1" path="districtName">
                  <c:forEach  var="district" items="${listDistrict}" >
                    <option value="${district.name}" <c:if test="${accountDto.districtName == district.name}">
                      selected
                    </c:if>>
                        ${district.name}
                    </option>
                  </c:forEach>
                </form:select>
              </div>
              <div class="form-group col-md-3">
                <label class="control-label">Xã/Phường/Thị Trấn</label>
                <form:select class="form-control" id="exampleSelect1" path="wardName">
                  <c:forEach  var="ward" items="${listWard}" >
                    <option value="${ward.name}" <c:if test="${accountDto.wardName == ward.name}">
                      selected
                    </c:if>>
                        ${ward.name}
                    </option>
                  </c:forEach>
                </form:select>
              </div>
              </div>
              <div class="row">
              <div class="form-group col-md-3">
                <label class="control-label">Số điện thoại</label>
                <form:input class="form-control" type="number" path="phone" value="${accountDto.phone}" />
              </div>
              <div class="form-group col-md-3">
                <label class="control-label">Địa chỉ email</label>
                <form:input class="form-control" type="text" path="email" value="${accountDto.email}" />
              </div>
              <div class="form-group col-md-3">
                <label class="control-label">Mật Khẩu</label>
                <form:input class="form-control" type="password" path="password" value="${accountDto.password}" />
              </div>
              </div>
              <!--<div class="form-group  col-md-3">
                <label for="exampleSelect1" class="control-label">Chức vụ</label>
                <form:select class="form-control" id="exampleSelect1" path="role">
                  <c:forEach  var="role" items="${roles}" >


                    <option value="${role.roleName}" <c:if test="${accountDto.role == role.roleName}">
                      selected
                    </c:if>>
                      <c:if test="${role.roleName =='ROLE_EMPLOYEE'}">Nhân viên</c:if>
                      <c:if test="${role.roleName =='ROLE_MANAGER'}">Quản lí</c:if>
                      <c:if test="${role.roleName =='ROLE_CUSTOMER'}">Khách hàng</c:if>
                    </option>
                  </c:forEach>

                </form:select>
              </div>!-->
              <div class="confirm-button">
                <div class="button">
                  <button type="submit" class="btn btn-save" type="button">Lưu lại</button>
                  <button class="btn btn-cancel" href="/doc/table-data-table.html">Hủy bỏ</button>
                </div>
              </div>
            </form:form>

          </div>
        </div>
      </div>
    </div>
  </main>
  <!-- Essential javascripts for application to work-->
  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/main.js"></script>
  <!-- The javascript plugin to display page loading on top-->
  <script src="js/plugins/pace.min.js"></script>
  <script src="js/api-province.js"></script>
  <script src="resources/data.json"></script>

</body>

</html>