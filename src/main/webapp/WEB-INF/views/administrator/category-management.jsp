<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Quản lý danh mục | Quản trị</title>
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
      <!-- Sidebar menu end--><main class="app-content">
        <div class="app-title">
            <ul class="app-breadcrumb breadcrumb side">
                <li class="breadcrumb-item active"><a href="#"><b>Quản lý danh mục</b></a></li>
            </ul>
            <div id="clock"></div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <div class="tile-body">
                        <div class="row element-button">
                            <div class="col-sm-2">
                                <a class="btn btn-add btn-sm" href="${pageContext.request.contextPath}/admin/categories/add"><i
                                        class="fas fa-folder-plus"></i> Thêm danh mục mới</a>
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
                        <table class="table table-hover table-bordered" id="sampleTable">
                            <thead>
                                <tr>
                                    <th width="200">Mã danh mục sản phẩm</th>
                                    <th>Tên danh mục sản phẩm</th>
                                    <th>Danh mục sản phẩm lớn</th>
                                    <th width="100">Tuỳ chọn</th>
                                </tr>
                            </thead>
                            <tbody id="result">
                            <c:forEach  items="${categories}" var="category">
                                <tr>
                                    <td>${category.id}</td>
                                    <td>${category.name}</td>
                                    <c:if test="${category.parentId !=null}">
                                        <td style="color: rgb(1, 98, 155);">${category.parentName}</td>
                                    </c:if>
                                    <c:if test="${category.parentId ==null}">
                                        <td></td>
                                    </c:if>

                                    <td>
                                        <a href="${pageContext.request.contextPath}/admin/categories/edit/${category.id}" class="btn btn-primary btn-sm edit"
                                           type="button" title="Sửa"><i class="fas fa-edit"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <!-- Essential javascripts for application to work-->
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/popper.min.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/js/main.js"/>"></script>
    <script src="<c:url value="/js/plugins/pace.min.js"/>"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="src/jquery.table2excel.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <!-- Page specific javascripts-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>

</body>

</html>