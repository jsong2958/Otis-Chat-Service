package com.example.ChatApp;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="chat")
@NoArgsConstructor
public class messageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "message_content")
    private String messageContent;

    public messageModel(String msg) {
        this.messageContent = msg;
    }

}
