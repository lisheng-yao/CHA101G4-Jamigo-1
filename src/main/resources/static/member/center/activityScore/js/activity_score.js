let inputActivityNo = document.querySelector('#inputActivityNo');
let inputName = document.querySelector('#inputName');

let activity_scores = document.querySelectorAll('.form-item-activity-score-content input');
let inputExp = document.querySelector('#inputExp');

let submit = document.querySelector('.upload-btn');

submit.addEventListener('click', () => {
	let score = 0;
	for(let i = 0; i < activity_scores.length; i++)
		if(activity_scores[i].checked)
			score = i + 1;
	axios.post('/Jamigo/activityOrder/insertExp', {
		activityOrderNo : inputActivityNo.value,
		activityScore : score,
		commentDetail : inputExp.value
	}, {Headers : {'Content-Type' : 'application/json'}})
	.then(resp => console.log(resp))
})

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