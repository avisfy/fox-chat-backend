package com.fox.chat.chatservice.service;

import com.fox.chat.chatservice.entity.Message;
import com.fox.chat.chatservice.exception.UserNotInChatException;
import com.fox.chat.chatservice.kafka.MessageProducerService;
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
    private final ChatParticipantService chatParticipantService;
    private final MessageProducerService messageProducerService;

    @Transactional
    public MessageDto createMessage(Long senderId, Long chatId, String content) {
        chatService.chatExistsValidation(chatId);
        //User can't send a message if they are not a participant in this chat
        var userInChat = chatParticipantService.checkIfUserInChat(chatId, senderId);
        if (!userInChat)
            throw new UserNotInChatException(senderId, chatId);

        var messageEntity = MessageMapper.toEntity(senderId, chatId, content);
        messageEntity = messageRepository.save(messageEntity);
        var savedMessage = MessageMapper.toDto(messageEntity);
        messageProducerService.sendMessage(savedMessage);
        return savedMessage;
    }

    @Transactional(readOnly = true)
    public Optional<MessageDto> getMessageById(Long id) {
        return messageRepository.findById(id).map(MessageMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Slice<MessageDto> getMessagePageByChatId(Long chatId, int page, int size) {
        chatService.chatExistsValidation(chatId);
        Pageable pageSortedByTimestamp = PageRequest.of(page, size, Sort.by("timestamp").descending());
        Slice<Message> messages = messageRepository.findByChatId(chatId, pageSortedByTimestamp);
        return messages.map(MessageMapper::toDto);
    }
}