package com.example.ChatApp;

import lombok.Getter;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;

@Getter
public class StompSessionHandlerImpl {

    protected StompSessionHandlerAdapter handler;

    public StompSessionHandlerImpl(String destination, String message) {
        this.handler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                System.out.println("Connected");
                try {
                    session.send(destination, message);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            @Override
            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
                exception.printStackTrace();
            }

            @Override
            public void handleTransportError(StompSession session, Throwable exception) {
                exception.printStackTrace();
            }

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return String.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                System.out.println("recieved from handleframe: " + (String.valueOf(payload)));
            }
        };
    }



}
