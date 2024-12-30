package com.example.ChatApp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Message {


    private String user;


    private String messageContent;



    public Message(String msg) {
        this.messageContent = msg;
    }



}
