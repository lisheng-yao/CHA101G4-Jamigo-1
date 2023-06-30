package com.jamigo.promotion.PromotionList.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name= "promotion_coupon")
@ToString
public class PromotionCoupon34 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer promotionCouponNo;
	private String promotionCouponName;
	private String promotionName;
	private Integer couponTypeNo;
	private Integer amountOfCoupon;
	private Integer getCouponLimitLevel;
	private Integer getCouponLimitAmount;
	private Date promotionEffectiveDate;
	private Date promotionExpireDate;
	private Date  promotionCreateDate;
	private Integer getAmount;
	private byte[] promotionPic;
	
	
}
