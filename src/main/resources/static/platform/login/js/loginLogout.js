let account = document.querySelector('#login-account');
let password = document.querySelector('#login-password');
let login_btn = document.querySelector('.content-input-field button');

//checkLogin();
//// 發請求給後端查找cookie中是不是有sessionId
//function checkLogin(){
////	let cookieValue = null;
////	let cookies = document.cookie.length.split(';');
////	for(let cookie of cookies) {
////		if(cookie.trim().startWidth(session + '=')) {
////			console.log("okkkkkkkk")
////		}
////	}
//	let url = "/Jamigo/administratorCheck/checkSession";
//	fetch(url, {
//		method : 'GET',
//		headers : {
//			'Content-Type' : 'text/plain'
//		}
//	})
//}

// 登入驗證
login_btn.addEventListener('click', () => {
	
	// 獲取輸入的帳號密碼
	let admin = {
		adminName : account.value,
		adminPassword : password.value	
	}
	console.log(admin)
	let url = "/Jamigo/administrator/login"
	
	fetch(url, {
		method : 'POST',
		headers : {
			'Content-Type' : 'application/json'
		},
		body : JSON.stringify(admin)
	})
	.then(response => {
		if(response.ok)
//			console.log('okkkkkkkkk')
			window.location.href = "/Jamigo/counter/activity_orderCtrl.html"
		else
			console.log('rrrrrrrrrrr')
	})
	.catch(error => console.log('nooooooo'))
	
})

