package com.fox.chat.common.dto;

import java.time.LocalDateTime;

public record MessageDto(
        Long id,
        Long chatId,
        Long senderId,
        LocalDateTime timestamp,
        String content) {
}