package com.fox.chat.userservice.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("User %s already exists".formatted(username));
    }
}
