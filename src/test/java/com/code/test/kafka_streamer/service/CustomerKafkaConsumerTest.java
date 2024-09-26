package com.code.test.kafka_streamer.service;

import com.code.test.kafka_streamer.model.Customer;
import com.code.test.kafka_streamer.service.streams.CustomerKafkaConsumer;
import com.code.test.kafka_streamer.service.streams.CustomerKafkaProducer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static com.code.test.kafka_streamer.Constants.CUSTOMER_EVEN_TOPIC_NAME;
import static com.code.test.kafka_streamer.Constants.CUSTOMER_ODD_TOPIC_NAME;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerKafkaConsumerTest {
    @InjectMocks
    private CustomerKafkaConsumer customerKafkaConsumer;
    @Mock
    private CustomerKafkaProducer customerKafkaProducer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_customerConsumer_odd_age() {
        Customer customer = new Customer();
        LocalDate dob = LocalDate.of(1991, 9, 2);
        customer.setDateOfBirth(dob);
        customer.setFirstName("Raj");
        customer.setLastName("Kumar");

        customerKafkaConsumer.customerConsumer(customer);
        // Verify that the customer message send to CustomerODD topic correctly
        verify(customerKafkaProducer, times(1)).sendMessage(customer, CUSTOMER_ODD_TOPIC_NAME);
    }

    @Test
    public void test_customerConsumer_even_age() {
        Customer customer = new Customer();
        LocalDate dob = LocalDate.of(1992, 9, 2);
        customer.setDateOfBirth(dob);
        customer.setFirstName("Raj");
        customer.setLastName("Kumar");

        customerKafkaConsumer.customerConsumer(customer);
        // Verify that the customer message send to CustomerEVEN topic correctly
        verify(customerKafkaProducer, times(1)).sendMessage(customer, CUSTOMER_EVEN_TOPIC_NAME);
    }

    @AfterAll
    public void close() {
        verifyNoMoreInteractions(customerKafkaProducer);
    }
}
