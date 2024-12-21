package com.example.ChatApp;


import org.springframework.messaging.simp.stomp.*;

import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.net.URI;


public class CustomerServiceClient {

    private WebSocketStompClient stompClient;
    private StompSessionHandlerImpl handler;

    public CustomerServiceClient(String url, String destination, String message) {
        stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        handler = new StompSessionHandlerImpl(destination, message);
        stompClient.connectAsync(url, handler.getHandler());
    }

}
