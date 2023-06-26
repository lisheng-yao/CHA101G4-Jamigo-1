//let credit_card_info = document.querySelector('.credit-card-info-box');
let mobile_pay = document.querySelector('.mobile-pay-box');
let pay_way_group = document.querySelectorAll('.pay-way');
let select_number_of_people = document.querySelector('.select-number-of-people select');
let activity_attendee_more = document.querySelector('.activity-attendee-more');

// 活動相關資訊欄位
let activity_name = document.querySelector('#activity-name');
let activity_date = document.querySelector('#activity-date');
let activity_time = document.querySelector('#activity-time');
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

let activity_form_submit = document.querySelector('.activity-form-submit button');

// 先拿1號會員的
getMemberInfo(1);
// 載入時讀取會員資料，並填入
function getMemberInfo(id){
	axios.post('/Jamigo/member/member/getmemberdata', 
				{memberNo : id}, 
				{header : {'Content-Type' : 'application/json'}})
	.then(resp => {return resp.data})
	.then(data => {
		activity_attendee_memberNo_input.value = data.memberNo;
		activity_attendee_name_input.value = data.memberName;
		activity_attendee_email_input.value = data.memberEmail;
		activity_attendee_phone_input.value = data.memberPhone;
	})
}

// 先抓活動編號為1的
getActivity(1);
// 載入時讀取活動資料，並填入
function getActivity(id) {
	axios.get(`/Jamigo/activity/getById/${id}`)
	.then(resp => {return resp.data})
	.then(data => {
		let [startDate, startTime] = data.activityStartTime.split(' ');
		let [endDate, endTime] = data.activityEndTime.split(' ');
		activity_activityNo_input.value = data.activityNo;
		activity_name.innerText = data.activityName;
		activity_date.innerText = `${startDate} ~ ${endDate}`;
		activity_time.innerText = `${startTime} ~ ${endTime}`;
		activity_price.innerText = data.activityCost;
		activity_address.innerText = data.activityPlaceNo;
		activity_memberLevel.innerText = data.activityLev == 0 ? '一般會員' : 'VIP會員';
		activity_pic.src = `/Jamigo/activityReader/${data.activityNo}`
	})
}

// 傳至後端
activity_form_submit.addEventListener('click', () => {
	let more_attendee_num = activity_attendee_num_select.value - 1;
	
	axios.post('/Jamigo/activityOrder/insert', {
		activityNo : activity_activityNo_input.value,
		memberNo : activity_attendee_memberNo_input.value,
		activityPaymentStat : 0,
		memberCouponNo : activity_attendee_coupon.value == 'AX' ? 0 : activity_attendee_coupon.value,
		numberOfAttendee : activity_attendee_more_num_select.value - 1
	}, {headers : {'Content-Type':'application/json'}})
	.then(resp => {
		console.log(resp.data);
		return resp.data;
	})
	.then(data => {
		if(more_attendee_num > 0) {
			let more_attendee_arr = [];
			for(let i = 1; i <= more_attendee; i++) {
				let more_attendee = {
					activityOrderNo : data.activityOrderNo,
					attendeeName : document.querySelect(`#activity-attendee-more-name-input${i + 1}`).value,
					attendeeGender : document.querySelect(`#activity-attendee-moregender-male-input${i + 1}`).checked ?
									 1 : 2,
					attendeeAge : document.querySelect(`#activity-attendee-more-age-input${i + 1}`).value
				}
				more_attendee_arr.push(more_attendee);
			}
			
			axios.post('/Jamigo/activityAttendee/insert')
		}
	})
})

















// HTML動態生成

// 付款方式選擇，並跑出對應的選項
//document.querySelector('.pay-way-credit-card-box').addEventListener('click', () => {
//    credit_card_info.classList.add('show');
//    mobile_pay.classList.remove('show');
//})
document.querySelector('.pay-way-mobile-box').addEventListener('click', () => {
    mobile_pay.classList.add('show');
//    credit_card_info.classList.remove('show');
})
document.querySelector('.pay-way-ATM-box').addEventListener('click', () => {
    mobile_pay.classList.remove('show');
//    credit_card_info.classList.remove('show');
})

// 選擇人數後更新額外參加人數的欄位
select_number_of_people.addEventListener('change', () => {
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


// 新增線下活動訂單
activity_form_submit.addEventListener('click', () => {
	axios.post("/Jamigo/counterCtrl/insert", {
			activityOrderNo : null,
			activityNo : document.querySelector('#activity-activityNo').value,
			memberNo : document.querySelector('#activity-attendee-memberNo').value,
			activityEnrollmentTime : null,
			activityPaymentStat : 0,
			memberCouponNo : document.querySelector('#activity-attendee-coupon').value,activity_attendee_more,
			numberOfAttendee : document.querySelector('#activity-attendee-more-num').vlaue - 1,
			commentDetail : null,
			activityScore : null
	})
	.then(response => console.log(response.data))
	
})


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
                <input id="activity-attendee-more-name-input${startIndex}" type="text" name="activity-attendee-more-name" placeholder="" required="">
            </div>
        </div>
        <div class="phone_email">
            <div class="row">
                <div class="form-radio col-3 attendee-gender mt-3">
                <input type="radio" id="activity-attendee-moregender-male-input${startIndex}" name="activity-attendee-more-gender-male${startIndex}" checked>
                    <label for="activity-attendee-more-gender-male-input${startIndex}" class="attendee-gender-male">
                        <div class="form-radio-text">男</div>
                    </label>
                </div>
                <div class="form-radio col-3 attendee-gender mt-3">
                    <input type="radio" id="activity-attendee-more-gender-female-input${startIndex}" name="activity-attendee-moregender-female${startIndex}">
                    <label for="activity-attendee-more-gender-female-input${startIndex}" class="attendee-gender-female"> 
                        <div class="form-radio-text">女</div>
                    </label>
                </div>
            </div>
        </div>
        <div class="phone_email">
            <label>年紀</label>
            <div class="form-text">
                <input type="text" id="activity-attendee-more-age-input${startIndex}" name="activity-attendee-more-age" placeholder="" required="">
            </div>
        </div>`;
    activity_attendee_more.lastChild.innerHTML = html;
}





