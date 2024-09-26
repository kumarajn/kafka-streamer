package com.code.test.kafka_streamer.service.streams;

import com.code.test.kafka_streamer.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.code.test.kafka_streamer.Constants.*;

@Service
public class CustomerKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerKafkaConsumer.class);

    @Autowired
    private CustomerKafkaProducer customerKafkaProducer;

    @KafkaListener(topics = CUSTOMER_TOPIC_NAME, groupId = CUSTOMER_GROUP_ID)
    public void customerConsumer(@Payload Customer customer) {
        LOGGER.info(String.format("Consuming message from topic -> %s", CUSTOMER_TOPIC_NAME));
        LOGGER.info(String.format("Message received -> %s", customer));
        // Calculate age from dateOfBirth
        int calculatedAge = customer.calculateAge();

        // Send to appropriate topic based on calculated age
        if (calculatedAge % 2 == 0)
            customerKafkaProducer.sendMessage(customer, CUSTOMER_EVEN_TOPIC_NAME);
        else
            customerKafkaProducer.sendMessage(customer, CUSTOMER_ODD_TOPIC_NAME);

    }
}
