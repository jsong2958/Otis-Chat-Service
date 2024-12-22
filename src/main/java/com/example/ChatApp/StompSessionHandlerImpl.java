package com.example.ChatApp;

import lombok.Getter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.util.concurrent.CountDownLatch;

@Getter
public class StompSessionHandlerImpl extends StompSessionHandlerAdapter {

    private StompSessionHandlerAdapter handler;
    private StompSession stompSession;
    private CountDownLatch latch;

    public StompSessionHandlerImpl() {
        this.latch = new CountDownLatch(1);

        this.handler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                stompSession = session;
                System.out.println("Connected! XD");
                session.subscribe("/topic/greetings", this);
                latch.countDown();
            }



            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                Message  msg = (Message) payload;
                System.out.println("Recieved :" + msg.getContent());
            }
        };


    }

    public void awaitConnection() throws InterruptedException {
        latch.await();
    }
    public void sendMessage(StompSession session, Message message) {
        session.send("/app/hello", message.getContent());
    }


}
