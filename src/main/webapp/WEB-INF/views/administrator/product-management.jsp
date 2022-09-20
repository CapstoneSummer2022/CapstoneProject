<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Quản lý sản phẩm | Quản trị</title>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/2.xx.x/css/uikit.min.css" />
  <!-- Main CSS-->
  <link href="<c:url value="/css/main.css"/>" rel="stylesheet" type="text/css">
  <link href="<c:url value="/css/uikit.css"/>" rel="stylesheet" type="text/css">
  <link href="<c:url value="/css/uikit.min.css"/>" rel="stylesheet" type="text/css">
  <link href="<c:url value="/css/uikit-rtl.css"/>" rel="stylesheet" type="text/css">
  <link href="<c:url value="/css/uikit-rtl.min.css"/>" rel="stylesheet" type="text/css">
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
                <a class="btn btn-add btn-sm" href="${pageContext.request.contextPath}/admin/products/add" title="Thêm"><i class="fas fa-plus"></i>
                  Thêm sản phẩm</a>
              </div>
              <div class="col-sm-2">
                <a class="btn btn-delete btn-sm nhap-tu-file" type="button" title="Nhập" data-toggle="modal"
                  data-target="#importFile"><i class="fas fa-file-upload"></i> Nhập dữ liệu từ
                  file</a>
              </div>
            </div>
            <div class="search-row">
              <form action="${pageContext.request.contextPath}/admin/products/search" method="GET">
                <div class="search-container">
                  <input class="form-control" type="text" placeholder="Tìm kiếm" name="text" id="searchText" value="${text}">
                  <input type="hidden" value="0" type="text" name="index">
                  <button type="submit" id="search" ><i class="fa fa-search"></i></button>
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
                  <th>Giá bán</th>
                  <th>Danh mục</th>
                  <th>Trạng thái</th>
                  <th>Tuỳ chọn</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="productDto" items="${productDtos}">
                <tr>
                  <td>${productDto.id}</td>
                  <td>${productDto.name}</td>
                  <td><img src="/img/${productDto.image}" alt="" width="100px;"></td>
                  <td>${productDto.available}</td>
                  <td>${productDto.price}</td>
                  <td>
                    <c:forEach var="categoryDto" items="${productDto.categories}">
                      ${categoryDto.name} ,
                    </c:forEach>
                  </td>
                  <td>
                    <div class="switch">
                      <input class="status-checkbox" onclick="return false" type="checkbox"  name="check" <c:if test="${productDto.status == 1}"> checked</c:if>/>

                      <span class="slider round"></span>
                      <div style="position:absolute; left:0; right:0; top:0; bottom:0;" onclick="updateProductStatus(this)"data-toggle="modal"
                              <c:if test="${productDto.status == 1}"> data-target="#disableStatus"</c:if> <c:if test="${productDto.status == 0}"> data-target="#enableStatus"</c:if> ></div>
                    </div>
                  </td>
                  <td>
                    <a href="${pageContext.request.contextPath}/admin/products/view/${productDto.id}" class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i
                            class="fas fa-edit"></i></a>
                  </td>
                </tr>
              </c:forEach>

              </tbody>
            </table>
            <div class="pagination-row">
              <div class="pagination-container">
              <p>${pageNo} + thuong</p>
                <div class="dataTables_paginate paging_simple_numbers" id="sampleTable_paginate">
                 <ul class="pagination">
                   <c:forEach var="i" step="1" begin="1" end="${total-1}">
                     <c:choose>
                        <c:when test="${pageNo == i}">
                            <li class="active paginate_button page-item " id="sampleTable_previous"><a href="${pageContext.request.contextPath}/admin/products<c:choose><c:when test="${text!=null}">/search?text=${text}&index=${i}</c:when><c:otherwise>?index=${i}</c:otherwise></c:choose>"
                                                                                                                                  aria-controls="sampleTable" data-dt-idx="0" tabindex="0" class="page-link">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="paginate_button page-item " id="sampleTable_previous"><a href="${pageContext.request.contextPath}/admin/products<c:choose><c:when test="${text!=null}">/search?text=${text}&index=${i}</c:when><c:otherwise>?index=${i}</c:otherwise></c:choose>"
                                                                                                                                  aria-controls="sampleTable" data-dt-idx="0" tabindex="0" class="page-link">${i}</a></li>
                        </c:otherwise>
                     </c:choose>
                   </c:forEach>
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
            <button style="margin: 5px;" class="btn btn-save" type="button" data-dismiss="modal" id="disableP">Xác nhận</button>
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
            <button style="margin: 5px;" class="btn btn-save" type="button" data-dismiss="modal" id="enableP">Xác nhận</button>
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
            <a href="${pageContext.request.contextPath}/admin/products" style="margin: 5px;" class="btn btn-cancel">Đóng</a>
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
  <script src="src/jquery.table2excel.js"></script>
  <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
  <script src="<c:url value="/js/popper.min.js"/>"></script>
  <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
  <script src="<c:url value="/js/main.js"/>"></script>
  <script src="<c:url value="/js/plugins/pace.min.js"/>"></script>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/2.xx.x/js/uikit.min.js"></script>
  <!-- The javascript plugin to display page loading on top-->
  <!-- Page specific javascripts-->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
  <script>
    $(document).ready(function (){

    });
    function updateProductStatus(e){
      var id = $(e.parentNode.parentNode.parentNode).find('td').eq(0).html();
      var element =$(e.parentNode).find('input');
      if($(element).is(":checked")){

        disableProduct(id);
      } else enableProduct(id);
    }
    function disableProduct(id){
      var dis = document.getElementById("disableP");
      alert("hoang");
      dis.addEventListener('click',()=>{
        $.ajax({
          type:"POST",
          url:"/admin/products/disable?id=" + id,
          contentType:"application/json",
          success: function (response){
            var  result = document.getElementById("resultLabel");
            result.innerHTML= response;
            $('#result').modal('show');
          }
        });
      });
    }
    function enableProduct(id){
      var en = document.getElementById("enableP");
      en.addEventListener('click', ()=>{
        $.ajax({
          type:"POST",
          url:"/admin/products/enable?id=" + id,
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
</body>

</html>