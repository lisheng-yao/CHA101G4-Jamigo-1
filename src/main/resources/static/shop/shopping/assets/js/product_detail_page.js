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
            $("#productName").val(productWithPics.productName);
            $("#productPrice").val(productWithPics.productPrice);
            $("#productDescription").val(productWithPics.productInfo);
            $("input[name='productStatus'][value='" + transProductStat + "']").prop("checked", true);
            $("#productSaleNum").val(productWithPics.productSaleNum);
            $("#reportNumber").val(productWithPics.reportNumber);
            // $("#evalTotalPeople").val(productWithPics.evalTotalPeople);
            // $("#evalTotalScore").val(productWithPics.evalTotalScore);
            // $("#evalAvg").innerText(productWithPics.evalTotalScore / productWithPics.evalTotalPeople);
            // console.log(productWithPics.productPics[0].productPic);
            if (productWithPics.productPics && productWithPics.productPics.length >= 1) {
                if(productWithPics.productPics[0].productPic != ""){
                    $("#imagePreview1").attr('src', 'data:image/*;base64,' + productWithPics.productPics[0].productPic).show();
                }else {
                    $("#imagePreview1").attr('src', '/Jamigo/shop/shopping/assets/img/noPic/noPic.jpg' + productWithPics.productPics[0].productPic).show();
                }
            }
            if (productWithPics.productPics && productWithPics.productPics.length >= 2) {
                if(productWithPics.productPics[1].productPic != "") {
                    $("#imagePreview2").attr('src', 'data:image/*;base64,' + productWithPics.productPics[1].productPic).show();
                }else {
                    $("#imagePreview1").attr('src', '/Jamigo/shop/shopping/assets/img/noPic/noPic.jpg' + productWithPics.productPics[0].productPic).show();
                }
            }
            if (productWithPics.productPics && productWithPics.productPics.length >= 3) {
                if(productWithPics.productPics[2].productPic != "") {
                    $("#imagePreview3").attr('src', 'data:image/*;base64,' + productWithPics.productPics[2].productPic).show();
                }else {
                    $("#imagePreview1").attr('src', '/Jamigo/shop/shopping/assets/img/noPic/noPic.jpg' + productWithPics.productPics[0].productPic).show();
                }
            }
            if (productWithPics.productPics && productWithPics.productPics.length >= 4) {
                if(productWithPics.productPics[3].productPic != "") {
                    $("#imagePreview4").attr('src', 'data:image/*;base64,' + productWithPics.productPics[3].productPic).show();
                }else {
                    $("#imagePreview1").attr('src', '/Jamigo/shop/shopping/assets/img/noPic/noPic.jpg' + productWithPics.productPics[0].productPic).show();
                }
            }
        },
        error: function (xhr, textStatus, errorThrown) {
            console.error(xhr);
        }
    });
});