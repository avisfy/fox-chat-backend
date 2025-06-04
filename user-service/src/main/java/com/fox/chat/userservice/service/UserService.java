package com.fox.chat.userservice.service;


import com.fox.chat.common.dto.JwtResponseDto;
import com.fox.chat.common.dto.UserDto;

import java.util.Optional;

public interface UserService {
    JwtResponseDto registerUser(UserDto userDto);
    JwtResponseDto loginUser(UserDto dto);
    boolean existsByUsername(String username);
    Optional<UserDto> findByUsername(String username);
    Optional<UserDto> findById(Long id);
}
