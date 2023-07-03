let inputActivityNo = document.querySelector('#inputActivityNo');
let inputName = document.querySelector('#inputName');

let activity_scores = document.querySelectorAll('.form-item-activity-score-content input');
let inputExp = document.querySelector('#inputExp');
let inputExp_error = document.querySelector('.form-item-exp .error-text span');

let submit = document.querySelector('.upload-btn');

submit.addEventListener('click', () => {
	let inputExp_flag = inputExp_reject();
	if(inputExp_flag) {
		submitScore();
	}
})
function submitScore() {
	Swal.fire({
		title: '確定送出活動評分?',
		// text: "You won't be able to revert this!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: '確定送出',
		cancelButtonText: "取消"
	}).then(async result => {
		if(result.isConfirmed) {
			let score = 0;
			for(let i = 0; i < activity_scores.length; i++)
				if(activity_scores[i].checked)
					score = i + 1;
			axios.post('/Jamigo/activityOrder/insertExp', {
				activityOrderNo : inputActivityNo.value,
				activityScore : score,
				commentDetail : inputExp.value
			}, {Headers : {'Content-Type' : 'application/json'}})
			.then(response => {
				Swal.fire({
					icon: 'success',
					title: '送出成功',
					text: '活動評分送出成功!',
					confirmButtonText: "確認"
				})
			.then(() => window.location.href="./counter_carouselCtrl.html")
			})
			.catch(error => {
				Swal.fire({
					icon: 'error',
					title: '送出失敗',
					text: '活動評分送出失敗!',
				})
			})
		}
	})
}

// 先拿訂單編號為3的
getActivityInfo(3);
// 獲取活動
function getActivityInfo(activityOrderNo){
	axios.get(`/Jamigo/activityOrder/getActivityOrderById/${activityOrderNo}`)
	.then(resp => {return resp.data})
	.then(data => {
		inputActivityNo.value = data.activityOrderNo,
		inputName.value = data.activity.activityName
	})
	.catch(err => console.log(err))
}

// 錯誤檢查

// 檢查輪播文字是否不為空
inputExp.addEventListener('blur', () => {
	inputExp_reject();
});
function inputExp_reject() {
	let flag = true;
	let error_text = '';
	let value = inputExp.value;
	if(value == null || value.trim() == 0) {
		flag = false;
		error_text = '請輸入活動心得';
	} 

	error_text_controll(flag, inputExp_error, error_text);
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