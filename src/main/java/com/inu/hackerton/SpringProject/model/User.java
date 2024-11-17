
package com.inu.hackerton.SpringProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ManyToAny;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "_user")
@Data
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    //@ElementCollection
    //@CollectionTable(name = "user_chatting_rooms", joinColumns = @JoinColumn(name = "user_id"))
    //@Column(name = "chatting_room")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_room",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private Set<ChattingRoom> chattingRooms;
}