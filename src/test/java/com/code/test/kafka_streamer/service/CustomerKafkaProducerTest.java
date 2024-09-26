package com.code.test.kafka_streamer.service;

import com.code.test.kafka_streamer.model.Customer;
import com.code.test.kafka_streamer.service.streams.CustomerKafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;

import static com.code.test.kafka_streamer.Constants.CUSTOMER_ODD_TOPIC_NAME;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerKafkaProducerTest {
    @InjectMocks
    private CustomerKafkaProducer customerKafkaProducer;
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_sendMessage() throws JsonProcessingException {
        Customer customer = new Customer();
        LocalDate dob = LocalDate.of(1991, 10, 2);
        customer.setDateOfBirth(dob);
        customer.setFirstName("Raj");
        customer.setLastName("Kumar");

        customerKafkaProducer.sendMessage(customer, CUSTOMER_ODD_TOPIC_NAME);

        // Verify that the customer message send to CustomerODD topic correctly
        verify(kafkaTemplate, times(1)).send(CUSTOMER_ODD_TOPIC_NAME, customer);
    }

    @AfterAll
    public void close() {
        verifyNoMoreInteractions(kafkaTemplate);
    }
}
