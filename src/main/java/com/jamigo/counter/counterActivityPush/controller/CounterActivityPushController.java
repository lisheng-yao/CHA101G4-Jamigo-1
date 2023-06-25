//package com.jamigo.counter.counterActivityPush.controller;
//
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.jamigo.counter.activityOrder.entity.ActivityOrderService;
//import com.jamigo.counter.activityOrder.entity.ActivityOrderVO;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//
//@Component
//public class CounterActivityPushController {
//	
//	@Autowired
//	ActivityOrderService service;
//	
//    private final SimpMessagingTemplate messagingTemplate;
//
//    public CounterActivityPushController(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @Scheduled(fixedRate = 0)
//    public void sendEventReminder() {
//    	
//        String eventDate = "10/10";
//        
//        LocalDateTime currDateTime = LocalDateTime.now();
//		LocalDateTime targetDateTime = LocalDateTime.of(2023, Month.JUNE, 4, 36, 0);
//		
//		if(currDateTime.equals(targetDateTime)) {
//			List<ActivityOrderVO> attendees = getEventAttendees();
//	        
//	        for (ActivityOrderVO attendee : attendees) {
//	            String message = "活動提醒：您參加的活動將在明天" + eventDate + "舉行，請準時參加";
//	            messagingTemplate.convertAndSendToUser(attendee.getMemberData().getMemberName(), 
//	            									   "/topic/event-reminders", 
//	            									   message);
//	        }
//		}
//		
//    }
//
//    private List<ActivityOrderVO> getEventAttendees() {
//        return service.getAll();
//    }
//}
