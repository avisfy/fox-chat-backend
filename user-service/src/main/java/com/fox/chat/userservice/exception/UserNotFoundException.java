package com.fox.chat.userservice.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String user) {
        super("User %s not found".formatted(user));
    }

    public UserNotFoundException() {
        super("User not found");
    }
}
