package com.jamigo.activity.activityCocoVer.activityEntity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
	
	@Autowired
	ActivityRepository repository;
	
	public ActivityEntity getById(Integer activityNo) {
		Optional<ActivityEntity> optional = repository.findById(activityNo);
		return optional.get();
	}
}
