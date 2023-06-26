//package com.jamigo.counter.counterActivityPush.controller;
//
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import com.jamigo.counter.counterActivityPush.entity.UserMessageQueueManager;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.LinkedBlockingQueue;
//
//public class CounterActivityHandler extends TextWebSocketHandler {
//
//	private Map<String, LinkedBlockingQueue<String>> userMessageQueues;
//	
//	@Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        String userId = getUserIdFromSession(session);
//
//        LinkedBlockingQueue<String> userQueue = userMessageQueues.get(userId);
//        if (userQueue == null) {
//            userQueue = new LinkedBlockingQueue<>();
//            userMessageQueues.put(userId, userQueue);
//        }
//
//        sendQueueToUser(session, userQueue);
//    }
//	private String getUserIdFromSession(WebSocketSession session) {
//        // 从 WebSocketSession 中获取用户ID的逻辑，根据实际情况进行实现
//        // 假设用户ID存储在 attributes 的 "userId" 键中
//        return session.getAttributes().get("userId").toString();
//    }
//
//    private void sendQueueToUser(WebSocketSession session, LinkedBlockingQueue<String> userQueue) throws Exception {
//        for (String message : userQueue) {
//            sendMessageToUser(session, message);
//        }
//    }
//
//    private void sendMessageToUser(WebSocketSession session, String message) throws Exception {
//        if (session.isOpen()) {
//            session.sendMessage(new TextMessage(message));
//        }
//    }
//
//}
