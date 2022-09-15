<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-us">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Alef' rel='stylesheet'>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  </head>
  <body>    
    <!--Page Header-->
    <jsp:include page="header.jsp"/>

    <!--Page Content-->
    <main>
      <div id="home_content">

        <c:forEach var="map" items="${productsByCate}">
          <div class="single-cate">
          <h3>${map.key.getName()} <span><a href="${pageContext.request.contextPath}/product?cate=${map.key.getId()}&pageNo=1">Xem tất cả &nbsp; <i class="material-icons">arrow_forward</i></a></span></h3>
          <div class="product_list">
           <div>
             <c:forEach var="product" items="${map.value}">
               <div class="product-card">
                 <a href="${pageContext.request.contextPath}/product/${product.getId()}"><img src="/img/${product.getImage()}" alt="hình ảnh sản phẩm" class="prod_img"></a>
                 <p class="product-name"><a href="/product/${product.getId()}">${product.getName()}</a></p>
                 <p>${product.getPrice()}đ</p>
               </div>
             </c:forEach>
           </div>
          </div>
          </div>
        </c:forEach>

    </main>

    <!--Page Footer-->
    <jsp:include page="footer.jsp"/>

  </body>
</html>