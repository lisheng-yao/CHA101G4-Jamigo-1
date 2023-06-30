package com.jamigo.promotion.PromotionCoupon.Dao;

import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromotionCouponDao extends JpaRepository<PromotionCoupon, Integer> {
    @Query(value = "SELECT couponTypeNo FROM Coupon_Type WHERE counterNo = :counterNo", nativeQuery = true)
    List<Integer> findCouponTypeNosByCounterNo(Integer counterNo);

    List<PromotionCoupon> findByCouponTypeNoIn(List<Integer> couponTypeNos);



}
