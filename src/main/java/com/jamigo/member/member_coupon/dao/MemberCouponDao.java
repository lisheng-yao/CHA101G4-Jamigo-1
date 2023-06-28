package com.jamigo.member.member_coupon.dao;

import com.jamigo.member.member_coupon.entity.MemberCoupon;
import com.jamigo.member.member_coupon.entity.MemberCouponId;
import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface MemberCouponDao extends JpaRepository<MemberCoupon, MemberCouponId> {
    List<MemberCoupon> findByMemberNo(Integer memberNo);

}
