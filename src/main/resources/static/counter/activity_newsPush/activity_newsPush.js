

var webSocket;
connect(1);
function connect(memberNo) {
	// 創建websocket連線
	webSocket = new WebSocket(`ws://localhost:8080/Jamigo/websocket?userId=${memberNo}`);
//	webSocket = new WebSocket(`ws://localhost:8080/Jamigo/newWebsocket/${memberNo}`);

	webSocket.onopen = function(event) {
//		updateStatus("WebSocket Connected");
//		document.getElementById('sendMessage').disabled = false;
//		document.getElementById('connect').disabled = true;
//		document.getElementById('disconnect').disabled = false;
	};
	
	webSocket.onmessage = function(event) {
		let message = event.data;
//		let jsonObj = JSON.parse(event.data); // 事件物件中的data裝著後端傳來的訊息，但是字串格式，需要轉換
//		let message = `${jsonObj.memberNoticeNo}: ${jsonObj.noticeContent}`;
		console.log('Received message:' + message)
//		let message = jsonObj.memberNoticeNo + ": " + jsonObj.noticeContent + "\r\n"; // \r\n換行
//		messagesArea.value = messagesArea.value + message;
		// 新訊息卷軸至底
//		messagesArea.scrollTop = messagesArea.scrollHeight;
	};

	// 類似destroy()
//	webSocket.onclose = function(event) {
//		updateStatus("WebSocket Disconnected");
//	};
}

//var inputUserName = document.getElementById("userName");
//inputUserName.focus();
//
//function sendMessage() {
//	var userName = inputUserName.value.trim();
//	if (userName === "") {
//		alert("Input a user name");
//		inputUserName.focus();
//		return;
//	}
//
//	var inputMessage = document.getElementById("message");
//	var message = inputMessage.value.trim();
//
//	if (message === "") {
//		alert("Input a message");
//		inputMessage.focus();
//	} else {
//		var jsonObj = {
//			"userName" : userName,
//			"message" : message
//		};
//		// 前端使用js 後端使用java 資料交換使用JSON
//		// 使用JSON轉成字串後交給websocket送出資訊到後端
//		webSocket.send(JSON.stringify(jsonObj));
//		inputMessage.value = "";
//		inputMessage.focus();
//	}
//}
//
//function disconnect() {
//	webSocket.close();
//	document.getElementById('sendMessage').disabled = true;
//	document.getElementById('connect').disabled = false;
//	document.getElementById('disconnect').disabled = true;
//}
//
//function updateStatus(newStatus) {
//	statusOutput.innerHTML = newStatus;
//}