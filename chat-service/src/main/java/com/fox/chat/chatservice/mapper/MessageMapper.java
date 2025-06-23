package com.fox.chat.chatservice.mapper;

import com.fox.chat.chatservice.entity.Chat;
import com.fox.chat.chatservice.entity.Message;
import com.fox.chat.common.dto.MessageDto;

public final class MessageMapper {

    private MessageMapper() {
    }

    public static MessageDto toDto(Message entity) {
        return new MessageDto(
                entity.getId(),
                entity.getChat().getId(),
                entity.getSenderId(),
                entity.getTimestamp(),
                entity.getContent()
        );
    }

    public static Message toEntity(MessageDto dto) {
        var chat = new Chat();
        chat.setId(dto.chatId());
        return new Message(
                dto.id(),
                dto.senderId(),
                dto.timestamp(),
                dto.content(),
                chat
        );
    }

    public static Message toEntity(Long senderId, Long chatId, String content) {
        var chat = new Chat();
        chat.setId(chatId);
        var message = new Message();
        message.setSenderId(senderId);
        message.setChat(chat);
        message.setContent(content);
        return message;
    }
}