package com.fox.chat.userservice.controller;

import com.fox.chat.common.dto.JwtResponseDto;
import com.fox.chat.common.dto.UserDto;
import com.fox.chat.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponseDto> registerUser(@RequestBody UserDto user) {
        JwtResponseDto response = userService.registerUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody UserDto loginUser) {
        JwtResponseDto response = userService.loginUser(loginUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
