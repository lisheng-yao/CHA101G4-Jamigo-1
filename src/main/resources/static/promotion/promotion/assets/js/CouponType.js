(() => {
    const tbody = document.querySelector('#tbody');
    const localstorageadminNo = localStorage.getItem("adminNo");

    // ===============================VVV方法區VVV====================================

    // ============================1.查資料回來getAllPromotion() 拿到字串和筆數========================
    let dataaccount = 0;
    let CouponType = [];

    function getAllPromotion() {
        console.log('進入getAllcouponType()')
        fetch("http://localhost:8080/Jamigo/Promotion/GetAllcouponType")
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
                    console.log('獲取的促銷活動數據：', CouponType);

                    for (let i = 0; i < CouponType.length; i++) {
                        dataaccount = i;
                        let row = CouponType[i];
                        const couponTypeNo = row.couponTypeNo;
                        const couponTypeName = row.couponTypeName;
                        const adminNod = row.adminNo;
                        const counterNo = row.counterNo;

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
                        dataTable.row.add([
                            couponTypeNo,
                            couponTypeName,
                            adminNod,
                            counterNo,
                            couponCreateDate,
                            couponExpireDate,
                            couponPrice,
                            couponLowest,
                            couponConditions,
                            `<button type="button" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#exampleModal${i}" data-bs-whatever="@mdo" id="editbutton${i}" ${counterNo !== null && !isNaN(counterNo) ? `style="display:none"` : ''}>修改
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
                                    <label for="recipientNo${i}"
                                           class="col-form-label">折價券種類編號:</label>
                                    <input type="text" class="form-control"
                                           id="recipientNo${i}" value="${couponTypeNo}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="recipientname${i}"
                                           class="col-form-label">活動種類名稱:</label>
                                    <input type="text" class="form-control"
                                           id="recipientname${i}" value="${couponTypeName}" >
                                </div>
                                <div class="mb-3">
                                    <label for="recipientcNo${i}"
                                           class="col-form-label">管理員編號:</label>
                                    <input type="text" class="form-control"
                                           id="recipientcNo${i}" value="${localstorageadminNo}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="recipientaNo${i}"
                                           class="col-form-label">欄位編號:</label>
                                    <input type="text" class="form-control"
                                           id="recipientaNo${i}" value="" readonly>
                                </div>
                                
                                <div class="mb-3" >
                                    <label for="recipientaDatea${i}"
                                           class="col-form-label">建立日期:</label>
                                    <input type="text" class="form-control"
                                           id="recipientaDatea${i}" value="${couponCreateDate}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="recipientaDateb${i}"
                                           class="col-form-label">失效日期:</label>
                                    <input type="date" class="form-control"
                                           id="recipientaDateb${i}" value="${couponExpireDate}" >
                                </div>
                                <div class="mb-3">
                                    <label for="recipientaPrice${i}"
                                           class="col-form-label">  折扣金額:</label>
                                    <input type="text" class="form-control"
                                           id="recipientaPrice${i}" value="${couponPrice}" >
                                </div>
                                <div class="mb-3">
                                    <label for="recipientaLow${i}"
                                           class="col-form-label"> 使用門檻:</label>
                                    <input type="text" class="form-control"
                                           id="recipientaLow${i}" value="${couponLowest}" >
                                </div>
                                 <div class="mb-3">
                                    <label for="recipientaCond${i}"
                                           class="col-form-label"> 使用說明:</label>
                                    <input type="text" class="form-control "
                                           id="recipientaCond${i}" value="${couponConditions}" >
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
            </div>`,
                            `<button type="button" class="btn btn-primary" id="delete${i}" ${counterNo !== null && !isNaN(counterNo) ? `style="display:none"` : ''}>刪除</button>`

                        ]);
                    }
                    dataTable.draw();
                    seteditbutton();
                    setinputinbox();
                    addeventlistener4editbutton();
                    setdeletebutton();
                    addeventlistener4deletebutton();
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
                target: 8,
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
    let recipientNoinputs = [];
    let recipientnameinputs = [];
    let recipientcNoinputs = [];
    let recipientaNoinputs = [];
    let recipientaDateainputs = [];
    let recipientaDatebinputs = [];
    let recipientaPriceinputs = [];
    let recipientaLowinputs = [];
    let recipientaCondinputs = [];
    let msgs = [];

    function setinputinbox() {
        for (let i = 0; i <= dataaccount; i++) {
            const recipientNoinput = document.querySelector('#recipientNo' + i);
            const recipientnameinput = document.querySelector('#recipientname' + i);
            const recipientcNoinput = document.querySelector('#recipientcNo' + i);
            const recipientaNoinput = document.querySelector('#recipientaNo' + i);
            const recipientaDateainput = document.querySelector('#recipientaDatea' + i);
            const recipientaDatebinput = document.querySelector('#recipientaDateb' + i);
            const recipientaPriceinput = document.querySelector('#recipientaPrice' + i);
            const recipientaLowinput = document.querySelector('#recipientaLow' + i);
            const recipientaCondinput = document.querySelector('#recipientaCond' + i);
            const msg = document.querySelector('#msg' + i);
            recipientNoinputs.push(recipientNoinput);
            recipientnameinputs.push(recipientnameinput);
            recipientcNoinputs.push(recipientcNoinput);
            recipientaNoinputs.push(recipientaNoinput);
            recipientaDateainputs.push(recipientaDateainput);
            recipientaDatebinputs.push(recipientaDatebinput);
            recipientaPriceinputs.push(recipientaPriceinput);
            recipientaLowinputs.push(recipientaLowinput);
            recipientaCondinputs.push(recipientaCondinput);
            msgs.push(msg);
        }
    }


    // ============================4. 修改資料進去 editPromotion()========================
    function editPromotion(i) {
        const recipientNoinputs4json = recipientNoinputs[i].value;
        const recipientnameinputs4json = recipientnameinputs[i].value;
        const recipientcNoinputs4json = recipientcNoinputs[i].value;
        const recipientaNoinputs4json = recipientaNoinputs[i].value;
        // const recipientaDateainputs4json = recipientaDateainputs[i].value;
        const recipientaDatebinputs4json = recipientaDatebinputs[i].value;
        const recipientaPriceinputs4json = recipientaPriceinputs[i].value;
        const recipientaLowinputs4json = recipientaLowinputs[i].value;
        const recipientaCondinputs4json = recipientaCondinputs[i].value;

        const promotionNameLength = recipientnameinputs4json.length;
        if (promotionNameLength < 1 || promotionNameLength > 20) {
            msgs[i].textContent = '名稱長度須介於1~20字元';
            return;
        }
        if (recipientaDatebinputs4json === '') {
            msgs[i].textContent = '失效日期不可為空';
            return;
        }
        if (recipientaPriceinputs4json === '') {
            msgs[i].textContent = '價格不可為空';
            return;
        }
        if (recipientaLowinputs4json === '') {
            msgs[i].textContent = '門檻不可為空';
            return;
        }

        if (recipientaCondinputs4json === '') {
            msgs[i].textContent = '使用說明不可為空';
            return;
        }

        msg.textContent = '';
        // 檢查結束

        fetch('editcouponType', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                couponTypeNo: recipientNoinputs4json,
                couponTypeName: recipientnameinputs4json,
                adminNo: recipientcNoinputs4json,
                counterNo: recipientaNoinputs4json,
                // couponCreateDate: recipientaDateainputs4json,
                couponExpireDate: recipientaDatebinputs4json,
                couponPrice: recipientaPriceinputs4json,
                couponLowest: recipientaLowinputs4json,
                couponConditions: recipientaCondinputs4json
            }),
        })
            .then(resp => resp.json())
            .then(body => {
                console.log(body);
                const {successful} = body;
                if (successful) {
                    msg.className = 'info';
                    msg.textContent = '修改成功';
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: '修改成功!',
                        showConfirmButton: false,
                        timer: 1500
                    }).then(() => {
                        location.reload()
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

    // ============================5. 綁定所有修改燈箱按鈕click事件========================
    function addeventlistener4editbutton() {
        for (let i = 0; i <= dataaccount; i++) {
            editbuttons[i]?.addEventListener('click', () => {
                editPromotion(i);
            })
        }
    }

    // ============================6.   newAPromotion()新增promotion========================
    const coupontypecNo = document.querySelector('#coupontypecNo');
    coupontypecNo.value = localstorageadminNo;
    console.log("coupontypecNo"+coupontypecNo.value);
    function newAPromotion() {
        // const coupontypeNo4new = document.querySelector('#coupontypeNoe').value;
        const coupontypeName4new = document.querySelector('#coupontypeName').value;
        const coupontypecNo4new = document.querySelector('#coupontypecNo').value;
        const coupontypeaNo4new = document.querySelector('#coupontypeaNo').value;
        const coupontypeExpireDate4new = document.querySelector('#coupontypeExpireDate').value;
        const coupontypeDate4new = document.querySelector('#coupontypeDate').value;
        const coupontypePrice4new = document.querySelector('#coupontypePrice').value;
        const coupontypeLowest4new = document.querySelector('#coupontypeLowest').value;
        const coupontypeConditions4new = document.querySelector('#coupontypeConditions').value;
        const msg = document.querySelector('#msg');

        //檢查
        const promotionNameLength = coupontypeName4new.length;
        if (promotionNameLength < 1 || promotionNameLength > 20) {
            msg.textContent = '名稱長度須介於1~20字元';
            return;
        }
        console.log(coupontypeExpireDate4new)
        if (coupontypeExpireDate4new === '') {
            msg.textContent = '失效日期不可為空';
            return;
        }
        if (coupontypePrice4new === '') {
            msg.textContent = '折扣金額不可為空';
            return;
        }
        if (coupontypeLowest4new === '') {
            msg.textContent = '門檻不可為空';
            return;
        }

        if (coupontypeConditions4new === '') {
            msg.textContent = '使用說明不可為空';
            return;
        }


        fetch('newcouponType', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                // couponTypeNo: coupontypeNo4new,
                couponTypeName: coupontypeName4new,
                adminNo: coupontypecNo4new.value,
                counterNo: coupontypeaNo4new,
                couponCreateDate: coupontypeDate4new,
                couponConditions: coupontypeConditions4new,
                couponPrice: coupontypePrice4new,
                couponExpireDate: coupontypeExpireDate4new,
                couponLowest: coupontypeLowest4new
            }),
        })
            .then(resp => resp.json())
            .then(body => {
                console.log(body);
                const {successful, Message} = body;
                if (successful) {

                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: '新增成功!',
                        showConfirmButton: false,
                        timer: 1500
                    }).then(() => {
                        location.reload()
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: Message,
                        footer: '<a href=""></a>'
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
                const inputvalue = recipientNoinputs[i].value;
                deledtbyPK(inputvalue);
            })
        }
    }

    // ============================9. 刪除方法========================
    function deledtbyPK(CouponTypeNo) {
        const confirmed = confirm("確定要刪除嗎？");
        if (confirmed) {
            fetch('deletecouponType', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    couponTypeNo: CouponTypeNo
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
                                icon: 'error',
                                title: 'Oops...',
                                text: '刪除失敗!',
                                footer: '<a href=""></a>'
                            })
                        }
                    }
                )
        }
    };

    // ===============================^^^方法區^^^====================================

    // ===============================VVV使用方法區VVV================================

    //=================================1. 總之先查一次=================================
    getAllPromotion();


    // ===============================2. 確認新增按鈕================================

    const button4new = document.querySelector('#newbutton');
    button4new?.addEventListener('click', () => {
        newAPromotion();
    })
    //=================================3. 刪除按鈕=============================


    // ===============================^^^使用方法區^^^==============================

})();