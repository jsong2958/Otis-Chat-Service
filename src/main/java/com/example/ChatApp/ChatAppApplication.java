package com.example.ChatApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


import java.io.IOException;



@SpringBootApplication
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
