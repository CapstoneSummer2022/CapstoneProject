<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <footer style="display: grid; grid-template-columns: repeat(2, 45%); column-gap: 10%;">
        </footer>

<script>
    getFooterInfo();

    function getFooterInfo() {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "http://localhost:8083/store-info-api", true);
        xhr.getResponseHeader("Content-type", "application/json");

        xhr.onload = function() {
            const data = JSON.parse(this.responseText);
            let html = '';

            for ( let c in data) {
               html += '<div id="contact_info">' +
                           '<h5>Thông tin liên hệ</h5>'+
                           '<p>Số điện thoại:' + data[c].phone + '</p>' +
                           '<p>Email: ' + data[c].email + '</p>' +
                           '<p>Địa chỉ:' + data[c].address+ '</p>' +
                         '</div>' +
                         '<div id="connect_info">' +
                           '<h5>Kết nối</h5>' +
                           '<a href="' + data[c].facebook + '"><img src="/logo/facebook.png" alt="facebook"></a>' +
                           '<a href="' + data[c].twitter + '"><img src="/logo/instagram.png" alt="twitter"></a>' +
                         '</div>';
            }

           document.getElementsByTagName("footer")[0].innerHTML = html;
       }

       xhr.send();
    }
</script>