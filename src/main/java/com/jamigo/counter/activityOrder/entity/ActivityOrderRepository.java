package com.jamigo.counter.activityOrder.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ActivityOrderRepository extends JpaRepository<ActivityOrderVO, Integer> {
	
	@Transactional
	List<ActivityOrderVO> findByMemberNo(Integer memberNo);
	
	@Transactional
	@Query(value = "select * from activity_order ao join Activity a on ao.activityNo = a.activityNo where counterNo = :counterNo LIMIT 0, 10", nativeQuery = true)
	List<ActivityOrderVO> getByCounterNo(@Param("counterNo") Integer counterNo);
}
