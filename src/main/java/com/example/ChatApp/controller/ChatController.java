package com.example.ChatApp.controller;


import com.example.ChatApp.gemini.services.AiResponseService;
import com.example.ChatApp.model.Message;
import com.example.ChatApp.model.MessageDTO;
import com.example.ChatApp.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.SessionConnectedEvent;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final PostMessageService postMessageService;
    private final GetMessagesService getMessagesService;
    private final AiResponseService aiResponseService;

    private boolean isUsingAi = true;


    public ChatController(PostMessageService postMessageService,
                          GetMessagesService getMessagesService,
                          AiResponseService aiResponseService) {
        this.postMessageService = postMessageService;
        this.getMessagesService = getMessagesService;
        this.aiResponseService = aiResponseService;

    }

    @MessageMapping("/hello") //when message sent to /hello, the method is called. Because of config, it will be /app/hello
    @SendTo("/topic/messages") //new MessageDTO is sent to /topic/greetings, which will be re-rendered in browser
    @PostMapping("/messages") //posts to database
    public MessageDTO onMessage(@RequestBody Message message) throws JsonProcessingException {

        
        if (isUsingAi) {
            postMessageService.execute(message);
            return aiResponseService.execute(message);
        }


    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return getMessagesService.execute(null);
    }

    @MessageMapping("/switch")
    public void switchOffAi() {
        isUsingAi = false;
        logger.info("Switch off Ai");
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("New connection");
    }
}
