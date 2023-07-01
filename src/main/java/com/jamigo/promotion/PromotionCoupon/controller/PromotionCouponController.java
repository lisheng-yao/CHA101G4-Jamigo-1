package com.jamigo.promotion.PromotionCoupon.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jamigo.promotion.CouponType.Entity.CouponType;
import com.jamigo.promotion.CouponType.Service.CouponTypeService;
import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;
import com.jamigo.promotion.PromotionCoupon.Service.PromotionCouponService;
import com.jamigo.promotion.PromotionPoint.Entity.PromotionPoint;
import com.jamigo.promotion.PromotionType.Entity.Promotion;
import com.jamigo.promotion.PromotionType.Service.PromotionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
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

    @PostMapping("promotion/promotion/editPromotionCoupon")
    public PromotionCoupon editPromotion(@RequestBody String PromotionCouponRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {//這段是要把利用json傳進來但是 不是key：vlaue取出並刪除
            JsonNode jsonNode = objectMapper.readTree(PromotionCouponRequest);//先拿到jsonnode
            String promotionPic4json = jsonNode.get("promotionPic4json").asText();//拿到 想取出的欄位
            byte[] PromotionPic = null;
            if (promotionPic4json != null) { //轉回byte[]
                PromotionPic = Base64.getDecoder().decode(promotionPic4json);
            }

            ObjectNode objectNode = (ObjectNode) jsonNode;//轉型 才能刪除
            objectNode.remove("promotionPic4json");//刪除
            objectNode.put("promotionPic", PromotionPic);
            PromotionCoupon promotionCoupona = objectMapper.convertValue(objectNode, PromotionCoupon.class);//刪完後再包裝回去成PromotionPoint物件

            PromotionCoupon promotionCoupon = PromotionCouponService.edit(promotionCoupona);
            System.out.println("修改controller");
            return promotionCoupon;
        } catch (IOException e) {
            // 處理 JSON 解析錯誤
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("promotion/promotion4counter/editPromotionCoupon")
    public PromotionCoupon editPromotion2(@RequestBody String PromotionCouponRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {//這段是要把利用json傳進來但是 不是key：vlaue取出並刪除
            JsonNode jsonNode = objectMapper.readTree(PromotionCouponRequest);//先拿到jsonnode
            String promotionPic4json = jsonNode.get("promotionPic4json").asText();//拿到 想取出的欄位
            byte[] PromotionPic = null;
            if (promotionPic4json != null) { //轉回byte[]
                PromotionPic = Base64.getDecoder().decode(promotionPic4json);
            }

            ObjectNode objectNode = (ObjectNode) jsonNode;//轉型 才能刪除
            objectNode.remove("promotionPic4json");//刪除
            objectNode.put("promotionPic", PromotionPic);
            PromotionCoupon promotionCoupona = objectMapper.convertValue(objectNode, PromotionCoupon.class);//刪完後再包裝回去成PromotionPoint物件

            PromotionCoupon promotionCoupon = PromotionCouponService.edit(promotionCoupona);
            System.out.println("修改controller");
            return promotionCoupon;
        } catch (IOException e) {
            // 處理 JSON 解析錯誤
            e.printStackTrace();
            return null;
        }
    }

    ;

    @PostMapping("promotion/promotion/newPromotionCoupon")
    public PromotionCoupon newPromotion(@RequestBody String PromotionCouponRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {//這段是要把利用json傳進來但是 不是key：vlaue取出並刪除
            JsonNode jsonNode = objectMapper.readTree(PromotionCouponRequest);//先拿到jsonnode
            String promotionPic4json = jsonNode.get("promotionPic4json").asText();//拿到 想取出的欄位
            byte[] PromotionPic = null;
            if (promotionPic4json != null) { //轉回byte[]
                PromotionPic = Base64.getDecoder().decode(promotionPic4json);
            }

            ObjectNode objectNode = (ObjectNode) jsonNode;//轉型 才能刪除
            objectNode.remove("promotionPic4json");//刪除
            objectNode.put("promotionPic", PromotionPic);
            PromotionCoupon promotionCoupona = objectMapper.convertValue(objectNode, PromotionCoupon.class);//刪完後再包裝回去成PromotionPoint物件


            PromotionCoupon promotionCoupon = PromotionCouponService.add(promotionCoupona);
            System.out.println("新增controller");
            return promotionCoupon;
        } catch (IOException e) {
            // 處理 JSON 解析錯誤
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("promotion/promotion4counter/newPromotionCoupon")
    public PromotionCoupon newPromotion2(@RequestBody String PromotionCouponRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {//這段是要把利用json傳進來但是 不是key：vlaue取出並刪除
            JsonNode jsonNode = objectMapper.readTree(PromotionCouponRequest);//先拿到jsonnode
            String promotionPic4json = jsonNode.get("promotionPic4json").asText();//拿到 想取出的欄位
            byte[] PromotionPic = null;
            if (promotionPic4json != null) { //轉回byte[]
                PromotionPic = Base64.getDecoder().decode(promotionPic4json);
            }
            ObjectNode objectNode = (ObjectNode) jsonNode;//轉型 才能刪除
            objectNode.remove("promotionPic4json");//刪除
            objectNode.put("promotionPic", PromotionPic);
            PromotionCoupon promotionCoupona = objectMapper.convertValue(objectNode, PromotionCoupon.class);//刪完後再包裝回去成PromotionPoint物件

            PromotionCoupon promotionCoupon = PromotionCouponService.add(promotionCoupona);
            System.out.println("新增controller");
            return promotionCoupon;
        } catch (IOException e) {
            // 處理 JSON 解析錯誤
            e.printStackTrace();
            return null;
        }
    }

    ;

    @GetMapping("promotion/promotion/getAllPromotionCoupon")
    public List<PromotionCoupon> findAll() {
        return PromotionCouponService.findAll();
    }

    @GetMapping("promotion/promotion/getAllCouponType")
    public List<CouponType> findAllCouponType() {
        return CouponTypeSERVICE.findAll();
    }

    @PostMapping("promotion/promotion4counter/getAllCouponType")
    public List<CouponType> findAllCouponType2(@RequestBody CouponType couponTypeRequest) {
        Integer counterNoa = couponTypeRequest.getCounterNo();
        return CouponTypeSERVICE.findBycounterNo(counterNoa);
    }

    @GetMapping("promotion/promotion/getAllPromotion")
    public List<Promotion> findAllPromotion() {
        return promotionTypeService.findAll();
    }

    @PostMapping("promotion/promotion4counter/getAllPromotion")
    public List<Promotion> findAllPromotion2(@RequestBody CouponType couponTypeRequest) {
        Integer counterNoa = couponTypeRequest.getCounterNo();
        return promotionTypeService.findbycounterNo(counterNoa);
    }

    @PostMapping("promotion/promotion4counter/getcounterPromotionCoupon")
    public List<PromotionCoupon> findPromotionbycounterNo(@RequestBody CouponType couponTypeRequest) {
        System.out.println(couponTypeRequest);
        Integer counterNo = couponTypeRequest.getCounterNo();
        System.out.println("counterNo" + counterNo);
        List<PromotionCoupon> counterPromotion = PromotionCouponService.findbcounterNo(counterNo);
        return counterPromotion;
    }

    @PostMapping("promotion/promotion/deletePromotionCoupon")
    public Boolean deletePromotion(@RequestBody PromotionCoupon PromotionCouponRequest) {
        Integer promotionCouponNo = PromotionCouponRequest.getPromotionCouponNo();
        Boolean deletesucceed = PromotionCouponService.remove(promotionCouponNo);
        return deletesucceed;
    }

    @PostMapping("promotion/promotion4counter/deletePromotionCoupon")
    public Boolean deletePromotion2(@RequestBody PromotionCoupon PromotionCouponRequest) {
        Integer promotionCouponNo = PromotionCouponRequest.getPromotionCouponNo();
        Boolean deletesucceed = PromotionCouponService.remove(promotionCouponNo);
        return deletesucceed;
    }

    ;
}






