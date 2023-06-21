package com.jamigo.promotion.CouponType.Entity;

import com.jamigo.member.member_data.core.Core;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "coupon_type")
public class CouponType extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couponTypeNo", nullable = false)
    private Integer couponTypeNo;

    @Column(name = "couponTypeName", nullable = false, length = 20)
    private String couponTypeName;

    @Column(name = "adminNo")
    private Integer adminNo;

    @Column(name = "counterNo")
    private Integer counterNo;

    @Column(name = "couponCreateDate", nullable = false, insertable = false, updatable = false)
    private Timestamp couponCreateDate;

    @Column(name = "couponConditions", nullable = false, length = 200)
    private String couponConditions;

    @Column(name = "couponPrice", nullable = false)
    private Integer couponPrice;

    @Column(name = "couponExpireDate", nullable = false)
    private Timestamp couponExpireDate;

    @Column(name = "couponLowest", nullable = false)
    private Integer couponLowest;

}