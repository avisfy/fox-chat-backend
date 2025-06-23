package com.fox.chat.chatservice.dto;

import java.time.LocalDateTime;

public record ChatDto(
        Long id,
        String chatName,
        boolean isGroupChat,
        LocalDateTime createdAt
) {
}