package com.jamigo.promotion.PromotionCoupon.Service;

import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;

import java.util.List;

public interface PromotionCouponService {
    PromotionCoupon add(PromotionCoupon promotionCoupon);


    PromotionCoupon  edit(PromotionCoupon promotionCoupon);

    List<PromotionCoupon > findAll();

    boolean remove(Integer promotionCouponNo);

}
