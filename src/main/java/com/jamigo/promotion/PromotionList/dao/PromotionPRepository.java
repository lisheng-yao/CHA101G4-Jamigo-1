package com.jamigo.promotion.PromotionList.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jamigo.promotion.PromotionList.entity.PromotionPoint34;



public interface PromotionPRepository extends JpaRepository<PromotionPoint34 , Integer>{
	
	@Query("SELECT pp.promotionPointNo, pp.promotionPointName, pp.promotionName FROM PromotionPoint34 pp")
	List<Object[]> getPromotionPointData();
}
