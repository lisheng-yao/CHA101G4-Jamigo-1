const counterNo = 1;

const $table = $('table#main_table');

$(function () {

    $table.bootstrapTable('destroy').bootstrapTable({
        url: `http://localhost:8080/Jamigo/shop/counter/${counterNo}/counter_order`,
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
    if (value === 20) {
        return '<span class="badge rounded-pill text-bg-danger">揀貨中</span>'
    }
    else if (value === 30) {
        return '<span class="badge rounded-pill text-bg-warning">等待包裝</span>'
    }
    else if (value === 40) {
        return '<span class="badge rounded-pill text-bg-warning">配送中</span>'
    }
    else if (value === 50) {
        return '<span class="badge rounded-pill text-bg-warning">等待取貨</span>'
    }
    else if (value === 60) {
        return '<span class="badge rounded-pill text-bg-warning">等待訂單完成</span>'
    }
    else if (value === 70) {
        return '<span class="badge rounded-pill text-bg-success">訂單完成</span>'
    }
    else if (value === 80) {
        return '<span class="badge rounded-pill text-bg-danger">訂單異常</span>'
    }
}

function disbursementStatFormatter(value) {
    if (value === 0) {
        return '<span class="badge rounded-pill text-bg-danger">尚未撥款</span>'
    }
    else if (value === 1) {
        return '<span class="badge rounded-pill text-bg-success">已撥款</span>'
    }
}

function actuallyPaidFormatter(value) {
    return '<b>$' + value + '</b>';
}