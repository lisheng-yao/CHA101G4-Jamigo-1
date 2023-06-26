//package com.jamigo.counter.counterActivityPush.controller;
//package com.jamigo.counter.counterActivityPush.controller;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//
//@Component
//public class ActivityWebsocket {
//
//    private final SimpMessagingTemplate messagingTemplate;
//
//    public ActivityWebsocket(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @Scheduled(cron = "0 0 9 9 10 ?") // 在10/9上午9点触发任务
//    public void sendEventReminder() {
//        String eventDate = "10/10";
//
//        List<String> attendees = getEventAttendees();
//
//        for (String attendee : attendees) {
//            String message = "活动提醒：您参加的活动将在明天（" + eventDate + "）举行，请准时参加！";
//            messagingTemplate.convertAndSendToUser(attendee, "/topic/event-reminders", message);
//        }
//    }
//
//    private List<String> getEventAttendees() {
//        List<String> attendees = new ArrayList<>();
//        attendees.add("user1");
//        attendees.add("user2");
//        attendees.add("user3");
//        return attendees;
//    }
//}
//
//
//
