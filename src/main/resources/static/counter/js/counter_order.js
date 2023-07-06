const $table = $('table#main_table');

let counterOrderNo;

$(function () {

    $table.bootstrapTable('destroy').bootstrapTable({
        url: `/Jamigo/shop/counter_order`,
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
                field: 'totalPaid',
                title: '原總金額',
                align: 'center',
                valign: 'middle',
                formatter: actuallyPaidFormatter
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
                valign: 'middle',
                width: 200
            },
            {
                field: 'operation',
                title: '操作',
                align: 'center',
                valign: 'middle',
                width: 150,
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
        url: `/Jamigo/shop/counter_order/${counterOrderNo}/detail`,
        success: function (response) {

            tableContent = `
                <div class="order_table table-responsive">
                    <table>
            `;

            for (let product of response) {

                tableContent += `
                    <tr class="${product['productNo']}">
                        <td class="cart_img">
                            <img src="/Jamigo/shop/product_picture/product/${product['productNo']}" alt="">
                        </td>
                        <td class="orderDetailStat">
                `;

                if (product['orderDetailStat'] === 0) {
                    tableContent += `
                                <span class="badge rounded-pill text-bg-secondary">訂單取消</span>
                            </td>
                            <td class="cart_info">
                                <h5>${product["productName"]}</h5>
                                <p>單價: $${product["productPrice"]} / 數量: ${product["amount"]}</p>
                            </td>
                            <td>
                                <h5>修改狀態：</h5>
                                <select class="form-select" id="platformOrderStat" disabled>
                                    <option value="0" selected>訂單取消</option>
                                </select>
                            </td>
                        </tr>
                    `;
                } else if (product['orderDetailStat'] === 10) {
                    tableContent += `
                                <span class="badge rounded-pill text-bg-warning">等待付款</span>
                            </td>
                            <td class="cart_info">
                                <h5>${product["productName"]}</h5>
                                <p>單價: $${product["productPrice"]} / 數量: ${product["amount"]}</p>
                            </td>
                            <td>
                                <h5>修改狀態：</h5>
                                <select class="form-select" id="platformOrderStat" disabled>
                                    <option value="10" selected>等待付款</option>
                                </select>
                            </td>
                        </tr>
                    `;
                } else if (product['orderDetailStat'] === 20) {
                    tableContent += `
                                <span class="badge rounded-pill text-bg-danger">揀貨中</span>
                            </td>
                            <td class="cart_info">
                                <h5>${product["productName"]}</h5>
                                <p>單價: $${product["productPrice"]} / 數量: ${product["amount"]}</p>
                            </td>
                            <td>
                                <h5>修改狀態：</h5>
                                <select class="form-select" id="platformOrderStat">
                                    <option value="20" selected>揀貨中</option>
                                    <option value="30">等待包裝</option>
                                </select>
                            </td>
                        </tr>
                    `;
                } else if (product['orderDetailStat'] === 30) {
                    tableContent += `
                                <span class="badge rounded-pill text-bg-warning">等待包裝</span>
                            </td>
                            <td class="cart_info">
                                <h5>${product["productName"]}</h5>
                                <p>單價: $${product["productPrice"]} / 數量: ${product["amount"]}</p>
                            </td>
                            <td>
                                <h5>修改狀態：</h5>
                                <select class="form-select" id="platformOrderStat" disabled>
                                    <option value="30" selected>等待包裝</option>
                                </select>
                            </td>
                        </tr>
                    `;
                } else if (product['orderDetailStat'] === 40) {
                    tableContent += `
                                <span class="badge rounded-pill text-bg-warning">配送中</span>
                            </td>
                            <td class="cart_info">
                                <h5>${product["productName"]}</h5>
                                <p>單價: $${product["productPrice"]} / 數量: ${product["amount"]}</p>
                            </td>
                            <td>
                                <h5>修改狀態：</h5>
                                <select class="form-select" id="platformOrderStat" disabled>
                                    <option value="40" selected>配送中</option>
                                </select>
                            </td>
                        </tr>
                    `;
                } else if (product['orderDetailStat'] === 50) {
                    tableContent += `
                                <span class="badge rounded-pill text-bg-warning">等待取貨</span>
                            </td>
                            <td class="cart_info">
                                <h5>${product["productName"]}</h5>
                                <p>單價: $${product["productPrice"]} / 數量: ${product["amount"]}</p>
                            </td>
                            <td>
                                <h5>修改狀態：</h5>
                                <select class="form-select" id="platformOrderStat" disabled>
                                    <option value="50" selected>等待取貨</option>
                                </select>
                            </td>
                        </tr>
                    `;
                } else if (product['orderDetailStat'] === 60) {
                    tableContent += `
                                <span class="badge rounded-pill text-bg-warning">等待訂單完成</span>
                            </td>
                            <td class="cart_info">
                                <h5>${product["productName"]}</h5>
                                <p>單價: $${product["productPrice"]} / 數量: ${product["amount"]}</p>
                            </td>
                            <td>
                                <h5>修改狀態：</h5>
                                <select class="form-select" id="platformOrderStat" disabled>
                                    <option value="60" selected>等待訂單完成</option>
                                </select>
                            </td>
                        </tr>
                    `;
                } else if (product['orderDetailStat'] === 70) {
                    tableContent += `
                                <span class="badge rounded-pill text-bg-success">訂單完成</span>
                            </td>
                            <td class="cart_info">
                                <h5>${product["productName"]}</h5>
                                <p>單價: $${product["productPrice"]} / 數量: ${product["amount"]}</p>
                            </td>
                            <td>
                                <h5>修改狀態：</h5>
                                <select class="form-select" id="platformOrderStat" disabled>
                                    <option value="70" selected>訂單完成</option>
                                </select>
                            </td>
                        </tr>
                    `;
                }
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

$("div.modal-footer button.save").on("click", function () {
    // 獲得平台訂單編號
    // counterOrderNo = parseInt($(this).closest("td").siblings().eq(0).text().substring(1));

    var products = {
        editOrderDetailDTOList: []
    };

    $('div.modal-body tbody tr').each(function() {
        var productNo = $(this).attr('class');
        var orderDetailStat = $(this).find('select[id="platformOrderStat"] option:selected').val();

        products.editOrderDetailDTOList.push({
            productNo: parseInt(productNo),
            orderDetailStat: parseInt(orderDetailStat)
        });
    });

    var jsonData = JSON.stringify(products);

    console.log(jsonData)

    $.ajax({
        url: `/Jamigo/shop/counter_order/${counterOrderNo}/detail`,
        type: 'PUT',
        contentType: 'application/json',
        data: jsonData,
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