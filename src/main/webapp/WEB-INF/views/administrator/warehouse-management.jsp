<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Quản lý kho hàng | Quản trị</title>
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

<body class="app sidebar-mini rtl">
<!-- Navbar-->
<jsp:include page="header.jsp"/>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<jsp:include page="warehouse-home-menu.jsp"/>
<main class="app-content">
  <div class="app-title">
    <ul class="app-breadcrumb breadcrumb side">
      <li class="breadcrumb-item active"><a href="#"><b>Quản lý kho hàng</b></a></li>
    </ul>
    <div id="clock"></div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <div class="tile">
        <div class="tile-body">
          <div class="row element-button">
            <div class="col-sm-2">
              <a class="btn btn-add btn-sm" href="${pageContext.request.contextPath}/admin/warehouses/add" title="Thêm"><i class="fas fa-plus"></i>
                Thêm kho hàng</a>
            </div>
          </div>
          <table class="table table-hover table-bordered" cellpadding="0" cellspacing="0" id="sampleTable">
            <thead>
            <tr>
              <th width="150">Mã kho hàng</th>
              <th width="300">Địa chỉ kho hàng</th>
              <th width="80">Trạng thái</th>
              <th width="100">Tuỳ chọn</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="warehouse" items="${warehouses}">
              <tr>
                <td>${warehouse.id}</td>
                <td>${warehouse.detailLocation}, ${warehouse.wardName}, ${warehouse.districtName}, ${warehouse.provinceName}</td>
                <td>
                  <div class="switch">
                  <input class="status-checkbox" onclick="return false" type="checkbox"  name="check" <c:if test="${warehouse.status == 1}"> checked</c:if>/>

                  <span class="slider round"></span>
                    <div style="position:absolute; left:0; right:0; top:0; bottom:0;" onclick="updateWarehouseStatus(this)"data-toggle="modal"
                            <c:if test="${warehouse.status == 1}"> data-target="#disableStatus"</c:if> <c:if test="${warehouse.status == 0}"> data-target="#enableStatus"</c:if> ></div>
                </div>
                </td>
                <td>
                  <a href="${pageContext.request.contextPath}/admin/warehouses/edit/${warehouse.id}" class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i
                          class="fas fa-edit"></i></a>
                </td>
              </tr>
            </c:forEach>
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
            <label class="control-label">Bạn có chắc chắn vô hiệu hoá kho hàng này</label>
          </div>
        </div>
        <div style="display: flex; justify-content: center; padding: 10px;">
          <button style="margin: 5px;" class="btn btn-save" data-dismiss="modal" id="disableW" type="button" href="#">Xác nhận</button>
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
            <label class="control-label">Bạn có chắc chắn kích hoạt kho hàng này</label>
          </div>
        </div>
        <div style="display: flex; justify-content: center; padding: 10px;">
          <button style="margin: 5px;" class="btn btn-save" id="enableW" data-dismiss="modal" type="button">Xác nhận</button>
          <a style="margin: 5px;" class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
        </div>
      </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="result" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
            <label class="control-label" style="font-size: medium" id="resultLabel"></label>
          </div>
        </div>
        <div style="display: flex; justify-content: center; padding: 10px;">
          <a href="${pageContext.request.contextPath}/admin/warehouses" style="margin: 5px;" class="btn btn-cancel">Đóng</a>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/main.js"/>"></script>
<script src="<c:url value="/js/api-province.js"/>"></script>
<script src="<c:url value="/js/plugins/pace.min.js"/>"></script>
<script src="<c:url value="/resources/data.json"/>"></script>
<script>
  $(document).ready(function (){

  });
  function updateWarehouseStatus(e){
    var id = $(e.parentNode.parentNode.parentNode).find('td').eq(0).html();
    var element =$(e.parentNode).find('input');
    if($(element).is(":checked")){

      disableWarehouse(id);
    } else enableWarehouse(id);
  }
  function disableWarehouse(id){
    var dis = document.getElementById("disableW");
    dis.addEventListener('click',()=>{
      $.ajax({
        type:"POST",
        url:"/admin/warehouses/disable?id=" + id,
        contentType:"application/json",
        success: function (response){
          var  result = document.getElementById("resultLabel");
          result.innerHTML= response;
          $('#result').modal('show');
        }
      });
    });
  }
  function enableWarehouse(id){
    var en = document.getElementById("enableW");
    en.addEventListener('click', ()=>{
      $.ajax({
        type:"POST",
        url:"/admin/warehouses/enable?id=" + id,
        contentType:"application/json",
        success: function (response){
          var  result = document.getElementById("resultLabel");
          result.innerHTML= response;
          $('#result').modal('show');
        }
      });
    });
  }

</script>
<!-- Page specific javascripts-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
</body>

</html>