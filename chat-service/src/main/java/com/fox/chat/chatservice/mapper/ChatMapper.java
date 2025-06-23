package com.fox.chat.chatservice.mapper;

import com.fox.chat.chatservice.dto.ChatDto;
import com.fox.chat.chatservice.entity.Chat;

import java.util.List;

public final class ChatMapper {

    private ChatMapper() {
    }

    public static ChatDto toDto(Chat chat) {
        return new ChatDto(chat.getId(), chat.getChatName(), chat.isGroupChat(), chat.getCreatedAt());
    }

    public static Chat toEntity(ChatDto chatDto) {
        return new Chat(chatDto.id(), chatDto.isGroupChat(), chatDto.chatName(), chatDto.createdAt());
    }

    public static Chat toEntity(String chatName, List<Long> participants) {
        var chatEntity = new Chat();
        var isGroupChat = participants.size() > 2;
        chatEntity.setGroupChat(isGroupChat);
        chatEntity.setChatName(chatName);
        return chatEntity;
    }
}
