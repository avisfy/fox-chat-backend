package com.fox.chat.chatservice.repository;

import com.fox.chat.chatservice.entity.ChatParticipant;
import org.springframework.data.repository.CrudRepository;

public interface ChatParticipantRepository extends CrudRepository<ChatParticipant, Long> {
    boolean existsByUserIdAndChatId(Long userId, Long chatId);
}