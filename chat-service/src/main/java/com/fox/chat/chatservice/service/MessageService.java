package com.fox.chat.chatservice.service;

import com.fox.chat.chatservice.exception.NoSuchChatException;
import com.fox.chat.chatservice.mapper.MessageMapper;
import com.fox.chat.chatservice.repository.MessageRepository;
import com.fox.chat.common.dto.MessageDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatService chatService;

    @Transactional
    public Long createMessage(Long senderId, Long chatId, String content) {
        var isChatExist = chatService.chatExists(chatId);
        if (!isChatExist)
            throw new NoSuchChatException(chatId);

        var messageEntity = MessageMapper.toEntity(senderId, chatId, content);
        return messageRepository.save(messageEntity).getId();
    }

    @Transactional
    public Optional<MessageDto> getMessageById(Long id) {
        return messageRepository.findById(id).map(MessageMapper::toDto);
    }

}