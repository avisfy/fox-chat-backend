package com.fox.chat.messageservice.controller;

import com.fox.chat.messageservice.entity.Message;
import com.fox.chat.messageservice.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat-api/v1/message")
@AllArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;

    @PostMapping("/create")
    public ResponseEntity<Long> createMessage(@RequestBody Message message) {
        var id = messageRepository.save(message).getId();
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        var message = messageRepository.findById(id);
        return message
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
