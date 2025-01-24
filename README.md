
# Otis Chat Service

A real-time customer service chat application built with **Spring**, utilizing **WebSocket**

Otis is a character from my favorite mobile game, Brawl Stars. He was chosen as the project mascot because he conveniently looks right up at the chat UI.

![Otis](src/main/resources/static/otis.png)





## Key Features

- Real-time messaging
- Initial chat with Google's Gemini AI, prompted to provide service
- A UI built with HTML/CSS and JavaScript for API handling
- Switch button from AI to server-side chat



## WebSocket Endpoints

#### /app/message

```http
Endpoint for which messages are sent to for client-server chatting
```


#### /app/ai-message

```http
Endpoint for which messages are sent to for client-AI chatting
```



#### /switch
```http
Endpoint for which the server knows when to switch off of Gemini AI
```

## WebSocket Subscription Topics

#### /topic/messages

```http
Topic where clients are subscribed to receive chat messages
```


#### /topic/ai-message

```http
Topic where clients are subscribed to receive AI chat messages
```



## Lessons Learned

Learned the basics of WebSocket configuration, STOMP protocol, and utilizing Spring Boot for uses beyond RESTful APIs.

