<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Feedback | Quản trị Admin</title>
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
                <li class="breadcrumb-item">Feedback</li>
            </ul>
            <div id="clock"></div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <div class="tile-body">
                        <table class="table table-hover table-bordered js-copytextarea" cellpadding="0" cellspacing="0"
                            id="sampleTable">
                            <thead>
                                <tr>
                                    <th width="50">Mã khách hàng </th>
                                    <th width="150">Họ và tên</th>
                                    <th width="60">Mã đơn hàng</th>
                                    <th width="200">Tên sản phẩm</th>
                                    <th>Nội dung</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Hồ Thị Thanh Ngân</td>
                                    <td>MD0837</td>
                                    <td>LCD2004 chữ đen nền vàng xanh 50mA</td>
                                    <td>Tốt đáng mua</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Trần Khả Ái</td>
                                    <td>MD0837</td>
                                    <td>LCD1602 chữ trắng nền xanh dương 3mA</td>
                                    <td>Tốt đáng mua</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Phạm Thu Cúc</td>
                                    <td>MD0837</td>
                                    <td>Dây jumper đực/đực 10cm</td>
                                    <td>Tốt đáng mua</td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>Trần Anh Khoa</td>
                                    <td>MD0837</td>
                                    <td>Biến trở 2KΩ 1/2W 2.19mm</td>
                                    <td>Tốt đáng mua</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>Nguyễn Thành Nhân</td>
                                    <td>MD0837</td>
                                    <td>Biến trở 100KΩ 1/4W 1.78mm</td>
                                    <td>Tốt đáng mua</td>
                                </tr>
                                <tr>
                                    <td>6</td>
                                    <td>Nguyễn Đặng Trọng Nhân</td>
                                    <td>MD0837</td>
                                    <td>Biến trở 2KΩ 1/2W 2.19mm</td>
                                    <td>Không hợp</td>
                                </tr>
                                <tr>
                                    <td>7</td>
                                    <td>Nguyễn Thị Mai</td>
                                    <td>MD0837</td>
                                    <td>Biến trở 1KΩ 1/2W 2.36mm</td>
                                    <td>Tốt đáng mua</td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="pagination-row">
                            <div class="pagination-container">
                                <div class="dataTables_paginate paging_simple_numbers" id="sampleTable_paginate">
                                    <ul class="pagination">
                                        <li class="paginate_button page-item previous disabled"
                                            id="sampleTable_previous"><a href="#" aria-controls="sampleTable"
                                                data-dt-idx="0" tabindex="0" class="page-link">Lùi</a></li>
                                        <li class="paginate_button page-item active"><a href="#"
                                                aria-controls="sampleTable" data-dt-idx="1" tabindex="0"
                                                class="page-link">1</a></li>
                                        <li class="paginate_button page-item "><a href="#" aria-controls="sampleTable"
                                                data-dt-idx="2" tabindex="0" class="page-link">2</a></li>
                                        <li class="paginate_button page-item next" id="sampleTable_next"><a href="#"
                                                aria-controls="sampleTable" data-dt-idx="3" tabindex="0"
                                                class="page-link">Tiếp</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
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