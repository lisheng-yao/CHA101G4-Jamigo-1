//  select欄位選取判斷
let form_option_group = document.querySelectorAll(".controll-box-form-select .form-option-item");
let controll_box_form_select = document.querySelector(".controll-box-form-select");
let selectState = document.querySelector("#selectState");

let sure_btn = document.querySelector('.sure-btn');
let revise_btn = document.querySelector('.revise-btn');

let form_items = document.querySelectorAll('.form-item');
let inputNo = document.querySelector('.form-label #inputNo');
let inputTime = document.querySelector('.form-label #inputTime');
let inputAttendee = document.querySelector('.form-label #inputAttendee');
let inputAttendeeMore = document.querySelector('.form-label #inputAttendeeMore');
let inputAttendeeMoreName = document.querySelector('.form-label #inputAttendeeMoreName');
let inputAttendeeMoreGenderMale = document.querySelector('.form-label #inputAttendeeMoreGenderMale');
let inputAttendeeMoreGenderFemale = document.querySelector('.form-label #inputAttendeeMoreGenderFemale');
let inputAttendeeMoreAge = document.querySelector('.form-label #inputAttendeeMoreAge');
let activity_attendee_info = document.querySelector('.activity-attendee');

let inputAttendee_error = document.querySelector('.form-item-attendee .error-text span');

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
        case 0:
          html = `<span class="label label-notEnabled">
            <i class="fa-solid fa-hourglass-half"></i>
            未付款
            </span>`;
          break;
        case '1':
        case 1:
          html = `<span class="label label-success">
            <i class="fa-solid fa-circle-check"></i>
            已完成付款
            </span>`;
          break;
        case '2':
        case 2:
          html = `<span class="label label-danger">
            <i class="fa-solid fa-circle-exclamation"></i>
            已取消
            </span>`;
          break;
        default:
          html = '111';
          break
      }
      selectState.innerHTML = html;
      selectState.setAttribute('data-value', data_value);
      form_select.value = data_value;
//       console.log(e.target);
      controll_box_form_select.classList.remove('hover');
    })
  }
}

// 檢查query中是否有值，有值代表為修改，要將活動訂單原本數值放進input內
getQueryString();
function getQueryString() {
  let urlParam = new URLSearchParams(window.location.search);
  let queryValue = urlParam.get('activityOrderNo');
  // console.log('queryValue: ' + queryValue);
  let activityOrder = ``;

  if(queryValue != null) {
    let url = `/Jamigo/activityOrder/getActivityOrderById/${queryValue}`;
    fetch(url, {
      method : 'GET',
      headers : {
        'Content-Type' : 'text/plain',
        'Header-Action': 'getactivityOrderById'
      }
    })
    .then(response => { return response.json() })
    .then(data => {
      activityOrder = data;
      inputNo.value = activityOrder.activityOrderNo;
      inputTime.value = activityOrder.activityEnrollmentTime;
      inputAttendeeMore.value = activityOrder.numberOfAttendee ? activityOrder.numberOfAttendee : '無';
      inputAttendee.value = activityOrder.numberOfAttendee ? activityOrder.numberOfAttendee + 1 : 1;
      if(activityOrder.activityAttendeeVO.length > 0){
		  autoCreateFormTitle();
	  }
      for(let i = 0; i < activityOrder.activityAttendeeVO.length; i++) {
		  autoCreateFormBody(i + 1, activityOrder.activityAttendeeVO[i]);
	  }
      form_option_group[activityOrder.activityPaymentStat].firstElementChild.click();
    })
    .catch(error => console.log(error))
  }
}

// 新增櫃位
// if(sure_btn) {
//   sure_btn.addEventListener('click', () => {
// 	  let sure_btn_promise = new Promise(async (resolve, reject) => {
// 		await inputAccount_reject();
// 		inputCutPercent_reject();
//         resolve();
// 	  });
// 	  sure_btn_promise.then(() => {
//   	   if(inputAccount_flag && inputCutPercent_flag) {
// 		  insertactivityOrder();
// 	    }
// 	  })
//    })
// }

// 確認是否新增活動訂單的彈窗訊息
// + 送出修改資料到後端
// function insertactivityOrder(){ 
// 	Swal.fire({
// 	    title: '確定新增櫃位?',
// 	    // text: "You won't be able to revert this!",
// 	    icon: 'warning',
// 	    showCancelButton: true,
// 	    confirmButtonColor: '#3085d6',
// 	    cancelButtonColor: '#d33',
// 	    confirmButtonText: '確定新增',
// 	    cancelButtonText: "取消"
// 	}).then((result) => {
// 		if(result.isConfirmed) {
// 			let data_value = selectState.getAttribute('data-value');
// 		    let activityOrder_insert = {
// 		      'activityOrderNo' : 0,
// 		      'cutPercent' : inputCutPercent.value,
// 		      'activityOrderAccount' : inputAccount.value,
// 		      'activityOrderPassword' : 'default1234',
// 		      'activityOrderStat' : data_value
// 		    }
		  
// 		    let url = '/Jamigo/activityOrder/insert';
// 		    fetch(url, {
// 		      method : 'POST',
// 		      headers : {
// 		        'Content-Type' : 'application/json',
// 		        'Header-Action' : 'insert'
// 		      },
// 		      body : JSON.stringify(activityOrder_insert)
// 		    })
// 		    .then(response => {
// 			    Swal.fire({
// 			      icon: 'success',
// 			      title: '新增成功',
// 			      text: '櫃位資料新增成功!',
// 			      confirmButtonText: "確認"
// 				})
// 			    .then(() => window.location.href="./activity_orderCtrl.html")
// 			})
// 		    .catch(error => {
// 		      Swal.fire({
// 		        icon: 'error',
// 		        title: '新增失敗',
// 		        text: '櫃位資料新增失敗!',
// 		      })
// 		    })
// 		}
// 	})
// }

// 修改活動訂單
if(revise_btn) {
  revise_btn.addEventListener('click', () => {
	  let revise_btn_promise = new Promise(async (resolve, reject) => {
		let inputAttendee_flag = await inputAttendee_reject();
		let attendeeNum = inputAttendee.value - 1;
		for(let i = 0; i < attendeeNum.length; i++) {
			let inputAttendeeMoreName_id = document.querySelector(`#inputAttendeeMoreName${i}`);
			let parent = inputAttendeeMoreName_id;
			let parentSibling = parent.nextElementSibling;
			let err = parentSibling.lastElementChild;
			inputAttendeeMoreName_reject(inputAttendeeMoreName_id, err)
			inputAttendee_reject();
			inputAttendeeMoreAge_reject(inputAttendeeMoreName_id, err);
		
		}
        resolve();
	  });
	  revise_btn_promise.then(() => {
		  editactivityOrder();
//  	   if(inputAccount_flag && inputCutPercent_flag) {
//	    }  
	  })
  })

}

// 確認是否修改活動訂單的彈窗訊息
// + 送出修改資料到後端
function editactivityOrder() {
  Swal.fire({
    title: '確定修改線下活動訂單?',
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
	    let activity_attendee_content = document.querySelectorAll('.activity-attendee-content');
	    let activityAttendeeArr = [];
	    for(let i = 0; i < activity_attendee_content.length; i++) {
			let item = {
				'attendeeNo' : document.querySelector(`#inputAttendeeMoreAttendeeNo${i + 1}`).value,
				'activityOrderNo' : inputNo.value,
				'attendeeName' : document.querySelector(`#inputAttendeeMoreName${i + 1}`).value,
				'attendeeGender' : document.querySelector(`#inputAttendeeMoreGenderMale${i + 1}`).checked ? 1 : 2,
				'attendeeAge' : document.querySelector(`#inputAttendeeMoreAge${i + 1}`).value ? document.querySelector(`#inputAttendeeMoreAge${i + 1}`).value : null
			}
			activityAttendeeArr.push(item);
		}
		
	    let activityOrder_update = {
			'activityOrderVO' : {
				'activityOrderNo' : inputNo.value,
	      		'numberOfAttendee' : inputAttendeeMore.value == '無' ? null : inputAttendeeMore.value,
	      		'activityPaymentStat' : data_value	
			},
			'activityAttendeeVOList' : activityAttendeeArr
	    }
	    console.log(activityOrder_update)
	    let url = '/Jamigo/activityOrder/updatePart';
	    fetch(url, {
	      method : 'PUT',
	      headers : {
	        'Content-Type' : 'application/json',
	        'Header-Action' : 'update'
	      },
	      body : JSON.stringify(activityOrder_update)
	    })
	    .then(response => {
	      Swal.fire({
	        icon: 'success',
	        title: '修改成功',
	        text: '線下活動訂單資料修改成功!',
	        confirmButtonText: "確認"
	      })
	      .then(() => window.location.href="./activity_orderCtrl.html")
	    })
	    .catch(error => {
	      Swal.fire({
	        icon: 'error',
	        title: '新增失敗',
	        text: '線下活動訂單資料修改失敗!',
	      })
	    })
	  }
  })
}

// 動態算出額外參加者人數
// 並動態產出額外參加者資料填寫表格
inputAttendee.addEventListener('blur', () => {
	// 創建一個新的 KeyboardEvent
	autoCreatForm();
});

inputAttendee.addEventListener('keyup', e => {
	if(e.code === 'Enter') autoCreatForm();	
})

// 動態算出額外參加者人數function
function autoCreatForm(){
		let inputAttendeeMoreNum = inputAttendee.value - 1;
		inputAttendeeMore.value = inputAttendeeMoreNum;
		let activity_attendee_formNum = document.querySelectorAll('.activity-attendee-content').length;
		if(activity_attendee_formNum < inputAttendeeMoreNum) {
			document.querySelector('.activity-attendee-title') ? '' : autoCreateFormTitle();
			let lackFormNum = inputAttendeeMoreNum - activity_attendee_formNum;
			for(let i = 0; i < lackFormNum; i++) 
				autoCreateFormBody(activity_attendee_formNum + 1 + i);
		} else if(activity_attendee_formNum > inputAttendeeMoreNum) {
			document.querySelector('.activity-attendee-title') ? '' : autoCreateFormTitle();
			let extraFormNum = activity_attendee_formNum - inputAttendeeMoreNum;
			for(let i = 0; i < extraFormNum; i++)		
				document.querySelector('.activity-attendee').lastChild.remove();
		} else if (inputAttendee.value == 1) {
			activity_attendee_info.innerHTML = '';
		}
	}

// 動態生成參加者填寫表格
function autoCreateFormTitle() {
	let element = document.createElement('div');
	  element.classList.add('activity-attendee-title');
	  element.classList.add('col-12');
	  activity_attendee_info.append(element);
	  let html = 
	  `<h4>
        <i class="fa-solid fa-user-pen"></i>
        額外參加者資訊
      </h4>
      <div class="activity-attendee-arrow">
        <i class="fa-solid fa-chevron-down"></i>
      </div>`;
       activity_attendee_info.lastChild.innerHTML = html;
}

function autoCreateFormBody(startIndex, item) {
	  let element =  document.createElement('div');
	  element.classList.add('activity-attendee-content');
	  activity_attendee_info.append(element);
	  let html = 
	  `<div class="row">
        <div class="form-item form-item-attendee-more-name col-12 mt-3">
          <div class="form-label input-group-attendee-more-name">
            <input name="attendeeName" type="text" class="form-control form-control-name" id="inputAttendeeMoreName${startIndex}" value="${item ? item.attendeeName : ''}">
            <label for="inputAttendeeMoreName${startIndex}" class="input-group-text">第${startIndex}位參加者姓名</label>
          </div>
          <div class="error-text error-text-attendee-more-name">
            <i class="fa-solid fa-circle-exclamation"></i>
            <span>錯誤訊息</span>
          </div>
        </div>
        <div class="form-item form-item-attendee-more-gender col-6 mt-3">
          <div class="form-item-attendee-more-gender-title">
            第${startIndex}位參加者生理性別
          </div>
          <div class="row form-item-attendee-more-gender-content">
            <div class="form-label input-group-attendee-more-gender col-6">
              <input name="attendeeGender${startIndex}" type="radio" class="form-control" id="inputAttendeeMoreGenderMale${startIndex}" 
              ${item ? item.attendeeGender == 1 ? 'checked' : '' : 'checked'}>
              <label for="inputAttendeeMoreGenderMale${startIndex}" class="input-group-text">男</label>
            </div>
            <div class="form-label input-group-attendee-more-gender col-6">
              <input name="attendeeGender${startIndex}" type="radio" class="form-control" id="inputAttendeeMoreGenderFemale${startIndex}"
              	${item ? item.attendeeGender == 2 ? 'checked' : '' : ''}>
              <label for="inputAttendeeMoreGenderFemale${startIndex}" class="input-group-text">女</label>
            </div>
          </div>
        </div>
        <div class="form-item form-item-attendee-more-age col-6 mt-3">
          <div class="form-label input-group-attendee-more-age">
            <input name="attendeeAge" type="text" class="form-control form-control-age" id="inputAttendeeMoreAge${startIndex}" value="${item ? item.attendeeAge : ''}">
            <label for="inputAttendeeMoreAge${startIndex}" class="input-group-text">第${startIndex}位參加者年紀</label>
          </div>
          <div class="error-text error-text-attendee-more-age">
            <i class="fa-solid fa-circle-exclamation"></i>
            <span>錯誤訊息</span>
          </div>
        </div>
       	<input type="hidden" name="form-item-attendee-more-attendeeNo" id="inputAttendeeMoreAttendeeNo${startIndex}" value="${item ? item.attendeeNo : ''}">
      </div>`;
	  activity_attendee_info.lastChild.innerHTML = html;

}

// 錯誤判斷

// 檢查參加人數
inputAttendee.addEventListener('blur', () => inputAttendee_reject());
function inputAttendee_reject() {
	let flag = true;
	let error_text = '';
	let value = inputAttendee.value;

 	if(value == null || value.trim() == 0) {
   		flag = false;
   		error_text = '請輸入參加人數';
	} else if (value <= 0) {
		 flag = false;
		 error_text = '請輸入正確的人數';
	}

	error_text_controll(flag, inputAttendee_error, error_text);
	return flag;
}
// activity_attendee_info.addEventListener('mouseleave', e => {
// 	console.log(e.target);
// 	if(e.target.classList.contains('form-control-name')) {
// 		console.log('觸發click');
// 		let parent = e.target.parentElement;
// 		let parentSibling = parent.nextElementSibling;
// 		let err = parentSibling.lastElementChild;
// 		inputAttendeeMoreName_reject(e.target, err)
// 	}
// 	if(e.target.classList.contains('form-control-age')) {
// 		console.log('觸發click');
// 		let parent = e.target.parentElement;
// 		let parentSibling = parent.nextElementSibling;
// 		let err = parentSibling.lastElementChild;
// 		inputAttendeeMoreAge_reject(e.target, err)
// 	}
// })
function inputAttendeeMoreName_reject(input, errorSpan) {
	let flag = true;
	let error_text = '';
	let value = input.value;

 	if(value == null || value.trim() == 0) {
   		flag = false;
   		error_text = `請輸入${input.nextElementSibling.innerText}`;
	}

	error_text_controll(flag, errorSpan, error_text);
	return flag;
}

function inputAttendeeMoreAge_reject(input, errorSpan) {
	let flag = true;
	let error_text = '';
	let value = input.value;

	if (value <= 0) {
		flag = false;
		error_text = '請輸入正確的年紀';
   	} else if(value == null || value.trim() == 0) {
   		flag = false;
   		error_text = `請輸入${input.nextElementSibling.innerText}`;
	}

	error_text_controll(flag, errorSpan, error_text);
	return flag;
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
  