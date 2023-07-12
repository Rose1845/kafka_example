package com.example.kafkaexample.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
     private String bootstrapservers;


    public Map<String,Object> consumerConfig(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapservers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_DOC,StringSerializer.class);
        return props;
    }

   @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>>
  kafkaListenerContainerFactory(ConsumerFactory<String,String> consumerFactory){
      ConcurrentKafkaListenerContainerFactory<String,String> concurrentKafkaListenerContainerFactory =
              new ConcurrentKafkaListenerContainerFactory<>();
      concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
      return concurrentKafkaListenerContainerFactory;

  }
}
