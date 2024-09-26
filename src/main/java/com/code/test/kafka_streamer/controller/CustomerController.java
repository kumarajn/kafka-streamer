package com.code.test.kafka_streamer.controller;


import com.code.test.kafka_streamer.model.Customer;
import com.code.test.kafka_streamer.service.streams.CustomerKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.code.test.kafka_streamer.Constants.CUSTOMER_TOPIC_NAME;

@RestController
@RequestMapping("/kafka/customer")
public class CustomerController {

    @Autowired
    private CustomerKafkaProducer customerKafkaProducer;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody Customer customer) {

        customerKafkaProducer.sendMessage(customer, CUSTOMER_TOPIC_NAME);
        return "Message published successfully!";
    }
}
