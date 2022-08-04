<!DOCTYPE html>
<html lang="en">

<head>
  <title>Quản lý đơn hàng | Quản trị</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Main CSS-->
  <link rel="stylesheet" type="text/css" href="css/main.css">
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
  <header class="app-header">
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
      aria-label="Hide Sidebar"></a>
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
  <!-- Sidebar menu-->
  <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
  <aside class="app-sidebar">
    <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="resources/images/avatar.jpg" width="50px"
        alt="User Image">
      <div>
        <p class="app-sidebar__user-name"><b>Bùi Minh Hiệu</b></p>
        <p class="app-sidebar__user-designation">Quản lý</p>
      </div>
    </div>
    <hr>
    <ul class="app-menu">
      <li><a class="app-menu__item" href="home-page.html"><i class='app-menu__icon bx bx-cart-alt'></i>
          <span class="app-menu__label">Trang chủ</span></a></li>
      <li><a class="app-menu__item" href="system-account-management.html"><i class='app-menu__icon bx bx-id-card'></i>
          <span class="app-menu__label">Quản lý tài khoản</span>
        </a>
      </li>
      <li><a class="app-menu__item" href="category-management.html"><i class='app-menu__icon bx bx-category'></i><span
            class="app-menu__label">Quản lý danh mục</span></a></li>
      <li><a class="app-menu__item" href="product-management.html"><i
            class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản
            phẩm</span></a>
      </li>
      <li><a class="app-menu__item" href="supplier-management.html"><i
            class='app-menu__icon bx bxs-user-account'></i><span class="app-menu__label">Quản lý nhà cung cấp
          </span></a></li>
      <li><a class="app-menu__item" href="warehouse-management.html"><i
            class='app-menu__icon bx bx-building-house'></i><span class="app-menu__label">Quản lý kho
            hàng
          </span></a></li>
      <li><a class="app-menu__item active" href="order-management.html"><i class='app-menu__icon bx bx-task'></i><span
            class="app-menu__label">Quản lý đơn hàng</span></a></li>
      <li><a class="app-menu__item" href="product-package-management.html"><i
            class='app-menu__icon bx bxs-package '></i><span class="app-menu__label">Quản lý lô sản
            phẩm</span></a>
      </li>
      <li><a class="app-menu__item" href="feedback-management.html"><i class='app-menu__icon bx bx-user-voice'></i><span
            class="app-menu__label">Feedback</span></a>
      </li>
      <li><a class="app-menu__item" href="report.html"><i class='app-menu__icon bx bx-pie-chart-alt-2'></i><span
            class="app-menu__label">Báo cáo thống kê</span></a>
      </li>
    </ul>
  </aside>
  <main class="app-content">
    <div class="app-title">
      <ul class="app-breadcrumb breadcrumb side">
        <li class="breadcrumb-item active"><a href="#"><b>Quản lý đơn hàng</b></a></li>
      </ul>
      <div id="clock"></div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="tile">
          <div class="tile-body">
            <div class="row element-button">
              <div id="myBtnContainer">
                <button class="filter-btn active">Chờ xử lý</button>
                <button class="filter-btn">Đang giao hàng</button>
                <button class="filter-btn">Hoàn thành</button>
                <button class="filter-btn">Đã huỷ</button> 
              </div>
            </div>
            <div class="search-row">
              <div class="search-container">
                <form action="/action_page.php">
                  <input type="text" placeholder="Tìm kiếm" name="search">
                  <button type="submit"><i class="fa fa-search"></i></button>
                </form>
              </div>
            </div>
            <table class="table table-hover table-bordered" id="sampleTable">
              <thead>
                <tr>
                  <th>Mã đơn hàng</th>
                  <th>Khách hàng</th>
                  <th>Đơn hàng</th>
                  <th>Số lượng</th>
                  <th>Tổng tiền</th>
                  <th>Tình trạng</th>
                  <th>Tuỳ chọn</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>MD0837</td>
                  <td>Triệu Thanh Phú</td>
                  <td>Ghế làm việc Zuno, Bàn ăn gỗ Theresa</td>
                  <td>2</td>
                  <td>9.400.000 đ</td>
                  <td><span class="badge bg-success">Hoàn thành</span></td>
                  <td>
                    <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng" data-toggle="modal"
                      data-target="#adddanhmuc"><i class="fa fa-info"></i></button>
                  </td>
                </tr>
                <tr>
                  <td>MĐ8265</td>
                  <td>Nguyễn Thị Ngọc Cẩm</td>
                  <td>Ghế ăn gỗ Lucy màu trắng</td>
                  <td>1</td>
                  <td>3.800.000 đ</td>
                  <td><span class="badge bg-success">Hoàn thành</span></td>
                  <td>
                    <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng" data-toggle="modal"
                      data-target="#adddanhmuc"><i class="fa fa-info"></i></button>
                  </td>
                </tr>
                <tr>
                  <td>MT9835</td>
                  <td>Đặng Hoàng Phúc</td>
                  <td>Giường ngủ Jimmy, Bàn ăn mở rộng cao cấp Dolas, Ghế làm việc Zuno</td>
                  <td>3 </td>
                  <td>40.650.000 đ</td>
                  <td><span class="badge bg-success">Hoàn thành</span></td>
                  <td>
                    <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng" data-toggle="modal"
                      data-target="#adddanhmuc"><i class="fa fa-info"></i></button>
                  </td>
                </tr>
                <tr>
                  <td>ER3835</td>
                  <td>Nguyễn Thị Mỹ Yến</td>
                  <td>Bàn ăn mở rộng Gepa</td>
                  <td>1 </td>
                  <td>16.770.000 đ</td>
                  <td><span class="badge bg-info">Chờ xử lý</span></td>
                  <td><button class="btn btn-primary btn-sm" type="button" title="Tuỳ chọn cho đơn hàng"
                      data-toggle="modal" data-target="#confirmOrder"><i class="fas fa-edit"></i>
                    </button>
                    <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng" data-toggle="modal"
                      data-target="#adddanhmuc"><i class="fa fa-info"></i></button>
                  </td>
                </tr>
                <tr>
                  <td>AL3947</td>
                  <td>Phạm Thị Ngọc</td>
                  <td>Bàn ăn Vitali mặt đá, Ghế ăn gỗ Lucy màu trắng</td>
                  <td>2 </td>
                  <td>19.770.000 đ</td>
                  <td><span class="badge bg-warning">Đang giao hàng</span></td>
                  <td><button class="btn btn-primary btn-sm" type="button" title="Tuỳ chọn cho đơn hàng"
                      data-toggle="modal" data-target="#confirmFinished"><i class=" fas fa-edit"></i>
                    </button>
                    <button class="btn btn-primary btn-sm" type="button" title="Chi tiết đơn hàng" data-toggle="modal"
                      data-target="#adddanhmuc"><i class="fa fa-info"></i></button>
                  </td>
                </tr>
                <tr>
                  <td>QY8723</td>
                  <td>Ngô Thái An</td>
                  <td>Giường ngủ Kara 1.6x2m</td>
                  <td>1 </td>
                  <td>14.500.000 đ</td>
                  <td><span class="badge bg-danger">Đã hủy</span></td>
                  <td>
                    <button class="btn btn-primary btn-sm edit" type="button" title="Chi tiết đơn hàng"
                      data-toggle="modal" data-target="#adddanhmuc"><i class="fa fa-info"></i></button>
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
  MODAL
-->
  <div class="modal fade" id="adddanhmuc" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
    data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">

        <div class="modal-body">
          <div class="row">
            <div class="form-group  col-md-12">
              <span class="thong-tin-thanh-toan">
                <h5>Chi tiết đơn hàng </h5>
              </span>
            </div>
            <div class="form-group col-md-12">
              <ul style="padding-left: 20px;">
                <li>Ghế làm việc Zuno x 2</li>
                <li>Bàn ăn gỗ Theresa x 5</li>
              </ul>
            </div>
          </div>
          <div style="display: flex; justify-content: flex-end;">
            <button class="btn btn-save" data-dismiss="modal" type="button">Quay lại</button>
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
  MODAL CONFIRM FINISHED
-->
  <div class="modal fade" id="confirmFinished" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <label class="control-label">Xác nhận đơn hàng đã được giao và thanh toán</label>
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
  MODAL CONFIRM ORDER
-->
  <div class="modal fade" id="confirmOrder" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <label class="control-label">Bạn có muốn chấp nhận đơn hàng này</label>
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
  <script>
    function deleteRow(r) {
      var i = r.parentNode.parentNode.rowIndex;
      document.getElementById("myTable").deleteRow(i);
    }
    jQuery(function () {
      jQuery(".trash").click(function () {
        swal({
          title: "Cảnh báo",

          text: "Bạn có chắc chắn là muốn xóa?",
          buttons: ["Hủy bỏ", "Đồng ý"],
        })
          .then((willDelete) => {
            if (willDelete) {
              swal("Đã xóa thành công.!", {

              });
            }
          });
      });
    });
    oTable = $('#sampleTable').dataTable();
    $('#all').click(function (e) {
      $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
      e.stopImmediatePropagation();
    });

    //EXCEL
    // $(document).ready(function () {
    //   $('#').DataTable({

    //     dom: 'Bfrtip',
    //     "buttons": [
    //       'excel'
    //     ]
    //   });
    // });


    //Thời Gian
    function time() {
      var today = new Date();
      var weekday = new Array(7);
      weekday[0] = "Chủ Nhật";
      weekday[1] = "Thứ Hai";
      weekday[2] = "Thứ Ba";
      weekday[3] = "Thứ Tư";
      weekday[4] = "Thứ Năm";
      weekday[5] = "Thứ Sáu";
      weekday[6] = "Thứ Bảy";
      var day = weekday[today.getDay()];
      var dd = today.getDate();
      var mm = today.getMonth() + 1;
      var yyyy = today.getFullYear();
      var h = today.getHours();
      var m = today.getMinutes();
      var s = today.getSeconds();
      m = checkTime(m);
      s = checkTime(s);
      nowTime = h + " giờ " + m + " phút " + s + " giây";
      if (dd < 10) {
        dd = '0' + dd
      }
      if (mm < 10) {
        mm = '0' + mm
      }
      today = day + ', ' + dd + '/' + mm + '/' + yyyy;
      tmp = '<span class="date"> ' + today + ' - ' + nowTime +
        '</span>';
      document.getElementById("clock").innerHTML = tmp;
      clocktime = setTimeout("time()", "1000", "Javascript");

      function checkTime(i) {
        if (i < 10) {
          i = "0" + i;
        }
        return i;
      }
    }
    //In dữ liệu
    var myApp = new function () {
      this.printTable = function () {
        var tab = document.getElementById('sampleTable');
        var win = window.open('', '', 'height=700,width=700');
        win.document.write(tab.outerHTML);
        win.document.close();
        win.print();
      }
    }
    //     //Sao chép dữ liệu
    //     var copyTextareaBtn = document.querySelector('.js-textareacopybtn');

    // copyTextareaBtn.addEventListener('click', function(event) {
    //   var copyTextarea = document.querySelector('.js-copytextarea');
    //   copyTextarea.focus();
    //   copyTextarea.select();

    //   try {
    //     var successful = document.execCommand('copy');
    //     var msg = successful ? 'successful' : 'unsuccessful';
    //     console.log('Copying text command was ' + msg);
    //   } catch (err) {
    //     console.log('Oops, unable to copy');
    //   }
    // });


    //Modal
    $("#show-emp").on("click", function () {
      $("#ModalUP").modal({ backdrop: false, keyboard: false })
    });
  </script>
</body>

</html>