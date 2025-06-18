package com.fox.chat.userservice.controller;

import com.fox.chat.common.dto.UserDto;
import com.fox.chat.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);

    }
}
