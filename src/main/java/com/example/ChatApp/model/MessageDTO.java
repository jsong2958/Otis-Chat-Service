package com.example.ChatApp.model;

import lombok.Data;

@Data
public class MessageDTO {

    private Integer id;
    private String user;
    private String messageContent;

    public MessageDTO(Message msg) {
        this.id = msg.getId();
        this.user = msg.getUser();
        this.messageContent = msg.getMessageContent();
    }

}
