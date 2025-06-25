package com.fox.chat.chatservice.controller;

import com.fox.chat.chatservice.dto.ChatDto;
import com.fox.chat.chatservice.dto.CreateChatRequestDto;
import com.fox.chat.chatservice.service.ChatService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/chat")
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<Long> createChat(@Valid @RequestBody CreateChatRequestDto request) {
        var chatId = chatService.createChat(request.chatName(), request.participants());
        return ResponseEntity.status(HttpStatus.CREATED).body(chatId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatDto> getChatById(@PathVariable Long id) {
        var chat = chatService.getChatById(id);
        return chat
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
