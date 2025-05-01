package com.fox.chat.userservice.controller;

import com.fox.chat.userservice.entity.User;
import com.fox.chat.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat-api/user")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        var id = userRepository.save(user).getId();
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        var user = userRepository.findById(id);
        return user
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
