//package com.jamigo.counter.counterActivityPush.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.jamigo.counter.counterActivityPush.entity.CounterActivityPushEntity;
//import com.jamigo.counter.counterActivityPush.entity.CounterActivityPushService;
//
//@RequestMapping("/counterActivityPushRequest")
//@RestController
//public class CounterActivityPushController {
//
//	@Autowired
//	private CounterActivityPushService service;
//	
//	@GetMapping("{userNo}")
//	public List<CounterActivityPushEntity> getByMemberNo(@PathVariable Integer userNo) {
//		return service.getByMemberNo(userNo);
//	}
//}
