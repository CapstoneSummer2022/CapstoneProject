<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${category.name}</title>
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
          <div>
          <div id="productList_content">
            <h4>${category.name}</h4>
            <ul id="child-cate">
              <c:forEach var="childCate" items="${category.childCategories}">
                <li><a href="/product?cate=${childCate.id}&pageNo=1">${childCate.name}</a></li>
              </c:forEach>
            </ul>
            <div>
               <c:forEach var="product" items="${products}">
                 <div class="product-card">
                   <a href="${pageContext.request.contextPath}/product/${product.getId()}"><img src="/img/${product.getImage()}" alt="hình ảnh sản phẩm" class="prod_img"></a>
                   <p class="product-name"><a href="/product/${product.getId()}">${product.getName()}</a></p>
                   <p>${product.getPrice()}đ</p>
                 </div>
               </c:forEach>
            </div>
          </div>
            <div id="paging" style="margin-top: 50px;">
              <c:forEach var="i" begin="1" end="${pageNum}">
                <c:choose>
                  <c:when test = "${active == i}">
                    <a id="active" href="${pageContext.request.contextPath}/product?cate=${category.id}&pageNo=${i}">${i}</a>
                  </c:when>

                  <c:otherwise>
                    <a href="${pageContext.request.contextPath}/product?cate=${category.id}&pageNo=${i}">${i}</a>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </div>
          </div>
        </main>
    
        <!--Page Footer-->
        <jsp:include page="footer.jsp"/>

</html>