package com.jamigo.promotion.PromotionCoupon.controller;

import com.jamigo.promotion.CouponType.Entity.CouponType;
import com.jamigo.promotion.CouponType.Service.CouponTypeService;
import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;
import com.jamigo.promotion.PromotionCoupon.Service.PromotionCouponService;
import com.jamigo.promotion.PromotionType.Entity.Promotion;
import com.jamigo.promotion.PromotionType.Service.PromotionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PromotionCouponController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private CouponTypeService CouponTypeSERVICE;

    @Autowired
    private PromotionCouponService PromotionCouponService;

    @Autowired
    private PromotionTypeService promotionTypeService;
    @PostMapping ("promotion/promotion/editPromotionCoupon")
    public PromotionCoupon editPromotion(@RequestBody PromotionCoupon PromotionCouponRequest) {
        PromotionCoupon  promotionCoupon= PromotionCouponService.edit(PromotionCouponRequest);
        System.out.println("修改controller");
        return promotionCoupon;
        };
    @PostMapping ("promotion/promotion/newPromotionCoupon")
    public PromotionCoupon newPromotion(@RequestBody PromotionCoupon PromotionCouponRequest) {
        PromotionCoupon  promotionCoupon= PromotionCouponService.add(PromotionCouponRequest);
        System.out.println("新增controller");
        return promotionCoupon;
    };

    @GetMapping("promotion/promotion/getAllPromotionCoupon")
    public List<PromotionCoupon> findAll() {
        return PromotionCouponService.findAll();
    }

    @GetMapping("promotion/promotion/getAllCouponType")
    public List<CouponType> findAllCouponType() {
        return CouponTypeSERVICE.findAll();
    }

    @GetMapping("promotion/promotion/getAllPromotion")
    public List<Promotion> findAllPromotion() {
        return promotionTypeService.findAll();
    }

    @PostMapping ("promotion/promotion/deletePromotionCoupon")
    public  Boolean deletePromotion(@RequestBody PromotionCoupon PromotionCouponRequest) {
        Integer promotionCouponNo = PromotionCouponRequest.getPromotionCouponNo();
        Boolean deletesucceed= PromotionCouponService.remove(promotionCouponNo);
        return deletesucceed;
    };
}






