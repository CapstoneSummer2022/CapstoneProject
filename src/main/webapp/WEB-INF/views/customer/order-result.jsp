<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chúc mừng</title>
  </head>
  <body>
    <div>
        <h1>Mã đơn hàng của bạn là ${orderId}</h1>
        <h2>Bạn có thể theo dõi lịch sử đơn hàng trong mục <a href="${pageContext.request.contextPath}/order">Đơn hàng</a></h2>
    </div>
  </body>
</html>