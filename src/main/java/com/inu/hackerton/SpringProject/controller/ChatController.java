package com.inu.hackerton.SpringProject.controller;

import com.inu.hackerton.SpringProject.dto.roomDTO;
import com.inu.hackerton.SpringProject.model.ChatMessage;
import com.inu.hackerton.SpringProject.model.ChattingRoom;
import com.inu.hackerton.SpringProject.model.User;
import com.inu.hackerton.SpringProject.repository.ChattingRoomRepository;
import com.inu.hackerton.SpringProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final UserRepository userRepository;
    private final ChattingRoomRepository chattingRoomRepository;

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

    @MessageMapping("/getRooms")
    public String[] getRooms(User chatRequest) {
        // chatRequest 객체에서 username을 가져옵니다
        System.out.println("hihi");
        String username = chatRequest.getUsername();
        User user = userRepository.findByUsername(username).orElse(null);
        // username에 따른 채팅방 목록을 반환하는 로직
        String[] rooms = user.getChattingRooms().toArray(new String[0]);
        System.out.println("hi");
        return rooms;  // 서버에서 채팅방 목록을 반환
    }

    @Transactional
    @MessageMapping("/createRoom")
    public void createRoom(roomDTO room) {
        System.out.println("Room created: " + room.getRoomName());
        System.out.println(room.getRoomName() + " + " + room.getUserName());

        ChattingRoom newChattingRoom = new ChattingRoom();
        newChattingRoom.setRoomName(room.getRoomName());
        newChattingRoom.setUserCount(1);

        ChattingRoom savedRoom = chattingRoomRepository.save(newChattingRoom);

        String username = room.getUserName();

        User user = userRepository.findByUsername(username).orElse(null);


        user.getChattingRooms().add(savedRoom);
        userRepository.save(user);
    }
}
