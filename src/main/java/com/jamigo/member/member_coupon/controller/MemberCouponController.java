package com.jamigo.member.member_coupon.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jamigo.member.member_coupon.Service.MemberCouponService;
import com.jamigo.member.member_coupon.Util.checkDate;
import com.jamigo.member.member_coupon.entity.MemberCoupon;
import com.jamigo.member.member_coupon.entity.MemberCouponId;
import com.jamigo.member.member_data.core.Core;
import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;
import com.jamigo.promotion.PromotionCoupon.Service.PromotionCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class MemberCouponController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private MemberCouponService memberCouponService;
    @Autowired
    private PromotionCouponService promotionCouponService;
    @Autowired
    private checkDate checkDate;


    //    @PostMapping ("")//修改 使用折價券
//    public MemberCoupon editMemberCoupon(@RequestBody MemberCoupon memberCouponRequest) {
//        MemberCoupon  memberCoupon= memberCouponService.edit(memberCouponRequest);
//        System.out.println("修改controller");
//        return memberCoupon;
//        }
    @PostMapping("/promotion/promotion_list/newMemberCoupon")//新增 領取
    public MemberCoupon newMemberCoupon(@RequestBody String memberCouponRequest) {
        System.out.println(memberCouponRequest + "memberCouponRequest");
        ObjectMapper objectMapper = new ObjectMapper();
        try {//這段是要把利用json傳進來
            JsonNode jsonNode = objectMapper.readTree(memberCouponRequest);//先拿到jsonnode

            Integer promotionCouponNo = jsonNode.get("promotionCouponNo").asInt();//拿到 想取出的欄位
            Integer memberNo = jsonNode.get("memberNo").asInt();//拿到 想取出的欄位
            Integer couponTypeNo = promotionCouponService.findByPK(promotionCouponNo).getCouponTypeNo();//  查到這個折價券活動使用的折價券編號
            Integer memberCouponNo = memberCouponService.findbycouponTypeNo(couponTypeNo);//查到該折價券最後一個編號+1

            MemberCouponId memberCouponId = new MemberCouponId();//新增複合主鍵
            memberCouponId.setCouponTypeNo(couponTypeNo);
            memberCouponId.setMemberCouponNo(memberCouponNo);

            MemberCoupon memberCouponRequesta = new MemberCoupon();//       創建新 MemberCoupon
            memberCouponRequesta.setMemberCouponId(memberCouponId);
            memberCouponRequesta.setMemberNo(memberNo);

            MemberCoupon memberCoupon = memberCouponService.add(memberCouponRequesta);
            System.out.println(memberCoupon.isSuccessful());
            if(memberCoupon.isSuccessful()){
                Integer GetAmounts = promotionCouponService.findByPK(promotionCouponNo).getGetAmount() + 1;

                PromotionCoupon promotionCouponRequesta = new PromotionCoupon();
                promotionCouponRequesta.setPromotionCouponNo(promotionCouponNo);
                promotionCouponRequesta.setGetAmount(GetAmounts);
                promotionCouponService.edit(promotionCouponRequesta);
            }
            return memberCoupon;
        } catch (IOException e) {
            // 處理 JSON 解析錯誤
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/member/member/getAllMemberCoupon")//查詢會員折價券
    public List<MemberCoupon> selectMemberCoupon(@RequestBody MemberCoupon memberCouponRequest) {
        List<MemberCoupon> memberCoupon = memberCouponService.selectByMember(memberCouponRequest);
        checkDate.checkExpiredCoupons();
        System.out.println("新增controller");
        return memberCoupon;
    }

}






