package com.code.test.kafka_streamer.service.streams;

import com.code.test.kafka_streamer.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerKafkaProducer.class);
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Object message, String topicName) {
        kafkaTemplate.send(topicName, message);
        LOGGER.info("Message sent:  -> " + message + "to Topic : " + topicName);
    }

}
