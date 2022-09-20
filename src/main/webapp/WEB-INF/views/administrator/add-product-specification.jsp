<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Thêm thông số kỹ thuật | Quản trị Admin</title>
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

<body class="app sidebar-mini rtl">
  <!-- Navbar-->
 <jsp:include page="header.jsp"/>
  <!-- Sidebar menu-->
  <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
  <jsp:include page="home-menu.jsp"/>
  <main class="app-content">
    <div class="app-title">
      <ul class="app-breadcrumb breadcrumb">
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/products">Quản lý sản phẩm</a></li>
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/products/add"> Thêm sản phẩm</a></li>
        <li class="breadcrumb-item active">Thêm thông số kỹ thuật</li>
      </ul>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="tile">
          <h3 class="tile-title">Thêm thông số kỹ thuật</h3>
          <div class="tile-body">
            <%--@elvariable id="newSpecification" type="com.example.electriccomponentsshop.dto.SpecificationDto"--%>
            <form:form modelAttribute="newSpecification" action="${pageContext.request.contextPath}/admin/products/specification/add" method="POST"  >
              <div class="row">
                <div class="form-group col-md-4">
                  <label class="control-label required-field">Tên thông số kỹ thuật</label>
                  <form:input class="form-control" path="name" type="text"/>
                </div>
                <div class="form-group col-md-7">
                  <label for="exampleSelect1" class="control-label">Bảng thông số kỹ thuật</label>
                  <table id="specTable" class="table table-hover table-bordered js-copytextarea" cellpadding="0"
                    cellspacing="0" id="sampleTable">
                    <thead>
                      <tr>
                        <th width="150">Mã thông số kỹ thuật</th>
                        <th width="300">Thông số kỹ thuật</th>
                        <th width="100">Tuỳ chọn</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="spec" items="${listSpec}">
                      <tr>
                        <td>${spec.id}</td>
                        <td>${spec.name}</td>
                        <td><a href="${pageContext.request.contextPath}/admin/products/specification/update?id=${spec.id}" class="btn btn-primary btn-sm edit"
                               type="button" title="Sửa" id="show-emp"><i class="fas fa-edit"></i></a></td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="row">
                <div class="confirm-button">
                  <div class="button">
                    <button type="submit" class="btn btn-save" >Lưu lại</button>
                    <button type="button" class="btn btn-cancel" onclick="location.href='setting-product.html'">Hủy
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
  <!--
  MODAL SUCCESSFUL
-->
  <div class="modal fade <c:if test="${success==1}">show</c:if>" id="successful" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <p class="modal-notify-successful">Thêm thông số kỹ thuật thành công</p>
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
  <!--
MODAL UNSUCCESSFUL 
-->
  <div class="modal fade <c:if test="${success==0}">show</c:if>" id="unsuccessful" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <p class="modal-notify-unsuccessful">Thêm thông số kỹ thuật không thành công</p>
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
  <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
  <script src="<c:url value="/js/popper.min.js"/>"></script>
  <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
  <script src="<c:url value="/js/main.js"/>"></script>
  <script src="<c:url value="/js/plugins/pace.min.js"/>"></script>
<script>
  $(document).ready(function (){
   if(document.getElementById("successful").classList.contains('show')){
     $('#successful').modal('show');
   }
   else if(document.getElementById("unsuccessful").classList.contains('show')){
     $('#unsuccessful').modal('show');
   }
  });
</script>
</body>

</html>