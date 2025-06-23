package com.fox.chat.chatservice.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateChatRequestDto(
        String chatName,
        @NotEmpty List<@NotNull Long> participants
) {
}
