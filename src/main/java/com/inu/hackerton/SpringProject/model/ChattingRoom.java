package com.inu.hackerton.SpringProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "_ChattingRoom")
@Accessors(chain = true)
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private long id; // 채팅방 고유 ID
    private String roomName; // 채팅방 이름
    private long userCount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "room_id") // 외래 키
    private List<ChatMessage> chatMessages = new ArrayList<>(); // 채팅 메시지 리스트

    @ManyToMany(mappedBy = "chattingRooms")
    private Set<User> users;


}
