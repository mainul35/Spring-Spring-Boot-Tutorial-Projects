package com.mainul35.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mainul35.model.ChatMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String messageBody = new String(message.getBody());
        ObjectMapper mapper = new ObjectMapper();
        ChatMessage chatMessage = null;
        try {
            chatMessage = mapper.readValue(messageBody, ChatMessage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(chatMessage.toString());
    }
}
