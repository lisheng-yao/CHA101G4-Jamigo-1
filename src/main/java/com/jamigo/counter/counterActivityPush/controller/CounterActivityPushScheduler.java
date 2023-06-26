//package com.jamigo.counter.counterActivityPush.controller;
//
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CounterActivityPushScheduler {
//	
//    private SimpMessagingTemplate messagingTemplate;
//
//    @Scheduled(fixedRate = 5000)
//    public void pushMessageToUser() {
//        // 从数据库或其他数据源获取要推送的消息和目标用户信息
//        String message = "Hello, user!";
//        String userId = "1";
//
//        // 推送消息给特定用户
//        messagingTemplate.convertAndSendToUser(userId, "/Jamigo/counter/counter_activityNewsPush.html", message);
//    }
//}
