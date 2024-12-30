package com.example.ChatApp.model;

import lombok.Data;

@Data
public class MessageDTO {


    private String user;
    private String messageContent;

    public MessageDTO(Message msg) {
        this.user = msg.getUser();
        this.messageContent = msg.getMessageContent();
    }

}
