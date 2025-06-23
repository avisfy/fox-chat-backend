package com.fox.chat.chatservice.controller;

import com.fox.chat.chatservice.dto.CreateMessageRequestDto;
import com.fox.chat.chatservice.service.MessageService;
import com.fox.chat.common.dto.MessageDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
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

//    @GetMapping
//    public ResponseEntity<Page<MessageDto>> getMessagePageById(
//            @RequestParam Long chatId,
//            @RequestParam(defaultValue = "0") Long page,
//            @@RequestParam(defaultValue = "0") Long size) {
//        //TODO
//    }
}
