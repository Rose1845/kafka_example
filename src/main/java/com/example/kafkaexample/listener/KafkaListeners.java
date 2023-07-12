package com.example.kafkaexample.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private final KafkaTemplate<String ,String> kafkaTemplate;

    public KafkaListeners(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(topics = "rose",groupId = "groupId")
    void listener(String data){
        System.out.println("Listener received" + data + "  ");
    }

}
