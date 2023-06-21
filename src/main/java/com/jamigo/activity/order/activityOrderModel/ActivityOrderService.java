package com.jamigo.activity.order.activityOrderModel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActivityOrderService {
	
	@Autowired
	ActivityOrderRepository repository;
	
	public void add(ActivityOrderVO activityOrderVO) {
		repository.save(activityOrderVO);
	}
	
	public void update(ActivityOrderVO activityOrderVO) {
		repository.save(activityOrderVO);
	}
	
	public ActivityOrderVO getById(Integer activityOrderNo) {
		Optional<ActivityOrderVO> optional = repository.findById(activityOrderNo);
		return optional.get();
	}
	
	public List<ActivityOrderVO> getAll() {
		return repository.findAll();
	}
	
}
