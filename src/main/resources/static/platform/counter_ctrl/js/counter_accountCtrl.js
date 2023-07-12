// 登出
let logout_btn = document.querySelector('.sidebar #logoutbutton');

console.log(logout_btn)

window.addEventListener("DOMContentLoaded", () =>{
  // 先查出所有櫃位
  getAllCounter();

})

function getAllCounter() {
  let xhr = new XMLHttpRequest();
  let url = "/Jamigo/counterCtrl/getPartInfo";
  
  xhr.addEventListener("load", () => {
    let counters = JSON.parse(xhr.responseText);
    render(counters);
  })
  xhr.open("get", url, true);
  xhr.setRequestHeader('Header-Action', 'getPartInfo');
  xhr.send(null);

}

// 渲出頁面
function render(counters) {
  let html = '';
  let stateHtml = '';
  for(let counter of counters){
	let result = stateSwitch(counter);
	stateHtml = result[0];
	let data_status = result[1];
    html += `
    <tr data-status="${data_status}">
      <td>${counter.counterNo}</td>
      <td>${counter.counterName ? counter.counterName : '預設'}</td>
      <td>${counter.cutPercent}</td>
      <td>${counter.counterTel ? counter.counterTel : '預設'}</td>
      <td>${stateHtml ? stateHtml : '預設'}</td>
      <td>
        <a href="./counter_accountEdit.html?counterNo=${counter.counterNo}" class="btn btn-edit">
          <i class="fa fa-edit"></i>
          修改
        </a>
      </td>
      <td>
        <a href="#" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#exampleModal">
          <i class="fa-solid fa-circle-info"></i>
          詳細資訊
        </a>
      </td>
    </tr>
    `;
    document.querySelector('#user').innerHTML = html;
  }
}

// 彈窗，抓出單一櫃欸的詳細資料
document.querySelector('.panel-table').addEventListener('click', e => {
  if(e.target.matches('.btn-info')) {
    let counter = "";
    let parentEle = e.target.parentElement;
    let index = parentEle.parentElement.firstElementChild.innerText;
    let url = `/Jamigo/counterCtrl/getCounterById/${index}`;
    
    let xhr = new XMLHttpRequest();

    xhr.addEventListener("load", () => {
      counter = JSON.parse(xhr.responseText);
    })

    xhr.open('GET', url, false);
    xhr.setRequestHeader('Header-Action', 'getCounterById');
    xhr.send(null);
	
    let stateHtml = stateSwitch(counter);
    let html = `
      <tr>
        <th scope="row">櫃位編號</th>
        <td>${counter.counterNo}</td>
      </tr>
      <tr>
        <th scope="row">櫃位名稱</th>
        <td>${counter.counterName ? counter.counterName : '預設'}</td>
      </tr>
      <tr>
        <th scope="row">抽成比例</th>
        <td>${counter.cutPercent}</td>
      </tr>
      <tr>
        <th scope="row">帳號</th>
        <td>${counter.counterAccount}</td>
      </tr>
      <tr class="modal-table-state">
        <th scope="row">狀態</th>
        <td>${stateHtml[0]}</td>
      </tr>
      <tr>
        <th scope="row">統編</th>
        <td>${counter.counterGui ? counter.counterGui : '預設'}</td>
      </tr>
      <tr>
        <th scope="row">樓層</th>
        <td>${counter.counterFloor ? counter.counterFloor : '預設'}</td>
      </tr>
      <tr>
        <th scope="row">櫃位電話</th>
        <td>${counter.counterTel ? counter.counterTel : '預設'}</td>
      </tr>
      <tr>
        <th scope="row">聯絡人</th>
        <td>${counter.counterPoc ? counter.counterPoc : '預設'}</td>
      </tr>
      <tr>
        <th scope="row">聯絡電話</th>
        <td>${counter.counterPocPhone ? counter.counterPocPhone : '預設'}</td>
      </tr>
      <tr>
        <th scope="row">聯絡地址</th>
        <td>${counter.counterPocAddress ? counter.counterPocAddress : '預設'}</td>
      </tr>
      <tr>
        <th scope="row">信箱</th>
        <td>${counter.counterEmail ? counter.counterEmail : '預設'}</td>
      </tr>
      <tr>
        <th scope="row">轉帳帳號</th>
        <td>${counter.counterBankAccount ? counter.counterBankAccount : '預設'}</td>
      </tr>
      <tr>
        <th scope="row">被檢舉成功次數</th>
        <td>${counter.counterReportCount ? counter.counterReportCount : '預設'}</td>
      </tr>
      <tr>
        <th scope="row">關於我們</th>
        <td>${counter.counterAbout ? counter.counterAbout : '預設'}</td>
      </tr>
      <tr>
        <th scope="row">櫃位頭貼</th>
        <td>
          <div class="detail-controll-table-img col-2">
            <img src="/Jamigo/counterCtrlImg/${counter.counterNo}" alt=${counter.counterName}>
          </div>
        </td>
      </tr>
    `;
    document.querySelector('.modal-footer a').href = `./counter_accountEdit.html?counterNo=${counter.counterNo}`;
    document.querySelector("#exampleModal .modal-body tbody").innerHTML = html;
  } 
})

function stateSwitch(item) {
	let stateHtml = '';
	let data_status = '';
	switch(item.counterStat){
      case 0:
        stateHtml = `<span class="label label-notEnabled">
          <i class="fa-solid fa-hourglass-half"></i>
          帳號未啟用
          </span>`;
        data_status = 'notEnabled';
        break;
      case 1:
        stateHtml = `<span class="label label-success">
          <i class="fa-solid fa-circle-check"></i>
          帳號啟用
          </span>`;
        data_status = 'success';
        break;
      case 2:
        stateHtml = `<span class="label label-danger">
          <i class="fa-solid fa-circle-exclamation"></i>
          帳號停用
          </span>`;
        data_status = 'danger';
        break;
      default:
        stateHtml = '111';
        break
    }
    return [stateHtml, data_status];
}


logout_btn.addEventListener('click', e => {
	e.preventDefault();
	let url = '/Jamigo/administrator/logout'
	fetch(url, {
		method : 'GET'
	})
	.then(resp => {
		console.log(resp);
		localStorage.removeItem('adminNo');
		localStorage.removeItem('adminName');
		window.location.assign('/Jamigo/platform/login/admin_login.html')
		
	})

})