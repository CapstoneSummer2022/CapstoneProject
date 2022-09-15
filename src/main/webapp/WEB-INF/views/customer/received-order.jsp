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
          <div class="account">
            <div class="account_side">
              <a href="/profile"><i class="material-icons"> arrow_right</i>Hồ sơ của tôi</a>
              <a href="/order/all" style="font-weight: bold; background-color: rgba(0,0,0,0.1);"><i class="material-icons"> arrow_right</i>Đơn hàng của tôi</a>
              <a href="/change-password"><i class="material-icons"> arrow_right</i>Đổi mật khẩu</a>
            </div>
            <div class="account_content">
              <div id="order_tabs">
                <a href="/order/all" style="color: white; background-color: #01447e;">Tất cả</a>
                <a href="/order/waiting">Chờ xác nhận</a>
                <a href="/order/shipping">Đang giao hàng</a>
                <a href="/order/received">Đã nhận hàng</a>
                <a href="/order/canceled">Đã hủy</a>
              </div>
              <div id="order_list">
                <div class="an-order">
                  <table>
                    <tr>
                      <td>Mã đơn: 1</td>
                      <td>CHỜ XÁC NHẬN</td>
                    </tr>
                    <tr>
                      <td><img src="/img/pros/0 Ohm 0.25W Precision Resistor.jpg" alt="hinh ảnh sản phẩm"> <span> Tụ điện 10V</span> <br> x10</td>
                      <td>20000</td>
                    </tr>
                    <tr>
                      <td><img src="/img/pros/0 Ohm 0.25W Precision Resistor.jpg" alt="hinh ảnh sản phẩm"><span> Tụ điện 10V</span>  <br> x10</td>
                      <td>20000</td>
                    </tr>
                    <tr>                      
                      <td><img src="/img/pros/0 Ohm 0.25W Precision Resistor.jpg" alt="hinh ảnh sản phẩm"><span> Tụ điện 10V</span>  <br> x10</td>
                      <td>20000</td>
                    </tr>
                    <tr>
                      <td></td>
                      <td><span style="font-weight: bold;">Tổng thanh toán:</span>  60000</td>
                    </tr>
                    <tr>
                      <td colspan="2"><button>Đánh giá</button></td>
                    </tr>
                  </table>
                </div>
                <div class="an-order">
                  <table>
                    <tr>
                      <td>Mã đơn: 1</td>
                      <td>CHỜ XÁC NHẬN</td>
                    </tr>
                    <tr>
                      <td><img src="/img/pros/0 Ohm 0.25W Precision Resistor.jpg" alt="hinh ảnh sản phẩm"> <span> Tụ điện 10V</span> <br> x10</td>
                      <td>20000</td>
                    </tr>
                    <tr>
                      <td><img src="/img/pros/0 Ohm 0.25W Precision Resistor.jpg" alt="hinh ảnh sản phẩm"><span> Tụ điện 10V</span>  <br> x10</td>
                      <td>20000</td>
                    </tr>
                    <tr>                      
                      <td><img src="/img/pros/0 Ohm 0.25W Precision Resistor.jpg" alt="hinh ảnh sản phẩm"><span> Tụ điện 10V</span>  <br> x10</td>
                      <td>20000</td>
                    </tr>
                    <tr>
                      <td></td>
                      <td><span style="font-weight: bold;">Tổng thanh toán:</span>  60000</td>
                    </tr>
                    <tr>
                      <td colspan="2"><button>Đánh giá</button></td>
                    </tr>
                  </table>
                </div>
                <div class="an-order">
                  <table>
                    <tr>
                      <td>Mã đơn: 1</td>
                      <td>CHỜ XÁC NHẬN</td>
                    </tr>
                    <tr>
                      <td><img src="/img/pros/0 Ohm 0.25W Precision Resistor.jpg" alt="hinh ảnh sản phẩm"> <span> Tụ điện 10V</span> <br> x10</td>
                      <td>20000</td>
                    </tr>
                    <tr>
                      <td><img src="/img/pros/0 Ohm 0.25W Precision Resistor.jpg" alt="hinh ảnh sản phẩm"><span> Tụ điện 10V</span>  <br> x10</td>
                      <td>20000</td>
                    </tr>
                    <tr>                      
                      <td><img src="/img/pros/0 Ohm 0.25W Precision Resistor.jpg" alt="hinh ảnh sản phẩm"><span> Tụ điện 10V</span>  <br> x10</td>
                      <td>20000</td>
                    </tr>
                    <tr>
                      <td></td>
                      <td><span style="font-weight: bold;">Tổng thanh toán:</span>  60000</td>
                    </tr>
                    <tr>
                      <td colspan="2"><button>Đánh giá</button></td>
                    </tr>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </main>
    
        <!--Page Footer-->
        <jsp:include page="footer.jsp"/>

      </body>
</html>