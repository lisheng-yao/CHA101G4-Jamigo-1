const memberNo = parseInt(localStorage.getItem("memberNo"));

const totalCoupon = 0;
const totalPoints = 1000;

let memberAddress;
let levelName;
let levelFeedback;

$(document).ready(function () {

    setupRadioButtons('payment_method', 'payment');
    setupRadioButtons('shipping_method', 'shipping');
    setupRadioButtons('invoice_method', 'invoice');

    autoFillMemberData(memberNo);

    check_buyer_data();
});

function setupRadioButtons(name, id) {
    // 為 radio button 和相關的 label 加上 click 事件處理器
    $(`input[type=radio][name=${name}], label[for^=${id}]`).click(function (event) {
        let radioButton;

        // 判斷被點擊的是 radio button 還是 label
        if (this.tagName === 'LABEL') {
            // 如果被點擊的是 label，則根據其 'for' 屬性獲取對應的 radio button
            radioButton = $('#' + $(this).attr('for'));
        } else {
            // 如果被點擊的就是 radio button，則直接使用它
            radioButton = $(this);
        }

        // 檢查對應的 radio button 是否已被選中
        if (radioButton.hasClass('checked')) {
            // 如果已經選中，則阻止事件冒泡來避免關閉對應的 div
            event.stopImmediatePropagation();
        } else {
            // 如果未選中，將所有的 radio buttons 的 'checked' class 移除
            $(`input[type=radio][name=${name}]`).removeClass('checked');
            // 然後將 'checked' class 加到被選中的 radio button 上
            radioButton.addClass('checked');
        }
    });
}

function autoFillMemberData(memberNo) {

    $.ajax({
        type: "GET",
        url: `http://localhost:8080/Jamigo/shop/platform_order/memberData/${memberNo}`,
        success: function (response) {
            $("input#buyerName").val(response.memberName);
            $("input#buyerPhone").val(response.memberPhone);
            $("input#buyerEmail").val(response.memberEmail);

            memberAddress = response.memberAddress;
            levelName = response.memberLevelDetail.levelName;
            levelFeedback = response.memberLevelDetail.levelFeedback;

            getCartInfo(memberNo);
        }
    });
}

function getCartInfo(memberNo) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/Jamigo/shop/platform_order/cart/${memberNo}`,
        success: function (response) {

            let totalPaid = 0;
            let html_str = '<table>';

            for (let counter_name in response) {

                html_str +=
                    `<tbody>
                        <tr>
                            <td class="counter" colspan="3">${counter_name}</td>
                        </tr>`;

                for (let item of response[counter_name]) {

                    totalPaid += item["productPrice"] * item["quantity"];

                    html_str +=
                        `<tr>
                            <td class="cart_img">
                                <img src="http://localhost:8080/Jamigo/shop/product_picture/product/${item['productNo']}" alt="">
                            </td>
                            <td class="cart_info" colspan="2">
                                <h5>${item["productName"]}</h5>
                                <p>單價: $${item["productPrice"]} / 數量: ${item["quantity"]}</p>
                            </td>
                        </tr>`;
                }

                html_str += `</tbody>`;
            }

            let actuallyPaid = totalPaid - totalCoupon - totalPoints;
            // 計算回饋點數 (每消費 10 元，回饋 1 點，且乘上會員等級)
            let rewardPoints = Math.round(actuallyPaid / 10 * levelFeedback);

            html_str +=
                `<tfoot>
                        <tr>
                            <th colspan="2">原總金額</th>
                            <th>$${totalPaid}</th>
                        </tr>
                        <tr>
                            <th colspan="2"><i class="fa fa-ticket" aria-hidden="true"></i> 折價券折抵</th>
                            <th style="color: red">-$${totalCoupon}</th>
                        </tr>
                        <tr>
                            <th colspan="2"><i class="fa-solid fa-coins fa-lg"></i> 點數折抵</th>
                            <th style="color: red">-$${totalPoints}</th>
                        </tr>
                        <tr class="order_total">
                            <th colspan="2">訂單實付金額</th>
                            <th>$${actuallyPaid}</th>
                        </tr>
                        <tr class="order_total">
                            <th colspan="2">回饋點數</th>
                            <th>
                                <i class="fa-solid fa-coins fa-lg" style="color: #e7eb00;"></i> ${rewardPoints} (會員等級：${levelName})
                            </th>
                        </tr>
                    </tfoot>
                </table>
                <div class="create_order">
                    <button type="button">送出訂單</button>
                </div>`;

            $("div.order_table").html(html_str);
        }
    });
}

$("input#fillInAddress").on("change", function () {
    if (this.checked) {
        // checkbox 被勾選時執行的程式碼
        $("input.address").val(memberAddress);
        $('input.address').prop('disabled', true);
    } else {
        // checkbox 被取消勾選時執行的程式碼
        $("input.address").val("");
        $('input.address').prop('disabled', false);
    }
})

$("div.order_table").on("click", "div.create_order button", function () {

    if (!check_all_fill_in()) {
        return;
    }

    Swal.fire({
        title: '確認送出訂單？',
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '是的，送出訂單',
        cancelButtonText: '取消'
    }).then((result) => {

        if (result.isConfirmed) {

            let buyerName = $("input#buyerName").val();
            let buyerPhone = $("input#buyerPhone").val();
            let buyerEmail = $("input#buyerEmail").val();
            let paymentMethod = $("input[name='payment_method']:checked").val();
            let pickupMethod = $("input[name='shipping_method']:checked").val();
            let deliveryCountry = $("div.country ul li.selected").attr("data-value");
            let deliveryAddress = $("input.address").val();
            let invoiceMethod = $("input[name='invoice_method']:checked").val();
            let invoiceGui = $("input.invoice_gui").val();

            let platform_order_data = {
                memberNo: memberNo,
                buyerName: buyerName,
                buyerPhone: buyerPhone,
                buyerEmail: buyerEmail,
                paymentMethod: paymentMethod,
                pickupMethod: pickupMethod,
                deliveryCountry: deliveryCountry,
                deliveryAddress: deliveryAddress,
                invoiceMethod: invoiceMethod,
                invoiceGui: invoiceGui,
                totalCoupon: totalCoupon,
                totalPoints: totalPoints
            }

            // if (paymentMethod === '1') {
            //     $.ajax({
            //         type: "POST",
            //         url: "http://localhost:8080/Jamigo/shop/platform_order/ecpayCheckout",
            //         data: form_data,
            //         contentType: "application/x-www-form-urlencoded",
            //
            //         success: function (res) {
            //
            //             Swal.fire({
            //                 title: '你的訂單已成功送出',
            //                 icon: 'success',
            //                 confirmButtonText: "進入綠界金流頁面"
            //             }).then(function () {
            //                 $('body').append(res); // 將返回的表單插入到網頁中
            //                 $('#allPayAPIForm').submit(); // 自動提交表單
            //             })
            //         },
            //
            //         error: function (err) {
            //             Swal.fire({
            //                 title: '訂單送出失敗',
            //                 icon: 'error',
            //                 confirmButtonText: "關閉"
            //             })
            //         }
            //     })
            // }

            let confirm_button_text;

            if (paymentMethod === '1')
                confirm_button_text = "進入綠界金流頁面"
            else if (paymentMethod === '2')
                confirm_button_text = "回到商城首頁"

            $.ajax({
                type: "POST",
                url: "http://localhost:8080/Jamigo/shop/platform_order",
                data: JSON.stringify(platform_order_data),
                contentType: "application/json",

                success: function(res) {
                    Swal.fire({
                        title: '你的訂單已成功送出',
                        icon: 'success',
                        confirmButtonText: confirm_button_text

                    }).then(function () {

                        if (paymentMethod === '1') {
                            $('body').append(res); // 將返回的表單插入到網頁中
                            $('#allPayAPIForm').submit(); // 自動提交表單
                        }
                        else if (paymentMethod === '2')
                            window.location.href = "shopping_main_page.html";
                    })
                },

                error: function (err) {
                    Swal.fire({
                        title: '訂單送出失敗',
                        icon: 'error',
                        confirmButtonText: "關閉"
                    })
                }
            })
        }
    })
});

function check_buyer_data() {

    $("input#buyerName").on("blur", function () {

        let span_text = "*";

        if ($(this).val() === "")
            span_text += " 不得為空！";

        $(this).prev("label").children("span").text(span_text);
    })

    $("input#buyerPhone").on("blur", function () {

        let span_text = "*";

        if (!$(this).val().match("^09\\d{8}$"))
            span_text += " 不符合格式！";

        $(this).prev("label").children("span").text(span_text);
    })

    $("input#buyerEmail").on("blur", function () {

        let span_text = "*";

        if (!$(this).val().match("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
            span_text += " 不符合格式！";

        $(this).prev("label").children("span").text(span_text);
    })
}

function check_all_fill_in() {

    if ($("input#buyerName").val() === "" ||
        !$("input#buyerPhone").val().match("^09\\d{8}$") ||
        !$("input#buyerEmail").val().match("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {

        Swal.fire({
            icon: 'error',
            title: '訂購人資料輸入錯誤！'
        })

        return false;
    }

    if (!$("input[name='payment_method']:checked").length) {
        Swal.fire({
            icon: 'error',
            title: '必須選擇一種付款方式！'
        })

        return false;
    }

    if (!$("input[name='shipping_method']:checked").length) {
        Swal.fire({
            icon: 'error',
            title: '必須選擇一種取貨方式！'
        })

        return false;
    }

    if ($("#shipping2").prop("checked") && $("input.address").val() === "") {
        Swal.fire({
            icon: 'error',
            title: '住址不得為空！'
        })

        return false;
    }

    if (!$("input[name='invoice_method']:checked").length) {
        Swal.fire({
            icon: 'error',
            title: '必須選擇一種開立發票方式！'
        })

        return false;
    }

    if ($("#invoice2").prop("checked") && $("input.invoice_gui").val() === "") {
        Swal.fire({
            icon: 'error',
            title: '統一編號不得為空！'
        })

        return false;
    }

    return true;
}

