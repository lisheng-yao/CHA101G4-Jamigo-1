(() => {
    const memberId = localStorage.getItem('memberNo');
    // ===============================VVV方法區VVV====================================

    // ============================1.查資料回來getAllPromotion() 拿到字串和筆數========================
    let dataaccount = 0;
    let memberCoupon = [];
    let couponTypeNos = []

    function getAllPromotion() {
        console.log('進入getAllPromotion()')
        fetch("getAllMemberCoupon", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                memberNo: memberId,
            }),
        })
            .then(function (response) {
                // 檢查 API 响應的狀態碼
                if (response.status !== 200) {
                    console.log('發生錯誤，狀態碼：' + response.status);
                    return;
                }

                // 解析 JSON 格式的數據
                response.json().then(function (data) {
                    // 在此處可以處理從 API 獲取的數據
                    memberCoupon = data;
                    console.log(memberCoupon);
                    for (let i = 0; i < memberCoupon.length; i++) {
                        dataaccount = i;
                        let row = memberCoupon[i];
                        const memberCouponNo = row.memberCouponId.memberCouponNo;
                        const couponTypeNo = row.memberCouponId.couponTypeNo;
                        couponTypeNos.push(couponTypeNo);
                        const couponUsedStat = row.couponUsedStat;
                        let couponUsedTime = '';
                        if (row.couponUsedTime !== null) {
                            //     處理日期格式
                            const originalDate = new Date(row.couponUsedTime);
                            const year = originalDate.getFullYear();
                            const month = originalDate.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                            const day = originalDate.getDate();
                            const formattedDate2 = year + '-' + ('0' + month).slice(-2) + '-' + ('0' + day).slice(-2);
                            //     處理日期格式
                            couponUsedTime = formattedDate2;
                        }
                        let usedStat=(couponUsedStat===0)?'未使用':'已使用';
                        const orderDetailCouponNo = row.orderDetailCouponNo;
                        dataTable.row.add([
                            couponTypeNo,
                            `<div id="coupontypename${i}"></div>`,
                            `<button type="button" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#exampleModal${i}" data-bs-whatever="@mdo" id="editbutton${i}">詳情
            </button>
            <div class="modal fade" id="exampleModal${i}" tabIndex="-1"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel${i}">折價券詳情
                            </h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body" id="memberCoupon${i}">
                        
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary editbutton"
                                    data-bs-dismiss="modal" id="cancle${i}">確定
                            </button>
                        </div>
                    </div>
                </div>
            </div>`,
                            usedStat,
                            orderDetailCouponNo,
                            couponUsedTime,
                        ]);
                    }
                    dataTable.draw();
                    getbodydiv();
                    selectbycouponTypeNo();
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
    // ============================3.   抓全部的詳情內的body========================
    const bodydiv = [];
    const coupontypenamediv = [];

    function getbodydiv() {
        for (let i = 0; i <= dataaccount; i++) {
            const bodyi = document.querySelector('#memberCoupon' + i)
            const coupontypename = document.querySelector('#coupontypename'+i)
            bodydiv.push(bodyi);
            coupontypenamediv.push(coupontypename);

        }
    }


    // ============================4.   把資料放進詳情========================
    function selectbycouponTypeNo() {
        for (let i = 0; i <= dataaccount; i++) {
            fetch('getcoupontype', {
                method: 'POST', headers: {
                    'Content-Type': 'application/json',
                }, body: JSON.stringify({
                    couponTypeNo: couponTypeNos[i]
                }),
            })
                .then(response => response.json())
                .then(body => {
                    console.log(body);
                    const {
                        couponTypeName,
                        couponExpireDate,
                        couponConditions,
                        couponPrice,
                    } = body;
                    // 處理日期格式
                    const originalDate = new Date(couponExpireDate);
                    const year = originalDate.getFullYear();
                    const month = originalDate.getMonth() + 1; // 月份是從 0 開始計算，所以需要加 1
                    const day = originalDate.getDate();
                    const formattedDate = year + '-' + ('0' + month).slice(-2) + '-' + ('0' + day).slice(-2);
                    const couponExpireDatea = formattedDate;



                    // 處理日期格式
                    let str = `
                            <form>
                                
                                <div class="mb-3">
                                    <label for="couponTypeName${i}"
                                           class="col-form-label">折價券名稱:</label>
                                    <input type="text" class="form-control"
                                           id="couponTypeNameo${i}" value="${couponTypeName}" readonly>
                                </div>
                                
                                 <div class="mb-3">
                                    <label for="couponExpireDate${i}"
                                           class="col-form-label">失效日:</label>
                                    <input type="text" class="form-control"
                                           id="couponExpireDate${i}" value="${couponExpireDatea}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="couponPrice${i}"
                                           class="col-form-label">折扣金額:</label>
                                    <input type="text" class="form-control"
                                           id="couponPrice${i}" value="${couponPrice}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="couponConditions${i}"
                                           class="col-form-label">使用說明:</label>
                                    <input type="text" class="form-control"
                                           id="couponConditions${i}" value="${couponConditions}" readonly>
                                </div>
                            </form>
                    `;
                    bodydiv[i].innerHTML = str;
                    coupontypenamediv[i].innerText=couponTypeName;
                });
        }
    }


    // ===============================^^^方法區^^^====================================

    // ===============================VVV使用方法區VVV================================

    //=================================1. 總之先查一次=================================
    getAllPromotion();

    // ===============================^^^使用方法區^^^==============================

})();