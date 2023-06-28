package com.jamigo.promotion.PromotionPoint.Dao;

import com.jamigo.promotion.PromotionCoupon.Entity.PromotionCoupon;
import com.jamigo.promotion.PromotionPoint.Entity.PromotionPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionPointDao extends JpaRepository<PromotionPoint, Integer> {

}
