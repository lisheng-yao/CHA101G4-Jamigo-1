(() => {
    const tbody = document.querySelector('#tbody');


    // ===============================VVV方法區VVV====================================

    // ============================1.查資料回來getAllPromotion()========================

    function getAllPromotion() {
        console.log('進入getAllPromotion()')
        fetch("http://localhost:8080/Jamigo/Promotion/GetAllPromotionType")
            .then(function (response) {
                // 檢查 API 响應的狀態碼
                if (response.status !== 200) {
                    console.log('發生錯誤，狀態碼：' + response.status);
                    return;
                }

                // 解析 JSON 格式的數據
                response.json().then(function (PromotiomType) {
                    // 在此處可以處理從 API 獲取的數據
                    console.log('獲取的促銷活動數據：', PromotiomType);
                    let str = '';
                    // 遍歷數據並進行處理
                    for (let i = 0; i < PromotiomType.length; i++) {
                        const promotion = PromotiomType[i];
                        const promotionName = promotion.promotionName;
                        const promotionType = promotion.promotionType;
                        const promotionMethod = promotion.promotionMethod;
                        const adminNo = promotion.adminNo;
                        const counterNo = promotion.counterNo;


                        str += `<tr>
    <td>${promotionName}</td>
    <td>${promotionType}</td>
    <td>${promotionMethod}</td>
    <td>${adminNo}</td>
    <td>${counterNo}</td>
    <td>
        <!-- 燈箱 -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                data-bs-target="#exampleModal${i}" data-bs-whatever="@mdo" id="editbutton${i}">修改
        </button>
        <div class="modal fade" id="exampleModal${i}" tabIndex="-1"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel${i}">優惠活動種類修改
                        </h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <form>
                            <div class="mb-3">
                                <label htmlFor="recipient-name"
                                       class="col-form-label">活動種類名稱:</label>
                                <input type="text" class="form-control"
                                       id="recipient-name${i}" value="${promotionName}">
                            </div>
                            <div class="mb-3">
                                <label htmlFor="recipient-name"
                                       class="col-form-label">發放種類:</label>
                                <select name="" id="recipient-type${i}" >
                                    <option value="折價券" ${promotionType === '折價券' ? 'selected' : ''}>折價券</option>
                                    <option value="點數" ${promotionType === '點數' ? 'selected' : ''}>點數</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label htmlFor="recipient-name"
                                       class="col-form-label">發放方式:</label>
                                <select name="" id="recipient-methed${i}" >
                                    <option value="結帳後取得" ${promotionMethod === '結帳後取得' ? 'selected' : ''}>結帳後取得</option>
                                    <option value="自由兌換" ${promotionMethod === '自由兌換' ? 'selected' : ''}>自由兌換</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label htmlFor="recipient-name"
                                       class="col-form-label">管理員編號:</label>
                                <input type="text" class="form-control"
                                       id="recipient-cNo${i}" value="">
                            </div>
                            <div class="mb-3">
                                <label htmlFor="recipient-name"
                                       class="col-form-label">欄位編號:</label>
                                <input type="text" class="form-control"
                                       id="recipient-aNo${i}" value="">
                            </div>
                        </form>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-bs-dismiss="modal">取消
                        </button>
                        <button type="button" class="btn btn-primary">修改</button>
                    </div>
                </div>
            </div>
        </div>
    </td>
    <td>
        <button type="button" class="btn btn-primary">刪除</button>
    </td>
</tr>`;

                        // 在此處可以使用獲得的數據進行相應的操作，例如添加到網頁元素中或進行其他處理
                        console.log("Promotion Name: " + promotionName);
                        console.log("Promotion Type: " + promotionType);
                        console.log("Promotion Method: " + promotionMethod);
                        console.log("Admin No: " + adminNo);
                        console.log("Counter No: " + counterNo);
                    }
                    tbody.innerHTML = str;
                });
            })
            .catch(function (err) {
                console.log('錯誤：', err);
            });
    }


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
                    })
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
        msg2.textContent = "";
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
        if (newpassword !== null && newpassword !== "") {
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
                        setTimeout(function () {
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
    getAllPromotion();
    // ===============================2. 確認修改按鈕================================
    // confirmbtn.addEventListener('click', () => {
    //     editmemberdata();
    // });
    //=================================3. 圖片檔案上傳按鈕=============================
//     inputmemberPic.addEventListener("change", function (event) {
//         readPic(event);
//         Swal.fire({
//             position: 'center',
//             icon: 'success',
//             title: '上傳成功!',
//             showConfirmButton: false,
//             timer: 1500
//         })
//     });
//     //=================================3. 重製按鈕(再查一次)=============================
//     canslebtn.addEventListener('click', () => {
//         resetform();
//         setTimeout(function () {
//             console.log("等1秒");
//             getmemberdata();
//         }, 1000);
//         Swal.fire({
//             position: 'center',
//             icon: 'success',
//             title: '已刷新!',
//             showConfirmButton: false,
//             timer: 1500
//         })
//     });
//     // ================================4. 性別select 轉貼至input====================================
//     const selectGender = document.querySelector('#selectGender');
//     selectGender.addEventListener('change', (e) => {
//         const selectedValue = selectGender.value;
//         inputmemberGender.value = selectedValue;
//     })
//
//     // ================================5. 修改密碼的確認按鈕====================================
//     confirmbtn2.addEventListener('click', () => {
//         editmemberpassword();
//     })
//     // ================================5. 修改密碼的取消按鈕====================================
//     cancelbtn2.addEventListener('click', () => {
//         inputnewmemberPassword.value = "";
//         inputnewmemberPasswordConfirm.value = "";
//         inputoldmemberPassword.value = "";
//     })
//     // ===============================^^^使用方法區^^^==============================
//
})();