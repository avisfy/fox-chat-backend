package com.fox.chat.chatservice.repository;

import com.fox.chat.chatservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
