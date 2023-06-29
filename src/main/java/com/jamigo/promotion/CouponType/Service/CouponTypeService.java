package com.jamigo.promotion.CouponType.Service;

import com.jamigo.promotion.CouponType.Entity.CouponType;
import com.jamigo.promotion.PromotionType.Entity.Promotion;

import java.util.List;

public interface CouponTypeService {
    CouponType  add(CouponType couponType);


    CouponType  edit(CouponType couponType);
    CouponType  findByCouponTypeNo(CouponType couponType);

    List<CouponType > findAll();

    boolean remove(Integer couponTypeNo);

}
