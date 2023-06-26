// 點擊觸發goback返回上頁
function goBack() {
    window.history.go(-1);
}

//判斷當前頁面，導覽列按鈕停留
const currentPage = window.location.href;
const links = document.querySelectorAll('.button-01');

links.forEach(link => {
    if (link.href === currentPage) {
        link.classList.add('active');
    }
});