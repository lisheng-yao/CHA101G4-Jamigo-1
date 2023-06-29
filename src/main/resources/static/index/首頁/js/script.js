
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
    const memberid = localStorage.getItem('memberNo');
    const memberaccount = localStorage.getItem('memberAccount');
    checklogin();//確認登入方法

    function checklogin() {
        if (memberid) {
            openmemberinfo();
            memberimg.src = `/Jamigo/member/member_data/${memberid}`;
            membername.innerText = 'HI!  ' + memberaccount;
        }
    }

    const logout = document.querySelector('#logoutbutton');
    logout.addEventListener('click', () => {
        opensignin();
        localStorage.setItem('memberNo', '');
        localStorage.setItem('memberAccount', '');
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
$(document).ready(function () {

    // 初始化swiper
    var swiper = new Swiper(".mySwiper1", {
        effect: "coverflow",
        loop: true,
        autoplay: {
            delay: 6000, // 延遲時間，單位為毫秒
            disableOnInteraction: false, // 使用者互動後是否停止自動播放
          },
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        coverflowEffect: {
            rotate: 50,
            stretch: 0,
            depth: 100,
            modifier: 1,
            slideShadows: true,
        },
    });


    $.ajax({
        type: 'GET',
        url: '/Jamigo/index/getAll',
        data: null,
        dataType: 'json',
        success: function (response) {
            success(response);
        },

        error: function (error) {
            alert(error.status);
        }
    })

    function success(response) {

        for (let i = 0; i < response.length; i++) {

            var byteArray = response[i].mainpageCarouselPic;
            var imageUrl = 'data:image/png;base64,' + byteArray;

            $('#imgeditcontainer').append(`
        <div class="swiper-slide">
            <img src="${imageUrl}" />`)
        }
    }


});


// 初始化輪播
// $(document).ready(function () {

//     $.ajax({
//         type: 'GET',
//         url: '/Jamigo/index/getAll',
//         data: null,
//         dataType: 'json',
//         success: function (response) {
//             success(response);
//         },
//         error: function (error) {
//             alert(error.status);
//         }
//     })

//     function success(response) {
//         $('#fluid_dg_wrap_4').fluid_dg({
//             height: 'auto',
//             loader: 'bar',
//             pagination: false,
//             thumbnails: true,
//             hover: false,
//             opacityOnGrid: false,
//             imagePath: '',
//             time: 6000,
//             transPeriod: 2000,
//         });

//         alert("近來")
//         for (let i = 0; i < response.length; i++) {

//             var byteArray = response[i].mainpageCarouselPic;
//             var imageUrl = 'data:image/png;base64,' + byteArray;

// 創建一個圖片元素
//             var img = new Image();
// img.url = imageUrl;

//             img.onload = function () {
//                 // 在這裡可以對圖片進行操作，如顯示在網頁上或保存到本地
//                 // var div = document.createElement('div');
//                 // div.setAttribute('data-src', imageUrl);
//                 // var imglocation = document.getElementById('imglocation');
//                 // imglocation.insertAdjacentElement('afterend',div);
//                 $(`<div data-src="${imageUrl}"></div>`).insertAfter('.main_before');
//             }

//         }


//     }

// })

// <!-- fullpage02 Initialize Swiper -->
$(document).ready(function () {
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
});