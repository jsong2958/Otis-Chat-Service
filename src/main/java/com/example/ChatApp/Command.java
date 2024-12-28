package com.example.ChatApp;


import com.fasterxml.jackson.core.JsonProcessingException;

public interface Command<I, O>{
    O execute(I input) throws JsonProcessingException;
}
