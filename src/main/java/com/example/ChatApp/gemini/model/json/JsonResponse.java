package com.example.ChatApp.gemini.model.json;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class JsonResponse {

    private List<Content> contents;

}
