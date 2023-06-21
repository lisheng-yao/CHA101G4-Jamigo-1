let credit_card_info = document.querySelector('.credit-card-info-box');
let mobile_pay = document.querySelector('.mobile-pay-box');
let pay_way_group = document.querySelectorAll('.pay-way');
let select_number_of_people = document.querySelector('.select-number-of-people select');
let activity_attendee_more = document.querySelector('.activity-attendee-more');

let activity_form_submit = document.querySelector('.activity-form-submit');

// 付款方式選擇，並跑出對應的選項
document.querySelector('.pay-way-credit-card-box').addEventListener('click', () => {
    credit_card_info.classList.add('show');
    mobile_pay.classList.remove('show');
})
document.querySelector('.pay-way-mobile-box').addEventListener('click', () => {
    mobile_pay.classList.add('show');
    credit_card_info.classList.remove('show');
})
document.querySelector('.pay-way-ATM-box').addEventListener('click', () => {
    mobile_pay.classList.remove('show');
    credit_card_info.classList.remove('show');
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