package com.example.ChatApp.services;

import com.example.ChatApp.Command;
import com.example.ChatApp.model.MessageDTO;
import com.example.ChatApp.repository.MessageRepository;
import com.example.ChatApp.model.Message;
import org.springframework.stereotype.Service;


@Service
public class PostMessageService implements Command<Message, MessageDTO> {

    private final MessageRepository messageRepository;

    public PostMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageDTO execute(Message msg) {
        messageRepository.save(msg);
        return new MessageDTO(msg);
    }
}
