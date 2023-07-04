const $table = $('table#main_table');

$(function () {

    $table.bootstrapTable('destroy').bootstrapTable({
        url: `http://localhost:8080/Jamigo/shop/counter_order`,
        stickyHeader: true,
        columns: [
            {
                field: 'counterOrderNo',
                title: '櫃位訂單編號',
                align: 'center',
                valign: 'middle',
                formatter: counterOrderNoFormatter
            },
            {
                field: 'counterOrderStat',
                title: '訂單狀態',
                align: 'center',
                valign: 'middle',
                width: 120,
                formatter: counterOrderStatFormatter
            },
            {
                field: 'disbursementStat',
                title: '平台撥款狀態',
                align: 'center',
                valign: 'middle',
                width: 120,
                formatter: disbursementStatFormatter
            },
            {
                field: 'actuallyPaid',
                title: '櫃位實收金額',
                align: 'center',
                valign: 'middle',
                formatter: actuallyPaidFormatter
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
                formatter: '<button type="button" class="btn btn-primary full-info" data-bs-toggle="modal" data-bs-target="#orderDetailModal" data-bs-whatever="@mdo">訂單明細</button>'
            }
        ],
    });
})

function counterOrderNoFormatter(value) {
    return '#' + value;
}

function counterOrderStatFormatter(value) {
    if (value === 0)
        return '<span class="badge rounded-pill text-bg-secondary">訂單取消</span>'
    else if (value === 10)
        return '<span class="badge rounded-pill text-bg-warning">等待付款</span>'
    else if (value === 20)
        return '<span class="badge rounded-pill text-bg-danger">揀貨中</span>'
    else if (value === 30)
        return '<span class="badge rounded-pill text-bg-warning">等待包裝</span>'
    else if (value === 40)
        return '<span class="badge rounded-pill text-bg-warning">配送中</span>'
    else if (value === 50)
        return '<span class="badge rounded-pill text-bg-warning">等待取貨</span>'
    else if (value === 60)
        return '<span class="badge rounded-pill text-bg-warning">等待訂單完成</span>'
    else if (value === 70)
        return '<span class="badge rounded-pill text-bg-success">訂單完成</span>'
    else if (value === 80)
        return '<span class="badge rounded-pill text-bg-danger">訂單異常</span>'
}

function disbursementStatFormatter(value) {
    if (value === 0) {
        return '<span class="badge rounded-pill text-bg-danger">尚未撥款</span>'
    } else if (value === 1) {
        return '<span class="badge rounded-pill text-bg-success">已撥款</span>'
    }
}

function actuallyPaidFormatter(value) {
    return '<b>$' + value + '</b>';
}

$table.on("click", "button.full-info", function () {

    // 獲得平台訂單編號
    counterOrderNo = parseInt($(this).closest("td").siblings().eq(0).text().substring(1));

    // 找到燈箱內容元素
    let modal_body = document.querySelector("div#orderDetailModal div.modal-body");

    $.ajax({
        method: "GET",
        url: `http://localhost:8080/Jamigo/shop/counter_order/${counterOrderNo}/detail`,
        success: function (response) {

            tableContent = `
                <div class="order_table table-responsive">
                    <table>
            `;

            for (let product of response) {

                tableContent += `
                    <td class="cart_img">
                        <img src="http://localhost:8080/Jamigo/shop/product_picture/product/${product['productNo']}" alt="">
                    </td>
                    <td class="orderDetailStat">
                `;

                if (product['orderDetailStat'] === 0) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-warning">訂單取消</span>
                    `;
                } else if (product['orderDetailStat'] === 10) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-warning">等待付款</span>
                    `;
                } else if (product['orderDetailStat'] === 20) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-danger">揀貨中</span>
                    `;
                } else if (product['orderDetailStat'] === 30) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-warning">等待包裝</span>
                    `;
                } else if (product['orderDetailStat'] === 40) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-warning">配送中</span>
                    `;
                } else if (product['orderDetailStat'] === 50) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-warning">等待取貨</span>
                    `;
                } else if (product['orderDetailStat'] === 60) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-warning">等待訂單完成</span>
                    `;
                } else if (product['orderDetailStat'] === 70) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-success">訂單完成</span>
                    `;
                } else if (product['orderDetailStat'] === 81) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-danger">申請換貨</span>
                    `;
                } else if (product['orderDetailStat'] === 82) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-danger">換貨中</span>
                    `;
                } else if (product['orderDetailStat'] === 83) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-danger">申請退貨退款</span>
                    `;
                } else if (product['orderDetailStat'] === 84) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-danger">退款中</span>
                    `;
                } else if (product['orderDetailStat'] === 85) {
                    tableContent += `
                        <span class="badge rounded-pill text-bg-success">已退款</span>
                    `;
                }

                tableContent += `
                        </td>
                        <td class="cart_info">
                            <h5>${product["productName"]}</h5>
                            <p>單價: $${product["productPrice"]} / 數量: ${product["amount"]}</p>
                        </td>
                        <td>
                            <select class="form-control" id="platformOrderStat">
                                <option value="0">訂單取消</option>
                                <option value="10">等待付款</option>
                                <option value="20">揀貨中</option>
                                <option value="30">等待包裝</option>
                                <option value="40">配送中</option>
                                <option value="50">等待取貨</option>
                                <option value="60">等待訂單完成</option>
                                <option value="70">訂單完成</option>
                                <option value="80">訂單異常</option>
                            </select>
                        </td>
                    </tr>
                `;
            }

            tableContent += `
                        </tbody>
                    </table>
                </div>
            `;

            modal_body.innerHTML = tableContent;
        }
    })
});