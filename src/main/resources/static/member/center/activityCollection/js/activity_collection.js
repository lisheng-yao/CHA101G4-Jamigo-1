let activityPace = document.querySelector('.form-content-edit .row');
// let addCollection_btn = document.querySelector('.activity-track-icon i');

let currentMemberNo = localStorage.getItem('memberNo');
let currentActivityNo;

getActivityCollection(currentMemberNo);
function getActivityCollection(memberNo){
    axios.get(`/Jamigo/activityCollection/getByMemberNo/${memberNo}`)
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

    let html = 
        `<div class="card-img-top">
            <img src="data:image/jpeg;base64, ${data.activity.activityPic}" class="card-img-top" alt="...">
        </div>
        <div class="activity-track-icon">
            <i class="fa-solid fa-heart"></i>
        </div>
        <div class="card-content">
            <div class="card-title">
            <h5>${data.activity.activityName}</h5>
            </div>
            <div class="card-text">
            <i class="fa-regular fa-clock"></i>
            <p class="card-text-startTime">${data.activity.activityRegStartTime.split('T')[0]}</p>
            <i class="fa-solid fa-chevron-right"></i>
            <p class="card-text-endTime">${data.activity.activityRegEndTime.split('T')[0]}</p>
            </div>
        </div>
        <div class="button-group mt-auto">
            <a href="/Jamigo/activity/event_detail.html?activityNo=${data.activity.activityNo}" class="btn btn-outline-primary mt-3">活動詳情</a>
            <a href="/Jamigo/activity/activity_orderSignUp.html?activityNo=${data.activity.activityNo}" class="btn btn-primary mt-3">立即報名</a>
        </div>`;

    activityPace.lastElementChild.innerHTML = html;
}

activityPace.addEventListener('click', e =>{
    if(e.target.classList.contains('activity-track-icon')) {
        let parent = e.target.parentElement;
        let addCollection_btn = parent.querySelector('.activity-track-icon');
        let link = parent.querySelector('.button-group a:last-child').href;
        let prarm = link.split('?')[1];
        let currentActivityNo = new URLSearchParams(prarm).get("activityNo");

        axios.post('/Jamigo/activityCollection/deleteByEntity', {
            activityNo : currentActivityNo,
            memberNo : currentMemberNo
        } , {
            headers : {
                'Content-Type' : 'application/json'
            }
        })
        .then(resp => {
            activityPace.innerHTML = '';
            console.log(resp);
            getActivityCollection(1);
        })
        .catch(err => console.log(err))

    }
})