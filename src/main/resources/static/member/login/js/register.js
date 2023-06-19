(() => {
    const btn2 = document.querySelector('#btn2');
    const msg = document.querySelector('#msg');
    const memberAccount2 = document.querySelector('#memberAccount2');
    const memberPassword = document.querySelector('#memberPassword');
    const confirmPassword = document.querySelector('#confirmPassword');
    const memberName = document.querySelector('#memberName');
    const memberPhone = document.querySelector('#memberPhone');
    const memberEmail = document.querySelector('#memberEmail');
    const email_verification = document.querySelector('#email_verification')
    const inputs = document.querySelectorAll('input');
    console.log("register.js啟動");

    btn2.addEventListener('click', () => {

        // 前端確認資料填寫
        msg.textContent = ' ';
        console.log("註冊按鈕啟動");
        const nicknameLength = memberName.value.length;
        if (nicknameLength < 1 || nicknameLength > 20) {
            msg.textContent = '姓名長度須介於1~20字元';
            return;
        }
        const phoneValue = memberPhone.value;
        const phonePattern = /^09\d{8}$/;
        if (!phonePattern.test(phoneValue)) {
            msg.textContent = '電話號碼須為09開頭，且為十碼'
            return;
        }
        const emailValue = memberEmail.value;
        const emailPattern = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
        if (!emailPattern.test(emailValue)) {
            msg.textContent = '請輸入電子信箱格式'
            return;
        }
        const accLength = memberAccount2.value.length;
        if (accLength < 5 || accLength > 50) {
            msg.textContent = '帳號長度須介於5~50字元';
            return;

        }
        const pwdLength = memberPassword.value.length;
        if (pwdLength < 6 || pwdLength > 12) {
            msg.textContent = '密碼長度須介於6~12字元';
            return;

        }
        if (confirmPassword.value !== memberPassword.value) {
            msg.textContent = '密碼與確認密碼不相符';
            return;

        }

        msg.textContent = '';
        fetch('register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                memberAccount: memberAccount2.value,
                memberPassword: memberPassword.value,
                memberName: memberName.value,
                memberPhone: memberPhone.value,
                memberEmail: memberEmail.value,
            }),
        })
            .then(resp => resp.json())// .then(function (resp) {resp.json();)})
            .then(body => {
                console.log(body);
                const {successful} = body;//const successful = body.successful;
                const {memberAccount} =body;
                if (successful) {
                    // for (let input of inputs) {
                    //     input.disabled = true;
                    // }

                    btn2.disabled = true;
                    msg.className = 'info';
                    msg.textContent = '註冊成功';
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: '註冊成功!',
                        showConfirmButton: false,
                        timer: 1500
                    })
                } else {
                    msg.className = 'error';
                    msg.textContent = '註冊失敗';
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: '註冊失敗!',
                        showConfirmButton: false,
                        timer: 1500
                    })
                };
                setTimeout(function() {
                    console.log("等1.5秒");
                    location = '../login/login.html';
                }, 1600);
            });

    });



})();