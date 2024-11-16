package com.inu.hackerton.SpringProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
public class ChattingRoom {
    @Id
    private String roomId; // 채팅방 고유 ID
    private String roomName; // 채팅방 이름
    private List<ChatMessage> chatMessages = new ArrayList<>(); // 채팅 메시지 리스트
}
