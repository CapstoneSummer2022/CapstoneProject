<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Thêm danh mục | Quản trị</title>
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

<body class="app sidebar-mini rtl">
    <!-- Navbar-->
    <jsp:include page="header.jsp"/>
    <!-- Sidebar menu-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <jsp:include page="home-menu.jsp"/>
    <main class="app-content">
        <div class="app-title">
            <ul class="app-breadcrumb breadcrumb">
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/categories">Quản lý danh mục</a></li>
                <li class="breadcrumb-item active">Tuỳ chỉnh danh mục</li>
            </ul>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <h3 class="tile-title">Tuỳ chỉnh danh mục</h3>
                    <div class="tile-body">
                        <%--@elvariable id="categoryDto" type="com.example.electriccomponentsshop.dto.CategoryDTO"--%>
                        <form:form modelAttribute="categoryDto" method="POST" action="${pageContext.request.contextPath}/admin/categories/edit/${categoryDto.id}">
                            <div class="row">
                                <div class="form-group col-md-3">
                                    <label class="control-label required-field">Tên danh mục</label>
                                    <form:input class="form-control" path="name" type="text" value="${categoryDto.name}" />
                                </div>
                                <div class="form-group col-md-3">
                                    <label class="control-label required-field">Danh mục lớn trực thuộc</label>
                                    <div class="options">
                                        <input type="checkbox" id="#hasBigCategory"
                                               onclick="showBigCategoryInput(this)" />
                                    </div>
                                </div>
                                <div id="bigCategory" class="form-group col-md-3" style="display: none;"
                                     onload="showBigCategoryInput(this)">
                                    <label class="control-label">Chọn danh mục lớn</label>
                                    <form:select class="form-control required-field" id="exampleSelect2" path="parentId">
                                        <option selected disabled hidden>-- Chọn danh mục lớn --</option>
                                        <c:forEach var="category" items="${categories}">
                                            <option value="${category.id}">${category.name}</option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="confirm-button">
                                    <div class="button">
                                        <button type="submit" class="btn btn-save" type="button">Lưu lại</button>
                                        <button type="button" class="btn btn-cancel"
                                                onclick="location.href='category-management.html'">Hủy bỏ</button>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
  <script src="<c:url value="/js/popper.min.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
  <script src="<c:url value="/js/main.js"/>"></script>
    <script src="<c:url value="/js/plugins/pace.min.js"/>"></script>


</body>

</html>