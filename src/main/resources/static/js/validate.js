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