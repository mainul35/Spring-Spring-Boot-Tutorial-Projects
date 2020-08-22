package com.mainul35.controller;

import com.mainul35.model.ChatMessage;
import com.mainul35.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app/rabbitmq/")
public class RabbitMQWebController {

    @Autowired
    RabbitMQSender rabbitMQSender;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) {

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(message);
        rabbitMQSender.send(chatMessage);

        return "Message sent to the RabbitMQ Chat App Successfully";
    }
}
