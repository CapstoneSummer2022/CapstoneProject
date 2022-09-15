<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${product.name}</title>
        <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Alef' rel='stylesheet'>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      </head>
      <body>    
        <!--Page Header-->
        <jsp:include page="header.jsp"/>
    
        <!--Page Content-->
        <main>
          <div id="product_content">
            <div>
              <div id="product_area"> 
                <div id="product_images">
                  <img src="/img/${product.getImage()}" alt="product_image">
                </div>
                <div id="product_info">
                  <div>
                    <p class="w3-xlarge">${product.name}</p>
                  </div>
                  <div>
                    <p class="w3-indigo w3-text-light-grey w3-padding w3-round w3-margin-bottom">
                              Giá: <span class="w3-xlarge">${product.price}đ</span></p>

                    <table class="w3-table w3-striped">
                        <c:forEach var="spec" items="${product.specificationValues}">
                          <tr>
                            <td>${spec.specificationName}</td>
                            <td>${spec.valueFrom}
                               <c:if test = "${spec.valueTo != null}">
                                 - ${spec.valueTo}
                               </c:if>
                            </td>
                          </tr>
                        </c:forEach>
                    </table>
                  </div>
                  <div>
                    <form action="/cart/add" method="post" name="addToCart" onsubmit="return checkNumberOfProduct();">
                      <input type="hidden" name="id" value="a11">
                      <label for="product">Số lượng: </label>
                      <input type="text" name="product" id="product" value="1" max="${product.getAvailable()}" onchange="changeNumber()"
                            oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" size="2">
                      <button type="submit">Thêm vào giỏ</button>
                    </form>
                  </div> 
                </div>
              </div>
              <div id="description">
                <h4>Mô tả sản phẩm</h4>
                ${product.getDescription()}
              </div>
            </div>
          </div>
        </main>
    
        <!--Page Footer-->
        <jsp:include page="footer.jsp"/>

        <!--Javascript-->
        <script>
            function checkNumberOfProduct () {
                const product = document.addToCart.product;
                let productValue = product.value;
                const maxNum = parseInt(product.max);
                const currentNumOfProduct = parseInt(productValue);

                if (maxNum < currentNumOfProduct) {
                  alert("Bạn không thể đặt quá giới hạn sản phẩm");
                  product.value = maxNum;
                  return false;
                }

                if ( currentNumOfProduct == 0 || productValue == "") {
                  alert("Bạn cần đặt tối thiểu 1 sản phẩm");
                  product.value = 1;
                  return false;
                }

                return true;
            }
        </script>
      </body>
</html>