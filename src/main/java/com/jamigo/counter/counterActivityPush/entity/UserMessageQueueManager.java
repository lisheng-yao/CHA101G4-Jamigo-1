//package com.jamigo.counter.counterActivityPush.entity;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.LinkedBlockingQueue;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserMessageQueueManager {
//    private Map<String, LinkedBlockingQueue<String>> userMessageQueues;
//
//    public UserMessageQueueManager() {
//        userMessageQueues = new ConcurrentHashMap<>();
//    }
//
//    public void addUserMessageQueue(String userId) {
//        if (!userMessageQueues.containsKey(userId)) {
//            userMessageQueues.put(userId, new LinkedBlockingQueue<>());
//        }
//    }
//
//    public void removeUserMessageQueue(String userId) {
//        userMessageQueues.remove(userId);
//    }
//
//    public void addMessageToUserQueue(String userId, String message) {
//        LinkedBlockingQueue<String> userQueue = userMessageQueues.get(userId);
//        if (userQueue != null) {
//            userQueue.offer(message);
//        }
//    }
//
//    public LinkedBlockingQueue<String> getUserMessageQueue(String userId) {
//        return userMessageQueues.get(userId);
//    }
//}
