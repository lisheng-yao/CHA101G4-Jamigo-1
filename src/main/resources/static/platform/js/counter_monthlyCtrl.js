const $table = $('table#main_table');

$(function () {

    $table.bootstrapTable('destroy').bootstrapTable({
        url: `/Jamigo/cut/all`,
        stickyHeader: true,
        columns: [
            {
                field: 'cutNo',
                title: '月結編號',
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
                field: 'counterName',
                title: '專櫃名稱',
                align: 'center',
                valign: 'middle'
            },
            {
                field: 'cutPayStat',
                title: '月結支付狀態',
                align: 'center',
                valign: 'middle',
                formatter: cutPayStatFormatter
            },
            {
                field: 'monthly',
                title: '專櫃當月收入',
                align: 'center',
                valign: 'middle',
                formatter: moneyFormatter
            },
            {
                field: 'cutPercent',
                title: '抽成比例',
                align: 'center',
                valign: 'middle',
                formatter: cutPercentFormatter
            },
            {
                field: 'cutMoney',
                title: '抽成金額',
                align: 'center',
                valign: 'middle',
                formatter: moneyFormatter
            }
        ],
    });
})

function cutPercentFormatter(value) {
    return (value * 100) + '%';
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

function cutNoFormatter(value) {
    return '#' + value;
}