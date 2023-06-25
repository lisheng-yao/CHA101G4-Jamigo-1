package com.jamigo.counter.activityOrder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.activity.attendee.entity.ActivityAttendeeService;
import com.jamigo.activity.attendee.entity.ActivityAttendeeVO;
import com.jamigo.counter.activityOrder.entity.ActivityOrderDTO;
import com.jamigo.counter.activityOrder.entity.ActivityOrderService;
import com.jamigo.counter.activityOrder.entity.ActivityOrderVO;

@RestController
@RequestMapping("/activityOrder")
public class ActivityOrderController {
	
	@Autowired
	ActivityOrderService activityOrderService;
	
	@Autowired
	ActivityAttendeeService activityAttendeeService;
	
	@GetMapping("/insert")
	public void insert(@RequestBody ActivityOrderVO activityOrderVO){
		
		activityOrderService.add(activityOrderVO);
		
	}
	@PutMapping("/updatePart")
	public void updatePart(@RequestBody ActivityOrderDTO activityOrderDTO){
		// 先查找DB物件原始的值，將從前端更新的值(Temp)賦值到新的物件上(VO)
		ActivityOrderVO activityOrderVOTemp = activityOrderDTO.getActivityOrderVO();
		ActivityOrderVO activityOrderVO = activityOrderService.getById(activityOrderVOTemp.getActivityOrderNo());
		
		activityOrderVO.setActivityPaymentStat(activityOrderVOTemp.getActivityPaymentStat());
		if(activityOrderVOTemp.getNumberOfAttendee() != null)
			activityOrderVO.setNumberOfAttendee(activityOrderVOTemp.getNumberOfAttendee());
		// 再把已更新部分的新物件做update
		activityOrderService.update(activityOrderVO);
		
		List<ActivityAttendeeVO> activityAttendeeVOList = activityOrderDTO.getActivityAttendeeVOList();
		for (ActivityAttendeeVO activityAttendeeVO : activityAttendeeVOList) {
			if(activityAttendeeVO.getAttendeeNo() != null)
				activityAttendeeService.update(activityAttendeeVO);
			else {
				ActivityAttendeeVO activityAttendeeVO2 = new ActivityAttendeeVO();
				activityAttendeeVO2.setActivityOrderNo(activityAttendeeVO.getActivityOrderNo());
				activityAttendeeVO2.setAttendeeName(activityAttendeeVO.getAttendeeName());
				activityAttendeeVO2.setAttendeeGender(activityAttendeeVO.getAttendeeGender());
				activityAttendeeVO2.setAttendeeAge(activityAttendeeVO.getAttendeeAge());
				activityAttendeeService.update(activityAttendeeVO2);
			}
			
		}
		
	}
	
	@GetMapping("/getActivityOrderById/{activityOrderNo}")
	public ActivityOrderVO getActivityOrderById(@PathVariable Integer activityOrderNo){
		
		return activityOrderService.getById(activityOrderNo);
		
	}
	
	@GetMapping("/getAll")
	public List<ActivityOrderVO> getAll(){
		
		return activityOrderService.getAll();
		
	}
}
