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
@Table(name = "promotion_point")
@ToString
public class PromotionPoint34 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer promotionPointNo;
	private String promotionPointName;
	private String promotionName;
	private String getPointConditions;
	private Integer getPointMag;
	private Date promotionExpireDate;
	private Date promotionEffectiveDate;
	private Date promotionCreateDate;
	private byte[] promotionPic;

}
