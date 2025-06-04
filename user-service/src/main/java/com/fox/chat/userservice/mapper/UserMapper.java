package com.fox.chat.userservice.mapper;

import com.fox.chat.common.dto.UserDto;
import com.fox.chat.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User entity) {
        return new UserDto(entity.getId(), entity.getUsername(), entity.getPassword(), entity.isEnabled());
    }

    public User toEntity(UserDto dto) {
        return new User(dto.id(), dto.username(), dto.password(), dto.isEnabled());
    }
}
