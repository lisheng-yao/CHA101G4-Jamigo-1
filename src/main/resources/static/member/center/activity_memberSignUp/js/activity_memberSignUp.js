let activityPace = document.querySelector('.form-content-edit .row');


getActivityOrder(1);
function getActivityOrder(memberNo){
    axios.get(`/Jamigo/activityOrder/getActivityOrderByMemberNo/${memberNo}`)
    .then(resp => {
        console.log(resp);
        return resp.data;
    })
    .then(datas => {
        for(let data of datas) {
            createCardItem(data);
        }
    })
    .catch(err => console.log(err))
}

function createCardItem(data){
    let ele = document.createElement('div');
    ele.classList.add('card-item');
    ele.classList.add('track');
    ele.classList.add('col-3');
    ele.classList.add('d-flex');
    ele.classList.add('flex-column');
    activityPace.append(ele);

    let [startDate, startTime] = data.activity.activityRegStartTime.split('T');
    let [endDate, endTime] = data.activity.activityRegEndTime.split('T');

    let html = 
        `<div class="card-img-top">
            <img src="data:image/jpeg;base64, ${data.activity.activityPic}" class="card-img-top" alt="...">
        </div>
        <div class="card-content">
            <div class="card-title">
            <h5>${data.activity.activityName}</h5>
            </div>
            <div class="card-text">
            <i class="fa-regular fa-clock"></i>
            <p class="card-text-startTime">${startDate}</p>
            <i class="fa-solid fa-chevron-right"></i>
            <p class="card-text-endTime">${endDate}</p>
            </div>
        </div>
        <div class="button-group mt-auto">
            <a href="#" class="btn btn-outline-primary mt-3">活動詳情</a>
            <a href="#" class="btn btn-primary mt-3">填寫評論</a>
        </div>`;

    activityPace.lastElementChild.innerHTML = html;
}

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
        <td>${activityOrder.commentDetail ? activityOrder.commentDetail : '無'}</td>
      </tr>
      <tr>
        <th scope="row">活動評價星等</th>
        <td>${activityOrder.activityScore ? activityOrder.activityScore : '無'}</td>
      </tr>
    `;
    document.querySelector('.modal-footer a').href = `./activity_orderEdit.html?activityOrderNo=${activityOrder.activityOrderNo}`;
    document.querySelector("#exampleModal .modal-body tbody").innerHTML = html;
  } 
})