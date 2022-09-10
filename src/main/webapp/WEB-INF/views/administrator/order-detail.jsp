<jsp:useBean id="orderDto" scope="request" type="shop.db.dto.order.OrderDTO"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Chi tiết đơn hàng | Quản trị</title>
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
                <li class="breadcrumb-item"><a href="order-management.html"><b>Quản lý đơn hàng</b></a></li>
                <li class="breadcrumb-item active"><b>Chi tiết đơn hàng</b></li>
            </ul>
            <div id="clock"></div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <h3 class="tile-title">Thông tin nhận hàng</h3>
                    <div class="tile-body" >
                        <div class="row">
                            <div class="form-group col-md-2">
                                <label class="control-label ">Họ và tên người nhận</label>

                                    ${orderDto.receivedPerson}
                            </div>
                            <div class="form-group col-md-4">
                                <label for="exampleSelect1" class="control-label">Địa chỉ giao hàng</label>
                                <p class="detail-text">${orderDto.detailLocation}, ${orderDto.wardName}, ${orderDto.districtName}, ${orderDto.provinceName}</p>
                            </div>
                            <div class="form-group col-md-2">
                                <label class="control-label">Số điện thoại</label>
                                <p class="detail-text">${orderDto.receivedPhone}</p>
                            </div>
                            <div class="form-group col-md-4">
                                <label class="control-label">Số tiền khách hàng cần thanh toán</label>
                                <p class="detail-text">${orderDto.totalPayment}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-4">
                                <label class="control-label">Tình trạng đơn hàng</label>
                                <p class="detail-text"><span class="badge bg-warning">${orderDto.status}</span></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="modal-confirm-button">
                                <div>
                                    <c:if test="${orderDto.status == 'Chờ xử lý'}">
                                        <button class="btn btn-primary" data-toggle="modal"
                                                data-target="#confirmOrder">Xác nhận đơn hàng</button>
                                    </c:if>
                                    <c:if test="${orderDto.status == 'Đã xác nhận'}">
                                        <button class="btn btn-primary" data-toggle="modal"
                                                data-target="#confirmGiaohang">Đóng gói và giao hàng</button>
                                    </c:if>
                                    <c:if test="${orderDto.status == 'Đang giao hàng'}">
                                        <button class="btn btn-primary" data-toggle="modal"
                                                data-target="#confirmFinished">Hoàn thành</button>
                                    </c:if>
                                    <c:if test="${orderDto.status != 'Hoàn thành'}">
                                        <button class="btn btn-cancel" data-toggle="modal"
                                                data-target="#confirmCancel">Huỷ đơn hàng</button>
                                    </c:if>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="tile">
                    <div class="tile-body">
                        <h3 class="tile-title">Thông tin giỏ hàng</h3>
                        <table class="table table-hover table-bordered" id="sampleTable">
                            <thead>
                            <tr>
                                <th>Mã sản phẩm</th>
                                <th>Tên sản phẩm</th>
                                <th>Ảnh sản phẩm</th>
                                <th>Danh mục</th>
                                <th>Đơn giá</th>
                                <th>Số lượng đặt hàng</th>
                                <th>Thành tiền</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var = "orderItemDTO" items = "${orderDto.orderItems}">
                                <tr>
                                    <td>${orderItemDTO.productId}</td>
                                    <td>${orderItemDTO.productName}</td>
                                    <td><img src="resources/images/diode.jpg" alt="" width="100px;"></td>
                                    <td>Dây kết nối</td>
                                    <td>${orderItemDTO.unitPrice} đ</td>
                                    <td>${orderItemDTO.quantity}</td>
                                    <td>${orderItemDTO.subTotal} đ</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="6" style="font-weight: bold;">Tổng tiền: </td>
                                <td>${orderDto.totalPayment} đ</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <!--
MODAL CONFIRM đóng gói và giao hàng
-->
    <div class="modal fade" id="confirmGiaohang" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
                            <label class="control-label">Xác nhận đóng gói và giao hàng </label>
                        </div>
                    </div>
                    <div style="display: flex; justify-content: center; padding: 10px;">
                        <form method="POST" action="${pageContext.request.contextPath}/admin/orders/update/${orderDto.id}">
                            <input style="margin: 5px;" type="submit" class="btn btn-save"  value="Xác nhận"/>
                        </form>
                        <a style="margin: 5px;" class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
                    </div>
                </div>
                <div class="modal-footer">
                </div>
            </div>
        </div>
    </div>
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
                        <form method="POST" action="${pageContext.request.contextPath}/admin/orders/update/${orderDto.id}">
                            <input style="margin: 5px;" type="submit" class="btn btn-save"  value="Xác nhận"/>
                        </form>
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
                            <label class="control-label">Bạn có muốn xác nhận đơn hàng này</label>
                        </div>
                    </div>
                    <div style="display: flex; justify-content: center; padding: 10px;">
                        <form method="POST" action="${pageContext.request.contextPath}/admin/orders/update/${orderDto.id}">
                            <input type="submit" class="btn btn-save"  value="Xác nhận"/>
                        </form>

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
MODAL CONFIRM CANCEL
-->
    <div class="modal fade" id="confirmCancel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
                            <label class="control-label">Bạn có muốn huỷ đơn hàng này?</label>
                        </div>
                    </div>
                    <div style="display: flex; justify-content: center; padding: 10px;">
                        <form method="POST" action="${pageContext.request.contextPath}/admin/orders/update/${orderDto.id}">
                            <input type="submit" class="btn btn-save"  value="Xác nhận"/>
                        </form>
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
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/popper.min.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/js/main.js"/>"></script>
    <script src="<c:url value="/js/plugins/pace.min.js"/>"></script>
    <script src="src/jquery.table2excel.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <!-- Page specific javascripts-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
</body>

</html>