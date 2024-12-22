package com.example.ChatApp;

import lombok.Getter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;


public class CustomerServiceClient {


    private WebSocketStompClient stompClient;

    @Getter
    private StompSessionHandlerImpl handler;

    public CustomerServiceClient(String url) {
        stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        handler = new StompSessionHandlerImpl();
        stompClient.connectAsync(url, handler.getHandler());

    }






}
