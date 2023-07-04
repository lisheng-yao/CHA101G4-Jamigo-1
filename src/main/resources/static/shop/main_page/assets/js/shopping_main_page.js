let productNo;
let cartItems = [];

$(document).ready(async function () {

    printCartItemsCount();

    // 使用 Promise.all() 等待 news_render, recommendation_render, counter_area_render(1), best_selling_render 這幾個函式都完成
    await Promise.all([news_render(), recommendation_render(), counter_area_render(1), best_selling_render()]);

    // 等待 counter_area_render(2) 完成
    await counter_area_render(2);

    // 執行 owl_carousel_init()
    owl_carousel_init();
});

function news_render() {

    return fetch("/Jamigo/ShopCarouselServlet")
        .then((response) => response.json())
        .then((data) => {
            let html_str = "";

            for (let item of data) {

                if (item['shopCarouselState'] === 0)
                    continue;

                let startTimeStr = item['shopCarouselStartTime'];
                let endTimeStr = item['shopCarouselEndTime'];

                // 將日期字串轉換為 Date 物件
                // 注意：因為日期是用中文表示月份，需要將 "月" 轉換為 "/" 才能被正確解析
                let startTime = new Date(startTimeStr.replace('月 ', '/'));
                let endTime = new Date(endTimeStr.replace('月 ', '/'));

                // 獲取當前日期（無時間部分）
                let today = new Date();
                today.setHours(0, 0, 0, 0);

                // 檢查今天的日期是否在開始時間和結束時間之間
                if (today < startTime || today > endTime)
                    continue;

                html_str += `
                    <a href="${item['shopCarouselUrl']}">
                        <div class="single_slider d-flex align-items-center" data-bgimg="/Jamigo/DBPicReader?shopCarouselNo=${item['shopCarouselNo']}">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="slider_content slider_c_three">
                                            <!-- 最新消息輪播標題 -->
                                            <h1>${item['shopCarouselTitle']}</h1>
                                            <!-- 最新消息輪播內容 -->
                                            <p>${item['shopCarouselText']}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                `;
            }

            $("div.slider_area").html(html_str);
        });

}

function recommendation_render() {

    return $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Jamigo/shop/recommendation',
        success: function (response) {

            let html_str = "";

            for (let item of response) {

                let avg_rate;

                if (item['evalTotalPeople'] === 0) avg_rate = 0; else avg_rate = Math.round(item['evalTotalScore'] / item['evalTotalPeople'] * 10) / 10;

                html_str += `
                    <div class="col-lg-3">
                        <article class="single_product">
                            <figure>
                                <div class="product_thumb">

                                    <a class="primary_img" href="/Jamigo/shop/shopping/product_detail_page.html?productNo=${item['productNo']}">
                                        <img src="/Jamigo/shop/product_picture/product/${item['productNo']}"alt="">
                                    </a>

                                    <div class="action_links">
                                        <ul>
                                            <li class="wishlist"><a onclick="addWish(this, ${item['productNo']});"><i class="fa-solid fa-heart"></i></a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <figcaption class="product_content">
                                
                                    <p class="product_no" style="display: none">${item['productNo']}</p>

                                    <!-- 商品的平均評價星等 -->
                                    <div class="product_rating">
                                        <ul>
                                            <!-- 修改「style="--rating: 4.5;"」內的「4.5」，即可控制星星 -->
                                            <div class="Stars" style="--rating: ${avg_rate};"><span>${avg_rate} (${item['evalTotalPeople']})</span>
                                            </div>
                                        </ul>
                                    </div>
                                    <!-- 櫃位名稱 -->
                                    <h5 class="counter_name">
                                        <a href="/Jamigo/shop/counter/counter_mainPage.html?counterNo=${item['counterNo']}">${item['counterName']}</a>
                                    </h5>
                                    <!-- 商品名稱 -->
                                    <h4 class="product_name">
                                        <a href="/Jamigo/shop/shopping/product_detail_page.html?productNo=${item['productNo']}">${item['productName']}</a>
                                    </h4>
                                    <div class="price_box">
                                        <span class="current_price">$${item['productPrice']}</span>
                                    </div>
                                    <div class="add_to_cart">
                                        <a href="#" data-toggle="modal" data-target="#modal_box">加入購物車</a>
                                    </div>
                                </figcaption>
                            </figure>
                        </article>
                    </div>
                `;
            }

            $("div.recommendation").html(html_str);


        }
    })
}

function counter_area_render(counterNo) {

    return $.ajax({
        type: 'GET',
        url: `http://localhost:8080/Jamigo/shop/recommendation/counter/${counterNo}`,
        success: function (response) {

            let html_str = "";

            html_str += `
                <div class="row align-items-center" style="margin-bottom: 20px">
                    <div class="col-lg-3 col-md-12">
                        <div class="single-testimonial">
    
                            <!-- 櫃位圖片 -->
                            <div class="testimonial_thumb">
                                <a href="/Jamigo/shop/counter/counter_mainPage.html?counterNo=${response[0]['counterNo']}"><img src="/Jamigo/counterPic/${response[0]['counterNo']}" alt=""></a>
                            </div>
    
                            <!-- 櫃位名稱 -->
                            <div class="testimonial_content">
    
                                <div class="testimonial_author">
                                    <a href="/Jamigo/shop/counter/counter_mainPage.html?counterNo=${response[0]['counterNo']}">${response[0]['counterName']}</a>
                                </div>
    
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-9 col-md-12">
                        <div class="testimonial_container">
                            <div class="product_carousel product_column3 owl-carousel">
            `;

            for (let item of response) {

                let avg_rate;

                if (item['evalTotalPeople'] === 0) avg_rate = 0; else avg_rate = Math.round(item['evalTotalScore'] / item['evalTotalPeople'] * 10) / 10;

                html_str += `
                    <div class="col-lg-3">
                        <article class="single_product">
                            <figure>
                                <div class="product_thumb">

                                    <a class="primary_img" href="/Jamigo/shop/shopping/product_detail_page.html?productNo=${item['productNo']}">
                                        <img src="/Jamigo/shop/product_picture/product/${item['productNo']}"alt="">
                                    </a>

                                    <div class="action_links">
                                        <ul>
                                            <li class="wishlist"><a onclick="addWish(this, ${item['productNo']});"><i class="fa-solid fa-heart"></i></a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <figcaption class="product_content">
                                
                                    <p class="product_no" style="display: none">${item['productNo']}</p>

                                    <!-- 商品的平均評價星等 -->
                                    <div class="product_rating">
                                        <ul>
                                            <!-- 修改「style="--rating: 4.5;"」內的「4.5」，即可控制星星 -->
                                            <div class="Stars" style="--rating: ${avg_rate};"><span>${avg_rate} (${item['evalTotalPeople']})</span>
                                            </div>
                                        </ul>
                                    </div>
                                    <!-- 櫃位名稱 -->
                                    <h5 class="counter_name">
                                        <a href="/Jamigo/shop/counter/counter_mainPage.html?counterNo=${item['counterNo']}">${item['counterName']}</a>
                                    </h5>
                                    <!-- 商品名稱 -->
                                    <h4 class="product_name">
                                        <a href="/Jamigo/shop/shopping/product_detail_page.html?productNo=${item['productNo']}">${item['productName']}</a>
                                    </h4>
                                    <div class="price_box">
                                        <span class="current_price">$${item['productPrice']}</span>
                                    </div>
                                    <div class="add_to_cart">
                                        <a href="#" data-toggle="modal" data-target="#modal_box">加入購物車</a>
                                    </div>
                                </figcaption>
                            </figure>
                        </article>
                    </div>
                `;
            }

            html_str += `
                            </div>
                        </div>
                    </div>
                </div>
            `;

            $("div.brand_area").append(html_str);
        }
    })
}

function best_selling_render() {
    return $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Jamigo/shop/best_selling',
        success: function (response) {

            let html_str = "";

            let ranking = 1;

            for (let item of response) {

                let avg_rate;

                if (item['evalTotalPeople'] === 0) avg_rate = 0; else avg_rate = Math.round(item['evalTotalScore'] / item['evalTotalPeople'] * 10) / 10;

                html_str += `
                    <div class="col-lg-3">
                        <article class="single_product">
                            <figure>
                                <div class="product_thumb">
                                
                                    <div class="label_product">
                                        <span class="label_sale">TOP ${ranking}</span>
                                    </div>

                                    <a class="primary_img" href="/Jamigo/shop/shopping/product_detail_page.html?productNo=${item['productNo']}">
                                        <img src="/Jamigo/shop/product_picture/product/${item['productNo']}"alt="">
                                    </a>

                                    <div class="action_links">
                                        <ul>
                                            <li class="wishlist"><a onclick="addWish(this, ${item['productNo']});"><i class="fa-solid fa-heart"></i></a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <figcaption class="product_content">
                                
                                    <p class="product_no" style="display: none">${item['productNo']}</p>

                                    <!-- 商品的平均評價星等 -->
                                    <div class="product_rating">
                                        <ul>
                                            <!-- 修改「style="--rating: 4.5;"」內的「4.5」，即可控制星星 -->
                                            <div class="Stars" style="--rating: ${avg_rate};"><span>${avg_rate} (${item['evalTotalPeople']})</span>
                                            </div>
                                        </ul>
                                    </div>
                                    <!-- 櫃位名稱 -->
                                    <h5 class="counter_name">
                                        <a href="/Jamigo/shop/counter/counter_mainPage.html?counterNo=${item['counterNo']}">${item['counterName']}</a>
                                    </h5>
                                    <!-- 商品名稱 -->
                                    <h4 class="product_name">
                                        <a href="/Jamigo/shop/shopping/product_detail_page.html?productNo=${item['productNo']}">${item['productName']}</a>
                                    </h4>
                                    <div class="price_box">
                                        <span class="current_price">$${item['productPrice']}</span>
                                    </div>
                                    <div class="add_to_cart">
                                        <a href="#" data-toggle="modal" data-target="#modal_box">加入購物車</a>
                                    </div>
                                </figcaption>
                            </figure>
                        </article>
                    </div>
                `;

                ranking++;
            }

            $("div.best-selling").html(html_str);

        }
    })
}

function owl_carousel_init() {
    // 這裡初始化carousel
    /*---slider activation---*/
    $('.slider_area').owlCarousel({
        animateOut: 'fadeOut',
        loop: true,
        nav: true,
        autoplay: true,
        autoplayTimeout: 8000,
        items: 1,
        dots: true,
        navText: ['<i class="ion-ios-arrow-left"></i>', '<i class="ion-ios-arrow-right"></i>'],
    });
    /*---product column4 activation---*/
    $('.product_column4').each(function () {

        $(this).on('changed.owl.carousel initialized.owl.carousel', function (event) {
            $(this).find('.owl-item').removeClass('last').eq(event.item.index + event.page.size - 1).addClass('last');
        }).owlCarousel({
            loop: $(this).find('.single_product').length >= 4,
            nav: true,
            autoplay: false,
            autoplayTimeout: 8000,
            items: 4,
            dots: false,
            navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                }, 450: {
                    items: 2,
                }, 768: {
                    items: 3,
                }, 992: {
                    items: 4,
                },
            }
        });
    });

    $('.product_column3').each(function () {

        $(this).on('changed.owl.carousel initialized.owl.carousel', function (event) {
            $(this).find('.owl-item').removeClass('last').eq(event.item.index + event.page.size - 1).addClass('last');
        }).owlCarousel({
            loop: $(this).find('.single_product').length >= 3,
            nav: true,
            autoplay: false,
            autoplayTimeout: 8000,
            items: 3,
            dots: false,
            navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                }, 450: {
                    items: 2,
                }, 768: {
                    items: 3,
                }, 992: {
                    items: 3,
                },
            }
        });
    });
}

$(document).on("click", "div.add_to_cart a", function () {

    productNo = parseInt($(this).closest("figcaption").children("p.product_no").text());

    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/Jamigo/products/getProductForDetailPage/${productNo}`,
        success: function (response) {

            let html_str = "";

            let avg_rate;

            if (response.evalTotalPeople === 0)
                avg_rate = 0;
            else
                avg_rate = Math.round(response.evalTotalScore / response.evalTotalPeople * 10) / 10;

            html_str += `
                <div class="col-lg-5 col-md-5 col-sm-12">
                    <div class="modal_tab">
                        <div class="tab-content product-details-large">
                            <div class="tab-pane fade show active" id="tab1" role="tabpanel">
                                <div class="modal_tab_img">
                                    <img src="/Jamigo/shop/product_picture/product/${productNo}" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-7 col-md-7 col-sm-12">
                    <div class="modal_right">
                    
                        <p class="counter_no" style="display: none">${response.counterNo}</p>
                    
                        <div class="modal_counter_name mb-10">
                            <h3>${response.counterName}</h3>
                        </div>
                        <div class="modal_product_name mb-10">
                            <h2>${response.productName}</h2>
                        </div>
                        <div class="modal_price mb-10">
                            商品價格：<span class="new_price">${response.productPrice}</span>
                        </div>
                        <div class="product_rating">
                            <ul>
                                <div class="Stars" style="--rating: ${avg_rate};"><span>${avg_rate} (${response.evalTotalPeople})</span>
                                </div>
                            </ul>
                        </div>
                        
                        <div class="variants_selects">
                            <div class="modal_add_to_cart">
                                <form action="#">
                                    <label style="font-size: 16px; font-weight: 500">商品數量：</label>
                                    <input min="1" max="100" step="1" value="1" type="number" id="product_quantity">
                                    <button type="button" style="font-size: 18px; width: 200px" class="btn_add_to_cart">加入購物車</button>
                                </form>
                            </div>
                        </div>
                        
                        <div class="modal_description mb-15">
                            <p>${response.productInfo}</p>
                        </div>
                    </div>
                </div>
            `;

            $("div.modal_body div.row").html(html_str);

        }
    })
})

//取得會員編號
function getMemberNo(){

    return parseInt(localStorage.getItem("memberNo"));
    // return 1;
}

//放入購物車
$(document).on("click", "button.btn_add_to_cart", function () {

    console.log($(this).closest("div.modal_right").find("div.modal_counter_name"));

    let memberNo = getMemberNo();

    if (!memberNo) {
        localStorage.setItem('currentPageUrl', window.location.href);
        window.location = '/Jamigo/member/login/login.html';
        return;
    }

    let counterName = $(this).closest("div.modal_right").find("div.modal_counter_name").text().trim();
    let counterNo = parseInt($(this).closest("div.modal_right").find("p.counter_no").text());
    let productName = $(this).closest("div.modal_right").find("div.modal_product_name").text().trim();
    let productPrice = $(this).closest("div.modal_right").find("div.modal_price span").text();
    let quantity = parseInt($(this).closest("div.modal_right").find("input#product_quantity").val());
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
        success: function (){

            Swal.fire({
                title: '商品已加入購物車',
                icon: 'success'
            })

            printCartItemsCount();
        },
        error: function (){
            alert("商品加入購物車失敗");
        }
    });
});

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