package com.jamigo.activity.activityCocoVer.activityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.activity.activityCocoVer.activityEntity.ActivityEntityCoco;
import com.jamigo.activity.activityCocoVer.activityEntity.ActivityServiceCoco;

@RestController
@RequestMapping("/activity")
public class ActivityControllerCoco {
	
	@Autowired
	ActivityServiceCoco service;
	
	@GetMapping("/getById/{activityNo}")
	public ActivityEntityCoco getById(@PathVariable Integer activityNo) {
		return service.getById(activityNo);
	}
}
