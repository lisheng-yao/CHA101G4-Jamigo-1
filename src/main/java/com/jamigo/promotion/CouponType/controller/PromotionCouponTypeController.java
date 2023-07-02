package com.jamigo.promotion.CouponType.controller;

import com.jamigo.promotion.CouponType.Entity.CouponType;
import com.jamigo.promotion.CouponType.Service.CouponTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PromotionCouponTypeController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private CouponTypeService SERVICE;
    @PostMapping ("promotion/promotion/editcouponType")
    public  CouponType  editPromotion(@RequestBody CouponType couponTypeRequest) {
        CouponType  couponType= SERVICE.edit(couponTypeRequest);
        System.out.println("修改controller");
        return couponType;
        };
    @PostMapping ("promotion/promotion4counter/editcouponType")
    public  CouponType  editPromotiona(@RequestBody CouponType couponTypeRequest) {
        CouponType  couponType= SERVICE.edit(couponTypeRequest);
        System.out.println("修改controller");
        return couponType;
    };
    @PostMapping ("promotion/promotion4counter/newcouponType")
    public CouponType newPromotion(@RequestBody CouponType couponTypeRequest) {
        CouponType  couponType= SERVICE.add(couponTypeRequest);
        System.out.println("新增controller");
        return couponType;
    };
    @PostMapping ("promotion/promotion/newcouponType")
    public CouponType newPromotiona(@RequestBody CouponType couponTypeRequest) {
        CouponType  couponType= SERVICE.add(couponTypeRequest);
        System.out.println("新增controller");
        return couponType;
    };

    @PostMapping ("member/member/getcoupontype")
    public CouponType selectbyid(@RequestBody CouponType couponTypeRequest) {
        CouponType  couponType= SERVICE.findByCouponTypeNo(couponTypeRequest);
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

    @PostMapping ("promotion/promotion4counter/deletecouponType")
    public  Boolean deletePromotiona(@RequestBody CouponType couponTypeRequest) {
        Integer CouponTypeNo = couponTypeRequest.getCouponTypeNo();
        Boolean deletesucceed= SERVICE.remove(CouponTypeNo);
        return deletesucceed;
    };
    @PostMapping ("promotion/promotion4counter/getCounterCouponType")
    public   List<CouponType> getcounterCouponType(@RequestBody CouponType couponTypeRequest) {
        Integer counterNo = couponTypeRequest.getCounterNo();
        List<CouponType> couponType = SERVICE.findBycounterNo(counterNo);
        return couponType;
    };
}






