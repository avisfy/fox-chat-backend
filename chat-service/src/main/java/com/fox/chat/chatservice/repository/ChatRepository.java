package com.fox.chat.chatservice.repository;

import com.fox.chat.chatservice.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}