package com.fox.chat.chatservice.exception;

public class NoSuchChatException extends RuntimeException {
    public NoSuchChatException(Long chatId) {
        super("Chat with chatId = %s not found".formatted(chatId));
    }
}