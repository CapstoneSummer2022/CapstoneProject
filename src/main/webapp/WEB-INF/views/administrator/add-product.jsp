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
        <li class="breadcrumb-item"><a href="product-management.html">Quản lý sản phẩm</a></li>
        <li class="breadcrumb-item active">Thêm sản phẩm</a></li>
      </ul>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="tile">
          <h3 class="tile-title">Thêm sản phẩm mới</h3>
          <div class="tile-body">
            <form>
              <div class="row">
                <div class="form-group col-md-3">
                  <label class="control-label">Mã sản phẩm</label>
                  <input class="form-control" type="text">
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label">Tên sản phẩm</label>
                  <input class="form-control" type="text">
                </div>
                <div class="form-group  col-md-3">
                  <label class="control-label">Số lượng</label>
                  <input class="form-control" type="number">
                </div>
                <div class="form-group col-md-3">
                  <label for="exampleSelect1" class="control-label">Danh mục</label>
                  <select class="form-control" id="exampleSelect1">
                    <option selected hidden disabled>-- Chọn danh mục --</option>
                    <option>Cầu chì</option>
                    <option>Biến trở</option>
                    <option>Cảm biến nhiệt độ</option>
                    <option>IC cảm ứng</option>
                    <option>Cảm biến màu</option>
                    <option>Cảm biến chuyển động</option>
                    <option>Cảm biến nước</option>
                    <option>Cảm biến âm thanh</option>
                    <option>IC cảm ứng</option>
                  </select>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-12">
                  <label class="control-label">Ảnh sản phẩm</label>
                  <div id="myfileupload">
                    <input type="file" id="uploadfile" name="ImageUpload" onchange="readURL(this);" />
                  </div>
                  <div id="thumbbox">
                    <img height="450" width="400" alt="Thumb image" id="thumbimage" style="display: none" />
                    <a class="removeimg" href="javascript:"></a>
                  </div>
                  <div id="boxchoice">
                    <a href="javascript:" class="Choicefile"><i class="fas fa-cloud-upload-alt"></i> Chọn ảnh</a>
                    <p style="clear:both"></p>
                  </div>
                </div>
                <div class="form-group col-md-12">
                  <label class="control-label">Mô tả sản phẩm</label>
                  <textarea class="form-control" name="mota" id="mota"></textarea>
                  <script>CKEDITOR.replace('mota');</script>
                </div>
              </div>
              <div class="row">
                <div class="confirm-button">
                  <div class="button">
                    <button type="submit" class="btn btn-save" type="button">Lưu lại</button>
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
  <script src="<c:url value="/js/popper.min.js"/>></script>
  <script src="<c:url value="/js/jquery-3.2.1.min.js"/>></script>
  <script src="<c:url value="/js/main.js"/>></script>
  <script src="<c:url value="/js/plugins/bootstrap.min.js"/>></script>
  <script src="<c:url value="/js/plugins/pace.min.js"/>></script>
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