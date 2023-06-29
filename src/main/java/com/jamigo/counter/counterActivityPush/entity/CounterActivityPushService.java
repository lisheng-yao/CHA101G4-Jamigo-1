package com.jamigo.counter.counterActivityPush.entity;

import java.util.List;

import org.springframework.stereotype.Service;

public interface CounterActivityPushService {
	
	public List<CounterActivityPushEntity> getByMemberNo(Integer memberNo);
}
