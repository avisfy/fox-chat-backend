package com.fox.chat.chatservice.service;

import com.fox.chat.chatservice.entity.Message;
import com.fox.chat.chatservice.mapper.MessageMapper;
import com.fox.chat.chatservice.repository.MessageRepository;
import com.fox.chat.common.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatService chatService;

    @Transactional
    public Long createMessage(Long senderId, Long chatId, String content) {
        chatService.checkChatExists(chatId);
        //TODO check if sender participate in this chat
        var messageEntity = MessageMapper.toEntity(senderId, chatId, content);
        return messageRepository.save(messageEntity).getId();
    }

    @Transactional(readOnly = true)
    public Optional<MessageDto> getMessageById(Long id) {
        return messageRepository.findById(id).map(MessageMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Slice<MessageDto> getMessagePageByChatId(Long chatId, int page, int size) {
        chatService.checkChatExists(chatId);
        Pageable pageSortedByTimestamp = PageRequest.of(page, size, Sort.by("timestamp").descending());
        Slice<Message> messages = messageRepository.findByChatId(chatId, pageSortedByTimestamp);
        return messages.map(MessageMapper::toDto);
    }
}