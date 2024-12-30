package com.example.ChatApp.services;

import com.example.ChatApp.Command;
import com.example.ChatApp.model.MessageDTO;
import com.example.ChatApp.model.Message;
import org.springframework.stereotype.Service;


@Service
public class PostMessageService implements Command<Message, MessageDTO> {

    @Override
    public MessageDTO execute(Message msg) {
        return new MessageDTO(msg);
    }
}
