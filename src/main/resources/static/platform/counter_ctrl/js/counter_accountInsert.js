//  select欄位選取判斷
let form_option_group = document.querySelectorAll(".controll-box-form-select .form-option-item");
let selectState = document.querySelector("#selectState");

let sure_btn = document.querySelector('.sure-btn');
let revise_btn = document.querySelector('.revise-btn');

let form_items = document.querySelectorAll('.form-item');
let inputNo = document.querySelector('.form-label #inputNo');
let inputName = document.querySelector('.form-label #inputName');
let inputAccount = document.querySelector('.form-label #inputAccount');
let inputAccount_error = document.querySelector('.form-item-account .error-text span');
let inputCutPercent = document.querySelector('.form-label #inputCutPercent');
let inputCutPercent_error = document.querySelector('.form-item-percent .error-text span');

let form_select = document.querySelector('.form-select');
let form_options = document.querySelectorAll('.form-select option');

let inputAccount_flag = 1;
let inputCutPercent_flag = 1;


// 自定義的select套用點選附值事件
getSelectVersion();
function getSelectVersion() {
  for(let form_option_item of form_option_group) {
    form_option_item.addEventListener('click', e => {
      let html = ``;
      let data_value = e.target.classList.contains('form-option-item') ? e.target.getAttribute('data-value') : e.target.parentElement.getAttribute('data-value');
      // console.log(e.target);
      switch (data_value) {
        case '0':
          html = `<span class="label label-notEnabled">
            <i class="fa-solid fa-hourglass-half"></i>
            帳號未啟用
            </span>`;
          break;
        case '1':
          html = `<span class="label label-success">
            <i class="fa-solid fa-circle-check"></i>
            帳號啟用
            </span>`;
          break;
        case '2':
          html = `<span class="label label-danger">
            <i class="fa-solid fa-circle-exclamation"></i>
            帳號停用
            </span>`;
          break;
        default:
          html = '111';
          break
      }
      selectState.innerHTML = html;
      selectState.setAttribute('data-value', data_value);
      form_select.value = data_value;
      // console.log(form_select.value);
    })
  }
}

// 檢查query中是否有值，有值代表為修改，要將櫃位原本數值放進input內
getQueryString();
function getQueryString() {
  let urlParam = new URLSearchParams(window.location.search);
  let queryValue = urlParam.get('counterNo');
  // console.log('queryValue: ' + queryValue);
  let counter = ``;

  if(queryValue != null) {
    let url = `/Jamigo/counterCtrl/getCounterById/${queryValue}`;
    fetch(url, {
      method : 'GET',
      headers : {
        'Content-Type' : 'text/plain',
        'Header-Action': 'getCounterById'
      }
    })
    .then(response => { return response.json() })
    .then(data => {
      counter = data;
      inputNo.value = counter.counterNo;
      console.log(counter.counterName);
      inputName.value = (counter.counterName ? counter.counterName : '預設');
      inputAccount.value = counter.counterAccount;
      inputCutPercent.value = counter.cutPercent;
      form_option_group[counter.counterStat].firstElementChild.click();
    })
    .catch(error => console.log(error))
  }
}

// 新增櫃位
if(sure_btn) {
  sure_btn.addEventListener('click', () => {
	  let sure_btn_promise = new Promise(async (resolve, reject) => {
		await inputAccount_reject();
		inputCutPercent_reject();
        resolve();
	  });
	  sure_btn_promise.then(() => {
  	   if(inputAccount_flag && inputCutPercent_flag) {
		  insertCounter();
	    }
	  })
   })
}

// 確認是否新增櫃位的彈窗訊息
// + 送出修改資料到後端
function insertCounter(){ 
	Swal.fire({
	    title: '確定新增櫃位?',
	    // text: "You won't be able to revert this!",
	    icon: 'warning',
	    showCancelButton: true,
	    confirmButtonColor: '#3085d6',
	    cancelButtonColor: '#d33',
	    confirmButtonText: '確定新增',
	    cancelButtonText: "取消"
	}).then((result) => {
		if(result.isConfirmed) {
			let data_value = selectState.getAttribute('data-value');
		    let counter_insert = {
		      'counterNo' : 0,
		      'cutPercent' : inputCutPercent.value,
		      'counterAccount' : inputAccount.value,
		      'counterPassword' : 'default1234',
		      'counterStat' : data_value
		    }
		  
		    let url = '/Jamigo/counterCtrl/insert';
		    fetch(url, {
		      method : 'POST',
		      headers : {
		        'Content-Type' : 'application/json',
		        'Header-Action' : 'insert'
		      },
		      body : JSON.stringify(counter_insert)
		    })
		    .then(response => {
			    Swal.fire({
			      icon: 'success',
			      title: '新增成功',
			      text: '櫃位資料新增成功!',
			      confirmButtonText: "確認"
				})
			    .then(() => window.location.href="./counter_accountCtrl.html")
			})
		    .catch(error => {
		      Swal.fire({
		        icon: 'error',
		        title: '新增失敗',
		        text: '櫃位資料新增失敗!',
		      })
		    })
		}
	})
}
// 修改櫃位
if(revise_btn) {
  revise_btn.addEventListener('click', () => {
	  let revise_btn_promise = new Promise(async (resolve, reject) => {
		await inputAccount_reject();
		inputCutPercent_reject();
        resolve();
	  });
	  revise_btn_promise.then(() => {
  	   if(inputAccount_flag && inputCutPercent_flag) {
		  editCounter();
	    }  
	  })
  })

}

// 確認是否修改櫃位的彈窗訊息
// + 送出修改資料到後端
function editCounter() {
  Swal.fire({
    title: '確定修改櫃位?',
    // text: "You won't be able to revert this!",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: '確定修改',
    cancelButtonText: "取消"
  }).then((result) => {
	  if(result.isConfirmed) {
	    let data_value = selectState.getAttribute('data-value');
	    let counter_update = {
	      'counterNo' : inputNo.value,
	      'cutPercent' : inputCutPercent.value,
	      'counterAccount' : inputAccount.value,
	      'counterPassword' : '',
	      'counterStat' : data_value
	    }
	  
	    let url = '/Jamigo/counterCtrl/update';
	    fetch(url, {
	      method : 'POST',
	      headers : {
	        'Content-Type' : 'application/json',
	        'Header-Action' : 'update'
	      },
	      body : JSON.stringify(counter_update)
	    })
	    .then(response => {
	      Swal.fire({
	        icon: 'success',
	        title: '修改成功',
	        text: '櫃位資料修改成功!',
	        confirmButtonText: "確認"
	      })
	      .then(() => window.location.href="./counter_accountCtrl.html")
	    })
	    .catch(error => {
	      Swal.fire({
	        icon: 'error',
	        title: '新增失敗',
	        text: '櫃位資料修改失敗!',
	      })
	    })
	  }
  })
}

inputAccount.addEventListener('blur', async () => inputAccount_reject());

// 檢查要新增或修改的帳號是否有重複
async function inputAccount_reject(){
  let flag = true;
  let error_text = '';
  let account = inputAccount.value;
  // 若inputNo無值為新增判斷，若inputNo有值為修改判斷
  inputNo ?	url = `/Jamigo/counterCtrl/findByAccount/${inputNo.value}/${account}` : url = `/Jamigo/counterCtrl/findByAccount/${account}`;

  if(account == null && account.trim() == 0) {
    flag = false;
      error_text = '請填寫帳號';
  }

  console.log(url);
  await fetch(url, {
    method : 'POST',
    headers : {
      'Content-Type' : 'text/plain',
      'Header-Action' : 'findByAccount',
      'Header-Account' : account
    }
  })
  .then(resp => { return resp.text() })
  .then(data => {
    if(data == 'true'){
      flag = true;
    } else if (data == 'false') {
      flag = false;
      error_text = '帳號已存在，請重新命名';
    } else {
      console.log(55555);
    }
    error_text_controll(flag, inputAccount_error, error_text);
  
  	inputAccount_flag = flag;
  })
  .catch(error => console.log(error));
}

inputCutPercent.addEventListener('blur', () => inputCutPercent_reject());

// 檢查抽成數字是否介於0~1之間
function inputCutPercent_reject() {
  let flag = true;
  let error_text = '';
  let percent = inputCutPercent.value;

  if(percent == null || percent.trim() == 0) {
    flag = false;
    error_text = '請輸入抽成比例';
  } else if (!(percent > 0 && percent < 1)) {
    flag = false;
    error_text = '抽成比例需介於0~1之間，請輸入正確的抽成比例';
  } else {
    flag = true;
  }

  error_text_controll(flag, inputCutPercent_error, error_text);
  inputCutPercent_flag = flag;
}

// 檢查flag判斷要添加錯誤訊息還是移除錯誤訊息
function error_text_controll(flag, item, error_text) {
  let itemParent = item.parentElement;
  if(flag) {
    itemParent.parentElement.classList.remove('show');
  } else {
    item.innerText = error_text;
    itemParent.parentElement.classList.add('show');
  }
}