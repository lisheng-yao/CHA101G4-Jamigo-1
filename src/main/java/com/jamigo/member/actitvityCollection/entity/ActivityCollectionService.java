package com.jamigo.member.actitvityCollection.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityCollectionService {
	
	@Autowired
	ActivityCollectionRepository reopsitory;
	
	public List<ActivityCollectionEntity> getAll() {
		return reopsitory.findAll();
	}

	public List<ActivityCollectionEntity> getByMemberNo(Integer memberNo) {
		return reopsitory.findByMemberNo(memberNo);
	}
	
	public void add(ActivityCollectionEntity entity) {
		reopsitory.save(entity);
	}

	public void delete(ActivityCollectionEntity entity) {
		reopsitory.delete(entity);
	}
	
	public ActivityCollectionEntity isActivityAdd(Integer activityNo, Integer memberNo) {
		return reopsitory.findByActivityCollectionEntity(activityNo, memberNo);
	}
	
}
