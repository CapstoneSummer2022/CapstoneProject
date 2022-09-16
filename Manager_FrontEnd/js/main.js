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
		fromValue.classList.add("fromValue");

		var toValue = document.createElement("input");
		toValue.setAttribute("type", "number");
		toValue.setAttribute("name", optionValue.concat("toValue"));
		toValue.setAttribute("placeholder", "Giá trị lớn nhất");
		toValue.classList.add("toValue");

		var checkbox = document.createElement("input");
		checkbox.setAttribute("type", "checkbox");

		cell0.innerHTML = optionValue;
		cell1.innerHTML = optionText;
		cell2.appendChild(fromValue.cloneNode());
		cell2.innerHTML += ' - '
		cell2.appendChild(toValue.cloneNode());
		cell3.appendChild(checkbox.cloneNode());
		validateMinMax();

	}
}

function validateMinMax() {
	var fromValues = document.getElementsByClassName("fromValue");
	var toValues = document.getElementsByClassName("toValue");

	for (var i = 0; i < toValues.length; i++) {
		if (typeof toValues[i].oninput !== "function") {
			toValues[i].addEventListener("input", function (e) {
				if (e.target.previousElementSibling.value > e.target.value) {
					console.log(e.target.value);
					console.log(e.target.previousElementSibling.value);
					e.target.setCustomValidity('Giá trị lớn nhất phải lớn hơn hoặc bằng giá trị nhỏ nhất');
				} else {
					// input is fine -- reset the error message
					e.target.setCustomValidity('');
				}
			});
		}
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
// Category list
function invalidOption(option) {
	var table = document.getElementById("categoryTable");
	for (var i = 1, row; row = table.rows[i]; i++) {
		if (row.cells[0].innerHTML === option) {
			return true;
		}
	}
	return false;
}

function addToCategoryTable() {
	var table = document.getElementById("categoryTable");
	var selection = document.getElementById("categoryOption");
	var optionText = selection.options[selection.selectedIndex].text;
	var optionValue = selection.options[selection.selectedIndex].value;
	if (selection.selectedIndex != 0 && !invalidOption(optionText)) {
		var row = table.insertRow(1);
		var cell0 = row.insertCell(0);
		var cell1 = row.insertCell(1);
		var cell2 = row.insertCell(2);

		var checkbox = document.createElement("input");
		checkbox.setAttribute("type", "checkbox");

		cell0.innerHTML = optionValue;
		cell1.innerHTML = optionText;
		cell2.appendChild(checkbox.cloneNode());
	}
}

function deleteFromCategoryTable() {
	var table = document.getElementById("categoryTable");
	for (var i = 1, row; row = table.rows[i]; i++) {
		if (row.cells[2].getElementsByTagName('input')[0].checked) {
			table.deleteRow(i);
			i = 0;
		}
	}
}

function checkRequiredTable() {
	var categoryTable = document.getElementById("categoryTable");
	if (categoryTable.rows.length < 2) {

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
//Detail button  


const detailButton = document.createElement("button");
detailButton.classList.add("btn");
detailButton.classList.add("btn-primary");
detailButton.title = "Chi tiết đơn hàng";
detailButton.type = "button";
detailButton.setAttribute("data-toggle", "modal");
detailButton.setAttribute("data-target", "#orderItemList");
const detailIcon = document.createElement("i");
detailIcon.classList.add("fa");
detailIcon.classList.add("fa-info");
detailButton.appendChild(detailIcon);


//input number
const price = document.createElement("input");
price.type = "number";
price.value = 0;
price.classList.add("import-price");

function resetImportTable() {
	var importProductTable = document.getElementById("importProductList");
	for (var i = 1, row; row = importProductTable.rows[i]; i++) {
		importProductTable.deleteRow(i);
		i = 0
	}
	addSkudCode();
}


//1-a-2-3-P1-2022-02-03
function addSkudCode() {
	var warehouse = document.getElementById("warehouse").value;
	var row = document.getElementById("row").value;
	var column = document.getElementById("column").value;
	var shelf = document.getElementById("shelf").value;
	var importDate = document.getElementById("importDate").value;
	var date = new Date(importDate);
	var dd = String(date.getDate()).padStart(2, '0');
	var mm = String(date.getMonth() + 1).padStart(2, '0');
	var yyyy = date.getFullYear();
	date = yyyy + '-' + mm + '-' + dd;

	var importProductTable = document.getElementById("importProductList");
	if (warehouse != "" && row != "" && column != "" && shelf != "" && importDate != "") {
		for (var i = 1, rowTable; rowTable = importProductTable.rows[i]; i++) {
			var id = rowTable.cells[0].innerHTML;
			rowTable.cells[1].style = "";
			rowTable.cells[1].innerHTML = warehouse + "-" + shelf + "-" + row + "-" + column + "-P" + id + "-" + date;
		}
	}
}

function duplicateRow(e) {
	var currentIndex = e.parentNode.parentNode.rowIndex;
	var exportTable = document.getElementById("exportProductList");
	var productID = parseInt(exportTable.rows[currentIndex].cells[0].innerHTML);
	var orderProductTable = document.getElementById("exportProductList");
	var currentRow = orderProductTable.rows[currentIndex];

	/* Loop here */
	if (validateNumberSKUByButton(currentIndex)) {
		console.log(validateNumberSKUByButton(currentIndex));
		var newRow = orderProductTable.insertRow(currentIndex + 1);
		var cell0 = newRow.insertCell(0);
		var cell1 = newRow.insertCell(1);
		var cell2 = newRow.insertCell(2);
		var cell3 = newRow.insertCell(3);
		var cell4 = newRow.insertCell(4);
		var cell5 = newRow.insertCell(5);
		var cell6 = newRow.insertCell(6);

		cell0.innerHTML = currentRow.cells[0].innerHTML;
		cell1.innerHTML = currentRow.cells[1].innerHTML;
		cell2.innerHTML = currentRow.cells[2].innerHTML;
		cell3.innerHTML = currentRow.cells[3].innerHTML;
		cell4.innerHTML = currentRow.cells[4].innerHTML;
		cell5.innerHTML = currentRow.cells[5].innerHTML;
		cell6.innerHTML = currentRow.cells[6].innerHTML;// added here
	} else {
		alert("Tổng Số lượng theo từng SKU vượt quá số lượng sản phẩm\nMã sản phẩm: " + productID);
	}




}
function validateNumberSKUByField(e) {
	var exportTable = document.getElementById("exportProductList");
	var clickedIndex = e.parentNode.parentNode.rowIndex;
	var productTotal = parseInt(exportTable.rows[clickedIndex].cells[3].innerHTML);
	var productID = parseInt(exportTable.rows[clickedIndex].cells[0].innerHTML);
	var skuTotal = 0;
	for (var i = 1, row; row = exportTable.rows[i]; i++) {
		if (parseInt(row.cells[0].innerHTML) == productID) {
			var skuNumber = parseInt(row.cells[5].getElementsByTagName("input")[0].value) || 0;
			skuTotal = skuTotal + skuNumber;
		}
	}

	if (skuTotal > productTotal) {
		e.setCustomValidity("Tổng Số lượng theo từng SKU vượt quá số lượng sản phẩm");
	} else {
		e.setCustomValidity("");
		console.log(skuTotal, productTotal);
	}
}

function validateNumberSKUByButton(clickedIndex) {
	var exportTable = document.getElementById("exportProductList");
	var productTotal = parseInt(exportTable.rows[clickedIndex].cells[3].innerHTML)
	var productID = parseInt(exportTable.rows[clickedIndex].cells[0].innerHTML);
	var skuTotal = 0;
	for (var i = 1, row; row = exportTable.rows[i]; i++) {
		if (parseInt(row.cells[0].innerHTML) == productID) {
			var skuNumber = parseInt(row.cells[5].getElementsByTagName("input")[0].value) || 0;
			skuTotal = skuTotal + skuNumber;
		}
	}
	return skuTotal < productTotal;
}


function importInfoSelect(e) {
	var warehouse = document.getElementById("warehouse").value;
	var row = document.getElementById("row").value;
	var column = document.getElementById("column").value;
	var shelf = document.getElementById("shelf").value;
	var importDate = document.getElementById("importDate").value;
	if (warehouse == "" || row == "" || column == "" || shelf == "" || importDate == "") {
		e.removeAttribute("data-toggle");
		$("#selectImportInfo").modal('show');
	} else {
		e.setAttribute("data-toggle", "modal");
	}
}



function addToImportTable() {
	var importProductTable = document.getElementById("importProductList");
	var productTable = document.getElementById("products");
	for (var i = 1, row; row = productTable.rows[i]; i++) {
		if (row.cells[7].getElementsByTagName('input')[0].checked && !duplicateimportProduct(row.cells[0].innerHTML)) {
			var newRow = importProductTable.insertRow(1);
			var cell0 = newRow.insertCell(0);
			var cell1 = newRow.insertCell(1);
			var cell2 = newRow.insertCell(2);
			var cell3 = newRow.insertCell(3);
			var cell4 = newRow.insertCell(4);
			var cell5 = newRow.insertCell(5);
			var cell6 = newRow.insertCell(6);
			var cell7 = newRow.insertCell(7);

			cell0.innerHTML = row.cells[0].innerHTML;
			cell1.innerHTML = "Chưa tạo";
			cell1.style.color = "red";
			cell2.innerHTML = row.cells[1].innerHTML;
			cell3.innerHTML = row.cells[2].innerHTML;
			cell4.appendChild(price.cloneNode(true));
			cell5.appendChild(quantity.cloneNode(true));
			cell6.innerHTML = 0;
			cell7.appendChild(deleteButton.cloneNode(true));

		}
	}
	setEventImportPrice();
	setSumImport();
	setWholeValue();
	addSkudCode();
}

function addToExportProductTable(e) {
	var exportProductTable = document.getElementById("exportProductList");
	var orderList = document.getElementById("orders");

	//OrderID
	var orderId = orderList.rows[e.parentNode.parentNode.rowIndex].cells[0].innerHTML;

	for (var i = 1, row; row = exportProductTable.rows[i]; i++) {
		exportProductTable.deleteRow(i);
		i = 0;
	}

	//Get items from new order


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
			cell4.getElementsByClassName("minus-btn")[0].addEventListener("click", minus);
			cell4.getElementsByClassName("plus-btn")[0].addEventListener("click", plus);
			cell5.innerHTML = row.cells[5].innerHTML;
			cell6.appendChild(deleteButton.cloneNode(true));

		}
	}
	setSumOrder();
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
			alert("Đơn hàng " + id + " đã có trong danh sách.")
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
			var currentUnit = row.cells[4].getElementsByTagName("input")[0].value;
			row.cells[6].innerHTML = currentUnit * row.cells[5].getElementsByTagName("input")[0].value;
			sumNumber = sumNumber + parseFloat(row.cells[6].innerHTML);
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
	validateMinMax();
}

function setMinusValueFunction() {
	var minusButton = document.getElementsByClassName("minus-btn");
	for (var i = 0; i < minusButton.length; i++) {
		if (typeof minusButton[i].onclick != "function") {
			minusButton[i].addEventListener("click", minus);
		}
	}
}

function minus(e) {
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
}

function setPlusValueFunction() {
	var plusButton = document.getElementsByClassName("plus-btn");
	for (var i = 0; i < plusButton.length; i++) {
		if (typeof plusButton[i].onclick != "function") {
			plusButton[i].addEventListener("click", plus);
		}

	}
}

function plus(e) {
	var input = e.target.parentNode.parentNode.getElementsByTagName("input")[0];
	input.value++;
	if (input.value > 999) {
		input.value = 999;
	}
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
							if (e.target.value > 999) {
								e.target.value = 999;
							}
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

//fixed
function checkPassword() {
	// /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
	var valid = true;
	var passwordRegex = /^(?=.{8,}$)(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?\W).*$/;
	var password = document.getElementById("pw");
	var rePassword = document.getElementById("rePw");
	if (!password.value.match(passwordRegex)) {
		password.setCustomValidity("Mật khẩu phải có ít nhất 8 ký tự, chữ in hoa, in thường, chữ số và ký tự đặc biệt");
		valid = false;
	} else {
		password.setCustomValidity("");
	}
	if (password.value != rePassword.value) {
		rePassword.setCustomValidity("Xác nhận lại mật khẩu không chính xác");
		valid = false;
	} else {
		rePassword.setCustomValidity("");
	}
	valid = true;
}

//fixed
function checkDate() {
	var date;
	if (document.getElementById("inputDate") != null) {
		date = document.getElementById("inputDate");
	} else if (document.getElementById("importDate") != null) {
		date = document.getElementById("importDate");
	} else {
		date = document.getElementById("exportDate");
	}
	var varDate = new Date(date.value); //dd-mm-YYYY
	var today = new Date();
	//today.setHours(0, 0, 0, 0);
	if (varDate > today) {
		date.setCustomValidity("Giá trị ngày nhập vượt quá thời điểm hiện tại");
		return false;
	} else {
		date.setCustomValidity("");
	}
	return true;
}

function checkValidField() {
	var validPassword = checkPassword();
	var validDateOfBirth = checkDate();
	return validPassword && validDateOfBirth;
}

function setThumbImage() {
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

function validateTable() {
	console.log("clicked")
	var formProduct = document.getElementById("categoryTable");
	if (formProduct.rows.length < 2) {
		$("#categoryEmpty").modal('show');
		return false;
	}
}




setEventImportPrice();
setSumOrder();
setSumImport();
greaterThanZero();
setPlusValueFunction();
setMinusValueFunction();
setWholeValue();


//Get the opener and Delete the product
$(document).ready(function () {

	var opener;
	$('.modal').on('show.bs.modal', function (e) {
		opener = document.activeElement;
	});

	$('.modal .btn-confirm').click(function () {
		if (document.getElementById("orderProductList") != null) {
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
			index = opener.parentNode.parentNode.rowIndex;
			var tableExport = document.getElementById("exportProductList");
			tableExport.deleteRow(index);
		}
	});


	$('.create-order-button').click(function () {
		var formProduct = document.getElementsByClassName("form-product")[0];
		if (formProduct.rows.length < 2) {
			$("#productEmpty").modal('show');
			return false;
		}
	});


});

