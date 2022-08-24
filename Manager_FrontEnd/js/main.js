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
	//$("[data-toggle='tooltip']").tooltip();
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
function showBigCategoryInput() {
	console.log("active");
	var hasBigCategory = document.getElementById("hasBigCategory");
	var bigCategory = document.getElementById("bigCategory");
	var bigCategoryList = document.getElementById("bigCategoryList");
	if (bigCategory != null) {
		if (hasBigCategory.checked) {
			bigCategoryList.required = true;
			bigCategory.style.display = "block";
		} else {
			bigCategoryList.required = false;
			bigCategory.style.display = "none";
		}
	}

}

//Add to product list admin

//Quantity
const quantity = document.createElement("div");
quantity.className = "quantity";

const minusButton = document.createElement("button");
minusButton.className = "minus-btn";
minusButton.type = "button";
minusButton.innerHTML = "-";
// const minusIcon = document.createElement("i");
// minusIcon.classList.add("bx");
// minusIcon.classList.add("bx-minus");
// minusButton.appendChild(minusIcon);

const plusButton = document.createElement("button");
plusButton.className = "plus-btn";
plusButton.type = "button";
plusButton.innerHTML = "+";
// const plusIcon = document.createElement("i");
// plusIcon.classList.add("bx");
// plusIcon.classList.add("bx-plus");
// plusButton.appendChild(plusIcon);

const quantityInput = document.createElement("input");
quantityInput.type = "number";
quantityInput.classList.add("input-number");
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
//input number
const price = document.createElement("input");
price.type = "number";
price.value = 0;
price.classList.add("import-price");

function addToImportTable() {
	var orderProductTable = document.getElementById("importProductList");
	var productTable = document.getElementById("products");
	for (var i = 1, row; row = productTable.rows[i]; i++) {
		if (row.cells[7].getElementsByTagName('input')[0].checked && !duplicateimportProduct(row.cells[0].innerHTML)) {
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
			cell3.appendChild(price.cloneNode(true));
			cell4.appendChild(quantity.cloneNode(true));
			cell5.innerHTML = 0;
			cell6.appendChild(deleteButton.cloneNode(true));
		}
	}
	setEventImportPrice();
	setSumImport();
	setMinusValueFunction();
	setPlusValueFunction();
	setWholeValue();
}

function addToExportTable() {
	var orderProductTable = document.getElementById("exportProductList");
	var productTable = document.getElementById("products");
	for (var i = 1, row; row = productTable.rows[i]; i++) {
		if (row.cells[7].getElementsByTagName('input')[0].checked && !duplicateExportProduct(row.cells[0].innerHTML)) {
			var newRow = orderProductTable.insertRow(1);
			var cell0 = newRow.insertCell(0);
			var cell1 = newRow.insertCell(1);
			var cell2 = newRow.insertCell(2);
			var cell3 = newRow.insertCell(3);
			var cell4 = newRow.insertCell(4);
			var cell5 = newRow.insertCell(5);

			cell0.innerHTML = row.cells[0].innerHTML;
			cell1.innerHTML = row.cells[1].innerHTML;
			cell2.innerHTML = row.cells[2].innerHTML;
			cell3.innerHTML = row.cells[6].innerHTML;
			cell4.appendChild(quantity.cloneNode(true));
			cell5.appendChild(deleteButton.cloneNode(true));
		}
	}
	setMinusValueFunction();
	setPlusValueFunction();
	setWholeValue();
}

function addToProductTable() {
	var orderProductTable = document.getElementById("orderProductList");
	var productTable = document.getElementById("products");
	for (var i = 1, row; row = productTable.rows[i]; i++) {
		if (row.cells[7].getElementsByTagName('input')[0].checked && !duplicateOrderProduct(row.cells[0].innerHTML)) {
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
			cell5.innerHTML = row.cells[5].innerHTML;
			cell6.appendChild(deleteButton.cloneNode(true));

		}
	}
	setSumOrder();
	setMinusValueFunction();
	setPlusValueFunction();
	setWholeValue();
}

function duplicateOrderProduct(id) {
	var isDuplicate = false;
	var table = document.getElementById("orderProductList");
	for (var i = 1, row; row = table.rows[i]; i++) {
		if (row.cells[0].innerHTML === id) {
			alert("Sản phẩm " + id + " đã có trong đơn hàng.")
			isDuplicate = true;
			return isDuplicate;
		}

	}
	return isDuplicate;
}

function duplicateimportProduct(id) {
	var isDuplicate = false;
	var table = document.getElementById("importProductList");
	for (var i = 1, row; row = table.rows[i]; i++) {
		if (row.cells[0].innerHTML === id) {
			alert("Sản phẩm " + id + " đã có trong danh sách.")
			isDuplicate = true;
			return isDuplicate;
		}
	}
	return isDuplicate;
}

function duplicateExportProduct(id) {
	var isDuplicate = false;
	var table = document.getElementById("exportProductList");
	for (var i = 1, row; row = table.rows[i]; i++) {
		if (row.cells[0].innerHTML === id) {
			alert("Sản phẩm " + id + " đã có trong danh sách.")
			isDuplicate = true;
			return isDuplicate;
		}
	}
	return isDuplicate;
}



function setSumOrder() {
	if (document.getElementById("orderProductList") != null) {
		var table = document.getElementById("orderProductList");
		var sum = document.getElementById("sum");
		var sumNumber = 0;
		for (var i = 1, row; row = table.rows[i]; i++) {
			sumNumber = sumNumber + parseFloat(row.cells[5].innerHTML);
		}
		sum.innerHTML = convertMoney(sumNumber);
	}
}

function setSumImport() {
	if (document.getElementById("importProductList") != null) {
		var sum = document.getElementById("sum");
		var sumNumber = 0;
		var table = document.getElementById("importProductList");
		for (var i = 1, row; row = table.rows[i]; i++) {
			var currentUnit = row.cells[3].getElementsByTagName("input")[0].value;
			console.log(currentUnit);
			row.cells[5].innerHTML = currentUnit * row.cells[4].getElementsByTagName("input")[0].value;
			sumNumber = sumNumber + parseFloat(row.cells[5].innerHTML);
		}
		sum.innerHTML = convertMoney(sumNumber);
	}
}



function greaterThanZero() {
	var inputs = document.getElementsByTagName("input");

	for (var i = 0; i < inputs.length; i++) {
		if (inputs[i].type.toLowerCase() == "number") {
			inputs[i].addEventListener("input", function (e) {
				if (e.target.value < 0) {
					e.target.setCustomValidity('Giá trị số không được nhỏ hơn 0');
				} else {
					// input is fine -- reset the error message
					e.target.setCustomValidity('');
				}
			});
		}
	}
}

function setMinusValueFunction() {
	var minusButton = document.getElementsByClassName("minus-btn");
	for (var i = 0; i < minusButton.length; i++) {
		if (typeof minusButton[i].onclick !== "function") {
			minusButton[i].addEventListener("click", function (e) {
				var input = e.target.parentNode.parentNode.getElementsByTagName("input")[0];
				input.value--;
				if (input.value < 1) {
					$("#deleteProduct").modal('show');
					input.value = 1;
				}
				if (e.target.parentNode.parentNode.parentNode.parentNode.parentNode.id !== "exportProductList") {
					var unit = e.target.parentNode.parentNode.previousElementSibling;
					var ammount = e.target.parentNode.parentNode.nextElementSibling;
					if (e.target.parentNode.parentNode.parentNode.parentNode.parentNode.id == "importProductList") {
						ammount.innerHTML = input.value * parseInt(unit.getElementsByTagName("input")[0].value);
					} else {
						ammount.innerHTML = input.value * parseInt(unit.innerHTML);
					}
					setSumImport();
					setSumOrder();
				}
			});
		}
	}
}

function setPlusValueFunction() {
	var plusButton = document.getElementsByClassName("plus-btn");
	for (var i = 0; i < plusButton.length; i++) {
		if (typeof plusButton[i].onclick !== "function") {
			plusButton[i].addEventListener("click", function (e) {
				var input = e.target.parentNode.parentNode.getElementsByTagName("input")[0];
				input.value++;

				if (e.target.parentNode.parentNode.parentNode.parentNode.parentNode.id !== "exportProductList") {
					var unit = e.target.parentNode.parentNode.previousElementSibling;
					var ammount = e.target.parentNode.parentNode.nextElementSibling;
					if (e.target.parentNode.parentNode.parentNode.parentNode.parentNode.id == "importProductList") {
						console.log(unit.getElementsByTagName("input")[0].value);
						ammount.innerHTML = input.value * parseInt(unit.getElementsByTagName("input")[0].value);
					} else {
						ammount.innerHTML = input.value * parseInt(unit.innerHTML);
					}

					setSumImport();
					setSumOrder();
				}

			});
		}

	}
}

function setWholeValue() {
	var inputNumber = document.getElementsByClassName("input-number");
	if (inputNumber != null) {
		for (var i = 0; i < inputNumber.length; i++) {
			if (typeof inputNumber[i].oninput !== "function") {
				inputNumber[i].oninput = function (e) {
					if (e.target.value == "" || e.target.value < 1) {
						e.target.value = 1;
					}
					e.target.value = parseInt(e.target.value);
					if (e.target.parentNode.parentNode.parentNode.parentNode.parentNode.id !== "exportProductList") {
						var unit = e.target.parentNode.parentNode.previousElementSibling;
						var ammount = e.target.parentNode.parentNode.nextElementSibling;
						if (e.target.parentNode.parentNode.parentNode.parentNode.parentNode.id == "importProductList") {
							ammount.innerHTML = parseInt(unit.getElementsByTagName("input")[0].value) * parseInt(e.target.value);
							setSumImport();
						} else {
							ammount.innerHTML = parseInt(unit.innerHTML) * parseInt(e.target.value);
							setSumOrder();
						}
					}

				};
			}

		}
	}
}

function setEventImportPrice() {
	var importPrice = document.getElementsByClassName("import-price");
	if (importPrice != null) {
		for (var i = 0; i < importPrice.length; i++) {
			if (typeof importPrice[i].oninput !== "function") {
				importPrice[i].oninput = function (e) {
					if (e.target.value == "" || e.target.value < 0) {
						e.target.value = 0;
					}
					e.target.value = parseInt(e.target.value);
					var number = e.target.parentNode.nextElementSibling.getElementsByTagName("input")[0];
					var ammout = e.target.parentNode.nextElementSibling.nextElementSibling;
					ammout.innerHTML = parseInt(number.value) * parseInt(e.target.value);
					setSumImport();
				}
			}
		}
	}
}


function convertMoney(num) {
	return num.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });
}

function showPassword(checkbox) {
	var password = document.getElementsByClassName("password");
	for (var i = 0; i < password.length; i++) {
		if (checkbox.checked) {
			password[i].type = "text";
		} else {
			password[i].type = "password";
		}

	}
}


function checkPassword() {
	// /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
	var passwordRegex = /^(?=.{8,}$)(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?\W).*$/;
	var password = document.getElementById("pw");
	var rePassword = document.getElementById("rePw");
	if (!password.value.match(passwordRegex)) {
		password.setCustomValidity("Mật khẩu phải có ít nhất 8 ký tự, chữ in hoa, in thường, chữ số và ký tự đặc biệt");
		return false;
	} else {
		password.setCustomValidity("");
	}
	if (password.value != rePassword.value) {
		rePassword.setCustomValidity("Xác nhận lại mật khẩu không chính xác");
		return false;
	} else {
		password.setCustomValidity("");
	}
	return true;
}

function checkDateOfBirth() {
	var date = document.getElementById("inputDate");
	console.log(date.value);
	var varDate = new Date(date.value); //dd-mm-YYYY
	var today = new Date();
	today.setHours(0, 0, 0, 0);
	console.log(today);
	if (varDate >= today) {
		date.setCustomValidity("Giá trị ngày nhập vượt quá thời điểm hiện tại");
		return false;
	} else {
		date.setCustomValidity("");
	}
	return true;
}

function checkValidField() {
	var validPassword = checkPassword();
	var validDateOfBirth = checkDateOfBirth();
	return validPassword && validDateOfBirth;
}

function setThumbImage() {
	console.log("trigger");
	var image = document.getElementById("thumbimage");
	var file = document.getElementById("uploadfile").files[0];
	var reader = new FileReader();
	reader.addEventListener("load", () => {
		// convert image file to base64 string
		image.src = reader.result;
		image.style.display = ''
	}, false);
	if (file) {
		reader.readAsDataURL(file);
	}
}



window.onload = setEventImportPrice();
window.onload = setSumOrder();
window.onload = setSumImport();
window.onload = greaterThanZero();
window.onload = setPlusValueFunction();
window.onload = setMinusValueFunction();
window.onload = setWholeValue();





//Get the opener and Delete the product
$(document).ready(function () {
	var opener;

	$('.modal').on('show.bs.modal', function (e) {
		opener = document.activeElement;
	});

	$('.modal .btn-confirm').click(function () {
		if (document.getElementById("orderProductList") != null) {
			console.log(opener.className);
			var index;
			if (opener.className == "minus-btn") {
				index = opener.parentNode.parentNode.parentNode.rowIndex;
			} else {
				index = opener.parentNode.parentNode.rowIndex;
			}

			var tableOrder = document.getElementById("orderProductList");
			tableOrder.deleteRow(index);
			setSumOrder();
		}
		if (document.getElementById("importProductList") != null) {
			var index;
			if (opener.className == "minus-btn") {
				index = opener.parentNode.parentNode.parentNode.rowIndex;
			} else {
				index = opener.parentNode.parentNode.rowIndex;
			}
			var tableImport = document.getElementById("importProductList");
			tableImport.deleteRow(index);
			setSumImport();
		}
		if (document.getElementById("exportProductList") != null) {
			var index;
			if (opener.className == "minus-btn") {
				index = opener.parentNode.parentNode.parentNode.rowIndex;
			} else {
				index = opener.parentNode.parentNode.rowIndex;
			}
			var tableExport = document.getElementById("exportProductList");
			tableExport.deleteRow(index);
		}

	});

	$('.create-order-button').click(function () {
		var formProduct = document.getElementsByClassName("form-product")[0];
		if (formProduct.rows.length < 2) {
			$("#productEmpty").modal('show');
			return 0;
		}
	});



});


