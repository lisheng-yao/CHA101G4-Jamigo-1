package com.jamigo.counter.counterActivityPush.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterActivityPushRepository extends JpaRepository<CounterActivityPushEntity, Integer> {
	
	List<CounterActivityPushEntity> findByMemberNo(Integer memberNo);
	
}
