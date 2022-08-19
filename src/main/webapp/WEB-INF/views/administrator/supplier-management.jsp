<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Quản lý nhà cung cấp | Quản trị</title>
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
  <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
  <jsp:include page="home-menu.jsp"/>
  <main class="app-content">
    <div class="app-title">
      <ul class="app-breadcrumb breadcrumb side">
        <li class="breadcrumb-item active"><a href="#"><b>Quản lý nhà cung cấp</b></a></li>
      </ul>
      <div id="clock"></div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="tile">
          <div class="tile-body">
            <div class="row element-button">
              <div class="col-sm-2">
                <a class="btn btn-add btn-sm" href="add-supplier.html" title="Thêm"><i class="fas fa-plus"></i>
                  Thêm nhà cung cấp</a>
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
                  <th width="100">Mã nhà cung cấp</th>
                  <th>Tên nhà cung cấp</th>
                  <th>Trang web </th>
                  <th width="80">Trạng thái</th>
                  <th width="100">Tuỳ chọn</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td>DAEWOO</td>
                  <td><a href="https://trangvangvietnam.com/" target=”_blank”>https://trangvangvietnam.com/</a></td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-supplier.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i
                        class="fas fa-edit"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>ABECO</td>
                  <td><a href="http://abecovietnam.com/" target=”_blank”>http://abecovietnam.com/</a></td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-supplier.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i
                        class="fas fa-edit"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>Nguyên Phi</td>
                  <td><a href="https://trangvangvietnam.com/" target=”_blank”>http://trangvangtructuyen.vn/</a></td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-supplier.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i
                        class="fas fa-edit"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>4</td>
                  <td>Banlinhkien</td>
                  <td><a href="https://trangvangvietnam.com/" target=”_blank”>https://banlinhkien.com/</a></td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#enableStatus" name="check">
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-supplier.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i
                        class="fas fa-edit"></i></a>
                  </td>
                </tr>
              </tbody>
            </table>
            <div class="pagination-row">
              <div class="pagination-container">
                <div class="dataTables_paginate paging_simple_numbers" id="sampleTable_paginate">
                  <ul class="pagination">
                    <li class="paginate_button page-item previous disabled" id="sampleTable_previous"><a href="#"
                        aria-controls="sampleTable" data-dt-idx="0" tabindex="0" class="page-link">Lùi</a></li>
                    <li class="paginate_button page-item active"><a href="#" aria-controls="sampleTable" data-dt-idx="1"
                        tabindex="0" class="page-link">1</a></li>
                    <li class="paginate_button page-item "><a href="#" aria-controls="sampleTable" data-dt-idx="2"
                        tabindex="0" class="page-link">2</a></li>
                    <li class="paginate_button page-item next" id="sampleTable_next"><a href="#"
                        aria-controls="sampleTable" data-dt-idx="3" tabindex="0" class="page-link">Tiếp</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
  <!--
  MODAL CONFIRM STATUS 
-->
  <div class="modal fade" id="disableStatus" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <label class="control-label">Bạn có chắc chắn vô hiệu hoá nhà cung cấp này này</label>
            </div>
          </div>
          <div style="display: flex; justify-content: center; padding: 10px;">
            <a style="margin: 5px;" class="btn btn-save" data-dismiss="modal" href="#">Xác nhận</a>
            <a style="margin: 5px;" class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
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
  MODAL ENABLE STATUS 
-->
  <div class="modal fade" id="enableStatus" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <label class="control-label">Bạn có chắc chắn kích hoạt sản phẩm này</label>
            </div>
          </div>
          <div style="display: flex; justify-content: center; padding: 10px;">
            <a style="margin: 5px;" class="btn btn-save" data-dismiss="modal" href="#">Xác nhận</a>
            <a style="margin: 5px;" class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
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
  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="src/jquery.table2excel.js"></script>
  <script src="js/main.js"></script>
  <!-- The javascript plugin to display page loading on top-->
  <script src="js/plugins/pace.min.js"></script>
  <!-- Page specific javascripts-->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
</body>

</html>