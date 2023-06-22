$(document).ready(function () {

    validqtyInput();

    changeProductAmount();

    trashCanRemove();

    // useCounterCouponAndRemove();

    usePlatformCouponAndRemove();

    validPointsInput();
});

// 只能輸入數字
function validqtyInput() {
    $("input[name='qty']").on("input", function () {
        var value = $(this).val().replace(/\D/g, ""); // 刪除非數字字符
        $(this).val(value);
    });
}

function changeProductAmount() {
    // 商品數量加減，若數量要被減至0移除清單
    $(".minus").click(function () {
        var input = $(this).next("input");
        var value = parseInt(input.val());

        if (value === 1) {
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#DA6272',
                cancelButtonColor: '#6A8CC7',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire(
                        'Deleted!',
                        'Your file has been deleted.',
                        'success'
                    ).then(() => {
                        // 移除該按鈕的父元素 <tr>
                        $(this).closest("tr").remove();
                        calculateProductTotal();
                    });
                }
            });
            return false;
        } else {
            input.val(value - 1);
            calculateProductTotal();
            return false;
        }
    });

    $(".plus").click(function () {
        var input = $(this).prev("input");
        var value = parseInt(input.val());
        input.val(value + 1);
        calculateProductTotal();
        return false;
    });

    // 輸入數量<=0或沒輸入值卻按下Enter的報錯
    $("input[name='qty']").on("keydown", function (event) {
        if (event.which === 13) {
            event.preventDefault();
            var value = parseInt($(this).val());

            if (!value || value <= 0) {
                Swal.fire({
                    icon: 'error',
                    title: '您輸入的商品數量不正確！',
                    text: '請重新輸入大於0的數量',
                });
                $(this).val(1);
            }
            calculateProductTotal();
        }
    });

    // 輸入數量<=0或沒輸入值離開輸入框的報錯
    $("input[name='qty']").on("blur", function () {
        var value = parseInt($(this).val());

        if (!value || value <= 0) {
            Swal.fire({
                icon: 'error',
                title: '您輸入的商品數量不正確！',
                text: '請重新輸入大於0的數量',
            });
            $(this).val(1);
        }
        calculateProductTotal();

    });

    // 初始計算價格
    calculateProductTotal();
}

function calculateProductTotal() {
    $("tr").each(function () {
        var price = parseInt($(this).find(".product_price").text());
        var quantity = parseInt($(this).find(".product_quantity input").val());
        var total = price * quantity;
        $(this).find(".product_total").text(total);
    });
}

function useCounterCouponAndRemove() {

}

function trashCanRemove() {
    $(document).on("click", ".product_remove a", function (e) {
        e.preventDefault(); // 防止點擊連結後跳轉頁面

        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#DA6272',
            cancelButtonColor: '#6A8CC7',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                );

                // 移除父元素 tr
                $(this).closest('tr').remove();
            }
        });
    });
}

function usePlatformCouponAndRemove() {
    $(document).on("click", ".use_platformCoupon", function () {
        var platformCouponCard = $(this).closest(".platformCoupon_card");
        var platformCouponDescription = platformCouponCard.find(".platformCoupon_description").text();
        var platformCouponDiscount = platformCouponCard.find(".platformCoupon_discount").text();

        var usedPlatformCouponItem = $("<div></div>").addClass("used_platformCoupon_item d-flex align-items-center my-1");
        var usedPlatformCouponDescription = $("<span></span>").addClass("usedPlatformCoupon_description").text(platformCouponDescription);
        var usedPlatformCouponMinus = $("<span></span>").addClass("usedPlatformCoupon_minus").text("-");
        var usedPlatformCouponDiscount = $("<span></span>").addClass("usedPlatformCoupon_discount").text(platformCouponDiscount);
        var removeButton = $("<button></button>").addClass("remove_usedPlatformCoupon").text("移除使用折價券");

        usedPlatformCouponItem.append(usedPlatformCouponDescription, usedPlatformCouponMinus, usedPlatformCouponDiscount, removeButton);
        $(".used_platformCoupon").append(usedPlatformCouponItem);

        platformCouponCard.hide();
    });

    $(document).on("click", ".remove_usedPlatformCoupon", function () {
        var usedPlatformCouponItem = $(this).closest(".used_platformCoupon_item");
        var platformCouponDescription = usedPlatformCouponItem.find(".usedPlatformCoupon_description").text();
        var platformCouponDiscount = usedPlatformCouponItem.find(".usedPlatformCoupon_discount").text();
        var platformCouponCard = $(".platformCoupon_card").filter(function () {
            return $(this).find(".platformCoupon_description").text() === platformCouponDescription &&
                $(this).find(".platformCoupon_discount").text() === platformCouponDiscount;
        });
        platformCouponCard.show();
        usedPlatformCouponItem.remove();
    });
}

function validPointsInput() {

    $("input[name='used_memberPoints']").on("input", function () {
        var value = $(this).val().replace(/\D/g, ""); // 刪除非數字字符
        $(this).val(value);
    });

}


