const $table = $('table#main_table');

$(function () {

    $table.bootstrapTable('destroy').bootstrapTable({
        url: `/Jamigo/cut/counter`,
        stickyHeader: true,
        columns: [
            {
                field: 'cutNo',
                title: '抽成月結編號',
                align: 'center',
                valign: 'middle',
                formatter: cutNoFormatter
            },
            {
                field: 'monthlyTime',
                title: '月結年月',
                align: 'center',
                valign: 'middle',
                formatter: monthlyTimeFormatter
            },
            {
                field: 'counterNo',
                title: '櫃位編號',
                align: 'center',
                valign: 'middle',
            },
            {
                field: 'monthly',
                title: '抽成前月結金額',
                align: 'center',
                valign: 'middle',
                formatter: moneyFormatter
            },
            {
                field: 'cutMonthly',
                title: '抽成後實收金額',
                align: 'center',
                valign: 'middle',
                formatter: moneyFormatter
            },
            {
                field: 'cutPayStat',
                title: '月結支付狀態',
                align: 'center',
                valign: 'middle',
                formatter: cutPayStatFormatter
            }
        ],
    });
})

function cutNoFormatter(value) {
    return '#' + value;
}

function moneyFormatter(value) {
    return '<b>$' + value + '</b>';
}

function cutPayStatFormatter(value) {
    if (value === 0) {
        return '<span class="badge rounded-pill text-bg-danger">尚未付款</span>'
    } else if (value === 1) {
        return '<span class="badge rounded-pill text-bg-success">已付款</span>'
    }
}

function monthlyTimeFormatter(value) {
    return value.substring(0, 7);
}