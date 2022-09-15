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
                <tr>
                  <td><img src="/img/pros/1N4148 SMD 1206.webp" alt=""> </td>
                  <td class="prod_name"><a href="product.html">Tụ điện 3V </a></td>
                  <td>2500đ</td>
                  <td>
                    <div>
                      <input type="text" name="product" id="a21" value="1" max="1000" onchange="updateCart('a21')" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                    </div>
                  </td>
                  <td >20000đ</td>
                  <td><a href="#"><i class="material-icons">delete</i></a></td>
                </tr>
                <tr>
                  <td><img src="/img/pros/0 Ohm 0.25W Precision Resistor.jpg" alt=""> </td>
                  <td class="prod_name"><a href="product.html"> Tụ điện 5V</a></td>
                  <td>3000đ</td>
                  <td>
                    <div>
                      <input type="text" name="product" id="a11" value="1" max="1000" onchange="updateCart('a11')" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                    </div>
                  </td>
                  <td>60000đ</td>
                  <td><a href="#"><i class="material-icons">delete</i></a></td>
                </tr>
              </table>
            </div>
            <div id="leadto_order">
              <table table class="w3-table-all">
                <tr>
                  <td colspan="2" style="text-align: center; font-weight: bold;">Giỏ hàng</td>
                </tr>
                <tr>
                  <td>Tổng thanh toán: </td>
                  <td id="total">60000đ</td>
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
          function updateCart(productId) {
            const product = document.getElementById(productId);
            const maxNum = product.max;
            const currentNumOfProduct = parseInt(product.value);

            if ( currentNumOfProduct > parseInt(maxNum)) {
              product.value = maxNum;
            } 

            if ( currentNumOfProduct == 0 ) {
              removeProd (productId);
            }
              //Continue update cart here
            
          }

          function removeProd (productId) {
            if (confirm("Bạn muốn xóa sản phẩm này khỏi giỏ hàng?")){
                alert("Sản phẩm " + productId + "đã được xóa!");
                //Contitnue remove product here
            }
          }

          function goToCreateOrder () {
            window.location.href = "/createOrder";
          }

        </script>
      </body>
</html>