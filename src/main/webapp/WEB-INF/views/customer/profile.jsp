<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hồ sơ của tôi</title>
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
          <div class="account">
            <div class="account_side">
              <a href="profile.html" style="font-weight: bold; background-color: rgba(0,0,0,0.1);"><i class="material-icons"> arrow_right</i>Hồ sơ của tôi</a>
              <a href="all-orders.html"><i class="material-icons"> arrow_right</i>Đơn hàng của tôi</a>
              <a href="change-password.html"><i class="material-icons"> arrow_right</i>Đổi mật khẩu</a>
            </div>
            <div class="account_content">
              <h4>Hồ sơ</h4>
              <div id="profile_content">
                <table>
                  <tr>
                    <td class="label">Họ tên:</td>
                    <td class="field"><input type="text" name="name" id="name" value="Thuong Nguyen" readonly/></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td class="label">Email:</td>
                    <td class="field"><input type="text" name="email" id="email" value="thuongnthe141559@gmail.com" readonly style="width: 100%;"/></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td class="label">Số điện thoại:</td>
                    <td class="field"><input type="text" name="phone" id="phone" value="0977456198" readonly/></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td class="label">Ngày sinh:</td>
                    <td class="field"><input type="date" name="birthday" id="birthday" value="2000-01-04" readonly/></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td class="label">Giới tính:</td>
                    <td class="field">
                      <input type="radio" checked/> &nbsp;Nữ
                      <input type="radio" disabled/> &nbsp;Nam
                    </td>
                    <td></td>
                  </tr>
                  <tr>
                    <td class="label">Địa chỉ:</td>
                    <td class="field"><input type="text" name="address" id="address" value="ngã tư Tân Xã (thôn 9), Tân Xã, Thạch Thất, Hà Nội" readonly style="width: 100%;"/></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td></td>
                    <td>
                      <button onclick="openUpdate()">Chỉnh sửa</button>
                    </td>
                  </tr>
                </table>
              </div>
              <div id="profile_update" style="display: none;">
                <table>
                  <form action="/" method="post" name="profileForm" onsubmit="return validateUpdateProfileForm();">
                    <tr>
                      <td class="label">Họ tên:</td>
                      <td class="field">
                        <input type="text" name="name" id="name" value="Thuong Nguyen"/>
                        <p id="name-error" class="error"></p>
                      </td>
                      <td><input type="hidden" name="id" id="id" value="2"/></td>
                    </tr>
                    <tr>
                      <td class="label">Email:</td>
                      <td class="field">
                        <input type="text" name="email" id="email" value="thuongnthe141559@gmail.com"/>
                        <p id="email-error" class="error"></p>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td class="label">Số điện thoại:</td>
                      <td class="field">
                        <input type="text" name="phone" id="phone" value="0977456198" maxlength="10" 
                          oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
                        <p id="phone-error" class="error"></p>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td class="label">Ngày sinh:</td>
                      <td class="field">
                        <input type="date" name="birthday" id="birthday" value="2000-01-04"/>
                        <p id="birthday-error" class="error"></p>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td class="label">Giới tính:</td>
                      <td class="field">
                        <input type="radio" name="gender" id="female" checked/>
                        <label for="female">Nữ</label>
                        <input type="radio" name="gender" id="male"/>
                        <label for="male">Nam</label>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td class="label">Thành phố:</td>
                      <td class="field">
                        <input type="text" name="province" id="province" value="Hà Nội"/>
                        <p id="province-error" class="error"></p>                    
                      </td>
                    </tr>
                    <tr>
                      <td class="label">Quận/Huyện:</td>
                      <td class="field">
                        <input type="text" name="district" id="district" value="Thạch Thất"/>
                        <p id="district-error" class="error"></p>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td class="label">Xã/Phường:</td>
                      <td class="field">
                        <input type="text" name="ward" id="ward" value="Tân Xã"/>
                        <p id="ward-error" class="error"></p>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td class="label">Địa chỉ cụ thể:</td>
                      <td class="field">
                        <input type="text" name="detailLocation" id="detailLocation" value="ngã tư Tân Xã (thôn 9)"/>
                        <p id="detaillocation-error" class="error"></p>
                      </td>
                      <td></td>
                    </tr>
                    <tr>
                      <td></td>
                      <td></td>
                      <td>
                        <button type="submit">Lưu</button>
                  </form>
                      <button onclick="closeUpdate()">Hủy</button>
                      </td>
                    </tr>
                </table>  
              </div>
            </div>
          </div>
        </main>
    
        <!--Page Footer-->
        <jsp:include page="footer.jsp"/>

        <script src="/js/validate.js"></script>

      </body>
</html>