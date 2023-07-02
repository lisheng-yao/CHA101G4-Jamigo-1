

function openNewWindow(e) {

    let memberNo = localStorage.getItem('memberNo');
    
    let urlParams = new URLSearchParams(window.location.search);
    let productNo = urlParams.get('productNo');

    if (!memberNo) {
        localStorage.setItem('currentPageUrl', window.location.href);
        window.location = '/Jamigo/member/login/login.html';
        return;
    }
    
    $.ajax({
        url: "/Jamigo/report/" + memberNo + "/" + productNo,
        type: "GET",
        success: function (response) {
            console.log("回來")
                Swal.fire({
                    icon: 'warning',
                    text: '您已對該商品提出檢舉，請至會員專區查看',
                    title: '重複檢舉',
                }).then(() => {
                    window.location.reload();
                    return;
                })
        },
        error: function(error){
            // 確認該商品的該會員檢舉尚未建立
            window.location = "/Jamigo/shop/report/report.html?memberNo=" + memberNo + '&productNo=' + productNo;
            
        }
    })
}