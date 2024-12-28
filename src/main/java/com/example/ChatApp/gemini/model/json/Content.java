package com.example.ChatApp.gemini.model.json;


import lombok.AllArgsConstructor;
import lombok.Data;



import java.util.List;

@Data
@AllArgsConstructor
public class Content {

    private List<Part> parts;



}
