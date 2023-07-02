package com.jamigo.counter.activityOrder.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamigo.shop.platform_order.entity.PlatformOrder;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;


@Service
public class ActivityOrderService {
	
	@Autowired
	ActivityOrderRepository repository;
	
	public Integer add(ActivityOrderVO activityOrderVO) {
		ActivityOrderVO saveVO = repository.save(activityOrderVO);
		return saveVO.getActivityOrderNo();
	}
	
	public void update(ActivityOrderVO activityOrderVO) {
		repository.save(activityOrderVO);
	}
	
	public ActivityOrderVO getById(Integer activityOrderNo) {
		Optional<ActivityOrderVO> optional = repository.findById(activityOrderNo);
		return optional.get();
	}
	
	public List<ActivityOrderVO> getByMemberNo(Integer memberNo) {
		return repository.findByMemberNo(memberNo);
	}
	
	public List<ActivityOrderVO> getAll() {
		return repository.findAll();
	}
	
	public void changePaidStat(ActivityOrderVO activityOrderVO) {
		
		System.out.println("執行更新setActivityPaymentStat");
		
		activityOrderVO.setActivityPaymentStat(Byte.valueOf((byte) 1));
		
		System.out.println("PaymentStat" + activityOrderVO.getActivityPaymentStat());
    }
}
