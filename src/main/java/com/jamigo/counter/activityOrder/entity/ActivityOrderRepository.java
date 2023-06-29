package com.jamigo.counter.activityOrder.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ActivityOrderRepository extends JpaRepository<ActivityOrderVO, Integer> {
	
	@Transactional
	List<ActivityOrderVO> findByMemberNo(Integer memberNo);
}
