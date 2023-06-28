<!doctype html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <style>
        .order_table {
            margin-bottom: 35px;
            font-family: "Noto Sans TC", "arial";
        }

        .order_table table {
            width: 100%;
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

            font-size: 20px;
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
        }

        .order_table table tbody tr td p {
            font-size: 14px;
            margin-bottom: 0;
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

        .table-responsive td.orderDetailStat {
            width: 120px;
            text-align: center;

            font-family: var(--bs-body-font-family);
        }

        .table-responsive td.orderDetailStat span{
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

    <div class="order_table table-responsive">
        <table>
            <tbody>
            <tr>
                <td class="counter" colspan="3">Nike</td>
            </tr>

            <tr>
                <td class="cart_img">
                    <img src="cid:productPicture" alt="">
                </td>
                <td class="cart_info" colspan="2">
                    <h5>(多文字測試)鞋子1鞋子1鞋子1鞋子1</h5>
                    <p>單價: NT$1234 / 數量: 1</p>
                </td>
            </tr>
            </tbody>


            <tfoot>
            <tr>
                <th colspan="2">原總金額</th>
                <th>NT$1000</th>
            </tr>
            <tr>
                <th colspan="2">折價券折抵</th>
                <th>NT$-100</th>
            </tr>
            <tr>
                <th colspan="2">點數折抵</th>
                <th>NT$-100</th>
            </tr>
            <tr class="order_total">
                <th colspan="2">訂單實付金額</th>
                <th>NT$800</th>
            </tr>
            </tfoot>
        </table>
    </div>
</body>

</html>