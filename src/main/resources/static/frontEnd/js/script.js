
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
( ()=> {
    const memberimg=document.querySelector('#memberimg');
    const membername=document.querySelector('#membername');
    const memberid = localStorage.getItem('memberNo');
    const memberaccount = localStorage.getItem('memberAccount');
    checklogin();//確認登入方法

    function checklogin(){
        if(memberid){
        openmemberinfo();
        memberimg.src= `/Jamigo/member/member_data/${memberid}`;
        membername.innerText='HI!  ' +  memberaccount;
        }
    }

    const logout=document.querySelector('#logoutbutton');
    logout.addEventListener('click',()=>{
        opensignin();
        localStorage.setItem('memberNo','');
        localStorage.setItem('memberAccount','');
    })

})();