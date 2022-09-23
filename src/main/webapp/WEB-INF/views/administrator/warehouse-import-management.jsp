<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Quản lý nhập kho | Quản trị</title>
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
            <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/categories"><i class='app-menu__icon bx bx-category'></i><span
                    class="app-menu__label">Quản lý danh mục</span></a></li>

            <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/suppliers"><i
                    class='app-menu__icon bx bxs-user-account'></i><span class="app-menu__label">Quản lý nhà cung cấp
          </span></a></li>
            <li><a class="app-menu__item active" href="${pageContext.request.contextPath}/admin/warehouses"><i
                    class='app-menu__icon bx bx-building-house'></i><span class="app-menu__label">Quản lý kho
                        hàng
                    </span></a>
                <ul class="sub-app-menu">
                    <li><a class="sub-app-menu_item" href="${pageContext.request.contextPath}/admin/warehouses"><span class="app-menu__label">Kho
                                hàng</span></a></li>
                    <li><a class="sub-app-menu_item  sub-item-active" href="${pageContext.request.contextPath}/admin/warehouses/view/import"><span
                            class="app-menu__label">Quản lý
                                nhập kho</span></a></li>
                    <li><a class="sub-app-menu_item" href="${pageContext.request.contextPath}/admin/warehouses/view/export"><span
                            class="app-menu__label">Quản lý
                                xuất kho</span></a></li>
                </ul>
            </li>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
            <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/feedbacks"><i class='app-menu__icon bx bx-user-voice'></i><span
                    class="app-menu__label">Feedback</span></a>
            </li>
            <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/reports"><i class='app-menu__icon bx bx-pie-chart-alt-2'></i><span
                    class="app-menu__label">Báo cáo thống kê</span></a>
            </li>
            </sec:authorize>
    </ul>
</aside>

<main class="app-content">
    <div class="app-title">
      <ul class="app-breadcrumb breadcrumb side">
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/warehouses/add"><b>Quản lý nhập kho</b></a></li>
      </ul>
      <div id="clock"></div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="tile">
          <div class="tile-body">
            <div class="row element-button">
              <div class="col-sm-2">
                <a class="btn btn-add btn-sm" href="${pageContext.request.contextPath}/admin/warehouses/import/add" title="Thêm"><i class="fas fa-plus"></i>
                  Thêm giao dịch mới</a>
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
            <table class="table table-hover table-bordered" cellpadding="0" cellspacing="0" id="sampleTable">
              <thead>
                <tr>
                  <th width="150">Mã giao dịch nhập kho</th>
                  <th>Nhà cung cấp</th>
                  <th>Kho hàng</th>
                  <th>Tổng tiền</th>
                  <th>Ngày nhập</th>
                  <th width="100">Tuỳ chọn</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="importTransaction" items="${listImport}">
                <tr>
                  <td>${importTransaction.id}</td>
                  <td>
                    ${importTransaction.supplierName}
                  </td>
                  <td>${importTransaction.wardName}, ${importTransaction.districtName},${importTransaction.provinceName}</td>
                  <td>${importTransaction.totalPayment}</td>
                  <td>${importTransaction.importDate}</td>
                  <td>
                    <a href="${pageContext.request.contextPath}/admin/warehouses/import/update/${importTransaction.id}" class="btn btn-primary btn-sm edit"
                       title="Sửa"><i class="fas fa-edit"></i></a>
                    <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng"
                            onclick="location.href='import-transaction-detail.html'"><i class="fa fa-info"></i></button>
                  </td>
                </tr>
              </c:forEach>

                <tr>
                  <td>2</td>
                  <td>
                    Hàng 2 - Cột 3 - Kệ A
                  </td>
                  <td>XQCV+F65, P. Văn Quán, Hà Đông, Hà Nội</td>
                  <td>120.000 đ</td>
                  <td>20/07/2022</td>
                  <td>
                    <a href="setting-warehouse-import.html" class="btn btn-primary btn-sm edit" type="button"
                      title="Sửa"><i class="fas fa-edit"></i></a>
                    <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng"
                      onclick="location.href='import-transaction-detail.html'"><i class="fa fa-info"></i></button>
                  </td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>
                    Hàng 2 - Cột 3 - Kệ A
                  </td>
                  <td>XQCV+F65, P. Văn Quán, Hà Đông, Hà Nội</td>
                  <td>120.000 đ</td>
                  <td>20/07/2022</td>
                  <td>
                    <a href="setting-warehouse-import.html" class="btn btn-primary btn-sm edit" type="button"
                      title="Sửa"><i class="fas fa-edit"></i></a>
                    <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng"
                      onclick="location.href='import-transaction-detail.html'"><i class="fa fa-info"></i></button>
                  </td>
                </tr>
                <tr>
                  <td>4</td>
                  <td>
                    Hàng 2 - Cột 3 - Kệ A
                  </td>
                  <td>XQCV+F65, P. Văn Quán, Hà Đông, Hà Nội</td>
                  <td>120.000 đ</td>
                  <td>20/07/2022</td>
                  <td>
                    <a href="setting-warehouse-import.html" class="btn btn-primary btn-sm edit" type="button"
                      title="Sửa"><i class="fas fa-edit"></i></a>
                    <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng"
                      onclick="location.href='import-transaction-detail.html'"><i class="fa fa-info"></i></button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </main>

  <!-- Essential javascripts for application to work-->
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
  <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
  <script src="<c:url value="/js/popper.min.js"/>"></script>
  <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
  <script src="<c:url value="/js/main.js"/>"></script>
  <script src="<c:url value="/js/plugins/pace.min.js"/>"></script>
  <script src="<c:url value="/resources/data.json"/>"></script>
  <!-- Page specific javascripts-->
</body>

</html>