//Thời Gian
function time() {
	var today = new Date();
	var weekday = new Array(7);
	weekday[0] = "Chủ Nhật";
	weekday[1] = "Thứ Hai";
	weekday[2] = "Thứ Ba";
	weekday[3] = "Thứ Tư";
	weekday[4] = "Thứ Năm";
	weekday[5] = "Thứ Sáu";
	weekday[6] = "Thứ Bảy";
	var day = weekday[today.getDay()];
	var dd = today.getDate();
	var mm = today.getMonth() + 1;
	var yyyy = today.getFullYear();
	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();
	m = checkTime(m);
	s = checkTime(s);
	nowTime = h + " giờ " + m + " phút " + s + " giây";
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}
	today = day + ', ' + dd + '/' + mm + '/' + yyyy;
	tmp = '<span class="date"> ' + today + ' - ' + nowTime +
		'</span>';
	document.getElementById("clock").innerHTML = tmp;
	clocktime = setTimeout("time()", "1000", "Javascript");

	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}
}

(function () {
	"use strict";

	var treeviewMenu = $('.app-menu');

	// Toggle Sidebar
	$('[data-toggle="sidebar"]').click(function (event) {
		event.preventDefault();
		$('.app').toggleClass('sidenav-toggled');
	});

	// Activate sidebar treeview toggle
	$("[data-toggle='treeview']").click(function (event) {
		event.preventDefault();
		if (!$(this).parent().hasClass('is-expanded')) {
			treeviewMenu.find("[data-toggle='treeview']").parent().removeClass('is-expanded');
		}
		$(this).parent().toggleClass('is-expanded');
	});
	// Set initial active toggle
	$("[data-toggle='treeview.'].is-expanded").parent().toggleClass('is-expanded');
	//Activate bootstrip tooltips
	$("[data-toggle='tooltip']").tooltip();
})();

document.querySelector(".status-checkbox").addEventListener("click", function (event) {
	return false
}, false);

function removeSelectedFile() {
	document.getElementById("fileInput").value = null;
}
//Validate email begin
function validateEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

function continueornot() {
	if (validateEmail(document.getElementById('emailfield').value)) {
		// ok
	} else { alert("email not valid"); return false; }
}
//validate email end	

//add to specification talbe
function invalidOption(option) {
	var table = document.getElementById("specTable");
	for (var i = 1, row; row = table.rows[i]; i++) {
		if (row.cells[0].innerHTML === option) {
			return true;
		}
	}
	return false;
}

function addToSpecTable() {
	var table = document.getElementById("specTable");
	var selection = document.getElementById("specOption");
	var optionText = selection.options[selection.selectedIndex].text;
	var optionValue = selection.options[selection.selectedIndex].value;
	if (selection.selectedIndex != 0 && !invalidOption(optionText)) {
		var row = table.insertRow(1);
		var cell0 = row.insertCell(0);
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);
		var cell3 = row.insertCell(3);

		var fromValue = document.createElement("input");
		fromValue.setAttribute("type", "number");
		fromValue.setAttribute("name", optionValue.concat("fromValue"));
		fromValue.setAttribute("placeholder", "Giá trị nhỏ nhất");

		var toValue = document.createElement("input");
		toValue.setAttribute("type", "number");
		toValue.setAttribute("name", optionValue.concat("toValue"));
		toValue.setAttribute("placeholder", "Giá trị lớn nhất");

		var checkbox = document.createElement("input");
		checkbox.setAttribute("type", "checkbox");

		cell0.innerHTML = optionValue;
		cell1.innerHTML = optionText;
		cell2.appendChild(fromValue.cloneNode());
		cell2.innerHTML += ' - '
		cell2.appendChild(toValue.cloneNode());
		cell3.appendChild(checkbox.cloneNode());
	}
}


function deleteFromTable() {
	var table = document.getElementById("specTable");
	for (var i = 1, row; row = table.rows[i]; i++) {
		if (row.cells[3].getElementsByTagName('input')[0].checked) {
			table.deleteRow(i);
			i = 0;
		}
	}
}

//has big category or not
function showBigCategoryInput(checkbox) {
	var bigCategory = document.getElementById("bigCategory");
	if (checkbox.checked) {
		bigCategory.style.display = "block";
	} else {
		bigCategory.style.display = "none";
	}
}

//Add to product list admin

//Quantity
const quantity = document.createElement("div");
quantity.className = "quantity";

const minusButton = document.createElement("button");
minusButton.className = "plus-btn";
minusButton.type = "button";
const minusIcon = document.createElement("i");
minusIcon.classList.add("bx");
minusIcon.classList.add("bx-minus");
minusButton.appendChild(minusIcon);

const plusButton = document.createElement("button");
plusButton.className = "plus-btn";
plusButton.type = "button";
const plusIcon = document.createElement("i");
plusIcon.classList.add("bx");
plusIcon.classList.add("bx-plus");
plusButton.appendChild(plusIcon);

const quantityInput = document.createElement("input");
quantityInput.type = "number";
quantityInput.value = 1;

quantity.appendChild(minusButton);
quantity.appendChild(quantityInput);
quantity.appendChild(plusButton);
//Delete button
const deleteButton = document.createElement("button");
deleteButton.classList.add("btn");
deleteButton.classList.add("btn-primary");
deleteButton.classList.add("btn-sm");
deleteButton.classList.add("trash");
deleteButton.setAttribute("data-toggle", "modal");
deleteButton.setAttribute("data-target", "#deleteProduct");
deleteButton.type = "button";
deleteButton.title = "Xoá";
const deleteIcon = document.createElement("i");
deleteIcon.classList.add("fas");
deleteIcon.classList.add("fa-trash-alt");
deleteButton.appendChild(deleteIcon);



function addToProductTable() {
	var orderProductTable = document.getElementById("orderProductList");
	var productTable = document.getElementById("products");
	for (var i = 1, row; row = productTable.rows[i]; i++) {
		if (row.cells[7].getElementsByTagName('input')[0].checked && !duplicateProduct(row.cells[0].innerHTML)) {
			var newRow = orderProductTable.insertRow(1);
			var cell0 = newRow.insertCell(0);
			var cell1 = newRow.insertCell(1);
			var cell2 = newRow.insertCell(2);
			var cell3 = newRow.insertCell(3);
			var cell4 = newRow.insertCell(4);
			var cell5 = newRow.insertCell(5);
			var cell6 = newRow.insertCell(6);

			cell0.innerHTML = row.cells[0].innerHTML;
			cell1.innerHTML = row.cells[1].innerHTML;
			cell2.innerHTML = row.cells[2].innerHTML;
			cell3.innerHTML = row.cells[5].innerHTML;
			cell4.appendChild(quantity.cloneNode(true));
			cell5.innerHTML = row.cells[5].innerHTML
			cell6.appendChild(deleteButton.cloneNode(true));
		}
	}
	setSum();
}

function duplicateProduct(id) {
	var isDuplicate = false;
	var table = document.getElementById("orderProductList");
	for (var i = 1, row; row = table.rows[i]; i++) {
		if (row.cells[0].innerHTML === id) {
			alert("Sản phẩm đã có trong đơn hàng.")
			isDuplicate = true;
			return isDuplicate;
		}

	}
	return isDuplicate;
}

function outOfStock() {

}


function setSum() {
	var sum = document.getElementById("sum");
	var sumNumber = 0;
	var table = document.getElementById("orderProductList");
	for (var i = 1, row; row = table.rows[i]; i++) {
		console.log(row.cells[5].innerHTML);
		sumNumber = sumNumber + parseFloat(row.cells[5].innerHTML);
	}
	sum.innerHTML = sumNumber;
}


function plusQuantity(button) {
	console.log(button.cellIndex);
	var currentValue = parseInt(button.nextSibling.value);
	button.nextSibling.value = currentValue + 1;
}

function minusQuantity(event) {
	var buttonClicked = event.target;
	var currentValue = buttonClicked.closest('input').querySelector.value;
	buttonClicked.closest('input').querySelector.value = currentValue - 1;

}