let sure_btn = document.querySelector('.sure-btn');
let revise_btn = document.querySelector('.revise-btn');

let form_items = document.querySelectorAll('.form-item');
let inputNo = document.querySelector('.form-label #inputNo');
let inputCounterNo = document.querySelector('#inputCounterNo');
let inputText = document.querySelector('.form-label #inputText');
let inputStartDate = document.querySelector('.form-label #inputStartDate');
let inputStartTime = document.querySelector('.form-label #inputStartTime');
let inputEndDate = document.querySelector('.form-label #inputEndDate');
let inputEndTime = document.querySelector('.form-label #inputEndTime');

let carousel_upfile_form = document.querySelector(".carousel-img-upfile-box form");
let carousel_upfile_imgInputBtn = document.querySelector(".carousel-img-upfile-btn");
let carousel_upfile_imgUpInput = document.querySelector(".carousel-img-upfile-box #carousel-img-upfile-input");
let carousel_upfile_info = document.querySelector(".carousel-img-upfile-info");
let carousel_insertFile_btn = document.querySelector(".button-group .carousel-img-insertFile-btn");
let carousel_editFile_btn = document.querySelector(".button-group .carousel-img-editFile-btn");

// 裝formData用的
let img_data;
// ------------------------------------------------------------------------------------------------

// 取得櫃位所有的輪播圖片資訊



// 新增輪播圖
if(carousel_insertFile_btn != null) {
	
	carousel_insertFile_btn.addEventListener('click', async () => {
		console.log('傳送1')
		// 第一次請求
		await axios.post('/Jamigo/counterCarousel/insert', {
			counterNo: inputCounterNo.value,
			counterCarouselText: inputText.value,
			counterCarouselStartTime: `${inputStartDate.value} ${inputStartTime.value}:00`,
			counterCarouselEndTime: `${inputEndDate.value} ${inputEndTime.value}:00`,
		}, {
			headers : {
				'Content-Type' : 'application/json'
			}
		})
		.then(resp => {
			console.log('post1' + resp)
			return resp.data;
		})
		.then(async data => {
			// 第二次請求
				let formData = new FormData();
				console.log(img_data);
				formData.append('counterCarouselPic', img_data);
				formData.append('counterCarouselNo', data);
				console.log('傳送2');
				console.log('data' + data);
				
				await axios.post('/Jamigo/counterCarousel/insertImg', formData, {
					headers : {
						'Content-Type' : 'multipart/form-data'
					}
				})
				.then(resp => {
					console.log('post1' + resp)
				})
				.catch(err => {
					console.log('post1' + err)
				})
		})
		.catch(err => {
			console.log('post1' + err)
		})
	})
}


// 修改輪播圖
if(carousel_editFile_btn != null) {
	
	carousel_editFile_btn.addEventListener('click', async () => {
		
		console.log('傳送1')
		// 第一次請求
		await axios.post('/Jamigo/counterCarousel/update', {
			counterCarouselNo: inputNo.value,
			counterNo: inputCounterNo.value,
			counterCarouselText: inputText.value,
			counterCarouselStartTime: `${inputStartDate.value} ${inputStartTime.value}:00`,
			counterCarouselEndTime: `${inputEndDate.value} ${inputEndTime.value}:00`,
		}, {
			headers : {
				'Content-Type' : 'application/json'
			}
		})
		.then(resp => {
			console.log('post1' + resp)
			return resp.data;
		})
		.then(async data => {
			// 第二次請求
			if(img_data != null) {
				let formData = new FormData();
				console.log(img_data);
				formData.append('counterCarouselPic', img_data);
				formData.append('counterCarouselNo', data);
				console.log('傳送2');
				console.log('data' + data);
				
				await axios.post('/Jamigo/counterCarousel/insertImg', formData, {
					headers : {
						'Content-Type' : 'multipart/form-data'
					}
				})
				.then(resp => {
					console.log('post1' + resp)
				})
				.catch(err => {
					console.log('post1' + err)
				})
			}
		})
		.catch(err => {
			console.log('post1' + err)
		})
	})
}


// 允許接受拖曳，且拖曳經過時加.drag
carousel_upfile_form.addEventListener('dragover', e => {
	e.preventDefault();
	carousel_upfile_form.classList.add("drag");
})
// 拖曳出來時加.drag
carousel_upfile_form.addEventListener('dragleave', e => 
	carousel_upfile_form.classList.remove("drag"))
// 拖曳進來後移除.drag，並且產生預覽圖
carousel_upfile_form.addEventListener('drop', e => {
	e.preventDefault();
	carousel_upfile_form.classList.remove("drag");
	
	
	getPrevImg(e.dataTransfer.files[0]);
	
//	img_data = e.dataTransfer.files[0];
})

// 抓取input的預覽圖
carousel_upfile_imgUpInput.addEventListener('change', () => {
	if(carousel_upfile_imgUpInput.files.length > 0) {
		getPrevImg(carousel_upfile_imgUpInput.files[0]);
	} else {
		let html = 
		 `<div class="carousel-img-upfile-icon">
	          <i class="fa-solid fa-cloud-arrow-up"></i>
	      </div>
	      <div class="carousel-img-upfile">
	          <div class="carousel-img-upfile-text">
	              <span>請將檔案拖曳至此處上傳，或</span>
	          </div>
	          <label class="carousel-img-upfile-btn" for="carousel-img-upfile-input">選擇檔案</label>
	          <input type="file" name="" id="carousel-img-upfile-input">
	      </div>`;
       carousel_upfile_info.innerHTML = html;
	}
})

// 顯示預覽圖
function getPrevImg(file){
	
	let reader64 = new FileReader();
	reader64.readAsDataURL(file);
	reader64.addEventListener('load', () => {
		
		let html = 
		`<div class="carousel-img-upfile-upText">
           <div class="carousel-img-upfile-label">輪播圖片預覽</div>
         </div>
         <div class="carousel-img-upfile-upImg">
         	<img src="${reader64.result}" alt="輪播預覽圖">
         </div>`;
		         
		carousel_upfile_info.innerHTML = html;
	
	})
	
	img_data = file;
}


// 檢查query中是否有值，有值代表為修改，要將櫃位原本數值放進input內
getQueryString();
function getQueryString() {
  let urlParam = new URLSearchParams(window.location.search);
  let queryValue = urlParam.get('counterCarouselNo');

  if(queryValue != null) {
    axios.get(`/Jamigo/counterCarousel/getByIdWithoutPic/${queryValue}`)
    .then(response => { return response.data })
    .then(data => {
		const [startDate, startTime] = data.counterCarouselStartTime.split(' ');
		const [endDate, endTime] = data.counterCarouselEndTime.split(' ');
        inputNo.value = data.counterCarouselNo;
        inputCounterNo.value = data.counterNo;
        inputText.value = (data.counterCarouselText ? data.counterCarouselText : '');
        inputStartDate.value = startDate;
        inputStartTime.value = startTime;
        inputEndDate.value = endDate;
        inputEndTime.value = endTime;
        document.querySelector('.carousel-img-upfile-originPic img').src = `/Jamigo/counterCarouselImg/${data.counterCarouselNo}`;
    })
    .catch(error => console.log(error))
  }
}




// ------------------------------------------------------------------------------------------------
/*
// 自定義的select套用點選附值事件
//getSelectVersion();
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
			    .then(() => window.location.href="/Jamigo/counter/ctrl/view/counter_accountCtrl.html")
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
	      .then(() => window.location.href="/Jamigo/counter/ctrl/view/counter_accountCtrl.html")
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
// 檢查要新增或修改的帳號是否有重複
inputAccount.addEventListener('blur', async () => inputAccount_reject());
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


// 檢查抽成數字是否介於0~1之間
inputCutPercent.addEventListener('blur', () => inputCutPercent_reject());
function inputCutPercent_reject() {
  let flag = true;
  let error_text = '';
  let percent = inputCutPercent.value;

  if(percent == null && percent.trim() == 0) {
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
*/