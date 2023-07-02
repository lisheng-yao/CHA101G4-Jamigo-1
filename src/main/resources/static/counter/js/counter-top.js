document.addEventListener("DOMContentLoaded", () => {

	fetch("/Jamigo/counter/counterAcc")
		.then((response) => response.json())
		.then((data) => {
			console.log(data);
			localStorage.setItem("counterNo", data.counterNo);
			localStorage.setItem("counterName", data.counterName);
			localStorage.setItem("counterAccount", data.counterAccount);

			const headpic = document.getElementById("head-pic");
			headpic.src = "/Jamigo/counterPic/" + data.counterNo;

			const ctn = document.getElementById("counter-top-name");
			ctn.textContent = "歡迎回來  " + data.counterName + " 專櫃";
		})
		.catch((error) => console.log("錯誤:", error));
});
