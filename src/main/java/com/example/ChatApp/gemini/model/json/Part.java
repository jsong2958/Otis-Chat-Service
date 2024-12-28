package com.example.ChatApp.gemini.model.json;

import com.example.ChatApp.model.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
public class Part {

    private String text;


    public Part(Message msg) {
        this.text = msg.getMessageContent();
    }




}
