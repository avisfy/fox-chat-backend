package com.fox.chat.chatservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.producer.topic.sent}")
    private String topicNameSent;

    @Bean
    public NewTopic sentMessageTopic() {
        return TopicBuilder.name(topicNameSent)
                .partitions(5)
                .replicas(1)
                .build();
    }
}
