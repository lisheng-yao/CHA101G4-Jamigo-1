const memberNo = 1;

let buyerName;
let buyerPhone;
let buyerEmail;

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
                                <p>單價: NT$${item["productPrice"]} / 數量: ${item["amount"]}</p>
                            </td>
                        </tr>`;
                }

                html_str += `</tbody>`;
            }

            html_str +=
                `<tfoot>
                        <tr>
                            <th colspan="2">原總金額</th>
                            <th>NT$${total_price}</th>
                        </tr>
                        <tr>
                            <th colspan="2">折價券折抵</th>
                            <th>NT$0</th>
                        </tr>
                        <tr>
                            <th colspan="2">點數折抵</th>
                            <th>NT$0</th>
                        </tr>
                        <tr class="order_total">
                            <th colspan="2">訂單實付金額</th>
                            <th>NT$${total_price}</th>
                        </tr>
                        <tr class="order_total">
                            <th colspan="2">回饋點數</th>
                            <th>NT$${total_price / 10}</th>
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

$("div.order_table").on("click", "div.create_order button",function() {

    Swal.fire({
        title: '確認送出訂單？',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '是的，送出訂單',
        cancelButtonText: '取消'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                title: '你的訂單已成功送出',
                icon: 'success'
            })

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
        }
    })

});
