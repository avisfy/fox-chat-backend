package com.fox.chat.chatservice.kafka;

import com.fox.chat.chatservice.mapper.MessageEventMapper;
import com.fox.chat.common.dto.MessageDto;
import com.fox.chat.common.event.MessageSentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageProducerService {

    @Value(value = "${spring.kafka.producer.topic.sent}")
    private String topicNameSent;

    private final KafkaTemplate<String, MessageSentEvent> kafkaTemplate;

    public void sendMessage(MessageDto messageDto) {
        var messageEvent = MessageEventMapper.dtoToEvent(messageDto);
        kafkaTemplate.send(topicNameSent, String.valueOf(messageDto.chatId()), messageEvent);
    }
}
