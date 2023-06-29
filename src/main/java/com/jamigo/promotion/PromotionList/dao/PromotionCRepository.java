package com.jamigo.promotion.PromotionList.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jamigo.promotion.PromotionList.entity.PromotionCoupon34;



public interface PromotionCRepository extends JpaRepository <PromotionCoupon34 , Integer>{
	
	 @Query("SELECT pc.promotionCouponNo, pc.promotionCouponName, pc.promotionName FROM PromotionCoupon34 pc")
	    List<Object[]> getPromotionCouponData();

}
