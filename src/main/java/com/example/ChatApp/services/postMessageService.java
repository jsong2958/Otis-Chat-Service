package com.example.ChatApp.services;

import com.example.ChatApp.Command;
import com.example.ChatApp.repository.MessageRepository;
import com.example.ChatApp.model.Message;
import org.springframework.stereotype.Service;


@Service
public class postMessageService implements Command<Message, Void> {

    private final MessageRepository messageRepository;

    public postMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Void execute(Message msg) {
        messageRepository.save(msg);
        return null;
    }
}
