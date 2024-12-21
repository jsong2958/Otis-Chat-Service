package com.example.ChatApp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello") //when message sent to /hello, the method is called. Because of config, it will be /app/hello
    @SendTo("/topic/greetings") //return Greeting is sent to /topic/greetings, which will be re-rendered in browser
    public String onMessage(Message message) {
        System.out.println("OnMessage() called");
        System.out.println("Recieved message: " + message.getContent());
        return HtmlUtils.htmlEscape(message.getContent());
    }
}
