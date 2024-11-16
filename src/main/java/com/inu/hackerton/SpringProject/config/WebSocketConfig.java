package com.inu.hackerton.SpringProject.config;

import com.inu.hackerton.SpringProject.controller.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration  // 이 클래스가 Spring의 설정 클래스임을 나타냄
@EnableWebSocket  // WebSocket 기능을 활성화함
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // WebSocket 핸들러를 등록하고, "/chat" 경로에서 사용할 수 있도록 설정
        registry.addHandler(new ChatWebSocketHandler(), "/chat")
                .setAllowedOrigins("*");  // 모든 출처에서의 요청을 허용
    }
}