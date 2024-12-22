package com.example.ChatApp.services;

import com.example.ChatApp.Command;
import com.example.ChatApp.Message;
import com.example.ChatApp.MessageRepository;
import com.example.ChatApp.messageModel;
import org.springframework.stereotype.Service;


@Service
public class postMessageService implements Command<Message, Void> {

    private final MessageRepository messageRepository;

    public postMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Void execute(Message msg) {
        messageModel message =  new messageModel(msg.getContent());
        messageRepository.save(message);
        return null;
    }
}
