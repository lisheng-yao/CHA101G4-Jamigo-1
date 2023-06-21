package com.jamigo.promotion.CouponType.controller;

import com.jamigo.promotion.CouponType.Entity.CouponType;
import com.jamigo.promotion.CouponType.Service.CouponTypeService;
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
    private CouponTypeService SERVICE;
    @PostMapping ("promotion/promotion/editcouponType")
    public  CouponType  editPromotion(@RequestBody CouponType couponTypeRequest) {
        CouponType  couponType= SERVICE.edit(couponTypeRequest);
        System.out.println("修改controller");
        return couponType;
        };
    @PostMapping ("promotion/promotion/newcouponType")
    public CouponType newPromotion(@RequestBody CouponType couponTypeRequest) {
        CouponType  couponType= SERVICE.add(couponTypeRequest);
        System.out.println("新增controller");
        return couponType;
    };

    @GetMapping("Promotion/GetAllcouponType")
    public List<CouponType> findAll() {
        return SERVICE.findAll();
    }

    @PostMapping ("promotion/promotion/deletecouponType")
    public  Boolean deletePromotion(@RequestBody CouponType couponTypeRequest) {
        Integer CouponTypeNo = couponTypeRequest.getCouponTypeNo();
        Boolean deletesucceed= SERVICE.remove(CouponTypeNo);
        return deletesucceed;
    };
}






