package com.example.ChatApp;

import com.example.ChatApp.gemini.model.JsonUtil;
import com.example.ChatApp.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


import java.io.IOException;



@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ChatAppApplication {

	public static void main(String[] args) throws InterruptedException, IOException {
		SpringApplication.run(ChatAppApplication.class, args);


		//CustomerServiceClient client = new CustomerServiceClient("ws://localhost:8080/chat", "/app/hello", "Hello my name is Joseph Song");










    }

}
