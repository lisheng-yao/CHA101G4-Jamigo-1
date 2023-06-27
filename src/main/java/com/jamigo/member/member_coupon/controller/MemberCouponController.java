package com.jamigo.member.member_coupon.controller;

import com.jamigo.member.member_coupon.Service.MemberCouponService;
import com.jamigo.member.member_coupon.entity.MemberCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberCouponController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private MemberCouponService memberCouponService;


//    @PostMapping ("")//修改 使用折價券
//    public MemberCoupon editMemberCoupon(@RequestBody MemberCoupon memberCouponRequest) {
//        MemberCoupon  memberCoupon= memberCouponService.edit(memberCouponRequest);
//        System.out.println("修改controller");
//        return memberCoupon;
//        }
//    @PostMapping ("")//新增 領取
//    public MemberCoupon newMemberCoupon(@RequestBody MemberCoupon memberCouponRequest) {
//        MemberCoupon  memberCoupon= memberCouponService.add(memberCouponRequest);
//        System.out.println("新增controller");
//        return memberCoupon;
//    }

    @PostMapping ("/member/member/getAllMemberCoupon")//查詢會員折價券
    public List<MemberCoupon> selectMemberCoupon(@RequestBody MemberCoupon memberCouponRequest) {
        List<MemberCoupon> memberCoupon= memberCouponService.selectByMember(memberCouponRequest);
        System.out.println("新增controller");
        return memberCoupon;
    }

}






