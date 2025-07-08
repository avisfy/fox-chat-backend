package com.fox.chat.chatservice.mapper;

import com.fox.chat.common.event.MessageSentEvent;
import com.fox.chat.common.dto.MessageDto;

public class MessageEventMapper {

    private MessageEventMapper() {
    }

    public static MessageSentEvent dtoToEvent(MessageDto messageDto) {
        return new MessageSentEvent(messageDto.chatId(), messageDto.senderId(), messageDto.content());
    }
}
