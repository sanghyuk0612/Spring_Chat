package com.inu.hackerton.SpringProject.repository;

import com.inu.hackerton.SpringProject.model.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
    Optional<ChattingRoom> findByroomName(String roomName);
}