/**
 * Styling message error presenting on screen
 * 
 * @param {*} error 
 */
 function styleError(error) {
    error.style.margin = '0 0 0.5em';
    error.style.fontSize = '14px';
    error.style.color = 'red';
}

/**
 * Validate email that has 5 to 50 characters and is right format
 * 
 * @param {*} email 
 * @returns boolean value
 */
function validateEmail(email) {
    let error = document.getElementById("email-error");
    styleError(error);

    let lengthOfEmail = email.length;
    if (lengthOfEmail !== 0) {
        let pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

        if (pattern.test(email)) {
            error.style.display = 'none';
            return true;
        } else {
            error.style.display = "block";
            error.innerHTML = "Bạn cần điền đúng format email. Ví dụ: abc@example.com";
        }
    } else {
        error.style.display = "block";
        error.innerHTML = "Email là bắt buộc";
    }

    return false;
}

/**
 * Validate text from a field that has valid length
 * 
 * @param {*} text . String got from input field
 * @param {*} errorElementID . Error element id to style if there has any problem
 * @param {*} field . Field name for present what field has error
 * @param {*} minLength . Min length of string got from input field
 * @param {*} maxLength . Max length of string got from input field
 * @returns boolean value
 */
function validatePassword(text, errorElementID, field) {
    let error = document.getElementById(errorElementID);
    styleError(error);

    let lengthOfText = text.length;
    if (lengthOfText !== 0) {
        if (lengthOfText < 8) {
            error.style.display = "block";
            error.innerHTML = `${field} không được ít hơn 8 kí tự`;
        } 
    } else {
        error.style.display = "block";
        error.innerHTML = `${field} là bắt buộc`;
    }
}

/**
 * Chech if re-password has valid length and same as password
 * 
 * @param {*} pwd . Password got from password field
 * @param {*} repwd . Re-password got from password field
 * @returns boolean value
 */
function validateRePassword(pwd, repwd) {
    let error = document.getElementById("rpwd-error");
    styleError(error);

    if (pwd !== repwd) {
        error.style.display = "block";
        error.innerHTML = "Mật khẩu xác nhận phải giống mật khẩu";
    } else {
        error.style.display = 'none';
        return true;
        
    }
    
    return false;
}

/**
 * Validate phone string
 * 
 * @param {*} phone . String got from phone field
 * @returns boolean value
 */
function validatePhone(phone) {
    let error = document.getElementById("phone-error");
    styleError(error);

    let pattern = /^\d{3,4}(-?\d{3,4}){2}$/;

    if (pattern.test(phone)) {
        error.style.display = 'none';
        return true;
    } else {
        error.style.display = "block";
        error.innerHTML = "Số điện thoại của bạn không hợp lệ";
        return false;
    }
}

/**
 * Validate Login Form
 * 
 * @returns boolean value
 */
function validateLoginForm() {
    const EMAIL = document.loginForm.email.value;
    const PWD = document.loginForm.password.value;

    let isValid = 0;
    if (!validateEmail(EMAIL)) { isValid++; }
    if (!validateText(PWD, "pwd-error", "Password", 8, 30)) { isValid++; }

    if (isValid !== 0) {
        return false;
    } else {
        return true;
    }
}

/**
 * Validate Register Form
 * 
 * @returns boolean value
 */
function validateRegisterForm() {
    const USERNAME = document.registerForm.username.value
    const EMAIL = document.registerForm.email.value;
    const PWD = document.registerForm.password.value;
    const RE_PWD = document.registerForm.repassword.value;

    let isValid = 0;
    if (!validateEmail(EMAIL)) { isValid++; }
    if (!validateText(PWD, "pwd-error", "Password", 8, 30)) { isValid++; }
    if (!validateRePassword(PWD, RE_PWD)) { isValid++; }

    if (isValid !== 0) {
        return false;
    } else {
        return true;
    }
}

let phonePattern = /^(84|0[3|5|7|8|9])+([0-9]{8})\b$/;
let emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
let passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
let textPattern = /^[a-zA-Z0-9 '\(\)àáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝ]*$/;
let passwordMessage = "Mật khẩu của bạn cần đủ 8 kí tự, có ít nhất 1 chữ hoa, 1 chữ thường, 1 chữ số và 1 kí tự đặc biệt";


/**
 * Print error message
 *
 * @param {*} error
 * @param {*} message
 */
function printError (error, message) {
    error.style.display = 'block';
    error.innerHTML = message;
}


function invalidMessage (field) {
    return `${field} của bạn không hợp lệ`;
}

/**
 * Hide error message
 *
 * @param {*} error
 */
function hideError (error) {
    error.style.display = 'none';
}

/**
 * Validate unrequired field'content by regex pattern
 * If it's empty, ignore it
 * else check if it match the pattern
 * Print error if it is not match the pattern and return false
 *
 * @param {*} text
 * @param {*} errorElementID
 * @param {*} field
 * @param {*} pattern
 * @param {*} required
 * @returns boolean value
 */
function validateUnrequiredField (text, errorID, field, pattern) {
    let error = document.getElementById(errorID);

    const lengthOfText = text.length;

    //check if field is empty
    if (lengthOfText !== 0) {
        //check if all form's field match the pattern then print error
        //else hide the error
        if (!pattern.test(text)) {
            printError(error, `${field} không hợp lệ`);
        }
        else {
            hideError(error);
            return true;
        }

    } else {
        hideError(error);
        return true;
    }

    return false;
}

/**
 * Validate required fields' content by regex pattern
 * Print error if they are not match the pattern and return false
 *
 * @param {*} text
 * @param {*} errorID
 * @param {*} field
 * @param {*} pattern
 * @param {*} message
 * @returns booolean value
 */
function validateRequiredField (text, errorID, field, pattern, message) {
    let error = document.getElementById(errorID);

    const lengthOfText = text.length;
    if (lengthOfText !== 0) {

        if (!pattern.test(text)) {
            printError(error, message);
        }
        else {
            hideError(error);
            return true;
        }

    } else {
        printError(error, `Vui lòng điền vào ${field} của bạn`);
    }

    return false;
}

/**
 * Check password and re-password if they are same
 * Print error if they are not same and return false
 *
 * @param {*} pwd
 * @param {*} repwd
 * @param {*} pwdField
 * @param {*} repwdField
 * @returns boolean value
 */
function checkSamePassword (pwd, repwd, pwdField, repwdField) {
    let error = document.getElementById("repassword-error");

    if (pwd.localeCompare(repwd) !== 0) {
        printError(error, `${pwdField} và ${repwdField} không trùng khớp`);
        return false;
    } else {
        hideError(error);
        return true;
    }
}

/**
 * Hide update form of profile
 * Open content form of profile
 */
function closeUpdate() {
    if(confirm("Bạn có chắc muốn hủy bản chỉnh sửa?")) {
        document.getElementById('profile_content').style.display = "block";
        document.getElementById('profile_update').style.display = "none";
    }
}

/**
 * Open update form of profile
 * Hide content form of profile
 */
function openUpdate() {
    document.getElementById('profile_content').style.display = 'none';
    document.getElementById('profile_update').style.display = 'block';
}

/**
 * Validate Signin Form's fields
 * If they are not valid
 * print error and return false
 *
 * @returns boolean value
 */
function validateSigninForm() {
    const EMAIL = document.signinForm.email.value;
    const PWD = document.signinForm.pwd.value;

    let isValid = 0;

    //check if email is valid
    //if it is not, increase "isValid"
    if (!validateRequiredField(EMAIL, "email-error", "Email",
                emailPattern, invalidMessage ("Email"))) { isValid++; }

    //check if password is valid
    //if it is not, increase "isValid"
    if (!validateRequiredField(PWD, "password-error", "Mật khẩu",
                passwordPattern, passwordMessage)) { isValid++; }

    //check if all form's fields is valid based on "isValid"
    //if it equals 0 which meaning is valid, return true; else return false
    if (isValid !== 0) { return false; }
    else { return true; }
}

/**
* Validate Register Form's fields
 * If they are not valid
 * print error and return false
 *
 * @returns boolean value
 */
function validateRegisterForm() {
    const EMAIL = document.signupForm.email.value;
    const PWD = document.signupForm.pwd.value;
    const RE_PWD = document.signupForm.repwd.value;

    let isValid = 0;

    //check if email is valid
    //if it is not, increase "isValid"
    if (!validateRequiredField(EMAIL, "email-error", "Email",
                emailPattern, invalidMessage ("Email"))) { isValid++; }

    //check if password is valid
    //if it is not, increase "isValid"
    if (!validateRequiredField(PWD, "password-error", "Mật khẩu",
                passwordPattern, passwordMessage)) { isValid++; }

    //check if re-password is valid
    //if it is not, increase "isValid"
    if (!validateRequiredField(RE_PWD, "repassword-error", "Mật khẩu đã nhập phía trên",
                passwordPattern, passwordMessage)) { isValid++; }
    else {
        //check if password and re-password are same
        //if it is not, increase "isValid"
        if (!checkSamePassword(PWD, RE_PWD, "Mật khẩu", "Mật khẩu xác nhận")) { isValid++; }
    }

    //check if all form's fields is valid based on "isValid"
    //if it equals 0 which meaning is valid, return true; else return false
    if (isValid !== 0) { return false; }
    else { return true; }

}

/**
 * Validate Change-Password Form's Fields
 * If they are not valid
 * print error and return false
 *
 * @returns boolean value
 */
function validateChangepwdForm () {
    const CURRENT_PWD = document.changepwdForm.current_pwd.value;
    const NEW_PWD = document.changepwdForm.new_pwd.value;
    const CONFIRM_PWD = document.changepwdForm.confirm_pwd.value;

    let isValid = 0;

    //check if current password is valid
    //if it is not, increase "isValid"
    if (!validateRequiredField(CURRENT_PWD, "currentpwd-error", "Mật khẩu hiện tại",
                passwordPattern, passwordMessage)) { isValid++; }

    //check if new password is valid
    //if it is not, increase "isValid"
    if (!validateRequiredField(NEW_PWD, "newpwd-error", "Mật khẩu mới",
                passwordPattern, passwordMessage)) { isValid++; }

    //check if confirm password is valid
    //if it is not, increase "isValid"
    if (!validateRequiredField(CONFIRM_PWD, "repassword-error", "Mật khẩu xác nhận",
                passwordPattern, passwordMessage)) { isValid++; }

    else
        //if confirm password is valid,
        //then check if new password and confirm password are same
        //if they are not, increase "isValid"
        if (!checkSamePassword(NEW_PWD, CONFIRM_PWD, "Mật khẩu mới", "Mật khẩu xác nhận")) { isValid++; }


    //check if all form's fields is valid based on "isValid"
    //if it equals 0 which meaning is valid, return true; else return false
    if (isValid !== 0) { return false; }
    else { return true; }
}

/**
 * Validate Update Profile Form's Fields
 * If they are not valid
 * print error and return false
 *
 * @returns boolean value
 */
function validateUpdateProfileForm() {
    const NAME = document.profileForm.name.value;
    const EMAIL = document.profileForm.email.value;
    const PHONE = document.profileForm.phone.value;
    const BIRTH_DATE = document.profileForm.birthday.value;
    const PROVINCE = document.profileForm.province.value;
    const DISTRICT = document.profileForm.district.value;
    const WARD = document.profileForm.ward.value;
    const DETAIL_LOCATION = document.profileForm.detailLocation.value;

    let isValid = 0;

    if (!validateUnrequiredField(NAME, "name-error", "Tên", textPattern)) { isValid++; }
    if (!validateRequiredField(EMAIL, "email-error", "Email", emailPattern, invalidMessage ("Email"))) { isValid++; }
    if (!validateUnrequiredField(PHONE, "phone-error", "Số điện thoại", phonePattern)) { isValid++; }
    if (!validateUnrequiredField(PROVINCE, "province-error", "Thành phố/Tỉnh", textPattern)) { isValid++; }
    if (!validateUnrequiredField(DISTRICT, "district-error", "Quận/Huyện", textPattern)) { isValid++; }
    if (!validateUnrequiredField(WARD, "ward-error", "Xã/Phường", textPattern)) { isValid++; }
    if (!validateUnrequiredField(DETAIL_LOCATION, "detaillocation-error", "Địa chỉ cụ thể", textPattern)) { isValid++; }

    if (isValid !== 0) { return false; }
    else { return true; }
}

/**
 * Validate order tracking form's phone field
 *
 * @returns boolean value
 */
function validateOrderSearch () {
    const PHONE = document.orderSearch.phone.value;

    if (!validateRequiredField(PHONE, "phone-error", "Số điện thoại",
                phonePattern, "Số điện thoại của bạn không hợp lệ")) { return false; }

    return true;
}

/**
 * Validate Create Order Form's fields
 *
 * @returns boolean value
 */
function validateCreateOrderForm () {
    const NAME = document.createorderForm.name.value;
    const PHONE = document.createorderForm.phone.value;
    const PROVINCE = document.createorderForm.province.value;
    const DISTRICT = document.createorderForm.district.value;
    const WARD = document.createorderForm.ward.value;
    const DETAIL_LOCATION = document.createorderForm.detailLocation.value;

    let isValid = 0;

    if (!validateRequiredField(NAME, "name-error", "Tên", textPattern, invalidMessage ("Tên"))) { isValid++; }
    if (!validateRequiredField(PHONE, "phone-error", "Số điện thoại",
                    phonePattern, invalidMessage ("Số điện thoại"))) { isValid++; }
    if (!validateRequiredField(PROVINCE, "province-error", "Thành phố/Tỉnh",
                    textPattern, invalidMessage ("Thành phố/Tỉnh"))) { isValid++; }
    if (!validateRequiredField(DISTRICT, "district-error", "Quận/Huyện",
                    textPattern, invalidMessage ("Quận/Huyện"))) { isValid++; }
    if (!validateRequiredField(WARD, "ward-error", "Xã/Phường",
                    textPattern, invalidMessage ("Xã/Phường"))) { isValid++; }
    if (!validateRequiredField(DETAIL_LOCATION, "detailLocation-error", "Địa chỉ cụ thể",
                    textPattern, invalidMessage ("Địa chỉ cụ thể"))) { isValid++; }

    if (isValid !== 0) { return false; }
    else { return true; }
}