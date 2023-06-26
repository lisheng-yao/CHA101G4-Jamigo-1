//package com.jamigo.counter.counterActivityPush.controller;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import com.google.gson.Gson;
//import com.jamigo.counter.counterActivityPush.entity.CounterActivityPushService;
//
//@Component
//public class CounterActivityPushHandler extends TextWebSocketHandler  {
//	
//	private static CounterActivityPushService service;
//	
//	@Autowired
//	public void setService(CounterActivityPushService service) {
//		CounterActivityPushHandler.service = service;
//	}
//	
//	private static Gson gson;
//	
//	@Autowired
//	public void setGson(Gson gson) {
//		CounterActivityPushHandler.gson = gson;
//	}
//
//	private static Map<String, WebSocketSession> sessionsMap = new ConcurrentHashMap<>();
//	private static final Set<WebSocketSession> connectedSessions = Collections.synchronizedSet(new HashSet<>());
//	
////	@Override
////	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
////		// 儲存session
////		connectedSessions.add(session);
////		Map<String, Object> attributes = session.getAttributes();
////        String userId = attributes.get("userId").toString();
////        sessionsMap.put(userId, session);
////        System.out.println(userId);
////        
////        // 傳送資料
////        String json = gson.toJson(service.getByMemberNo(1));
////		session.sendMessage(new TextMessage(json));
////	}
//	
//	@OnOpen
//    public void onOpen(Session session, @PathParam("userId") String userId) {
//        // 处理用户信息
//        System.out.println("User ID: " + userId);
//    }
//	public void sendMessageToClient(String username, String message) {
//        String destination = "/user/" + username + "/queue/notifications";
////        messagingTemplate.convertAndSend(destination, message);
//    }
////	@OnOpen
////	public void onOpen(Integer memberNo, Session userSession) throws IOException {
////		connectedSessions.add(userSession);
////		String jsonObj = gson.toJson(service.getByMemberNo(memberNo));
////		System.out.println(jsonObj);
////	}
////
////	@OnMessage
////	public void onMessage(Session userSession, String message) {
////		for (Session session : connectedSessions) {
////			if (session.isOpen())
////				session.getAsyncRemote().sendText(message);
////		}
////		System.out.println("Message received: " + message);
////	}
////
////	@OnClose
////	public void onClose(Session userSession, CloseReason reason) {
////		connectedSessions.remove(userSession);
////		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
////				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
////		System.out.println(text);
////	}
////
////	@OnError
////	public void onError(Session userSession, Throwable e) {
////		System.out.println("Error: " + e.toString());
////	}
//
//}
