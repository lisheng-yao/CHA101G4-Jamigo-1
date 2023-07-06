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
let inputText_error = document.querySelector('.form-item-text .error-text span');
let inputStartDate_error = document.querySelector('.form-item-startDate .error-text span');
let inputStartTime_error = document.querySelector('.form-item-startTime .error-text span');
let inputEndDate_error = document.querySelector('.form-item-endDate .error-text span');
let inputEndTime_error = document.querySelector('.form-item-endTime .error-text span');
let img_data_error = document.querySelector('.carousel-img-content .error-text span');

let carousel_upfile_form = document.querySelector(".carousel-img-upfile-box form");
let carousel_upfile_imgInputBtn = document.querySelector(".carousel-img-upfile-btn");
let carousel_upfile_imgUpInput = document.querySelector(".carousel-img-upfile-box #carousel-img-upfile-input");
let carousel_upfile_info = document.querySelector(".carousel-img-upfile-info");
let carousel_insertFile_btn = document.querySelector(".button-group .carousel-img-insertFile-btn");
let carousel_editFile_btn = document.querySelector(".button-group .carousel-img-editFile-btn");

let currentCounterNo = localStorage.getItem('counterNo');

// 裝formData用的
let img_data;
// ------------------------------------------------------------------------------------------------
console.log(currentCounterNo);
// 新增輪播圖
carousel_insertFile_btn?.addEventListener('click', async () => {
	// 檢查欄位
	let inputText_flag = inputText_reject();
	let endDateTime_flag = endDateTime_reject(inputEndDate.value, inputEndTime.value);
	let inputStartDate_flag = inputDateTime_reject(inputStartDate);
	let inputStartTime_flag = inputDateTime_reject(inputStartTime);
	let inputEndDate_flag = inputDateTime_reject(inputEndDate);
	let inputEndTime_flag = inputDateTime_reject(inputEndTime);
	insertImg_reject();

	// 確認欄位
	if(inputText_flag && endDateTime_flag && inputStartDate_flag && inputStartTime_flag && inputEndDate_flag && inputEndTime_flag && (img_data ?? false))
		insertCarousel();
})

// 新增輪播圖
function insertCarousel() {
	Swal.fire({
		title: '確定新增輪播圖?',
		// text: "You won't be able to revert this!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: '確定新增',
		cancelButtonText: "取消"
	})
	.then(async result => {
		if(result.isConfirmed) {
			console.log('傳送1')
			// 第一次請求
			await axios.post('/Jamigo/counterCarousel/insert', {
				counterNo: currentCounterNo,
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
			.then(response => {
				Swal.fire({
					icon: 'success',
					title: '新增成功',
					text: '輪播資料新增成功!',
					confirmButtonText: "確認"
				})
				.then(() => window.location.href="./counter_carouselCtrl.html")
				})
				.catch(error => {
				Swal.fire({
					icon: 'error',
					title: '新增失敗',
					text: '輪播資料新增失敗!',
				})
			})
		}
	})
}


// 修改輪播圖
carousel_editFile_btn?.addEventListener('click', async () => {
	// 檢查欄位
	let inputText_flag = inputText_reject();
	let endDateTime_flag = endDateTime_reject(inputEndDate.value, inputEndTime.value);
	let inputStartDate_flag = inputDateTime_reject(inputStartDate);
	let inputStartTime_flag = inputDateTime_reject(inputStartTime);
	let inputEndDate_flag = inputDateTime_reject(inputEndDate);
	let inputEndTime_flag = inputDateTime_reject(inputEndTime);
	
	// 確認欄位
	if(inputText_flag && endDateTime_flag && inputStartDate_flag && inputStartTime_flag && inputEndDate_flag && inputEndTime_flag)
		updateCarousel();
})

// 修改輪播圖方法
async function updateCarousel() {
	Swal.fire({
		title: '確定修改輪播圖?',
		// text: "You won't be able to revert this!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: '確定修改',
		cancelButtonText: "取消"
	})
	.then(async result => {
		if(result.isConfirmed) {
			console.log('傳送1')
			// 第一次請求，送出文字內容
			await axios.post('/Jamigo/counterCarousel/update', {
				counterCarouselNo: inputNo.value,
				counterNo: currentCounterNo,
				counterCarouselText: inputText.value,
				counterCarouselStartTime: `${inputStartDate.value} ${inputStartTime.value}:00`,
				counterCarouselEndTime: `${inputEndDate.value} ${inputEndTime.value}:00`,
			}, { headers : {
					'Content-Type' : 'application/json'
				}
			})
			.then(resp => {
				console.log('post1' + resp)
				return resp.data;
			})
			.then(async data => {
				// 第二次請求，送出二進位內容
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
			.then(response => {
				Swal.fire({
					icon: 'success',
					title: '修改成功',
					text: '輪播資料修改成功!',
					confirmButtonText: "確認"
				})
			.then(() => window.location.href="./counter_carouselCtrl.html")
			})
			.catch(error => {
				Swal.fire({
					icon: 'error',
					title: '修改失敗',
					text: '輪播資料修改失敗!',
				})
			})
		}
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
	// 圖片是否為空判斷
	insertImg_reject();
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

	// 圖片是否為空判斷
	insertImg_reject();
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

// 時間限制
let utcTime = new Date();
function formatDate(date) {
	var year = date.getFullYear();
	var month = (date.getMonth() + 1).toString().padStart(2, '0');
	var day = date.getDate().toString().padStart(2, '0');
	
	return year + '-' + month + '-' + day;
}
function formatTime(time) {
	var hour = time.getHours().toString().padStart(2, '0');
  var minute = time.getMinutes().toString().padStart(2, '0');
  
  return hour + ':' + minute;
}

inputStartDate.setAttribute('min', formatDate(utcTime));
inputStartTime.setAttribute('min', formatDate(utcTime));

// 錯誤檢查

// 檢查輪播文字是否不為空
inputText.addEventListener('blur', () => {
	inputText_reject();
});
function inputText_reject() {
	let flag = true;
	let error_text = '';
	let text = inputText.value;
	if(text == null || text.trim() == 0) {
		flag = false;
		error_text = '請輸入輪播文字';
	} 

	error_text_controll(flag, inputText_error, error_text);
	return inputText
}

// 開始日期判斷
inputStartDate.addEventListener('blur', () => {
	inputDateTime_reject(inputStartDate);
	inputEndDate.setAttribute('min', inputStartDate.value);
});

// 開始時間判斷
inputStartTime.addEventListener('blur', () => {
	inputDateTime_reject(inputStartTime);
});

// 結束日期判斷
inputEndDate.addEventListener('blur', () => {
	inputDateTime_reject(inputEndDate);
});

// 結束時間判斷
inputEndTime.addEventListener('blur', () => {
	inputDateTime_reject(inputEndTime);
});

// 日期時間的input為空判斷
function inputDateTime_reject(inputDateTime) {
	let flag = true;
	let error_text = '';
	let value = inputDateTime.value;
    let alertText = inputDateTime.nextElementSibling.innerText;
	if(value == null || value.trim() == 0) {
		flag = false;
		error_text = `請選擇${alertText}`;
	} 
	let inputParent = inputDateTime.parentElement;
	let error_box = inputParent.nextElementSibling;
	let inputDateTime_error = error_box.lastElementChild;
	error_text_controll(flag, inputDateTime_error, error_text);
	return flag;
}

// 判斷結束日期時間是不是比開始還早
function endDateTime_reject(endDate, endTime) {
	let flag = true;
	let error_text = '';
	let startDateTime = new Date(`${inputStartDate.value} ${inputStartTime.value}`)
	let endDateTime = new Date(`${endDate} ${endTime}`);
	console.log(startDateTime);
	console.log(endDateTime);
	console.log(startDateTime > endDateTime);
	if(endDateTime < startDateTime) {
		flag = false;
		error_text = '結束時間不能比開始時間早'
	}
	error_text_controll(flag, inputEndDate_error, error_text);
	error_text_controll(flag, inputEndTime_error, error_text);
	return flag;
}
function insertImg_reject() {
	// console.log('圖片判斷');
	let flag = true;
	let error_text = '';
	// console.log('img_data ?? true: ' + img_data ?? true);
	if(!(img_data ?? false)) {
		// console.log('沒有圖片');
		flag = false;
		error_text = '需選取上傳圖片'
	}
	error_text_controll(flag, img_data_error, error_text)
	return flag;
}

// 檢查flag判斷要添加錯誤訊息還是移除錯誤訊息
function error_text_controll(flag, item, error_text) {
	let itemParent = item.parentElement;
	console.log('rrrrrrrrrrr');
	console.log(itemParent);
	if(flag) {
	  itemParent.parentElement.classList.remove('show');
	} else {
	  item.innerText = error_text;
	  itemParent.parentElement.classList.add('show');
	}
  }