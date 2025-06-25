package com.fox.chat.chatservice.service;

import com.fox.chat.chatservice.entity.Chat;
import com.fox.chat.chatservice.entity.ChatParticipant;
import com.fox.chat.chatservice.repository.ChatParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatParticipantService {

    private final ChatParticipantRepository chatParticipantRepo;

    @Transactional
    public void createParticipantsForChat(Chat chat, List<Long> usersId) {
        List<ChatParticipant> chatParticipant = usersId
                .stream()
                .map(uId -> new ChatParticipant(null, uId, chat))
                .toList();
        chatParticipantRepo.saveAll(chatParticipant);
    }
}