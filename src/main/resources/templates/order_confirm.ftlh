<!doctype html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <style>
        body {
            color: black;
        }

        h2 {
            font-size: 22px;
        }

        p {
            font-size: 16px;
        }

        .order_table {
            margin-bottom: 35px;
        }

        .order_table table {
            width: 750px;
        }

        .order_table table thead tr th {
            min-width: 50%;
            text-align: center;
            padding: 15px 10px;
            border-bottom: 1px solid #ddd;

            font-size: 16px;
        }

        .order_table table tbody tr td {
            min-width: 50%;
            text-align: center;
            padding: 15px 10px;
            border-bottom: 1px solid #ddd;
        }

        .order_table table tbody tr td.counter {
            min-width: 50%;
            text-align: center;
            padding: 15px 10px;
            border-bottom: 1px solid #ddd;

            font-size: 18px;
            font-weight: 700;

            background: #e5e5e5;
        }

        .order_table table tbody tr td h5 {
            line-height: 20px;
            font-size: 16px;

            min-height: 40px;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            overflow: hidden;
            overflow-wrap: break-word;

            margin: 0 0 8px 0;
        }

        .order_table table tbody tr td p {
            font-size: 14px;
            margin: 0;
        }

        .order_table table tfoot tr th {
            min-width: 50%;
            text-align: center;
            padding: 15px 10px;
            border-bottom: 1px solid #ddd;

            font-size: 16px;
        }

        .order_table table tfoot tr td {
            min-width: 50%;
            text-align: center;
            padding: 15px 10px;
            border-bottom: 1px solid #ddd;
        }

        .table-responsive td.cart_img {
            width: 100px;
            height: 100px;
            padding: 0;
        }

        .table-responsive td.cart_img img {
            width: 100px;
            height: 100px;
            padding: 0;
        }

        .table-responsive td.orderDetailStat span {
            font-size: 14px;
        }

        .table-responsive table tbody tr td {
            border-right: 1px solid #e5e5e5;
            border-left: 1px solid #e5e5e5;
            font-weight: 500;
            text-transform: capitalize;
            font-size: 14px;
            text-align: left;
        }
    </style>
</head>

<body>

<div>
    <h2>親愛的會員 ${platformOrder.buyerName} 您好：</h2>
    <p>
        感謝您在我們的線上商城選購商品。您的訂單已成功接收並分派至各品牌櫃位進行揀貨。在此，我們希望您能享受到最優質的購物體驗。</p>
</div>

<h2 style="margin: 40px 0 15px; font-size: 20px" >您的購物明細：</h2>

<div class="order_table table-responsive">
    <table>
        <#list orderDetailMap?keys as counter>
            <tbody>
            <tr>
                <td class="counter" colspan="4">
                    ${counter}
                </td>
            </tr>
            <#list orderDetailMap[counter].product as product>
                <tr>
                    <td class="cart_img">
                        <img src="cid:image${product.productNo}" alt="">
                    </td>
                    <td class="cart_info" colspan="3">
                        <h5>${product.productName}</h5>
                        <p>單價: $${product.productPrice} / 數量: ${product.amount}</p>
                    </td>
                </tr>
            </#list>
            </tbody>
        </#list>

        <tfoot>
        <tr class="order_total">
            <th colspan="2">訂單實付金額</th>
            <th colspan="2">$${platformOrder.actuallyPaid}</th>
        </tr>
        <tr class="order_total">
            <th colspan="2">回饋點數</th>
            <th colspan="2">${platformOrder.rewardPoints} 點</th>
        </tr>
        <tr class="order_total">
            <th colspan="2">下單時間</th>
            <th colspan="2">${platformOrder.orderTime}</th>
        </tr>
        </tfoot>
    </table>
</div>
</body>


</html>