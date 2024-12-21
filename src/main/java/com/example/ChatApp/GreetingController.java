package com.example.ChatApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @MessageMapping("/hello") //when message sent to /hello, the method is called. Because of config, it will be /app/hello
    @SendTo("/topic/greetings") //return Greeting is sent to /topic/greetings, which will be re-rendered in browser
    public String onMessage(Message message) {
        logger.info("OnMessage() called");
        logger.info("Recieved message: {}", message.getContent());
        return HtmlUtils.htmlEscape(message.getContent());
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("New connections");
    }
}
