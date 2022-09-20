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
      <div><span><a href="${pageContext.request.contextPath}/home">Trở về cửa hàng</a></span>Electroco</div>
    </header>
  
    <main>
    <div class="container">
      <div class="row">
          <div class="col-md-4 order-md-2 mb-4">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
              <span class="text-muted">Giỏ hàng</span>
            </h4>
            <c:set var="total" value="${0}"/>
            <ul class="list-group mb-3">
                <c:forEach var="item" items="${cartItems}">
                  <li class="list-group-item d-flex justify-content-between lh-condensed">
                    <div>
                      <h6 class="my-0">${item.productDTO.name}</h6>
                      <small class="text-muted">x${item.quantity}</small>
                    </div>
                    <span class="text-muted">${item.subTotal}đ</span>
                  </li>
                  <c:set var="total" value="${total + item.subTotal}"/>
                </c:forEach>
                <li class="list-group-item d-flex justify-content-between">
                    <span>Tổng</span>
                    <strong>${total}đ</strong>
                </li>
            </ul>

          </div>
          <div class="col-md-8 order-md-1">
            <h4 class="mb-3">Thông tin nhận hàng</h4>
            <form class="needs-validation" action="${pageContext.request.contextPath}/create-order" method="post" name="createorderForm"
                        onsubmit="return validateCreateOrderForm();">
              <div class="row">
                <div class="mb-3">
                  <label for="name">Tên người nhận</label>
                  <input type="text" class="form-control" id="name" name="name" value="${account.name}">
                  <p id="name-error" class="invalid-feedback">
                  </p>
                </div>
              </div>

              <div class="mb-3">
                <label for="phone">Số điện thoại</label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="03xxxxxxxx" value="${account.phone}" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');">
                <p id="phone-error" class="invalid-feedback">
                </p>
              </div>

              <div class="mb-3">
                <label for="province">Tỉnh/Thành phố</label>
                <select class="form-select" aria-label="Default select example" id="province" name="province"  onchange="getDistricts(); getWards();">
                  <c:forEach var="pro" items="${provinces}">
                    <c:choose>
                        <c:when test="${account.provinceName eq pro.name}">
                            <option value="${pro.PCode}" selected>${pro.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${pro.PCode}">${pro.name}</option>
                        </c:otherwise>
                    </c:choose>
                  </c:forEach>
                </select>
                <p id="province-error" class="invalid-feedback">
                </p>
              </div>

              <div class="mb-3">
                <label for="district">Quận/Huyện</label>
                <select class="form-select" aria-label="Default select example" id="district" name="district" onchange="getWards();">
                    <c:if test="${not empty districts}">
                      <c:forEach var="dis" items="${districts}">
                        <c:choose>
                            <c:when test="${account.districtName eq dis.name}">
                                <option value="${dis.DCode}" selected>${dis.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${dis.DCode}">${dis.name}</option>
                            </c:otherwise>
                        </c:choose>
                      </c:forEach>
                    </c:if>
                </select>
                <p id="district-error" class="invalid-feedback">
                </p>
              </div>

              <div class="mb-3">
                <label for="ward">Xã/Phường</label>
                <select class="form-select" aria-label="Default select example" id="ward" name="ward">
                    <c:if test="${not empty wards}">
                      <c:forEach var="ward" items="${wards}">
                        <c:choose>
                            <c:when test="${account.wardName eq ward.name}">
                                <option value="${ward.WCode}">${ward.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${ward.WCode}">${ward.name}</option>
                            </c:otherwise>
                        </c:choose>
                      </c:forEach>
                    </c:if>
                </select>
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
                 <label>Phương thức thanh toán</label><br/>
                 <input type="radio" id="all" name="payment_method" value="all" checked>
                 <label for="all">Thanh toán toàn bộ</label><br/>
                 <input type="radio" id="40" name="payment_method" value="40">
                 <label for="40">Thanh toán 40% đơn hàng</label><br/>
               </div>

              <button class="btn btn-primary btn-lg btn-block" type="submit">Xác nhận đặt hàng</button>
            </form>
          </div>
        </div>
      </div>
        
    </main>

    <!-- Bootstrap 5 JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="/js/validate.js">
    </script>
    <script>
    function getDistricts () {
        const province = document.getElementById("province").value;
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "http://localhost:8083/address/get-district?pCode=" + province, true);
        xhr.getResponseHeader("Content-type", "application/json");

        xhr.onload = function() {
            const data = JSON.parse(this.responseText);
            let html = '';

            for ( let c in data ) {
               html += '<option value="'+ data[c].dcode +'">'+ data[c].name +'</option>';
            }

           document.querySelector("#district").innerHTML = html;
        }

        xhr.send();

        getWards ();
    }

    function getWards () {
        const district = document.getElementById("district").value;
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "http://localhost:8083/address/get-ward?dCode=" + district, true);
        xhr.getResponseHeader("Content-type", "application/json");

        xhr.onload = function() {
            const data = JSON.parse(this.responseText);
            let html = '';

            for ( let c in data ) {
               html += '<option value="'+ data[c].wcode +'">'+ data[c].name +'</option>';

            }

           document.querySelector("#ward").innerHTML = html;
        }

        xhr.send();
    }
    </script>
  </body>
</html>