package com.jamigo.activity.attendee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.activity.attendee.entity.ActivityAttendeeService;
import com.jamigo.activity.attendee.entity.ActivityAttendeeVO;

@RestController
@RequestMapping("/activityAttendee")
public class ActivityAttendeeController {
	
	@Autowired
	ActivityAttendeeService service;
	
	@PostMapping("/insert")
	public void insert(@RequestBody List<ActivityAttendeeVO> activityAttendeeVOArr) {
		for(ActivityAttendeeVO vo : activityAttendeeVOArr)
			service.add(vo);
	}
}
