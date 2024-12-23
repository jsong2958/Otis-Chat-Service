package com.example.ChatApp.controller;


import com.example.ChatApp.model.Message;
import com.example.ChatApp.model.MessageDTO;
import com.example.ChatApp.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final postMessageService postMessageService;
    private final getMessagesService getMessagesService;

    public ChatController(postMessageService postMessageService, getMessagesService getMessagesService) {
        this.postMessageService = postMessageService;
        this.getMessagesService = getMessagesService;
    }

    @MessageMapping("/hello") //when message sent to /hello, the method is called. Because of config, it will be /app/hello
    @SendTo("/topic/messages") //return Greeting is sent to /topic/greetings, which will be re-rendered in browser
    @PostMapping("/messages")
    public MessageDTO onMessage(@RequestBody Message message) {
        postMessageService.execute(message);
        return new MessageDTO(message);
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return getMessagesService.execute(null);
    }


    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("New connection");
    }
}
