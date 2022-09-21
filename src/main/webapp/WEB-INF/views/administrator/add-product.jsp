<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Header</title>
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


  <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
  <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>
  <script>

    function readURL(input, thumbimage) {
      if (input.files && input.files[0]) { //Sử dụng  cho Firefox - chrome
        var reader = new FileReader();
        reader.onload = function (e) {
          $("#thumbimage").attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
      }
      else { // Sử dụng cho IE
        $("#thumbimage").attr('src', input.value);

      }
      $("#thumbimage").show();
      $('.filename').text($("#uploadfile").val());
      $('.Choicefile').css('background', '#14142B');
      $('.Choicefile').css('cursor', 'default');
      $(".removeimg").show();
      $(".Choicefile").unbind('click');

    }
    $(document).ready(function () {
      $(".Choicefile").bind('click', function () {
        $("#uploadfile").click();

      });
      $(".removeimg").click(function () {
        $("#thumbimage").attr('src', '').hide();
        $("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
        $(".removeimg").hide();
        $(".Choicefile").bind('click', function () {
          $("#uploadfile").click();
        });
        $('.Choicefile').css('background', '#14142B');
        $('.Choicefile').css('cursor', 'pointer');
        $(".filename").text("");
      });
    })
  </script>
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
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/products">Quản lý sản phẩm</a></li>
        <li class="breadcrumb-item active">Thêm sản phẩm</a></li>
      </ul>
      <div id="clock"></div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="tile">
          <h3 class="tile-title">Thêm sản phẩm mới</h3>
          <div class="tile-body">
            <div class="row element-button">
              <div class="col-sm-2">
                <a class="btn btn-add btn-sm" href="${pageContext.request.contextPath}/admin/products/specification/add" title="Thêm"><i
                        class="fas fa-plus"></i>
                  Thêm thông số kỹ thuật</a>
              </div>
            </div>
            <form>
              <div class="row">
                <div class="form-group col-md-3">
                  <label class="control-label required-field">Tên sản phẩm</label>
                  <input class="form-control" id="name" type="text" required>
                </div>

                <div class="form-group col-md-3">
                  <label class="control-label required-field">Giá bán</label>
                  <input class="form-control" id="price" type="number">
                </div>
                <div class="form-group col-md-3">
                  <label for="supplierOption" class="control-label required-field">Nhà cung cấp</label>
                  <select class="form-control" id="supplierOption" required>
                    <c:forEach var = "supplierDto" items="${listSuppliers}">
                      <option value="${supplierDto.id}">${supplierDto.name}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-3">
                  <label class="control-label required-field">Đơn vị bán</label>
                  <input class="form-control" id="unit" type="number">
                </div>
                <div class="form-group col-md-3">
                  <label for="exampleSelect1" class="control-label required-field">Danh mục</label>
                  <select class="form-control" id="categoryOption" required>
                    <c:forEach var = "categoryDto" items="${listCategories}">
                      <option value="${categoryDto.id}">${categoryDto.name}</option>
                    </c:forEach>
                  </select>
                </div>
                <div class="form-group col-md-3">
                  <button class="side-button btn btn-add" type="button" title="Thêm vào danh sách"
                          onclick="addToCategoryTable()">Thêm
                    vào
                    danh sách</button>
                  <button id="deleteButton" type="button" class="side-button btn btn-cancel" title="Xoá"
                          onclick="deleteFromCategoryTable()">Xoá</button>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-5">
                  <label for="exampleSelect1" class="control-label">Bảng danh mục</label>
                  <table id="categoryTable" class="table table-hover table-bordered js-copytextarea" cellpadding="0"
                         cellspacing="0">
                    <thead>
                    <tr>
                      <th width="200">Mã danh mục</th>
                      <th width="300">Tên danh mục</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-3">
                  <label for="exampleSelect1" class="control-label">Thông số kỹ thuật</label>
                  <select id="specOption" class="form-control">

                    <c:forEach items="${specificationDtos}" var="spec">
                      <option value="${spec.id}">${spec.name}</option>
                    </c:forEach>
                  </select>
                </div>
                <div class="form-group col-md-3">
                  <button class="side-button btn btn-add" type="button" title="Thêm vào danh sách"
                          onclick="addToSpecTable()">Thêm
                    vào
                    danh sách</button>
                  <button id="deleteButton" type="button" class="side-button btn btn-cancel" title="Xoá"
                          onclick="deleteFromTable()">Xoá</button>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-7">
                  <label for="exampleSelect1" class="control-label">Bảng thông số kỹ thuật</label>
                  <table id="specTable" class="table table-hover table-bordered js-copytextarea" cellpadding="0"
                         cellspacing="0">
                    <thead>
                    <tr>
                      <th width="200">Mã thông số kỹ thuật</th>
                      <th width="200">Thông số kỹ thuật</th>
                      <th width="400">Giá trị</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-12">
                  <label class="control-label required-field">Ảnh sản phẩm</label>
                  <div id="myfileupload">
                    <input type="file" id="uploadfile" name="ImageUpload" onchange="setThumbImage();" accept="image/*"
                           />
                  </div>
                  <div id="thumbbox">
                    <img height="450" width="400" alt="Thumb image" id="thumbimage" style="display: none" />
                  </div>
                </div>
                <div class="form-group col-md-12">
                  <label class="control-label">Mô tả sản phẩm</label>
                  <textarea class="form-control" name="mota" id="mota"></textarea>
                </div>
              </div>
              <div class="row">
                <div class="confirm-button">
                  <div class="button">
                    <button type="button" id="createProduct" class="btn btn-save" onclick="addProduct()" >Lưu lại</button>
                    <button type="button" class="btn btn-cancel" onclick="location.href='product-management.html'">Hủy
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
  <!--
  MODAL SUCCESSFUL
-->
  <div class="modal fade" id="successful" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <p class="modal-notify-successful">Thêm sản phẩm thành công</p>
            </div>
          </div>
          <div style="display: flex; justify-content: center; padding: 10px;">
            <a  href="${pageContext.request.contextPath}/admin/products" style="margin: 5px;" class="btn btn-save" >Đóng</a>
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
MODAL CATEGORY EMPTY
-->
  <div class="modal fade" id="categoryEmpty" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <p class="modal-notify-unsuccessful" >Chưa có danh mục nào được chọn</p>
            </div>
          </div>
          <div style="display: flex; justify-content: center; padding: 10px;">
            <button style="margin: 5px;" class="btn btn-save" data-dismiss="modal">Đóng</button>
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
  <div class="modal fade" id="unsuccessful" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
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
              <p class="modal-notify-unsuccessful" id="reason"></p>
            </div>
          </div>
          <div style="display: flex; justify-content: center; padding: 10px;">
            <button style="margin: 5px;" class="btn btn-save" data-dismiss="modal">Đóng</button>
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
  <script src="<c:url value="/js/api-province.js"/>"></script>
  <script src="<c:url value="/js/plugins/pace.min.js"/>"></script>
  <script src="<c:url value="/resources/data.json"/>"></script>
  <script>
    const inpFile = document.getElementById("inpFile");
    const loadFile = document.getElementById("loadFile");
    const previewContainer = document.getElementById("imagePreview");
    const previewImage = previewContainer.querySelector(".image-preview__image");
    const previewDefaultText = previewContainer.querySelector(".image-preview__default-text");
    inpFile.addEventListener("change", function () {
      const file = this.files[0];
      if (file) {
        const reader = new FileReader();
        previewDefaultText.style.display = "none";
        previewImage.style.display = "block";
        reader.addEventListener("load", function () {
          previewImage.setAttribute("src", this.result);
        });
        reader.readAsDataURL(file);
      }
    });

  </script>

</body>

</html>