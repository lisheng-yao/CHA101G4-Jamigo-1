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
                memberimg.src = '/Jamigo/frontEnd/images/somebody.jpg';
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

// 加入追蹤(還有css tored)
function addWish(e, productNo) {

    var button = e;

    let memberNo = localStorage.getItem('memberNo');

    if (!memberNo) {
        window.location.href = '/Jamigo/member/login/login.html';
        return;
    }

    $.ajax({
        type: 'GET',
        url: '/Jamigo/wishlist/checkWishedByMemberNo/' + memberNo,
        success: function (response) {
            success(response);
        },
        error: function (error) {
            Swal.fire({
                icon: 'error',
                title: '後台缺電...請稍後再試',
                text: error.status,
            });
        }
    });

    function success(response) {

        for (let i = 0; i < response.length; i++) {
            if (productNo == response[i]) {
                $.ajax({
                    type: 'GET',
                    url: '/Jamigo/wishlist/deleteone/' + memberNo + "/" + response[i],
                    success: function (response) {
                        Swal.fire({
                            icon: 'success',
                            title: '已取消追蹤',
                        }).then(() => {
                            button.classList.remove('tored');
                        })
                        return;
                    },
                    error: function (error) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...出了點小問題',
                            text: error.status,
                        }).then(() => {
                            window.location.reload();
                        })
                    }
                });
                return;
            }
        }
        $.ajax({
            type: 'GET',
            url: '/Jamigo/wishlist/addone/' + memberNo + "/" + productNo,
            success: function (response) {
                Swal.fire({
                    title: '成功加入追蹤',
                    icon: 'success',
                }).then((result) => {

                })
                button.classList.add('tored');
                return;
            },
            error: function (error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...出了點小問題',
                    text: error.status,
                }).then(() => {
                    window.location.reload();
                    return;
                })
            }
        })
    }
}