const $table = $('table#main_table');

let platformOrderNo;

$(function () {

    $table.bootstrapTable('destroy').bootstrapTable({
        url: "/Jamigo/shop/platform_order",
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
                width: 120,
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
                width: 150,
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
                valign: 'middle',
                width: 200
            },
            {
                field: 'operation',
                title: '操作',
                align: 'center',
                valign: 'middle',
                width: 250,
                formatter: '<button type="button" class="btn btn-primary full-info" data-bs-toggle="modal" data-bs-target="#orderDetailModal" data-bs-whatever="@mdo">其他資訊</button>' +
                    '<button type="button" class="btn btn-primary changeStat" data-bs-toggle="modal" data-bs-target="#orderEditModal" data-bs-whatever="@mdo">更改狀態</button>'
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
    else if (value === 10) {
        return '<span class="badge rounded-pill text-bg-warning">等待付款</span>'
    }
    else if (value === 20) {
        return '<span class="badge rounded-pill text-bg-warning">揀貨中</span>'
    }
    else if (value === 30) {
        return '<span class="badge rounded-pill text-bg-danger">等待包裝</span>'
    }
    else if (value === 60) {
        return '<span class="badge rounded-pill text-bg-warning">等待訂單完成</span>'
    }
    else if (value === 70) {
        return '<span class="badge rounded-pill text-bg-success">訂單完成</span>'
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
        return '<span class="badge rounded-pill text-bg-info">綠界</span>';
    }
    else if (value === 2) {
        return '<span class="badge rounded-pill text-bg-info">貨到付款</span>';
    }
}

function actuallyPaidFormatter(value) {
    return '<b>$' + value + '</b>';
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

    // 找到燈箱內容元素
    let modal_body = document.querySelector("div#orderDetailModal div.modal-body");

    let totalPaid;
    let totalCoupon;
    let totalPoints;
    let actuallyPaid;
    let rewardPoints;

    $.ajax({
        method: "GET",
        url: `/Jamigo/shop/platform_order/${platformOrderNo}`,
        success: function(res) {

            totalPaid = res.totalPaid;
            totalCoupon = res.totalCoupon;
            totalPoints = res.totalPoints;
            actuallyPaid = res.actuallyPaid;
            rewardPoints = res.rewardPoints;

            // 動態新增訂購人資訊
            modal_body.innerHTML = `
               <div>
                   <p><b>訂購人姓名：</b>${res.buyerName}</p>
                   <p><b>訂購人手機號碼：</b>${res.buyerPhone}</p>
                   <p><b>訂購人Email：</b>${res.buyerEmail}</p>
               </div>
            `;

            // 動態新增付款資訊
            if (res.paymentMethod === 1) {
                modal_body.innerHTML += `
                    <div>
                        <p><b>付款方式：</b>綠界</p>
                `;
            }
            else if (res.paymentMethod === 2) {
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



            $.ajax({
                method: "GET",
                url: `/Jamigo/shop/platform_order/${platformOrderNo}/detail`,
                success: function(response) {

                    tableContent  = `
                        <div class="order_table table-responsive">
                            <table>
                    `;

                    for (let counter_name in response) {
                        tableContent += `
                            <tbody>
                                <tr>
                                    <td class="counter" colspan="3">
                                        ${counter_name}
                        `;

                        if (response[counter_name]["disbursementStat"] === 0) {
                            tableContent += `
                                <span class="badge rounded-pill text-bg-danger">尚未撥款</span>
                            `;
                        }
                        else if (response[counter_name]["disbursementStat"] === 1) {
                            tableContent += `
                                <span class="badge rounded-pill text-bg-success">已撥款</span>
                            `;
                        }

                        for (let item of response[counter_name]["product"]) {
                            tableContent += `
                                    </td>
                                </tr>
                                <tr>
                                    <td class="cart_img">
                                        <img src="/Jamigo/shop/product_picture/product/${item['productNo']}" alt="">
                                    </td>
                                    <td class="orderDetailStat">
                            `;

                            if (item['orderDetailStat'] === 0) {
                                tableContent += `
                                    <span class="badge rounded-pill text-bg-secondary">訂單取消</span>
                                `;
                            }
                            else if (item['orderDetailStat'] === 10) {
                                tableContent += `
                                    <span class="badge rounded-pill text-bg-warning">等待付款</span>
                                `;
                            }
                            else if (item['orderDetailStat'] === 20) {
                                tableContent += `
                                    <span class="badge rounded-pill text-bg-warning">揀貨中</span>
                                `;
                            }
                            else if (item['orderDetailStat'] === 30) {
                                tableContent += `
                                    <span class="badge rounded-pill text-bg-danger">等待包裝</span>
                                `;
                            }
                            else if (item['orderDetailStat'] === 60) {
                                tableContent += `
                                    <span class="badge rounded-pill text-bg-warning">等待訂單完成</span>
                                `;
                            }
                            else if (item['orderDetailStat'] === 70) {
                                tableContent += `
                                    <span class="badge rounded-pill text-bg-success">訂單完成</span>
                                `;
                            }

                            tableContent += `
                                </td>
                                    <td class="cart_info">
                                        <h5>${item["productName"]}</h5>
                                        <p>單價: $${item["productPrice"]} / 數量: ${item["amount"]}</p>
                                    </td>
                                </tr>
                            `;
                        }

                        tableContent += `
                            </tbody>
                        `;
                    }

                    tableContent += `
                            <tfoot>
                                <tr>
                                    <th colspan="2">原總金額</th>
                                    <th>$${totalPaid}</th>
                                </tr>
                                <tr>
                                    <th colspan="2">折價券折抵</th>
                                    <th style="color: red">-$${totalCoupon}</th>
                                </tr>
                                <tr>
                                    <th colspan="2">點數折抵</th>
                                    <th style="color: red">-$${totalPoints}</th>
                                </tr>
                                <tr class="order_total">
                                    <th colspan="2">訂單實付金額</th>
                                    <th>$${actuallyPaid}</th>
                                </tr>
                                <tr class="order_total">
                                    <th colspan="2">回饋點數</th>
                                    <th>
                                        <i class="fa-solid fa-coins fa-lg" style="color: #e7eb00;"></i> ${rewardPoints}
                                    </th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                    `;

                    modal_body.innerHTML += tableContent;
                }
            })
        }
    })
});

$table.on("click", "button.changeStat", function () {

    // 獲得平台訂單編號
    platformOrderNo = parseInt($(this).closest("td").siblings().eq(0).text().substring(1));

    // 找到燈箱內容元素
    let modal_body = document.querySelector("div#orderEditModal div.modal-body");

    $.ajax({
        method: "GET",
        url: `/Jamigo/shop/platform_order/${platformOrderNo}`,
        success: function(res) {

            if (res.platformOrderStat === 0) {
                modal_body.innerHTML = `
                    <form>
                        <div class="mb-3">
                            <label for="platformOrderStat" class="col-form-label">訂單狀態：</label>
                            <select class="form-select" id="platformOrderStat" disabled>
                                <option value="0" selected>訂單取消</option>
                            </select>
                        </div>
                    </form>
                `
            }
            else if (res.platformOrderStat === 10) {
                modal_body.innerHTML = `
                    <form>
                        <div class="mb-3">
                            <label for="platformOrderStat" class="col-form-label">訂單狀態：</label>
                            <select class="form-select" id="platformOrderStat" disabled>
                                <option value="10" selected>等待付款</option>
                            </select>
                        </div>
                    </form>
                `
            }
            else if (res.platformOrderStat === 20) {
                modal_body.innerHTML = `
                    <form>
                        <div class="mb-3">
                            <label for="platformOrderStat" class="col-form-label">訂單狀態：</label>
                            <select class="form-select" id="platformOrderStat" disabled>
                                <option value="20" selected>揀貨中</option>
                            </select>
                        </div>
                    </form>
                `
            }
            else if (res.platformOrderStat === 30) {
                modal_body.innerHTML = `
                    <form>
                        <div class="mb-3">
                            <label for="platformOrderStat" class="col-form-label">訂單狀態：</label>
                            <select class="form-select" id="platformOrderStat">
                                <option value="30" selected>等待包裝</option>
                                <option value="60">包裝完成</option>
                            </select>
                        </div>
                    </form>
                `
            }
            else if (res.platformOrderStat === 60) {
                modal_body.innerHTML = `
                    <form>
                        <div class="mb-3">
                            <label for="platformOrderStat" class="col-form-label">訂單狀態：</label>
                            <select class="form-select" id="platformOrderStat" disabled>
                                <option value="60" selected>等待訂單完成</option>
                            </select>
                        </div>
                    </form>
                `
            }
            else if (res.platformOrderStat === 70) {
                modal_body.innerHTML = `
                    <form>
                        <div class="mb-3">
                            <label for="platformOrderStat" class="col-form-label">訂單狀態：</label>
                            <select class="form-select" id="platformOrderStat" disabled>
                                <option value="70" selected>訂單完成</option>
                            </select>
                        </div>
                    </form>
                `
            }

            let modal_footer = document.querySelector("div#orderEditModal div.modal-footer");

            if (res.platformOrderStat === 30) {
                modal_footer.innerHTML = '<button type="button" class="btn btn-primary save" data-bs-dismiss="modal">儲存</button>';
            }
            else {
                modal_footer.innerHTML = '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>';
            }
        }
    })
})

$("div#orderEditModal").on("click", "div.modal-footer button.save", function () {

    var selectedOption = $('select#platformOrderStat option:selected').val();

    let data = {
        platformOrderStat: selectedOption
    }

    $.ajax({
        url: `/Jamigo/shop/platform_order/${platformOrderNo}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(res) {
            Swal.fire({
                title: '修改成功',
                icon: 'success',
                confirmButtonText: '重新載入頁面'

            }).then(function () {
                location.reload();
            })
        },

        error: function (err) {
            Swal.fire({
                title: '修改失敗',
                icon: 'error',
                confirmButtonText: "關閉"
            })
        }
    });
})