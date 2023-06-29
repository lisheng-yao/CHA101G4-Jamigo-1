package com.jamigo.counter.counterActivityPush.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.jamigo.counter.counterActivityPush.controller.NewCounterActivityPushHandler;



@ComponentScan("com.jamigo.counter.*.*")
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(), "/websocket")
                .setAllowedOrigins("*"); // 設置允許跨域來源
    }

    public WebSocketHandler myWebSocketHandler() {
        return new NewCounterActivityPushHandler();
    }
}

