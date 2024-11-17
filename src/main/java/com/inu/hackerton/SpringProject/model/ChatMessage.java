package com.inu.hackerton.SpringProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Entity
@Data
@Accessors(chain = true)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키 (필수)

    private String content;
    private String sender;
    private MessageType type;
    private Instant timestamp;
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

}