const $table = $('table#main_table');

$(function () {

    $table.bootstrapTable('destroy').bootstrapTable({
        url: "http://localhost:8080/Jamigo/shop/platform_order",
        stickyHeader: true,
        columns: [
            {
                field: 'platformOrderNo',
                title: '平台訂單編號',
                align: 'center',
                valign: 'middle',
                formatter: platformOrderNoFormatter
            },
            {
                field: 'platformOrderStat',
                title: '訂單狀態',
                align: 'center',
                valign: 'middle',
                width: 100,
                formatter: platformOrderStatFormatter
            },
            {
                field: 'paymentStat',
                title: '付款狀態',
                align: 'center',
                valign: 'middle',
                width: 80,
                formatter: paymentStatFormatter
            },
            {
                field: 'paymentMethod',
                title: '付款方式',
                align: 'center',
                valign: 'middle',
                width: 80,
                formatter: paymentMethodFormatter
            },
            {
                field: 'actuallyPaid',
                title: '訂單實付金額',
                align: 'center',
                valign: 'middle',
                formatter: actuallyPaidFormatter
            },
            {
                field: 'pickupMethod',
                title: '取貨方式',
                align: 'center',
                valign: 'middle',
                width: 80,
                formatter: pickupMethodFormatter
            },
            {
                field: 'orderTime',
                title: '下單時間',
                align: 'center',
                valign: 'middle'
            },
            {
                field: 'operation',
                title: '操作',
                align: 'center',
                valign: 'middle',
                formatter: '<button type="button" class="btn btn-primary full-info" data-bs-toggle="modal" data-bs-target="#orderDetailModal" data-bs-whatever="@mdo">顯示完整資訊</button>' +
                    '<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#orderEditModal" data-bs-whatever="@mdo">編輯</button>'
            }
        ],
    });
})

function platformOrderNoFormatter(value) {
    return '#' + value;
}

function platformOrderStatFormatter(value) {
    if (value === 0) {
        return '<span class="badge rounded-pill text-bg-secondary">訂單取消</span>'
    }
    else if (value === 1) {
        return '<span class="badge rounded-pill text-bg-warning">等待付款</span>'
    }
    else if (value === 2) {
        return '<span class="badge rounded-pill text-bg-warning">揀貨中</span>'
    }
    else if (value === 3) {
        return '<span class="badge rounded-pill text-bg-warning">配送中</span>'
    }
    else if (value === 4) {
        return '<span class="badge rounded-pill text-bg-warning">等待取貨</span>'
    }
    else if (value === 5) {
        return '<span class="badge rounded-pill text-bg-warning">等待訂單完成</span>'
    }
    else if (value === 6) {
        return '<span class="badge rounded-pill text-bg-success">訂單完成</span>'
    }
    else if (value === 7) {
        return '<span class="badge rounded-pill text-bg-danger">訂單異常</span>'
    }
}

function paymentStatFormatter(value) {
    if (value === 0) {
        return '<span class="badge rounded-pill text-bg-danger">未付款</span>'
    }
    else if (value === 1) {
        return '<span class="badge rounded-pill text-bg-success">已付款</span>'
    }
}

function paymentMethodFormatter(value) {
    if (value === 1) {
        return '<span class="badge rounded-pill text-bg-info">信用卡</span>';
    }
    else if (value === 2) {
        return '<span class="badge rounded-pill text-bg-info">ATM 轉帳</span>';
    }
    else if (value === 3) {
        return '<span class="badge rounded-pill text-bg-info">貨到付款</span>';
    }
}

function actuallyPaidFormatter(value) {
    return '<b>NT$' + value + '</b>';
}

function pickupMethodFormatter(value) {
    if (value === 1) {
        return '<span class="badge rounded-pill text-bg-dark">店取</span>'
    }
    else if (value === 2) {
        return '<span class="badge rounded-pill text-bg-dark">宅配到府</span>'
    }
}

$table.on("click", "button.full-info", function () {

    // 獲得平台訂單編號
    platformOrderNo = parseInt($(this).closest("td").siblings().eq(0).text().substring(1));

    $.ajax({
        method: "GET",
        url: `http://localhost:8080/Jamigo/shop/platform_order/${platformOrderNo}`,
        success: function(res) {

            // 找到燈箱內容元素
            let modal_body = document.querySelector("div#orderDetailModal div.modal-body");

            // 動態新增訂購人資訊
            modal_body.innerHTML = `
               <div>
                   <p><b>訂購人姓名：</b>${res.buyerName}</p>
                   <p><b>訂購人手機：</b>${res.buyerPhone}</p>
                   <p><b>訂購人Email：</b>${res.buyerEmail}</p>
               </div>
            `;

            // 動態新增付款資訊
            if (res.paymentMethod === 1) {
                modal_body.innerHTML += `
                    <div>
                        <p><b>付款方式：</b>信用卡</p>
                `;
            }
            else if (res.paymentMethod === 2) {
                modal_body.innerHTML += `
                    <div>
                        <p><b>付款方式：</b>ATM 轉帳</p>
                `;
            }
            else if (res.paymentMethod === 3) {
                modal_body.innerHTML += `
                    <div>
                        <p><b>付款方式：</b>貨到付款</p>
                `;
            }

            // 動態新增取貨資訊
            if (res.pickupMethod === 1) {
                modal_body.innerHTML += `
                        <p><b>取貨方式：</b>店取</p>
                        <p><b>店別：</b>中壢館</p>
                    </div>
                `;
            }
            else if (res.pickupMethod === 2) {
                modal_body.innerHTML += `
                        <p><b>取貨方式：</b>宅配到府</p>
                        <p><b>宅配國家：</b>${res.deliveryCountry}</p>
                        <p><b>宅配住址：</b>${res.deliveryAddress}</p>
                   </div>
                `;
            }

            // 動態新增開立發票資訊
            if (res.invoiceMethod === 1) {
                modal_body.innerHTML += `
                    <div>
                        <p><b>開立發票方式：</b>個人發票</p>
                    </div>
                `;
            }
            else if (res.invoiceMethod === 2) {
                modal_body.innerHTML += `
                    <div>
                        <p><b>開立發票方式：</b>公司發票</p>
                        <p><b>發票統一編號：</b>${res.invoiceGui}</p>
                    </div>
                `;
            }
        }
    })
});