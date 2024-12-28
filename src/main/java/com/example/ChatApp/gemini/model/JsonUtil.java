package com.example.ChatApp.gemini.model;

import com.example.ChatApp.gemini.model.json.Content;
import com.example.ChatApp.gemini.model.json.JsonResponse;
import com.example.ChatApp.gemini.model.json.Part;
import com.example.ChatApp.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



import java.util.List;


public class JsonUtil {

    private final ObjectMapper mapper;

    public JsonUtil(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    public String createJson(Message msg) {
        try {
            Part part = new Part(msg);
            Content content = new Content(List.of(part));
            JsonResponse response = new JsonResponse(List.of(content));

            return mapper.writeValueAsString(response);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
