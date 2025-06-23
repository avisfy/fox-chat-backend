package com.fox.chat.chatservice.repository;

import com.fox.chat.chatservice.entity.ChatParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatParticipantRepository extends JpaRepository<ChatParticipant, Long> {
}