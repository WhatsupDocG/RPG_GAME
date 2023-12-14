package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CharacterProducer {

    private final KafkaTemplate<String, Character> kafkaTemplate;
    private final String characterTopic = "character-topic";

    @Autowired
    public CharacterProducer(KafkaTemplate<String, Character> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCharacterInfo(Character characterInfo) {
        kafkaTemplate.send(characterTopic, characterInfo);
    }
}