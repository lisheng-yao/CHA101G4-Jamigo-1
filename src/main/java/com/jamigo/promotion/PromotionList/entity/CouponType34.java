package com.jamigo.promotion.PromotionList.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coupon_type")
public class CouponType34 {
	
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
