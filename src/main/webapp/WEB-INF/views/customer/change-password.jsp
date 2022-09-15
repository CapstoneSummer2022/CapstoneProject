<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đổi mật khẩu</title>
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
              <h4>Đổi mật khẩu</h4>
              <div id="changepwd_content">
                <form action="/change-password" method="post" name="changepwdForm" onsubmit="return validateChangepwdForm();">
                  <table>
                      <tr>
                        <td>
                          <input type="password" name="current_pwd" id="current_pwd" placeholder="Mật khẩu hiện tại"/>
                          <p id="currentpwd-error" class="error"></p>
                        </td>
                        <td><input type="hidden" name="id" id="id" value="2"/></td>
                      </tr>
                      <tr>
                        <td>
                          <input type="password" name="new_pwd" id="new_pwd" placeholder="Mật khẩu mới"/>
                          <p id="newpwd-error" class="error"></p>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>
                          <input type="password" name="confirm_pwd" id="confirm_pwd" placeholder="Xác nhận mật khẩu"/>
                          <p id="repassword-error" class="error"></p>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td></td>
                        <td><button type="submit">Xác nhận</button></td>
                      </tr>
                  </table>
                </form>
              </div>
            </div>
          </div>
        </main>
    
        <!--Page Footer-->
        <jsp:include page="footer.jsp"/>

        <script src="/js/validate.js"></script>
      </body>
</html>