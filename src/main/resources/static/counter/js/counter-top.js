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

document.getElementById("sing-out").addEventListener("click", () => {
  fetch("/Jamigo/sign_out")
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error("登出失敗");
      }
    })
    .then(() => {
      Swal.fire({
        icon: "success",
        title: "成功登出",
        showConfirmButton: false,
        timer: 1500,
      }).then(() => {
        window.location.href = "/Jamigo/counter/counter_login.html";
      });
    })
    .catch((error) => {
      console.log("錯誤:", error);
      Swal.fire({
        icon: "error",
        title: "登出失敗",
        text: "發生了一些問題，請稍後再試",
      });
    });
});
