
//判斷當前頁面，導覽列按鈕停留
const currentPage = window.location.href;
const links = document.querySelectorAll('.button-01');

links.forEach(link => {
    if (link.href === currentPage) {
        link.classList.add('active');
    }
});


//登入登出
function openmemberinfo(){
document.getElementById('signin').style.display = 'none';
document.getElementById('memberinfo').style.display = 'flex';
}
function opensignin(){
document.getElementById('signin').style.display = 'flex';
document.getElementById('memberinfo').style.display = 'none';
}
