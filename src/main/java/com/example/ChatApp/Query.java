package com.example.ChatApp;

public interface Query<I, O> {
    O execute(I input);
}
