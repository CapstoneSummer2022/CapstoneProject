<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <header>
          <c:set var="username" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.name}"/>
          <div id="first_row">
            <a href="/home"><h1 id="store_name">Electroco</h1></a>
            <div id="searchByCate">
              <form action="" id="store_search">
                <input type="text" id="prod_search" name="prod_search" placeholder="Tìm kiếm..."/>
                <button type="submit"><i class="material-icons">search</i></button>
              </form>
            </div>
            <div id="store_icons">
              <div id="cart_icon">
                <a href="${pageContext.request.contextPath}/cart">
                <i class="material-icons">shopping_cart</i><span id="no_of_prods"></span> </a>
                <a href="${pageContext.request.contextPath}/cart">Giỏ hàng</a>
              </div>
                 <c:choose>
                     <c:when test = "${username == null}">
                        <div id="avatar_icon">
                            <a href="${pageContext.request.contextPath}/signin"><i class="material-icons">person</i></a><a href="${pageContext.request.contextPath}/signin">Đăng nhập</a> / <a href="${pageContext.request.contextPath}/signup">Đăng ký</a>
                        </div>
                     </c:when>

                     <c:otherwise>
                        <div class="dropdown">
                            <button class="dropbtn"><i class="material-icons">person</i><c:out value = "${username}"/></button>
                            <div class="dropdown-content">
                              <a href="${pageContext.request.contextPath}/profile" style="color: black;">Tài khoản của tôi</a>
                              <a href="${pageContext.request.contextPath}/order" style="color: black;">Đơn hàng của tôi</a>
                              <a href="${pageContext.request.contextPath}/sign-out" style="color: black;">Đăng xuất</a>
                            </div>
                         </div>
                     </c:otherwise>
                 </c:choose>
            </div>
          </div>
          <div id="second_row">
            <ul id="nav">
              <li><a href="#">Danh mục sản phẩm <i class="material-icons">arrow_drop_down</i></a>
                <ul>
                  <c:forEach var="cate" items="${categories}">
                    <li><a href="${pageContext.request.contextPath}/product?cate=${cate.id}&pageNo=1">${cate.name}</a>
                        <ul>
                            <c:forEach var="childCate" items="${cate.childCategories}">
                                <li><a href="${pageContext.request.contextPath}/product?cate=${childCate.id}&pageNo=1">${childCate.name}</a>
                            </c:forEach>
                        </ul>
                    </li>
                  </c:forEach>
                </ul>
              </li>
              <li><a href="${pageContext.request.contextPath}/contact">Thông tin liên hệ</a></li>
            </ul>
          </div>
        </header>

<script>
    getNumbersOfProduct();

    function getNumbersOfProduct () {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "http://localhost:8083/cart/get-numbers-product", true);

        xhr.onload = function() {
           const data = JSON.parse(this.responseText);
           document.getElementById("no_of_prods").innerHTML = this.responseText;
       }

       xhr.send();

    }
</script>