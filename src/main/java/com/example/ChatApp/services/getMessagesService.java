package com.example.ChatApp.services;


import com.example.ChatApp.MessageRepository;
import com.example.ChatApp.Query;
import com.example.ChatApp.messageModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class getMessagesService implements Query<Void, List<messageModel>> {

    private final MessageRepository messageRepository;

    public getMessagesService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<messageModel> execute(Void input) {
        return messageRepository.findAll();
    }
}
