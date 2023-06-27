let cartItems;
let btnMinus;
let btnPlus;
let productNos;
let productNames;
let productPrices;
let quantities;
let subtotals;
let counterTotals;
let memberCoupons;
let availableCounterCoupons;
let originalTotal = 0;
let counterFinalTotal;

$(function () {
    let memberNo = getMemberNo();
    //取回所有會員折價券
    getMemberCoupons();
    $.ajax({
        url: `/Jamigo/cart/getCartList/${memberNo}`,
        method: "GET",
        async: false,
        success: function (respCartItems) {
            cartItems = respCartItems;
            //依櫃位編號排序
            sortCartByCounter();
            //依櫃位區塊顯示
            showCartByCounter();
        },
        error: function (xhr, textStatus, errorThrown) {
            console.error(xhr);
        }
    });

    registerInputEvent();
    trashCanRemove();
    counterSelectChange();
    //platformSelectChange();
});

//取得會員編號
function getMemberNo() {
    // return localStorage.getItem("memberNo");
    return 1;
}

//依櫃位排序
function sortCartByCounter() {
    cartItems.sort(function (item1, item2) {
        return item1.counterNo - item2.counterNo;
    });
}

//櫃位網頁結構區塊---------------------------------------------------------------------------------------------------------
let counterArea_head = `
        <div class="row">
            <div class="col-12">
                <div class="table_desc">
                    <div class="cart_page table-responsive">
                        <table>
                            <thead>
                                <tr>
                                    <th class="counter_name" colspan="7"><a href="#">head_counterName</a></th>
                                </tr>
    
                                <tr>
                                    <th class="prductNo_title" style="display: none">商品編號</th>
                                    <th class="product_thumb_title">商品圖片</th>
                                    <th class="product_name_title">商品</th>
                                    <th class="product_price_title">單價</th>
                                    <th class="product_quantity_title">數量</th>
                                    <th class="product_total_title">總價</th>
                                    <th class="product_remove_title">刪除</th>
                                </tr>
                            </thead>
                            <tbody class="">
    `;
let counterArea_foot = `
                            </tbody>
                        </table>
                    </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-6">
                                <div class="coupon_code left">
                                    <div class="coupon_inner">
                                        <div class="show_counterCoupon d-flex">
                                            <i class="fa-solid fa-ticket fa-lg p-2"></i>
                                            <span class="counter_promotions">本櫃位適用折價券</span>
                                        </div>
                                        <div class="canUse_counterCoupon d-flex">
                                            <i class="fa fa-check p-2"></i>
                                            <span class="canUseCounterCoupon_area">已符合使用門檻</span>   
                                        </div>
                                        <div class="counterCoupon_placeholder">
                                            <select class="available_counterCoupon">
                                                foot_canUseCounterCouponOptions
                                            </select>
                                        </div>

                                        <div class="canNotUse_counterCoupon d-flex">
                                            <i class="fa-solid fa-xmark p-2"></i>
                                            <span class="canNotUseCounterCoupon_area">未符合使用門檻</span>
                                            <button type="button" class="go_shopping"
                                                onclick="window.location.href='商城首頁.html'">
                                                繼續購物
                                                <i class="fa-solid fa-bag-shopping"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6">
                                <div class="coupon_code right">
                                    <div class="price_inner">
                                        <div class="cart_subtotal">
                                            <p>商品總金額</p>
                                            <p class="counterTotal">foot_counterTotal</p>
                                        </div>
                                        <div class="cart_subtotal">
                                            <p>優惠券折價</p>
                                            <p class="counterDiscount">-0</p>
                                        </div>
                                        <div class="cart_subtotal">
                                            <p>櫃位總金額</p>
                                            <p class="counterFinalTotal">foot_counterFinalTotal</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    `;
//----------------------------------------------------------------------------------------------------------------------
//依櫃位渲染購物車清單頁面
function showCartByCounter() {
    let cart_html = "";
    let currentCounterNo = -1;
    //如果購物車沒有商品
    if (cartItems.length == 0) {
        $("#cartPanel").html(`<div>購物車尚無商品</div>`);
        return;
    }
    let counterTotal = 0;
    for (let i = 0; i < cartItems.length; i++) {
        //新櫃位進入區塊
        if (cartItems[i].counterNo != currentCounterNo) { //有新櫃位出現
            if (i > 0) {  //第一個出現的櫃位不需要結束前一個櫃位的table
                let replaced_counterArea_foot = counterArea_foot
                                                        .replace("foot_canUseCounterCouponOptions", putCanUseCounterCoupons(memberCoupons, currentCounterNo, counterTotal))
                                                        .replace("foot_counterTotal", counterTotal)
                                                        .replace("foot_counterFinalTotal", counterTotal);
                // replaced_counterArea_foot = replaced_counterArea_foot.replace("foot_counterTotalDiscount",counterTotalDiscount);
                cart_html += replaced_counterArea_foot; //結束前一個櫃位的table
            }
            //新增櫃位table標題
            let replaced_counterArea_head = counterArea_head.replace("head_counterName", cartItems[i].counterName);
            cart_html += replaced_counterArea_head;
            //紀錄新櫃位編號
            currentCounterNo = cartItems[i].counterNo;
            //累加櫃位金額到整筆訂單總額
            originalTotal += counterTotal;
            //櫃位未折扣總額
            $(".counterFinalTotal").text(originalTotal);
            //重設櫃位總和給新櫃位
            counterTotal = 0;
        }
        let subtotal = cartItems[i].productPrice * cartItems[i].quantity;
        counterTotal += subtotal;
        cart_html += `
            <tr>
                <td class="productNo" style="display: none">${cartItems[i].productNo}</td>
                <td class="product_thumb">
                    <img src="http://localhost:8080/Jamigo/shop/platform_order/product_picture/${cartItems[i].productNo}" alt="">
                </td>
                <td class="product_name"><a href="/Jamigo/shop/shopping/product_detail_page.html?productNo=${cartItems[i].productNo}">${cartItems[i].productName}</a></td>
                <td class="product_price">${cartItems[i].productPrice}</td>
                <td class="product_quantity">
                    <button type="button" class="btnMinus">-</button>
                    <input type="text" class="quantity" value="${cartItems[i].quantity}">
                    <button type="button" class="btnPlus">+</button>
                </td>
                <td class="product_total">${subtotal}</td>
                <td class="product_remove">
                    <a href="#" class="trash_delete"><i class="fa fa-trash-o"></i></a>
                </td>
            </tr>
        `;
    }
    let replaced_counterArea_foot = counterArea_foot
                                            .replace("foot_canUseCounterCouponOptions", putCanUseCounterCoupons(memberCoupons, currentCounterNo, counterTotal))
                                            .replace("foot_counterTotal", counterTotal)
                                            .replace("foot_counterFinalTotal", counterTotal);
    // replaced_counterArea_foot = replaced_counterArea_foot.replace("foot_counterTotalDiscount",counterTotalDiscount);
    cart_html += replaced_counterArea_foot; //結束前一個櫃位的table
    $("#cartPanel").html(cart_html);

    //累加櫃位金額到整筆訂單總額
    originalTotal += counterTotal;
    $(".original_total").text(originalTotal);
    let availablePlatformCoupons = putCanUseCounterCoupons(memberCoupons, 0, originalTotal);
    $(".available_platformCoupon").html(availablePlatformCoupons);
    $(".totalAfterCounterDiscount").text(originalTotal);
    $(".final_total").text(originalTotal);  //最後結帳金額
}

//註冊商品數量改變事件
function registerInputEvent() {
    btnMinus = $(".btnMinus");
    btnPlus = $(".btnPlus");
    productNos = $(".productNo");
    productNames = $(".product_name");
    productPrices = $(".product_price");
    quantities = $(".quantity");
    subtotals = $(".product_total");
    for (let i = 0; i < btnMinus.length; i++) {
        //限制blur事件在點擊+按鈕時不要被觸發
        let btnClicked = false; //追蹤按鈕被點擊狀態
        btnMinus.eq(i).on("click", function () {
            if (quantities.eq(i).val() > 1) { //數量為0時,不給減
                quantities.eq(i).val(parseInt(quantities.eq(i).val()) - 1);
            }
            quantities.eq(i).trigger("input");
        });

        btnPlus.eq(i).on("click", function (e) {
            e.stopPropagation(); //阻止bubble
            btnClicked = true; //設為被點擊
            quantities.eq(i).val(parseInt(quantities.eq(i).val()) + 1);
            quantities.eq(i).trigger("input");
        });

        quantities.eq(i).on("keypress", function (e) {
            if (e.charCode != 8 && e.charCode < 48 || e.charCode > 57) {
                console.log("testtest");
                e.preventDefault();
            }
        });

        quantities.eq(i).on("blur", function (e) {
            if (btnClicked) {
                btnClicked = false; //將按鈕追蹤flag reset
                return; //+按鈕點擊時不觸發blur事件限制
            }
            if (quantities.eq(i).val() == "") {
                quantities.eq(i).val(1);
                //加是否移除sweet alert
                Swal.fire({
                    title: '您沒有輸入商品數量',
                    text: "想要把此商品從購物清單移除嗎?",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#DA6272',
                    cancelButtonColor: '#6A8CC7',
                    confirmButtonText: '確認移除',
                    cancelButtonText: '取消'
                }).then((result) => {
                    if (result.isConfirmed) {
                        Swal.fire(
                            '移除成功！',
                            '商品已從購物車內移除',
                            'success'
                        );
                        //將商品從購物車移除
                        let memberNo = getMemberNo();
                        let cartItem = {
                            counterNo: parseInt(cartItems[i].counterNo),
                            counterName: cartItems[i].counterName,
                            productNo: parseInt(cartItems[i].productNo),
                            productName: cartItems[i].productName,
                            productPrice: parseInt(cartItems[i].productPrice),
                            // image: productImages.eq(i).attr("src"),
                            quantity: parseInt(quantities.eq(i).val())
                        };
                        let cartData = {
                            memberNo: memberNo,
                            cartItem: cartItem
                        };
                        $.ajax({
                            url: `/Jamigo/cart/deleteOneInCart`,
                            method: "POST",
                            contentType: "application/json",
                            data: JSON.stringify(cartData),
                            success: function (resp) {
                                alert("商品刪除成功" + resp);
                            },
                            error: function () {
                                alert("商品刪除失敗");
                            }
                        });
                        location.reload();
                    }
                });
            }
            quantities.eq(i).trigger("input");
        });
        //----------------------------------------------------------------------------------------------------
        quantities.eq(i).on("input", function (e) {

            let value = parseInt($(this).val());

            if (value == 0) {
                Swal.fire({
                    icon: 'error',
                    title: '您輸入的商品數量不正確！',
                    text: '請重新輸入大於0的數量',
                });
                $(this).val(1);
            }

            //------小計
            subtotals.eq(i).text(productPrices.eq(i).text() * quantities.eq(i).val());
            //------計算櫃位原始總計
            let table_counterTotal = 0;
            //找報該櫃位所屬的table
            let table = quantities.eq(i).closest('tr').closest('table');
            //table中的各項商品小計
            let table_subTotals = table.find(".product_total");
            for (let j = 0; j < table_subTotals.length; j++) {
                table_counterTotal += parseInt(table_subTotals.eq(j).text());
            }
            //即時更新櫃位原始總計
            table.parent().next().find(".counterTotal").eq(0).text(table_counterTotal);
            table.parent().next().find(".available_counterCoupon").eq(0).html(putCanUseCounterCoupons(memberCoupons, cartItems[i].counterNo, table_counterTotal));
            //------更新累加櫃位金額到整筆訂單總額
            counterTotals = $(".counterTotal");
            originalTotal = 0;
            for (let i = 0; i < counterTotals.length; i++){
                originalTotal += parseInt(counterTotals.eq(i).text());
            }
            $(".original_total").text(originalTotal);   //平台原總額
            if (quantities.eq(i).val() == "") {
                return;
            }
            let cartItem = {
                counterNo: parseInt(cartItems[i].counterNo),
                counterName: cartItems[i].counterName,
                productNo: parseInt(cartItems[i].productNo),
                productName: cartItems[i].productName,
                productPrice: parseInt(cartItems[i].productPrice),
                // image: productImages.eq(i).attr("src"),
                quantity: parseInt(quantities.eq(i).val())
            };
            console.log(cartItem);
            updateCart(cartItem); //只要有異動一律記錄下來
        });
    }
}

//更新購物車
function updateCart(cartItem) {
    let memberNo = getMemberNo();
    let cartData = {
        memberNo: memberNo,
        cartItem: cartItem
    };
    $.ajax({
        url: `/Jamigo/cart/changeOneInCart`,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(cartData),
        success: function (resp) {
            // alert("商品數量更改成功" + resp);
        },
        error: function () {
            // alert("商品數量更改失敗");
        }
    });
}

function trashCanRemove() {
    let btnTrash = $(".trash_delete");
    for (let i = 0; i < btnTrash.length; i++) {
        btnTrash.eq(i).on("click", function (e) {
            e.preventDefault();
            let memberNo = getMemberNo();
            let cartItem = {
                counterNo: parseInt(cartItems[i].counterNo),
                counterName: cartItems[i].counterName,
                productNo: parseInt(cartItems[i].productNo),
                productName: cartItems[i].productName,
                productPrice: parseInt(cartItems[i].productPrice),
                // image: productImages.eq(i).attr("src"),
                quantity: parseInt(quantities.eq(i).val())
            };
            let cartData = {
                memberNo: memberNo,
                cartItem: cartItem
            };
            $.ajax({
                url: `/Jamigo/cart/deleteOneInCart`,
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(cartData),
                success: function (resp) {
                    alert("商品刪除成功" + resp);
                },
                error: function () {
                    alert("商品刪除失敗");
                }
            });
        });
    }
}

//取回會員的所有折價券
function getMemberCoupons() {
    $.ajax({
        url: `/Jamigo/cart/getMemberCoupons/${getMemberNo()}`,
        method: "GET",
        async: false,
        contentType: "application/json",
        success: function (resp) {
            console.log(resp);
            memberCoupons = resp;
        }
    });
}

//把櫃位折價券放入各櫃位區塊
function putCanUseCounterCoupons(memberCoupons, currentCounterNo, counterTotal) {
    console.log(memberCoupons);
    //篩選可以用的櫃位折價券
    let canUseCounterCoupons = memberCoupons.filter(function (coupon) {
        return coupon.counterNo == currentCounterNo && counterTotal > coupon.couponLowest;
    });
    let canUseCounterCouponHtml = "<option value='0'>請選擇欲使用的折價券</option>";
    for (let i = 0; i < canUseCounterCoupons.length; i++) {
        canUseCounterCouponHtml += `
            <option value="${canUseCounterCoupons[i].memberCouponNo}">${canUseCounterCoupons[i].couponTypeName}, 折抵金額-${canUseCounterCoupons[i].couponPrice}</option>
        `;
    }

    return canUseCounterCouponHtml;
}


//把被選擇的折價券存入sessionStorage
function couponToSessionStorage() {
    $("#checkout_confirm").on("click", function (e) {
        e.preventDefault();
        let usedCounterCoupons = [];
        let counterCoupons = $(".available_counterCoupon");
        for (let i = 0; i < counterCoupons.length; i++) {
            if (counterCoupons.eq(i).val() != "") {
                usedCounterCoupons.push(counterCoupons.eq(i).val());
            }
        }
        sessionStorage["usedCounterCoupon"] = JSON.stringify(usedCounterCoupons);
    });
}

//櫃位折價券下拉選單change(選用折價券)
function counterSelectChange(){
    availableCounterCoupons = $(".available_counterCoupon");
    for(let i = 0; i < availableCounterCoupons.length; i++){
        availableCounterCoupons.eq(i).on("change", function (e){
            let usedCounterCoupon = $(this);
            let counterDiscount = usedCounterCoupon.find("option:selected").text().split("-")[1];
            if (counterDiscount == undefined){
                counterDiscount = 0;
            }
            // console.log(counterDiscount);
            let counterDiscountElement = $(this).closest(".coupon_code").parent().next().find(".counterDiscount");
            // console.log("test", counterDiscountElement);
            counterDiscountElement.text("-" + counterDiscount);
            counterFinalTotal = parseInt($(this).closest(".coupon_code").parent().next().find(".counterTotal").text()) - counterDiscount;
            $(".counterFinalTotal").text(counterFinalTotal);

            //計算所有櫃位折價後總額
            // let counterFinalTotals = $(".counterFinalTotal");
            // let totalAfterCounterDiscount = 0;
            // console.log("counterFinalTotals.length :", counterFinalTotals.length);
            // for (i = 0; i < counterFinalTotals.length; i++){
            //     totalAfterCounterDiscount += parseInt(counterFinalTotals.eq(i).text());
            // }
            // console.log("total",totalAfterCounterDiscount);
            // $(".totalAfterCounterDiscount").text(totalAfterCounterDiscount);
        });
    }
}

function putCanUsePlatformCoupons(memberCoupons, currentCounterNo, counterTotal) {
    console.log(memberCoupons);
    //篩選可以用的櫃位折價券
    let canUseCounterCoupons = memberCoupons.filter(function (coupon) {
        return coupon.counterNo == currentCounterNo && counterTotal > coupon.couponLowest;
    });
    let canUseCounterCouponHtml = "<option value='0'>請選擇欲使用的折價券</option>";
    for (let i = 0; i < canUseCounterCoupons.length; i++) {
        canUseCounterCouponHtml += `
            <option value="${canUseCounterCoupons[i].memberCouponNo}">${canUseCounterCoupons[i].couponTypeName}, 折抵金額-${canUseCounterCoupons[i].couponPrice}</option>
        `;
    }

    return canUseCounterCouponHtml;
}
