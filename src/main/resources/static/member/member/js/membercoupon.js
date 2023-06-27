(() => {
    const memberId = localStorage.getItem('memberNo');
    // ===============================VVV方法區VVV====================================

    // ============================1.查資料回來getAllPromotion() 拿到字串和筆數========================
    let dataaccount = 0;
    let memberCoupon = [];

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
                        const memberNo = row.memberNo;
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
                        const orderDetailCouponNo = row.orderDetailCouponNo;
                        dataTable.row.add([
                            memberCouponNo,
                            couponTypeNo,
                            couponUsedStat,
                            orderDetailCouponNo,
                            couponUsedTime,
                        ]);
                    }
                    dataTable.draw();
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

    // ===============================^^^方法區^^^====================================

    // ===============================VVV使用方法區VVV================================

    //=================================1. 總之先查一次=================================
    getAllPromotion();

    // ===============================^^^使用方法區^^^==============================

})();