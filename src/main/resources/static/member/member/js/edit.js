(() => {
    const inputmemberNo = document.querySelector('#inputmemberNo');
    const inputmemberAccount = document.querySelector('#inputmemberAccount');
    const inputmemberName = document.querySelector('#inputmemberName');
    const inputmemberGender = document.querySelector('#inputmemberGender');
    const inputmemberPhone = document.querySelector('#inputmemberPhone');
    const inputmemberEmail = document.querySelector('#inputmemberEmail');
    const inputmemberAddress = document.querySelector('#inputmemberAddress');
    const inputmemberJoinTime = document.querySelector('#inputmemberJoinTime');
    const inputlevelNo = document.querySelector('#inputlevelNo');
    const inputmemberBirthday = document.querySelector('#inputmemberBirthday');
    const inputmemberNation = document.querySelector('#inputmemberNation');
    const inputmemberPic = document.querySelector('#inputmemberPic');
    const inputmemberCard = document.querySelector('#inputmemberCard');
    const inputmemberPoints = document.querySelector('#inputmemberPoints');
    const inputmemberStat = document.querySelector('#inputmemberStat');
    const inputs = document.querySelectorAll('input');
    const confirmbtn = document.querySelector('#confirmbtn');
    const avatarpreview = document.querySelector('#avatar-preview')
    const avatarPlaceholder = document.getElementById("avatar-placeholder");
    const avatarPreview = document.getElementById("avatar-preview");

    const canslebtn = document.querySelector('#canslebtn');
    const confirmbtn2 = document.querySelector('#confirmbtn2');
    const cancelbtn2 = document.querySelector('#cancelbtn2');
    const msg = document.querySelector('#msg');
    const memberId = localStorage.getItem('memberNo');

    const inputoldmemberPassword = document.querySelector('#oldmemberPassword')
    const inputnewmemberPassword = document.querySelector('#newmemberPassword')
    const inputnewmemberPasswordConfirm = document.querySelector('#newmemberPasswordConfirm')

    const msg2 = document.querySelector('#msg2');
    console.log("edit.js啟動");


    // ===============================VVV方法區VVV====================================

    // ============================1.查資料回來getmemberdata()========================
    let memberpassword4edit = '';

    function getmemberdata() {//取瀏覽器的memberid 去查出來
        console.log('進入getmemberdata')
        fetch("getmemberdata", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                memberNo: memberId,
            }),
        })
            .then((response) => response.json())
            .then(body => {
                // 在這裡處理回應的資料
                const {
                    memberNo,
                    memberAccount,
                    memberName,
                    memberGender,
                    memberPhone,
                    memberEmail,
                    memberAddress,
                    memberJoinTime,
                    levelNo,
                    memberBirthday,
                    memberNation,
                    memberPic4json,
                    memberCard,
                    memberPoints,
                    memberStat,
                    memberPassword
                } = body;
                console.log(body);
                inputmemberNo.value = memberNo;
                inputmemberAccount.value = memberAccount;
                inputmemberName.value = memberName;
                let memberGendera = '';
                if (memberGender === 2) {
                    memberGendera = '女生';
                } else if (memberGender === 1) {
                    memberGendera = '男生'
                }
                inputmemberGender.value = memberGendera;
                inputmemberPhone.value = memberPhone;
                inputmemberEmail.value = memberEmail;

                if (typeof memberAddress !== 'undefined') {
                    inputmemberAddress.value = memberAddress;
                }

                inputmemberJoinTime.value = memberJoinTime;
                inputlevelNo.value = levelNo;

                const originalDate = new Date(memberBirthday);
                const year = originalDate.getFullYear();
                const month = originalDate.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                const day = originalDate.getDate();
                const formattedDate = year + '-' + ('0' + month).slice(-2) + '-' + ('0' + day).slice(-2);
                inputmemberBirthday.value = formattedDate;

                inputmemberNation.value = memberNation;

                if (typeof memberCard !== 'undefined') {
                    inputmemberCard.value = memberCard;
                } else {
                    inputmemberCard.value = 0;
                }

                inputmemberPoints.value = memberPoints;
                inputmemberStat.value = memberStat;
                inputmemberAccount.value = memberAccount;

                if (memberPic4json ==="有圖") {
                    avatarpreview.src = `http://localhost:8080/Jamigo/member/member_data/${memberNo}`;
                    avatarPreview.style.display = "block";
                    avatarPlaceholder.style.display = "none";
                } else {
                    avatarPreview.style.display = "none";
                    avatarPlaceholder.style.display = "block";
                }
                memberpassword4edit = memberPassword;


            })
            .catch((error) => {
                console.error("Error:", error);
            });

    };

    // ============================2. 修改資料進去 editmemberdata()========================
    function editmemberdata() {

        // 前端確認資料填寫
        msg.textContent = ' ';
        console.log("確認修改按鈕啟動");
        const nicknameLength = inputmemberName.value.length;
        if (nicknameLength < 1 || nicknameLength > 20) {
            msg.textContent = '姓名長度須介於1~20字元';
            return;
        }
        const phoneValue = inputmemberPhone.value;
        const phonePattern = /^09\d{8}$/;
        if (!phonePattern.test(phoneValue)) {
            msg.textContent = '電話號碼須為09開頭，且為十碼'
            return;
        }
        const emailValue = inputmemberEmail.value;
        const emailPattern = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
        if (!emailPattern.test(emailValue)) {
            msg.textContent = '請輸入電子信箱格式'
            return;
        }

        // console.log(inputmemberAddress.value);
        // const AddressValue = inputmemberAddress.value;
        // const AddressPattern = /^[\u4E00-\u9FA5]{3,}(?:市|縣|區)[^\s]*[路街巷][\u4E00-\u9FA5]{2,}/;
        // if (!AddressPattern.test(AddressValue)) {
        //     msg.textContent = '請輸入地址，其中需包含:縣/市/區;路/街/巷'
        //     return;
        // }

        const memberCarda = inputmemberCard.value;
        const memberCardLength = inputmemberCard.value.length;
        if (memberCarda != 0) {
            if (memberCardLength < 15 || memberCardLength > 19) {
                msg.textContent = '信用卡長度須介於15~19碼';
                return;
            }
        }
        const Birthday = inputmemberBirthday.value;
        if (!Birthday) {
            msg.textContent = '生日為必填';
            return;
        }

        let membergender = 0;
        if (inputmemberGender.value) {
            if (inputmemberGender.value === "男生") {
                membergender = 1;
            } else if (inputmemberGender.value === "女生") {
                membergender = 2;
            }
        }
        // 檢查結束

        msg.textContent = '';
        fetch('edit', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                memberNo: inputmemberNo.value,
                memberAccount: inputmemberAccount.value,
                memberName: inputmemberName.value,
                memberPhone: inputmemberPhone.value,
                memberEmail: inputmemberEmail.value,
                memberName: inputmemberName.value,
                memberGender: membergender,
                memberPhone: inputmemberPhone.value,
                memberEmail: inputmemberEmail.value,
                memberAddress: inputmemberAddress.value,
                memberBirthday: inputmemberBirthday.value,
                memberNation: inputmemberNation.value,
                memberPic4json: base64Image,
                memberCard: inputmemberCard.value,
            }),
        })
            .then(resp => resp.json())// .then(function (resp) {resp.json();)})
            .then(body => {
                console.log(body);
                const {successful} = body;//const successful = body.successful;
                if (successful) {
                    msg.className = 'info';
                    msg.textContent = '修改成功';
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: '修改成功!',
                        showConfirmButton: false,
                        timer: 1500
                    }).then(()=>{location.reload()})
                } else {
                    msg.className = 'error';
                    msg.textContent = '修改失敗';
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: '修改失敗!',
                        footer: '<a href=""></a>'
                    })
                }
            });

    }

    // ============================3. 圖片讀取，轉型成base64 readPic()========================
    let base64Image = '';

    function readPic(event) {
        const file = event.target.files[0]; // 獲取選擇的檔案
        if (file) {
            const reader = new FileReader(); //讀取
            reader.onload = function (e) {
                const imageSrc = e.target.result; // 獲取數據
                base64Image = imageSrc.split(",")[1];// 轉成base64

            };
            reader.readAsDataURL(file); // 讀取成url
        }
    }

    // ============================4.清空表單 resetform()========================
    function resetform() {
        inputmemberNo.value = "";
        inputmemberAccount.value = "";
        inputmemberName.value = "";
        inputmemberGender.value = "";
        inputmemberPhone.value = "";
        inputmemberEmail.value = "";
        inputmemberJoinTime.value = "";
        inputlevelNo.value = "";
        inputmemberBirthday.value = "";
        inputmemberNation.value = "";
        inputmemberPoints.value = "";
        inputmemberStat.value = "";
        inputmemberAccount.value = "";
        inputmemberPic.type = "text";
        inputmemberPic.type = "file";//清空圖片
        inputmemberAddress.value = "";
        inputmemberCard.value = "";
        msg.textContent = '';
    }

    // ============================5. 修改密碼 editmemberpassword()========================

    function editmemberpassword() {
        console.log("修改密碼按鈕")
        msg2.textContent="";
        const pwdLength = inputnewmemberPassword.value.length;
        if (pwdLength < 6 || pwdLength > 12) {
            msg2.textContent = '密碼長度須介於6~12字元';
            return;
        }
        msg.textContent = '';
        let newpassword = null;
        if (inputnewmemberPassword.value == inputnewmemberPasswordConfirm.value) {
            if (inputoldmemberPassword.value == memberpassword4edit) {
                newpassword = inputnewmemberPassword.value;
            }
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: '密碼不正確!!',
                footer: '<a href=""></a>'
            })
        }
        if (newpassword !== null && newpassword !== "" ) {
            fetch('edit', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    memberNo: inputmemberNo.value,
                    memberPassword: newpassword
                })
            })
                .then(resp => resp.json())
                .then(body => {
                    console.log(body);
                    const {successful} = body;//const successful = body.successful;
                    if (successful) {
                        msg.className = 'info';
                        msg2.textContent = '修改成功';
                        Swal.fire({
                            position: 'center',
                            icon: 'success',
                            title: '密碼修改成功!',
                            showConfirmButton: false,
                            timer: 1500
                        })
                        setTimeout(function() {
                            console.log("等1.5秒");
                            location = '../member/edit.html';
                        }, 1600);
                    } else {
                        msg.className = 'error';
                        msg2.textContent = '修改失敗';
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: '密碼修改失敗!',
                            footer: '<a href=""></a>'
                        })
                    }
                });

        }

    }

    // ===============================^^^方法區^^^====================================

    // ===============================VVV使用方法區VVV================================

    //=================================1. 總之先查一次=================================
    getmemberdata();
    // ===============================2. 確認修改按鈕================================
    confirmbtn.addEventListener('click', () => {
        editmemberdata();
    });
    //=================================3. 圖片檔案上傳按鈕=============================
    inputmemberPic.addEventListener("change", function (event) {
        readPic(event);
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: '上傳成功!',
            showConfirmButton: false,
            timer: 1500
        })
    });
    //=================================3. 重製按鈕(再查一次)=============================
    canslebtn.addEventListener('click', () => {
        resetform();
        setTimeout(function () {
            console.log("等1秒");
            getmemberdata();
        }, 1000);
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: '已刷新!',
            showConfirmButton: false,
            timer: 1500
        })
    });
    // ================================4. 性別select 轉貼至input====================================
    const selectGender = document.querySelector('#selectGender');
    selectGender.addEventListener('change', (e) => {
        const selectedValue = selectGender.value;
        inputmemberGender.value = selectedValue;
    })

    // ================================5. 修改密碼的確認按鈕====================================
    confirmbtn2.addEventListener('click', () => {
        editmemberpassword();
    })
    // ================================5. 修改密碼的取消按鈕====================================
    cancelbtn2.addEventListener('click', () => {
        inputnewmemberPassword.value = "";
        inputnewmemberPasswordConfirm.value = "";
        inputoldmemberPassword.value = "";
    })
    // ===============================^^^使用方法區^^^==============================

})();