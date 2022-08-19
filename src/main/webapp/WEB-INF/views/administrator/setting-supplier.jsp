<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Tuỳ chỉnh nhà cung cấp | Quản trị</title>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Main CSS-->
  <link href="<c:url value="/css/main.css"/>" rel="stylesheet" type="text/css">
  <!-- Font-icon css-->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
  <!-- or -->
  <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
  <link rel="stylesheet" type="text/css"
    href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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
        <li class="breadcrumb-item"><a href="supplier-management.html">Quản lý nhà cung cấp</a></li>
        <li class="breadcrumb-item active">Thêm nhà cung cấp</li>
      </ul>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="tile">
          <h3 class="tile-title">Thêm nhà cung cấpt</h3>
          <div class="tile-body">
            <form>
              <div class="row">
                <div class="form-group col-md-3">
                  <label class="control-label required-field">Mã nhà cung cấp</label>
                  <input class="form-control" type="text" disabled value="SP-1">
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label required-field">Tên nhà cung cấp</label>
                  <input class="form-control" type="text" value="DAEWOO">
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label required-field">Địa chỉ trang web</label>
                  <input class="form-control" type="text" value="http://trangvangtructuyen.vn/">
                </div>
              </div>
              <div class="row">
                <div class="confirm-button">
                  <div class="button">
                    <button type="submit" class="btn btn-save" type="button">Lưu lại</button>
                    <button type="button" class="btn btn-cancel" onclick="location.href='supplier-management.html'">Hủy
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

  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/main.js"></script>
  <script src="js/plugins/pace.min.js"></script>
</body>

</html>