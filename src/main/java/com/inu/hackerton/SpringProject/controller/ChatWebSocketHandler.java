package com.inu.hackerton.SpringProject.controller;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.HashSet;
import java.util.Set;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = new HashSet<>(); // 연결된 클라이언트 세션을 관리

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session); // 새로 연결된 세션을 추가
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 받은 메시지를 모든 클라이언트에게 전송
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) { // 세션이 열려 있는 경우
                webSocketSession.sendMessage(message); // 모든 세션에 메시지 전송
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session); // 연결이 종료된 세션을 제거
    }
}