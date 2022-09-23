<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Thêm khách mới</title>
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

              <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/accounts/system-account"><i class='app-menu__icon bx bx-id-card'></i>
                  <span class="app-menu__label">Quản lý tài khoản</span>
              </a>

              </li>
              <li><a class="app-menu__item active" href="${pageContext.request.contextPath}/admin/categories"><i class='app-menu__icon bx bx-category'></i><span
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
  <!-- Sidebar menu end-->

  <main class="app-content">
        <div class="app-title">
            <ul class="app-breadcrumb breadcrumb side">
                <li class="breadcrumb-item"><a href="#"><b>Quản lý danh mục</b></a></li>
                <li class="breadcrumb-item active"><b>Thêm danh mục mới</b></li>
            </ul>
            <div id="clock"></div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <h3 class="tile-title">Thêm danh mục mới</h3>
                    <div class="tile-body">
                        <form>
                            <div class="row">
                                <div class="form-group col-md-3">
                                    <label class="control-label required-field">Mã danh mục</label>
                                    <input class="form-control" type="text" required>
                                </div>
                                <div class="form-group col-md-3">
                                    <label class="control-label required-field">Tên danh mục</label>
                                    <input class="form-control" type="text" required>
                                </div>
                                <div class="form-group col-md-3">
                                    <label class="control-label required-field">Danh mục lớn trực thuộc</label>
                                    <div class="options">
                                        <div class="option-item">
                                            <label for="yes"> <input name="category_option" type="radio" value="YES"
                                                    required />
                                                Có </label>
                                        </div>
                                        <div class="option-item">
                                            <label for="no"> <input name="category_option" type="radio" value="NO" />
                                                Không </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group col-md-3">
                                    <label class="control-label">Chọn danh mục lớn</label>
                                    <select class="form-control required-field" id="exampleSelect2" required>
                                        <option selected disabled hidden>-- Chọn danh mục lớn --</option>
                                        <option>Linh kiện thụ động</option>
                                        <option>Linh kiện bán dẫn</option>
                                        <option>Connector</option>
                                        <option>LED/LCD</option>
                                        <option>IC chức năng</option>
                                        <option>Cảm biến</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="confirm-button">
                                    <div class="button">
                                        <button type="submit" id="saveProduct" class="btn btn-save" type="button">Lưu lại</button>
                                        <button class="btn btn-cancel"
                                            onclick="location.href='category-management.html'">Hủy bỏ</button>
                                    </div>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </main>
    <!-- Essential javascripts for application to work-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="src/jquery.table2excel.js"></script>
    <!-- Page specific javascripts-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>></script>
  <script src="<c:url value="/js/popper.min.js"/>></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>></script>
  <script src="<c:url value="/js/main.js"/>></script>
  <script src="<c:url value="/js/plugins/pace.min.js"/>></script>
    <script src="<c:url value="/resources/data.json"/>></script>
</body>

</html>