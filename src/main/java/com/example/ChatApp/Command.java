package com.example.ChatApp;



public interface Command<I, O>{
    O execute(I input);
}
