let product;
$(function (){
    //從網址上獲取productNo
    let urlParams = new URLSearchParams(window.location.search);
    let productNo = urlParams.get(`productNo`);

    $.ajax({
        url: `/Jamigo/products/getProductForDetailPage/${productNo}`,
        type: "GET",
        success: function (productWithPics) {
            console.log(productWithPics);
            product = productWithPics;

            // $("#counterNo").val(productWithPics.counterNo);
            let transProductStat = product.productStat === true ? 1 : 0;
            $("#counterName").text(product.counterName);
            $("#counterNo").text(product.counterNo);
            $("#productName").text(product.productName);
            $("#productPrice").text(product.productPrice);
            $("#productCat").text(product.productCategory.productCatName);
            $("#productInfo").text(product.productInfo);
            $("input[name='productStatus'][value='" + transProductStat + "']").prop("checked", true);
            $("#productSaleNum").val(product.productSaleNum);
            $("#reportNumber").val(product.reportNumber);
            // $("#evalTotalPeople").val(productWithPics.evalTotalPeople);
            // $("#evalTotalScore").val(productWithPics.evalTotalScore);
            // $("#evalAvg").innerText(productWithPics.evalTotalScore / productWithPics.evalTotalPeople);
            // console.log(productWithPics.productPics[0].productPic);
            for(let i= 0; i < 4; i++) {
                let pic = (product.productPics[i].productPic == "") ?
                    "/Jamigo/shop/shopping/assets/img/noPic/noPic.jpg" :
                    'data:image/*;base64,' + product.productPics[i].productPic;

                if( i === 0){
                    $("#zoom1:not(.cloned)").attr({
                        'src': pic,
                        'data-zoom-image': pic
                    });
                }
                $(`#picture${i+1}:not(.cloned)`).attr({
                    'data-image': pic,
                    'data-zoom-image': pic
                });
                $(`#pic${i+1}:not(.cloned)`).attr('src', pic);
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            console.error(xhr);
        }
    });
    //加入購物稱
    addToCart(productNo);
});

//取得會員編號
function getMemberNo(){
    // return localStorage.getItem("memberNo");
    return 3;
}

//放入購物車
function addToCart(productNo){
    $("#add_to_cart").on("click", function (){
        let memberNo = getMemberNo();
        let counterName = product.counterName;
        let counterNo = product.counterNo;
        let productName = product.productName;
        let productPrice = product.productPrice;
        let quantity = parseInt($("#product_quantity").val());
        let cartItem = {
            counterNo: counterNo,
            counterName: counterName,
            productNo: productNo,
            productName: productName,
            productPrice: productPrice,
            quantity: quantity
        };
        let cartData = {
            memberNo: memberNo,
            cartItem: cartItem
        }
        $.ajax({
            url: `/Jamigo/cart/addOneToCart`,
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(cartData),
            success: function (resp){
                alert("商品已加入購物車" + resp);
            },
            error: function (){
                alert("商品加入購物車失敗");
            }
        });
    });
}