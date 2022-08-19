<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Thông tin cá nhân người dùng | Quản trị</title>
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

<body class="app sidebar-mini rtl">

  <!-- Navbar-->
 <jsp:include page="header.jsp"/>
  <!-- Sidebar menu-->
  <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
  <jsp:include page="home-menu.jsp"/>
  <main class="app-content">
    <div class="app-title">
      <ul class="app-breadcrumb breadcrumb">
        <li class="breadcrumb-item"><a href="#">thông tin cá nhân</a></li>
      </ul>
    </div>
    <div class="row">
      <div class="col-md-12">

        <div class="tile">

          <h3 class="tile-title">Thông tin cá nhân</h3>
          <div class="tile-body">
            <form>
              <div class="row">
                <div class="form-group col-md-4">
                  <label class="control-label required-field">Họ và tên</label>
                  <input class="form-control" type="text" value="Bùi Minh Hiệu" required>
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label required-field">Giới tính</label>
                  <div class="options">
                    <div class="option-item">
                      <label for="yes"> <input name="gender" type="radio" value="Nam" checked />
                        Nam </label>
                    </div>
                    <div class="option-item">
                      <label for="no"> <input name="gender" type="radio" value="Nữ" />
                        Nữ </label>
                    </div>
                  </div>
                </div>
                <div class="form-group col-md-4">
                  <label class="control-label required-field">Ngày sinh</label>
                  <input class="form-control" type="date" value="2000-02-09" required>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-3">
                  <label for="exampleSelect1" class="control-label">Tỉnh/Thành phố</label>
                  <select class="form-control" id="province">
                    <option value="-1">-- Chọn tỉnh thành --</option>
                  </select>
                </div>
                <div class="form-group col-md-3">
                  <label for="exampleSelect1" class="control-label">Quận/Huyện</label>
                  <select class="form-control" id="district">
                    <option value="-1">-- Chọn quận/huyện --</option>
                  </select>
                </div>
                <div class="form-group col-md-3">
                  <label for="exampleSelect1" class="control-label">Phường/Xã/Thị trấn</label>
                  <select class="form-control" id="town">
                    <option value="-1">-- Chọn phường/xã/thị trấn --</option>
                  </select>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-4">
                  <label class="control-label required-field">Địa chỉ email</label>
                  <input class="form-control" type="text" value="hieubmhe140134@fpt.edu.vn" required>
                </div>
                <div class="form-group col-md-4">
                  <label class="control-label required-field">Số điện thoại</label>
                  <input class="form-control" type="number" value="0945631764" required>
                </div>
                <div class="form-group col-md-4">
                  <label class="control-label required-field">Mật khẩu</label>
                  <input class="form-control" type="password" value="293884hkhf8H" required>
                </div>
              </div>
              <div class="row">
                <div class="confirm-button">
                  <div class="button">
                    <button type="submit" class="btn btn-save" type="button">Lưu lại</button>
                    <button class="btn btn-cancel" href="/doc/table-data-table.html">Hủy bỏ</button>
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