package com.fox.chat.userservice.service;


import com.fox.chat.common.dto.JwtResponseDto;
import com.fox.chat.common.dto.UserDto;

public interface UserService {
    JwtResponseDto registerUser(UserDto userDto);
    JwtResponseDto loginUser(UserDto dto);;
    UserDto findById(Long id);
}
