<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tạo đơn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link rel="stylesheet" href="css/demo.css">
    <style>
      .intro {
        margin-bottom: 50px;
        text-align: center;
        padding: 20px;
        font-size: 30px;
        font-weight: bold;
      }

      .intro span {
        float: left;
        font-size: 14px;
        font-weight: normal;
      }
    </style>
  </head>
  <body>
    <header class="intro">
      <div><span><a href="/home">Trở về cửa hàng</a></span>Electroco</div>
    </header>
  
    <main>
    <div class="container">
      <div class="row">
          <div class="col-md-4 order-md-2 mb-4">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
              <span class="text-muted">Giỏ hàng</span>
              <span class="badge badge-secondary badge-pill">3</span>
            </h4>
            <ul class="list-group mb-3">
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                <div>
                  <h6 class="my-0">Tụ điện 10V</h6>
                  <small class="text-muted">x10</small>
                </div>
                <span class="text-muted">20000đ</span>
              </li>
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                <div>
                  <h6 class="my-0">Tụ điện 10V</h6>
                  <small class="text-muted">x10</small>
                </div>
                <span class="text-muted">20000đ</span>
              </li>
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                <div>
                  <h6 class="my-0">Tụ điện 10V</h6>
                  <small class="text-muted">x10</small>
                </div>
                <span class="text-muted">20000đ</span>
              </li>
              <li class="list-group-item d-flex justify-content-between">
                <span>Tổng</span>
                <strong>60000đ</strong>
              </li>
            </ul>

          </div>
          <div class="col-md-8 order-md-1">
            <h4 class="mb-3">Thông tin nhận hàng</h4>
            <form class="needs-validation" action="/createOrder" method="post" name="createorderForm"
                        onsubmit="return validateCreateOrderForm();">
              <div class="row">
                <div class="mb-3">
                  <label for="name">Tên người nhận</label>
                  <input type="text" class="form-control" id="name" name="name">
                  <p id="name-error" class="invalid-feedback">
                  </p>
                </div>
              </div>

              <div class="mb-3">
                <label for="phone">Số điện thoại</label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="03xxxxxxxx">
                <p id="phone-error" class="invalid-feedback">
                </p>
              </div>

              <div class="mb-3">
                <label for="province">Tỉnh/Thành phố</label>
                <input type="text" class="form-control" id="province" name="province" placeholder="Hà Nội">
                <p id="province-error" class="invalid-feedback">
                </p>
              </div>

              <div class="mb-3">
                <label for="district">Quận/Huyện</label>
                <input type="text" class="form-control" id="district" name="district" placeholder="Thạch Thất">
                <p id="district-error" class="invalid-feedback">
                </p>
              </div>

              <div class="mb-3">
                <label for="ward">Xã/Phường</label>
                <input type="text" class="form-control" id="ward" name="ward" placeholder="Tân Xã">
                <p id="ward-error" class="invalid-feedback">
                </p>
              </div>

              <div class="mb-3">
                <label for="detailLocation">Địa chỉ cụ thể</label>
                <input type="text" class="form-control" id="detailLocation" name="detailLocation" placeholder="thôn X, ngõ Y">
                <p id="detailLocation-error" class="invalid-feedback">
                </p>
              </div>

               <div class="mb-3">
                 <label for="detailLocation">Phương thức thanh toán</label>
                 <input type="radio" id="html" name="fav_language" value="HTML">
                 <label for="html">Thanh toán toàn bộ</label><br>
                 <input type="radio" id="css" name="fav_language" value="CSS">
                 <label for="css">Thanh toán một phần</label><br>
                 <input type="radio" id="javascript" name="fav_language" value="JavaScript">
                 <label for="javascript">Thanh toán sau khi nhận hàng</label>
               </div>

              <button class="btn btn-primary btn-lg btn-block" type="submit">Xác nhận đặt hàng</button>
            </form>
          </div>
        </div>
      </div>
        <!-- End Demo HTML -->
        
    </main> 
<!-- Bootstrap 5 JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="/js/validate.js"></script>
  </body>
</html>