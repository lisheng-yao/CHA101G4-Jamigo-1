$(document).ready(async function () {

    await news_render();
    await recommendation_render();
    await counter_area_render(1);
    await counter_area_render(10);
    owl_carousel_init();
});

function news_render() {

    fetch("/Jamigo/ShopCarouselServlet")
        .then((response) => response.json())
        .then((data) => {
            let html_str = "";

            for (let item of data) {

                console.log(item)

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
        })

}

function recommendation_render() {

    return $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Jamigo/shop/recommendation',
        success: function (response) {

            let html_str = "";

            for (let item of response) {

                let avg_rate;

                if (item['evalTotalPeople'] === 0)
                    avg_rate = 0;
                else
                    avg_rate = Math.round(item['evalTotalScore'] / item['evalTotalPeople'] * 10) / 10;

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
                                            <li class="wishlist"><a href="#" title="追蹤商品"><i class="fa fa-heart-o"
                                                                                                 aria-hidden="true"></i></a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <figcaption class="product_content">

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

                if (item['evalTotalPeople'] === 0)
                    avg_rate = 0;
                else
                    avg_rate = Math.round(item['evalTotalScore'] / item['evalTotalPeople'] * 10) / 10;

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
                                            <li class="wishlist"><a href="#" title="追蹤商品"><i class="fa fa-heart-o"
                                                                                                 aria-hidden="true"></i></a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <figcaption class="product_content">

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
                },
                450: {
                    items: 2,
                },
                768: {
                    items: 3,
                },
                992: {
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
                },
                450: {
                    items: 2,
                },
                768: {
                    items: 3,
                },
                992: {
                    items: 3,
                },
            }
        });
    });
}
