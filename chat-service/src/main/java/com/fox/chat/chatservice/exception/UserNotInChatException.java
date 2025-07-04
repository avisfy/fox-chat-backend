package com.fox.chat.chatservice.exception;

public class UserNotInChatException extends RuntimeException {
    public UserNotInChatException(Long userId, Long chatId) {
        super("User %s is not a participant of chat %s".formatted(userId, chatId));
    }
}
