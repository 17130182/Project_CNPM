//btn submit
let conditionForm = [];
const btnSubmit = document.getElementById('btn-submit');
btnSubmit.addEventListener('click', (e) => {
  checkNameProduct(),
    checkCategory(),
    checkContent(),
    checkDescription(),
    checkPrice(),
    checkDate(),
    checkDiscount();
  let tmpCondition = true;
  conditionForm.forEach((item) => {
    if (item === false) {
      tmpCondition = false;
    }
  });
  if (conditionForm.length < 7 || !tmpCondition) {
    e.preventDefault();
  }
});

//nameProduct
const nameProduct = document.getElementById('input-1');
nameProduct.addEventListener('focusout', () => {
  checkNameProduct();
});
function checkNameProduct() {
  const data = nameProduct.value;
  const condition = data.trim().length;
  const parent = nameProduct.parentElement;
  if (condition <= 0) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Vui lòng không để trống tên sản phẩm';
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
//category
const category = document.getElementById('input-2');
category.addEventListener('focusout', (e) => {
  checkCategory();
});
function checkCategory() {
  const data = category.value.trim();
  const parent = category.parentElement;
  if (!Number.parseInt(data) || data === '' || Number.parseInt(data) < 1) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Vui lòng nhập đúng danh mục';
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
//price
const price = document.getElementById('input-3');
price.addEventListener('focusout', () => {
  checkPrice();
});
function checkPrice() {
  const data = price.value.trim();
  const parent = price.parentElement;
  if (!Number.parseInt(data) || data === '' || Number.parseInt(data) < 1000) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Vui lòng nhập giá tiền (nhỏ nhất 1000 VNĐ)';
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

function checkContent() {
  const content = document.getElementById('input-4');
  const data = content.value.trim();
  console.log(data);
  const parent = content.parentElement.parentElement;
  console.log(parent);
  if (data.length < 20) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = `Nội dung phải lơn hơn 20 kí tự. Bạn đã nhập ${data.length}`;
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
//check description

function checkDescription() {
  const description = document.getElementById('input-5');
  const data = description.value.trim();
  const parent = description.parentElement.parentElement;
  if (data.length < 20) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = `Mô tả phải lơn hơn 20 kí tự. Bạn đã nhập ${data.length}`;
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
//check content
getClassRich();
function getClassRich() {
  const abc = document.querySelectorAll('.richText-editor');
  if (abc.length === 0) {
    setInterval(getClassRich, 1000);
  } else {
    clearInterval(getClassRich);
    abc[0].addEventListener('focusout', () => {
      checkContent();
    });
    abc[1].addEventListener('focusout', () => {
      checkDescription();
    });
  }
}
//discount
const discount = document.getElementById('input-6');
discount.addEventListener('focusout', () => {
  checkDiscount();
});
function checkDiscount() {
  const data = discount.value.trim();
  console.log(data);
  const parent = discount.parentElement;
  if (!Number.parseInt(data) || data === '' || Number.parseInt(data) < 0) {
    parent.classList.add('error');
    const textError = document.createElement('span');
    textError.innerText = 'Vui lòng nhập giảm giá (0->100)%';
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
    textError.innerText = 'Vui lòng chọn ngày tạo';
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
