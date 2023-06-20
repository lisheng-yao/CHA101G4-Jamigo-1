// 函式：更新購物車
function updateCart() {

    // 定義購物車內可顯示的最大商品數量
    let maxItems = 3;
    // 選取所有購物車內的商品
    let $cartItems = $(".main_header .cart_gallery .cart_item");
    // 計算超過最大商品數量的商品數
    let excessItems = $cartItems.length - maxItems;

    // 將購物車右上角的商品數量文字更新為目前購物車內的商品數量
    $(".main_header .mini_cart_wrapper .item_count").text($cartItems.length);

    // 先移除上一次更新購物車時添加的 "還有其他 X 件商品" 元素
    $(".cart_gallery .excess_items").remove();

    // 先刪除先前可能添加的 "尚未加入商品" 元素
    $(".cart_gallery .empty_cart_message").hide();

    $(".mini_cart .recent_add").text('近期加入');  // 先回復「近期加入」字樣

    $(".mini_cart_table").show();
    $(".mini_cart_footer").show();

    if ($cartItems.length === 0) {
        $(".mini_cart .recent_add").text('');  // 清除「近期加入」字樣

        $(".mini_cart_table").hide();
        $(".mini_cart_footer").hide();

        // 如果購物車內沒有商品，則顯示 "尚未加入商品" 提示
        $(".cart_gallery .empty_cart_message").show();
    }
    // 如果超過最大商品數量的商品數量大於0，則需要隱藏部分商品並顯示 "還有其他 X 件商品" 的訊息
    else if (excessItems > 0) {

        // 顯示所有商品
        $cartItems.show();
        // 將超過最大商品數量的商品隱藏
        $cartItems.slice(maxItems).hide();

        // 在購物車內添加 "還有其他 X 件商品" 的訊息
        $(".cart_gallery").append('<div class="excess_items">還有其他 ' + excessItems + ' 件商品</div>');
    } else {
        // 如果所有商品都在最大商品數量內，則顯示所有商品
        $cartItems.show();
    }

    // 初始化總金額為0
    var total = 0;
    // 逐一處理每個商品
    $cartItems.each(function () {
        // 取得商品的數量和價格資訊
        var itemText = $(this).find('.cart_info p').text();
        // 從文字中分割出商品數量並轉換為整數
        var quantity = parseInt(itemText.split('x')[0].trim());
        // 從文字中分割出商品價格並轉換為整數
        var price = parseInt(itemText.split('NT$')[1].trim());

        // 將此商品的總價格（數量 x 價格）加到總金額
        total += quantity * price;
    });
    // 更新顯示的總金額，不需要保留小數
    $('.cart_total .price').text('NT$' + total);
}

// 在購物車內的商品移除按鈕被點擊時觸發
$(document).on("click", ".cart_remove", function () {
    // 從 DOM 中移除被點擊的商品
    $(this).closest(".cart_item").remove();
    // 更新購物車以反映商品已被移除
    updateCart();

    return false;  // 阻止連結的預設行為，即不執行連結跳轉
});

// 在網頁載入時更新一次購物車
updateCart();
