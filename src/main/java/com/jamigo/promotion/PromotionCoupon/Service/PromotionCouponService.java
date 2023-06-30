package com.jamigo.promotion.PromotionCoupon.Service;

import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PromotionCouponService {
    PromotionCoupon add(PromotionCoupon promotionCoupon);


    PromotionCoupon edit(PromotionCoupon promotionCoupon);

    List<PromotionCoupon> findAll();

    PromotionCoupon findByPK(Integer promotionCouponNo);
    List<PromotionCoupon> findbcounterNo(Integer counterNo);



    boolean remove(Integer promotionCouponNo);

}
