package com.example.ChatApp.gemini.services;

import com.example.ChatApp.Command;
import com.example.ChatApp.controller.ChatController;
import com.example.ChatApp.gemini.model.JsonUtil;
import com.example.ChatApp.model.Message;

import com.example.ChatApp.model.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AiResponseService implements Command<Message, MessageDTO> {

    @Value("${api.key}")
    private String API_KEY;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(AiResponseService.class);



    public AiResponseService(RestTemplate restTemplate,
                             ObjectMapper objectMapper
                             ) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;

    }

    public Message createPrompt(Message message) {
        message.setMessageContent("You are an assistant that provides helpful information on a given query" +
                "Please generate a detailed response based on this message: " + message.getMessageContent());
        return message;
    }

    @Override
    public MessageDTO execute(Message msg) throws JsonProcessingException {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;
        JsonUtil json = new JsonUtil(this.objectMapper);

        HttpEntity<String> entity = new HttpEntity<>(json.createJson(createPrompt(msg)));

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        JsonNode rootNode = objectMapper.readTree(response.getBody());

        Message message = new Message(rootNode.get("candidates").get(0).get("content").get("parts").get(0).get("text").asText());
        logger.info(message.getMessageContent());
        return new MessageDTO(message);
    }
}
