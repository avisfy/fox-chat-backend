package com.fox.chat.chatservice.dto;

import com.fox.chat.common.dto.MessageDto;

import java.util.List;

public record MessagePageResponse(
        List<MessageDto> messages,
        boolean hasNext
) {
}
