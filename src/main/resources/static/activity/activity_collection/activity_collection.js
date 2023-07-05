let addCollect_btn = document.querySelector('.list-group-flush .list-group-item .btn-primary:last-child');

let currentMemberNo = localStorage.getItem('memberNo');
let currentActivityNo = 0;

// 獲取活動編號
getActivityNo();
function getActivityNo(){
    let url = location.search;
	currentActivityNo = new URLSearchParams(url).get('activityNo');
}

// 執行收藏
addCollect_btn.addEventListener('click', () => {
    // 加入收藏
    if(!addCollect_btn.getAttribute('data-add')){
        addCollect_btn.setAttribute('data-add', true);
        addCollect_btn.style.backgroundColor = '#0c6e7e';
        addCollect_btn.innerText = '已收藏';
        axios.post('/Jamigo/activityCollection/add', {
            activityNo : currentActivityNo,
            memberNo : currentMemberNo
        })
        .then(resp => console.log(resp.data))
    } else {
        // 刪除收藏
        addCollect_btn.removeAttribute('data-add');
        addCollect_btn.style.backgroundColor = '#01a5c0';
        addCollect_btn.innerText = '收藏活動';
        axios.post('/Jamigo/activityCollection/deleteByEntity', {
            activityNo : currentCounterNo,
            memberNo : currentMemberNo
        })
        .then(resp => console.log(resp.data))
    }
})