
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
            delay: 5000,
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
        url: '/Jamigo/index/getDuringTimeAll',
        data: null,
        dataType: 'json',
        success: function (response) {
            success(response);
        },

        error: function (error) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...出了點小問題',
                text: error.status,
            });
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

// <!-- fullpage02 Initialize Swiper -->
$(document).ready(function () {

    $.ajax({
        type: 'GET',
        url: '/Jamigo/index/getpopularproduct',
        data: null,
        dataType: 'json',
        success: function (response) {
            success(response);
        },
        error: function (error) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...出了點小問題',
                text: error.status,
            });
        }
    })

    function success(response) {

        // 初始化輪播套件
        var swiper = new Swiper(".mySwiper2", {
            slidesPerView: 3,
            spaceBetween: 30,
            slidesPerGroupWithBlank: 1,
            // loop: true,
            pagination: {
                el: ".swptwo",
                clickable: true,
            },
            navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev"
            },
        });

        for (let i = 0; i < response.length; i++) {

            // 處理評價 人數/總評價數
            let nonestar = `<i class="far fa-star i"></i>`;
            let halfstar = `<i class="fa-solid fa-star-half-stroke i"></i>`;
            let allstar = `<i class="fas fa-star i"></i>`;

            let stars = nonestar + nonestar + nonestar + nonestar + nonestar;

            if (response[i].evalTotalScore !== 0 || response[i].evalTotalPeople !== 0) {

                let starvalue = response[i].evalTotalScore / response[i].evalTotalPeople;

                if (starvalue > 0 && starvalue <= 1) {
                    stars = halfstar + nonestar + nonestar + nonestar + nonestar;
                } else if (starvalue == 1) {
                    stars = allstar + nonestar + nonestar + nonestar + nonestar;
                } else if (starvalue > 1 && starvalue < 2) {
                    stars = allstar + halfstar + nonestar + nonestar + nonestar;
                } else if (starvalue == 2) {
                    stars = allstar + allstar + nonestar + nonestar + nonestar;
                } else if (starvalue > 2 && starvalue < 3) {
                    stars = allstar + allstar + halfstar + nonestar + nonestar;
                } else if (starvalue == 3) {
                    stars = allstar + allstar + allstar + nonestar + nonestar;
                } else if (starvalue > 3 && starvalue < 4) {
                    stars = allstar + allstar + allstar + halfstar + nonestar;
                } else if (starvalue == 4) {
                    stars = allstar + allstar + allstar + allstar + nonestar;
                } else if (starvalue > 4 && starvalue < 5) {
                    stars = allstar + allstar + allstar + allstar + halfstar;
                } else {
                    stars = allstar + allstar + allstar + allstar + allstar;
                }

            }

            $('#cardbefore').append(`<div class="swiper-slide card">
            <div class="card-content">
            <div class="image">
            <img src="http://localhost:8080/Jamigo/shop/product_picture/product/${response[i].productNo}" alt="">
            </div>
            <div class="media-icons">
            <a onclick="addWish(this,${response[i].productNo});"><i class="fa-solid fa-heart" style="color: #f1f2f3;"></i></a>
            </div>
            <h4 class="productname">${response[i].productName}</h4>
            <div class="productcontent">${response[i].productInfo}
            </div>
            <div class="evaluate">
            ${stars}(${response[i].evalTotalPeople})
            </div>
            <div class="button">
            <h5>特價:${response[i].productPrice}元</h5>
            <button class="seeMorebutton" onclick="gotoEditPage(${response[i].productNo});">查看更多</button>
            <button class="addCartbutton" value="${response[i].productNo}" onclick="addcart(event);">加入購物車</button>
            <div class = "saveCounterNo${response[i].productNo}" data-counterNo="${response[i].counterNo}" style="display:none;" ></div>
            <div class = "saveCounterName${response[i].productNo}" data-counterName="${response[i].counterName}" style="display:none;" ></div>
            <div class = "saveProductNo${response[i].productNo}" data-productNo="${response[i].productNo}" style="display:none;" ></div>
            <div class = "saveProductName${response[i].productNo}" data-productName="${response[i].productName}" style="display:none;" ></div>
            <div class = "saveProductPrice${response[i].productNo}" data-productPrice="${response[i].productPrice}" style="display:none;" ></div>
            </div>
            </div>
            </div>`)
        }

    }

});

function gotoEditPage(id) {
    window.location = `/Jamigo/shop/shopping/product_detail_page.html?productNo=${id}`;
}

function addcart(e) {

    const memberid = parseInt(localStorage.getItem('memberNo'));
    if (!memberid) {
        localStorage.setItem('currentPageUrl', window.location.href);
        window.location = '/Jamigo/member/login/login.html';
        return;
    }

    let productNo = e.target.value;
    let counterNo = document.querySelector('.saveCounterNo' + productNo).getAttribute("data-counterNo");
    let counterName = document.querySelector('.saveCounterName' + productNo).getAttribute("data-counterName");
    let productName = document.querySelector('.saveProductName' + productNo).getAttribute("data-productName");
    let productPrice = document.querySelector('.saveProductPrice' + productNo).getAttribute("data-productPrice");


    let cartItem = {
        counterNo: counterNo,
        counterName: counterName,
        productNo: productNo,
        productName: productName,
        productPrice: productPrice,
        quantity: 1
    };
    let cartData = {
        memberNo: memberid,
        cartItem: cartItem
    };


    $.ajax({
        url: '/Jamigo/cart/addOneToCart',
        method: 'POST',
        data: JSON.stringify(cartData),
        contentType: 'application/json',
        success: function (response) {

            Swal.fire({
                title: '成功加入購物車',
                icon: 'success',
                showCancelButton: true,
                confirmButtonText: '查看購物車',
                cancelButtonText: '繼續購物'
            }).then((result) => {
                if (result.isConfirmed)
                    window.location = `/Jamigo/shop/shopping/cart_detail_page.html`;
            })

        }
    })
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

// fullpage 04
// $(document).ready(function () {




// })



