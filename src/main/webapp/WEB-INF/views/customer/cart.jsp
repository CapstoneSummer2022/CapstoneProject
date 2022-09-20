<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Giỏ hàng</title>
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
          <div id="cart_content">
            <div id="product_table">
              <table>
                <tr>
                  <th></th>
                  <th class="prod_name">Tên sản phẩm</th>
                  <th>Giá</th>
                  <th>Số lượng</th>
                  <th>Tổng</th>
                  <th></th>
                </tr>
                <c:set var = "total" value = "${0}"/>
                <c:forEach var = "item" items = "${cartItems}">
                    <tr>
                      <td><img src="/img/${item.productDTO.image}" alt=""> </td>
                      <td class="prod_name"><a href="${pageContext.request.contextPath}/product/${item.productDTO.id}">${item.productDTO.name} </a></td>
                      <td>${item.productDTO.price}đ</td>
                      <td>
                        <div>
                          <input type="text" name="quantity" id="${item.productDTO.id}" value="${item.quantity}" min="${item.productDTO.unit}" max="${item.productDTO.available}" onchange="updateCartNumber('${item.productDTO.id}')" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');">
                        </div>
                      </td>
                      <td >${item.subTotal}đ</td>
                      <td><a href="${pageContext.request.contextPath}/cart/remove?prodId=${item.productDTO.id}" onclick="return confirm('Bạn muốn xóa sản phẩm này khỏi giỏ hàng?')"><i class="material-icons">delete</i></a></td>
                    </tr>
                    <c:set var = "total" value = "${total + item.subTotal}"/>
                </c:forEach>
                </table>
                </div>
            <div id="leadto_order">
              <table table class="w3-table-all">
                <tr>
                  <td colspan="2" style="text-align: center; font-weight: bold;">Giỏ hàng</td>
                </tr>
                <tr>
                  <td>Tổng thanh toán: </td>
                  <td id="total">${total}đ</td>
                </tr>
                <tr>
                  <td colspan="2" style="padding: 0;"><button onclick="goToCreateOrder()" id="createorder_btn">Đặt hàng</button></td>
                </tr>
              </table>
            </div>
          </div>
        </main>
    
        <!--Page Footer-->
        <jsp:include page="footer.jsp"/>

        <!--Javascript-->
        <script>          
          function updateCartNumber(productId) {
            const product = document.getElementById(productId);
            const productValue = product.value;
            const maxNum = parseInt(product.max);
            const minNum = parseInt(product.min)
            const currentNumOfProduct = parseInt(productValue);
            const maxNumForCustomer = maxNum/minNum;

            if ( currentNumOfProduct > maxNumForCustomer ) {
              alert("Bạn không thể đặt quá giới hạn của sản phẩm");
              product.value = currentNumOfProduct;
            } 

            if ( currentNumOfProduct == 0 ) {
              product.value = 1;
            }
          }

          function goToCreateOrder () {
            window.location.href = "http://localhost:8083/create-order";
          }

        </script>
      </body>
</html>