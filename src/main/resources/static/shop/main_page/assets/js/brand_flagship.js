$(document).ready(function () {

    render();
});

function render() {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Jamigo/counterCtrl/getAll',
        success: function (response) {

            let html_str = "";

            for (let item of response) {
                html_str += `
                    <div class="col-lg-4">
                        <div class="single-testimonial">
                            <div class="testimonial_thumb">
                                <a href="/Jamigo/shop/counter/counter_mainPage.html?counterNo=${item['counterNo']}"><img src="/Jamigo/counterPic/${item['counterNo']}" alt=""></a>
                            </div>
                            <div class="testimonial_content">
                                <div class="testimonial_author">
                                    <a href="/Jamigo/shop/counter/counter_mainPage.html?counterNo=${item['counterNo']}">${item['counterName']}</a>
                                </div>
    
                                <p>${item['counterAbout']}</p>
    
                            </div>
                        </div>
                    </div>
                `;
            }

            $("div.testimonial_container2 div.row").html(html_str);
        }
    })
}