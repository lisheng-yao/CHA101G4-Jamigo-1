
//判斷當前頁面，導覽列按鈕停留
const currentPage = window.location.href;
const links = document.querySelectorAll('.button-01');

links.forEach(link => {
    if (link.href === currentPage) {
        link.classList.add('active');
    }
});


//登入登出
function openmemberinfo(){
    document.getElementById('signin').style.display = 'none';
    document.getElementById('memberinfo').style.display = 'flex';
}
function opensignin(){
    document.getElementById('signin').style.display = 'flex';
    document.getElementById('memberinfo').style.display = 'none';

}
( ()=> {
    const memberimg=document.querySelector('#memberimg');
    const membername=document.querySelector('#membername');
    const memberid = localStorage.getItem('memberNo');
    const memberaccount = localStorage.getItem('memberAccount');
    checklogin();//確認登入方法

    function checklogin(){
        if(memberid){
            openmemberinfo();
            memberimg.src= `/Jamigo/member/member_data/${memberid}`;
            membername.innerText='HI!  ' +  memberaccount;
        }
    }

    const logout=document.querySelector('#logoutbutton');
    logout.addEventListener('click',()=>{
        opensignin();
        localStorage.setItem('memberNo','');
        localStorage.setItem('memberAccount','');
        window.location.href = "/Jamigo/index/首頁/index.html";
    })

})();

function check(event) {

    const memberNo = localStorage.getItem('memberNo');
    if (memberNo) {
        return;
    } else {
        event.preventDefault();
        window.location.href = '/Jamigo/member/login/login.html';
    }

}

//  fullpage01 

// 初始化輪播
$(document).ready(function () {

$.ajax

    $(function () {
        $('#fluid_dg_wrap_4').fluid_dg({
            height: 'auto',
            loader: 'bar',
            pagination: false,
            thumbnails: true,
            hover: false,
            opacityOnGrid: false,
            imagePath: '',
            time: 6000,
            transPeriod: 2000,
        });
    });
})

// <!-- fullpage02 Initialize Swiper -->
    var swiper = new Swiper(".mySwiper", {
        slidesPerView: 3,
        spaceBetween: 30,
        slidesPerGroupWithBlank: true,
        loop: true,
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
    });
