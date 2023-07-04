(() => {
    const tbody = document.querySelector('#tbody');
    const localstorageadminNo = localStorage.getItem("adminNo");

    // ===============================VVV方法區VVV====================================

    // ============================1.查資料回來getAllPromotion() 拿到字串和筆數========================
    let dataaccount = 0;
    let PromotionType = [];

    function getAllPromotion() {
        console.log('進入getAllPromotion()')
        fetch("/Jamigo/Promotion/GetAllPromotionType")
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


                    for (let i = 0; i < PromotionType.length; i++) {
                        dataaccount = i;
                        let row = PromotionType[i];
                        const promotionName = row.promotionName;
                        const promotionType = row.promotionType;
                        const promotionMethod = row.promotionMethod;
                        const adminNo = row.adminNo;
                        const counterNo = row.counterNo;
                        dataTable.row.add([
                            row.promotionName,
                            row.promotionType,
                            row.promotionMethod,
                            row.adminNo,
                            row.counterNo,
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
                                    <label for="recipientname${i}"
                                           class="col-form-label">活動種類名稱:</label>
                                    <input type="text" class="form-control"
                                           id="recipientname${i}" value="${promotionName}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="recipienttype${i}"
                                           class="col-form-label">發放種類:</label>
                                    <select name="" id="recipienttype${i}" class="form-control">
                                        <option value="折價券" ${promotionType === '折價券' ? 'selected' : ''}>折價券
                                        </option>
                                        <option value="點數" ${promotionType === '點數' ? 'selected' : ''}>點數</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="recipientmethed${i}"
                                           class="col-form-label">發放方式:</label>
                                    <select name="" id="recipientmethed${i}" class="form-control">
                                        <option value="結帳後取得"
                                                ${promotionMethod === '結帳後取得' ? 'selected' : ''}>結帳後取得
                                        </option>
                                        <option value="自由兌換"
                                                ${promotionMethod === '自由兌換' ? 'selected' : ''}>自由兌換
                                        </option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="recipientcNo${i}"
                                           class="col-form-label">管理員編號:</label>
                                    <input type="text" class="form-control"
                                           id="recipientcNo${i}" value="${localstorageadminNo}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="recipientaNo${i}"
                                           class="col-form-label">櫃位編號:</label>
                                    <input type="text" class="form-control"
                                           id="recipientaNo${i}" value="" readonly>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                         <span id="msg"> </span>
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
    let promotionNameinputs = [];
    let promotionTypeinputs = [];
    let promotionMethodinputs = [];
    let adminNoinputs = [];
    let counterNoinputs = [];

    function setinputinbox() {
        for (let i = 0; i <= dataaccount; i++) {
            const promotionNameinput = document.querySelector('#recipientname' + i);
            const promotionTypeinput = document.querySelector('#recipienttype' + i);
            const promotionMethodinput = document.querySelector('#recipientmethed' + i);
            const adminNoinput = document.querySelector('#recipientcNo' + i);
            const counterNoinput = document.querySelector('#recipientaNo' + i);
            promotionNameinputs.push(promotionNameinput);
            promotionTypeinputs.push(promotionTypeinput);
            promotionMethodinputs.push(promotionMethodinput);
            adminNoinputs.push(adminNoinput);
            counterNoinputs.push(counterNoinput);
        }
    }


    // ============================4. 修改資料進去 editPromotion()========================
    function editPromotion(i) {
        const promotionName4json = promotionNameinputs[i].value;
        const promotionType4json = promotionTypeinputs[i].value;
        const promotionMethod4json = promotionMethodinputs[i].value;
        const adminNo4json = adminNoinputs[i].value;
        const counterNo4json = counterNoinputs[i].value;


        msg.textContent = '';
        const promotionNameLength = promotionName4json.length;
        if (promotionNameLength < 1 || promotionNameLength > 10) {
            msg.textContent = '名稱長度須介於1~10字元';
            return;
        }
        // 檢查結束

        fetch('editpromotion', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                promotionName: promotionName4json,
                promotionType: promotionType4json,
                promotionMethod: promotionMethod4json,
                adminNo: adminNo4json,
                counterNo: counterNo4json
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
                    }).then(()=>{location.reload()})
                } else {
                    msg.className = 'error';
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
    const msg2 =document.querySelector('#msg2');
    const recipientcNo4in = document.querySelector('#recipient-cNo');
    recipientcNo4in.value=localstorageadminNo;

    function newAPromotion() {
        const recipientname4new = document.querySelector('#recipient-name').value;
        const recipienttype4new = document.querySelector('#recipient-type').value;
        const recipientmethed4new = document.querySelector('#recipient-methed').value;
        const recipientcNo4new = document.querySelector('#recipient-cNo').value;
        const recipientaNo4new = document.querySelector('#recipient-aNo').value;
        const promotionNameLength = recipientname4new.length;
        if (promotionNameLength < 1 || promotionNameLength > 10) {
            msg2.textContent = '名稱長度須介於1~10字元';
            return;
        }
        fetch('newpromotion', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                promotionName: recipientname4new,
                promotionType: recipienttype4new,
                promotionMethod: recipientmethed4new,
                adminNo: recipientcNo4new,
                counterNo: recipientaNo4new
            }),
        })
            .then(resp => resp.json())
            .then(body => {
                console.log(body);
                const {successful, message} = body;
                if (successful) {

                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: '新增成功!',
                        showConfirmButton: false,
                        timer: 1500
                    }).then(()=>{location.reload()})
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: `${message}`,
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
                const inputvalue =promotionNameinputs[i].value;
                deledtbyPK(inputvalue);
            })
        }
    }

    // ============================9. 刪除方法========================
    function deledtbyPK(promotionName) {
        const confirmed = confirm("確定要刪除嗎？");
        if (confirmed) {
            fetch('deletepromotion', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    promotionName: promotionName
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
                            }).then(()=>{location.reload()})
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