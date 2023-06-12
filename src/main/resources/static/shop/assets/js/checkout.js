$(document).ready(function () {

    setupRadioButtons('payment_method', 'payment');
    setupRadioButtons('shipping_method', 'shipping');
    setupRadioButtons('invoice_method', 'invoice');

    firstAutoFill();
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

function firstAutoFill() {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/Jamigo/shop/checkout/memberData", // 設定與後端相同的 url
        success: function(response) {
            $("input#memberName").val(response.memberName);
            $("input#memberPhone").val(response.memberPhone);
            $("input#memberEmail").val(response.memberEmail);// test
        }
    });
}