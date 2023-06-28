package com.jamigo.member.member_coupon.entity;

import com.jamigo.member.member_data.core.Core;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "member_coupon")
public class MemberCoupon extends Core {
    @EmbeddedId
    private MemberCouponId  MemberCouponId;

    @NotNull
    @Column(name = "memberNo", nullable = false)
    private Integer memberNo;
    @Builder.Default
    @Column(name = "couponUsedStat")
    private Byte couponUsedStat=0;

    @Column(name = "couponUsedTime")
    private Timestamp couponUsedTime;

    @Column(name = "orderDetailCouponNo")
    private Integer orderDetailCouponNo;


}