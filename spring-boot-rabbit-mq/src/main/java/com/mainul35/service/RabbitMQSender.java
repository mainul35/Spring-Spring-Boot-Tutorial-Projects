package com.mainul35.service;

import com.mainul35.model.ChatMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing.key}")
    private String routingkey;
//	String kafkaTopic = "java_in_use_topic";

    public void send(ChatMessage chatMessage) {
        amqpTemplate.convertAndSend(exchange, routingkey, chatMessage);
        System.out.println("Send msg = " + chatMessage);

    }
}