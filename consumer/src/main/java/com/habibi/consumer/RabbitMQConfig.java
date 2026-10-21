package com.habibi.consumer;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "antrean_pesanan";

    @Bean
    public Queue myQueue() {
        // true artinya antrean tidak akan hilang meskipun container RabbitMQ di-restart
        return new Queue(QUEUE_NAME, true);
    }
}