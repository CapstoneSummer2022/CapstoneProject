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
          <c:if test="${not empty error}">
            <p class="error" style="display: block; background-color: pink; text-align: center; padding: 10px; border-radius: 5px;">
              ${error}
            </p>
          </c:if>
          <c:if test="${not empty success}">
            <p class="success" style="display: block; background-color: green; text-align: center; padding: 10px; border-radius: 5px; color: white;">
              ${success}
            </p>
          </c:if>
          <div id="product_content">
            <div>
              <div id="product_area"> 
                <div id="product_images">
                  <img src="/img/${product.image}" alt="product_image">
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
                    <form action="/cart/add-to-cart" method="post" name="addToCart" onsubmit="return checkNumberOfProduct();">
                      <input type="hidden" id="productId" name="productId" value="${product.id}"/>
                      <label for="quantity">Số lượng: </label>
                      <input type="text" id="quantity" name="quantity" value="1" min="${product.unit}" max="${product.available}" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" size="2"/>
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
                const product = document.addToCart.quantity;
                let productValue = product.value;
                const maxNum = parseInt(product.max);
                const minNum = parseInt(product.min)
                const currentNumOfProduct = parseInt(productValue);
                const maxNumForCustomer = maxNum/minNum;

                if (maxNumForCustomer < currentNumOfProduct) {
                  alert("Bạn không thể đặt quá giới hạn sản phẩm");
                  product.value = maxNumForCustomer;
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