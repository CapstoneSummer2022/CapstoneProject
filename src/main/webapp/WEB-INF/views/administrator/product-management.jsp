<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Quản lý sản phẩm | Quản trị</title>
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
        <li class="breadcrumb-item active"><a href="#"><b>Quản lý sản phẩm</b></a></li>
      </ul>
      <div id="clock"></div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="tile">
          <div class="tile-body">
            <div class="row element-button">
              <div class="col-sm-2">
                <a class="btn btn-add btn-sm" href="add-product.html" title="Thêm"><i class="fas fa-plus"></i>
                  Thêm sản phẩm</a>
              </div>
              <div class="col-sm-2">
                <a class="btn btn-delete btn-sm nhap-tu-file" type="button" title="Nhập" data-toggle="modal"
                  data-target="#importFile"><i class="fas fa-file-upload"></i> Nhập dữ liệu từ
                  file</a>
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
                  <th>Mã sản phẩm</th>
                  <th>Tên sản phẩm</th>
                  <th>Ảnh sản phẩm</th>
                  <th>Số lượng tồn kho</th>
                  <th>Tình trạng</th>
                  <th>Giá bán</th>
                  <th>Danh mục</th>
                  <th>Trạng thái</th>
                  <th>Tuỳ chọn</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1-A-2-2</td>
                  <td>Diode Xung Đôi SBL3040 TO-247 600V 30A</td>
                  <td><img src="/img-sanpham/theresa.jpg" alt="" width="100px;"></td>
                  <td>40</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>5.600 đ</td>
                  <td>Đi-ốt</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i
                        class="fas fa-edit"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>1-A-10-1</td>
                  <td>Diode Chỉnh Lưu FR307 3A 1000V</td>
                  <td><img src="/img-sanpham/reno.jpg" alt="" width="100px;"></td>
                  <td>70</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>24.200 đ</td>
                  <td>Đi-ốt</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>1-A-6-2</td>
                  <td>Diode Cầu 25A Dẹt 1000V KBJ2510</td>
                  <td><img src="/img-sanpham/matda.jpg" alt="" width="100px;"></td>
                  <td>40</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>33.235 đ</td>
                  <td>Đi-ốt</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>1-A-10-2</td>
                  <td>Tụ Hóa 6.3V</td>
                  <td><img src="/img-sanpham/ghethera.jpg" alt="" width="100px;"></td>
                  <td>50</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>9.500 đ</td>
                  <td>Tụ điện</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>1-A-6-5</td>
                  <td>Tụ Phân Tần Loa Trebel CBB 335J 250V</td>
                  <td><img src="/img-sanpham/zuno.jpg" alt="" width="100px;"></td>
                  <td>50</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>3.800 đ</td>
                  <td>Tụ điện</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>

                  </td>
                </tr>
                <tr>
                  <td>1-A-10-6</td>
                  <td>Cuộn cảm HCI1005LF-10NJ-MS8</td>
                  <td><img src="/img-sanpham/vita.jpg" alt="" width="100px;"></td>
                  <td>55</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>4.600 đ</td>
                  <td>Cuộn cảm</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>1-A-5-8</td>
                  <td>Cuộn cảm SDB0625 10uH</td>
                  <td><img src="/img-sanpham/lucy.jpg" alt="" width="100px;"></td>
                  <td>38</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>11.000 đ</td>
                  <td>Cuộn cảm</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>1-A-6-8</td>
                  <td>Cuộn cảm DRGR875 10000uH</td>
                  <td><img src="/img-sanpham/vegas.jpg" alt="" width="100px;"></td>
                  <td>80</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>21.550 đ</td>
                  <td>Cuộn cảm</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>

                  </td>
                </tr>
                <tr>
                  <td>1-A-5-9</td>
                  <td>Điện trở 100Ω 1/2W</td>
                  <td><img src="/img-sanpham/banan.jpg" alt="" width="100px;"></td>
                  <td>80</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>16.770 đ</td>
                  <td>Điện trở</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>

                  </td>
                </tr>
                <tr>
                  <td>1-A-3-10</td>
                  <td>Điện trở 1Ω 1/4W</td>
                  <td><img src="/img-sanpham/cera.jpg" alt="" width="100px;"></td>
                  <td>46</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>20.790 đ</td>
                  <td>Điện trở</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>

                  </td>
                </tr>
                <tr>
                  <td>1-B-3-2</td>
                  <td>Dây jumper đực/đực 10cm</td>
                  <td><img src="/img-sanpham/dolas.jpg" alt="" width="100px;"></td>
                  <td>66</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>22.650 đ</td>
                  <td>Dây kết nối</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>

                  </td>
                </tr>
                <tr>
                  <td>1-C-2-1</td>
                  <td>LCD1602 chữ trắng nền xanh dương 3mA</td>
                  <td><img src="/img-sanpham/tu.jpg" alt="" width="100px;"></td>
                  <td>0</td>
                  <td><span class="badge bg-danger">Hết hàng</span></td>
                  <td>24.500 đ</td>
                  <td>LCD</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>

                  </td>
                </tr>
                <tr>
                  <td>1-C-1-1</td>
                  <td>LCD2004 chữ đen nền vàng xanh 50mA</td>
                  <td><img src="/img-sanpham/thomas.jpg" alt="" width="100px;"></td>
                  <td>73</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>12.950 đ</td>
                  <td>LCD</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>

                  </td>
                </tr>
                <tr>
                  <td>1-B-10-10</td>
                  <td>LED xanh ngọc lục bảo 5mm</td>
                  <td><img src="/img-sanpham/jimmy.jpg" alt="" width="100px;"></td>
                  <td>60</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>16.320 đ</td>
                  <td>LED</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>

                  </td>
                </tr>
                <tr>
                  <td>1-B-9-10</td>
                  <td>LED cực tím 5mm</td>
                  <td><img src="/img-sanpham/tare.jpg" alt="" width="100px;"></td>
                  <td>65</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>19.600 đ</td>
                  <td>LED</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#disableStatus" name="check" checked>
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>
                  </td>
                </tr>
                <tr>
                  <td>1-B-8-10</td>
                  <td>LED hổ phách 5mm</td>
                  <td><img src="/img-sanpham/kara.jpg" alt="" width="100px;"></td>
                  <td>60</td>
                  <td><span class="badge bg-success">Còn hàng</span></td>
                  <td>14.500 đ</td>
                  <td>LED</td>
                  <td>
                    <label class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox" data-toggle="modal"
                        data-target="#enableStatus" name="check">
                      <span class="slider round"></span>
                    </label>
                  </td>
                  <td>
                    <a href="setting-product.html" class="btn btn-primary btn-sm edit" type="button" title="Sửa"
                      id="show-emp"><i class="fas fa-edit"></i></a>
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
  MODAL IMPORT FILE 
-->
  <div class="modal fade" id="importFile" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
    data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <form>
          <div class="modal-body">
            <div class="row">
              <div class="form-group col-md-12">
                <span class="thong-tin-thanh-toan">
                  <h5>Chọn file</h5>
                </span>
              </div>
              <div class="form-group col-md-12" style="text-align: center;">
                <input type="file" required id="fileInput" value="Chọn tệp" class="btn input-file">
              </div>
            </div>
            <div style="display: flex; justify-content: center; padding: 10px;">
              <button style="margin: 5px;" class="btn btn-save" type="submit">Xác nhận</button>
              <button style="margin: 5px;" class="btn btn-cancel" data-dismiss="modal"
                onclick="removeSelectedFile()">Hủy bỏ</a>
            </div>
          </div>
          <div class="modal-footer">
          </div>
        </form>
      </div>
    </div>
  </div>
  <!--
MODAL
-->
  <!--
  MODAL DISABLE STATUS 
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
              <label class="control-label">Bạn có chắc chắn vô hiệu hoá sản phẩm này</label>
            </div>
          </div>
          <div style="display: flex; justify-content: center; padding: 10px;">
            <button style="margin: 5px;" class="btn btn-save" data-dismiss="modal">Xác nhận</button>
            <button style="margin: 5px;" class="btn btn-cancel" data-dismiss="modal">Hủy bỏ</button>
          </div>
        </div>
        <div class="modal-footer">
        </div>
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
  <!--
  MODAL IMPORT FILE ARLERT 
-->
  <div class="modal fade" id="importAlert" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <label class="control-label">(Thông báo về việc file excel đã nhập thành công hay chưa)</label>
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