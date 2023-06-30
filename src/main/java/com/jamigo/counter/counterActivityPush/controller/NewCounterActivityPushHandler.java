package com.jamigo.counter.counterActivityPush.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

@Component
public class NewCounterActivityPushHandler extends TextWebSocketHandler {

	private static Map<String, WebSocketSession> sessionsMap = new ConcurrentHashMap<>();
	private static final Set<WebSocketSession> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	
	private static Gson gson;
	
	@Autowired
	public void setGson(Gson gson) {
		NewCounterActivityPushHandler.gson = gson;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		Object credentials = session.getPrincipal().getCredentials();

		session.sendMessage(new TextMessage("okkkkkkkkkkkkk"));
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException{
		String payload = message.getPayload();
		
		session.sendMessage(new TextMessage(payload));
	}
	
//	@OnOpen
//    public void onOpen(Session session, @PathParam("memberNo") String userId, HttpServletResponse resp) throws IOException {
//		System.out.println("User ID: " + userId);
//    }
//	
//	@OnMessage
//	public void onMessage(Session userSession, String message) {
//		// 只要有新訊息，就把新訊息傳給線上所有人
//		// I/O已寫在api裡
////		userSession.getAsyncRemote().sendText(message);
//
//		System.out.println("Message received: " + message);
//	}
	
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		// 儲存session
//		connectedSessions.add(session);
//		Map<String, Object> attributes = session.getAttributes();
//	    String userId = attributes.get("userId").toString();
//	    sessionsMap.put(userId, session);
//	    System.out.println(userId);
//	    
//	    // 傳送資料
//	    String json = gson.toJson(service.getByMemberNo(1));
//		session.sendMessage(new TextMessage(json));
//	}
}
