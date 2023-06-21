const memberNo = 1;

let memberAddress;

let buyerName;
let buyerPhone;
let buyerEmail;
let paymentMethod;
let pickupMethod;
let deliveryCountry;
let deliveryAddress;
let invoiceMethod;
let invoiceGui;
let totalPaid;
let actuallyPaid;
let rewardPoints;

$(document).ready(function () {

    setupRadioButtons('payment_method', 'payment');
    setupRadioButtons('shipping_method', 'shipping');
    setupRadioButtons('invoice_method', 'invoice');


    autoFillMemberData(memberNo);
    getCartInfo(memberNo);
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
        url: `http://localhost:8080/Jamigo/shop/platform_order/memberData/${memberNo}`, // 設定與後端相同的 url
        success: function (response) {
            $("input#memberName").val(response.memberName);
            $("input#memberPhone").val(response.memberPhone);
            $("input#memberEmail").val(response.memberEmail);

            buyerName = response.memberName;
            buyerPhone = response.memberPhone;
            buyerEmail = response.memberEmail;
            memberAddress = response.memberAddress;
        }
    });
}

function getCartInfo(memberNo) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/Jamigo/shop/platform_order/cart/${memberNo}`, // 設定與後端相同的 url
        success: function (response) {

            let total_price = 0;
            let html_str = '<table>';

            for (let counter_name in response) {

                html_str +=
                    `<tbody>
                        <tr>
                            <td class="counter" colspan="3">${counter_name}</td>
                        </tr>`;

                for (let item of response[counter_name]) {

                    total_price += item["productPrice"] * item["amount"];

                    html_str +=
                        `<tr>
                            <td class="cart_img">
                                <img src="http://localhost:8080/Jamigo/shop/product_picture/${item['productNo']}/temp" alt="">
                            </td>
                            <td class="cart_info" colspan="2">
                                <h5>${item["productName"]}</h5>
                                <p>單價: $${item["productPrice"]} / 數量: ${item["amount"]}</p>
                            </td>
                        </tr>`;
                }

                html_str += `</tbody>`;
            }

            html_str +=
                `<tfoot>
                        <tr>
                            <th colspan="2">原總金額</th>
                            <th>$${total_price}</th>
                        </tr>
                        <tr>
                            <th colspan="2"><i class="fa fa-ticket" aria-hidden="true"></i> 折價券折抵</th>
                            <th>-$0</th>
                        </tr>
                        <tr>
                            <th colspan="2"><i class="fa-solid fa-coins fa-lg"></i> 點數折抵</th>
                            <th>-$0</th>
                        </tr>
                        <tr class="order_total">
                            <th colspan="2">訂單實付金額</th>
                            <th>$${total_price}</th>
                        </tr>
                        <tr class="order_total">
                            <th colspan="2">回饋點數</th>
                            <th>
                                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
                                <i class="fa-solid fa-coins fa-lg" style="color: #e7eb00;"></i> ${total_price / 10}
                            </th>
                        </tr>
                    </tfoot>
                </table>
                <div class="create_order">
                    <button type="button">送出訂單</button>
                </div>`;

            $("div.order_table").html(html_str);


            totalPaid = total_price;
            actuallyPaid = total_price;
            rewardPoints = total_price / 10;
        }
    });
}

$("input#fillInAddress").on("change", function () {
    if(this.checked) {
        // checkbox 被勾選時執行的程式碼
        $("input.address").val(memberAddress);
        $('input.address').prop('disabled', true);
    } else {
        // checkbox 被取消勾選時執行的程式碼
        $("input.address").val("");
        $('input.address').prop('disabled', false);
    }
})

$("div.order_table").on("click", "div.create_order button",function() {

    if(!check_all_fill_in()){
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
                totalPaid: totalPaid,
                actuallyPaid: actuallyPaid,
                rewardPoints: rewardPoints
            }

            $.ajax({
                type: "POST",
                url: "http://localhost:8080/Jamigo/shop/platform_order",
                data: JSON.stringify(platform_order_data),
                contentType: "application/json",
                success: function(res) {
                    console.log(res);
                }
            })

            Swal.fire({
                title: '你的訂單已成功送出',
                icon: 'success',
                confirmButtonText: "回到商城首頁"
            }).then(function () {
                window.location.href = "商城首頁.html"
            })
        }
    })
});

function check_all_fill_in() {

    if(!$("input[name='payment_method']:checked").length) {
        Swal.fire({
            icon: 'error',
            title: '錯誤',
            text: '您必須選擇一種付款方式！'
        })

        return false;
    }
    else if(!$("input[name='shipping_method']:checked").length) {
        Swal.fire({
            icon: 'error',
            title: '錯誤',
            text: '您必須選擇一種取貨方式！'
        })

        return false;
    }
    else if($("#shipping2").prop("checked") && $("input.address").val() === "") {
        Swal.fire({
            icon: 'error',
            title: '錯誤',
            text: '住址不得為空！'
        })

        return false;
    }
    else if(!$("input[name='invoice_method']:checked").length) {
        Swal.fire({
            icon: 'error',
            title: '錯誤',
            text: '您必須選擇一種開立發票方式！'
        })

        return false;
    }
    else if($("#invoice2").prop("checked") && $("input.invoice_gui").val() === "") {
        Swal.fire({
            icon: 'error',
            title: '錯誤',
            text: '統一編號不得為空！'
        })

        return false;
    }

    return true;
}

