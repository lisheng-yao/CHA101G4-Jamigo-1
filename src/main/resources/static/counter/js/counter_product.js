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
<!--                        <td><img src="/Jamigo/shop/product_picture/product/${resp[e].productNo}" alt="" style="height: 50px; width: 50px;"></td>-->
                        <td>${resp[e].productName}</td>
                        <td>${resp[e].productPrice}</td>
                        <td class="prod-description">${resp[e].productInfo}</td>
                        <td>
                            <span class="${productStatusColor}">${productStatusString}</span>
                        </td>
                        <td>
                            <button type="button" class="btn border border-0 btn-primary edit_btn" id="${resp[e].productNo}" onclick="gotoEditPage(this.id)"><i class="fa-solid fa-pen-to-square"></i></button>
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
    //檢查新增商品的格式
    validInputContent();
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
        if(productCatNo == "0" || productName.trim() == "" || productPrice.trim() == "" || productInfo.trim() == ""){
            Swal.fire({
                icon: 'error',
                title: '商品資料未填寫完整',
                text: '請完成所有商品資料欄位填寫再新增',
            });
            return;
        }
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

function validInputContent(){
    // let hasShownAlert = false; // 跟踪警告是否已经触发过
    // $('#productName').on('blur', function() {
    //     const value = $(this).val().trim();
    //     if (value === '') {
    //         if (!hasShownAlert) { // 如果警告未触发过
    //             Swal.fire({
    //                 icon: 'error',
    //                 title: '商品名稱未輸入',
    //                 text: '商品名稱為必填項目'
    //             });
    //             hasShownAlert = true; // 将变量设为 true，表示已经触发过警告
    //         }
    //         $(this).focus(); // 将焦点保持在输入框内
    //     } else {
    //         hasShownAlert = false; // 当重新输入后，将变量设为 false，以便下次触发警告
    //     }
    // });

    $('#productPrice').on('keypress', function(event) {
        let inputValue = event.which;
        // 限制只能輸入整數
        if (!(inputValue >= 48 && inputValue <= 57)) {
            event.preventDefault(); //阻止輸入
        }
    });
    $('#productPrice').on('input', function() {
        let inputValue = $(this).val().trim();
        // 移除前导的0
        $(this).val(inputValue.replace(/^0+/, ''));
    });
}