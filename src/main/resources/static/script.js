const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/chat'
});

stompClient.onConnect = (frame) => {
    console.log('Connected: ', frame);
    stompClient.subscribe("/topic/greetings", (greeting) => {
        const message = (greeting.body)
        showGreeting(message);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function sendName() { //sends name to /app/hello, called by the function in GreetingController
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({'content': $('#message').val()})
    });
}

function showGreeting(message) {
    $("#messages").append(message);
}

$(function () {
   $("#send").click(() => sendName());
});

stompClient.activate();