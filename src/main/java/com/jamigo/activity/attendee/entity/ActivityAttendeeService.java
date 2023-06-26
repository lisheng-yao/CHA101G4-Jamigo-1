package com.jamigo.activity.attendee.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityAttendeeService {
	
	@Autowired
	ActivityAttendeeRepository repository;
	
	public void add(ActivityAttendeeVO activityAttendeeVO) {
		repository.save(activityAttendeeVO);
	}

	public void update(ActivityAttendeeVO activityAttendeeVO) {
		repository.save(activityAttendeeVO);
	}
	
}
