package com.example.ChatApp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;



@Configuration
@EnableWebSocketMessageBroker //enables websocket message handling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) { //configure broker
        config.enableSimpleBroker("/topic"); /*enables simple memory-based broker to carry the greetings
                                                                back to client on destinations with prefix /topic */
        config.setApplicationDestinationPrefixes("/app"); //designates the /app prefix for messages bound to @MessageMapping
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").setAllowedOrigins("*"); //registered /chatApp for websocket connections (client and server)
    }
}
