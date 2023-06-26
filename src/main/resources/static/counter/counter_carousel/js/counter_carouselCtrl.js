let carousel_controll_group = document.querySelector('.carousel-img-controll-group .container-fluid .row');
let carousel_controll_infoBox = document.querySelector('.carousel-img-controll-item .carousel-img-controll-infoBox');
let carousel_controll_pic = document.querySelector('.carousel-img-controll-item .carousel-img-controll-pic');
let delet_btn = document.querySelector(".btn-danger");

window.addEventListener("DOMContentLoaded", () =>{
  // 先查出所有櫃位
  // 目前先查櫃位編號為1的
  getCounterAllInfo(1);

})

function getCounterAllInfo(counterNo){
	axios.get(`/Jamigo/counterCarousel/getAllByCounterNoWithoutPic/${counterNo}`)
	.then(resp => {
		return resp.data;
	})
	.then(datas => {
		render(datas);
		console.log('okkkkkkk');
	})
	.catch(err => console.log(err))
}

// 渲出頁面
function render(datas) {
  for(let data of datas){
		let element = document.createElement('div');
		element.classList.add('carousel-img-controll-item');
		element.classList.add('col-12');
		carousel_controll_group.append(element);
		let html = 
		`<div class="row">
			<div class="col-5">
	            <div class="carousel-img-controll-pic">
	                <img src="/Jamigo/counterCarouselImg/${data.counterCarouselNo}" alt="${data.counterCarouselNo}">
	            </div>
	        </div>
	        <div class="carousel-img-controll-infoBox col-7 py-3">
	          <div class="carousel-img-controll-infoNo">
	            <span>編號</span>
	            <span class="carousel-img-controll-infoNo-num">${data.counterCarouselNo}</span>
	          </div>
	          <div class="carousel-img-controll-infoTime mt-3">
	            <div class="carousel-img-controll-startTime">
	              <span>開始時間</span>
	              <span>${data.counterCarouselStartTime}</span>
	            </div>
	            <div class="carousel-img-controll-endTime">
	              <span>結束時間</span>
	              <span>${data.counterCarouselEndTime}</span>
	            </div>
	          </div>
	          <div class="carousel-img-controll-infoText">
	            <span>輪播內容</span>
	            <span class="carousel-img-controll-infoNo-text">${data.counterCarouselText}</span>
	          </div>
	          <div class="button-group carousel-img-controll-btnGroup pt-3">
	            <a href="./counter_carouselEdit.html?counterCarouselNo=${data.counterCarouselNo}" class="btn revise-btn btn-primary">
	              <i class="fa-solid fa-pen-to-square"></i>
	              修改輪播圖
	            </a>
                <button type="button" class="btn delete-btn btn-danger">
                  <i class="fa-solid fa-pen-to-square"></i>
                  刪除輪播圖
                </button>
	          </div>
	        </div>
		</div>`;
		
		carousel_controll_group.lastElementChild.innerHTML = html;
	}
}

// 刪除輪播圖
carousel_controll_group.addEventListener('click', e => {
	if(e.target.classList.contains('btn-danger')) {
		let parentEle = e.target.parentElement;
		let grandfatherEle = parentEle.parentElement;
		let infoBox = grandfatherEle.firstElementChild;
		let index = infoBox.lastElementChild.innerText;
		
		axios.get(`/Jamigo/counterCarousel/deleteById/${index}`)
		.then(resp => location.reload())
		.catch(err => console.log(err))
	}
})

//
//// 彈窗，抓出單一櫃欸的詳細資料
//document.querySelector('.panel-table').addEventListener('click', e => {
//  if(e.target.matches('.btn-info')) {
//    let counter = "";
//    let parentEle = e.target.parentElement;
//    let index = parentEle.parentElement.firstElementChild.innerText;
//    let url = `/Jamigo/counterCtrl/getCounterById/${index}`;
//    
//    let xhr = new XMLHttpRequest();
//
//    xhr.addEventListener("load", () => {
//      counter = JSON.parse(xhr.responseText);
//    })
//
//    xhr.open('GET', url, false);
//    xhr.setRequestHeader('Header-Action', 'getCounterById');
//    xhr.send(null);
//	
//    let stateHtml = stateSwitch(counter);
//    let html = `
//      <tr>
//        <th scope="row">櫃位編號</th>
//        <td>${counter.counterNo}</td>
//      </tr>
//      <tr>
//        <th scope="row">櫃位名稱</th>
//        <td>${counter.counterName ? counter.counterName : '預設'}</td>
//      </tr>
//      <tr>
//        <th scope="row">抽成比例</th>
//        <td>${counter.cutPercent}</td>
//      </tr>
//      <tr>
//        <th scope="row">帳號</th>
//        <td>${counter.counterAccount}</td>
//      </tr>
//      <tr class="modal-table-state">
//        <th scope="row">狀態</th>
//        <td>${stateHtml[0]}</td>
//      </tr>
//      <tr>
//        <th scope="row">統編</th>
//        <td>${counter.counterGui ? counter.counterGui : '預設'}</td>
//      </tr>
//      <tr>
//        <th scope="row">樓層</th>
//        <td>${counter.counterFloor ? counter.counterFloor : '預設'}</td>
//      </tr>
//      <tr>
//        <th scope="row">櫃位電話</th>
//        <td>${counter.counterTel ? counter.counterTel : '預設'}</td>
//      </tr>
//      <tr>
//        <th scope="row">聯絡人</th>
//        <td>${counter.counterPoc ? counter.counterPoc : '預設'}</td>
//      </tr>
//      <tr>
//        <th scope="row">聯絡電話</th>
//        <td>${counter.counterPocPhone ? counter.counterPocPhone : '預設'}</td>
//      </tr>
//      <tr>
//        <th scope="row">聯絡地址</th>
//        <td>${counter.counterPocAddress ? counter.counterPocAddress : '預設'}</td>
//      </tr>
//      <tr>
//        <th scope="row">信箱</th>
//        <td>${counter.counterEmail ? counter.counterEmail : '預設'}</td>
//      </tr>
//      <tr>
//        <th scope="row">轉帳帳號</th>
//        <td>${counter.counterBankAccount ? counter.counterBankAccount : '預設'}</td>
//      </tr>
//      <tr>
//        <th scope="row">被檢舉成功次數</th>
//        <td>${counter.counterReportCount ? counter.counterReportCount : '預設'}</td>
//      </tr>
//      <tr>
//        <th scope="row">關於我們</th>
//        <td>${counter.counterAbout ? counter.counterAbout : '預設'}</td>
//      </tr>
//      <tr>
//        <th scope="row">櫃位頭貼</th>
//        <td>
//          <div class="detail-controll-table-img col-2">
//            <img src="/Jamigo/counterCtrlImg/${counter.counterNo}" alt=${counter.counterName}>
//          </div>
//        </td>
//      </tr>
//    `;
//    document.querySelector('.modal-footer a').href = `/Jamigo/counter/ctrl/view/counter_accountEdit.html?counterNo=${counter.counterNo}`;
//    document.querySelector("#exampleModal .modal-body tbody").innerHTML = html;
//  } 
//})
//
//function stateSwitch(item) {
//	let stateHtml = '';
//	let data_status = '';
//	switch(item.counterStat){
//      case 0:
//        stateHtml = `<span class="label label-notEnabled">
//          <i class="fa-solid fa-hourglass-half"></i>
//          帳號未啟用
//          </span>`;
//        data_status = 'notEnabled';
//        break;
//      case 1:
//        stateHtml = `<span class="label label-success">
//          <i class="fa-solid fa-circle-check"></i>
//          帳號啟用
//          </span>`;
//        data_status = 'success';
//        break;
//      case 2:
//        stateHtml = `<span class="label label-danger">
//          <i class="fa-solid fa-circle-exclamation"></i>
//          帳號停用
//          </span>`;
//        data_status = 'danger';
//        break;
//      default:
//        stateHtml = '111';
//        break
//    }
//    return [stateHtml, data_status];
//}