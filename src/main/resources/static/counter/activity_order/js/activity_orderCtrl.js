window.addEventListener("DOMContentLoaded", () =>{
  // 先查出所有櫃位
  getAllactivityOrder();

})

function getAllactivityOrder() {
  let xhr = new XMLHttpRequest();
  let url = "/Jamigo/activityOrder/getAll";
  
  xhr.addEventListener("load", () => {
    let activityOrders = JSON.parse(xhr.responseText);
    render(activityOrders);
  })
  xhr.open("get", url, true);
  xhr.setRequestHeader('Header-Action', 'getPatrInfo');
  xhr.send(null);

}

// 渲出頁面
function render(activityOrders) {
  let html = '';
  let stateHtml = '';
  for(let activityOrder of activityOrders){
	let result = stateSwitch(activityOrder);
	stateHtml = result[0];
	let data_status = result[1];
    html += `
    <tr data-status="${data_status}">
      <td>${activityOrder.activityOrderNo}</td>
      <td>${activityOrder.activityNo}</td>
      <td>${activityOrder.activityEnrollmentTime}</td>
      <td>${activityOrder.numberOfAttendee ? activityOrder.numberOfAttendee + 1 : 1}</td>
      <td>${stateHtml}</td>
      <td>
        <a href="./activity_orderEdit.html?activityOrderNo=${activityOrder.activityOrderNo}" class="btn btn-edit">
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
    let activityOrder = "";
    let parentEle = e.target.parentElement;
    let index = parentEle.parentElement.firstElementChild.innerText;
    let url = `/Jamigo/activityOrder/getActivityOrderById/${index}`;
    
    let xhr = new XMLHttpRequest();

    xhr.addEventListener("load", () => {
      activityOrder = JSON.parse(xhr.responseText);
    })

    xhr.open('GET', url, false);
    xhr.setRequestHeader('Header-Action', 'getactivityOrderById');
    xhr.send(null);

    let stateHtml = stateSwitch(activityOrder);
    let html = `
      <tr>
        <th scope="row">訂單編號</th>
        <td>${activityOrder.activityOrderNo}</td>
      </tr>
      <tr>
        <th scope="row">線下活動編號</th>
        <td>${activityOrder.activityNo}</td>
      </tr>
      <Xtr>
        <th scope="row">會員編號</th>
        <td>${activityOrder.memberNo}</td>
      </tr>
      <Xtr>
        <th scope="row">會員帳號</th>
        <td>${activityOrder.memberData.memberAccount}</td>
      </tr>
      <Xtr>
        <th scope="row">會員姓名</th>
        <td>${activityOrder.memberData.memberName}</td>
      </tr>
      <tr>
        <th scope="row">參加人數</th>
        <td>${activityOrder.numberOfAttendee ? activityOrder.numberOfAttendee + 1 : 1}</td>
      </tr>
      <tr>
        <th scope="row">額外參加人數</th>
        <td>${activityOrder.numberOfAttendee ? activityOrder.numberOfAttendee : '無'}</td>
      </tr>
      <tr>
        <th scope="row">下單時間</th>
        <td>${activityOrder.activityEnrollmentTime}</td>
      </tr>
      <tr class="modal-table-state">
        <th scope="row">附款狀態</th>
        <td>${stateHtml[0]}</td>
      </tr>
      <tr>
        <th scope="row">使用折價券編號</th>
        <td>${activityOrder.memberCouponNo}</td>
      </tr>
      <tr>
        <th scope="row">活動評論</th>
        <td>${activityOrder.commentDetail ? activityOrder.activityOrderTel : '無'}</td>
      </tr>
      <tr>
        <th scope="row">活動評價星等</th>
        <td>${activityOrder.activityScore ? activityOrder.activityOrderPoc : '無'}</td>
      </tr>
    `;
    document.querySelector('.modal-footer a').href = `./activity_orderEdit.html?activityOrderNo=${activityOrder.activityOrderNo}`;
    document.querySelector("#exampleModal .modal-body tbody").innerHTML = html;
  } 
})


function stateSwitch(item) {
	let stateHtml = '';
	let data_status = '';
	switch(item.activityPaymentStat){
      case 0:
        stateHtml = `<span class="label label-notEnabled">
          <i class="fa-solid fa-hourglass-half"></i>
          未付款
          </span>`;
        data_status = 'notEnabled';
        break;
      case 1:
        stateHtml = `<span class="label label-success">
          <i class="fa-solid fa-circle-check"></i>
          已完成付款
          </span>`;
        data_status = 'success';
        break;
      case 2:
        stateHtml = `<span class="label label-danger">
          <i class="fa-solid fa-circle-exclamation"></i>
          已取消
          </span>`;
        data_status = 'danger';
        break;
      default:
        stateHtml = '111';
        break
    }
    return [stateHtml, data_status];
}