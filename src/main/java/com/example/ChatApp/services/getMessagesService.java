package com.example.ChatApp.services;


import com.example.ChatApp.model.MessageDTO;
import com.example.ChatApp.repository.MessageRepository;
import com.example.ChatApp.Query;
import com.example.ChatApp.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class getMessagesService implements Query<Void, List<Message>> {

    private final MessageRepository messageRepository;

    public getMessagesService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> execute(Void input) {
        return messageRepository.findAll();
    }
}
