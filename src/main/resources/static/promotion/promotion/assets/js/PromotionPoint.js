(() => {
    const tbody = document.querySelector('#tbody');
    const msg=document.querySelector('#msg')


    // ===============================VVV方法區VVV====================================

    // ============================1.查資料回來getAllPromotion() 拿到字串和筆數========================
    let dataaccount = 0;
    let PromotionType = [];
    let promotionName4 = [];
    let promotionPointNo4=[];

    function getAllPromotionPoint() {
        console.log('進入getAllPromotionPoint()')
        fetch("getAllPromotionPoint")
            .then(function (response) {
                // 檢查 API 响應的狀態碼
                if (response.status !== 200) {
                    console.log('發生錯誤，狀態碼：' + response.status);
                    return;
                }

                // 解析 JSON 格式的數據
                response.json().then(function (data) {
                    // 在此處可以處理從 API 獲取的數據
                    PromotionPoint = data;
                    console.log('獲取的折價券活動：', PromotionPoint);


                    for (let i = 0; i < PromotionPoint.length; i++) {
                        dataaccount = i;
                        let row = PromotionPoint[i];
                        const promotionPointNo = row.promotionPointNo;
                        promotionPointNo4.push(promotionPointNo);
                        const promotionPointName = row.promotionPointName;
                        const promotionName = row.promotionName;
                        promotionName4.push(promotionName);
                        const getPointConditions = row.getPointConditions;
                        const getPointMag = row.getPointMag;
                        //     處理日期格式
                        const originalDate = new Date(row.promotionExpireDate);
                        const year = originalDate.getFullYear();
                        const month = originalDate.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                        const day = originalDate.getDate();
                        const formattedDate = year + '-' + ('0' + month).slice(-2) + '-' + ('0' + day).slice(-2);
                        //     處理日期格式
                        const promotionExpireDate = formattedDate;
                        //     處理日期格式
                        const originalDate2 = new Date(row.promotionEffectiveDate);
                        const year2 = originalDate2.getFullYear();
                        const month2 = originalDate2.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                        const day2 = originalDate2.getDate();
                        const formattedDate2 = year2 + '-' + ('0' + month2).slice(-2) + '-' + ('0' + day2).slice(-2);
                        //     處理日期格式
                        const promotionEffectiveDate = formattedDate2;
                        //     處理日期格式
                        const originalDate3 = new Date(row.promotionCreateDate);
                        const year3 = originalDate3.getFullYear();
                        const month3 = originalDate3.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                        const day3 = originalDate3.getDate();
                        const formattedDate3 = year3 + '-' + ('0' + month3).slice(-2) + '-' + ('0' + day3).slice(-2);
                        const promotionCreateDate = formattedDate3;
                        const promotionPic = row.promotionPic;

                        dataTable.row.add([
                            promotionPointName,
                            promotionName,
                            promotionEffectiveDate,
                            promotionExpireDate,
                            getPointMag,
                            getPointConditions,
                            `<a href="/Jamigo/promotion/promotion_list/promotionP_detail.html?promotionPointNo=${promotionPointNo}"><button type="button" class="btn btn-outline-primary">詳情</button></a>`,
                            `<button type="button" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#exampleModal${i}" data-bs-whatever="@mdo" id="editbutton${i}" ${promotionName !== null && promotionName.includes("櫃位") ? `style="display:none"` : ''}>修改
            </button>
            <div class="modal fade" id="exampleModal${i}" tabIndex="-1"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel${i}">點數活動修改
                            </h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">

                            <form>
                                <div class="mb-3">
                                    <label for="promotionPointNo${i}"
                                           class="col-form-label">點數活動編號:</label>
                                    <input type="text" class="form-control"
                                           id="promotionPointNo${i}" value="${promotionPointNo}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="promotionPointName${i}"
                                           class="col-form-label">點數活動名稱:</label>
                                    <input type="text" class="form-control"
                                           id="promotionPointName${i}" value="${promotionPointName}" >
                                </div>
                                
                                <div class="mb-3">
                                    <label for="promotionName${i}"
                                           class="col-form-label">活動類別:</label>
                                    <select name="" id="promotionName${i}" value="${promotionName}" class="form-control">
<!--                                    動態生成-->
                                    </select>
                                    <div id="dynamicSpanspromotionName${i}" style="display: flex;">
                                            </div>
                                </div>
                                <div class="mb-3">
                                    <label for="promotionEffectiveDate${i}"
                                           class="col-form-label">活動生效日期:</label>
                                    <input type="date" class="form-control"
                                           id="promotionEffectiveDate${i}" value="${promotionEffectiveDate}" >
                                </div>
                                
                                <div class="mb-3">
                                    <label for="promotionExpireDate${i}"
                                           class="col-form-label">活動結束日期:</label>
                                    <input type="date" class="form-control"
                                           id="promotionExpireDate${i}" value="${promotionExpireDate}" >
                                </div>
                               
                                
                                <div class="mb-3">
                                    <label for="getPointMag${i}"
                                           class="col-form-label">點數倍率:</label>
                                    <input type="text" class="form-control"
                                           id="getPointMag${i}" value="${getPointMag}" >
                                </div>
                                
                                <div class="mb-3">
                                    <label for="getPointConditions${i}"
                                           class="col-form-label">領取條件:</label>
                                    <input type="text" class="form-control"
                                           id="getPointConditions${i}" value="${getPointConditions}" >
                                </div>
                                
                                <div class="mb-3">
                                            <label for="promotionCreateDate${i}" class="col-form-label">建立時間:</label>
                                            <input type="text" class="form-control" id="promotionCreateDate${i}" value="${promotionCreateDate}" readonly>
                                        </div>
                                
                                <div class="mb-3">
                                <div class="img-div  ms-3">
                                                <img id="avatar-preview${i}" src="/Jamigo/member/member/image/gray.jpg" alt="Avatar Preview"
                                                     class="img-fluid border border-dark"/>
                                            </div>
                                    <label for="promotionPic${i}"
                                           class="col-form-label">活動圖片:</label>
                                    <input type="file" class="form-control"
                                           id="promotionPic${i}" value="${promotionPic}" accept="image/jpeg, image/png">
                                </div>
                                <span id="msg"> </span>
                            </form>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary editbutton"
                                    data-bs-dismiss="modal" id="cancle${i}">取消
                            </button>
                            <button type="button" class="btn btn-primary" id="confirm${i}">修改</button>
                        </div>
                    </div>
                </div>
            </div>`, `<button type="button" class="btn btn-primary" id="delete${i}" ${promotionName !== null && promotionName.includes("櫃位") ? `style="display:none"` : ''}>刪除</button>`

                        ]);
                    }
                    dataTable.draw();
                    setinputinbox();
                    seteditbutton();
                    addeventlistener4editbutton();
                    setdeletebutton();
                    addeventlistener4deletebutton();
                    getAllPromotion();
                    preview();
                    const msg = document.querySelector('#msg');
                });
            })
            .catch(function (err) {
                console.log('錯誤：', err);
            });
    }


    // ============================2.  初始化datatable函式========================

    let dataTable = $('#all').DataTable({
        scrollY: '600px',
        scrollCollapse: true,
        paging: false,
        pageLength: 10,
        info: false,
        destroy: true,
        columnDefs: [
            {
                target: 6,
                width: 5,
                className: "hiddenyes"
            }
        ]
    });

    // ============================３. 抓取所有修改的燈箱按鈕 ========================
    let editbuttons = [];

    function seteditbutton() {
        for (let i = 0; i <= dataaccount; i++) {
            const editbuttona = document.getElementById('confirm' + i);
            editbuttons.push(editbuttona);
        }
    }

    // ============================4. 抓取所有燈箱的input ========================
    let promotionPointNoinputs = [];
    let promotionPointNameinputs = [];
    let promotionNameinputs = [];
    let promotionEffectiveDateinputs = [];
    let promotionExpireDateinputs = [];
    let promotionCreateDateinputs = [];
    let getPointMaginputs = [];
    let getPointConditionsinputs = [];
    let dynamicSpanspromotionNameinputs = [];
    let promotionPicinputs = [];

    function setinputinbox() {
        for (let i = 0; i <= dataaccount; i++) {
            const promotionNameinput = document.querySelector('#promotionName' + i);
            const dynamicSpanspromotionNameinput = document.querySelector('#dynamicSpanspromotionName' + i);
            const promotionEffectiveDateinput = document.querySelector('#promotionEffectiveDate' + i);
            const promotionExpireDateinput = document.querySelector('#promotionExpireDate' + i);
            const promotionCreateDateinput = document.querySelector('#promotionCreateDate' + i);
            const promotionPicinput = document.querySelector('#promotionPic' + i);
            const promotionPointNoinput = document.querySelector('#promotionPointNo' + i);
            const promotionPointNameinput = document.querySelector('#promotionPointName' + i);
            const getPointMaginput = document.querySelector('#getPointMag' + i);
            const getPointConditionsinput = document.querySelector('#getPointConditions' + i);
            promotionNameinputs.push(promotionNameinput);
            dynamicSpanspromotionNameinputs.push(dynamicSpanspromotionNameinput);
            promotionEffectiveDateinputs.push(promotionEffectiveDateinput);
            promotionExpireDateinputs.push(promotionExpireDateinput);
            promotionCreateDateinputs.push(promotionCreateDateinput);
            promotionPointNoinputs.push(promotionPointNoinput);
            promotionPicinputs.push(promotionPicinput);
            promotionPointNameinputs.push(promotionPointNameinput);
            getPointMaginputs.push(getPointMaginput);
            getPointConditionsinputs.push(getPointConditionsinput);
        }
    }

    // promotionPointNo
    // promotionPointName
    // promotionName
    // promotionEffectiveDate
    // promotionExpireDate
    // promotionCreateDate
    // getPointMag
    // getPointConditions
    // dynamicSpanspromotionName
    // promotionPic

    // ============================4. 修改資料進去 editPromotion()========================

    function editPromotion(i) {
        const promotionPointNo4json = promotionPointNoinputs[i].value;
        const promotionPointName4json = promotionPointNameinputs[i].value;
        const promotionName4json = promotionNameinputs[i].value;
        const promotionEffectiveDate4json = promotionEffectiveDateinputs[i].value;
        const promotionExpireDate4json = promotionExpireDateinputs[i].value;
        const getPointMag4json = getPointMaginputs[i].value;
        const getPointConditions4json = getPointConditionsinputs[i].value;
        const promotionPic4json = promotionPicinputs[i];
        const msg = document.querySelector('#msg');

        promotionPic4json?.addEventListener("change", function (event) {
            readPic(event);
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: '上傳成功!',
                showConfirmButton: false,
                timer: 1500
            })
        });

        msg.textContent = '';
        const promotionNameLength = promotionPointName4json.length;
        if (promotionNameLength < 1 || promotionNameLength > 10) {
            msg.textContent = '名稱長度須介於1~10字元';
            return;
        }
        // 檢查結束

        fetch('editPromotionPoint', {
            method: 'POST', headers: {
                'Content-Type': 'application/json',
            }, body: JSON.stringify({
                promotionPointNo: promotionPointNo4json,
                promotionPointName: promotionPointName4json,
                getPointMag: getPointMag4json,
                getPointConditions: getPointConditions4json,
                promotionName: promotionName4json,
                promotionEffectiveDate: promotionEffectiveDate4json,
                promotionExpireDate: promotionExpireDate4json,
                promotionPic4json: base64Image
            }),
        })
            .then(resp => resp.json())
            .then(body => {
                console.log(body);
                const {successful, message} = body;
                if (successful) {
                    msg.className = 'info';
                    msg.textContent = '修改成功';
                    Swal.fire({
                        position: 'center', icon: 'success', title: '修改成功!', showConfirmButton: false, timer: 1500
                    }).then(() => {
                        location.reload()
                    })
                } else {
                    msg.className = 'error';
                    Swal.fire({
                        icon: 'error', title: 'Oops...', text: `${message}`, footer: '<a href=""></a>'
                    })
                }
            });

    }

    // ============================5. 綁定所有修改燈箱按鈕click / 圖片上傳change事件========================
    function addeventlistener4editbutton() {
        for (let i = 0; i <= dataaccount; i++) {
            editbuttons[i]?.addEventListener('click', () => {
                console.log('有綁到')
                editPromotion(i);
            })
            promotionPicinputs[i]?.addEventListener("change", function (event) {
                readPic(event);
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: '上傳成功!',
                    showConfirmButton: false,
                    timer: 1500
                })
            });
        }
    }

    // ============================6.   newAPromotion()新增promotion========================
    const msg2 = document.querySelector('#msg2');
    const promotionPic4new = document.querySelector('#promotionPica');
    promotionPic4new?.addEventListener("change", function (event) {
        readPic(event);
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: '上傳成功!',
            showConfirmButton: false,
            timer: 1500
        })
    });
    function newAPromotion() {
        const promotionPointName4new = document.querySelector('#promotionPointName').value;
        const getPointMag4new = document.querySelector('#getPointMag').value;
        const getPointConditions4new = document.querySelector('#getPointConditions').value;
        const promotionName4new = document.querySelector('#promotionName').value;
        const promotionEffectiveDate4new = document.querySelector('#promotionEffectiveDate').value;
        const promotionExpireDate4new = document.querySelector('#promotionExpireDate').value;

        const promotionNameLength = promotionPointName.length;
        if (promotionNameLength < 1 || promotionNameLength > 10) {
            msg2.textContent = '名稱長度須介於1~10字元';
            return;
        }
        fetch('newPromotionPoint', {
            method: 'POST', headers: {
                'Content-Type': 'application/json',
            }, body: JSON.stringify({
                promotionPointName: promotionPointName4new,
                getPointMag: getPointMag4new,
                getPointConditions: getPointConditions4new,
                promotionName: promotionName4new,
                promotionEffectiveDate: promotionEffectiveDate4new,
                promotionExpireDate: promotionExpireDate4new,
                promotionPic4json: base64Image
            }),
        })
            .then(resp => resp.json())
            .then(body => {
                console.log(body);
                const {successful, message} = body;
                if (successful) {

                    Swal.fire({
                        position: 'center', icon: 'success', title: '新增成功!', showConfirmButton: false, timer: 1500
                    }).then(() => {
                        location.reload()
                    })
                } else {
                    Swal.fire({
                        icon: 'error', title: 'Oops...', text: `${message}`, footer: '<a href=""></a>'
                    })
                }
                ;

            });

    };

    // ============================7. 找到所有刪除按鈕========================
    let deletebuttons = [];

    function setdeletebutton() {
        for (let i = 0; i <= dataaccount; i++) {
            const deletebutton = document.getElementById('delete' + i);
            deletebuttons.push(deletebutton);
        }
    }

    // ============================8. 綁定所有刪除按鈕========================
    function addeventlistener4deletebutton() {
        for (let i = 0; i <= dataaccount; i++) {
            deletebuttons[i]?.addEventListener('click', () => {
                const inputvalue = promotionPointNoinputs[i].value;
                deledtbyPK(inputvalue);
            })
        }
    }

    // ============================9. 刪除方法========================
    function deledtbyPK(promotionPointNo) {
        const confirmed = confirm("確定要刪除嗎？");
        if (confirmed) {
            fetch('deletePromotionPoint', {
                method: 'POST', headers: {
                    'Content-Type': 'application/json',
                }, body: JSON.stringify({
                    promotionPointNo: promotionPointNo
                }),
            })
                .then((resp) => {
                    if (resp) {
                        Swal.fire({
                            position: 'center',
                            icon: 'success',
                            title: '刪除成功!',
                            showConfirmButton: false,
                            timer: 1500
                        }).then(() => {
                            location.reload()
                        })
                    } else {
                        Swal.fire({
                            icon: 'error', title: 'Oops...', text: '刪除失敗!', footer: '<a href=""></a>'
                        })
                    }
                })
        }
    };


    // ============================12.查詢活動種類========================
    const select4promotionName = document.querySelector('#promotionName');
    const dynamicSpanPromotionName = document.querySelector('#dynamicSpanPromotionName');
    let promotionNamelength = 0;
    let promotionname = [];

    function getAllPromotion() {
        console.log('進入getAllPromotion()')
        fetch("getAllPromotion2")
            .then(function (response) {
                // 檢查 API 响應的狀態碼
                if (response.status !== 200) {
                    console.log('發生錯誤，狀態碼：' + response.status);
                    return;
                }

                // 解析 JSON 格式的數據
                response.json().then(function (data) {
                    // 在此處可以處理從 API 獲取的數據
                    PromotionType = data;
                    console.log('獲取的促銷活動數據：', PromotionType);
                    promotionNamelength = PromotionType.length;
                    let str = ' <option selected disabled>請選擇活動種類</option>';
                    let str2 = '';
                    let str3_0 = '';
                    let str4_0 = '';
                    let str3 = [];
                    let str4 = [];


                    for (let h = 0; h <= dataaccount; h++) {
                        for (let u = 0; u < promotionNamelength; u++) {
                            let row = PromotionType[u];
                            const promotionName2 = row.promotionName;
                            promotionname.push(promotionName2);
                            const promotionType = row.promotionType;
                            const promotionMethod = row.promotionMethod;
                            let adminNo = '';
                            if (row.adminNo !== null && row.adminNo !== "") {
                                adminNo = row.adminNo;
                            }
                            let counterNo = '';
                            if (row.counterNo != null) {
                                counterNo = row.counterNo
                            }
                            if (adminNo !== "") {
                                str4_0 += `<option value="${promotionName2}" ${promotionName2 === promotionName4[h] ? 'selected' : ''}>${promotionName2}</option>`
                                str3_0 += `<div id="span4Promotion${promotionName2}${h}" class="hiddenyee">
                                <span>管理員編號： ${adminNo}</span>
                                <span>欄位編號: ${counterNo}</span>
                                <br>
                                <span>發放種類: ${promotionType}</span>
                                <span>發放方式: ${promotionMethod}</span>
                                </div>`
                            }
                        }
                        str3.push(str3_0);
                        str3_0 = '';
                        str4.push(str4_0);
                        str4_0 = '';
                    }
                    for (let i = 0; i < promotionNamelength; i++) {
                        let row = PromotionType[i];
                        const promotionName2 = row.promotionName;
                        promotionname.push(promotionName);
                        const promotionType = row.promotionType;
                        const promotionMethod = row.promotionMethod;

                        let adminNo = '';
                        if (row.adminNo !== null && row.adminNo !== "") {
                            adminNo = row.adminNo;
                        }
                        let counterNo = '';
                        if (row.counterNo !== null) {
                            counterNo = row.counterNo
                        }
                        if (adminNo !== "") {
                            str += `<option value="${promotionName2}" >${promotionName2}</option>`
                            str2 += `<div id="span4Promotion${promotionName2}" class="hiddenyee">
                                <span>管理員編號： ${adminNo}</span>
                                <span>欄位編號: ${counterNo}</span>
                                <br>
                                <span>發放種類: ${promotionType}</span>
                                <span>發放方式: ${promotionMethod}</span>
                                </div>`
                        }

                    }
                    dynamicSpanPromotionName.innerHTML = str2;
                    select4promotionName.innerHTML = str;
                    for (let i = 0; i < promotionNamelength; i++) {
                        if (typeof dynamicSpanspromotionNameinputs[i] !== 'undefined') {
                            dynamicSpanspromotionNameinputs[i].innerHTML = str3[i];
                        }
                        if (typeof promotionNameinputs[i] !== 'undefined') {
                            promotionNameinputs[i].innerHTML = str4[i];
                        }
                    }
                    setdiv4Promotion();
                    setdiv4Promotion2()
                    Listener4SelectPromotion();

                });
            })
            .catch(function (err) {
                console.log('錯誤：', err);
            });
    }

    // ============================11.抓到選項下div並綁定select========================
    let div4Promotion = [];

    function setdiv4Promotion() {
        for (let i = 0; i < promotionNamelength; i++) {
            const div4Promotiona = document.getElementById('span4Promotion' + promotionname[i]);
            if (div4Promotiona) {
                div4Promotion.push(div4Promotiona);
            }
        }
    }

    let div4Promotion2 = [];
    let div4promotion3 = []

    function setdiv4Promotion2() {
        for (let p = 0; p <= dataaccount; p++) {
            for (let i = 0; i < promotionNamelength; i++) {
                const div4Promotiona = document.getElementById('span4Promotion' + promotionname[i] + p);
                if (div4Promotiona) {
                    div4Promotion2.push(div4Promotiona);
                }
            }
            div4promotion3.push(div4Promotion2);
            div4Promotion2 = [];
        }
    }


    function Listener4SelectPromotion() {
        for (let i = 0; i <= dataaccount; i++) {
            promotionNameinputs[i]?.addEventListener('change', () => {
                const selectedValue = promotionNameinputs[i].value;
                div4promotion3[i].forEach(function (div) {

                    if (div.id === "span4Promotion" + selectedValue + i) {
                        div.classList.remove("hiddenyee");
                    } else {
                        div.classList.add("hiddenyee");
                    }
                });
            })
        }
        select4promotionName?.addEventListener('change', () => {
            const selectedValue = select4promotionName.value;
            div4Promotion.forEach(function (div) {
                if (div.id === "span4Promotion" + selectedValue) {
                    div.classList.remove("hiddenyee");
                } else {
                    div.classList.add("hiddenyee");
                }
            });
        })
    };

// ============================12. 圖片讀取，轉型成base64 readPic()========================
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


    // ===============================預覽圖====================================
    const avatarUploads = [];
    const avatarPreviews = [];
    function preview() {


        for (let i = 0; i <= dataaccount; i++) {
            const avatarUploada = document.getElementById("promotionPic" + i);
            const avatarPreviewa = document.getElementById("avatar-preview" + i);
            avatarUploads.push(avatarUploada);
            avatarPreviews.push(avatarPreviewa);

        }

        for (let i = 0; i <= dataaccount; i++) {
            avatarPreviews[i].src=`/Jamigo/promotion/promotionPoint4pic/${promotionPointNo4[i]}`
            avatarUploads[i].addEventListener("change", function () {
                const file = avatarUploads[i].files[0];
                const reader = new FileReader();

                reader.onload = function (e) {
                    avatarPreviews[i].src = e.target.result;
                };

                if (file) {
                    reader.readAsDataURL(file);
                } else {
                    avatarPreviews[i].src = "#";
                }
            });
        }
    }
    // ===============================^^^方法區^^^====================================

    // ===============================VVV使用方法區VVV================================

    //=================================1. 總之先查一次=================================
    getAllPromotionPoint();


    // ===============================2. 確認新增按鈕================================

    const button4new = document.querySelector('#newbutton');
    button4new?.addEventListener('click', () => {
        newAPromotion();
    })
//=================================3. 圖片檔案上傳按鈕=============================


    // ===============================^^^使用方法區^^^==============================

})();