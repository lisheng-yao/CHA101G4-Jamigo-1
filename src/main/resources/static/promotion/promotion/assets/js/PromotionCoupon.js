(() => {
    const tbody = document.querySelector('#tbody');


    // ===============================VVV方法區VVV====================================

    // ============================1.查資料回來getAllPromotion() 拿到字串和筆數========================
    let dataaccount = 0;
    console.log(dataaccount)
    let PromotionType = [];
    let promotionName4 = [];
    let couponTypeNo4 = [];

    function getAllPromotionCoupon() {
        console.log('進入getAllPromotionCoupon()')
        fetch("getAllPromotionCoupon")
            .then(function (response) {
                // 檢查 API 响應的狀態碼
                if (response.status !== 200) {
                    console.log('發生錯誤，狀態碼：' + response.status);
                    return;
                }

                // 解析 JSON 格式的數據
                response.json().then(function (data) {
                    // 在此處可以處理從 API 獲取的數據
                    PromotionCoupon = data;
                    console.log('獲取的折價券活動：', PromotionCoupon);


                    for (let i = 0; i < PromotionCoupon.length; i++) {
                        dataaccount = i;
                        let row = PromotionCoupon[i];
                        const promotionCouponNo = row.promotionCouponNo;
                        const promotionCouponName = row.promotionCouponName;
                        const promotionName = row.promotionName;
                        promotionName4.push(promotionName);
                        const couponTypeNo = row.couponTypeNo;
                        couponTypeNo4.push(couponTypeNo);
                        //     處理日期格式
                        const originalDate = new Date(row.promotionEffectiveDate);
                        const year = originalDate.getFullYear();
                        const month = originalDate.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                        const day = originalDate.getDate();
                        const formattedDate = year + '-' + ('0' + month).slice(-2) + '-' + ('0' + day).slice(-2);
                        //     處理日期格式
                        const promotionEffectiveDate = formattedDate;
                        //     處理日期格式
                        const originalDate2 = new Date(row.promotionExpireDate);
                        const year2 = originalDate2.getFullYear();
                        const month2 = originalDate2.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                        const day2 = originalDate2.getDate();
                        const formattedDate2 = year2 + '-' + ('0' + month2).slice(-2) + '-' + ('0' + day2).slice(-2);
                        //     處理日期格式
                        const promotionExpireDate = formattedDate2;
                        const amountOfCoupon = row.amountOfCoupon;
                        const getCouponLimitLevel = row.getCouponLimitLevel;
                        const getCouponLimitAmount = row.getCouponLimitAmount;

                        const promotionPic = row.promotionPic;
                        const getAmount = row.getAmount;

                        //     處理日期格式
                        const originalDate3 = new Date(row.promotionCreateDate);
                        const year3 = originalDate3.getFullYear();
                        const month3 = originalDate3.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                        const day3 = originalDate3.getDate();
                        const formattedDate3 = year3 + '-' + ('0' + month3).slice(-2) + '-' + ('0' + day3).slice(-2);
                        const promotionCreateDate = formattedDate3;

                        dataTable.row.add([promotionCouponNo,
                            promotionCouponName,
                            promotionName,
                            couponTypeNo,
                            promotionEffectiveDate,
                            promotionExpireDate,
                            `<a href="/Jamigo/promotion/promotion_list/promotionC_detail.html?promotionCouponNo=${promotionCouponNo}"><button type="button" class="btn btn-outline-primary">詳情</button></a>`,
                            `<button type="button" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#exampleModal${i}" data-bs-whatever="@mdo" id="editbutton${i}">修改
            </button>
            <div class="modal fade" id="exampleModal${i}" tabIndex="-1"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel${i}">折價券活動修改
                            </h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">

                            <form>
                                <div class="mb-3">
                                    <label for="promotionCouponNo${i}"
                                           class="col-form-label">折價券活動編號:</label>
                                    <input type="text" class="form-control"
                                           id="promotionCouponNo${i}" value="${promotionCouponNo}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="promotionCouponName${i}"
                                           class="col-form-label">折價券活動名稱:</label>
                                    <input type="text" class="form-control"
                                           id="promotionCouponName${i}" value="${promotionCouponName}" >
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
                                    <label for="couponTypeNo${i}"
                                           class="col-form-label">折價券種類:</label>
                                    <select name="" id="couponTypeNo${i}" value="${couponTypeNo}" class="form-control">
                                    </select>
                                    <div id="dynamicSpanscouponTypeNo${i}" style="display: flex;">
                                            </div>
                                </div>
                                
                                <div class="mb-3">
                                    <label for="amountOfCoupon${i}"
                                           class="col-form-label">發放數量:</label>
                                    <input type="text" class="form-control"
                                           id="amountOfCoupon${i}" value="${amountOfCoupon}" >
                                </div>
                                
                                <div class="mb-3">
                                    <label for="getCouponLimitLevel${i}"
                                           class="col-form-label">會員等級限制:</label>
                                    <input type="text" class="form-control"
                                           id="getCouponLimitLevel${i}" value="${getCouponLimitLevel}" >
                                </div>
                                
                                <div class="mb-3">
                                    <label for="getCouponLimitAmount${i}"
                                           class="col-form-label">領取量限制:</label>
                                    <input type="text" class="form-control"
                                           id="getCouponLimitAmount${i}" value="${getCouponLimitAmount}" >
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
                                            <label for="promotionCreateDate${i}" class="col-form-label">建立時間:</label>
                                            <input type="text" class="form-control" id="promotionCreateDate${i}" value="${promotionCreateDate}" readonly>
                                        </div>
                                <div class="mb-3">
                                            <label for="getAmount${i}" class="col-form-label">已領取數量:</label>
                                            <input type="text" class="form-control" id="getAmount${i}" value="${getAmount}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="promotionPic${i}"
                                           class="col-form-label">活動圖片:</label>
                                    <input type="file" class="form-control"
                                           id="promotionPic${i}" value="${promotionPic}" accept="image/jpeg, image/png">
                                </div>
                                
                            </form>

                        </div>
                        <div class="modal-footer">
                        <span id="msg${i}"> </span>
                            <button type="button" class="btn btn-secondary editbutton"
                                    data-bs-dismiss="modal" id="cancle${i}">取消
                            </button>
                            <button type="button" class="btn btn-primary" id="confirm${i}">修改</button>
                        </div>
                    </div>
                </div>
            </div>`, `<button type="button" class="btn btn-primary" id="delete${i}">刪除</button>`

                        ]);
                    }
                    dataTable.draw();
                    setinputinbox();
                    seteditbutton();
                    addeventlistener4editbutton();
                    setdeletebutton();
                    addeventlistener4deletebutton();
                    getAllCouponType();
                    getAllPromotion();
                    const msg = document.querySelector('#msg');
                });
            })
            .catch(function (err) {
                console.log('錯誤：', err);
            });
    }


    // ============================2.  初始化datatable函式========================

    let dataTable = $('#all').DataTable({
        scrollY: '600px', scrollCollapse: true, paging: false, pageLength: 10, info: false, destroy: true,
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

    let promotionCouponNoinputs = [];
    let promotionCouponNameinputs = [];
    let promotionNameinputs = [];
    let dynamicSpanspromotionNameinputs = [];
    let couponTypeNoinputs = [];
    let dynamicSpanscouponTypeNoinputs = [];
    let amountOfCouponinputs = [];
    let getCouponLimitLevelinputs = [];
    let getCouponLimitAmountinputs = [];
    let promotionEffectiveDateinputs = [];
    let promotionExpireDateinputs = [];
    let promotionCreateDateinputs = [];
    let getAmountinputs = [];
    let promotionPicinputs = [];
    let msgs = [];

    function setinputinbox() {
        for (let i = 0; i <= dataaccount; i++) {
            const promotionCouponNoinput = document.querySelector('#promotionCouponNo' + i);
            const promotionCouponNameinput = document.querySelector('#promotionCouponName' + i);
            const promotionNameinput = document.querySelector('#promotionName' + i);
            const dynamicSpanspromotionNameinput = document.querySelector('#dynamicSpanspromotionName' + i);
            const couponTypeNoinput = document.querySelector('#couponTypeNo' + i);
            const dynamicSpanscouponTypeNoinput = document.querySelector('#dynamicSpanscouponTypeNo' + i);
            const amountOfCouponinput = document.querySelector('#amountOfCoupon' + i);
            const getCouponLimitLevelinput = document.querySelector('#getCouponLimitLevel' + i);
            const getCouponLimitAmountinput = document.querySelector('#getCouponLimitAmount' + i);
            const promotionEffectiveDateinput = document.querySelector('#promotionEffectiveDate' + i);
            const promotionExpireDateinput = document.querySelector('#promotionExpireDate' + i);
            const promotionCreateDateinput = document.querySelector('#promotionCreateDate' + i);
            const getAmountinput = document.querySelector('#getAmount' + i);
            const promotionPicinput = document.querySelector('#promotionPic' + i);
            const msg = document.querySelector('#msg' + i);
            promotionCouponNoinputs.push(promotionCouponNoinput);
            promotionCouponNameinputs.push(promotionCouponNameinput);
            promotionNameinputs.push(promotionNameinput);
            dynamicSpanspromotionNameinputs.push(dynamicSpanspromotionNameinput);
            couponTypeNoinputs.push(couponTypeNoinput);
            dynamicSpanscouponTypeNoinputs.push(dynamicSpanscouponTypeNoinput);
            amountOfCouponinputs.push(amountOfCouponinput);
            getCouponLimitLevelinputs.push(getCouponLimitLevelinput);
            getCouponLimitAmountinputs.push(getCouponLimitAmountinput);
            promotionEffectiveDateinputs.push(promotionEffectiveDateinput);
            promotionExpireDateinputs.push(promotionExpireDateinput);
            promotionCreateDateinputs.push(promotionCreateDateinput);
            getAmountinputs.push(getAmountinput);
            promotionPicinputs.push(promotionPicinput);
            msgs.push(msg);
        }
    }


    // ============================4. 修改資料進去 editPromotion()========================
    function editPromotion(i) {
        const promotionCouponNo4json = promotionCouponNoinputs[i].value;
        const promotionCouponName4json = promotionCouponNameinputs[i].value;
        const promotionName4json = promotionNameinputs[i].value;
        const couponTypeNo4json = couponTypeNoinputs[i].value;
        const amountOfCoupon4json = amountOfCouponinputs[i].value;
        const getCouponLimitLevel4json = getCouponLimitLevelinputs[i].value;
        const getCouponLimitAmount4json = getCouponLimitAmountinputs[i].value;
        const promotionEffectiveDate4json = promotionEffectiveDateinputs[i].value;
        const promotionExpireDate4json = promotionExpireDateinputs[i].value;
        // const getAmount4json = getAmountinputs[i].value;

        msgs[i].textContent = '';
        const promotionNameLength = promotionCouponName4json.length;
        if (promotionNameLength < 1 || promotionNameLength > 100) {
            msgs[i].textContent = '名稱長度須介於1~100字元';
            return;
        }
        if (amountOfCoupon4json === '') {
            msgs[i].textContent = '發放數量不可為空';
            return;
        }
        if (getCouponLimitLevel4json === '') {
            msgs[i].textContent = '等級限制不可為空';
            return;
        }
        if (getCouponLimitAmount4json === '') {
            msgs[i].textContent = '數量限制不可為空';
            return;
        }
        if (promotionEffectiveDate4json === '') {
            msgs[i].textContent = '開始日期不可為空';
            return;
        }
        if (promotionExpireDate4json === '') {
            msgs[i].textContent = '結束日期不可為空';
            return;
        }

        // 檢查結束

        fetch('editPromotionCoupon', {
            method: 'POST', headers: {
                'Content-Type': 'application/json',
            }, body: JSON.stringify({
                promotionCouponNo: promotionCouponNo4json,
                promotionCouponName: promotionCouponName4json,
                promotionName: promotionName4json,
                couponTypeNo: couponTypeNo4json,
                amountOfCoupon: amountOfCoupon4json,
                getCouponLimitLevel: getCouponLimitLevel4json,
                getCouponLimitAmount: getCouponLimitAmount4json,
                promotionEffectiveDate: promotionEffectiveDate4json,
                promotionExpireDate: promotionExpireDate4json,
                // getAmount: getAmount4json,
                promotionPic4json: base64Image
            }),
        })
            .then(resp => resp.json())
            .then(body => {
                console.log(body);
                const {successful} = body;
                if (successful) {
                    msgs[i].textContent = '修改成功';
                    Swal.fire({
                        position: 'center', icon: 'success', title: '修改成功!', showConfirmButton: false, timer: 1500
                    }).then(() => {
                        location.reload()
                    })
                } else {
                    Swal.fire({
                        icon: 'error', title: 'Oops...', text: '修改失敗!', footer: '<a href=""></a>'
                    })
                }
            });

    }

    // ============================5. 綁定所有修改燈箱按鈕click / 圖片上傳change事件========================
    function addeventlistener4editbutton() {
        for (let i = 0; i <= dataaccount; i++) {
            editbuttons[i].addEventListener('click', () => {
                console.log("修改按鈕啟動")
                editPromotion(i);
            })
            promotionPicinputs[i].addEventListener("change", function (event) {
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
    const msg2 = document.querySelector('#msga');
    getpicinput()
    function getpicinput() {
        const promotionPic4new = document.querySelector('#promotionPic');
        promotionPic4new.addEventListener("change", function (event) {
            readPic(event);
            console.log("有圖")
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: '上傳成功!',
                showConfirmButton: false,
                timer: 1500
            })
        });
    };


    function newAPromotion() {
        const promotionCouponName4new = document.querySelector('#promotionCouponName').value;
        const promotionName4new = document.querySelector('#promotionName').value;
        const couponTypeNo4new = document.querySelector('#couponTypeNo').value;
        const amountOfCoupon4new = document.querySelector('#amountOfCoupon').value;
        const getCouponLimitLevel4new = document.querySelector('#getCouponLimitLevel').value;
        const getCouponLimitAmount4new = document.querySelector('#getCouponLimitAmount').value;
        const promotionEffectiveDate4new = document.querySelector('#promotionEffectiveDate').value;
        const promotionExpireDate4new = document.querySelector('#promotionExpireDate').value;

        const promotionNameLength = promotionCouponName4new.length;
        if (promotionNameLength < 1 || promotionNameLength > 100) {
            msg2.textContent = '名稱長度須介於1~100字元';
            return;
        }
        if (promotionName4new === '請選擇活動種類') {
            msg2.textContent = '活動種類為必選';
            return;
        }
        if (couponTypeNo4new === '請選擇折價券種類') {
            msg2.textContent = '折價券種類為必選';
            return;
        }
        if (amountOfCoupon4new === '') {
            msg2.textContent = '發放數量不可為空';
            return;
        }
        if (getCouponLimitLevel4new === '') {
            msg2.textContent = '等級限制不可為空';
            return;
        }
        if (getCouponLimitAmount4new === '') {
            msg2.textContent = '數量限制不可為空';
            return;
        }
        if (promotionEffectiveDate4new === '') {
            msg2.textContent = '開始日期不可為空';
            return;
        }
        if (promotionExpireDate4new === '') {
            msg2.textContent = '結束日期不可為空';
            return;
        }


        fetch('newPromotionCoupon', {
            method: 'POST', headers: {
                'Content-Type': 'application/json',
            }, body: JSON.stringify({
                promotionCouponName: promotionCouponName4new,
                promotionName: promotionName4new,
                couponTypeNo: couponTypeNo4new,
                amountOfCoupon: amountOfCoupon4new,
                getCouponLimitLevel: getCouponLimitLevel4new,
                getCouponLimitAmount: getCouponLimitAmount4new,
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
                        icon: 'error', title: 'Oops...', text:`${message}`, footer: '<a href=""></a>'
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
            deletebuttons[i].addEventListener('click', () => {
                const inputvalue = promotionCouponNoinputs[i].value;
                deledtbyPK(inputvalue);
            })
        }
    }

    // ============================9. 刪除方法========================
    function deledtbyPK(promotionCouponNo) {
        const confirmed = confirm("確定要刪除嗎？");
        if (confirmed) {
            fetch('deletePromotionCoupon', {
                method: 'POST', headers: {
                    'Content-Type': 'application/json',
                }, body: JSON.stringify({
                    promotionCouponNo: promotionCouponNo
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

    // ============================10.查詢折價券種類========================
    const select4CouponType = document.querySelector('#couponTypeNo');
    const dynamicSpansCouponTypeNo = document.querySelector('#dynamicSpansCouponTypeNo');
    let CouponTypelength = 0;
    let x = 0;

    function getAllCouponType() {
        console.log('進入getAllCouponType()')
        fetch("getAllCouponType")
            .then(function (response) {
                // 檢查 API 响應的狀態碼
                if (response.status !== 200) {
                    console.log('發生錯誤，狀態碼：' + response.status);
                    return;
                }

                // 解析 JSON 格式的數據
                response.json().then(function (data) {
                    // 在此處可以處理從 API 獲取的數據
                    CouponType = data;
                    console.log('獲取的折價券種類資訊：', CouponType);
                    const lastData = CouponType[CouponType.length - 1];
                    CouponTypelength = lastData.couponTypeNo;
                    let str = ' <option selected disabled>請選擇折價券種類</option>';
                    let str2 = '';
                    let str3 = [];
                    let str4 = [];
                    let str3_0 = '';
                    let str4_0 = '';
                    for (let x = 0; x <= dataaccount; x++) {
                        for (let u = 0; u < CouponType.length; u++) {
                            let row = CouponType[u];
                            const couponTypeNo2 = row.couponTypeNo;
                            const couponTypeName = row.couponTypeName;
                            let adminNod = "";
                            if (row.adminNo != null) {
                                adminNod = row.adminNo;
                            }
                            let counterNo = "";
                            if (row.counterNo != null) {
                                counterNo = row.counterNo;
                            }
                            // 處理日期格式
                            const originalDate = new Date(row.couponCreateDate);
                            const year = originalDate.getFullYear();
                            const month = originalDate.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                            const day = originalDate.getDate();
                            const formattedDate = year + '-' + ('0' + month).slice(-2) + '-' + ('0' + day).slice(-2);
                            // 處理日期格式
                            const couponCreateDate = formattedDate;
                            // 處理日期格式
                            const originalDatea = new Date(row.couponExpireDate);
                            const yeara = originalDatea.getFullYear();
                            const montha = originalDatea.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                            const daya = originalDatea.getDate();
                            const formattedDatea = yeara + '-' + ('0' + montha).slice(-2) + '-' + ('0' + daya).slice(-2);
                            // 處理日期格式
                            const couponExpireDate = formattedDatea;

                            const couponConditions = row.couponConditions;
                            const couponPrice = row.couponPrice;
                            const couponLowest = row.couponLowest;

                            str4_0 += `<option value="${couponTypeNo2}" ${couponTypeNo2 === couponTypeNo4[x] ? 'selected' : ''}>${couponTypeNo2} : ${couponTypeName}</option>`
                            str3_0 += `<div id="span4CouponType${couponTypeNo2}${x}" class="hiddenyee">
                                <span>管理員編號： ${adminNod}</span>
                                <span>欄位編號: ${counterNo}</span>
                                <br>
                                <span>建立日期: ${couponCreateDate}</span>
                                <span>失效日期: ${couponExpireDate}</span>
                                <br>
                                <span>折扣金額: ${couponPrice}</span>
                                <span>使用門檻: ${couponLowest}</span>
                                <br>
                                <span>使用說明: ${couponConditions}</span>
                                </div>`
                        }
                        str3.push(str3_0);
                        str3_0 = '';
                        str4.push(str4_0);
                        str4_0 = '';
                    }

                    for (let i = 0; i < CouponType.length; i++) {
                        let row = CouponType[i];
                        const couponTypeNo2 = row.couponTypeNo;
                        const couponTypeName = row.couponTypeName;
                        let adminNod = "";
                        if (row.adminNo != null) {
                            adminNod = row.adminNo;
                        }
                        let counterNo = "";
                        if (row.counterNo != null) {
                            counterNo = row.counterNo;
                        }
                        // 處理日期格式
                        const originalDate = new Date(row.couponCreateDate);
                        const year = originalDate.getFullYear();
                        const month = originalDate.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                        const day = originalDate.getDate();
                        const formattedDate = year + '-' + ('0' + month).slice(-2) + '-' + ('0' + day).slice(-2);
                        // 處理日期格式
                        const couponCreateDate = formattedDate;
                        // 處理日期格式
                        const originalDatea = new Date(row.couponExpireDate);
                        const yeara = originalDatea.getFullYear();
                        const montha = originalDatea.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                        const daya = originalDatea.getDate();
                        const formattedDatea = yeara + '-' + ('0' + montha).slice(-2) + '-' + ('0' + daya).slice(-2);
                        // 處理日期格式
                        const couponExpireDate = formattedDatea;

                        const couponConditions = row.couponConditions;
                        const couponPrice = row.couponPrice;
                        const couponLowest = row.couponLowest;
                        str += `<option value="${couponTypeNo2}" >${couponTypeNo2} : ${couponTypeName}</option>`
                        str2 += `<div id="span4CouponType${couponTypeNo2}" class="hiddenyee">
                                <span>管理員編號： ${adminNod}</span>
                                <span>欄位編號: ${counterNo}</span>
                                <br>
                                <span>建立日期: ${couponCreateDate}</span>
                                <span>失效日期: ${couponExpireDate}</span>
                                <br>
                                <span>折扣金額: ${couponPrice}</span>
                                <span>使用門檻: ${couponLowest}</span>
                                <br>
                                <span>使用說明: ${couponConditions}</span>
                                </div>`

                    }
                    dynamicSpansCouponTypeNo.innerHTML = str2;
                    select4CouponType.innerHTML = str;
                    for (let i = 0; i <= dataaccount; i++) {
                        if (typeof dynamicSpanscouponTypeNoinputs[i] !== 'undefined') {
                            dynamicSpanscouponTypeNoinputs[i].innerHTML = str3[i];
                        }
                        if (typeof couponTypeNoinputs[i] !== 'undefined') {
                            couponTypeNoinputs[i].innerHTML = str4[i];
                        }
                    }
                    setdiv4CouponType();
                    Listener4SelectCoupontype()
                    setdiv4div4CouponType2()

                });
            })
            .catch(function (err) {
                console.log('錯誤：', err);
            });
    }

    // ============================11.抓到選項下div並綁定select========================
    let div4CouponType = [];

    function setdiv4CouponType() {
        for (let i = 0; i <= CouponTypelength; i++) {
            const div4CouponTypea = document.getElementById('span4CouponType' + i);
            if (div4CouponTypea) {
                div4CouponType.push(div4CouponTypea);
            }
        }
    }

    let div4CouponType2 = [];
    let div4CouponType3 = []

    function setdiv4div4CouponType2() {//ok
        for (let p = 0; p <= dataaccount; p++) {
            for (let i = 0; i < CouponTypelength; i++) {
                const div4CouponTypea = document.getElementById('span4CouponType' + i + p);
                if (div4CouponTypea) {
                    div4CouponType2.push(div4CouponTypea);
                }
            }
            div4CouponType3.push(div4CouponType2);
            div4CouponType2 = [];
        }
    }

    function Listener4SelectCoupontype() {
        for (let i = 0; i <= dataaccount; i++) {
            couponTypeNoinputs[i].addEventListener('change', () => {
                const selectedValue2 = couponTypeNoinputs[i].value;
                div4CouponType3[i].forEach(function (div) {
                    if (div.id === "span4CouponType" + selectedValue2 + i) {
                        div.classList.remove("hiddenyee");
                    } else {
                        div.classList.add("hiddenyee");
                    }
                });

            })
        }
        select4CouponType.addEventListener('change', () => {
            const selectedValue = select4CouponType.value;
            div4CouponType.forEach(function (div) {
                if (div.id === "span4CouponType" + selectedValue) {
                    div.classList.remove("hiddenyee");
                } else {
                    div.classList.add("hiddenyee");
                }
            });
        })
    };

    // ============================12.查詢活動種類========================
    const select4promotionName = document.querySelector('#promotionName');
    const dynamicSpanPromotionName = document.querySelector('#dynamicSpanPromotionName');
    let promotionNamelength = 0;
    let promotionname = [];

    function getAllPromotion() {
        console.log('進入getAllPromotion()')
        fetch("getAllPromotion")
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
                            if (row.adminNo != null) {
                                adminNo = row.adminNo;
                            }
                            let counterNo = '';
                            if (row.counterNo != null) {
                                counterNo = row.counterNo
                            }
                            str4_0 += `<option value="${promotionName2}" ${promotionName2 === promotionName4[h] ? 'selected' : ''}>${promotionName2}</option>`
                            str3_0 += `<div id="span4Promotion${promotionName2}${h}" class="hiddenyee">
                                <span>管理員編號： ${adminNo}</span>
                                <span>欄位編號: ${counterNo}</span>
                                <br>
                                <span>發放種類: ${promotionType}</span>
                                <span>發放方式: ${promotionMethod}</span>
                                </div>`
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
                        if (row.adminNo != null) {
                            adminNo = row.adminNo;
                        }
                        let counterNo = '';
                        if (row.counterNo != null) {
                            counterNo = row.counterNo
                        }

                        str += `<option value="${promotionName2}" >${promotionName2}</option>`
                        str2 += `<div id="span4Promotion${promotionName2}" class="hiddenyee">
                                <span>管理員編號： ${adminNo}</span>
                                <span>欄位編號: ${counterNo}</span>
                                <br>
                                <span>發放種類: ${promotionType}</span>
                                <span>發放方式: ${promotionMethod}</span>
                                </div>`

                    }
                    dynamicSpanPromotionName.innerHTML = str2;
                    select4promotionName.innerHTML = str;
                    for (let i = 0; i <= dataaccount; i++) {
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
            promotionNameinputs[i].addEventListener('change', () => {
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
        select4promotionName.addEventListener('change', () => {
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
                console.log()
            };
            reader.readAsDataURL(file); // 讀取成url
            // return base64Image;
        }
    }

    // ===============================^^^方法區^^^====================================

    // ===============================VVV使用方法區VVV================================

    //=================================1. 總之先查一次=================================
    getAllPromotionCoupon();


    // ===============================2. 確認新增按鈕================================

    const button4new = document.querySelector('#newbutton');
    button4new.addEventListener('click', () => {
        newAPromotion();
    })
//=================================3. 圖片檔案上傳按鈕=============================


    // ===============================^^^使用方法區^^^==============================

})();