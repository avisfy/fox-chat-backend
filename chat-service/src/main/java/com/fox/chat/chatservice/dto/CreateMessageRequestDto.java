package com.fox.chat.chatservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMessageRequestDto(
        @NotNull Long senderId,
        @NotNull Long chatId,
        @NotBlank String content
) {
}
