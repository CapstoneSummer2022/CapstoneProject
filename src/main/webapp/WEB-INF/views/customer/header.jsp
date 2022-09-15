<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <header>
          <div id="first_row">
            <a href="/home"><h1 id="store_name">Electroco</h1></a>
            <div id="searchByCate">
              <form action="" id="store_search">
                <input type="text" id="prod_search" name="prod_search" placeholder="Tìm kiếm..."/>
                <button type="submit"><i class="material-icons">search</i></button>
              </form>
            </div>
            <div id="store_icons">
              <div id="cart_icon">
                <a href="/cart"><i class="material-icons">shopping_cart</i><span id="no_of_prods">1</span> </a> <a href="cart.html">Giỏ hàng</a>
              </div>
              <div id="avatar_icon">
                <a href="../signin"><i class="material-icons">person</i></a><a href="../signin">Đăng nhập</a> / <a href="../signup">Đăng ký</a>
              </div>
            </div>
          </div>
          <div id="second_row">
            <ul id="nav">
              <li><a href="#">Danh mục sản phẩm <i class="material-icons">arrow_drop_down</i></a>
                <ul>
                  <li><a href="/product?cate=?">Linh kiện bán dẫn </a>
                    <ul>
                      <li><a href="child-cgr.html">Transistor</a>
                      <li><a href="#">Diode</a>
                      <li><a href="#">Thysistor</a>
                      <li><a href="#">Diac - Triac</a>
                    </ul>
                  </li>
                  <li><a href="#">Linh kiện thụ động </a>
                    <ul>
                      <li><a href="child-cgr.html">Tụ điện</a>
                      <li><a href="#">Cuộn cảm</a>
                      <li><a href="#">Biến trở</a>
                      <li><a href="#">Điện trở</a>
                      <li><a href="#">Chiết áp</a>
                    </ul>
                  </li>
                  <li><a href="#">Cảm biến</a></li>
                </ul>
              </li>
              <li><a href="../contact">Thông tin liên hệ</a></li>
            </ul>
          </div>
        </header>

        <script>

        </script>