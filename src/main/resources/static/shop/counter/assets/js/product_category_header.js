$(document).ready(function() {

    product_category_header_render()
});

function product_category_header_render() {

    $.ajax({
        type: 'GET',
        url: '/Jamigo/shop/main_page/product_category',
        success: function (response) {

            let filteredData = response.filter(item => item.productCatNo !== 0);

            let sortedData = filteredData.sort((a, b) => a.productCatNo - b.productCatNo);

            let html_str = "";

            for (let item of sortedData) {

                html_str += `
                    <li>
                        <a href="/Jamigo/shop/main_page/product_category_page.html?productCatNo=${item['productCatNo']}">${item['productCatName']}</a>
                    </li>
                `;
            }

            $("div.main_menu ul.mega_menu_inner").html(html_str);
        }
    })
}