<!DOCTYPE html>
<html lang="vi">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Alef' rel='stylesheet'>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<!--Page Header-->
<header>
    <div id="first_row">
        <a href="home.jsp"><h1 id="store_name">Electroco</h1></a>
        <div id="searchByCate">
            <form action="" id="store_search">
                <select name="cate_search" id="cate_search">
                    <option value="">Tất cả</option>
                    <option value="">Diode</option>
                    <option value="">Transistor</option>
                    <option value="">Thysistor</option>
                    <option value="">Triac - Diac</option>
                    <option value="">IGBT</option>
                    <option value="">Fet</option>
                    <option value="">Tụ điện</option>
                    <option value="">Cuộn cảm</option>
                    <option value="">Điện trở</option>
                    <option value="">Thạch anh</option>
                    <option value="">Biến trở</option>
                    <option value="">Chiết áp</option>
                    <option value="">Nút bấm</option>
                    <option value="">Cầu chì</option>
                    <option value="">Led</option>
                    <option value="">Lcd</option>
                    <option value="">Cảm biến nhiệt độ</option>
                    <option value="">Cảm biến độ ẩm</option>
                    <option value="">Cảm biến ánh sáng</option>
                    <option value="">Cảm biến màu</option>
                    <option value="">Cảm biến chuyển động</option>
                    <option value="">Cảm biến nước</option>
                    <option value="">Cảm biến âm thanh</option>
                    <option value="">Cảm biến từ</option>
                    <option value="">IC cảm ứng</option>
                    <option value="">IC giao tiếp</option>
                    <option value="">IC real time</option>
                    <option value="">IC nguồn</option>
                    <option value="">IC audio</option>
                    <option value="">Dây kết nối</option>
                    <option value="">Cổng kết nối</option>
                </select>
                <input type="text" id="prod_search" name="prod_search" placeholder="Tìm kiếm..."/>
                <button type="submit"><i class="material-icons">search</i></button>
            </form>
        </div>
        <div id="store_icons">
            <div id="cart_icon">
                <a href="cart.html"><i class="material-icons">shopping_cart</i><span id="no_of_prods">1</span> </a> <a href="cart.html">Giỏ hàng</a>
            </div>
            <div id="avatar_icon">
                <a href="signin.jsp"><i class="material-icons">person</i></a><a href="signin.jsp">Đăng nhập</a> / <a href="signup.jsp">Đăng ký</a>
            </div>
        </div>
    </div>
    <div id="second_row">
        <ul id="nav">
            <li><a href="#">Danh mục sản phẩm <i class="material-icons">arrow_drop_down</i></a>
                <ul>
                    <li><a href="parent-cgr.html">Linh kiện bán dẫn </a>
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
            <li><a href="#">Nhà cung cấp <i class="material-icons">arrow_drop_down</i></a>
                <ul>
                    <li><a href="prod-by-supplier.html">DAEWOO</a></li>
                    <li><a href="#">ABECO</a></li>
                    <li><a href="#">DIGIKEY</a></li>
                </ul>
            </li>
            <li><a href="track-order.html">Theo dõi đơn hàng</a></li>
            <li><a href="contact.html">Thông tin liên hệ</a></li>
        </ul>
    </div>
</header>

<!--Page Content-->
<main>
    <div id="home_content">
        <div id="big_cates">
            <div class="one_big_cate"></div>
            <div class="one_big_cate"></div>
            <div class="one_big_cate"></div>
            <div class="one_big_cate"></div>
            <div class="one_big_cate"></div>
            <div class="one_big_cate"></div>
            <div class="one_big_cate"></div>
        </div>
        <div class="single_cate">
            <div class="cate_header">
                <h3>Linh kiện bán dẫn</h3>
                <a href="/service-pages/parent-cgr.html">Xem tất cả &nbsp; <i class="material-icons">arrow_forward</i></a>
            </div>
            <div class="some_prods">
                <div class="card">
                    <a href="/service-pages/child-cgr.html"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="/service-pages/child-cgr.html" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">3000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
            </div>
        </div>
        <div class="single_cate">
            <div class="cate_header">
                <h3>Linh kiện thụ động</h3>
                <a href="#">Xem tất cả &nbsp; <i class="material-icons">arrow_forward</i></a>
            </div>
            <div class="some_prods">
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">3000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
            </div>
        </div>
        <div class="single_cate">
            <div class="cate_header">
                <h3>LED/LCD</h3>
                <a href="#">Xem tất cả &nbsp; <i class="material-icons">arrow_forward</i></a>
            </div>
            <div class="some_prods">
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">3000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
            </div>
        </div>
        <div class="single_cate">
            <div class="cate_header">
                <h3>Cảm biến</h3>
                <a href="#">Xem tất cả &nbsp; <i class="material-icons">arrow_forward</i></a>
            </div>
            <div class="some_prods">
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">3000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
            </div>
        </div>
        <div class="single_cate">
            <div class="cate_header">
                <h3>IC chức năng</h3>
                <a href="#">Xem tất cả &nbsp; <i class="material-icons">arrow_forward</i></a>
            </div>
            <div class="some_prods">
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">3000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
            </div>
        </div>
        <div class="single_cate">
            <div class="cate_header">
                <h3>Kit phát triển</h3>
                <a href="#">Xem tất cả &nbsp; <i class="material-icons">arrow_forward</i></a>
            </div>
            <div class="some_prods">
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">3000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
            </div>
        </div>
        <div class="single_cate">
            <div class="cate_header">
                <h3>Connectors</h3>
                <a href="#">Xem tất cả &nbsp; <i class="material-icons">arrow_forward</i></a>
            </div>
            <div class="some_prods">
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">3000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">1500 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
                <div class="card">
                    <a href="#"><img src="../img/pros/1N4148 SMD 1206.webp" alt="hình ảnh sản phẩm" class="prod_img"></a>
                    <a href="#" class="prod_name">Tụ điện 3V</a>
                    <a href="#" class="price">2000 đ</a>
                    <button>Thêm vào giỏ</button>
                </div>
            </div>
        </div>
    </div>
</main>

<!--Page Footer-->
<footer style="display: grid; grid-template-columns: repeat(2, 45%); column-gap: 10%;">
    <div id="contact_info">
        <h5>Thông tin liên hệ</h5>
        <p>Số điện thoại: 0962352843</p>
        <p>Email: electroco@gmail.com</p>
        <p>Địa chỉ: 444 Xuân Phương, P. Xuân Phương, Q. Nam Từ Liêm, Tp. Hà Nội</p>
    </div>
    <div id="connect_info">
        <h5>Kết nối</h5>
        <a href="#"><img src="../img/logo/facebook.png" alt="facebook"></a>
        <a href="#"><img src="../img/logo/twitter.png" alt="twitter"></a>
        <a href="#"><img src="../img/logo/instagram.png" alt="instagram"></a>
    </div>
</footer>
</body>
</html>