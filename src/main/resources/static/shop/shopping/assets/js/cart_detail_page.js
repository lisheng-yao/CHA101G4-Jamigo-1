let cartItems;
let btnMinus;
let btnPlus;
let productNos;
let productNames;
let productPrices;
let quantities;
let subtotals;

$(function () {
    let memberNo = getMemberNo();
    $.ajax({
        url: `/Jamigo/cart/getCartList/${memberNo}`,
        method: "GET",
        async: false,
        success: function (respCartItems){
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

});

//取得會員編號
function getMemberNo(){
    // return localStorage.getItem("memberNo");
    return 3;
}

//依櫃位排序
function sortCartByCounter(){
    cartItems.sort(function (item1, item2){
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
                                            <p class="counterDiscount">foot_counterDiscount</p>
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
function showCartByCounter(){
    let cart_html = "";
    let currentCounterNo = -1;
    //如果購物車沒有商品
    if(cartItems.length == 0){
        $("#cartPanel").html(`<div>購物車尚無商品</div>`);
        return;
    }
    let counterTotal = 0;
    for(let i = 0; i < cartItems.length; i++){
        //新櫃位進入區塊
        if(cartItems[i].counterNo != currentCounterNo){ //有新櫃位出現
            if(i > 0){  //第一個出現的櫃位不需要結束前一個櫃位的table
                let replaced_counterArea_foot = counterArea_foot.replace("foot_counterTotal",counterTotal);
                // replaced_counterArea_foot = replaced_counterArea_foot.replace("foot_counterTotalDiscount",counterTotalDiscount);
                cart_html += replaced_counterArea_foot; //結束前一個櫃位的table
            }
            //新增櫃位table標題
            let replaced_counterArea_head = counterArea_head.replace("head_counterName",cartItems[i].counterName);
            cart_html += replaced_counterArea_head;
            //紀錄新櫃位編號
            currentCounterNo = cartItems[i].counterNo;
            //重設櫃位總和給新櫃位
            counterTotal = 0;
        }
        let subtotal = cartItems[i].productPrice * cartItems[i].quantity;
        counterTotal += subtotal;
        cart_html += `
            <tr>
                <td class="productNo" style="display: none">${cartItems[i].productNo}</td>
                <td class="product_thumb">
                    <img src="http://localhost:8080/Jamigo/shop/product_picture/${cartItems[i].productNo}/temp" alt="">
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
    let replaced_counterArea_foot = counterArea_foot.replace("foot_counterTotal",counterTotal);
    // replaced_counterArea_foot = replaced_counterArea_foot.replace("foot_counterTotalDiscount",counterTotalDiscount);
    cart_html += replaced_counterArea_foot; //結束前一個櫃位的table
    $("#cartPanel").html(cart_html);
}

//註冊商品數量改變事件
function registerInputEvent(){
    btnMinus = $(".btnMinus");
    btnPlus = $(".btnPlus");
    productNos = $(".productNo");
    productNames = $(".product_name");
    productPrices = $(".product_price");
    quantities = $(".quantity");
    subtotals = $(".product_total");
    for(let i = 0; i < btnMinus.length; i++){
        btnMinus.eq(i).on("click", function (){
            if (quantities.eq(i).val() > 1) { //數量為0時,不給減
                quantities.eq(i).val(parseInt(quantities.eq(i).val()) - 1);
            }
            quantities.eq(i).trigger("input");
        });

        btnPlus.eq(i).on("click", function () {
            quantities.eq(i).val(parseInt(quantities.eq(i).val()) + 1);
            quantities.eq(i).trigger("input");
        });
        quantities.eq(i).on("keypress", function(e) {
            if (e.charCode != 8 && e.charCode < 48 || e.charCode > 57) {
                console.log("testtest");
                e.preventDefault();
            }
        });
        quantities.eq(i).on("input", function(e) {
            console.log(1111, $(this).val());
            let value = parseInt($(this).val());
            console.log(value);

            if (value == 0) {
                Swal.fire({
                    icon: 'error',
                    title: '您輸入的商品數量不正確！',
                    text: '請重新輸入大於0的數量',
                });
                $(this).val(1);
            }

            //小計
            subtotals.eq(i).text(productPrices.eq(i).text() * quantities.eq(i).val());
            //table中的店面總計
            let table_counterTotal = 0;
            //input->td->tr->tbody->table
            let table = quantities.eq(i).closest('tr').closest('table');
            //table中的各項小計
            let table_subTotals = table.find(".product_total");
            for (let j = 0; j < table_subTotals.length; j++) {
                table_counterTotal += parseInt(table_subTotals.eq(j).text());
            }
            console.log("total:",table_counterTotal);
            console.log("table:",table);
            table.parent().next().find(".counterTotal").eq(0).text(table_counterTotal);
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
function updateCart(cartItem){
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
        success: function (resp){
            // alert("商品數量更改成功" + resp);
        },
        error: function (){
            // alert("商品數量更改失敗");
        }
    });
}

function trashCanRemove(){
    let btnTrash = $(".trash_delete");
    for(let i = 0; i < btnTrash.length; i++){
        btnTrash.eq(i).on("click", function (e){
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
                success: function (resp){
                    alert("商品刪除成功" + resp);
                },
                error: function (){
                    alert("商品刪除失敗");
                }
            });
        });
    }
}



