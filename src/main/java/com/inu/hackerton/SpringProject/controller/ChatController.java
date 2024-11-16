package com.inu.hackerton.SpringProject.controller;

import com.inu.hackerton.SpringProject.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @GetMapping
    public String getChatHtml() {
        return "chattering";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage handle(ChatMessage message) {
        System.out.println(message.getContent());
        return message;
    }
}
