package com.jamigo.member.member_coupon.Service;

import com.jamigo.member.member_coupon.entity.MemberCoupon;
import com.jamigo.member.member_coupon.entity.MemberCouponId;

import java.util.List;

public interface MemberCouponService {

    MemberCoupon add(MemberCoupon memberCoupon);


    MemberCoupon edit(MemberCoupon memberCoupon);

    List<MemberCoupon> findAll();

    List<MemberCoupon> selectByMember(MemberCoupon memberCoupon);

    Integer findbycouponTypeNo(Integer couponTypeNo);


}