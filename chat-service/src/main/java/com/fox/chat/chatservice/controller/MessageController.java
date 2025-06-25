package com.fox.chat.chatservice.controller;

import com.fox.chat.chatservice.dto.CreateMessageRequestDto;
import com.fox.chat.chatservice.dto.MessagePageResponse;
import com.fox.chat.chatservice.service.MessageService;
import com.fox.chat.common.dto.MessageDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Long> createMessage(@Valid @RequestBody CreateMessageRequestDto request) {
        var id = messageService.createMessage(request.senderId(), request.chatId(), request.content());
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable Long id) {
        var message = messageService.getMessageById(id);
        return message
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<MessagePageResponse> getMessagePageByChatId(
            @RequestParam Long chatId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) {
        var messagesSlice = messageService.getMessagePageByChatId(chatId, page, size);
        var responseBody = new MessagePageResponse(messagesSlice.getContent(), messagesSlice.hasNext());
        return ResponseEntity.ok(responseBody);
    }
}
