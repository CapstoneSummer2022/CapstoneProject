<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Tuỳ chỉnh sản phẩm | Quản trị Admin</title>
  <meta charset="utf-8">
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <!-- Main CSS-->
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>">
  <!-- Font-icon css-->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
  <!-- or -->
  <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
  <link rel="stylesheet" type="text/css"
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body onload="greaterThanZero()" class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header">
  <!-- Navbar Right Menu-->
  <ul class="app-nav">


    <!-- User Menu-->
    <div class="app-nav__button">
      <div class="app-nav__item"><i class='bx bx-cog'></i></div>
      <div class="app-nav__item-content">
        <a href="personal-info.html">Thông tin cá nhân</a>
        <a href="#">Đăng xuất</a>
      </div>
    </div>
  </ul>
</header>

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
          <li><a class="app-menu__item active" href="${pageContext.request.contextPath}/admin/products"><i
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
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/products">Quản lý sản phẩm</a></li>
        <li class="breadcrumb-item active">Thêm sản phẩm</li>
      </ul>
      <div id="clock"></div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="tile">
          <h3 class="tile-title">Thêm sản phẩm mới</h3>
          <div class="tile-body">
            <div class="row element-button">
              <div class="col-sm-2">
                <a class="btn btn-add btn-sm" href="${pageContext.request.contextPath}/admin/products/specification/add" title="Thêm"><i
                        class="fas fa-plus"></i>
                  Thêm thông số kỹ thuật</a>
              </div>
            </div>
            <form id="updateProduct">
              <input type="hidden" id="productId" value="${productDto.id}">
              <div class="row">
                <div class="form-group col-md-3">
                  <label class="control-label required-field">Tên sản phẩm</label>
                  <input class="form-control" id="name" type="text" value="${productDto.name}" required>
                  <span class="error" id="errorProductName"></span>
                </div>

                <div class="form-group col-md-3">
                  <label class="control-label required-field">Giá bán</label>
                  <input class="form-control" id="price" type="number" value="${productDto.price}" onchange="isPricePositive()" required>
                  <p class="error" id="negativePrice"></p>
                </div>
                <div class="form-group col-md-3">
                  <label for="supplierOption" class="control-label required-field">Nhà cung cấp</label>
                  <select class="form-control" id="supplierOption" required>
                    <c:forEach var = "supplierDto" items="${listSuppliers}">
                      <option value="${supplierDto.id}">${supplierDto.name}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-3">
                  <label class="control-label required-field">Đơn vị bán</label>
                  <input class="form-control" id="unit" type="number" value="${productDto.unit}" onchange="isUnitPositive()" required>
                  <span class="error" id="negativeUnit"></span>
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label required-field">Số lượng có sẵn</label>
                  <input class="form-control" id="available" type="number" value="${productDto.available}" onchange="isAvailablePositive()" required>
                </div>
                <div class="form-group col-md-3">
                  <label for="exampleSelect1" class="control-label required-field">Danh mục</label>
                  <select class="form-control" id="categoryOption" required>
                    <c:forEach var = "categoryDto" items="${listCategories}">
                      <option value="${categoryDto.id}">${categoryDto.name}</option>
                    </c:forEach>
                  </select>
                </div>
                <div class="form-group col-md-3">
                  <button class="side-button btn btn-add" type="button" title="Thêm vào danh sách"
                          onclick="addToCategoryTable()">Thêm
                    vào
                    danh sách</button>
                  <button id="deleteButton" type="button" class="side-button btn btn-cancel" title="Xoá"
                          onclick="deleteFromCategoryTable()">Xoá</button>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-5">
                  <label  class="control-label">Bảng danh mục</label>
                  <table id="categoryTable" class="table table-hover table-bordered js-copytextarea" cellpadding="0"
                         cellspacing="0">
                    <thead>
                    <tr>
                      <th width="200">Mã danh mục</th>
                      <th width="300">Tên danh mục</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="c" items="${productDto.categories}">
                      <tr class="category-items">
                        <td>${c.id}</td>
                        <td>${c.name}</td>
                        <td><input type="checkbox"></td>
                      </tr>

                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-3">
                  <label for="exampleSelect1" class="control-label">Thông số kỹ thuật</label>
                  <select id="specOption" class="form-control">

                    <c:forEach items="${specificationDtos}" var="spec">
                      <option value="${spec.id}">${spec.name}</option>
                    </c:forEach>
                  </select>
                </div>
                <div class="form-group col-md-3">
                  <button class="side-button btn btn-add" type="button" title="Thêm vào danh sách"
                          onclick="addToSpecTable()">Thêm
                    vào
                    danh sách</button>
                  <button id="deleteButton" type="button" class="side-button btn btn-cancel" title="Xoá"
                          onclick="deleteFromTable()">Xoá</button>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-7">
                  <label for="exampleSelect1" class="control-label">Bảng thông số kỹ thuật</label>
                  <table id="specTable" class="table table-hover table-bordered js-copytextarea" cellpadding="0"
                         cellspacing="0">
                    <thead>
                    <tr>
                      <th width="200">Mã thông số kỹ thuật</th>
                      <th width="200">Thông số kỹ thuật</th>
                      <th width="400">Giá trị</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="specification" items="${listSpecificationValue}">
                      <tr class="spec-items">
                        <td>${specification.specificationId}</td>
                        <td>${specification.specificationName}</td>
                        <td>
                          <input type="text" name="3fromValue" class="fromValue" value="${specification.valueFrom}" style="width: 100px;">
                          -
                          <input type="text" name="3toValue" class="toValue" value="${specification.valueTo}"style="width: 100px;"></td><td><input type="checkbox">
                        </td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-12">
                  <label class="control-label required-field">Ảnh sản phẩm</label>
                  <div id="myfileupload">
                    <input type="file" id="uploadfile" name="ImageUpload" onchange="setThumbImage();" accept="image/*"
                    />
                  </div>
                  <div id="thumbbox">
                    <img height="450" width="400" alt="Thumb image" id="thumbimage" style="display: none" />
                  </div>
                </div>
                <div class="form-group col-md-12">
                  <label class="control-label">Mô tả sản phẩm</label>
                  <textarea class="form-control" name="mota" id="mota"></textarea>
                </div>
              </div>
              <div class="row">
                <div class="confirm-button">
                  <div class="button">
                    <button type="submit" id="createProduct" class="btn btn-save" >Lưu lại</button>
                    <button type="button" class="btn btn-cancel" onclick="location.href='product-management.html'">Hủy
                      bỏ</button>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </main>
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
              <p class="modal-notify-successful">Thay đổi thông tin sản phẩm thành công</p>
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
  MODAL CATEGORY EMPTY
  -->
  <div class="modal fade" id="categoryEmpty" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <p class="modal-notify-unsuccessful">Chưa có danh mục nào được chọn</p>
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
  MODAL UNSUCCESSFUL
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
              <p class="modal-notify-unsuccessful" id="reason">Thay đổi thông tin sản phẩm không thành công</p>
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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
  <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
  <script src="<c:url value="/js/popper.min.js"/>"></script>
  <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
  <script src="<c:url value="/js/main.js"/>"></script>
  <script src="<c:url value="/js/plugins/pace.min.js"/>"></script>
  <script src="<c:url value="/resources/data.json"/>"></script>
  <script>
    $('#updateProduct').on('submit',function(e) {
      e.preventDefault();
      if (validateTable()) {
        var id = $('#productId').val();
        var specificationValues = new Array();
        var categories = new Array();
        $(".spec-items").each(function () {
          var row = $(this);
          var specValue = new Object();
          specValue.specificationId = row.find("TD").eq(0).html();
          specValue.valueFrom = row.find("INPUT").eq(0).val();
          specValue.valueTo = row.find("INPUT").eq(1).val();
          specificationValues.push(specValue);
        });
        $(".category-items").each(function () {
          var row = $(this);
          var category = new Object();
          category.id = row.find("TD").eq(0).html();
          categories.push(category);
        });
        var data1 = {
          name: $('#name').val(),
          price: $('#price').val(),
          description: $('#mota').val(),
          supplierId: $('#supplierOption').val(),
          unit: $('#unit').val(),
          available:$('#available').val(),
          categories: categories,
          specificationValues: specificationValues
        };
        $.ajax({
          type: "POST",
          contentType: "application/json",
          url: "/admin/products/update/" + id ,
          data:
                  JSON.stringify(data1)
          ,
          dataType: "text",
          success: function (response) {
            if (response === "thành công") {
              $('#successful').modal('show');
            } else {
              document.getElementById("reason").innerText = response;
              $('#unsuccessful').modal('show');
            }
          },
          error: function (error) {
            window.location.replace('http://localhost:8083/auth/signin');
          }
        });
      }
    });
  </script>
  </body>

  </html>