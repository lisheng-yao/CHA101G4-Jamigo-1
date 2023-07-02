package com.jamigo.promotion.PromotionPoint.Service;

import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;
import com.jamigo.promotion.PromotionPoint.Entity.PromotionPoint;

import java.util.List;

public interface PromotionPointService {
    PromotionPoint add(PromotionPoint  promotionPoint);


    PromotionPoint   edit(PromotionPoint  promotionPoint);

    List<PromotionPoint> findAll();


    PromotionPoint   findbypk(Integer promotionPointNo);
    boolean remove(Integer promotionPointNo);

}
