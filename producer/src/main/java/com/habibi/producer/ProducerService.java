package com.habibi.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void kirimPesan(String pesan) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, pesan);
        System.out.println("Massage sent: " + pesan);
    }
}