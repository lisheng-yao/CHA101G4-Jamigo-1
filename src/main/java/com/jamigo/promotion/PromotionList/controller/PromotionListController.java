package com.jamigo.promotion.PromotionList.controller;

import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.promotion.PromotionList.dao.CouponType34Repository;
import com.jamigo.promotion.PromotionList.dao.PromotionCRepository;
import com.jamigo.promotion.PromotionList.dao.PromotionPRepository;
import com.jamigo.promotion.PromotionList.entity.CouponType34;
import com.jamigo.promotion.PromotionList.entity.PromotionCoupon34;
import com.jamigo.promotion.PromotionList.entity.PromotionPoint34;



@RestController
public class PromotionListController {

	@Autowired
	public PromotionCRepository promotionCRepository;

	@Autowired
	public PromotionPRepository promotionPRepository;
	
	@Autowired
	public CouponType34Repository couponType34Repository;

//	===================== 列出點數、折價券優惠===================

	@GetMapping("/promotions")
	public List<Object[]> getAllData() {
		List<Object[]> promotionPointData = promotionPRepository.getPromotionPointData();
		List<Object[]> promotionCouponData = promotionCRepository.getPromotionCouponData();

		// 合併兩個list
		List<Object[]> result = new ArrayList<>();
		result.addAll(promotionPointData);
		result.addAll(promotionCouponData);

		return result;
	}

//	================== 折價券活動說明專頁 ========================

	@GetMapping("/coupons/{promotionCouponNo}")
	public PromotionCoupon34 getCouponData(@PathVariable Integer promotionCouponNo) {

		return promotionCRepository.findById(promotionCouponNo).orElse(null);

	}
	
	@GetMapping("/coupontype/{couponTypeNo}")
	public CouponType34 getCouponType(@PathVariable Integer couponTypeNo) {
		
		return couponType34Repository.findById(couponTypeNo).orElse(null);
	}

//	================== 點數活動說明專頁 =========================

	@GetMapping("/promotion/point/{promotionPointNo}")
	public PromotionPoint34 getPointData(@PathVariable Integer promotionPointNo) {

		PromotionPoint34 promotionPoint = promotionPRepository.findById(promotionPointNo).orElse(null);

		return promotionPoint;
	}

//	================== 點數活動說明專頁 ========================
	@GetMapping("/points/{promotionPointNo}")
	public PromotionPoint34 getAllPoints(@PathVariable Integer promotionPointNo) {
		
		return promotionPRepository.findById(promotionPointNo).orElse(null);
	}

}
