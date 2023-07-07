package com.jamigo.member.member_coupon.Util;

import com.jamigo.member.member_coupon.Service.MemberCouponService;
import com.jamigo.member.member_coupon.entity.MemberCoupon;
import com.jamigo.promotion.CouponType.Entity.CouponType;
import com.jamigo.promotion.CouponType.Service.CouponTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class checkDate {
    @Autowired
    private MemberCouponService memberCouponService;
    @Autowired
    private CouponTypeService couponTypeService;

    @Scheduled(cron = "0 0 0 * * *") // 每天午夜执行一次
    public void checkExpiredCoupons() {
        System.out.println("有執行排成");
        List<MemberCoupon> allcoupon = memberCouponService.findAll();
        for (MemberCoupon coupons : allcoupon) {
            CouponType couponType4check = new CouponType();
            Integer TypeNo = coupons.getMemberCouponId().getCouponTypeNo();//取得種類編號
            couponType4check.setCouponTypeNo(TypeNo);
            CouponType shark = couponTypeService.findByCouponTypeNo(couponType4check);//以種類編號找出該種折價券
            Timestamp selected = shark.getCouponExpireDate();//找到會員折價券內的每個折價券 種類的失效日期
            Date currentDate = new Date();
            Timestamp currentTimestamp = new Timestamp(currentDate.getTime());//取得當前日期
            if (selected.before(currentTimestamp)) { // selected時間早於currentTimestamp，即已過期
                coupons.setCouponUsedStat((byte) 1);
                coupons.setCouponUsedTime(currentTimestamp);
                memberCouponService.edit(coupons);
            }
        }
    }


}
