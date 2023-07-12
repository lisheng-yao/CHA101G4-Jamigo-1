let account = document.querySelector('#login-account');
let password = document.querySelector('#login-password');
let login_btn = document.querySelector('.content-input-field button');

password.addEventListener('keyup', e => {
	if(e.code === 'Enter')
		login_btn.click();	
})

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
		if(response.ok) {
			console.log(response);
			return response.json();
		}
		else {
			Swal.fire({
				icon: 'error',
				title: '登入失敗',
				text: '請輸入正確的帳號密碼',
			})
		}
	})
	.then(data => {
		console.log(data);
		localStorage.setItem('adminNo', data.adminNo);
		localStorage.setItem('adminName', data.adminName);
		window.location.assign('/Jamigo/platform/counter_accountCtrl.html')
	})
	.catch(error => console.log(error))
	
})

//logout_btn.addEventListener('click', e => {
//	e.preventDefault();
//	let url = '/Jamigo/administrator/logout'
//	fetch(url, {
//		method : 'GET'
//	})
//	.then(resp => {
//		console.log(resp);
//		
//	})
//	localStorage.removeItem('adminNo', data.adminNo);
//	localStorage.removeItem('adminName', data.adminName);
//
//})