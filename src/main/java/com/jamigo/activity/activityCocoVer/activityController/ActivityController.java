package com.jamigo.activity.activityCocoVer.activityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.activity.activityCocoVer.activityEntity.ActivityEntity;
import com.jamigo.activity.activityCocoVer.activityEntity.ActivityService;

@RestController
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired
	ActivityService service;
	
	@GetMapping("/getById/{activityNo}")
	public ActivityEntity getById(@PathVariable Integer activityNo) {
		return service.getById(activityNo);
	}
}
