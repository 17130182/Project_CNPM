//btn submit
let conditionForm = [];
const btnSubmit = document.getElementById('btn-submit');
btnSubmit.addEventListener('click', (e) => {
  checkFullName(),
    checkEmail(),
    checkUserName(),
    checkPassword(),
    checkPhone(),
    checkDate(),
    checkStatus(),
    checkRole();
  let tmpCondition = true;
  conditionForm.forEach((item) => {
    if (item === false) {
      tmpCondition = false;
    }
  });
  if (conditionForm.length < 8 || !tmpCondition) {
    e.preventDefault();
  }
});
//name
const fullName = document.getElementById('input-1');
fullName.addEventListener('focusout', () => {
  checkFullName();
});
function checkFullName() {
  const data = fullName.value;
  const condition = data.split(' ').filter((item) => item.trim() !== '').length;
  const parent = fullName.parentElement;
  if (condition < 2) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Vui lòng nhập đúng tên';
    textError.classList.add('error__message');
    if (parent.childElementCount === 2) {
      parent.appendChild(textError);
    } else {
      parent.replaceChild(textError, parent.lastChild);
    }
    conditionForm[0] = false;
  } else {
    conditionForm[0] = true;
    parent.classList.remove('error');
    if (parent.childElementCount === 3) {
      parent.removeChild(parent.lastChild);
    }
  }
}
//email
const email = document.getElementById('input-2');
email.addEventListener('focusout', (e) => {
  checkEmail();
});
function checkEmail() {
  const data = email.value;
  const parent = email.parentElement;
  if (!validateEmail(data)) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Vui lòng nhập đúng email';
    textError.classList.add('error__message');
    if (parent.childElementCount === 2) {
      parent.appendChild(textError);
    } else {
      parent.replaceChild(textError, parent.lastChild);
    }
    conditionForm[1] = false;
  } else {
    conditionForm[1] = true;
    parent.classList.remove('error');
    if (parent.childElementCount === 3) {
      parent.removeChild(parent.lastChild);
    }
  }
}
function validateEmail(email) {
  const re =
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(String(email).toLowerCase());
}
//numberPhone
const numberPhone = document.getElementById('input-3');
numberPhone.addEventListener('focusout', () => {
  checkPhone();
});
function checkPhone() {
  const data = numberPhone.value.trim();
  const parent = numberPhone.parentElement;
  if (!validatePhone(data)) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Vui lòng nhập đúng số điện thoại của bạn';
    textError.classList.add('error__message');
    if (parent.childElementCount === 2) {
      parent.appendChild(textError);
    } else {
      parent.replaceChild(textError, parent.lastChild);
    }
    conditionForm[2] = false;
  } else {
    conditionForm[2] = true;
    parent.classList.remove('error');
    if (parent.childElementCount === 3) {
      parent.removeChild(parent.lastChild);
    }
  }
}
function validatePhone(input) {
  const reg = /(84|0[3|5|7|8|9])+([0-9]{8})\b/;
  return reg.test(input);
}
//check user name
const username = document.getElementById('input-4');
username.addEventListener('focusout', () => {
  checkUserName();
});
function checkUserName() {
  const data = username.value.trim();
  const parent = username.parentElement;
  if (data.length <= 5) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Username phải dài hơn 5 kí tự';
    textError.classList.add('error__message');
    if (parent.childElementCount === 2) {
      parent.appendChild(textError);
    } else {
      parent.replaceChild(textError, parent.lastChild);
    }
    conditionForm[3] = false;
  } else {
    conditionForm[3] = true;
    parent.classList.remove('error');
    if (parent.childElementCount === 3) {
      parent.removeChild(parent.lastChild);
    }
  }
}
//check password
const password = document.getElementById('input-5');
password.addEventListener('focusout', () => {
  checkPassword();
});
function checkPassword() {
  const data = password.value.trim();
  const parent = password.parentElement;
  if (data.length <= 8) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Mật khẩu phải dài hơn 8 kí tự';
    textError.classList.add('error__message');
    if (parent.childElementCount === 2) {
      parent.appendChild(textError);
    } else {
      parent.replaceChild(textError, parent.lastChild);
    }
    conditionForm[4] = false;
  } else {
    conditionForm[4] = true;
    parent.classList.remove('error');
    if (parent.childElementCount === 3) {
      parent.removeChild(parent.lastChild);
    }
  }
}
//date
const theDate = document.getElementById('the-date');
theDate.addEventListener('focusout', () => {
  checkDate();
});
function checkDate() {
  const data = theDate.value.trim();
  const parent = theDate.parentElement;
  if (data.length <= 4) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Vui lòng chọn ngày sinh';
    textError.classList.add('error__message');
    if (parent.childElementCount === 2) {
      parent.appendChild(textError);
    } else {
      parent.replaceChild(textError, parent.lastChild);
    }
    conditionForm[5] = false;
  } else {
    conditionForm[5] = true;
    parent.classList.remove('error');
    if (parent.childElementCount === 3) {
      parent.removeChild(parent.lastChild);
    }
  }
}
//status
const status = document.getElementById('input-6');
status.addEventListener('focusout', () => {
  checkStatus();
});
function checkStatus() {
  const data = status.value.trim();
  const parent = status.parentElement;
  if (data.length < 1 || data < 0 || data > 1) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Vui lòng nhập đúng status';
    textError.classList.add('error__message');
    if (parent.childElementCount === 2) {
      parent.appendChild(textError);
    } else {
      parent.replaceChild(textError, parent.lastChild);
    }
    conditionForm[6] = false;
  } else {
    conditionForm[6] = true;
    parent.classList.remove('error');
    if (parent.childElementCount === 3) {
      parent.removeChild(parent.lastChild);
    }
  }
}
//role
const role = document.getElementById('input-7');
role.addEventListener('focusout', () => {
  checkRole();
});
function checkRole() {
  const data = role.value.trim();
  const parent = role.parentElement;
  if (data.length < 1 || data < 0 || data > 2) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Vui lòng nhập đúng quyền';
    textError.classList.add('error__message');
    if (parent.childElementCount === 2) {
      parent.appendChild(textError);
    } else {
      parent.replaceChild(textError, parent.lastChild);
    }
    conditionForm[7] = false;
  } else {
    conditionForm[7] = true;
    parent.classList.remove('error');
    if (parent.childElementCount === 3) {
      parent.removeChild(parent.lastChild);
    }
  }
}
