package com.inu.hackerton.SpringProject.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration  // 이 클래스가 설정 클래스임을 나타냄
@EnableWebSocketMessageBroker  // WebSocket 메시지 브로커를 활성화하여 WebSocket을 통해 메시지를 주고받을 수 있게 함
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 메시지 브로커 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트에서 "/topic"으로 시작하는 메시지를 구독하면 서버가 이를 브로드캐스트함
        registry.enableSimpleBroker("/topic");
        // 클라이언트에서 "/app"으로 시작하는 경로로 메시지를 보내면, 해당 메시지는 애플리케이션에서 처리됨
        registry.setApplicationDestinationPrefixes("/app");
    }

    // STOMP 엔드포인트 설정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // "/ws" URL로 WebSocket 연결을 설정하고, SockJS를 통해 연결이 끊어지면 자동으로 재연결함
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")  // 모든 출처에서의 요청을 허용
                .withSockJS();  // SockJS를 사용하여 브라우저가 WebSocket을 지원하지 않아도 연결이 가능하게 함
    }
}