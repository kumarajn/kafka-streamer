package com.code.test.kafka_streamer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaStreamerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamerApplication.class, args);
    }

}
