//判斷當前頁面，導覽列按鈕停留
const currentPage = window.location.href;
const links = document.querySelectorAll('.button-01');

links.forEach(link => {
    if (link.href === currentPage) {
        link.classList.add('active');
    }
});


//登入登出
function openmemberinfo() {
    document.getElementById('signin').style.display = 'none';
    document.getElementById('memberinfo').style.display = 'flex';
}

function opensignin() {
    document.getElementById('signin').style.display = 'flex';
    document.getElementById('memberinfo').style.display = 'none';

}

(() => {
    const memberimg = document.querySelector('#memberimg');
    const membername = document.querySelector('#membername');
    let memberid = localStorage.getItem('memberNo');
    if (typeof memberid === 'undefined' || memberid === null ||memberid ==="") {
        memberid = sessionStorage.getItem('memberNo');
    }
    let memberaccount = localStorage.getItem('memberAccount');
    if (typeof memberaccount === 'undefined' || memberaccount === null|| memberaccount ==="") {
        memberaccount = sessionStorage.getItem('memberAccount');
    }
    console.log("memberaccount" + memberaccount+"ccc")
    checklogin();//確認登入方法

    function checklogin() {
        if (memberid) {
            openmemberinfo();

            let img = new Image();
            img.onload = function() {
                // 图片加载成功
                memberimg.src = `/Jamigo/member/member_data/${memberid}`;
            };
            img.onerror = function() {
                // 图片加载失败，使用默认图片路径
                memberimg.src = '/Jamigo/member/member/image/gray.jpg';
            };
            img.src = `/Jamigo/member/member_data/${memberid}`;

            membername.innerText = 'HI! ' + memberaccount;
        }
    }


    const logout = document.querySelector('#logoutbutton');
    logout.addEventListener('click', () => {
        opensignin();
        localStorage.setItem('memberNo', '');
        localStorage.setItem('memberAccount', '');
        sessionStorage.setItem('memberNo', '');
        sessionStorage.setItem('memberAccount', '');
        window.location.href = "/Jamigo/member/login/login.html";
    })

})();

function check(event) {

    let memberNo = localStorage.getItem('memberNo');
    if (typeof memberNo === 'undefined' || memberNo === null||memberNo === "") {
        memberNo = sessionStorage.getItem('memberNo');
    }
    if (memberNo) {
        return;
    } else {
        event.preventDefault();
        window.location.href = '/Jamigo/member/login/login.html';
    }

}