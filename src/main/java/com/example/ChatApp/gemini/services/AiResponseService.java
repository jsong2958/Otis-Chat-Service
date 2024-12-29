package com.example.ChatApp.gemini.services;

import com.example.ChatApp.Command;
import com.example.ChatApp.gemini.model.JsonUtil;
import com.example.ChatApp.model.Message;

import com.example.ChatApp.model.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AiResponseService implements Command<Message, MessageDTO> {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;




    public AiResponseService(RestTemplate restTemplate,
                             ObjectMapper objectMapper
                             ) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;

    }


    @Override
    public MessageDTO execute(Message msg) throws JsonProcessingException {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AIzaSyBn76qs9e_EwynYVMUvn2VzD7bDZq3P0Fc";
        JsonUtil json = new JsonUtil(this.objectMapper);

        HttpEntity<String> entity = new HttpEntity<>(json.createJson(msg));

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        JsonNode rootNode = objectMapper.readTree(response.getBody());

        Message message = new Message(rootNode.get("candidates").get(0).get("content").get("parts").get(0).get("text").asText());

        return new MessageDTO(message);
    }
}
