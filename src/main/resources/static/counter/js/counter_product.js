$(function(){
    // DOM 載入完成之後執行

    // let urlParams = new URLSearchParams(window.location.search);
    // let counterNo = urlParams.get(`counterNo`);

    //進入頁面需要列出商品清單
    // let counterNo = 1;
    $.ajax({
        url: `/Jamigo/products/listAllCounterProducts`,
        method: "GET",
        success: function (resp){
            //測試GET資料
            console.log(resp);
            let list_html = "";
            for (let e in resp){
                let productStatusString = `${resp[e].productStat === true ? "上架" : "下架"}`;
                let productStatusColor = `${resp[e].productStat === true ? "badge rounded-pill text-bg-success" : "badge rounded-pill text-bg-danger"}`;
                list_html += `
                    <tr>
                        <td>${resp[e].productCategory.productCatName}</td>
                        <td>${resp[e].productName}</td>
                        <td>${resp[e].productPrice}</td>
                        <td class="prod-description">${resp[e].productInfo}</td>
                        <td>
                            <span class="${productStatusColor}">${productStatusString}</span>
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary" id="${resp[e].productNo}"  onclick="gotoEditPage(this.id)">查看/修改</button>
                        </td>
                    </tr>
                `;
            }
            $(".product-list").html(list_html);
        },
        error: function (xhr, errorThrown){
            console.log(xhr, errorThrown);
        }
    });

    //商品類別選單
    getProductCatOptions();
    //新增商品品項
    addProduct();

});

// function getCounterNo() {
//     // return localStorage.getItem("counterNo");
//     return 1;
// }

function getProductCatOptions(){
    $("#addProduct-btn").on("click", function (){
        $.ajax({
            url: `/Jamigo/products/getAllCategories`,
            method: "GET",
            success: function (resp){
                console.log(resp);
                let options_html = `<option value="0">選擇商品類別</option>`;
                for (let e in resp){
                    options_html += `
                        <option value="${resp[e].productCatNo}">${resp[e].productCatName}</option>
                    `;
                }
                $("#categorySelect").html(options_html);
            },
            error: function (xhr, errorThrown){
                console.log(xhr, errorThrown);
            }
        });
    });
}

function addProduct(){
    $("#addProduct-confirm").on("click", function (){
        let productCatNo = $("#categorySelect").val();
        let productName = $("#productName").val();
        let productPrice = $("#productPrice").val();
        let productInfo = $("#productDescription").val();
        let productStat = $("input[name='productStatus']:checked").val() === "1" ? "true" : "false";
    // === "1" ? "true" : "false"
        $.ajax({
            url: `/Jamigo/products/addProduct`,
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                productCategory: productCatNo,
                productName: productName,
                productPrice: productPrice,
                productInfo: productInfo,
                productStat: productStat
            }),
            success: function (){
                console.log("成功!");
                var addProductModal = document.getElementById("staticBackdrop");
                console.log(addProductModal);
                $(addProductModal).modal("hide");
                location.reload();
            },
            error: function(xhr, textStatus, errorThrown) {
                console.log(xhr, textStatus, errorThrown);
            }
        });
    });
}

function gotoEditPage(id){
    window.location=`/Jamigo/counter/counter_product_edit.html?productNo=${id}`;
}

// document.getElementById('imageUpload').addEventListener('change', function (event) {
//     var previewContainer = document.getElementById('imagePreview');
//     var files = event.target.files;
//
//     for (var i = 0; i < files.length; i++) {
//         var reader = new FileReader();
//
//         reader.onload = (function (file) {
//             return function (event) {
//                 var imgElement = document.createElement('img');
//                 imgElement.src = event.target.result;
//                 imgElement.classList.add('preview-image');
//
//                 previewContainer.appendChild(imgElement);
//             };
//         })(files[i]);
//
//         reader.readAsDataURL(files[i]);
//     }
// });