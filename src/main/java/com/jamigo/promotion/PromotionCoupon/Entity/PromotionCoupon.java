package com.jamigo.promotion.PromotionCoupon.Entity;

import com.jamigo.member.member_data.core.Core;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@ToString
@Table(name = "promotion_coupon")
public class PromotionCoupon extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotionCouponNo", nullable = false, insertable = false, updatable = false)
    private Integer promotionCouponNo;

    @Size(max = 100)
    @Column(name = "promotionCouponName", nullable = false, length = 100)
    private String promotionCouponName;

    @Size(max = 10)
    @Column(name = "promotionName", nullable = false, length = 10)
    private String promotionName;

    @Column(name = "couponTypeNo", nullable = false)
    private Integer couponTypeNo;

    @Column(name = "amountOfCoupon", nullable = false)
    private Integer amountOfCoupon;


    @Column(name = "getCouponLimitLevel")
    private Byte getCouponLimitLevel;

    @Column(name = "getCouponLimitAmount")
    private Integer getCouponLimitAmount;

    @Column(name = "promotionEffectiveDate", nullable = false)
    private Timestamp promotionEffectiveDate;

    @Column(name = "promotionExpireDate", nullable = false)
    private Timestamp promotionExpireDate;

    @Column(name = "promotionCreateDate", nullable = false, insertable = false, updatable = false)
    private Timestamp promotionCreateDate;

    @Builder.Default
    @Column(name = "getAmount", nullable = false)
    private Integer getAmount=0;

    @Column(name = "promotionPic")
    private byte[] promotionPic;


}