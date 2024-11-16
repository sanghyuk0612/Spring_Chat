package com.inu.hackerton.SpringProject.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class ChatMessage {
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