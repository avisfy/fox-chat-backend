package com.fox.chat.common.event;


public record MessageSentEvent(
        Long chatId,
        Long senderId,
        String content
) {
}
