let product;
let cartItems = [];
$(function (){
    //從網址上獲取productNo
    let urlParams = new URLSearchParams(window.location.search);
    let productNo = urlParams.get(`productNo`);
    printCartItemsCount();
    $.ajax({
        url: `/Jamigo/products/getProductForDetailPage/${productNo}`,
        type: "GET",
        success: function (productWithPics) {
            console.log(productWithPics);
            product = productWithPics;

            // $("#counterNo").val(productWithPics.counterNo);
            let transProductStat = product.productStat === true ? 1 : 0;
            $("#counterName").text(product.counterName);
            $("#counterName").attr("href", `/Jamigo/shop/counter/counter_mainPage.html?counterNo=${product.counterNo}`);
            $("#counterNo").text(product.counterNo);
            $("#productName").text(product.productName);
            $("#productPrice").text(product.productPrice);
            $("#productCat").text(product.productCategory.productCatName);
            $("#productInfo").text(product.productInfo);
            $("input[name='productStatus'][value='" + transProductStat + "']").prop("checked", true);
            $("#productSaleNum").val(product.productSaleNum);
            $("#reportNumber").val(product.reportNumber);
            // $("#evalTotalPeople").val(productWithPics.evalTotalPeople);
            // $("#evalTotalScore").val(productWithPics.evalTotalScore);
            // $("#evalAvg").innerText(productWithPics.evalTotalScore / productWithPics.evalTotalPeople);
            // console.log(productWithPics.productPics[0].productPic);
            for(let i= 0; i < 4; i++) {
                let pic = (product.productPics[i].productPic == "") ?
                    "/Jamigo/shop/shopping/assets/img/noPic/noPic.jpg" :
                    'data:image/*;base64,' + product.productPics[i].productPic;

                if( i === 0){
                    $("#zoom1:not(.cloned)").attr({
                        'src': pic,
                        'data-zoom-image': pic
                    });
                }
                $(`#picture${i+1}:not(.cloned)`).attr({
                    'data-image': pic,
                    'data-zoom-image': pic
                });
                $(`#pic${i+1}:not(.cloned)`).attr('src', pic);
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            console.error(xhr);
        }
    });
    //加入購物稱
    addToCart(productNo);
});

//取得會員編號
function getMemberNo(){
    return parseInt(localStorage.getItem("memberNo"));
    // return 1;
}

//放入購物車
function addToCart(productNo){
    $("#add_to_cart").on("click", function (){
        let memberNo = getMemberNo();
        if (!memberNo) {
            localStorage.setItem('currentPageUrl', window.location.href);
            window.location = '/Jamigo/member/login/login.html';
            return;
        }
        let counterName = product.counterName;
        let counterNo = product.counterNo;
        let productName = product.productName;
        let productPrice = product.productPrice;
        let quantity = parseInt($("#product_quantity").val());
        let cartItem = {
            counterNo: counterNo,
            counterName: counterName,
            productNo: productNo,
            productName: productName,
            productPrice: productPrice,
            quantity: quantity
        };
        let cartData = {
            memberNo: memberNo,
            cartItem: cartItem
        };
        $.ajax({
            url: `/Jamigo/cart/addOneToCart`,
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(cartData),
            success: function (resp){
                // alert("商品已加入購物車" + resp);
                Swal.fire(
                    '加入成功！',
                    '商品已加入您的購物車',
                    'success'
                ).then((result) => {
                    if (result.isConfirmed) {
                        window.location.reload();   //重新整理
                    }
                });
            },
            error: function (){
                alert("商品加入購物車失敗");
            }
        });
    });
}

function printCartItemsCount(){
    let memberNo = getMemberNo();
    if(!memberNo){
        $(".main_header .mini_cart_wrapper .item_count").text(cartItems.length);
    }else {
        $.ajax({
            url: `/Jamigo/cart/getCartList/${memberNo}`,
            method: "GET",
            async: false,
            success: function (respCartItems){
                cartItems = respCartItems;
                $(".main_header .mini_cart_wrapper .item_count").text(cartItems.length);
            }
        });
    }
}

function goToCartDetailPage(){
    let memberNo = getMemberNo();
    if (!memberNo) {
        localStorage.setItem('currentPageUrl', window.location.href);
        window.location = '/Jamigo/member/login/login.html';
        return;
    }
    window.location = `/Jamigo/shop/shopping/cart_detail_page.html`;
}

function goToWishListPage(){
    let memberNo = getMemberNo();
    if (!memberNo) {
        localStorage.setItem('currentPageUrl', window.location.href);
        window.location = '/Jamigo/member/login/login.html';
        return;
    }
    window.location = `/Jamigo/member/member/wishlist/wishlist.html`;
}

// 加入追蹤(還有css tored)
function addWish(e) {
    let urlParams = new URLSearchParams(window.location.search);
    let productNo = urlParams.get(`productNo`);

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

function goToCategoryPage(){
    $("#productCat").on("click", function (){
        window.location = `/Jamigo/shop/main_page/product_category_page.html?productCatNo=${product.productCategory.productCatNo}`;
    });
}