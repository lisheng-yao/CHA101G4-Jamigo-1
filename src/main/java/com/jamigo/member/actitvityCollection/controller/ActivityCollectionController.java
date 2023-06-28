package com.jamigo.member.actitvityCollection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.member.actitvityCollection.entity.ActivityCollectionEntity;
import com.jamigo.member.actitvityCollection.entity.ActivityCollectionService;

@RestController
@RequestMapping("/activityCollection")
public class ActivityCollectionController {
	
	@Autowired
	ActivityCollectionService service;
	
	@GetMapping("/getAll")
	public List<ActivityCollectionEntity> getAll() {
		return service.getAll();
	}

	@GetMapping("/getByMemberNo/{memberNo}")
	public List<ActivityCollectionEntity> getByMemberNo(@PathVariable Integer memberNo) {
		return service.getByMemberNo(memberNo);
	}
	
}
