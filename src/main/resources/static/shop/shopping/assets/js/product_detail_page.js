$(function (){
    //從網址上獲取productNo
    let urlParams = new URLSearchParams(window.location.search);
    let productNo = urlParams.get(`productNo`);

    $.ajax({
        url: `/Jamigo/products/getProductForDetailPage/${productNo}`,
        type: "GET",
        success: function (productWithPics) {
            console.log(productWithPics);

            // $("#counterNo").val(productWithPics.counterNo);
            let transProductStat = productWithPics.productStat === true ? 1 : 0;
            $("#counterName").text(productWithPics.counterName);
            $("#counterNo").text(productWithPics.counterNo);
            $("#productName").text(productWithPics.productName);
            $("#productPrice").text(productWithPics.productPrice);
            $("#productCat").text(productWithPics.productCategory.productCatName);
            $("#productInfo").text(productWithPics.productInfo);
            $("input[name='productStatus'][value='" + transProductStat + "']").prop("checked", true);
            $("#productSaleNum").val(productWithPics.productSaleNum);
            $("#reportNumber").val(productWithPics.reportNumber);
            // $("#evalTotalPeople").val(productWithPics.evalTotalPeople);
            // $("#evalTotalScore").val(productWithPics.evalTotalScore);
            // $("#evalAvg").innerText(productWithPics.evalTotalScore / productWithPics.evalTotalPeople);
            // console.log(productWithPics.productPics[0].productPic);
            for(let i= 0; i < 4; i++) {
                let pic = (productWithPics.productPics[i].productPic == "") ?
                    "/Jamigo/shop/shopping/assets/img/noPic/noPic.jpg" :
                    'data:image/*;base64,' + productWithPics.productPics[i].productPic;

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
            // if (productWithPics.productPics && productWithPics.productPics.length >= 1) {
            //     if(productWithPics.productPics[0].productPic != ""){
            //         $("#zoom1:not(.cloned)").attr({
            //             'src': 'data:image/*;base64,' + productWithPics.productPics[0].productPic,
            //             'data-zoom-image': 'data:image/*;base64,' + productWithPics.productPics[0].productPic
            //         });
            //         $("#picture1:not(.cloned)").attr({
            //             'data-image': 'data:image/*;base64,' + productWithPics.productPics[0].productPic,
            //             'data-zoom-image': 'data:image/*;base64,' + productWithPics.productPics[0].productPic
            //         });
            //         $("#pic1:not(.cloned)").attr('src', 'data:image/*;base64,' + productWithPics.productPics[0].productPic);
            //     }
            // }
            // if (productWithPics.productPics && productWithPics.productPics.length >= 2) {
            //     if(productWithPics.productPics[1].productPic != "") {
            //         $("#picture2:not(.cloned)").attr({
            //             'data-image': 'data:image/*;base64,' + productWithPics.productPics[1].productPic,
            //             'data-zoom-image': 'data:image/*;base64,' + productWithPics.productPics[1].productPic
            //         });
            //         $("#pic2:not(.cloned)").attr('src', 'data:image/*;base64,' + productWithPics.productPics[1].productPic);
            //     }
            // }
            // if (productWithPics.productPics && productWithPics.productPics.length >= 3) {
            //     if(productWithPics.productPics[2].productPic != "") {
            //         $("#picture3:not(.cloned)").attr({
            //             'data-image': 'data:image/*;base64,' + productWithPics.productPics[2].productPic,
            //             'data-zoom-image': 'data:image/*;base64,' + productWithPics.productPics[2].productPic
            //         });
            //         $("#pic3:not(.cloned)").attr('src', 'data:image/*;base64,' + productWithPics.productPics[2].productPic);
            //     }
            // }
            // if (productWithPics.productPics && productWithPics.productPics.length >= 4) {
            //     if(productWithPics.productPics[3].productPic != "") {
            //         $("#picture4:not(.cloned)").attr({
            //             'data-image': 'data:image/*;base64,' + productWithPics.productPics[3].productPic,
            //             'data-zoom-image': 'data:image/*;base64,' + productWithPics.productPics[3].productPic
            //         });
            //         $("#pic4:not(.cloned)").attr('src', 'data:image/*;base64,' + productWithPics.productPics[3].productPic);
            //     }
            // }
        },
        error: function (xhr, textStatus, errorThrown) {
            console.error(xhr);
        }
    });
    //加入購物稱
    addToCart(parseInt(productNo));
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
        let counterName = $("#counterName").text();
        let counterNo = parseInt($("#counterNo").text());
        let productName = $("#productName").text();
        let productPrice = parseInt($("#productPrice").text());
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