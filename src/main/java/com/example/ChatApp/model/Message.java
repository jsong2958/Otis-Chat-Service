package com.example.ChatApp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="chat")
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="user")
    private String user;

    @Column(name = "message_content")
    private String messageContent;



    public Message(String msg) {
        this.messageContent = msg;
    }



}
