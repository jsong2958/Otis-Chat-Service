package com.example.ChatApp.services;


import com.example.ChatApp.repository.MessageRepository;
import com.example.ChatApp.Query;
import com.example.ChatApp.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMessagesService implements Query<Void, List<Message>> {

    private final MessageRepository messageRepository;

    public GetMessagesService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> execute(Void input) {
        return messageRepository.findAll();
    }
}
