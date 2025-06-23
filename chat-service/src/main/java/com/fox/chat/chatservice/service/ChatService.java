package com.fox.chat.chatservice.service;

import com.fox.chat.chatservice.dto.ChatDto;
import com.fox.chat.chatservice.mapper.ChatMapper;
import com.fox.chat.chatservice.repository.ChatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatParticipantService chatParticipantService;

    @Transactional
    public Long createChat(String chatName, List<Long> participants) {
        var chatEntity = ChatMapper.toEntity(chatName, participants);
        var chat = chatRepository.save(chatEntity);
        chatParticipantService.createParticipantsForChat(chat, participants);
        return chat.getId();
    }

    @Transactional
    public Optional<ChatDto> getChatById(Long id) {
        return chatRepository.findById(id).map(ChatMapper::toDto);
    }

    public Boolean chatExists(Long chatId) {
        return chatRepository.existsById(chatId);
    }
}