package com.example.ChatApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ChatAppApplication {

	public static void main(String[] args) throws InterruptedException, IOException {
		SpringApplication.run(ChatAppApplication.class, args);


		//CustomerServiceClient client = new CustomerServiceClient("ws://localhost:8080/chat", "/app/hello", "Hello my name is Joseph Song");


		try {
			if (System.getProperty("os.name").contains("Windows")) {

				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

			} else {

				Runtime.getRuntime().exec("clear");

			}


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }

}
