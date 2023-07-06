let credit_card_info = document.querySelector('.credit-card-info-box');
let mobile_pay = document.querySelector('.mobile-pay-box');
let pay_way_group = document.querySelectorAll('.pay-way');
let select_number_of_people = document.querySelector('.select-number-of-people select');
let activity_attendee_more = document.querySelector('.activity-attendee-more');

// 活動相關資訊欄位
let activity_name = document.querySelector('#activity-name');
let activity_statrDate = document.querySelector('#activity-statrDate');
let activity_endDate = document.querySelector('#activity-endDate');
let activity_singUp_startDate = document.querySelector('#activity-singUp-startDate');
let activity_singUp_endDate = document.querySelector('#activity-singUp-endDate');
let activity_price = document.querySelector('#activity-price');
let activity_address = document.querySelector('#activity-address');
let activity_memberLevel = document.querySelector('#activity-memberLevel');
let activity_pic = document.querySelector('.single-item-carousel .image img');

// 表格相關欄位
let activity_activityNo_input = document.querySelector('#activity-activityNo');
let activity_attendee_memberNo_input = document.querySelector('#activity-attendee-memberNo');
let activity_attendee_name_input = document.querySelector('#activity-attendee-name');
let activity_attendee_email_input = document.querySelector('#activity-attendee-email');
let activity_attendee_phone_input = document.querySelector('#activity-attendee-phone');
let activity_attendee_num_select = document.querySelector('#activity-attendee-more-num');
let activity_attendee_coupon = document.querySelector('#activity-attendee-coupon');
let pay_way_credit_card = document.querySelector('#pay-way-credit-card');
let creditCardNum_input = document.querySelector('#credit-card-num');
let creditCardNumEnd3_input = document.querySelector('#credit-card-num-end3');
let creditCardYear_select = document.querySelector('#credit-card-year-select');
let creditCardMonth_select = document.querySelector('#credit-card-month-select');

let couponInfo = {couponPrice0 : 0, couponLowest0 : 0};

// 報名等級
let activityLev_DB;
let activityLev_member;

// 送出報名按鈕
let activity_form_submit = document.querySelector('.activity-form-submit button');

// 總金額
let total_pay_money = document.querySelector('.total-pay .total-pay-money');

// 拿取當前會員編號
let currentMemberNo = localStorage.getItem('memberNo');
// 拿取會員資料
getMemberInfo(currentMemberNo);

// 獲取活動編號
let currentActivityNo = getActivityNo();
let currentCounterNo;

// 拿取活動資訊
getActivity(currentActivityNo);
function getActivityNo(){
    let url = location.search;
	return new URLSearchParams(url).get('activityNo');
}

// 載入時讀取會員資料，並填入
function getMemberInfo(id){
	axios.post('/Jamigo/member/member/getmemberdata', 
				{memberNo : id}, 
				{header : {'Content-Type' : 'application/json'}})
	.then(resp => {return resp.data})
	.then(data => {
		// console.log(data);
		if(data.levelNo) {}
		activity_attendee_memberNo_input.value = data.memberNo;
		activity_attendee_name_input.value = data.memberName;
		activity_attendee_email_input.value = data.memberEmail;
		activity_attendee_phone_input.value = data.memberPhone;

		activityLev_member = data.levelNo;
	})
}

// 載入時讀取活動資料，並填入
function getActivity(id) {
//	axios.get(`/Jamigo/activity/getById/${id}`)
	axios.get(`/Jamigo/backend/appdetail/${id}`)
	.then(resp => {return resp.data})
	.then(data => {
		// console.log(data);
		activity_activityNo_input.value = data.activityNo;
		activity_name.innerText = data.activityName;
		activity_statrDate.innerText = formatDate(data.activityStartTime);
		activity_endDate.innerText = formatDate(data.activityEndTime);
		activity_singUp_startDate.innerText = formatDate(data.activityRegStartTime);
		activity_singUp_endDate.innerText = formatDate(data.activityRegEndTime);
		// activity_time.innerText = `${startTime} ~ ${endTime}`;
		activity_price.innerText = `${data.activityCost} 元`;
		activity_address.innerText = activityPlace(data.activityPlaceNo);
		activity_memberLevel.innerText = data.activityLev == 0 ? '一般會員' : 'VIP會員';
		activity_pic.src = `data:image/png;base64, ${data.activityPic}`
		total_pay_money.innerText = data.activityCost;
		
		currentCounterNo = data.counterNo;
		activityLev_DB = data.activityLev + 1;
	})
	.then(() => {
		if(activityLev_member < activityLev_DB) {
			Swal.fire({
				icon: 'warning',
				title: '會員等級不符',
				text: '未符合活動的會員等級限制',
				confirmButtonText: "確認"
			  })
			.then(() => window.location.href="./event_space.html");
		}
	})
}

// 格式化時間
function formatDate(dateString) {
	const date = new Date(dateString);
	const options = {
		year: 'numeric',
		month: 'long',
		day: 'numeric',
		hour: 'numeric',
		minute: 'numeric',
		timeZone: 'Asia/Taipei',
		locale: 'zh-TW'//指定中文
	};
	return date.toLocaleString('zh-TW', options);
}

// 判斷場地資訊
function activityPlace(index){
	switch(index) {
		case '0' :
		case 0 :
			return '文創基地';
		case '1' :
		case 1 :
			return '露天廣場'
		case '2' :
		case 2 :
			return '森林劇場'
	}
}

// 點擊送出按鈕，並作錯誤判斷
activity_form_submit.addEventListener('click', () => {
	let inputAttendeeMoreName_flag = true;
	let inputAttendeeMoreAge_flag = true;
	
	let inputCreditCardNum_flag = true;
	let inputCreditCardNumEnd3_falg = true;
	let selectCreditCardYear_falg = true;
	let selectCreditCardMonth_flag = true;
	let activity_coupon_flag = activity_coupon_reject();
	if(pay_way_credit_card.checked) {
		inputCreditCardNum_flag = inputCreditCardNum_reject();
		inputCreditCardNumEnd3_falg = inputCreditCardNumEnd3_reject();
		selectCreditCardYear_falg = selectCreditCardYear_reject(creditCardYear_select);
		selectCreditCardMonth_flag = selectCreditCardMonth_reject(creditCardMonth_select);
		for(let i = 1; i <= activity_attendee_num_select.value - 1; i++) {
			let attendeeMoreName = document.querySelector(`#activity-attendee-more-name-input${i}`);
			let attendeeMoreAge = document.querySelector(`#activity-attendee-more-age-input${i}`);
			if(!inputAttendeeMoreName_reject(attendeeMoreName)){
				inputAttendeeMoreName_flag = false;
			}
			
			if(!inputAttendeeMoreAge_reject(attendeeMoreAge)) {
				inputAttendeeMoreAge_flag = false;
			}
		}
		if(inputCreditCardNum_flag && inputCreditCardNumEnd3_falg && selectCreditCardYear_falg && selectCreditCardMonth_flag && inputAttendeeMoreName_flag && inputAttendeeMoreAge_flag && activity_coupon_flag){
			form_submit();
		}
	} else {
		form_submit();
	}
	
})

// 送出表單
function form_submit() {
	Swal.fire({
	    title: '確定送出報名?',
	    icon: 'warning',
	    showCancelButton: true,
	    confirmButtonColor: '#3085d6',
	    cancelButtonColor: '#d33',
	    confirmButtonText: '確定報名',
	    cancelButtonText: "取消"
	}).then(result => {
		if(result.isConfirmed){
			let more_attendee_num = activity_attendee_num_select.value - 1;
	
			axios.post('/Jamigo/activityOrder/insert', {
				activityNo : currentActivityNo,
				memberNo : currentMemberNo,
				activityPaymentStat : pay_way_credit_card.checked ? 1 : 0,
				memberCouponNo : activity_attendee_coupon.value == 0 ? null : activity_attendee_coupon.value,
				numberOfAttendee : more_attendee_num
			}, {headers : {'Content-Type' : 'application/json'}})
			.then(resp_activity => {
				console.log('activityOrderNo' + resp_activity.data);
				return resp_activity.data;
			})
			.then(data_activity => {
				if(more_attendee_num > 0) {
					let more_attendee_arr = [];
					for(let i = 1; i <= more_attendee_num; i++) {
						let more_attendee = {
							activityOrderNo : data_activity,
							attendeeName : document.querySelector(`#activity-attendee-more-name-input${i}`).value,
							attendeeGender : document.querySelector(`#activity-attendee-more-gender-male-input${i}`).checked ?
											1 : 2,
							attendeeAge : document.querySelector(`#activity-attendee-more-age-input${i}`).value
						}
						more_attendee_arr.push(more_attendee);
					}
					
					axios.post('/Jamigo/activityAttendee/insert', more_attendee_arr, {headers : {'Content-Type':'application/json'}})
					.then(resp_attendee => {
						console.log(resp_attendee);
					})
					.catch(err_attendee => console.log(err_attendee))
				}
				console.log('data_activity' + data_activity);
				return data_activity;
			})
			.then(data_attendee => {
				console.log(data_attendee);
				if(pay_way_credit_card.checked) {
					Swal.fire({
						icon: 'success',
						title: '付款成功',
						text: '線下活動付款成功!',
						confirmButtonText: "確認"
					  })
					  .then(() => window.location.href="/Jamigo/member/center/activity_memberSignUp/member_activityMemberSignUp.html")
				} else {
					data_attendee = parseInt(data_attendee);
					const requestBody = new URLSearchParams();
					requestBody.append('activityOrderNo', data_attendee);
					axios.post(`/Jamigo/activityOrder/ecpayCheckout`, requestBody.toString(), {headers : {
						'Content-Type' : 'application/x-www-form-urlencoded'
					}})
					.then(resp_ecpayCheckout => {
						return resp_ecpayCheckout.data;
					})
					.then(data_ecpayCheckout => {
						let ele = document.createElement('div');
						ele.setAttribute('id', 'responseData');
						ele.innerHTML = data_ecpayCheckout;
						console.log(document.querySelector('body'));
						document.querySelector('body').append(ele);
						document.querySelector('#allPayAPIForm').submit();
					})
					.catch(err_ecpayCheckout => console.log(err_ecpayCheckout))
				}
			})
			.catch(err_activity => console.log(err_activity))
		}
	})
}

// HTML動態生成

// 付款方式選擇，並跑出對應的選項
document.querySelector('.pay-way-credit-card-box').addEventListener('click', () => {
   	credit_card_info.classList.add('show');
   	mobile_pay.classList.remove('show');
})
document.querySelector('.pay-way-mobile-box').addEventListener('click', () => {
    mobile_pay.classList.add('show');
	credit_card_info.classList.remove('show');
})
// document.querySelector('.pay-way-ATM-box').addEventListener('click', () => {
//     mobile_pay.classList.remove('show');
// //    credit_card_info.classList.remove('show');
// })

// 選擇人數後更新額外參加人數的欄位
select_number_of_people.addEventListener('change', () => {
	let couponNum = `couponTypeNo${activity_attendee_coupon.value}`;
	let discount = couponInfo[couponNum];
	// 更新總金額
	total_pay_money.innerText = parseInt(activity_price.innerText) * parseInt(select_number_of_people.value) - parseInt(discount);
    
	// 抓取選擇後的人數
    let num = select_number_of_people.value - 1;
    let attendee_more_num = document.querySelectorAll('.activity-attendee-more .activity-form').length;
    if(num > attendee_more_num){
        let extraFormNum = num - attendee_more_num;
        for(let i = 0; i < extraFormNum; i++)
            autoCreateForm(attendee_more_num - 0 + 1 + i);
    }else if(num < attendee_more_num) {
        let lackFormNum = attendee_more_num - num;
        for(let i = 0; i < lackFormNum; i++)		
			document.querySelector('.activity-attendee-more').lastChild.remove();
    }
})

activity_attendee_coupon.addEventListener('change', () => {
	activity_coupon_reject();
})
function activity_coupon_reject(){
	let flag = true;
	let error_text = '';
	let value = activity_attendee_coupon.value;
	let couponLowest = couponInfo[`couponLowest${value}`];
	let discount = couponInfo[`couponPrice${value}`];
	console.log(discount);
	// console.log('couponLowest' + couponLowest);
	// console.log(total_pay_money.innerText >= couponLowest);
	if(total_pay_money.innerText >= couponLowest)
		total_pay_money.innerText = parseInt(activity_price.innerText) * parseInt(select_number_of_people.value) - parseInt(discount);
	else {
		flag = false;
		error_text = `未達使用門檻`;
	}
	error_text_controll(flag, activity_attendee_coupon, error_text);
	return flag;

}

// 自動生成額外參加人數的欄位
function autoCreateForm(startIndex) {
    let element =  document.createElement('div');
    element.classList.add('book-form');
    element.classList.add('agileits-login');
    element.classList.add('activity-form');
    activity_attendee_more.append(element);
    let html = 
        `<h2 class="book-appointment-title text-start">第${startIndex}位額外參與者資料</h2>
        <div class="phone_email">
            <label>姓名</label>
            <div class="form-text">
                <input id="activity-attendee-more-name-input${startIndex}" onblur="inputAttendeeMoreName_reject(this)" tabindex="0" type="text" name="activity-attendee-more-name" placeholder="" required="">
				<div class="error_text">
					<i class="fa-solid fa-circle-exclamation"></i>
					<span>錯誤訊息</span>
				</div>
            </div>
        </div>
        <div class="phone_email">
            <div class="row">
                <div class="form-radio col-3 attendee-gender mt-3">
                <input type="radio" id="activity-attendee-more-gender-male-input${startIndex}" name="activity-attendee-more-gender${startIndex}" checked>
                    <label for="activity-attendee-more-gender-male-input${startIndex}" class="attendee-gender-male">
                        <div class="form-radio-text">男</div>
                    </label>
                </div>
                <div class="form-radio col-3 attendee-gender mt-3">
                    <input type="radio" id="activity-attendee-more-gender-female-input${startIndex}" name="activity-attendee-more-gender${startIndex}">
                    <label for="activity-attendee-more-gender-female-input${startIndex}" class="attendee-gender-female"> 
                        <div class="form-radio-text">女</div>
                    </label>
                </div>
            </div>
        </div>
        <div class="phone_email">
            <label>年紀</label>
            <div class="form-text">
                <input type="text" id="activity-attendee-more-age-input${startIndex}" onblur="inputAttendeeMoreAge_reject(this)" tabindex="0" name="activity-attendee-more-age" placeholder="" required="">
				<div class="error_text">
					<i class="fa-solid fa-circle-exclamation"></i>
					<span>錯誤訊息</span>
				</div>
            </div>
        </div>`;
    activity_attendee_more.lastChild.innerHTML = html;
}

// 動態生成優惠select欄位
getCouponSelect();
function getCouponSelect(){
	axios.get(`/Jamigo/cart/getMemberCoupons/${currentMemberNo}`)
	.then(resp => {return resp.data})
	.then(datas => {
		datas = datas.filter(data => {
			return data.counterNo == currentCounterNo || data.counterNo == 0
		})
		for(let i = 0; i < datas.length; i++) {
			let element = document.createElement('option');
			element.setAttribute('value', datas[i].couponTypeNo);
			element.innerText = `消費滿${datas[i].couponLowest}折${datas[i].couponPrice}`;
			// element.innerText = datas[i].couponConditions;
			activity_attendee_coupon.append(element);
			couponInfo[`couponPrice${datas[i].couponTypeNo}`] = datas[i].couponPrice;
			couponInfo[`couponLowest${datas[i].couponTypeNo}`] = datas[i].couponLowest;
		}
		console.log(datas);
	})
	.catch(err => console.log(err))
}

// 錯誤判斷

// 檢查姓名
function inputAttendeeMoreName_reject(ele) {
	let flag = true;
	let error_text = '';
	let value = ele.value;

 	if(value == null || value.trim() == 0) {
   		flag = false;
   		error_text = `請輸入姓名`;
	}

	error_text_controll(flag, ele, error_text);
	return flag;
}

// 檢查年紀
function inputAttendeeMoreAge_reject(ele) {
	let flag = true;
	let error_text = '';
	let value = ele.value;
	
	if (value <= 0) {
		flag = false;
		error_text = '請輸入正確的年紀';
   	} else if(value == null || value.trim() == 0) {
   		flag = false;
   		error_text = `請輸入${ele.nextElementSibling.innerText}`;
	} else if(isNaN(value)) {
   		flag = false;
   		error_text = `請輸入正確的年紀`;
	}

	error_text_controll(flag, ele, error_text);
	return flag;
}

// 檢查卡號
creditCardNum_input.addEventListener('blur', () => inputCreditCardNum_reject())
function inputCreditCardNum_reject() {
	let flag = true;
	let error_text = '';
	let value = creditCardNum_input.value;

	let numericPattern = /^[0-9]*$/;

 	if(value == null || value.trim() == 0) {
   		flag = false;
   		error_text = `請輸入卡號`;
	} else if(!numericPattern.test(value)) {
		flag = false;
   		error_text = `請輸入正確的數字`;
	}

	error_text_controll(flag, creditCardNum_input, error_text);
	return flag;
}

// 檢查後三碼
creditCardNumEnd3_input.addEventListener('blur', () => inputCreditCardNumEnd3_reject())
function inputCreditCardNumEnd3_reject() {
	let flag = true;
	let error_text = '';
	let value = creditCardNumEnd3_input.value;

	let numericPattern = /^\d{3}$/;

 	if(value == null || value.trim() == 0) {
   		flag = false;
   		error_text = `請輸入後三碼`;
	} else if(!numericPattern.test(value)) {
		flag = false;
   		error_text = `請輸入正確的數字`;
	}

	error_text_controll(flag, creditCardNumEnd3_input, error_text);
	return flag;
}

// 判斷年分
function selectCreditCardYear_reject(ele) {
	let flag = true;
	let error_text = '';
	let value = ele.value;
	console.log(value);
	if(value == null || value == 0) {
		flag = false;
		error_text = `請選擇到期年分`;
	}

	error_text_controll(flag, ele, error_text);
	return flag;
}

// 判斷月分
function selectCreditCardMonth_reject(ele) {
	let flag = true;
	let error_text = '';
	let value = ele.value;
	if(value == null || value == 0) {
		flag = false;
		error_text = `請選擇到期月分`;
	}

	error_text_controll(flag, ele, error_text);
	return flag;
}

// 檢查flag判斷要添加錯誤訊息還是移除錯誤訊息
function error_text_controll(flag, item, error_text) {
	let itemParent = item.parentElement;
	let itemSibling = item.nextElementSibling;
	if(flag) {
	  itemParent.parentElement.classList.remove('show');
	} else {
	  itemSibling.lastElementChild.innerText = error_text;
	  itemParent.parentElement.classList.add('show');
	}
  }