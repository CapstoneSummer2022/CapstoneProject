<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đơn hàng của tôi</title>
        <link rel="stylesheet" href="/css/style.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Alef' rel='stylesheet'>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      </head>
      <body>    
        <!--Page Header-->
        <jsp:include page="header.jsp" />

        <!--Page Content-->
        <main>
          <c:if test="${not empty orderId}">
            <div id="myModal" class="modal">

              <!-- Modal content -->
              <div class="modal-content">
                <span class="close">&times;</span>
                <p>Đơn hàng với mã ${orderId} vừa được tạo</p>
              </div>

            </div>
          </c:if>
          <div class="account">
            <div class="account_side">
              <a href="${pageContext.request.contextPath}/profile"><i class="material-icons"> arrow_right</i>Hồ sơ của tôi</a>
              <a href="${pageContext.request.contextPath}/order" style="font-weight: bold; background-color: rgba(0,0,0,0.1);"><i class="material-icons"> arrow_right</i>Đơn hàng của tôi</a>
              <a href="${pageContext.request.contextPath}/change-password"><i class="material-icons"> arrow_right</i>Đổi mật khẩu</a>
            </div>
            <div class="account_content">
              <div id="order_tabs">
                <a href="${pageContext.request.contextPath}/order" style="color: white; background-color: #01447e;">Tất cả</a>
                <a href="${pageContext.request.contextPath}/order/waiting">Chờ xác nhận</a>
                <a href="${pageContext.request.contextPath}/order/shipping">Đang giao hàng</a>
                <a href="${pageContext.request.contextPath}/order/received">Đã nhận hàng</a>
                <a href="${pageContext.request.contextPath}/order/cancelled">Đã hủy</a>
              </div>
              <div id="order_list">
                <c:forEach var="order" items="${orders}">
                    <div class="an-order">
                      <table>
                        <tr>
                          <td>Mã đơn: ${order.id}</td>
                          <td>${order.status}</td>
                        </tr>
                        <c:forEach var="item" items="${order.orderItems}">
                            <tr>
                              <td><img src="/img/${item.productImage}" alt="hinh ảnh sản phẩm"> <span> ${item.productName}</span> <br> x${item.quantity}</td>
                              <td>${item.subTotal}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                          <td></td>
                          <td style="font-size: 20px;"><span style="font-weight: bold;">Tổng thanh toán:</span>  ${order.totalPayment}</td>
                        </tr>
                        <c:if test="${item.status != 'Đã Hủy' && item.status != 'Hoàn Thành'}">
                            <tr>
                              <td colspan="2"><button>Hủy đơn hàng</button></td>
                            </tr>
                        </c:if>

                      </table>
                    </div>
                </c:forEach>
              </div>
            </div>
          </div>
        </main>
    
        <!--Page Footer-->
        <jsp:include page="footer.jsp" />

        <script>
        var modal = document.getElementById("myModal");

        var btn = document.getElementById("myBtn");

        var span = document.getElementsByClassName("close")[0];

        span.onclick = function() {
          modal.style.display = "none";
        }

        window.onclick = function(event) {
          if (event.target == modal) {
            modal.style.display = "none";
          }
        }
        </script>

      </body>
</html>