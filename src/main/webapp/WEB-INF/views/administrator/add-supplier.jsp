<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
  <script>

    function readURL(input, thumbimage) {
      if (input.files && input.files[0]) { //Sử dụng  cho Firefox - chrome
        var reader = new FileReader();
        reader.onload = function (e) {
          $("#thumbimage").attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
      }
      else { // Sử dụng cho IE
        $("#thumbimage").attr('src', input.value);

      }
      $("#thumbimage").show();
      $('.filename').text($("#uploadfile").val());
      $('.Choicefile').css('background', '#14142B');
      $('.Choicefile').css('cursor', 'default');
      $(".removeimg").show();
      $(".Choicefile").unbind('click');

    }
    $(document).ready(function () {
      $(".Choicefile").bind('click', function () {
        $("#uploadfile").click();

      });
      $(".removeimg").click(function () {
        $("#thumbimage").attr('src', '').hide();
        $("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
        $(".removeimg").hide();
        $(".Choicefile").bind('click', function () {
          $("#uploadfile").click();
        });
        $('.Choicefile').css('background', '#14142B');
        $('.Choicefile').css('cursor', 'pointer');
        $(".filename").text("");
      });
    })
  </script>
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
              <li><a class="app-menu__item" href="${pageContext.request.contextPath}/admin/categories"><i class='app-menu__icon bx bx-category'></i><span
                      class="app-menu__label">Quản lý danh mục</span></a></li>

              <li><a class="app-menu__item active" href="${pageContext.request.contextPath}/admin/suppliers"><i
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
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/suppliers">Quản lý nhà cung cấp</a></li>
        <li class="breadcrumb-item active">Thêm nhà cung cấp</li>
      </ul>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="tile">
          <h3 class="tile-title">Thêm nhà cung cấp</h3>
          <div class="tile-body">
            <%--@elvariable id="newSupplier" type="com.example.electriccomponentsshop.dto.SupplierDTO"--%>
            <form:form action="${pageContext.request.contextPath}/admin/suppliers/add" method="post" modelAttribute="newSupplier">
              <div class="row">
                <div class="form-group col-md-3">
                  <label class="control-label required-field">Tên nhà cung cấp</label>
                  <form:input class="form-control" type="text" required="required" path="name"/>
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label">Số điện thoại</label>
                  <form:input class="form-control" type="text" path="phone"/>
                </div>
              </div>
              <div class="row">
                <div class="confirm-button">
                  <div class="button">
                    <button type="submit" class="btn btn-save" >Lưu lại</button>
                    <button type="button" class="btn btn-cancel" onclick="location.href='supplier-management.html'">Hủy
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
<script src="<c:url value="/js/jquery-3.2.1.min.js"/>></script>
<script src="<c:url value="/js/popper.min.js"/>></script>
<script src="<c:url value="/js/bootstrap.min.js"/>></script>
<script src="<c:url value="/js/main.js"/>></script>
<script src="<c:url value="/js/plugins/pace.min.js"/>></script>

</body>

</html>