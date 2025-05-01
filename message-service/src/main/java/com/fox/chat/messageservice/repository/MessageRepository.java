package com.fox.chat.messageservice.repository;

import com.fox.chat.messageservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
