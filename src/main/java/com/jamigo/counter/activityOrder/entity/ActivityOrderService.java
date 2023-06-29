package com.jamigo.counter.activityOrder.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
	
}
