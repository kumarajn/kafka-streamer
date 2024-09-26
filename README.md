# kafka-streamer
Kafka Streamer (publisher &amp; consumer ) using apache kafka and springboot

Use Case: 
1. Consume message from Customer topic.
2. publish Customer data to topics CustomerODD if customer age is odd compare to current date.
3. publish Customer data to topics CustomerEVEN if customer age is EVEN compare to current date.

This Kafka Streamer application is built using SpringBoot , spring-kafka and kafka-streams.

Local Setup required:
1. Apache kafka window setup
2. Zookeeper
3. Create Topics Customer, CustomerODD , CustomerEVEN
4. postman to publish customer payload to Customer TOPIC
5. Maven
6. JDK 17
7. Intellij

Steps to RUN

1. Clone code in your local directory
2. run mvn clean install
3. run KafkaStreamerApplication

Test Cases :
it has been tested locally with apache kafak.
Below Test evidence file is attached
Kafka-Streamer-Test-Evidence.docx

TDD
Unit Test : Springboot Test & Mockito