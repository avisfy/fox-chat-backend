package com.fox.chat.common.dto;

public record UserDto(Long id, String username, String password, boolean isEnabled) {
}
